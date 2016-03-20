/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.springframework.stereotype.Repository;

import br.com.cams7.app.entity.SortOrder;
import br.com.cams7.app.repository.AbstractRepository;
import br.com.cams7.marph.entity.UsuarioEntity;

/**
 * @author cesar
 *
 */
@Repository
public class UsuarioRepositoryImpl extends AbstractRepository<UsuarioEntity> implements UsuarioRepository {

	public UsuarioRepositoryImpl() {
		super();
	}

	@Override
	public List<UsuarioEntity> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		Criteria select = getPaginacaoOrdenacao(pageFirst, pageSize, sortField, sortOrder);
		select.setFetchMode("pessoa", FetchMode.JOIN);

		@SuppressWarnings("unchecked")
		List<UsuarioEntity> usuarios = select.list();

		return usuarios;
	}

	@Override
	public List<UsuarioEntity> buscaTodosDadosPessoais() {
		@SuppressWarnings("unchecked")
		List<UsuarioEntity> usuarios = getCurrentSession().getNamedQuery("Usuario.buscaTodosDadosPessoais").list();
		return usuarios;
	}

}
