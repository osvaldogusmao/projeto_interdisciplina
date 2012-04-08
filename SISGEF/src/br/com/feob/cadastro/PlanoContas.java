package br.com.feob.cadastro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import br.com.feob.layout.SpringUtilities;

public class PlanoContas extends JFrame{

	/**
	 * Arrays
	 * 
	 * */
	private String[] column = {"Conta", "Descri��o", "Tipo", "A��o"};
	private String[][] planoContas = {
										{"01.01.01", "Caixa", "Anal�tica"},
										{"01.01.01", "Caixa", "Anal�tica"}
									};
	private String[] itensTipoConta = {"Ativo Circulante", 
										"Ativo N�o Circulante",
										"Passivo Circulante", 
										"Passivo N�o Circulante",
										"Patrim�nio L�quido",
										"DRE"};
	
	/**
	 * Componentes
	 * 
	 * */
	private JLabel lbConta = new JLabel("Conta", JLabel.RIGHT);
	private JLabel lbDescricao = new JLabel("Descri��o", JLabel.RIGHT);
	private JLabel lbTipoConta = new JLabel("Tipo conta", JLabel.RIGHT);
	private JLabel lbNivel = new JLabel("N�vel", JLabel.RIGHT);
	private JFormattedTextField txtConta = new JFormattedTextField();
	private JTextField txtDescricao = new JTextField(10);
	private JComboBox<String> cbTipoConta = new JComboBox<String>(itensTipoConta);
	private ButtonGroup bgTipo = new ButtonGroup();
	private JRadioButton rbAnalitica = new JRadioButton();
	private JRadioButton rbSintetica = new JRadioButton();
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnEditar = new JButton("Editar");
	private JButton btnCancelar = new JButton("Cancelar");
	
	private JTable tbPlanoContas = new JTable();
	private JScrollPane spPlanoContas = new JScrollPane(tbPlanoContas);
	
	private SpringLayout layoutForm = new SpringLayout();
	private JLayeredPane pnForm = new JLayeredPane();
	private JLayeredPane pnTable = new JLayeredPane();
	private JPanel pnRadios = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

	
	/**
	 * Constructor	
	 * 
	 * */
	public PlanoContas() {
		
		this.setTitle("Plano de contas");
		this.setSize(800, 600);
		this.setLayout(new GridLayout(1,2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		/**
		 * 
		 * Configura��es
		 * 
		 * 
		 * */
		btnSalvar.setIcon(new ImageIcon(getClass().getResource("/assets/images/save.png")));
		btnSalvar.setFocusable(false);
		btnEditar.setIcon(new ImageIcon(getClass().getResource("/assets/images/edit.png")));
		btnEditar.setFocusable(false);
		btnCancelar.setIcon(new ImageIcon(getClass().getResource("/assets/images/cancel.png")));
		btnCancelar.setFocusable(false);
		
		pnForm.setBorder(BorderFactory.createTitledBorder("Cadastro de Conta Cont�bil"));
		pnTable.setBorder(BorderFactory.createTitledBorder("Plano de Contas"));
		
		
		/**
		 * Table
		 * 
		 * */
		tbPlanoContas.setModel(new DefaultTableModel(
	            new Object [][] {},
	                new String [] {
	                    "Conta", "Descri��o", "Tipo Conta", "A��o"
	                }
	            ));
		pnTable.setSize(400,600);
		pnTable.setLayout(layoutForm);
		spPlanoContas.setSize(400, 600);
		tbPlanoContas.setShowGrid(true);
		tbPlanoContas.setShowVerticalLines(true);
		tbPlanoContas.setShowHorizontalLines(true);
		tbPlanoContas.setGridColor(new Color(204,204,204));
		tbPlanoContas.getColumnModel().getColumn(0).setMinWidth(100);
		tbPlanoContas.getColumnModel().getColumn(0).setMaxWidth(100);
		tbPlanoContas.getColumnModel().getColumn(1).setMinWidth(140);
		tbPlanoContas.getColumnModel().getColumn(1).setMaxWidth(140);
		tbPlanoContas.getColumnModel().getColumn(2).setMinWidth(100);
		tbPlanoContas.getColumnModel().getColumn(2).setMaxWidth(100);
		tbPlanoContas.getColumnModel().getColumn(3).setMinWidth(100);
		tbPlanoContas.getColumnModel().getColumn(3).setMaxWidth(100);
		
		pnTable.add(spPlanoContas);
		
		/**
		 * Formul�rio
		 * 
		 * */
		pnForm.setSize(300,200);
		pnForm.setLayout(layoutForm);
		
		pnForm.add(lbConta);
		
		try {
            txtConta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.##.##")));
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
		pnButtons.add(btnSalvar);
		pnButtons.add(btnEditar);
		pnButtons.add(btnCancelar);
		pnForm.add(new JLabel(""));
		pnForm.add(pnButtons);
	
		SpringUtilities.makeCompactGrid(pnForm,5,2,10,10,10,10);

		this.add(pnTable);
		this.add(pnForm);
		this.setVisible(true);
	}
}