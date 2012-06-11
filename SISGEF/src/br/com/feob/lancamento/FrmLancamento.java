package br.com.feob.lancamento;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.TableRowSorter;

import br.com.feob.lancamento.repository.LancamentoRepository;
import br.com.feob.planoContas.FrmConsultaPlanoContas;
import br.com.feob.planoContas.PlanoConta;
import br.com.feob.singleton.DadosSistema;
import br.com.feob.util.DecimalRenderer;

public class FrmLancamento extends JFrame implements ActionListener, WindowListener, RowSorterListener, MouseListener {

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
    private JButton btnValidadarLancamentos;
    private JLayeredPane lpLancamentos;
    private JScrollPane scrollPane;
    private JTable tbLancamentos;
    private FrmConsultaPlanoContas consultaPlanoContas;
    private PlanoConta planoConta;

    private List<Lancamento> lancamentos = DadosSistema.getIstance().getLancamentos();
    private LancamentoTableModel lancamentoTableModel = new LancamentoTableModel(lancamentos);
    private LancamentoRepository repository = new LancamentoRepository();

    private JLabel lbTotalColuna;
    private JLabel lbResultadoTotalColuna;
    private JLabel lbFiltros;
    private JLabel lbFiltroTipoConta;
    private JComboBox<String> cbAnosLancamentos;
    private JTextField txtFiltroContaContabil;
    private JButton btnFiltro;
    private JButton btnFiltroLimpar;
    private TableRowSorter<LancamentoTableModel> sorter;
    private NumberFormat format;
    private int rowSelected = -1;

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
	btnValidadarLancamentos = new JButton();
	lpLancamentos = new JLayeredPane();
	scrollPane = new JScrollPane();
	tbLancamentos = new JTable();

	lbResultadoTotalColuna = new JLabel();
	lbTotalColuna = new JLabel();
	lbFiltros = new JLabel();
	lbFiltroTipoConta = new JLabel();
	cbAnosLancamentos = new JComboBox<String>();
	txtFiltroContaContabil = new JTextField();
	btnFiltro = new JButton();
	btnFiltroLimpar = new JButton();
	format = DecimalFormat.getCurrencyInstance();

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

	lbFiltros.setText("Filtros");
	lbFiltros.setBounds(10, 20, 50, 15);
	lpLancamentos.add(lbFiltros, JLayeredPane.DEFAULT_LAYER);

	List<Integer> anos = DadosSistema.getIstance().retornaAnosLancamento();

	cbAnosLancamentos.addItem("Todos");
	for (Integer ano : anos) {
	    cbAnosLancamentos.addItem(ano.toString());
	}

	cbAnosLancamentos.setBounds(60, 15, 100, 25);
	cbAnosLancamentos.setFocusable(false);
	lpLancamentos.add(cbAnosLancamentos, JLayeredPane.DEFAULT_LAYER);

	lbFiltroTipoConta.setText("Conta contábil");
	lbFiltroTipoConta.setBounds(160, 20, 100, 15);
	lpLancamentos.add(lbFiltroTipoConta, JLayeredPane.DEFAULT_LAYER);

	txtFiltroContaContabil.setBounds(260, 13, 100, txtFiltroContaContabil.getPreferredSize().height);
	lpLancamentos.add(txtFiltroContaContabil, JLayeredPane.DEFAULT_LAYER);

	btnFiltro.setText("Filtrar");
	btnFiltro.setBounds(360, 13, 100, btnFiltro.getPreferredSize().height);
	btnFiltro.setFocusable(false);
	btnFiltro.addActionListener(this);
	lpLancamentos.add(btnFiltro, JLayeredPane.DEFAULT_LAYER);

	btnFiltroLimpar.setText("Limpar");
	btnFiltroLimpar.setBounds(460, 13, 100, btnFiltro.getPreferredSize().height);
	btnFiltroLimpar.setFocusable(false);
	btnFiltroLimpar.addActionListener(this);
	lpLancamentos.add(btnFiltroLimpar, JLayeredPane.DEFAULT_LAYER);

	lpLancamentos.setBorder(javax.swing.BorderFactory.createTitledBorder("Lançamentos"));

	DecimalFormat formatter = new DecimalFormat("###,##0.00");

	tbLancamentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tbLancamentos.setModel(lancamentoTableModel);
	tbLancamentos.addMouseListener(this);
	tbLancamentos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	tbLancamentos.getColumnModel().getColumn(0).setPreferredWidth(80);
	tbLancamentos.getColumnModel().getColumn(1).setPreferredWidth(90);
	tbLancamentos.getColumnModel().getColumn(2).setPreferredWidth(350);
	tbLancamentos.getColumnModel().getColumn(3).setPreferredWidth(80);
	tbLancamentos.getColumnModel().getColumn(4).setPreferredWidth(80);
	tbLancamentos.getColumnModel().getColumn(4).setCellRenderer(new DecimalRenderer(formatter));

	sorter = new TableRowSorter<LancamentoTableModel>(lancamentoTableModel);
	sorter.setRowFilter(null);
	sorter.addRowSorterListener(this);
	tbLancamentos.setRowSorter(sorter);

	scrollPane.setViewportView(tbLancamentos);

	scrollPane.setBounds(10, 40, 700, 280);
	lpLancamentos.add(scrollPane, JLayeredPane.DEFAULT_LAYER);

	lbTotalColuna.setText("Total");
	lbTotalColuna.setBounds(540, 330, 50, 15);
	lbTotalColuna.setMaximumSize(new Dimension(50, 15));
	lbTotalColuna.setHorizontalAlignment(JLabel.RIGHT);
	lpLancamentos.add(lbTotalColuna, JLayeredPane.DEFAULT_LAYER);

	lbResultadoTotalColuna.setText(format.format(lancamentoTableModel.getTotalColuna(4)));
	lbResultadoTotalColuna.setBounds(590, 330, 100, 15);
	lbResultadoTotalColuna.setForeground(Color.blue);
	lbResultadoTotalColuna.setMaximumSize(new Dimension(50, 15));
	lbResultadoTotalColuna.setHorizontalAlignment(JLabel.RIGHT);
	lpLancamentos.add(lbResultadoTotalColuna, JLayeredPane.DEFAULT_LAYER);

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
	btnNovo.setBounds(200, 100, 100, 29);
	lpDadosLancamento.add(btnNovo, JLayeredPane.DEFAULT_LAYER);
	btnNovo.addActionListener(this);

	btnSalvar.setText("Salvar");
	btnSalvar.setBounds(300, 100, 100, 29);
	lpDadosLancamento.add(btnSalvar, JLayeredPane.DEFAULT_LAYER);
	btnSalvar.addActionListener(this);

	btnCancelar.setText("Cancelar");
	btnCancelar.setBounds(400, 100, 100, 29);
	lpDadosLancamento.add(btnCancelar, JLayeredPane.DEFAULT_LAYER);
	btnCancelar.addActionListener(this);

	btnDeletar.setText("Deletar");
	btnDeletar.setBounds(500, 100, 100, 29);
	lpDadosLancamento.add(btnDeletar, JLayeredPane.DEFAULT_LAYER);
	btnDeletar.addActionListener(this);

	btnValidadarLancamentos.setText("Validação");
	btnValidadarLancamentos.setBounds(600, 100, 100, 29);
	lpDadosLancamento.add(btnValidadarLancamentos, JLayeredPane.DEFAULT_LAYER);
	btnValidadarLancamentos.addActionListener(this);

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

	if (e.getSource() == this.btnBuscarConta) {
	    consultaPlanoContas = new FrmConsultaPlanoContas();
	    consultaPlanoContas.setVisible(true);
	    consultaPlanoContas.addWindowListener(this);
	}

	if (e.getSource() == this.btnNovo) {
	    btnCancelar.setEnabled(true);
	    btnDeletar.setEnabled(true);
	    btnSalvar.setEnabled(true);
	    txtCodigoLancamento.setText(DadosSistema.getIstance().getCodigoLancamento() + 1 + "");
	}

