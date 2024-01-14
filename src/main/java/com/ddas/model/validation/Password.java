package com.ddas.model.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.ddas.model.validation.Password.PasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password
{
    String message() default "Invalid password format!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    int minLength() default 8; // Minimum password length
    int maxLength() default 64; // Maximum password length
    boolean requireUppercase() default true; // Require at least one uppercase letter
    boolean requireLowercase() default true; // Require at least one lowercase letter
    boolean requireDigit() default true; // Require at least one digit
    boolean requireSpecialChar() default true; // Require at least one special character

    public class PasswordValidator implements ConstraintValidator<Password, String>
    {
        @Override
        public void initialize(Password constraintAnnotation)
        {
            minLength = constraintAnnotation.minLength();
            maxLength = constraintAnnotation.maxLength();
            requireUppercase = constraintAnnotation.requireUppercase();
            requireLowercase = constraintAnnotation.requireLowercase();
            requireDigit = constraintAnnotation.requireDigit();
            requireSpecialChar = constraintAnnotation.requireSpecialChar();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context)
        {
            // Handeled by other annotations like @NotNull
            if(value == null) return true;

            int length = value.length();

            return length >= minLength &&
                length <= maxLength &&
                (requireUppercase && containsUppercase(value)) &&
                (requireLowercase && containsLowercase(value)) &&
                (requireDigit && containsDigit(value)) &&
                (requireSpecialChar && containsSpecialChar(value));
        }

        private boolean containsUppercase(String str)
        {
            return str.chars().anyMatch(Character::isUpperCase);
        }

        private boolean containsLowercase(String str)
        {
            return str.chars().anyMatch(Character::isLowerCase);
        }

        private boolean containsDigit(String str)
        {
            return str.chars().anyMatch(Character::isDigit);
        }

        private boolean containsSpecialChar(String str)
        {
            String specialChars = "`~!@#$%^&*()-_=+[]{};:'\",<.>/?\\|";
            return str.chars().anyMatch(c -> specialChars.indexOf(c) != -1);
        }

        private int minLength;
        private int maxLength;
        private boolean requireUppercase;
        private boolean requireLowercase;
        private boolean requireDigit;
        private boolean requireSpecialChar;
    }
}
