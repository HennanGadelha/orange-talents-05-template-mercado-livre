package com.mercadolivre.config.validacao.existingId;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ExistingIdValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface  ExistingID {

    String message() default "Id não existente";

    Class<?>[] groups() default  { };

    Class<? extends Payload>[] payload() default { };

    String fieldName();

    Class<?> domainClass();


}
