package br.com.feob.importacao;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import br.com.feob.singleton.DadosSistema;

public class Importacao extends JFrame implements ActionListener {

    private JLabel lbPath = new JLabel("Caminho");
    private JTextField txtFilePath = new JTextField(25);
    private JButton btnProcurar = new JButton("Procurar");
    private JButton btnImportar = new JButton("Importar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JFileChooser fileChooser = new JFileChooser();
    private String fileOpen = "";
    private DadosSistema dadosSistemas = DadosSistema.getIstance();
    private int opcaoChooser = -1;
    private JPanel frameChooser = new JPanel();
    private JPanel frameButtons = new JPanel();

    public Importacao() {

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();

	this.setTitle("Importa��o de Plano de Contas");
	this.setSize(450, 100);
	this.setLayout(new GridLayout(2, 2));
	this.setMaximumSize(new Dimension(450, 100));
	this.setMinimumSize(new Dimension(450, 100));

	this.setLocation((int) (dimension.getWidth() - this.getWidth()) / 2,
		(int) (dimension.getHeight() - this.getHeight()) / 2);

	frameChooser.setLayout(new FlowLayout(FlowLayout.LEFT));
	frameButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));

	fileChooser.setDialogTitle("Importa��o de Arquivo");
	fileChooser.setApproveButtonText("Abrir");
	txtFilePath.setEditable(false);

	btnProcurar.addActionListener(this);
	btnImportar.addActionListener(this);
	btnCancelar.addActionListener(this);

	frameChooser.add(lbPath);
	frameChooser.add(txtFilePath);
	frameChooser.add(btnProcurar);

	frameButtons.add(btnImportar);
	frameButtons.add(btnCancelar);

	this.add(frameChooser);
	this.add(frameButtons);

	this.setVisible(true);
    }

    private void lerArquivo() throws IOException {

	int tamanhoArquivo = getTamanhoArquivo(fileOpen);
	int atributosArquivo = getAtributosArquivos(fileOpen);
	int count = 0;
	String[][] planoContas = new String[tamanhoArquivo][atributosArquivo];

	BufferedReader bufferedReader = openFile();
	String line = bufferedReader.readLine();

	String[] conta;

	while (line != null) {
	    conta = new String[atributosArquivo];
	    conta = line.split("\\|");
	    planoContas[count] = conta;
	    line = bufferedReader.readLine();
	    count++;
	}

	dadosSistemas.setPlanoContas(planoContas);

	bufferedReader.close();

	JOptionPane.showMessageDialog(null, "Dados importados com sucesso!!");
    }

    private int getTamanhoArquivo(String filePath) throws IOException {

	BufferedReader bufferedReader = openFile();
	int numeroLinhas = 0;
	String line = bufferedReader.readLine();

	while (line != null) {

	    numeroLinhas++;
	    line = bufferedReader.readLine();
	}

	bufferedReader.close();

	return numeroLinhas;
    }

    private int getAtributosArquivos(String filePath) throws IOException {

	BufferedReader bufferedReader = openFile();
	String line = bufferedReader.readLine();
	String[] atributos = {};

	while (line != null) {
	    atributos = line.split("\\|");
	    break;
	}

	bufferedReader.close();

	return atributos.length;
    }

    private BufferedReader openFile() throws IOException {

	FileInputStream fileInputStream = new FileInputStream(fileOpen);

	InputStreamReader inputStreamReader = new InputStreamReader(
		fileInputStream);

	BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

	return bufferedReader;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

	if (e.getSource() == this.btnProcurar) {
	    opcaoChooser = fileChooser.showOpenDialog(this);
	}
	
	if (opcaoChooser == JFileChooser.OPEN_DIALOG) {
	    fileOpen = fileChooser.getSelectedFile().getPath();
	    txtFilePath.setText(fileOpen);
	}

	if (e.getSource() == this.btnImportar) {
	    try {
		lerArquivo();
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	}

	if (e.getSource() == this.btnCancelar) {
	    this.dispose();
	}
    }
}