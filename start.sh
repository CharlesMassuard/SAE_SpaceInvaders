# !bin/bash

javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d ./bin ./src/*.java
java -ea -cp bin --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls Executable