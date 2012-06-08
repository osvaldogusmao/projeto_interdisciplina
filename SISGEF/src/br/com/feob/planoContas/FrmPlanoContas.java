package br.com.feob.planoContas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import br.com.feob.layout.SpringUtilities;
import br.com.feob.renderer.JTextFieldUpperCase;
import br.com.feob.singleton.DadosSistema;

public class FrmPlanoContas extends JFrame implements ActionListener, ListSelectionListener {

    /**
     * Variaveis and Arrays
     * 
     * */
    private int rowSelected = -1;

    private List<PlanoConta> planoContas = DadosSistema.getIstance().getPlanoContas();

    /**
     * Componentes
     * 
     * */
    private JLabel lbConta = new JLabel("Conta", JLabel.RIGHT);
    private JLabel lbDescricao = new JLabel("Descrição", JLabel.RIGHT);
    private JLabel lbNivel = new JLabel("Nível", JLabel.RIGHT);
    private JLabel lbStatus = new JLabel("Status : ", JLabel.LEFT);
    private JLabel lbStatusMsg = new JLabel("", JLabel.LEFT);

    private JFormattedTextField txtConta = new JFormattedTextField();
    private JTextFieldUpperCase txtDescricao = new JTextFieldUpperCase(10);

    private ButtonGroup bgTipo = new ButtonGroup();

    private JRadioButton rbAnalitica = new JRadioButton();
    private JRadioButton rbSintetica = new JRadioButton();

    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnNovo = new JButton("Novo");
    private JButton btnCancelar = new JButton("Cancelar");
    private JButton btnDeletar = new JButton("Deletar");

    private JTable tbPlanoContas = new JTable();
    private JScrollPane spPlanoContas = new JScrollPane(tbPlanoContas);

    private SpringLayout layout = new SpringLayout();

    private JLayeredPane pnForm = new JLayeredPane();
    private JLayeredPane pnTable = new JLayeredPane();
    private JLayeredPane pnLegenda = new JLayeredPane();

    private JPanel pnRadios = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnButtonRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnButtonLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    private PlanoContasTableModel planoContasTableModel;

    /**
     * Constructor
     * 
     * */
    public FrmPlanoContas() {

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();

	this.setTitle("Plano de contas");
	this.setSize(470, 600);
	this.setMaximumSize(new Dimension(470, 600));
	this.setMinimumSize(new Dimension(470, 600));
	this.setLayout(new GridLayout(2, 1));
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setResizable(false);

	this.setLocation((int) (dimension.getWidth() - this.getWidth()) / 2, (int) (dimension.getHeight() - this.getHeight()) / 2);

	/**
	 * 
	 * Configurações
	 * 
	 * 
	 * */
	btnSalvar.setIcon(new ImageIcon(getClass().getResource("/assets/images/save.png")));
	btnNovo.setIcon(new ImageIcon(getClass().getResource("/assets/images/new.png")));
	btnNovo.setFocusable(false);
	btnCancelar.setIcon(new ImageIcon(getClass().getResource("/assets/images/cancel.png")));
	btnDeletar.setIcon(new ImageIcon(getClass().getResource("/assets/images/delete.png")));
	btnCancelar.setFocusable(false);

	pnForm.setBorder(BorderFactory.createTitledBorder("Cadastro de Conta Contábil"));

	pnTable.setLayout(new GridLayout(2, 1));
	pnTable.setBorder(BorderFactory.createTitledBorder("Plano de Contas"));

	/**
	 * 
	 * Perimite selecionar apenas 1 item da grid
	 * 
	 * */
	tbPlanoContas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	btnDeletar.setEnabled(false);
	btnCancelar.setEnabled(false);
	btnSalvar.setEnabled(false);
	actionForm("C");

	/**
	 * Table
	 * 
	 * */
	if (planoContas == null) {

	    JOptionPane.showMessageDialog(null, "Você não importou seu plano de contas.\n"
		    + "Você deve importar usando a tela de importação, localizada no Menu [Importação]");
	    this.remove(this);
	} else {

	    getTableModel();
	    /**
	     * Estudar: É aqui onde é setado o conteudo da tabela
	     * 
	     * */
	    tbPlanoContas.setModel(planoContasTableModel);

	    pnTable.setSize(420, 500);
	    pnTable.setLayout(new GridLayout(1, 1));
	    
	    /**
	     * 
	     * Mostra as linhas da tabela
	     * */
	    tbPlanoContas.setShowGrid(true);
	    tbPlanoContas.setShowVerticalLines(true);
	    tbPlanoContas.setShowHorizontalLines(true);
	    
	    /**
	     * Cor de fundo ou cor da linha
	     * 
	     * */
	    tbPlanoContas.setGridColor(new Color(204, 204, 204));
	    
	    /**
	     * Define o tamanho de cado coluna
	     * */
	    tbPlanoContas.getColumnModel().getColumn(0).setMinWidth(100);
	    tbPlanoContas.getColumnModel().getColumn(0).setMaxWidth(100);
	    tbPlanoContas.getColumnModel().getColumn(1).setMinWidth(350);
	    tbPlanoContas.getColumnModel().getColumn(1).setMaxWidth(350);

	    
	    pnTable.add(spPlanoContas);

	    /**
	     * 
	     * Eventos
	     * 
	     * */
	    tbPlanoContas.getSelectionModel().addListSelectionListener(this);

	    /**
	     * Formulário
	     * 
	     * */
	    pnForm.setLayout(layout);

	    pnForm.add(lbConta);

	    try {
		txtConta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new MaskFormatter("##.##.##.##")));
	    } catch (java.text.ParseException ex) {
		ex.printStackTrace();
	    }

