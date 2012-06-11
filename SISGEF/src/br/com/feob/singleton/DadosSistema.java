package br.com.feob.singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.text.rtf.RTFEditorKit;

import br.com.feob.Enum.GrupoConta;
import br.com.feob.lancamento.Lancamento;
import br.com.feob.lancamento.Validador;
import br.com.feob.planoContas.PlanoConta;

public class DadosSistema {

    private static DadosSistema instance = new DadosSistema();
    private List<PlanoConta> planoContas = new ArrayList<PlanoConta>();
    private String[] colunasPlanoContas;
    private List<Item> grupoContas = new ArrayList<Item>();
    private List<Item> grupoPlanoContas = new ArrayList<Item>();
    private List<Item> niveisPlanoContas = new ArrayList<Item>();

    /**
     * 
     * Lancamentos
     * 
     * */
    private long codigoLancamento = 000000;
    private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
    private List<Validador> lancamentosValidados = new ArrayList<Validador>();

    /**
     * 
     * DRE
     * 
     * */
    private Double resultadoLiquidoDRE = 0.00;

    /**
     * Getter and Setter
     * 
     * */
    public List<PlanoConta> getPlanoContas() {
	return planoContas;
    }

    public void addPlanoConta(PlanoConta pc) {
	this.planoContas.add(pc);
    }

    public String[] getColunasPlanoContas() {
	return colunasPlanoContas;
    }

    public void setColunasPlanoContas(String[] colunasPlanoContas) {
	this.colunasPlanoContas = colunasPlanoContas;
    }

    public List<Item> getGrupoContas() {
	return grupoContas;
    }

    public long getCodigoLancamento() {
	return codigoLancamento;
    }

    public void setCodigoLancamento() {
	this.codigoLancamento = this.codigoLancamento + 1;
    }

    public void setCodigoLancamentoImportacao(long codigo) {
	this.codigoLancamento = codigo;
    }

    public List<Lancamento> getLancamentos() {
	return lancamentos;
    }

    public void addLancamento(Lancamento lancamento) {
	this.lancamentos.add(lancamento);
    }

    public void addValidadorLancamento(int ano, boolean validado) {

	if (lancamentosValidados.size() > 0) {

	    boolean existe = false;

	    for (Validador validador : lancamentosValidados) {
		if (ano == validador.getAno()) {
		    validador.setValidado(validado);
		    existe = true;
		    break;
		}
	    }
	    if (existe == false) {
		lancamentosValidados.add(new Validador(ano, validado));
	    }
	} else {
	    lancamentosValidados.add(new Validador(ano, validado));
	}
    }

    public List<Validador> getLancamentosValidados() {
	return this.lancamentosValidados;
    }

    public void setrResultadoLiquidoDRE(Double valor) {
	this.resultadoLiquidoDRE = valor;
    }

    public Double getrResultadoLiquidoDRE() {
	return this.resultadoLiquidoDRE;
    }

    /**
     * 
     * Constructor
     * 
     * */
    private DadosSistema() {
	Item itemGrupoAtivo = new Item("01", GrupoConta.ATIVO);
	addGrupoContas(itemGrupoAtivo);

	Item itemGrupoAtivoCirculante = new Item("01.01", GrupoConta.ATIVO_CIRCULANTE);
	addGrupoContas(itemGrupoAtivoCirculante);

	Item itemContaNivelDisponivel = new Item("01.01.01", GrupoConta.DISPONIVEL);
	addGrupoContas(itemContaNivelDisponivel);

	Item itemContaNivelContaReceber = new Item("01.01.02", GrupoConta.CONTAS_RECEBER);
	addGrupoContas(itemContaNivelContaReceber);

	Item itemContaNivelEstoque = new Item("01.01.03", GrupoConta.ESTOQUE);
	addGrupoContas(itemContaNivelEstoque);

	Item itemGrupoAtivoNaoCirculante = new Item("01.02", GrupoConta.ATIVO_NAO_CIRCULANTE);
	addGrupoContas(itemGrupoAtivoNaoCirculante);

	Item itemGrupoPassivo = new Item("02", GrupoConta.PASSIVO);
	addGrupoContas(itemGrupoPassivo);

	Item itemGrupoPassivoCirculante = new Item("02.01", GrupoConta.PASSIVO_CIRCULANTE);
	addGrupoContas(itemGrupoPassivoCirculante);

	Item itemGrupoFornecedor = new Item("02.01.01.04", GrupoConta.FORNECEDOR);
	addGrupoContas(itemGrupoFornecedor);

	Item itemGrupoPassivoNaoCirculante = new Item("02.02", GrupoConta.PASSIVO_NAO_CIRCULANTE);
	addGrupoContas(itemGrupoPassivoNaoCirculante);

	Item itemGrupoPL = new Item("03", GrupoConta.PL);
	addGrupoContas(itemGrupoPL);

	Item itemGrupoDRE = new Item("04", GrupoConta.DRE);
	addGrupoContas(itemGrupoDRE);

	Item itemReceitaVendas = new Item("04.01.01", GrupoConta.RECEITA_VENDAS);
	addGrupoContas(itemReceitaVendas);

	Item itemGrupoCustoMercadoria = new Item("04.01.03.01", GrupoConta.CUSTO_MERCADORIA);
	addGrupoContas(itemGrupoCustoMercadoria);

	this.setrResultadoLiquidoDRE(3000.00);

    }

    public void addGrupoContas(Item item) {
	this.grupoContas.add(item);
    }

    public void addGrupoPlanoConta(Item item) {
	this.grupoPlanoContas.add(item);
    }

    public Item getGrupoPlanoConta(String descricao) {

	for (Item item : grupoPlanoContas) {
	    if (item.getDescricao().equals(descricao))
		return item;
	}
	return null;
    }

    public void addNivelPlanoConta(Item item) {
	this.niveisPlanoContas.add(item);
    }

    public Item getNivelPlanoConta(String descricao) {

	for (Item item : niveisPlanoContas) {
	    if (item.getDescricao().equals(descricao))
		return item;
	}
	return null;
    }

    /**
     * 
     * getInstance()
     * 
     * */
    public static DadosSistema getIstance() {
	return instance;
    }

    /**
     * 
     * Metodos Auxiliares
     * 
     * */
    public List<Integer> retornaAnosLancamento() {

	List<Integer> anos = new ArrayList<Integer>();
	int ano = lancamentos.get(0).getAno();
	anos.add(ano);

	for (Lancamento lancamento : this.lancamentos) {

	    if (this.retornaExistencia(lancamento.getAno(), anos) == false) {
		ano = lancamento.getAno();
		anos.add(lancamento.getAno());
	    }
	}
	return anos;
    }

    private boolean retornaExistencia(int anoValue, List<Integer> anos) {

	boolean existe = false;

	for (Integer ano : anos) {

	    if (anoValue == ano) {
		return true;
	    }
	}

	return existe;
    }

    public List<Integer> retornaAnosValidados() {

	List<Validador> anosValidados = getLancamentosValidados();
	List<Integer> anos = new ArrayList<Integer>();

	for (Validador validador : anosValidados) {
	    if (validador.isValidado()) {
		anos.add(validador.getAno());
	    }
	}

	Collections.sort(anos);
	return anos;
    }

    public long getMaiorValor() {

	long maiorCodigo = 0;

	for (Lancamento lancamento : this.lancamentos) {

	    if (lancamento.getCodigoLancamento() > maiorCodigo) {

		maiorCodigo = lancamento.getCodigoLancamento();
	    }
	}

	return maiorCodigo;

    }
   

}