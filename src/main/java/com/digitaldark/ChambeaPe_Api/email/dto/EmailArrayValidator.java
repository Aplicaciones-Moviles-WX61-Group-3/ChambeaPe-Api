package com.digitaldark.ChambeaPe_Api.email.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class EmailArrayValidator implements ConstraintValidator<ValidEmailArray, String[]> {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private Pattern pattern;

    @Override
    public void initialize(ValidEmailArray constraintAnnotation) {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public boolean isValid(String[] emails, ConstraintValidatorContext context) {
        if (emails == null || emails.length == 0) {
            return false;
        }

        for (String email : emails) {
            if (StringUtils.isBlank(email) || !pattern.matcher(email).matches()) {
                return false;
            }
        }
        return true;
    }
}