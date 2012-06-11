package br.com.feob.principal;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.com.feob.configuracao.FrmConfiguracao;
import br.com.feob.importacao.FrmImportacao;
import br.com.feob.indices.FrmGraficos;
import br.com.feob.indices.FrmIndices;
import br.com.feob.indices.Indice;
import br.com.feob.indices.repository.IndiceRepository;
import br.com.feob.lancamento.FrmLancamento;
import br.com.feob.lancamento.FrmValidarLancamento;
import br.com.feob.planoContas.FrmPlanoContas;
import br.com.feob.singleton.DadosSistema;
import br.com.feob.util.ImagePanel;

public class FrmMenu extends JFrame implements ActionListener {

    private UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
    private JMenu mCadastro = new JMenu("Cadastro");
    private JMenuItem miPlanoContas = new JMenuItem("Plano de Contas");
    private JMenu mLancamento = new JMenu("Lançamentos");
    private JMenuItem miLancamento = new JMenuItem("Saldos das contas");
    private JMenuItem miValidacaoSaldos = new JMenuItem("Validação de Saldos");
    private JMenu mImportacao = new JMenu("Importação");
    private JMenuItem miImportacaoPlanoContas = new JMenuItem("Importação de arquivo");
    private JMenu mConfiguracao = new JMenu("Configurações");
    private JMenuItem miConfiguracaoSistema = new JMenuItem("Configuração Sistema");
    private JMenu mIndices = new JMenu("Indicadores");
    private JMenuItem miVisualizacaoIndices = new JMenuItem("Indices");
    private JMenuItem miGraficos = new JMenuItem("Gráficos");
    private JMenu mSistema = new JMenu("Sistema");
    private JMenuItem miLayout = new JMenuItem("Layout");
    private JMenuItem miSair = new JMenuItem("Sair");

    public FrmMenu() {

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();

	this.setSize(1024, 768);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Finance - Sistema de gestão financeiro");

	ImagePanel background = new ImagePanel(new ImageIcon(getClass().getResource("/assets/images/bg_finance.jpg")).getImage());
	super.setContentPane(background);
	super.setResizable(false);

	mCadastro.add(miPlanoContas);
	miPlanoContas.addActionListener(this);

	mLancamento.add(miLancamento);
	mLancamento.add(miValidacaoSaldos);
	miLancamento.addActionListener(this);
	miValidacaoSaldos.addActionListener(this);

	mImportacao.add(miImportacaoPlanoContas);
	miImportacaoPlanoContas.addActionListener(this);

	mConfiguracao.add(miConfiguracaoSistema);
	miConfiguracaoSistema.addActionListener(this);

	mIndices.add(miVisualizacaoIndices);
	miVisualizacaoIndices.addActionListener(this);
	
	mIndices.add(miGraficos);
	miGraficos.addActionListener(this);

	// mSistema.add(miLayout);
	mSistema.add(miSair);
	miSair.addActionListener(this);
	miLayout.addActionListener(this);

	JMenuBar menuBar = new JMenuBar();
	menuBar.add(mCadastro);
	menuBar.add(mLancamento);
	menuBar.add(mImportacao);
	menuBar.add(mConfiguracao);
	menuBar.add(mIndices);
	menuBar.add(mSistema);

	try {
	    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    // UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	this.setJMenuBar(menuBar);
	this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

	if (e.getSource() == this.miSair) {
	    int opcao = JOptionPane.showConfirmDialog(getParent(), "Deseja realmente sair?", "Aten‹o", JOptionPane.YES_NO_OPTION,
		    JOptionPane.WARNING_MESSAGE);
	    if (opcao == 0) {
		System.exit(0);
	    }

	}
	if (e.getSource() == this.miPlanoContas) {
	    new FrmPlanoContas();
	}
	if (e.getSource() == this.miConfiguracaoSistema) {
	    new FrmConfiguracao().setVisible(true);

	}

	if (e.getSource() == this.miLancamento) {
	    if (DadosSistema.getIstance().getPlanoContas().size() > 0) {
		new FrmLancamento().setVisible(true);
	    } else {
		JOptionPane.showMessageDialog(null, "O sistema não tem plano de contas cadastrado!\n"
			+ "1ª Opção: Menu: Importação -> Importação de Plano de Contas\n" + "2ª Opção: Menu: Cadastro -> Plano de contas", "Atenção",
			JOptionPane.INFORMATION_MESSAGE);
	    }

	}

	if (e.getSource() == this.miImportacaoPlanoContas) {
	    new FrmImportacao();
	}

	if (e.getSource() == miValidacaoSaldos) {

	    if (DadosSistema.getIstance().getLancamentos().size() > 0) {
		new FrmValidarLancamento().setVisible(true);
	    } else {
		JOptionPane.showMessageDialog(null, "O sistema não tem lançamentos efetuados!\n"
			+ "1ª Opção: Menu: Importação -> Importação de lançamentos\n" + "2ª Opção: Menu: Lançamentos -> Saldos das contas",
			"Atenção", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

	if (e.getSource() == this.miVisualizacaoIndices) {

	    DadosSistema dadosSistema = DadosSistema.getIstance();

	    if (dadosSistema.getLancamentos().size() > 0) {
		if (dadosSistema.retornaAnosValidados().size() > 0) {
		    FrmIndices indices = new FrmIndices();
		    indices.setLocationRelativeTo(this);
		    indices.setVisible(true);
		} else {
		    JOptionPane.showMessageDialog(null, "Os lançamentos não foram validado.\n" + "Menu : Lançamentos -> Validação de saldos",
			    "Atenção", JOptionPane.INFORMATION_MESSAGE);
		}
	    } else {
		JOptionPane.showMessageDialog(null, "O sistema não tem lançamentos efetuados!\n"
			+ "1ª Opção: Menu: Importação -> Importação de lançamentos\n" + "2ª Opção: Menu: Lançamentos -> Saldos das contas",
			"Atenção", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	if (e.getSource() == this.miGraficos) {

	    DadosSistema dadosSistema = DadosSistema.getIstance();
	    IndiceRepository indiceRepository = new IndiceRepository();
	    
	    List<Indice> indicesLI = new ArrayList<Indice>();
	    Indice indice;
	    
	    for (Integer ano : dadosSistema.retornaAnosValidados()) {
		indice = new Indice();
		indice.setDescricao("Liquidez Imediata");
		indice.setAno(ano);
		indice.setValor(indiceRepository.getLiquidezImediata(ano));
		indicesLI.add(indice);
	    }
	    
	    if (dadosSistema.getLancamentos().size() > 0) {
		if (dadosSistema.retornaAnosValidados().size() > 0) {
		    FrmGraficos grafico = new FrmGraficos(indicesLI, "Liquidez Imediata");
		    grafico.pack();
		    grafico.setLocationRelativeTo(this);
		    grafico.setVisible(true);
		} else {
		    JOptionPane.showMessageDialog(null, "Os lançamentos não foram validado.\n" + "Menu : Lançamentos -> Validação de saldos",
			    "Atenção", JOptionPane.INFORMATION_MESSAGE);
		}
	    } else {
		JOptionPane.showMessageDialog(null, "O sistema não tem lançamentos efetuados!\n"
			+ "1ª Opção: Menu: Importação -> Importação de lançamentos\n" + "2ª Opção: Menu: Lançamentos -> Saldos das contas",
			"Atenção", JOptionPane.INFORMATION_MESSAGE);
	    }
	}

    }
}
