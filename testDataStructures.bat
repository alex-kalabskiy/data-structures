REM print the results of testing to the specified file 
REM run from the project folder
REM For example to test version of jar 1.3-SNAPSHOT  run "testDataStrucutures 3" from console

set version=%1
java -jar .\target\data-structures-1.%version%-SNAPSHOT.jar > testResult%version%.csv