@echo off
REM Iniciar server database H2
C:/Users/christian/.jdks/corretto-11.0.25/bin/java.exe -cp /database/h2-*.jar org.h2.tools.Server -tcp -tcpAllowOthers -tcpPort 9092

REM Conectar a la base de datos y ejecutar las consultas SQL

pause