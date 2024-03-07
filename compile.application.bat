mvn clean install compile package -e -X -U

@echo off
SET "baseDir=E:\Proyectos Programacion\backend.services.project-login"

for /d %%p in ("%baseDir%\*") do (
    echo Procesando: %%p
    cd %%p
    mvn clean install compile package
)

echo Todos los proyectos han sido compilados.
pause
