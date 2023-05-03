```java
javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d ./bin ./src/ *.java
java -ea -cp bin:img --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls AppliConverter
javadoc --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d doc -charset utf8 -noqualifier all src/*.java
```