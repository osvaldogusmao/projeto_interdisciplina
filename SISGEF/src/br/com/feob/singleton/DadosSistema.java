package br.com.feob.singleton;

import java.util.ArrayList;
import java.util.List;

import br.com.feob.lancamento.Lancamento;
import br.com.feob.lancamento.Validador;
import br.com.feob.planoContas.PlanoConta;
import br.com.feob.util.GrupoConta;

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

    /**
     * 
     * Constructor
     * 
     * */
    private DadosSistema() {
	Item itemGrupoAtivo = new Item("01", GrupoConta.ATIVO);
	addGrupoContas(itemGrupoAtivo);

	Item itemGrupoPassivo = new Item("02", GrupoConta.PASSIVO);
	addGrupoContas(itemGrupoPassivo);

	Item itemGrupoPL = new Item("03", GrupoConta.PL);
	addGrupoContas(itemGrupoPL);
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
	    if (ano != lancamento.getAno()) {
		ano = lancamento.getAno();
		anos.add(lancamento.getAno());
	    }
	}
	return anos;
    }

}