	    pnForm.add(txtConta);
	    pnForm.add(lbDescricao);
	    pnForm.add(txtDescricao);
	    pnForm.add(lbNivel);
	    bgTipo.add(rbAnalitica);
	    bgTipo.add(rbSintetica);
	    rbSintetica.setText("Sintético");
	    rbAnalitica.setText("Analítica");
	    pnRadios.add(rbSintetica);
	    pnRadios.add(rbAnalitica);
	    pnForm.add(pnRadios);
	    pnButtonLeft.add(btnNovo);
	    pnButtonRight.add(btnSalvar);
	    pnButtonRight.add(btnCancelar);
	    pnButtonRight.add(btnDeletar);
	    pnForm.add(pnButtonLeft);
	    pnForm.add(pnButtonRight);
	    pnForm.add(lbStatus);
	    pnForm.add(lbStatusMsg);

	    // (Componente, linhas, colunas, left, top,
	    // distancia_entre_projetos_v,distancia_entre_projetos_h )
	    SpringUtilities.makeCompactGrid(pnForm, 5, 2, 5, 10, 5, 15);

	    /**
	     * 
	     * Eventos Formulários
	     * 
	     * */
	    btnSalvar.addActionListener(this);
	    btnNovo.addActionListener(this);
	    btnCancelar.addActionListener(this);
	    btnDeletar.addActionListener(this);

	    this.add(pnTable);
	    this.add(pnForm);

