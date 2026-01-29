@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-25
set M2_HOME=c:\tools\apache-maven-3.9.11
call %M2_HOME%\bin\mvn clean test
pause