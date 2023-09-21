package com.ddas.model.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.ddas.model.dto.RegisterRequest;
import com.ddas.model.validation.PasswordMatches.PasswordMatchesValidator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches
{
    String message() default "Passwords do not match!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

    public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>
    {
        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context)
        {
            if(value instanceof RegisterRequest)
            {
                RegisterRequest  registerRequest = (RegisterRequest) value;

                // Handeled by other annotations like @NotNull
                if(registerRequest.password() == null || registerRequest.rePassword() == null) return true;

                return registerRequest.password().equals(registerRequest.rePassword());
            }

            return false;
        }
    }
}
