
JAVAC = javac
CLASS = *.java

OBJ = *.class

JAVA = java -cp . 
MAIN = Main 


compilar:
	$(JAVAC) $(CLASS)

run:
	$(JAVA) $(MAIN)

all:
	$(JAVAC) $(CLASS)
	$(JAVA) $(MAIN)

.PHONY: clear

clear:
	rm $(OBJ)