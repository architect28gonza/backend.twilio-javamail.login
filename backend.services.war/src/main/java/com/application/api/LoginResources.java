package com.application.api;

import com.application.dto.InitSessionDto;
import com.application.ejb.SendEmailBean;
import com.application.ejb.SendSmsBean;
import com.application.ejb.UserBean;
import com.application.lib.dto.UserLoginDto;
import com.application.security.Secure;
import com.application.util.ValidateInpustsUtil;
import com.application.lib.constants.MessageSend;
import com.application.lib.dto.ChangePasswordDto;
import com.application.lib.dto.MessageDto;
import com.application.lib.dto.UserDto;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class LoginResources {

    @EJB
    private UserBean userBean;

    @EJB
    private SendEmailBean sendEmailBean;

    @EJB
    private SendSmsBean sendSmsBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response initSessionUser(UserLoginDto initUser) {
        InitSessionDto initSession = this.userBean.setInitSession(
                initUser.getUsername(),
                initUser.getPassword());
        String success = initSession.getStatus();
        return Response
                .status(("SUCCESS".equals(success))
                        ? Response.Status.OK
                        : Response.Status.NOT_ACCEPTABLE)
                .entity(initSession)
                .build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserDto user) {
        MessageDto messageDto = this.userBean.setRegisterUser(user);
        return Response
                .status(("SUCCESS".equals(messageDto.getStatus()))
                        ? Response.Status.OK
                        : Response.Status.BAD_REQUEST)
                .entity(messageDto)
                .build();
    }

    @POST
    @Path("change")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendCodeEmailOrPhone(ChangePasswordDto change) {
        boolean isCorrect = ValidateInpustsUtil.isCorrectFields(change);
        String response = MessageSend.ERROR;
        if (isCorrect) {
            response = (change.isEmailOrPhone())
                    ? this.sendEmailBean.sendMail(change)
                    : this.sendSmsBean.sendSms(change);
        }
        return Response
                .status(response.equals(MessageSend.ERROR)
                        ? Response.Status.CONFLICT
                        : Response.Status.ACCEPTED)
                .entity(response.equals(MessageSend.ERROR)
                        ? "No se puedo realizar el envio, Vuelva a intentar mas tarde"
                        : "El codigo fue enviado a ".concat(change.getEmailOrPhoneNumber()))
                .build();
    }

    @GET
    @Secure
    @Path("private")
    @Produces(MediaType.TEXT_PLAIN)
    public Response messagePrivate() {
        return Response
                .status(Response.Status.ACCEPTED)
                .entity("Message")
                .build();
    }
}