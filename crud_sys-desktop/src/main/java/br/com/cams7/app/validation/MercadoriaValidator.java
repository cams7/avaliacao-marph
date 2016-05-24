package br.com.cams7.app.validation;

import static javax.validation.Validation.buildDefaultValidatorFactory;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import br.com.cams7.crud.entity.MercadoriaEntity;

import javax.validation.Validator;

/**
 * Implementa componente para validar os dados da entidade
 * <code>Mercadoria</code>.
 * 
 * <p>
 * A validação ocorre através do Bean Validations, mecanismo de validação padrão
 * do Java baseado em anotações.
 * </p>
 * 
 * @author YaW Tecnologia
 */
@Component
public class MercadoriaValidator implements EntityValidator<MercadoriaEntity> {

	private static ValidatorFactory factory;

	static {
		factory = buildDefaultValidatorFactory();
	}

	@Override
	public String validate(MercadoriaEntity mercadoria) {
		StringBuilder sb = new StringBuilder();
		if (mercadoria != null) {
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<MercadoriaEntity>> constraintViolations = validator.validate(mercadoria);

			if (!constraintViolations.isEmpty()) {
				sb.append("Validação da entidade Mercadoria\n");
				for (ConstraintViolation<MercadoriaEntity> constraint : constraintViolations)
					sb.append(String.format("%n%s: %s", constraint.getPropertyPath(), constraint.getMessage()));
			}
		}
		return sb.toString();
	}

}
