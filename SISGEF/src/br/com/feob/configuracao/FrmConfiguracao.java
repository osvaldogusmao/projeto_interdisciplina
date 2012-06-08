package br.com.feob.configuracao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import br.com.feob.singleton.DadosSistema;
import br.com.feob.singleton.Item;
import br.com.feob.util.Util;

public class FrmConfiguracao extends JPanel implements ActionListener {

    /**
     * 
     * Class Util
     * 
     * */
    private Util util = new Util();

    /**
     * Componentes
     * */
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel pnConfConta = new JPanel();
    private JPanel pnConfNivel = new JPanel();
    private JPanel pnButtons = new JPanel();

    private JLabel lbGrupoConta = new JLabel("Grupo de contas");

    private JLabel lbGrupoAtivo = new JLabel("Ativo");
    private JLabel lbGrupoPassivo = new JLabel("Passivo");
    private JLabel lbGrupoPL = new JLabel("Patrimônio Líquido");
    private JLabel lbGrupoResultado = new JLabel("Resultado");

    private JTextField txtGrupoAtivo = new JTextField(5);
    private JTextField txtGrupoPassivo = new JTextField(5);
    private JTextField txtGrupoPL = new JTextField(5);
    private JTextField txtGrupoResultado = new JTextField(5);
    
