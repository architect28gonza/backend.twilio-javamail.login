package com.application.ejb;

import com.application.dao.UserDao;
import com.application.lib.constants.MessageSend;
import static com.application.lib.constants.PropertiesSmsUtil.*;
import com.application.lib.dto.ChangePasswordDto;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Stateless
@LocalBean
public class SendSmsBean {

    @PersistenceContext(unitName = "DataSourceUN")
    private EntityManager em;

    private String getSidTwilio(String numero) {
        final String messageText = "Su codigo para la recuperacion es : 11111";
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber(numero),
                    new PhoneNumber(NUMBER_PHONE),
                    messageText).create();
            return message.getSid();
        } catch (Exception e) {
            e.printStackTrace();
            return MessageSend.ERROR;
        }
    }

    public String sendSms(ChangePasswordDto change) {
        UserDao userDao = new UserDao();
        boolean userFoundWithEmail = userDao.countUserNameAndOther(em, change);
        return (userFoundWithEmail) ? getSidTwilio(change.getEmailOrPhoneNumber())
                : MessageSend.ERROR;
    }

}
