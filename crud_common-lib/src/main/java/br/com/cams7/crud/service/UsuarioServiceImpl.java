/**
 * 
 */
package br.com.cams7.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.crud.entity.UsuarioEntity;
import br.com.cams7.crud.repository.UsuarioRepository;

/**
 * @author cesar
 *
 */
@Service
public class UsuarioServiceImpl extends AbstractService<UsuarioRepository, UsuarioEntity> implements UsuarioService {

	/**
	 * Utiliza a injecao de dependencia do <code>Spring Framework</code> para
	 * resolver a instancia do <code>Repository</code>.
	 */
	@Autowired
	private UsuarioRepository repository;

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
	 * br.com.cams7.cw.service.AbstractService#salva(br.com.cams7.sys.entity.
	 * AbstractEntity)
	 */
	// @Transactional
	@Override
	public void save(UsuarioEntity usuario) {
		String hashedPassword = getHashedPassword(usuario.getSenha());

		usuario.setSenha(hashedPassword);
		super.save(usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.cw.service.AbstractService#atualiza(br.com.cams7.sys.entity.
	 * AbstractEntity)
	 */
	// @Transactional
	@Override
	public void update(UsuarioEntity usuario) {
		String hashedPassword = getHashedPassword(usuario.getSenha());

		usuario.setSenha(hashedPassword);
		super.update(usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.UsuarioRepository#buscaTodosDadosPessoais()
	 */
	// @Transactional(readOnly = true)
	@Override
	public List<UsuarioEntity> getTodosDadosPessoais() {
		return getRepository().getTodosDadosPessoais();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.repository.UsuarioRepository#
	 * loginFoiCadastradoAnteriormente(java.lang.String)
	 */
	// @Transactional(readOnly = true)
	@Override
	public boolean isLoginCadastradoAnteriormente(String login) {
		return getRepository().isLoginCadastradoAnteriormente(login);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.UsuarioRepository#buscaLoginPeloId(java.lang
	 * .Long)
	 */
	// @Transactional(readOnly = true)
	@Override
	public String getLoginPeloId(Long id) {
		return getRepository().getLoginPeloId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.service.UsuarioService#loginValido(java.lang.Long,
	 * java.lang.String)
	 */
	// @Transactional(readOnly = true)
	@Override
	public boolean isLoginValido(Long usuarioId, String login) {
		if (usuarioId != null) {
			String usuarioLogin = getLoginPeloId(usuarioId);
			if (login.equals(usuarioLogin))
				return true;
		}

		boolean loginCadastrado = isLoginCadastradoAnteriormente(login);
		if (loginCadastrado)
			return false;

		return true;
	}

	@Override
	protected UsuarioRepository getRepository() {
		return repository;
	}

	// @Override
	// public void setRepository(UsuarioRepository repository) {
	// this.repository = repository;
	// }

}