	    this.setVisible(true);
	}

    }

    @Override
    public void actionPerformed(ActionEvent e) {

	if (e.getSource() == this.btnSalvar) {

	    String validacao = validateForm();

	    if (validacao.isEmpty()) {
		if (rowSelected > 0) {
		    planoContas.get(rowSelected).setConta(txtConta.getText());
		    planoContas.get(rowSelected).setDescricao(txtDescricao.getText());

		    if (rbAnalitica.isSelected()) {
			planoContas.get(rowSelected).setNivel("A");
		    }
		    if (rbSintetica.isSelected()) {
			planoContas.get(rowSelected).setNivel("S");
		    }
		    lbStatusMsg.setForeground(new Color(0, 127, 0));
		    lbStatusMsg.setText("Dados salvos com sucesso!!!");
		    actionForm("C");

		} else {
		    if (planoContasTableModel.verificaItemExistente(txtConta.getText()) == false) {
			planoContas.add(new PlanoConta(txtConta.getText().toUpperCase(), txtDescricao.getText().toUpperCase(), getSelectedNivel()));
			planoContasTableModel.setData(planoContas);
		    }else{
			lbStatusMsg.setForeground(new Color(191, 0, 0));
			lbStatusMsg.setText("Erro ao salvar os dados!!!");
			JOptionPane.showMessageDialog(null, validacao);
		    }
		}
	    } else {
		lbStatusMsg.setForeground(new Color(191, 0, 0));
		lbStatusMsg.setText("Erro ao salvar os dados!!!");
		JOptionPane.showMessageDialog(null, "Conta já existente.");
	    }
	}

	if (e.getSource() == this.btnCancelar) {
	    actionForm("C");
	}
	
	if (e.getSource() == this.btnDeletar) {
	    int opcao = JOptionPane.showConfirmDialog(null, "Deseja remover este item?", "Atenção", JOptionPane.YES_NO_OPTION,
		    JOptionPane.WARNING_MESSAGE);
	    
	    if (opcao == JOptionPane.YES_OPTION) {
		planoContas.remove(rowSelected);
		planoContasTableModel.deletePlanoConta(rowSelected);
	    }
	}
	if (e.getSource() == this.btnNovo) {
	    rowSelected = -1;
	    actionForm("N");
	}
	
    }

    private String getSelectedNivel() {

	if (rbAnalitica.isSelected()) {
	    return "A";
	} else {
	    return "S";
	}

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

	if (e.getSource() == tbPlanoContas.getSelectionModel() && tbPlanoContas.getRowSelectionAllowed()
		&& rowSelected != tbPlanoContas.getSelectedRow()) {
	    rowSelected = tbPlanoContas.getSelectedRow();
	    setItemSelected(rowSelected);
	}
    }

    /**
     * 
     * Seta o item selecionado nos campos do formulário
     * 
     * */
    private void setItemSelected(int indice) {

	actionForm("E");
	txtConta.setText(planoContas.get(indice).getConta());
	txtDescricao.setText(planoContas.get(indice).getDescricao());

	if (planoContas.get(indice).getNivel().equals("S")) {
	    rbSintetica.setSelected(true);
	} else {
	    rbAnalitica.setSelected(true);
	}

	lbStatusMsg.setText("Selecionado a conta : " + planoContas.get(indice).getConta());

    }

    /**
     * 
     * Valida prenchimento do formulario
     * 
     * */
    private String validateForm() {

	String validacao = "";

	if (txtConta.getText().replace(".", "").replace(" ", "").isEmpty()) {
	    validacao = validacao + "\nCampo [Conta] está em branco";
	}
	if (txtDescricao.getText().isEmpty()) {
	    validacao = validacao + "\nCampo [Descrição] está em branco";
	}
	if (!rbAnalitica.isSelected() && !rbSintetica.isSelected()) {
	    validacao = validacao + "\nÉ obrigatório escolher um [Nível]";
	}

	return validacao;
    }

    /**
     * 
     * Habilita formulario para Ediçao e Adição
     * 
     * */
    private void actionForm(String opcao) {

	if (opcao.equalsIgnoreCase("N")) {
	    txtConta.requestFocus();
	    txtConta.setText("");
	    txtConta.setEditable(true);
	    txtDescricao.setText("");
	    txtDescricao.setEditable(true);
	    rbAnalitica.setEnabled(true);
	    rbAnalitica.setSelected(false);
	    rbSintetica.setEnabled(true);
	    rbSintetica.setSelected(false);
	    btnDeletar.setEnabled(false);
	    btnCancelar.setEnabled(true);
	    btnSalvar.setEnabled(true);
	    lbStatusMsg.setText("");
	} else if (opcao.equalsIgnoreCase("C")) {
	    txtConta.setText("");
	    txtConta.setEditable(false);
	    txtDescricao.setText("");
	    txtDescricao.setEditable(false);
	    rbAnalitica.setEnabled(false);
	    rbAnalitica.setSelected(false);
	    rbSintetica.setEnabled(false);
	    rbSintetica.setSelected(false);
	    btnDeletar.setEnabled(false);
	    btnCancelar.setEnabled(false);
	    btnSalvar.setEnabled(false);
	    btnNovo.setEnabled(true);
	    lbStatusMsg.setText("");
	} else if (opcao.equalsIgnoreCase("E")) {
	    txtConta.setEditable(true);
	    txtDescricao.setEditable(true);
	    rbAnalitica.setEnabled(true);
	    rbSintetica.setEnabled(true);
	    btnDeletar.setEnabled(true);
	    btnCancelar.setEnabled(true);
	    btnSalvar.setEnabled(true);
	    btnNovo.setEnabled(true);
	    lbStatusMsg.setText("");
	}
    }

    private TableModel getTableModel() {
	planoContasTableModel = new PlanoContasTableModel(planoContas);
	return planoContasTableModel;
    }
}