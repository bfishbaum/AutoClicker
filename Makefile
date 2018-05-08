all: AutoClicker.class Cheater.class

%.class: %.java
	javac $< -Xlint:unchecked

clean:
	rm -f *.class
