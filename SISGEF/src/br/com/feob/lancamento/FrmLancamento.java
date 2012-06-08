package br.com.feob.lancamento;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.com.feob.lancamento.repository.LancamentoRepository;
import br.com.feob.planoContas.FrmConsultaPlanoContas;
import br.com.feob.planoContas.PlanoConta;
import br.com.feob.singleton.DadosSistema;

public class FrmLancamento extends JFrame implements ActionListener, WindowListener{

    private JLayeredPane lpDadosLancamento;
    private JLabel lbLancamento;
    private JTextField txtCodigoLancamento;
    private JLabel lbConta;
    private JTextField txtNomeConta;
    private JButton btnBuscarConta;
    private JTextField txtSaldoLancamento;
    private JLabel lbAno;
    private JLabel lbSaldo;
    private JFormattedTextField txtNumeroConta;
    private JFormattedTextField txtAnoLancamento;
    private JButton btnCancelar;
    private JButton btnNovo;
    private JButton btnSalvar;
    private JButton btnDeletar;
    private JLayeredPane lpLancamentos;
    private JScrollPane scrollPane;
    private JTable tbLancamentos;
    private FrmConsultaPlanoContas consultaPlanoContas;
    private PlanoConta planoConta;
    
    private List<Lancamento> lancamentos = DadosSistema.getIstance().getLancamentos();
    private LancamentoTableModel lancamentoTableModel = new LancamentoTableModel(lancamentos);
    private LancamentoRepository repository = new LancamentoRepository();
    
    
    /**
     * 
     * Constructor
     * 
     * */
    public FrmLancamento() {
	this.addWindowListener(this);
	
	GridBagConstraints gridBagConstraints;

	lpDadosLancamento = new JLayeredPane();
	lbLancamento = new JLabel();
	txtCodigoLancamento = new JTextField();
	lbConta = new JLabel();
	txtNomeConta = new JTextField();
	btnBuscarConta = new JButton();
	txtSaldoLancamento = new JTextField();
	lbAno = new JLabel();
	lbSaldo = new JLabel();
	txtNumeroConta = new JFormattedTextField();
	txtAnoLancamento = new JFormattedTextField();
	btnCancelar = new JButton();
	btnNovo = new JButton();
	btnSalvar = new JButton();
	btnDeletar = new JButton();
	lpLancamentos = new JLayeredPane();
	scrollPane = new JScrollPane();
	tbLancamentos = new JTable();

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();

	super.setTitle("Lançamentos de saldos");
	super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	super.getContentPane().setLayout(new GridBagLayout());
	super.setResizable(false);
	super.setSize(720, 550);
	this.setLocation((int) (dimension.getWidth() - this.getWidth()) / 2, (int) (dimension.getHeight() - this.getHeight()) / 2);
	
	btnCancelar.setEnabled(false);
	btnDeletar.setEnabled(false);
	btnNovo.setEnabled(true);
	btnSalvar.setEnabled(false);

	lpLancamentos.setBorder(javax.swing.BorderFactory.createTitledBorder("Lançamentos"));
	
	tbLancamentos.setModel(lancamentoTableModel);
	
	scrollPane.setViewportView(tbLancamentos);

	scrollPane.setBounds(10, 20, 700, 320);
	lpLancamentos.add(scrollPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

	gridBagConstraints = new java.awt.GridBagConstraints();
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 0;
	gridBagConstraints.ipadx = 720;
	gridBagConstraints.ipady = 350;
	gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
	gridBagConstraints.insets = new Insets(10, 10, 0, 10);
	getContentPane().add(lpLancamentos, gridBagConstraints);
	
	lpDadosLancamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do lançamento"));

	lbLancamento.setText("Lançamento");
	lbLancamento.setBounds(20, 30, 80, 16);
	lpDadosLancamento.add(lbLancamento, JLayeredPane.DEFAULT_LAYER);

	txtCodigoLancamento.setEditable(false);
	txtCodigoLancamento.setBounds(100, 20, 110, 28);
	lpDadosLancamento.add(txtCodigoLancamento, JLayeredPane.DEFAULT_LAYER);

	lbConta.setText("Conta");
	lbConta.setBounds(220, 30, 40, 16);
	lpDadosLancamento.add(lbConta, JLayeredPane.DEFAULT_LAYER);
	
	btnBuscarConta.setText("...");
	btnBuscarConta.setBounds(352, 20, 40, 25);
	lpDadosLancamento.add(btnBuscarConta, JLayeredPane.DEFAULT_LAYER);
	btnBuscarConta.addActionListener(this);
	
	txtNomeConta.setBounds(395, 20, 320, 28);
	txtNomeConta.setEditable(false);
	lpDadosLancamento.add(txtNomeConta, JLayeredPane.DEFAULT_LAYER);

	txtSaldoLancamento.setBounds(260, 50, 90, 28);
	lpDadosLancamento.add(txtSaldoLancamento, JLayeredPane.DEFAULT_LAYER);

	lbSaldo.setText("Saldo");
	lbSaldo.setBounds(220, 60, 40, 16);
	lpDadosLancamento.add(lbSaldo, JLayeredPane.DEFAULT_LAYER);

	lbAno.setText("Ano");
	lbAno.setBounds(70, 60, 25, 16);
	lpDadosLancamento.add(lbAno, JLayeredPane.DEFAULT_LAYER);

	try {
	    txtNumeroConta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.##.##.##")));
	} catch (java.text.ParseException ex) {
	    ex.printStackTrace();
	}
	txtNumeroConta.setBounds(260, 20, 90, 28);
	lpDadosLancamento.add(txtNumeroConta, javax.swing.JLayeredPane.DEFAULT_LAYER);

	try {
	    txtAnoLancamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
	} catch (java.text.ParseException ex) {
	    ex.printStackTrace();
	}
	txtAnoLancamento.setBounds(100, 50, 110, 28);
	lpDadosLancamento.add(txtAnoLancamento, JLayeredPane.DEFAULT_LAYER);

	btnNovo.setText("Novo");
	btnNovo.setBounds(300, 100, 100, 29);
	lpDadosLancamento.add(btnNovo, JLayeredPane.DEFAULT_LAYER);
	btnNovo.addActionListener(this);
	
	btnSalvar.setText("Salvar");
	btnSalvar.setBounds(400, 100, 100, 29);
	lpDadosLancamento.add(btnSalvar, JLayeredPane.DEFAULT_LAYER);
	btnSalvar.addActionListener(this);
	
	btnCancelar.setText("Cancelar");
	btnCancelar.setBounds(500, 100, 100, 29);
	lpDadosLancamento.add(btnCancelar, JLayeredPane.DEFAULT_LAYER);
	btnCancelar.addActionListener(this);

	btnDeletar.setText("Deletar");
	btnDeletar.setBounds(600, 100, 100, 29);
	lpDadosLancamento.add(btnDeletar, JLayeredPane.DEFAULT_LAYER);
	btnDeletar.addActionListener(this);
	
	
	gridBagConstraints = new GridBagConstraints();
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 1;
	gridBagConstraints.ipadx = 720;
	gridBagConstraints.ipady = 140;
	gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
	gridBagConstraints.insets = new Insets(10, 10, 10, 10);
	getContentPane().add(lpDadosLancamento, gridBagConstraints);

	pack();
    }

    /**
     * 
     * Eventos
     * 
     * */

    @Override
    public void actionPerformed(ActionEvent e) {
	
	if(e.getSource() == this.btnBuscarConta){
	    consultaPlanoContas  = new FrmConsultaPlanoContas();
	    consultaPlanoContas.setVisible(true);
	    consultaPlanoContas.addWindowListener(this);
	}
	
	if(e.getSource() == this.btnNovo){
	    btnCancelar.setEnabled(true);
	    btnDeletar.setEnabled(true);
	    btnSalvar.setEnabled(true);
	    txtCodigoLancamento.setText(DadosSistema.getIstance().getCodigoLancamento()+1+"");
	}
	
	if(e.getSource() == this.btnSalvar){

	    if(lancamentoTableModel.verificaItemExistente(txtNumeroConta.getText(), Integer.parseInt(txtAnoLancamento.getText())) == false){
		Lancamento lancamento = new Lancamento();
		lancamento.setCodigoLancamento(Long.parseLong(txtCodigoLancamento.getText()));
		lancamento.setConta(txtNumeroConta.getText());
		lancamento.setAno(Integer.parseInt(txtAnoLancamento.getText()));
		lancamento.setSaldo(Double.parseDouble(txtSaldoLancamento.getText()));
		lancamentos.add(lancamento);
		lancamentoTableModel.setData(lancamentos);
		DadosSistema.getIstance().setCodigoLancamento();
		this.clearForm();
		repository.gravarLancamentoTXT(lancamentos);
	    }else{
		JOptionPane.showMessageDialog(null, "Já existe lançamento com estes dados", "Aviso", JOptionPane.WARNING_MESSAGE);
	    }
	}
	
	if(e.getSource() == this.btnCancelar){
	    clearForm();
	}
	
	if(e.getSource() == this.btnDeletar){
	 
	    int rowSelected  = tbLancamentos.getSelectedRow();
	    
	    int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar este registro?", "Atenção", JOptionPane.WARNING_MESSAGE);
	    
	    if(opcao == JOptionPane.YES_OPTION){
		lancamentoTableModel.deleteItem(rowSelected);
	    }
	    
	}
    }
    
    /**
     * 
     * Metodos Auxiliares
     * 
     * */
    private void clearForm(){
	
	this.txtAnoLancamento.setText("");
	this.txtCodigoLancamento.setText("");
	this.txtNomeConta.setText("");
	this.txtNumeroConta.setText("");
	this.txtSaldoLancamento.setText("");
	btnCancelar.setEnabled(false);
	btnDeletar.setEnabled(false);
	btnSalvar.setEnabled(false);
	btnNovo.setEnabled(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
	
    }

    @Override
    public void windowClosing(WindowEvent e) {
	
	if(e.getSource() == this){
	    repository.gravarLancamentoTXT(lancamentos);
	}
	
    }

    @Override
    public void windowClosed(WindowEvent e) {
	if(e.getSource() == this.consultaPlanoContas){
	    planoConta = consultaPlanoContas.getPlanoConta();
	    txtNumeroConta.setText(planoConta.getConta());
	    txtNomeConta.setText(planoConta.getDescricao());
	}
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
	
    }

    @Override
    public void windowActivated(WindowEvent e) {
	
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
	
    }
}
