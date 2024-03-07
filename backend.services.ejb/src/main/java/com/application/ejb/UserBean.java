package com.application.ejb;

import java.util.HashMap;
import java.util.Optional;

import com.application.dao.UserDao;
import com.application.dto.InitSessionDto;
import com.application.entity.UserEntity;
import com.application.lib.dto.MessageDto;
import com.application.lib.dto.UserDto;
import com.application.mapper.AppMapper;
import com.application.util.EncryptUtil;
import com.application.util.TokenUtil;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserBean {

    @PersistenceContext(unitName = "DataSourceUN")
    private EntityManager em;

    private AppMapper appMapper = new AppMapper();

    public InitSessionDto setInitSession(String username, String password) {
        UserDao userDao = new UserDao();
        UserEntity userEntity = userDao.userNameAndPassword(em, username);
        boolean isIgualContrasena = EncryptUtil.checkPassword(password, userEntity.getPassword());
        if (isIgualContrasena) {
            return Optional.ofNullable(userDao.userNameAndPassword(em, username))
                    .map(user -> createSuccessDto(username))
                    .orElse(createFailureDto());
        }
        return createFailureDto();
    }

    public MessageDto setRegisterUser(UserDto user) {
        try {
            UserEntity userEntity = this.appMapper.userDtoToEntity(user);
            userEntity.setPassword(EncryptUtil.encodePassword(user.getPassword()));
            em.persist(userEntity);
            return getMessageDto("Registro completado con exito", "SUCCESS");
        } catch (Exception e) {
            return getMessageDto(e.getMessage(), "ERROR");
        }
    }

    private InitSessionDto createSuccessDto(String username) {
        TokenUtil token = new TokenUtil();
        InitSessionDto success = new InitSessionDto();
        success.setMessage("Inicio de sesion");
        success.setToken(token.setGenerarToken(new HashMap<>(), username));
        success.setStatus("SUCCESS");
        return success;
    }

    private InitSessionDto createFailureDto() {
        InitSessionDto failure = new InitSessionDto();
        failure.setMessage("Credenciales ingresadas de forma incorrecta, Verificar");
        failure.setToken("");
        failure.setStatus("FAILED");
        return failure;
    }

    private MessageDto getMessageDto(String msg, String status) {
        return MessageDto.builder()
                .message(msg)
                .status(status)
                .build();
    }

}
