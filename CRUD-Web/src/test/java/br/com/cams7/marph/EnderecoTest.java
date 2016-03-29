/**
 * 
 */
package br.com.cams7.marph;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import br.com.cams7.app.AbstractAppTest;
import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.service.EnderecoService;
import br.com.cams7.utils.SortOrder;

/**
 * Testa o CRUD (cria, lê, atualiza e remove) da entidade "Endereco"
 * 
 * @author cesar
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public final class EnderecoTest extends AbstractAppTest<EnderecoService, EnderecoEntity> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.AbstractAppTest#setEntity(br.com.cams7.app.entity.
	 * AbstractEntity, br.com.cams7.app.entity.AbstractEntity)
	 */
	@Override
	protected void setEntity(EnderecoEntity endereco, EnderecoEntity novoEndereco) {
		endereco.setRua(novoEndereco.getRua());
		endereco.setBairro(novoEndereco.getBairro());
		endereco.setTelefone(novoEndereco.getTelefone());
		endereco.setPessoa(novoEndereco.getPessoa());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.AbstractAppTest#checkEntity(br.com.cams7.app.entity.
	 * AbstractEntity, br.com.cams7.app.entity.AbstractEntity)
	 */
	@Override
	protected void checkEntity(EnderecoEntity endereco, EnderecoEntity novoEndereco) {
		assertEquals(endereco.getRua(), novoEndereco.getRua());
		assertEquals(endereco.getBairro(), novoEndereco.getBairro());
		assertEquals(endereco.getTelefone(), novoEndereco.getTelefone());
		assertEquals(endereco.getPessoa(), novoEndereco.getPessoa());
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
		final long ENDERECO_ID = 10l;

		final String NOVA_RUA = "Rua Numero Sete";
		final String NOVO_BAIRRO = "Bairro Numero Sete";
		final String NOVO_TELEFONE = "3136010109";
		final PessoaEntity PESSOA = new PessoaEntity(PESSOA_ID);

		EnderecoEntity novoEndereco = new EnderecoEntity();
		novoEndereco.setRua(NOVA_RUA);
		novoEndereco.setBairro(NOVO_BAIRRO);
		novoEndereco.setTelefone(NOVO_TELEFONE);
		novoEndereco.setPessoa(PESSOA);

		EnderecoEntity endereco = getNewEntity(novoEndereco);
		getService().salva(endereco);

		endereco = findById(ENDERECO_ID);
		// Obs: O atributo pessoa foi modificado devido ao erro:
		// org.hibernate.LazyInitializationException: could not initialize proxy
		// - no Session
		endereco.setPessoa(PESSOA);
		checkEntity(endereco, novoEndereco);
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
		final long ENDERECO_ID = 1l;

		final String NOVA_RUA = "Rua Numero Oito";
		final String NOVO_BAIRRO = "Bairro Numero Oito";
		final String NOVO_TELEFONE = "3136010110";
		final PessoaEntity PESSOA = new PessoaEntity(PESSOA_ID);

		EnderecoEntity endereco = findById(ENDERECO_ID);

		EnderecoEntity novoEndereco = new EnderecoEntity();
		novoEndereco.setRua(NOVA_RUA);
		novoEndereco.setBairro(NOVO_BAIRRO);
		novoEndereco.setTelefone(NOVO_TELEFONE);
		novoEndereco.setPessoa(PESSOA);

		setEntity(endereco, novoEndereco);
		getService().atualiza(endereco);

		endereco = findById(ENDERECO_ID);
		// Obs: O atributo pessoa foi modificado devido ao erro:
		// org.hibernate.LazyInitializationException: could not initialize proxy
		// - no Session
		endereco.setPessoa(PESSOA);
		checkEntity(endereco, novoEndereco);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testRemove()
	 */
	@Test
	@Override
	public void testRemove() {
		final long ENDERECO_ID = 2l;

		getService().remove(ENDERECO_ID);

		EnderecoEntity endereco = getService().buscaPeloId(ENDERECO_ID);
		assertNull(endereco);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testBuscaTodos()
	 */
	@Test
	@Override
	public void testBuscaTodos() {
		List<EnderecoEntity> enderecos = getService().buscaTodos();

		checkList(enderecos, 9);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testBuscaPeloId()
	 */
	@Test
	@Override
	public void testBuscaPeloId() {
		final long PESSOA_ID = 3l;
		final long ENDERECO_ID = 3l;

		final String RUA = "Rua Numero Tres";
		final String BAIRRO = "Bairro Numero Tres";
		final String TELEFONE = "3136010103";
		final PessoaEntity PESSOA = new PessoaEntity(PESSOA_ID);

		EnderecoEntity endereco = new EnderecoEntity();
		endereco.setRua(RUA);
		endereco.setBairro(BAIRRO);
		endereco.setTelefone(TELEFONE);
		endereco.setPessoa(PESSOA);

		EnderecoEntity enderecoBuscado = findById(ENDERECO_ID);
		// Obs: O atributo pessoa foi modificado devido ao erro:
		// org.hibernate.LazyInitializationException: could not initialize proxy
		// - no Session
		enderecoBuscado.setPessoa(PESSOA);
		checkEntity(enderecoBuscado, endereco);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.AbstractAppTest#testSearch()
	 */
	@Test
	@Override
	public void testSearch() {
		final String[] GLOBAL_FILTERS = new String[] { "bairro", "rua", "telefone", "pessoa.nome" };
		final Map<String, Object> FILTERS = new HashMap<>();
		FILTERS.put("globalFilter", "a");
		FILTERS.put("pessoa.nome", "m");
		FILTERS.put("rua", "r");
		FILTERS.put("bairro", "b");
		FILTERS.put("telefone", "0");

		List<EnderecoEntity> enderecos = getService().search(0, (short) 10, "bairro", SortOrder.UNSORTED, FILTERS,
				GLOBAL_FILTERS);

		checkList(enderecos, 3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.AbstractAppTest#testGetTotalElements()
	 */
	@Test
	@Override
	public void testGetTotalElements() {
		final String[] GLOBAL_FILTERS = new String[] { "bairro", "rua", "telefone", "pessoa.nome" };
		final Map<String, Object> FILTERS = new HashMap<>();
		FILTERS.put("globalFilter", "a");
		FILTERS.put("pessoa.nome", "m");
		FILTERS.put("rua", "r");
		FILTERS.put("bairro", "b");
		FILTERS.put("telefone", "0");

		int total = getService().getTotalElements(FILTERS, GLOBAL_FILTERS);
		assertEquals(4, total);
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
		assertEquals(9, count);
	}

}
