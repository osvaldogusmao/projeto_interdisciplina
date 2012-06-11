package br.com.feob.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    Image image;
	
	public ImagePanel(String image) {
		this(new ImageIcon(image).getImage());
	}
	
	public ImagePanel(Image image) {
		this.image = image;
		Dimension dimension = new Dimension(image.getWidth(null), image.getHeight(null));
		setPreferredSize(dimension);
		setSize(dimension);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image,0,0,null);
	}
	
    
}

