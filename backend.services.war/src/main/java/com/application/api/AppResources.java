package com.application.api;

import com.application.ejb.WelcomeBean;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class AppResources {

    @EJB
    private WelcomeBean welcomeBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getBienvenida() {
        return Response
                .status(Response.Status.OK)
                .entity(this.welcomeBean.getBienvenidaApp())
                .build();
    }
}
