package com.application.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.application.lib.dto.ChangePasswordDto;

import io.vavr.control.Try;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateInpustsUtil {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isCorrectFields(ChangePasswordDto changePasswordDto) {
        return changePasswordDto.isEmailOrPhone()
                ? isEmail(changePasswordDto.getEmailOrPhoneNumber())
                : isPhone(changePasswordDto.getEmailOrPhoneNumber());
    }

    private static boolean isEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isPhone(String phone) {
        return Try.of(() -> Long.parseLong(phone.replace("+", ""))).isSuccess();
    }
}
