package br.com.feob.renderer;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ActionButtons extends JFrame{

	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnEditar = new JButton("Editar");
	private JButton btnCancelar = new JButton("Cancelar");
	
	
	public ActionButtons() {
		super();
		this.setLayout(new FlowLayout());
		
		btnSalvar.setIcon(new ImageIcon(getClass().getResource("/assets/images/save.png")));
		btnSalvar.setFocusable(false);
		btnEditar.setIcon(new ImageIcon(getClass().getResource("/assets/images/edit.png")));
		btnEditar.setFocusable(false);
		btnCancelar.setIcon(new ImageIcon(getClass().getResource("/assets/images/cancel.png")));
		btnCancelar.setFocusable(false);
		
		
	}
}
