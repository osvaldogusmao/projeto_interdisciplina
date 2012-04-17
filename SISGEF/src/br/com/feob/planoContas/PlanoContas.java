package br.com.feob.planoContas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.feob.layout.SpringUtilities;
import br.com.feob.renderer.JTextFieldUpperCase;
import br.com.feob.singleton.DadosSistema;

public class PlanoContas extends JFrame implements ActionListener,
	ListSelectionListener {

    /**
     * Variaveis and Arrays
     * 
     * */
    private int rowSelected = -1;

    private String[] column = { "Conta", "Descri��o", "N�vel", "Tipo" };

    private String[][] planoContas = DadosSistema.getIstance().getPlanoContas();

    private String[] itensTipoConta = { "ATIVO", "ATIVO CIRCULANTE",
	    "ATIVO N�O CIRCULANTE", "PASSIVO", "PASSIVO CIRCULANTE",
	    "PASSIVO N�O CIRCULANTE", "PATRIM�NIO L�QUIDO", "DRE" };

    /**
     * Componentes
     * 
     * */
    private JLabel lbConta = new JLabel("Conta", JLabel.RIGHT);
    private JLabel lbDescricao = new JLabel("Descri��o", JLabel.RIGHT);
    private JLabel lbTipoConta = new JLabel("Tipo conta", JLabel.RIGHT);
    private JLabel lbNivel = new JLabel("N�vel", JLabel.RIGHT);
    private JLabel lbStatus = new JLabel("Status : ", JLabel.LEFT);
    private JLabel lbStatusMsg = new JLabel("", JLabel.LEFT);
    private JFormattedTextField txtConta = new JFormattedTextField();
    private JTextFieldUpperCase txtDescricao = new JTextFieldUpperCase(10);
    private JComboBox<String> cbTipoConta = new JComboBox<String>(
	    itensTipoConta);
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
    private JPanel pnRadios = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnButtonRight = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnButtonLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    /**
     * Constructor
     * 
     * */
    public PlanoContas() {

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();

	this.setTitle("Plano de contas");
	this.setSize(470, 600);
	this.setMaximumSize(new Dimension(470, 600));
	this.setMinimumSize(new Dimension(470, 600));
	this.setLayout(new GridLayout(2, 1));
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	this.setLocation((int) (dimension.getWidth() - this.getWidth()) / 2,
		(int) (dimension.getHeight() - this.getHeight()) / 2);

	/**
	 * 
	 * Configura��es
	 * 
	 * 
	 * */
	btnSalvar.setIcon(new ImageIcon(getClass().getResource(
		"/assets/images/save.png")));
	btnNovo.setIcon(new ImageIcon(getClass().getResource(
		"/assets/images/new.png")));
	btnNovo.setFocusable(false);
	btnCancelar.setIcon(new ImageIcon(getClass().getResource(
		"/assets/images/cancel.png")));
	btnDeletar.setIcon(new ImageIcon(getClass().getResource(
		"/assets/images/delete.png")));
	btnCancelar.setFocusable(false);

	pnForm.setBorder(BorderFactory
		.createTitledBorder("Cadastro de Conta Cont�bil"));
	pnTable.setBorder(BorderFactory.createTitledBorder("Plano de Contas"));

	tbPlanoContas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	cbTipoConta.setSelectedIndex(-1);

	btnDeletar.setEnabled(false);
	btnCancelar.setEnabled(false);
	btnSalvar.setEnabled(false);
	actionForm("C");

	/**
	 * Table
	 * 
	 * */
	if (planoContas == null) {

	    JOptionPane
		    .showMessageDialog(
			    null,
			    "Voc� n�o importou seu plano de contas.\n"
				    + "Voc� deve importar usando a tela de importa��o, localizada no Menu [Importa��o]");
	    this.remove(this);
	} else {
	    tbPlanoContas.setModel(new DefaultTableModel(planoContas, column) {
		boolean[] canEdit = new boolean[] { false, false, false };

		public boolean isCellEditable(int rowIndex, int columnIndex) {
		    return canEdit[columnIndex];
		}
	    });

	    pnForm.setBackground(Color.RED);
	    pnTable.setSize(420, 500);
	    pnTable.setLayout(new GridLayout(1, 1));
	    tbPlanoContas.setShowGrid(true);
	    tbPlanoContas.setShowVerticalLines(true);
	    tbPlanoContas.setShowHorizontalLines(true);
	    tbPlanoContas.setGridColor(new Color(204, 204, 204));
	    tbPlanoContas.getColumnModel().getColumn(0).setMinWidth(100);
	    tbPlanoContas.getColumnModel().getColumn(0).setMaxWidth(100);
	    tbPlanoContas.getColumnModel().getColumn(1).setMinWidth(130);
	    tbPlanoContas.getColumnModel().getColumn(1).setMaxWidth(130);
	    tbPlanoContas.getColumnModel().getColumn(2).setMinWidth(130);
	    tbPlanoContas.getColumnModel().getColumn(2).setMaxWidth(130);
	    tbPlanoContas.getColumnModel().getColumn(2).setMinWidth(100);
	    tbPlanoContas.getColumnModel().getColumn(2).setMaxWidth(100);

	    pnTable.add(spPlanoContas);

	    /**
	     * 
	     * Eventos
	     * 
	     * */
	    tbPlanoContas.getSelectionModel().addListSelectionListener(this);

	    /**
	     * Formul�rio
	     * 
	     * */
	    pnForm.setLayout(layout);

	    pnForm.add(lbConta);

	    try {
		txtConta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
			new MaskFormatter("##.##.##")));
	    } catch (java.text.ParseException ex) {
		ex.printStackTrace();
	    }

	    pnForm.add(txtConta);
	    pnForm.add(lbDescricao);
	    pnForm.add(txtDescricao);
	    pnForm.add(lbTipoConta);
	    pnForm.add(cbTipoConta);
	    pnForm.add(lbNivel);
	    bgTipo.add(rbAnalitica);
	    bgTipo.add(rbSintetica);
	    rbSintetica.setText("Sint�tico");
	    rbAnalitica.setText("Anal�tica");
	    pnRadios.add(rbSintetica);
	    pnRadios.add(rbAnalitica);
	    pnForm.add(pnRadios);
	    pnButtonLeft.add(btnNovo);
	    // pnButtonRight.add(btnEditar);
	    pnButtonRight.add(btnSalvar);
	    pnButtonRight.add(btnCancelar);
	    pnButtonRight.add(btnDeletar);
	    pnForm.add(pnButtonLeft);
	    pnForm.add(pnButtonRight);
	    pnForm.add(lbStatus);
	    pnForm.add(lbStatusMsg);

	    // (Componente, linhas, colunas, left, top,
	    // distancia_entre_projetos_v,distancia_entre_projetos_h )
	    SpringUtilities.makeCompactGrid(pnForm, 6, 2, 5, 10, 5, 15);

	    /**
	     * 
	     * Eventos Formul�rios
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
		planoContas[rowSelected][0] = txtConta.getText();
		planoContas[rowSelected][1] = txtDescricao.getText();

		if (rbAnalitica.isSelected()) {
		    planoContas[rowSelected][2] = rbAnalitica.getText()
			    .toUpperCase();
		}
		if (rbSintetica.isSelected()) {
		    planoContas[rowSelected][2] = rbSintetica.getText()
			    .toUpperCase();
		}
		planoContas[rowSelected][3] = cbTipoConta.getSelectedItem()
			.toString();
		refreshJTable();
		lbStatusMsg.setForeground(new Color(0, 127, 0));
		lbStatusMsg.setText("Dados salvos com sucesso!!!");
		actionForm("C");
	    } else {
		lbStatusMsg.setForeground(new Color(191, 0, 0));
		lbStatusMsg.setText("Erro ao salvar os dados!!!");
		JOptionPane.showMessageDialog(null, validacao);
	    }

	}

	if (e.getSource() == this.btnCancelar) {
	    actionForm("C");
	}
	if (e.getSource() == this.btnDeletar) {
	    int opcao = JOptionPane.showConfirmDialog(null,
		    "Deseja remover este item?", "Aten��o",
		    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	    if (opcao == 1) {

	    }
	}
	if (e.getSource() == this.btnNovo) {
	    actionForm("N");
	}

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

	if (e.getSource() == tbPlanoContas.getSelectionModel()
		&& tbPlanoContas.getRowSelectionAllowed()
		&& rowSelected != tbPlanoContas.getSelectedRow()) {
	    rowSelected = tbPlanoContas.getSelectedRow();
	    setItemSelected(rowSelected);
	}
    }

    /**
     * 
     * Seta o item selecionado nos campos do formul�rio
     * 
     * */
    private void setItemSelected(int indice) {

	actionForm("E");
	txtConta.setText(planoContas[indice][0]);
	txtDescricao.setText(planoContas[indice][1]);

	if (planoContas[indice][2].equalsIgnoreCase(rbSintetica.getText())) {
	    rbSintetica.setSelected(true);
	} else {
	    rbAnalitica.setSelected(true);
	}
	cbTipoConta.setSelectedIndex(getItemSelected(planoContas[indice][3],
		itensTipoConta));

	lbStatusMsg.setText("");

    }

    /**
     * 
     * Refresh no componente JTable
     * 
     * */
    private void refreshJTable() {
	for (int i = 0; i < planoContas[rowSelected].length; i++) {
	    tbPlanoContas.setValueAt(planoContas[rowSelected][i], rowSelected,
		    i);
	}
    }

    /**
     * 
     * Retorna o item selecionado do ComboBox
     * 
     * */
    private int getItemSelected(String valorGrid, String[] array) {

	for (int i = 0; i < array.length; i++) {
	    if (array[i].equals(valorGrid)) {
		return i;
	    }
	}
	return -1;
    }

    /**
     * 
     * Valida prenchimento do formulario
     * 
     * */
    private String validateForm() {

	String validacao = "";

	if (txtConta.getText().replace(".", "").replace(" ", "").isEmpty()) {
	    validacao = validacao + "\nCampo [Conta] est� em branco";
	}
	if (txtDescricao.getText().isEmpty()) {
	    validacao = validacao + "\nCampo [Descri��o] est� em branco";
	}
	if (cbTipoConta.getSelectedIndex() == -1) {
	    validacao = validacao + "\n� obrigat�rio escolher um [Tipo conta]";
	}
	if (!rbAnalitica.isSelected() && !rbSintetica.isSelected()) {
	    validacao = validacao + "\n� obrigat�rio escolher um [N�vel]";
	}

	return validacao;
    }

    /**
     * 
     * Habilita formulario para Edi�ao e Adi��o
     * 
     * */
    private void actionForm(String opcao) {

	if (opcao.equalsIgnoreCase("N")) {
	    txtConta.setText("");
	    txtConta.setEditable(true);
	    txtDescricao.setText("");
	    txtDescricao.setEditable(true);
	    rbAnalitica.setEnabled(true);
	    rbAnalitica.setSelected(false);
	    rbSintetica.setEnabled(true);
	    rbSintetica.setSelected(false);
	    cbTipoConta.setEnabled(true);
	    cbTipoConta.setSelectedIndex(-1);
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
	    cbTipoConta.setEnabled(false);
	    cbTipoConta.setSelectedIndex(-1);
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
	    cbTipoConta.setEnabled(true);
	    btnDeletar.setEnabled(true);
	    btnCancelar.setEnabled(true);
	    btnSalvar.setEnabled(true);
	    btnNovo.setEnabled(true);
	    lbStatusMsg.setText("");
	}
    }
}