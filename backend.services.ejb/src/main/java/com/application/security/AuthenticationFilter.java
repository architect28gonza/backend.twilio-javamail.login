package com.application.security;

import com.application.util.TokenUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Secure
public class AuthenticationFilter implements ContainerRequestFilter { 

    private TokenUtil tokenUtil = new TokenUtil();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Autorizacion no permitida");
        }

        if (!isValidTokenUser(authorizationHeader)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isValidTokenUser(String authorizationHeader) {
        final String jsonWebToken = authorizationHeader.substring(7);
        final boolean isTokenExpirado = this.tokenUtil.isTokenExpirado(jsonWebToken);
        if (!isTokenExpirado) {
            final String nombreUsuario = tokenUtil.extraerNombreUsuarioJwt(jsonWebToken);
            return this.tokenUtil.isTokenValido(jsonWebToken, nombreUsuario);
        }
        return false;
    }

}
