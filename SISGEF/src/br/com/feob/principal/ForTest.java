package br.com.feob.principal;

import java.util.ArrayList;

import br.com.feob.indices.FrmGraficos;
import br.com.feob.indices.FrmIndices;
import br.com.feob.indices.Indice;
import br.com.feob.lancamento.FrmLancamento;
import br.com.feob.lancamento.FrmValidarLancamento;

public class ForTest {

    public static void main(String[] args) {
	//new FrmLancamento().setVisible(true);
	
	//new FrmIndices().setVisible(true);
	
	FrmGraficos graficos = new FrmGraficos(new ArrayList<Indice>(), "Liquidez Imediata");
	graficos.pack();
	graficos.setVisible(true);
    }
    
}
