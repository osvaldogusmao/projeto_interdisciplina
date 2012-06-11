package br.com.feob.indices.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.feob.Enum.GrupoConta;
import br.com.feob.indices.Indice;
import br.com.feob.lancamento.repository.LancamentoRepository;
import br.com.feob.singleton.DadosSistema;

public class IndiceRepository {

    private LancamentoRepository lancamentoRepository = new LancamentoRepository();

    public IndiceRepository() {

    }

    public Double getLiquidezImediata(int ano) {

	double disponivel = lancamentoRepository.getTotalContaTerceiroNivel(ano, GrupoConta.DISPONIVEL);
	double passivoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_CIRCULANTE);

	return (disponivel / passivoCirculante);
    }

    public Double getLiquidezCorrente(int ano) {

	double ativoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.ATIVO_CIRCULANTE);
	double passivoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_CIRCULANTE);

	return (ativoCirculante / passivoCirculante);
    }

    public Double getLiquidezSeca(int ano) {

	double ativoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.ATIVO_CIRCULANTE);
	double estoque = lancamentoRepository.getTotalContaTerceiroNivel(ano, GrupoConta.ESTOQUE);
	double passivoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_CIRCULANTE);

	return (ativoCirculante - estoque) / passivoCirculante;
    }

    public Double getLiquidezGeral(int ano) {

	double ativoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.ATIVO_CIRCULANTE);
	double passivoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_CIRCULANTE);
	double passivoNaoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_NAO_CIRCULANTE);

	return (ativoCirculante / (passivoCirculante + passivoNaoCirculante));
    }

    public Double getEndividamentoQuantidade(int ano) {

	double passivo = lancamentoRepository.getTotalContaPrimeiroNivel(ano, GrupoConta.PASSIVO);
	double passivoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_CIRCULANTE);
	double passivoNaoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_NAO_CIRCULANTE);
	double patrimonioLiquido = lancamentoRepository.getTotalContaPrimeiroNivel(ano, GrupoConta.PL);

	return ((passivoCirculante + passivoNaoCirculante) / ((passivo) + patrimonioLiquido));
    }

    public Double getEndividamentoQualidade(int ano) {

	double passivoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_CIRCULANTE);
	double passivoNaoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_NAO_CIRCULANTE);

	return (passivoCirculante / (passivoCirculante + passivoNaoCirculante));
    }

    public Double getEndividamentoTercProp(int ano) {

	double passivoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_CIRCULANTE);
	double passivoNaoCirculante = lancamentoRepository.getTotalContaSegundoNivel(ano, GrupoConta.PASSIVO_NAO_CIRCULANTE);
	double patrimonioLiquido = lancamentoRepository.getTotalContaPrimeiroNivel(ano, GrupoConta.PL);

	return ((passivoCirculante + passivoNaoCirculante) / patrimonioLiquido);
    }

    public Double getPMRE(int ano) {
	double estoque = lancamentoRepository.getTotalContaTerceiroNivel(ano, GrupoConta.ESTOQUE);
	double receitaVendas = lancamentoRepository.getTotalContaTerceiroNivel(ano, GrupoConta.RECEITA_VENDAS);

	if (receitaVendas == 0.0) {
	    return 0.00;
	} else {

	    return ((estoque / receitaVendas) * 360);
	}
    }

    public Double getPMRV(int ano) {
	double duplicataReceber = lancamentoRepository.getTotalContaTerceiroNivel(ano, GrupoConta.CONTAS_RECEBER);
	double receitaVendas = lancamentoRepository.getTotalContaTerceiroNivel(ano, GrupoConta.RECEITA_VENDAS);

	if (receitaVendas == 0.0) {
	    return 0.00;
	} else {
	    return ((duplicataReceber / receitaVendas) * 360);
	}
    }

    public Double getPMPC(int ano) {

	double fornecedor = lancamentoRepository.getTotalContaQuartoNivel(ano, GrupoConta.FORNECEDOR);
	double estoqueAnterior = lancamentoRepository.getTotalContaTerceiroNivel(ano - 1, GrupoConta.ESTOQUE);
	double estoque = lancamentoRepository.getTotalContaTerceiroNivel(ano, GrupoConta.ESTOQUE);
	double custoMercadoria = lancamentoRepository.getTotalContaQuartoNivel(ano, GrupoConta.CUSTO_MERCADORIA);

	if (custoMercadoria == 0.0) {
	    return 0.00;
	} else {
	    return (fornecedor / ((0 - custoMercadoria) - estoqueAnterior + estoque) * 360);
	}
    }

    public Double getCicloOperacional(int ano) {
	return getPMRE(ano) + getPMRV(ano);
    }

    public Double getCicloCaixa(int ano) {

	double ciclo = (getPMRE(ano - 1) + getPMRV(ano - 1)) - getPMPC(ano - 1);

	return (Double.compare(ciclo, Double.NaN) == 0) ? 0.0 : ciclo;
    }

    public Double getMargemLucro(int ano) {

	double resultadoLiquidoDRE = DadosSistema.getIstance().getrResultadoLiquidoDRE();
	double receitaVendas = lancamentoRepository.getTotalContaTerceiroNivel(ano, GrupoConta.RECEITA_VENDAS);

	if (receitaVendas == 0.0) {
	    return 0.00;
	} else {

	    return (resultadoLiquidoDRE / receitaVendas) * 100;
	}

    }

    public Double getGiroAtivo(int ano) {

	double receitaVendas = lancamentoRepository.getTotalContaTerceiroNivel(ano, GrupoConta.RECEITA_VENDAS);
	double ativo = lancamentoRepository.getTotalContaPrimeiroNivel(ano, GrupoConta.ATIVO);

	if (receitaVendas == 0.0 || ativo == 0.0) {
	    return 0.00;
	} else {

	    return (receitaVendas / ativo);
	}
    }

    public Double getTRI(int ano) {

	return getMargemLucro(ano) * getGiroAtivo(ano);

    }

    public List<Indice> getIndecesPorAno(int ano) {

	List<Indice> indices = new ArrayList<Indice>();

	Indice liquidezImediata = new Indice();
	liquidezImediata.setAno(ano);
	liquidezImediata.setDescricao("Liquidez Imediata");
	liquidezImediata.setValor(getLiquidezImediata(ano));
	indices.add(liquidezImediata);

	Indice liquidezCorrente = new Indice();
	liquidezCorrente.setAno(ano);
	liquidezCorrente.setDescricao("Liquidez Corrente");
	liquidezCorrente.setValor(getLiquidezCorrente(ano));
	indices.add(liquidezCorrente);

	Indice liquidezSeca = new Indice();
	liquidezSeca.setAno(ano);
	liquidezSeca.setDescricao("Liquidez Seca");
	liquidezSeca.setValor(getLiquidezSeca(ano));
	indices.add(liquidezSeca);

	Indice liquidezGeral = new Indice();
	liquidezGeral.setAno(ano);
	liquidezGeral.setDescricao("Liquidez Geral");
	liquidezGeral.setValor(getLiquidezGeral(ano));
	indices.add(liquidezGeral);

	Indice endividaQuantidade = new Indice();
	endividaQuantidade.setAno(ano);
	endividaQuantidade.setDescricao("Endividamento(Quantidade)");
	endividaQuantidade.setValor(getEndividamentoQuantidade(ano));
	indices.add(endividaQuantidade);

	Indice endividaQualidade = new Indice();
	endividaQualidade.setAno(ano);
	endividaQualidade.setDescricao("Endividamento(Qualidade)");
	endividaQualidade.setValor(getEndividamentoQualidade(ano));
	indices.add(endividaQualidade);

	Indice endividaTercProp = new Indice();
	endividaTercProp.setAno(ano);
	endividaTercProp.setDescricao("Endividamento(3º e Próprios)");
	endividaTercProp.setValor(getEndividamentoTercProp(ano));
	indices.add(endividaTercProp);

	Indice PMRE = new Indice();
	PMRE.setAno(ano);
	PMRE.setDescricao("PMRE");
	PMRE.setValor(getPMRE(ano));
	indices.add(PMRE);

	Indice PMRV = new Indice();
	PMRV.setAno(ano);
	PMRV.setDescricao("PMRV");
	PMRV.setValor(getPMRV(ano));
	indices.add(PMRV);

	Indice PMPC = new Indice();
	PMPC.setAno(ano);
	PMPC.setDescricao("PMPC");
	PMPC.setValor(getPMPC(ano));
	indices.add(PMPC);

	Indice cicloOperacional = new Indice();
	cicloOperacional.setAno(ano);
	cicloOperacional.setDescricao("Ciclo Operacional");
	cicloOperacional.setValor(getCicloOperacional(ano));
	indices.add(cicloOperacional);

	Indice cicloCaixa = new Indice();
	cicloCaixa.setAno(ano);
	cicloCaixa.setDescricao("Ciclo Caixa");
	cicloCaixa.setValor(getCicloCaixa(ano));
	indices.add(cicloCaixa);

	Indice margemLucro = new Indice();
	margemLucro.setAno(ano);
	margemLucro.setDescricao("Margem de Lucro");
	margemLucro.setValor(getMargemLucro(ano));
	indices.add(margemLucro);

	Indice giroAtivo = new Indice();
	giroAtivo.setAno(ano);
	giroAtivo.setDescricao("Giro do Ativo");
	giroAtivo.setValor(getGiroAtivo(ano));
	indices.add(giroAtivo);

	Indice TRI = new Indice();
	TRI.setAno(ano);
	TRI.setDescricao("TRI");
	TRI.setValor(getTRI(ano));
	indices.add(TRI);

	return indices;
    }

}