	if (e.getSource() == this.btnSalvar) {

	    if (rowSelected >= 0) {

		Lancamento lancamento = lancamentos.get(rowSelected);
		lancamento.setCodigoLancamento(Long.parseLong(txtCodigoLancamento.getText()));
		lancamento.setConta(txtNumeroConta.getText());
		lancamento.setAno(Integer.parseInt(txtAnoLancamento.getText()));
		lancamento.setSaldo(Double.parseDouble(txtSaldoLancamento.getText()));
		lancamentoTableModel.setData(lancamentos);
		this.clearForm();
		repository.gravarLancamentoTXT(lancamentos);

	    } else {
		if (lancamentoTableModel.verificaItemExistente(txtNumeroConta.getText(), Integer.parseInt(txtAnoLancamento.getText())) == false) {
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
		} else {
		    JOptionPane.showMessageDialog(null, "Já existe lançamento com estes dados", "Aviso", JOptionPane.WARNING_MESSAGE);
		}
	    }
	}

	if (e.getSource() == this.btnCancelar) {
	    clearForm();
	}

	if (e.getSource() == this.btnDeletar) {

	    int rowDelete = tbLancamentos.getSelectedRow();

	    int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar este registro?", "Atenção", JOptionPane.WARNING_MESSAGE);

	    if (opcao == JOptionPane.YES_OPTION) {
		lancamentoTableModel.deleteItem(rowDelete);
	    }
	}

	if (e.getSource() == this.btnFiltro) {

	    String ano = cbAnosLancamentos.getSelectedItem().toString();
	    String conta = txtFiltroContaContabil.getText();

	    List filters = new ArrayList();

	    if (ano.equals("Todos") && conta.isEmpty()) {
		sorter.setRowFilter(null);
		lbResultadoTotalColuna.setText(format.format(lancamentoTableModel.getTotalColuna(4)));
	    }

	    if (ano.equals("Todos") == false) {
		filters.add(RowFilter.regexFilter(ano));
	    }

	    if (conta.isEmpty() == false) {
		filters.add(RowFilter.regexFilter("^" + conta));
	    }
	    sorter.setRowFilter(RowFilter.andFilter(filters));
	}

	if (e.getSource() == this.btnFiltroLimpar) {

	    sorter.setRowFilter(null);
	    cbAnosLancamentos.setSelectedIndex(0);
	    txtFiltroContaContabil.setText(null);
	    lbResultadoTotalColuna.setText(format.format(lancamentoTableModel.getTotalColuna(4)));
	}

	if (e.getSource() == this.btnValidadarLancamentos) {
	    new FrmValidarLancamento().setVisible(true);
	}
    }

    /**
     * 
     * Metodos Auxiliares
     * 
     * */
    private void clearForm() {

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

	if (e.getSource() == this) {
	    repository.gravarLancamentoTXT(lancamentos);
	}

    }

    @Override
    public void windowClosed(WindowEvent e) {
	if (e.getSource() == this.consultaPlanoContas) {
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

    @Override
    public void sorterChanged(RowSorterEvent e) {

	if (e.getType() == javax.swing.event.RowSorterEvent.Type.SORTED) {

	    int total = e.getSource().getViewRowCount();

	    Integer[] indices = new Integer[total];

	    for (int i = 0; i < total; i++) {
		indices[i] = e.getSource().convertRowIndexToModel(i);
	    }
	    recalculaTotalColuna(indices);
	}
    }

    private void recalculaTotalColuna(Integer[] indices) {

	Double total = 0.00;

	for (int i = 0; i < indices.length; i++) {
	    total = total + lancamentos.get(indices[i]).getSaldo();
	}
	lbResultadoTotalColuna.setText(format.format(total));
    }

    private void setItemSelected(int indice) {

	Lancamento lancamento = lancamentos.get(indice);

	txtCodigoLancamento.setText(lancamento.getCodigoLancamento() + "");
	txtNumeroConta.setText(lancamento.getConta());
	txtNomeConta.setText(lancamento.getDescricaoConta());
	txtAnoLancamento.setText(lancamento.getAno() + "");
	txtSaldoLancamento.setText(lancamento.getSaldo() + "");
	btnCancelar.setEnabled(true);
	btnDeletar.setEnabled(true);
	btnSalvar.setEnabled(true);
	btnNovo.setEnabled(true);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

	rowSelected = tbLancamentos.convertRowIndexToModel(tbLancamentos.getSelectedRow());
	if (rowSelected != -1) {
	    setItemSelected(rowSelected);
	}

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
