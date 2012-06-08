package br.com.feob.lookfeel;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LookAndFeel extends JFrame {

	private UIManager.LookAndFeelInfo[] looks = UIManager
			.getInstalledLookAndFeels();
	private JLabel lbLabel01 = new JLabel("Nome :");
	private JLabel lbLabel02 = new JLabel("E-mail :");
	private JTextField jtTexto01 = new JTextField(10);
	private JTextField jtTexto02 = new JTextField(10);
	private JButton jbOk = new JButton("Enviar");
	private JButton jbLim = new JButton("Limpar");
	private JRadioButton[] escolha = new JRadioButton[looks.length];
	private ButtonGroup grupo = new ButtonGroup();

	public LookAndFeel() {
		
		super("Selecione um LF");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		ItemSelecionado iselect = new ItemSelecionado();
		for (int i = 0; i < looks.length; i++) {
			escolha[i] = new JRadioButton(looks[i].getName());
			escolha[i].addItemListener(iselect);
			grupo.add(escolha[i]);
			c.add(escolha[i]);
		}
		escolha[2].setSelected(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(200, 250);
		setVisible(true);
	}

	public void atualiza(int i) {
		
		try {
			UIManager.setLookAndFeel(looks[i].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class ItemSelecionado implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			for (int i = 0; i < escolha.length; i++) {
				if (escolha[i].isSelected())
					atualiza(i);
			}
		}
	}

}
