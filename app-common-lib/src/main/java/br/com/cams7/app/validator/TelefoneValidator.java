/**
 * 
 */
package br.com.cams7.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author cesar
 *
 */
public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

	public TelefoneValidator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(Telefone constraintAnnotation) {
	}

	/*
	 * Verifica se o telefone e valido
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String telefone, ConstraintValidatorContext context) {
		if (telefone == null || telefone.isEmpty())
			return true;

		return telefone.matches("[0-9]{10}");
	}

}