    private JLabel lbGrupoNivel = new JLabel("Configuração de nível");
    private JLabel lbDisponivel = new JLabel("Disponível");
    private JLabel lbContasReceber = new JLabel("Contas a receber");
    private JLabel lbEstoque = new JLabel("Estoque");

    
    private JTextField txtDisponivel = new JTextField(5);
    private JTextField txtContasReceber = new JTextField(5);
    private JTextField txtEstoque = new JTextField(5);
    
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnBuscar = new JButton("Carregar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JButton btnDeletar = new JButton("Deletar");

    private DadosSistema dadosSistema = DadosSistema.getIstance();
    
    /**
     * Constructor
     * */
    public FrmConfiguracao() {

	/**
	 * 
	 * Configuração
	 * 
	 * */
	this.setLayout(new BorderLayout());
	pnConfConta.setLayout(new BorderLayout());
	pnConfNivel.setLayout(new BorderLayout());
	this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	
	
	tabbedPane.addTab("Configuração Conta", pnConfConta);
	tabbedPane.addTab("Configuração Níveis", pnConfNivel);
	/**
	 * 
	 * Components
	 * 
	 * */
	btnSalvar.setIcon(new ImageIcon(getClass().getResource("/assets/images/save.png")));
	btnBuscar.setIcon(new ImageIcon(getClass().getResource("/assets/images/search.png")));
	btnBuscar.setFocusable(false);
	btnCancelar.setIcon(new ImageIcon(getClass().getResource("/assets/images/cancel.png")));
	btnDeletar.setIcon(new ImageIcon(getClass().getResource("/assets/images/delete.png")));
	btnCancelar.setFocusable(false);

	btnSalvar.addActionListener(this);
	btnBuscar.addActionListener(this);
	
	
	/**
	 * Add Components
	 * 
	 * */
	lbGrupoAtivo.setLabelFor(txtGrupoAtivo);
	lbGrupoPassivo.setLabelFor(txtGrupoPassivo);
	lbGrupoPL.setLabelFor(txtGrupoPL);
	lbGrupoResultado.setLabelFor(txtGrupoResultado);
	
	
	/**
	 * 
	 * Aba configuração de grupo
	 * 
	 * */
	JPanel panelLbGrupo = new JPanel(new GridLayout(0,1));
	panelLbGrupo.add(lbGrupoAtivo);
	panelLbGrupo.add(lbGrupoPassivo);
	panelLbGrupo.add(lbGrupoPL);
	panelLbGrupo.add(lbGrupoResultado);
	
	
	JPanel panelFdGrupo = new JPanel(new GridLayout(0,1));
	panelFdGrupo.add(txtGrupoAtivo);
	panelFdGrupo.add(txtGrupoPassivo);
	panelFdGrupo.add(txtGrupoPL);
	panelFdGrupo.add(txtGrupoResultado);
	
	pnConfConta.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	pnConfConta.add(panelLbGrupo, BorderLayout.WEST);
	pnConfConta.add(panelFdGrupo, BorderLayout.LINE_END);
	
	/**
	 * 
	 * Aba configuração de nível
	 * 
	 * */
	lbDisponivel.setLabelFor(txtDisponivel);
	lbEstoque.setLabelFor(txtEstoque);
	lbContasReceber.setLabelFor(txtContasReceber);
	
	JPanel panelLbNivel = new JPanel(new GridLayout(0,1));
	panelLbNivel.add(lbDisponivel);
	panelLbNivel.add(lbEstoque);
	panelLbNivel.add(lbContasReceber);
	
	JPanel panelFdNivel = new JPanel(new GridLayout(0,1));
	panelFdNivel.add(txtDisponivel);
	panelFdNivel.add(txtEstoque);
	panelFdNivel.add(txtContasReceber);
	
	pnConfNivel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	pnConfNivel.add(panelLbNivel, BorderLayout.WEST);
	pnConfNivel.add(panelFdNivel, BorderLayout.LINE_END);
	
	/**
	 * 
	 * Construção do JFrame
	 * 
	 * */
	pnButtons.add(btnBuscar);
	pnButtons.add(btnSalvar);
	//pnButtons.add(btnCancelar);
	//pnButtons.add(btnDeletar);

	this.add(tabbedPane, BorderLayout.NORTH);
	this.add(pnButtons, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	
	if(e.getSource() == btnSalvar){
	    
	    if(validateFields()){
		
//		dadosSistema.addGrupoPlanoConta(new Item(txtGrupoAtivo.getText(), lbGrupoAtivo.getText()));
//		dadosSistema.addGrupoPlanoConta(new Item(txtGrupoPassivo.getText(), lbGrupoPassivo.getText()));
//		dadosSistema.addGrupoPlanoConta(new Item(txtGrupoPL.getText(), lbGrupoPL.getText()));
//		dadosSistema.addGrupoPlanoConta(new Item(txtGrupoResultado.getText(), lbGrupoResultado.getText()));
//		
//		dadosSistema.addNivelPlanoConta(new Item(txtDisponivel.getText(), lbDisponivel.getText()));
//		dadosSistema.addNivelPlanoConta(new Item(txtEstoque.getText(), lbEstoque.getText()));
//		dadosSistema.addNivelPlanoConta(new Item(txtContasReceber.getText(), lbContasReceber.getText()));
		
		JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
		
	    }else{
		JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Atenção", JOptionPane.WARNING_MESSAGE);
	    }
	}
	if(e.getSource() == btnBuscar){
	    
	    txtGrupoAtivo.setText(dadosSistema.getGrupoPlanoConta(lbGrupoAtivo.getText()).getConta());
	    txtGrupoPassivo.setText(dadosSistema.getGrupoPlanoConta(lbGrupoPassivo.getText()).getConta());
	    txtGrupoPL.setText(dadosSistema.getGrupoPlanoConta(lbGrupoPL.getText()).getConta());
	    txtGrupoResultado.setText(dadosSistema.getGrupoPlanoConta(lbGrupoResultado.getText()).getConta());
	    
	    txtDisponivel.setText(dadosSistema.getNivelPlanoConta(lbDisponivel.getText()).getConta());
	    txtEstoque.setText(dadosSistema.getNivelPlanoConta(lbEstoque.getText()).getConta());
	    txtContasReceber.setText(dadosSistema.getNivelPlanoConta(lbContasReceber.getText()).getConta());
	 
	    JOptionPane.showMessageDialog(null, "Dados carregados com sucesso.", "Informação", JOptionPane.INFORMATION_MESSAGE);
	}
	
    }
    
    private boolean validateFields(){
	
	if(txtGrupoAtivo.getText().isEmpty() || txtGrupoPassivo.getText().isEmpty() ||
		txtGrupoPL.getText().isEmpty() || txtGrupoResultado.getText().isEmpty() ||
		txtDisponivel.getText().isEmpty() || txtEstoque.getText().isEmpty() ||
		txtContasReceber.getText().isEmpty())
	return false;
	
	return true;
    }

}
