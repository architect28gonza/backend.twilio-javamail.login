package com.application.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.application.entity.UserEntity;
import com.application.lib.dto.ChangePasswordDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserDao {

    private static final Logger log = LogManager.getLogger(UserDao.class);

    public UserEntity userNameAndPassword(EntityManager em, String username) {
        try {
            TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.findByUsername", UserEntity.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public boolean countUserNameAndOther(EntityManager em, ChangePasswordDto change) {
        try {
            TypedQuery<Long> query = em.createNamedQuery(getJpqlUser(change.isEmailOrPhone()), Long.class);
            query.setParameter("username", change.getUsername());
            query.setParameter(change.isEmailOrPhone() ? "email" : "phone", change.getEmailOrPhoneNumber());
            return (query.getSingleResult() > 0);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    private String getJpqlUser(boolean isUserEmailOrPhone) {
        return isUserEmailOrPhone ? "UserEntity.findByUsernameAndEmail" : "UserEntity.findByUsernameAndPhone";
    }
}
