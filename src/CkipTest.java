//package ckiptest;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import tw.cheyingwu.ckip.CKIP;
import tw.cheyingwu.ckip.Term;
import tw.cheyingwu.ckip.WordSegmentationService;
 
public class CkipTest {
	 public static String readFile(String FileName) throws IOException {
		 BufferedReader br = new BufferedReader(new FileReader(FileName));
		 try {
			 StringBuilder sb = new StringBuilder();
			 String line = br.readLine();
			 while(line != null )
			 {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			 }
			 return sb.toString();
		 } finally{
			 br.close();
		 }

	 }
	 public static void main(String[] args) {
int j =1;
for(File docFile : new File("resource/").listFiles()){
j++;
	        WordSegmentationService c; //宣告一個class變數c
	        ArrayList<String> inputList = new ArrayList<>(); //宣告動態陣列 存切詞的name
	        ArrayList<String> TagList = new ArrayList<>();   //宣告動態陣列 存切詞的詞性i
			String s = new String();
	        System.out.println("**********     讀入資源字串    **********");
			try {
				s = readFile(docFile.getAbsolutePath());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			System.out.print(s);
	        System.out.println("********** 使用中研院斷詞伺服器 *********");
	         
	        c = new CKIP( "140.109.19.104" , 1502, "sam961124", "sam961103"); //輸入申請的IP、port、帳號、密碼
	         
	        c.setRawText(s);
	        c.send(); //傳送至中研院斷詞系統服務使用
	        for (Term t : c.getTerm()) {
	           
	            inputList.add(t.getTerm()); // t.getTerm()會讀到斷詞的String，將其存到inputList陣列
	            TagList.add(t.getTag());    // t.getTag() 會讀到斷詞的詞性，將其存到TagList陣列
	        }
		BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
		String input;
		ArrayList<String> BIOList = new ArrayList<>();
		ArrayList<String> BodyList = new ArrayList<>();
		for (int i=0;i<inputList.size();i++) {
		    System.out.print(inputList.get(i)+'\n');
                    try{
 		    	input = cin.readLine();
			if(input.isEmpty())
			{
			    input = "o";
			    BIOList.add(input.toUpperCase());
  		            BodyList.add(input.toUpperCase());
			    continue;
			}
			else if(input.equals("x"))
			{
			    i-=2;
			    BIOList.remove(BIOList.size()-1);
			    BodyList.remove(BodyList.size()-1);
			    continue;
			}
			BIOList.add(input.toUpperCase());
			input = cin.readLine();
  			BodyList.add(input.toUpperCase());
  		    }catch(IOException ex){}
		}
	         
	        //將資料output成檔案
	        try {
	            FileWriter fr1 = new FileWriter("result/symptom/sean_s_"+j+".txt");
	            BufferedWriter bw = new BufferedWriter(fr1);
	            for(int i=0;i<inputList.size();i++)
	            {
	                bw.write(BIOList.get(i) + "-" + TagList.get(i)+"\t");
	                if(i != 0)
	                	bw.write("w[-1]=" + inputList.get(i-1) + "\t");
	                bw.write("w[0]=" + inputList.get(i) + "\t");
	                if(i != inputList.size()-1)
	                	bw.write("w[1]=" + inputList.get(i+1) + "\t");
	                if(i != 0)
	                	bw.write("pos[-1]=" + TagList.get(i-1) + "\t");
	                bw.write("pos[0]=" + TagList.get(i) + "\t");
	                if(i != inputList.size()-1)
	                	bw.write("pos[1]=" + TagList.get(i+1) + "\t");
	                if(i == 0)
	                	bw.write("__BOS__");
	                else if (i == inputList.size()-1 || inputList.get(i).equals("。") )
	                	bw.write("__EOS__");
	                bw.newLine();
	            }
	            bw.close();
	             
	             
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	         try {
	            FileWriter fr1 = new FileWriter("result/body/sean_b_"+j+".txt");
	            BufferedWriter bw = new BufferedWriter(fr1);
	            for(int i=0;i<inputList.size();i++)
	            {
	                bw.write(BodyList.get(i) + "-" + TagList.get(i)+"\t");
	                if(i != 0)
	                	bw.write("w[-1]=" + inputList.get(i-1) + "\t");
	                bw.write("w[0]=" + inputList.get(i) + "\t");
	                if(i != inputList.size()-1)
	                	bw.write("w[1]=" + inputList.get(i+1) + "\t");
	                if(i != 0)
	                	bw.write("pos[-1]=" + TagList.get(i-1) + "\t");
	                bw.write("pos[0]=" + TagList.get(i) + "\t");
	                if(i != inputList.size()-1)
	                	bw.write("pos[1]=" + TagList.get(i+1) + "\t");
	                if(i == 0)
	                	bw.write("__BOS__");
	                else if (i == inputList.size()-1 || inputList.get(i).equals("。") )
	                	bw.write("__EOS__");
	                bw.newLine();
	            }
	            bw.close();
	             
	             
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

}
}
