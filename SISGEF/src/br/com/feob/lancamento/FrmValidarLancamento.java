package br.com.feob.lancamento;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataListener;

import br.com.feob.lancamento.repository.LancamentoRepository;
import br.com.feob.singleton.DadosSistema;
import br.com.feob.util.GrupoConta;

public class FrmValidarLancamento extends JFrame implements ActionListener{

    /**
     * 
     * Components
     * 
     * */
    private JLabel lbAno;
    private JLabel lbAtivo;
    private JLabel lbTotalAtivo;
    private JLabel lbPassivo;
    private JLabel lbTotalPassivo;
    private JLabel lbPL;
    private JLabel lbTotalPl;
    private JLabel lbResultado;
    private JLabel lbRespostaValidador;
    private JComboBox<Integer> cbAnos;
    private JLabel lbDiferenca;
    private JLabel lbTotalDiferenca;
    
    private JButton btnValidar;
    private LancamentoRepository lancamentoRepository = new LancamentoRepository();
    
    public FrmValidarLancamento(){
	
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	super.setTitle("Validação de saldos");
	super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	super.getContentPane().setLayout(new GridBagLayout());
	super.setResizable(false);
	super.setSize(280, 200);
	this.setLocation((int) (dimension.getWidth() - this.getWidth()) / 2, (int) (dimension.getHeight() - this.getHeight()) / 2);
	
	GridBagConstraints gridBagConstraints;
	
	lbAno = new JLabel();
	cbAnos = new JComboBox<Integer>();
	lbAtivo = new JLabel();
	lbPassivo = new JLabel();
	lbPL = new JLabel();
	lbTotalAtivo = new JLabel();
	lbTotalPassivo = new JLabel();
	lbTotalPl = new JLabel();
	lbResultado = new JLabel();
	lbRespostaValidador = new JLabel();
	lbDiferenca = new JLabel();
	lbTotalDiferenca = new JLabel();
	btnValidar = new JButton();
	
	List<Integer> anos = DadosSistema.getIstance().retornaAnosLancamento();
	
	for (Integer ano : anos) {
	   cbAnos.addItem(ano);
	}
	
	JLayeredPane panelComponents = new JLayeredPane();
	panelComponents.setBorder(BorderFactory.createTitledBorder("Validação de saldos"));
		
	lbAno.setText("Escolha um ano");
	lbAno.setBounds(10, 20, 150, 15);
	panelComponents.add(lbAno, JLayeredPane.DEFAULT_LAYER);
	
	cbAnos.setBounds(160, 15, 80, cbAnos.getPreferredSize().height);
	panelComponents.add(cbAnos, JLayeredPane.DEFAULT_LAYER);
	
	lbAtivo.setText("Ativo");
	lbAtivo.setBounds(10, 40, 80, lbAtivo.getPreferredSize().height);
	panelComponents.add(lbAtivo, JLayeredPane.DEFAULT_LAYER);
	
	lbTotalAtivo.setText(" ");
	lbTotalAtivo.setFont(lbTotalAtivo.getFont().deriveFont(Font.BOLD));
	lbTotalAtivo.setForeground(Color.BLUE);
	lbTotalAtivo.setMaximumSize(new Dimension(lbTotalAtivo.getPreferredSize().height, 120));
	lbTotalAtivo.setHorizontalAlignment(JLabel.RIGHT);
	lbTotalAtivo.setBounds(90, 40, 120, lbTotalAtivo.getPreferredSize().height);
	panelComponents.add(lbTotalAtivo, JLayeredPane.DEFAULT_LAYER);
	
	lbPassivo.setText("Passivo");
	lbPassivo.setBounds(10, 60, 80, lbPassivo.getPreferredSize().height);
	panelComponents.add(lbPassivo, JLayeredPane.DEFAULT_LAYER);
	
	lbTotalPassivo.setText(" ");
	lbTotalPassivo.setFont(lbTotalPassivo.getFont().deriveFont(Font.BOLD));
	lbTotalPassivo.setForeground(new Color(146,6,17));
	lbTotalPassivo.setMaximumSize(new Dimension(lbTotalPassivo.getPreferredSize().height, 120));
	lbTotalPassivo.setHorizontalAlignment(JLabel.RIGHT);
	lbTotalPassivo.setBounds(90, 60, 120, lbTotalPassivo.getPreferredSize().height);
	panelComponents.add(lbTotalPassivo, JLayeredPane.DEFAULT_LAYER);
	
	lbPL.setText("PL");
	lbPL.setBounds(10, 80, 80, lbPL.getPreferredSize().height);
	panelComponents.add(lbPL, JLayeredPane.DEFAULT_LAYER);
	
	lbTotalPl.setText(" ");
	lbTotalPl.setFont(lbTotalPl.getFont().deriveFont(Font.BOLD));
	lbTotalPl.setForeground(new Color(146,6,17));
	lbTotalPl.setMaximumSize(new Dimension(lbTotalPl.getPreferredSize().height, 120));
	lbTotalPl.setHorizontalAlignment(JLabel.RIGHT);
	lbTotalPl.setBounds(90, 80, 120, lbTotalPl.getPreferredSize().height);
	panelComponents.add(lbTotalPl, JLayeredPane.DEFAULT_LAYER);
	
	lbDiferenca.setText("À Corrigir");
	lbDiferenca.setBounds(10, 100, 80, lbDiferenca.getPreferredSize().height);
	panelComponents.add(lbDiferenca, JLayeredPane.DEFAULT_LAYER);

	lbTotalDiferenca.setText(" ");
	lbTotalDiferenca.setFont(lbTotalDiferenca.getFont().deriveFont(Font.BOLD));
	lbTotalDiferenca.setForeground(new Color(146,6,17));
	lbTotalDiferenca.setMaximumSize(new Dimension(lbTotalDiferenca.getPreferredSize().height, 120));
	lbTotalDiferenca.setHorizontalAlignment(JLabel.RIGHT);
	lbTotalDiferenca.setBounds(90, 100, 120, lbTotalDiferenca.getPreferredSize().height);
	panelComponents.add(lbTotalDiferenca, JLayeredPane.DEFAULT_LAYER);

	
	lbResultado.setText("Resultado");
	lbResultado.setBounds(10, 130, 80, lbResultado.getPreferredSize().height);
	panelComponents.add(lbResultado, JLayeredPane.DEFAULT_LAYER);

	lbRespostaValidador.setText(" ");
	lbRespostaValidador.setFont(lbRespostaValidador.getFont().deriveFont(Font.BOLD));
	lbRespostaValidador.setBounds(90, 130, 180, lbRespostaValidador.getPreferredSize().height);
	panelComponents.add(lbRespostaValidador, JLayeredPane.DEFAULT_LAYER);
	
	btnValidar.setText("Validar");
	btnValidar.setBounds(this.getWidth()-btnValidar.getPreferredSize().width, 160, btnValidar.getPreferredSize().width, btnValidar.getPreferredSize().height);
	panelComponents.add(btnValidar, JLayeredPane.RIGHT_ALIGNMENT);
	btnValidar.addActionListener(this);
	
	gridBagConstraints = new GridBagConstraints();
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 0;
	gridBagConstraints.ipadx = 280;
	gridBagConstraints.ipady = 200;
	gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
	
	getContentPane().add(panelComponents, gridBagConstraints);
	
	pack();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

	if(e.getSource() == btnValidar){
	    
	    NumberFormat format = NumberFormat.getCurrencyInstance();
	    int anoSelecionado = (Integer)cbAnos.getSelectedItem();
	    
	    Double totalAtivo = lancamentoRepository.getTotalConta((Integer)cbAnos.getSelectedItem(), GrupoConta.ATIVO);
	    Double totalPassivo = lancamentoRepository.getTotalConta((Integer)cbAnos.getSelectedItem(), GrupoConta.PASSIVO);
	    Double totalPL = lancamentoRepository.getTotalConta((Integer)cbAnos.getSelectedItem(), GrupoConta.PL);
	    
	    lbTotalAtivo.setText("(+) "+format.format(totalAtivo));
	    lbTotalPassivo.setText("(-) "+format.format(totalPassivo));
	    lbTotalPl.setText("(-) "+format.format(totalPL));
	    lbTotalDiferenca.setText("(#) "+ format.format((totalAtivo - (totalPassivo+totalPL))));
	    lbTotalDiferenca.setHorizontalTextPosition(JLabel.RIGHT);
	    
	    if(totalAtivo == (totalPassivo+totalPL)){
		lbRespostaValidador.setText("Validado");
		lbRespostaValidador.setForeground(new Color(0,127,63));
		DadosSistema.getIstance().addValidadorLancamento(anoSelecionado, true);
	    }else{
		lbRespostaValidador.setText("Corrigir valores");
		lbRespostaValidador.setForeground(new Color(146,6,17));
		DadosSistema.getIstance().addValidadorLancamento(anoSelecionado, false);
	    }
	    
	    System.out.println(DadosSistema.getIstance().getLancamentosValidados());
	    
	}
	
    }
}
