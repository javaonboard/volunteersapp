Execution options: JavaFX is no longer part of JDK 11 and it provided in standalone SDK therefore, please consider the below points.
Fat Jar
-Fat Jar File has been created volunteersapp-1.0-SNAPSHOT-jar-with-dependencies.jar
please use this command in terminal at <Project>\target>$ java -jar volunteersapp-1.0-SNAPSHOT-jar-with-dependencies.jar
In case: For recreate the Fat Jar <Project>$ mvn clean install

Maven
-This application is runnable with help of Maven please use below command to run it.
<Project>$ mvn clean compile exec:java




	

