@echo off
echo Iniciando la limpieza de archivos y carpetas...

del /Q "C:\Servers\wildfly-30.0.1\standalone\deployments\*"
for /d %%x in ("C:\Servers\wildfly-30.0.1\standalone\deployments\*") do rd /s /q "%%x"

del /Q "C:\Servers\wildfly-30.0.1\standalone\tmp\*"
for /d %%x in ("C:\Servers\wildfly-30.0.1\standalone\tmp\*") do rd /s /q "%%x"

echo La limpieza de archivos y carpetas ha terminado.
pause