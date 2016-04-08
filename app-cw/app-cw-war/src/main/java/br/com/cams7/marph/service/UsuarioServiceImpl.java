/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.cw.service.AbstractService;
import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.repository.UsuarioRepository;

/**
 * @author cesar
 *
 */
@Service
public class UsuarioServiceImpl extends AbstractService<UsuarioRepository, UsuarioEntity> implements UsuarioService {

	public UsuarioServiceImpl() {
		super();
	}

	/**
	 * Criptografa a senha do usuario
	 * 
	 * @param senha
	 * @return
	 */
	private String getHashedPassword(String senha) {
		// password: 12345
		// encoder: $2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(senha);
		return hashedPassword;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.service.AbstractService#salva(br.com.cams7.app.entity.
	 * AbstractEntity)
	 */
	@Transactional
	@Override
	public void salva(UsuarioEntity usuario) {
		String hashedPassword = getHashedPassword(usuario.getSenha());

		usuario.setSenha(hashedPassword);
		super.salva(usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.service.AbstractService#atualiza(br.com.cams7.app.entity
	 * .AbstractEntity)
	 */
	@Transactional
	@Override
	public void atualiza(UsuarioEntity usuario) {
		String hashedPassword = getHashedPassword(usuario.getSenha());

		usuario.setSenha(hashedPassword);
		super.atualiza(usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.repository.UsuarioRepository#buscaTodosDadosPessoais()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<UsuarioEntity> buscaTodosDadosPessoais() {
		return getRepository().buscaTodosDadosPessoais();
	}

}
