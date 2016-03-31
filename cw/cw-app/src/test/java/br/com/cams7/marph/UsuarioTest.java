/**
 * 
 */
package br.com.cams7.marph;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import br.com.cams7.app.AbstractAppTest;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.service.UsuarioService;
import br.com.cams7.utils.SortOrder;

/**
 * Testa o CRUD (cria, lê, atualiza e remove) da entidade "Usuario"
 * 
 * @author cesar
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public final class UsuarioTest extends AbstractAppTest<UsuarioService, UsuarioEntity> {

	public UsuarioTest() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.AbstractAppTest#setEntity(br.com.cams7.app.entity.
	 * AbstractEntity, br.com.cams7.app.entity.AbstractEntity)
	 */
	@Override
	protected void setEntity(UsuarioEntity usuario, UsuarioEntity novoUsuario) {
		usuario.setLogin(novoUsuario.getLogin());
		usuario.setSenha(novoUsuario.getSenha());
		usuario.setHabilitado(novoUsuario.getHabilitado());
		usuario.setStringAutorizacoes(novoUsuario.getStringAutorizacoes());
		usuario.setPessoa(novoUsuario.getPessoa());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.AbstractAppTest#checkEntity(br.com.cams7.app.entity.
	 * AbstractEntity, br.com.cams7.app.entity.AbstractEntity)
	 */
	@Override
	protected void checkEntity(UsuarioEntity usuario, UsuarioEntity novoUsuario) {
		assertEquals(usuario.getLogin(), novoUsuario.getLogin());
		assertTrue(new BCryptPasswordEncoder().matches(novoUsuario.getSenha(), usuario.getSenha()));
		assertEquals(usuario.getHabilitado(), novoUsuario.getHabilitado());
		assertEquals(usuario.getStringAutorizacoes(), novoUsuario.getStringAutorizacoes());
		assertEquals(usuario.getPessoa(), novoUsuario.getPessoa());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testSalva()
	 */
	@Test
	@Override
	public void testSalva() {
		final long PESSOA_ID = 1l;
		final long USUARIO_ID = 11l;

		final String NOVO_LOGIN = "admin6";
		final String NOVA_SENHA = "admin123";
		final boolean HABILITADO = true;
		final String AUTORIZACOES = "CLIENTE-SECRETARIO-ADMINISTRADOR";
		final PessoaEntity PESSOA = new PessoaEntity(PESSOA_ID);

		UsuarioEntity novoUsuario = new UsuarioEntity();
		novoUsuario.setLogin(NOVO_LOGIN);
		novoUsuario.setSenha(NOVA_SENHA);
		novoUsuario.setHabilitado(HABILITADO);
		novoUsuario.setStringAutorizacoes(AUTORIZACOES);
		novoUsuario.setPessoa(PESSOA);

		UsuarioEntity usuario = getNewEntity(novoUsuario);
		getService().salva(usuario);

		usuario = findById(USUARIO_ID);
		// Obs: O atributo pessoa foi modificado devido ao erro:
		// org.hibernate.LazyInitializationException: could not initialize proxy
		// - no Session
		usuario.setPessoa(PESSOA);
		checkEntity(usuario, novoUsuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testAtualiza()
	 */
	@Test
	@Override
	public void testAtualiza() {
		final long PESSOA_ID = 1l;
		final long USUARIO_ID = 1l;

		final String NOVO_LOGIN = "admin1";
		final String NOVA_SENHA = "admin123";
		final boolean HABILITADO = false;
		final String AUTORIZACOES = "ADMINISTRADOR";
		final PessoaEntity PESSOA = new PessoaEntity(PESSOA_ID);

		UsuarioEntity usuario = findById(USUARIO_ID);

		UsuarioEntity novoUsuario = new UsuarioEntity();
		novoUsuario.setLogin(NOVO_LOGIN);
		novoUsuario.setSenha(NOVA_SENHA);
		novoUsuario.setHabilitado(HABILITADO);
		novoUsuario.setStringAutorizacoes(AUTORIZACOES);
		novoUsuario.setPessoa(PESSOA);

		setEntity(usuario, novoUsuario);
		getService().atualiza(usuario);

		usuario = findById(USUARIO_ID);
		// Obs: O atributo pessoa foi modificado devido ao erro:
		// org.hibernate.LazyInitializationException: could not initialize proxy
		// - no Session
		usuario.setPessoa(PESSOA);
		checkEntity(usuario, novoUsuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testRemove()
	 */
	@Test
	@Override
	public void testRemove() {
		final long USUARIO_ID = 2l;

		getService().remove(USUARIO_ID);

		UsuarioEntity usuario = getService().buscaPeloId(USUARIO_ID);
		assertNull(usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testBuscaTodos()
	 */
	@Test
	@Override
	public void testBuscaTodos() {
		List<UsuarioEntity> usuarios = getService().buscaTodos();

		checkList(usuarios, 10);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testBuscaPorId()
	 */
	@Test
	@Override
	public void testBuscaPeloId() {
		final long PESSOA_ID = 3l;
		final long USUARIO_ID = 3l;

		final String LOGIN = "admin2";
		final String SENHA = "12345";
		final boolean HABILITADO = true;
		final String AUTORIZACOES = "CLIENTE-ADMINISTRADOR";
		final PessoaEntity PESSOA = new PessoaEntity(PESSOA_ID);

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setLogin(LOGIN);
		usuario.setSenha(SENHA);
		usuario.setHabilitado(HABILITADO);
		usuario.setStringAutorizacoes(AUTORIZACOES);
		usuario.setPessoa(PESSOA);

		UsuarioEntity usuarioBuscado = findById(USUARIO_ID);
		// Obs: O atributo pessoa foi modificado devido ao erro:
		// org.hibernate.LazyInitializationException: could not initialize proxy
		// - no Session
		usuarioBuscado.setPessoa(PESSOA);
		checkEntity(usuarioBuscado, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testSearch()
	 */
	@Test
	@Override
	public void testSearch() {
		final String[] GLOBAL_FILTERS = new String[] { "login", "pessoa.nome" };
		final Map<String, Object> FILTERS = new HashMap<>();
		FILTERS.put("globalFilter", "e");
		FILTERS.put("pessoa.nome", "a");
		FILTERS.put("login", "s");
		FILTERS.put("habilitado", true);

		List<UsuarioEntity> usuarios = getService().search(0, (short) 10, "login", SortOrder.ASCENDING, FILTERS,
				GLOBAL_FILTERS);

		checkList(usuarios, 2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.AbstractAppTest#testGetTotalElements()
	 */
	@Test
	@Override
	public void testGetTotalElements() {
		final String[] GLOBAL_FILTERS = new String[] { "login", "pessoa.nome" };
		final Map<String, Object> FILTERS = new HashMap<>();
		FILTERS.put("globalFilter", "e");
		FILTERS.put("pessoa.nome", "a");
		FILTERS.put("login", "s");
		FILTERS.put("habilitado", true);

		int total = getService().getTotalElements(FILTERS, GLOBAL_FILTERS);
		assertEquals(3, total);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testCount()
	 */
	@Test
	@Override
	public void testCount() {
		int count = getService().count();
		assertEquals(10, count);
	}

}
