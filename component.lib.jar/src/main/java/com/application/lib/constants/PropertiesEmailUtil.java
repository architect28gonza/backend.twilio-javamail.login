package com.application.lib.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesEmailUtil {

    public static final int MAIL_PORT = 587;
    public static final String MAIL_HOST = "smtp.gmail.com";
    public static final boolean MAIL_AUTH = true;
    public static final boolean MAIL_DEBUG = true;
    public static final String FROM = "example@gmail.com";

    public static final String USERNAME_EMAIL = "example@gmail.com";
    public static final String PASSWORD_EMAIL = "12mdd 2gxm ojux jzps"; /* generar un clave de aplicacion en su correo */

}
