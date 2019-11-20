call mvn clean compile assembly:single
echo clean compile complete
call mvn test
echo tests complete
cd target/
call java -jar simpleSpringApp-0.0.1-jar-with-dependencies.jar
pause