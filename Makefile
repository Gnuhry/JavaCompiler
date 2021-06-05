all: javalexer.java javaparser.java sources.txt spezifikation yyTokenclass.class javascanner.class main.class

javalexer.java: javalexer
	java -cp JLex2.jar JLex2.Main javalexer

javaparser.java: javaparser.jay skeleton.jaooy
    jaooy -v -t  javaparser.jay < skeleton.jaooy > javaparser.java

sources.txt: 
	find -name "* .java" > sources.txt

spezifikation: sources.txt
	javac @ sources.txt

yyTokenclass.class: javaparser.java
    javac javaparser.java

javascanner.class: javalexer.java
    javac javalexer.java

main.class: main.java yyTokenclass.class javascanner.class
    javac main.java

clean:
    rm -f *.class javalexer.java javaparser.java