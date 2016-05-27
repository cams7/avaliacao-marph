/**
 * 
 */
package br.com.cams7.crud;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.service.PessoaService;
import br.com.cams7.sys.SearchParams;
import br.com.cams7.sys.SearchParams.SortOrder;

/**
 * Testa o CRUD (cria, lê, atualiza e remove) da entidade "Pessoa"
 * 
 * @author cesar
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public final class PessoaTest extends AbstractAppTest<PessoaService, PessoaEntity> {

	public PessoaTest() {
		super();
	}

	/**
	 * Cria uma data de nascimento
	 *
	 * @param dia
	 * @param mes
	 * @param ano
	 * @return
	 */
	private Date getNovoNascimento(int dia, int mes, int ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.MONTH, mes);
		calendar.set(Calendar.YEAR, ano);

		calendar.clear(Calendar.HOUR);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MILLISECOND);

		return calendar.getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.cw.utils.test.AbstractAppTest#setEntity(br.com.cams7.sys.
	 * entity.AbstractEntity, br.com.cams7.sys.entity.AbstractEntity)
	 */
	@Override
	protected void setEntity(PessoaEntity pessoa, PessoaEntity novaPessoa) {
		pessoa.setNome(novaPessoa.getNome());
		pessoa.setCpf(novaPessoa.getCpf());
		pessoa.setNascimento(novaPessoa.getNascimento());
	}

	/**
	 * Busca pessoa pelo nome
	 * 
	 * @param nome
	 * @return
	 */
	private PessoaEntity buscaPessoaPeloNome(String nome) {
		List<PessoaEntity> pessoas = getService().getPessoaPeloNome(nome);

		checkList(pessoas, 1);

		return pessoas.get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.cw.utils.test.AbstractAppTest#checkEntity(br.com.cams7.sys.
	 * entity.AbstractEntity, br.com.cams7.sys.entity.AbstractEntity)
	 */
	@Override
	@SuppressWarnings("deprecation")
	protected void checkEntity(PessoaEntity pessoa, PessoaEntity novaPessoa) {
		assertEquals(pessoa.getNome(), novaPessoa.getNome());
		assertEquals(pessoa.getCpf(), novaPessoa.getCpf());

		assertEquals(pessoa.getNascimento().getYear(), novaPessoa.getNascimento().getYear());
		assertEquals(pessoa.getNascimento().getMonth(), novaPessoa.getNascimento().getMonth());
		assertEquals(pessoa.getNascimento().getDate(), novaPessoa.getNascimento().getDate());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.cw.utils.test.AbstractAppTest#testSalva()
	 */
	@Test
	@Override
	public void testSalva() {
		final String NOVO_NOME = "Ana Gomes de Oliveira";
		final String NOVO_CPF = "53702505881";
		final Date NOVO_NASCIMENTO = getNovoNascimento(20, Calendar.APRIL, 1991);

		PessoaEntity novaPessoa = new PessoaEntity();
		novaPessoa.setNome(NOVO_NOME);
		novaPessoa.setCpf(NOVO_CPF);
		novaPessoa.setNascimento(NOVO_NASCIMENTO);

		PessoaEntity pessoa = getNewEntity(novaPessoa);
		getService().save(pessoa);

		pessoa = buscaPessoaPeloNome(NOVO_NOME);
		checkEntity(pessoa, novaPessoa);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.cw.utils.test.AbstractAppTest#testAtualiza()
	 */
	@Test
	@Override
	public void testAtualiza() {
		final long PESSOA_ID = 1l;

		final String NOVO_NOME = "Joao Santana Alvares";
		final String NOVO_CPF = "41665028670";
		final Date NOVO_NASCIMENTO = getNovoNascimento(11, Calendar.JANUARY, 1974);

		PessoaEntity pessoa = findById(PESSOA_ID);

		PessoaEntity novaPessoa = new PessoaEntity();
		novaPessoa.setNome(NOVO_NOME);
		novaPessoa.setCpf(NOVO_CPF);
		novaPessoa.setNascimento(NOVO_NASCIMENTO);

		setEntity(pessoa, novaPessoa);
		getService().update(pessoa);

		pessoa = findById(PESSOA_ID);
		checkEntity(pessoa, novaPessoa);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.cw.utils.test.AbstractAppTest#testRemove()
	 */
	@Test
	@Override
	public void testRemove() {
		final long PESSOA_ID = 11l;

		getService().remove(PESSOA_ID);

		PessoaEntity pessoa = getService().getEntityById(PESSOA_ID);
		assertNull(pessoa);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.cw.utils.test.AbstractAppTest#testBuscaTodos()
	 */
	@Test
	@Override
	public void testBuscaTodos() {
		List<PessoaEntity> pessoas = getService().getAll();

		checkList(pessoas, 17);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.cw.utils.test.AbstractAppTest#testBuscaPeloId()
	 */
	@Test
	@Override
	public void testBuscaPeloId() {
		final long PESSOA_ID = 2l;
		final String NOME = "Maria Antonieta de Almeida";
		final String CPF = "90763518646";
		final Date NASCIMENTO = getNovoNascimento(6, Calendar.JULY, 1984);

		PessoaEntity pessoa = new PessoaEntity();
		pessoa.setNome(NOME);
		pessoa.setCpf(CPF);
		pessoa.setNascimento(NASCIMENTO);

		checkEntity(findById(PESSOA_ID), pessoa);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.cw.utils.test.AbstractAppTest#testSearch()
	 */
	@Test
	@Override
	public void testSearch() {
		final String[] GLOBAL_FILTERS = new String[] { "nome", "cpf" };
		final Map<String, Object> FILTERS = new HashMap<>();
		FILTERS.put("globalFilter", "a");
		FILTERS.put("nome", "a");
		FILTERS.put("cpf", "0");

		SearchParams params = new SearchParams(5, (short) 5, "cpf", SortOrder.DESCENDING, FILTERS, GLOBAL_FILTERS);
		List<PessoaEntity> pessoas = getService().search(params);

		checkList(pessoas, 3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.cw.utils.test.AbstractAppTest#testGetTotalElements()
	 */
	@Test
	@Override
	public void testGetTotalElements() {
		final String[] GLOBAL_FILTERS = new String[] { "nome", "cpf" };
		final Map<String, Object> FILTERS = new HashMap<>();
		FILTERS.put("globalFilter", "a");
		FILTERS.put("nome", "a");
		FILTERS.put("cpf", "0");

		int total = getService().getTotalElements(FILTERS, GLOBAL_FILTERS);
		assertEquals(7, total);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.cw.utils.test.AbstractAppTest#testCount()
	 */
	@Test
	@Override
	public void testCount() {
		int count = getService().count();
		assertEquals(17, count);
	}

	/**
	 * Testa o metodo "buscaPessoasSemUsuarioPeloNome"
	 */
	@Test
	public void testBuscaPessoasSemUsuarioPeloNome() {
		List<PessoaEntity> pessoas = getService().getPessoasSemUsuarioPeloNome("an");

		checkList(pessoas, 3);
	}

	/**
	 * Testa o metodo "buscaPeloNome"
	 */
	@Test
	public void testBuscaPeloNome() {
		List<PessoaEntity> pessoas = getService().getPessoaPeloNome("an");

		checkList(pessoas, 5);
	}

}
