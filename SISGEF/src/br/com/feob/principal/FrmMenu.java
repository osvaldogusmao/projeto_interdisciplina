package br.com.feob.principal;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.com.feob.configuracao.FrmConfiguracao;
import br.com.feob.importacao.FrmImportacao;
import br.com.feob.lancamento.FrmLancamento;
import br.com.feob.lancamento.FrmValidarLancamento;
import br.com.feob.planoContas.FrmPlanoContas;
import br.com.feob.singleton.DadosSistema;

public class FrmMenu extends JFrame implements ActionListener {

	private UIManager.LookAndFeelInfo[] looks =  UIManager.getInstalledLookAndFeels();
	private JMenu mCadastro = new JMenu("Cadastro");
	private JMenuItem miPlanoContas = new JMenuItem("Plano de Contas");
	private JMenu mLancamento = new JMenu("Lançamentos");
	private JMenuItem miLancamento = new JMenuItem("Saldos das contas");
	private JMenuItem miValidacaoSaldos = new JMenuItem("Validação de Saldos");
	private JMenu mImportacao = new JMenu("Importação");
	private JMenuItem miImportacaoPlanoContas = new JMenuItem("Importação de arquivo");
	private JMenu mConfiguracao = new JMenu("Configurações");
	private JMenuItem miConfiguracaoSistema = new JMenuItem("Configuração Sistema");
	private JMenu mSistema = new JMenu("Sistema");
	private JMenuItem miLayout = new JMenuItem("Layout");
	private JMenuItem miSair = new JMenuItem("Sair");

	public FrmMenu() {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();

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

		
		//mSistema.add(miLayout);
		mSistema.add(miSair);
		miSair.addActionListener(this);
		miLayout.addActionListener(this);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(mCadastro);
		menuBar.add(mLancamento);
		menuBar.add(mImportacao);
		menuBar.add(mConfiguracao);
		menuBar.add(mSistema);

		this.setJMenuBar(menuBar);
		this.setSize((int) dimension.getWidth(), (int) dimension.getHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(looks[0].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.miSair) {
			int opcao = JOptionPane.showConfirmDialog(getParent(), "Deseja realmente sair?", "Aten‹o", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(opcao == 0){
				System.exit(0);
			}
			
		}
		if (e.getSource() == this.miPlanoContas) {
			new FrmPlanoContas();
		}
		if(e.getSource() == this.miConfiguracaoSistema){
			JFrame frame = new JFrame("Configuração do sistema");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setSize(500, 200);
			frame.add(new FrmConfiguracao());
			frame.pack();
			frame.setVisible(true);
		}
		
		if(e.getSource() == this.miLancamento){
		    //if(DadosSistema.getIstance().getPlanoContas().size() > 0){
			new FrmLancamento().setVisible(true);
		 //   }else{
			//JOptionPane.showMessageDialog(null, "O sistema não tem plano de contas cadastrado!\n" +
			//		"Menu: Importação -> Importação de Plano de Contas", "Atenção", JOptionPane.INFORMATION_MESSAGE);
		   // }
		    
		}
		
		if(e.getSource() == this.miImportacaoPlanoContas){
		    new FrmImportacao();
		}
		
		if(e.getSource() == miValidacaoSaldos){
		    new FrmValidarLancamento().setVisible(true);
		}

	}
}
