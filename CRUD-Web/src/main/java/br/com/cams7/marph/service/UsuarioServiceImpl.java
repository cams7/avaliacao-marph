/**
 * 
 */
package br.com.cams7.marph.service;

import org.springframework.stereotype.Service;

import br.com.cams7.app.AbstractService;
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

}
