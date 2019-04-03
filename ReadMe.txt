JavaFX is no longer part of JDK 11 therefore please consider the below points.
- please add below VM argument in your project configuration: 
  '--module-path "C:\software\openjfx-11.0.2\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls,javafx.fxml,javafx.graphics'
-please replace your javafx-sdk path of your local machine in the above argument.

-this application is runnable with help of Maven please use below command to run it without above configuration:
	mvn compile exec:java

	

