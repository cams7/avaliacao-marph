/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.springframework.stereotype.Repository;

import br.com.cams7.app.repository.AbstractRepository;
import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.utils.SortOrder;

/**
 * @author cesar
 *
 */
@Repository
public class EnderecoRepositoryImpl extends AbstractRepository<EnderecoEntity> implements EnderecoRepository {

	public EnderecoRepositoryImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see br.com.cams7.app.repository.AbstractRepository#search(int, short, java.lang.String, br.com.cams7.utils.SortOrder, java.util.Map)
	 */
	@Override
	public List<EnderecoEntity> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		Criteria select = getPaginacaoOrdenacao(pageFirst, pageSize, sortField, sortOrder);
		select.setFetchMode("pessoa", FetchMode.JOIN);

		@SuppressWarnings("unchecked")
		List<EnderecoEntity> enderecos = select.list();

		return enderecos;
	}

}
