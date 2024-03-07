# Backend Services EAR

## Descripción

Este proyecto Maven es un módulo Enterprise Application Archive (EAR) diseñado para agrupar y desplegar los componentes EJB y web de los servicios backend como una sola unidad en un entorno Jakarta EE. Utiliza Jakarta EE 10.0.0 y está configurado para Java 11.

## Pre-requisitos

- Java JDK 11 instalado y configurado correctamente en tu máquina.
- Maven para la gestión de dependencias y construcción del proyecto.

## Configuración

Para configurar este proyecto en tu entorno de desarrollo local, sigue estos pasos:

1. Clona el repositorio en tu máquina local.
2. Asegúrate de que Java JDK 11 y Maven estén instalados y configurados correctamente.

## Compilación

Para compilar el proyecto y generar el paquete EAR, ejecuta el siguiente comando en la terminal:


Este comando compilará el proyecto, ejecutará las pruebas y, si son exitosas, generará un archivo `.ear` en el directorio `target`.

## Despliegue

El archivo `.ear` generado puede ser desplegado en cualquier servidor de aplicaciones que soporte Jakarta EE 10. Para desplegar, sigue las instrucciones específicas de tu servidor de aplicaciones.

## Dependencias

- **Jakarta EE API**: Este proyecto depende de la API de Jakarta EE para los componentes web y EJB.
- **Componentes específicos del proyecto**: Dependencias a módulos EJB y WAR específicos de la aplicación están incluidos como parte del ensamblaje EAR.

## Contribuir

Si deseas contribuir a este proyecto, por favor sigue los estándares de código y envía un pull request para su revisión.

