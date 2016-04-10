all: src/CkipTest.class
src/CkipTest.class:src/CkipTest.java
	javac -d ./lib src/CkipTest.java -cp ./lib 
clean:
	rm lib/CkipTest.class
	rm result/output.txt
run:
	java -cp lib CkipTest
	cat result/output.txt
move:
	cp result/output.txt ../crfsuite-0.12/chinese_pos
