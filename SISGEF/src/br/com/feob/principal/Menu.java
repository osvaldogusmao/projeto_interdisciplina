package br.com.feob.principal;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.com.feob.cadastro.PlanoContas;
import br.com.feob.lookfeel.LookAndFeel;

public class Menu extends JFrame implements ActionListener {

	private UIManager.LookAndFeelInfo[] looks =  UIManager.getInstalledLookAndFeels();
	private JMenu mCadastro = new JMenu("Cadastro");
	private JMenuItem miPlanoContas = new JMenuItem("Plano de Contas");
	private JMenu mLancamento = new JMenu("Lan�amentos");
	private JMenuItem miLancamento = new JMenuItem("Saldos das contas");
	private JMenu mSistema = new JMenu("Sistema");
	private JMenuItem miLayout = new JMenuItem("Layout");
	private JMenuItem miSair = new JMenuItem("Sair");

	public Menu() {
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();

		mCadastro.add(miPlanoContas);
		miPlanoContas.addActionListener(this);

		mLancamento.add(miLancamento);
		miLancamento.addActionListener(this);

		//mSistema.add(miLayout);
		mSistema.add(miSair);
		miSair.addActionListener(this);
		miLayout.addActionListener(this);

		JMenuBar menuBar = new JMenuBar();
		menuBar.add(mCadastro);
		menuBar.add(mLancamento);
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
			int opcao = JOptionPane.showConfirmDialog(getParent(), "Deseja realmente sair?", "Aten��o", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(opcao == 0){
				System.exit(0);
			}
			
		}
		if (e.getSource() == this.miPlanoContas) {
			new PlanoContas();
		}
		if(e.getSource() == this.miLayout){
			new LookAndFeel();
		}

	}
}