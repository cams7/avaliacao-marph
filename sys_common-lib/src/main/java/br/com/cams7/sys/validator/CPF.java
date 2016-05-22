package br.com.cams7.sys.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valida CPF
 * 
 * @author cesar
 *
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CpfValidator.class)
@Documented
public @interface CPF {

	String message() default "{br.com.cams7.validator.cpf}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
