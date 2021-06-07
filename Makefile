all: clean javalexer.java javaparser.java sources.txt spezifikation moveclasses yyTokenclass.class javascanner.class main.class test
all2: clean pull javalexer.java javaparser.java sources.txt spezifikation moveclasses yyTokenclass.class javascanner.class main.class test

pull:
	git pull
javalexer.java: javalexer
	java -cp JLex2.jar JLex2.Main javalexer

javaparser.java: javaparser.jay skeleton.jaooy
	jaooy -v -t  javaparser.jay < skeleton.jaooy > javaparser.java

sources.txt: 
	find ./src/ -name "*.java" > sources.txt

spezifikation: sources.txt
	javac @sources.txt

moveclasses:
	mv src/*.class .

yyTokenclass.class: javaparser.java
	javac javaparser.java

javascanner.class: javalexer.java
	javac javalexer.java

main.class: main.java yyTokenclass.class javascanner.class
	javac main.java

test:
	java main < TestClass

clean:
	rm -f *.class javalexer.java javaparser.java sources.txt
