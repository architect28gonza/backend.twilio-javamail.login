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


# Backend Services EJB Module

## Descripción

Este módulo EJB forma parte de los servicios backend y está diseñado para ejecutarse dentro de un contenedor EJB, proporcionando lógica de negocio a través de beans de sesión y entidades. Utiliza Java 11 y se basa en la especificación Jakarta EE 10.0.0, integrando diversas bibliotecas y dependencias para optimizar el desarrollo.

## Pre-requisitos

- Java JDK 11.
- Maven para la gestión del proyecto.

## Configuración

Asegúrate de tener instalado Java JDK 11 y Maven en tu sistema. Puedes clonar este repositorio o descargar el proyecto para comenzar a trabajar con él.

## Compilación y Empaquetado

Para compilar el proyecto y empaquetarlo como un archivo JAR (o EJB), navega al directorio raíz del proyecto y ejecuta:

```bash
mvn clean package
```

## Dependencias Principales

Este proyecto hace uso de varias dependencias importantes, que incluyen:

- Jakarta EE API para la base de la especificación Jakarta EE.
- Project Lombok para reducir el boilerplate en Java.
- Log4J para logging.
- PostgreSQL JDBC Driver para la conectividad con bases de datos PostgreSQL.
- JSON Web Tokens (JJWT) para la gestión de tokens JWT.
- Vavr para funcionalidades de programación funcional en Java.
- ModelMapper para el mapeo objeto-objeto.
- BCrypt para la encriptación de contraseñas.
- JavaMail API para el envío de correos electrónicos.
- Twilio SDK para servicios de mensajería y voz.

## Uso
Este módulo EJB puede ser desplegado en un contenedor EJB/Jakarta EE, como parte de una aplicación empresarial más grande. Configura las dependencias y servicios necesarios antes de desplegar el módulo.


