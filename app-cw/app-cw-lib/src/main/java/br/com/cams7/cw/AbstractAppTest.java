/**
 * 
 */
package br.com.cams7.cw;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.service.BaseService;
import br.com.cams7.app.utils.AppException;
import br.com.cams7.app.utils.AppHelper;

/**
 * @author cesar
 *
 */
public abstract class AbstractAppTest<S extends BaseService<E>, E extends AbstractEntity>
		extends AbstractTestNGSpringContextTests {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;
	private final Class<E> ENTITY_TYPE;

	/**
	 * Utiliza a injecao de dependencia do <code>Spring Framework</code> para
	 * resolver a instancia do <code>Service</code>.
	 */
	@Autowired
	private S service;

	@SuppressWarnings("unchecked")
	public AbstractAppTest() {
		super();
		ENTITY_TYPE = (Class<E>) AppHelper.getType(this.getClass(), getEntityArgumentNumber());
	}

	@BeforeClass
	public static void setUp() {
	}

	@AfterClass
	public static void afterTest() {
	}

	/**
	 * Altera os dados da entidade
	 * 
	 * @param currentEntity
	 * @param newEntity
	 */
	protected abstract void setEntity(E currentEntity, E newEntity);

	/**
	 * Cria uma nova entidade
	 * 
	 * @param newEntity
	 * @return
	 */
	protected E getNewEntity(E newEntity) throws AppException {
		E entity = AppHelper.getNewEntity(getEntityType());
		setEntity(entity, newEntity);

		assertNotNull(newEntity);

		return entity;
	}

	/**
	 * Verifica a lista
	 * 
	 * @param entities
	 * @param total
	 */
	protected void checkList(List<E> entities, int total) {
		assertFalse(entities.isEmpty());
		assertEquals(total, entities.size());
	}

	/**
	 * Busca entidade pelo id
	 * 
	 * @param id
	 * @return
	 */
	protected E findById(Long id) {
		E entity = getService().buscaPeloId(id);

		assertNotNull(entity);

		return entity;
	}

	/**
	 * Verfifica entidade
	 * 
	 * @param currentEntity
	 * @param newEntity
	 */
	protected abstract void checkEntity(E currentEntity, E newEntity);

	/**
	 * Testa o metodo "salva"
	 */
	public abstract void testSalva();

	/**
	 * Testa o metodo "atualiza"
	 */
	public abstract void testAtualiza();

	/**
	 * Testa o metodo "remove"
	 */
	public abstract void testRemove();

	/**
	 * Testa o metodo "buscaTodos"
	 */
	public abstract void testBuscaTodos();

	/**
	 * Testa o metodo "buscaPorId"
	 */
	public abstract void testBuscaPeloId();

	/**
	 * Testa o metodo "search"
	 */
	public abstract void testSearch();

	/**
	 * Testa o metodo "getTotalElements"
	 */
	public abstract void testGetTotalElements();

	/**
	 * Testa o metodo "count"
	 */
	public abstract void testCount();

	/**
	 * Indice do Template
	 * 
	 * @return byte
	 */
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	/**
	 * Tipo da entidade usada como Template
	 * 
	 * @return Tipo Entity
	 */
	protected Class<E> getEntityType() {
		return ENTITY_TYPE;
	}

	/**
	 * @return Service
	 */
	protected S getService() {
		return service;
	}

}
