/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.repository.UsuarioRepository;

/**
 * @author cesar
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public UsuarioServiceImpl() {
		super();
	}

	@Transactional
	@Override
	public void salva(UsuarioEntity usuario) {
		repository.salva(usuario);
	}

	@Transactional
	@Override
	public void atualiza(UsuarioEntity usuario) {
		repository.atualiza(usuario);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		repository.remove(id);
	}

	@Transactional
	@Override
	public void remove(List<Long> ids) {
		repository.remove(ids);
	}

	@Transactional(readOnly = true)
	@Override
	public List<UsuarioEntity> buscaTodos() {
		return repository.buscaTodos();
	}

	@Transactional(readOnly = true)
	@Override
	public UsuarioEntity buscaPorId(Long id) {
		return repository.buscaPorId(id);
	}

}
