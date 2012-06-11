package br.com.feob.importacao;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import br.com.feob.lancamento.Lancamento;
import br.com.feob.planoContas.PlanoConta;
import br.com.feob.singleton.DadosSistema;

public class FrmImportacao extends JFrame implements ActionListener {

    private JLabel lbPath = new JLabel("Caminho");
    private JTextField txtFilePath = new JTextField(22);
    private JButton btnProcurar = new JButton("...");
    private JButton btnImportar = new JButton("Importar");
    private JButton btnCancelar = new JButton("Fechar");
    private JFileChooser fileChooser = new JFileChooser();
    private JProgressBar progressBar = new JProgressBar();
    private String fileOpen = "";
    private DadosSistema dadosSistemas = DadosSistema.getIstance();
    private int opcaoChooser = -1;
    private JPanel frameChooser = new JPanel();
    private JPanel frameButtons = new JPanel();
    private JPanel frameTipoImportacao = new JPanel();
    private JRadioButton rbPlanoContas = new JRadioButton("Plano de Contas");
    private JRadioButton rbLancamentos = new JRadioButton("Lançamentos");
    private ButtonGroup buttonGroupLayout = new ButtonGroup();
    
    public FrmImportacao() {

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();

	this.setTitle("Importação de Plano de Contas");
	this.setSize(450, 150);
	this.setLayout(new GridLayout(3, 2));
	this.setMaximumSize(new Dimension(450, 100));
	this.setMinimumSize(new Dimension(450, 100));
	this.setResizable(false);

	this.setLocation((int) (dimension.getWidth() - this.getWidth()) / 2, (int) (dimension.getHeight() - this.getHeight()) / 2);

	frameChooser.setLayout(new FlowLayout(FlowLayout.LEFT));
	frameButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
	frameTipoImportacao.setLayout(new FlowLayout(FlowLayout.LEFT));

	fileChooser.setDialogTitle("Importação de Arquivo");
	fileChooser.setApproveButtonText("Abrir");
	txtFilePath.setEditable(false);

	btnProcurar.addActionListener(this);
	btnImportar.addActionListener(this);
	btnCancelar.addActionListener(this);

	frameChooser.add(lbPath);
	frameChooser.add(txtFilePath);
	frameChooser.add(btnProcurar);

	buttonGroupLayout.add(rbPlanoContas);
	buttonGroupLayout.add(rbLancamentos);

	frameTipoImportacao.add(rbPlanoContas);
	frameTipoImportacao.add(rbLancamentos);

	progressBar.setIndeterminate(true);
	progressBar.setVisible(false);

	frameButtons.add(progressBar);
	frameButtons.add(btnImportar);
	frameButtons.add(btnCancelar);

	this.add(frameChooser);
	this.add(frameTipoImportacao);
	this.add(frameButtons);

	this.setVisible(true);
    }

    private void lerArquivo() throws IOException {
	progressBar.setVisible(true);

	BufferedReader bufferedReader = openFile();
	String line = bufferedReader.readLine();

	String[] item;

	if (rbPlanoContas.isSelected()) {

	    PlanoConta planoConta;

	    while (line != null) {
		item = new String[3];
		item = line.split("\\|");
		planoConta = new PlanoConta(item[0], item[1], item[2]);
		dadosSistemas.addPlanoConta(planoConta);
		line = bufferedReader.readLine();
	    }
	}
	
	if(rbLancamentos.isSelected()){
	    Lancamento lancamento;

	    while (line != null) {
		item = new String[4];
		item = line.split("\\|");
		lancamento = new Lancamento();
		lancamento.setCodigoLancamento(Long.parseLong(item[0]));
		lancamento.setConta(item[1]);
		lancamento.setAno(Integer.parseInt(item[2]));
		lancamento.setSaldo(Double.parseDouble(item[3]));
		dadosSistemas.addLancamento(lancamento);
		line = bufferedReader.readLine();
	    }
	    
	    dadosSistemas.setCodigoLancamentoImportacao(dadosSistemas.getMaiorValor());
	}

	bufferedReader.close();

	JOptionPane.showMessageDialog(null, "Dados importados com sucesso!!");

	progressBar.setVisible(false);
    }

    public BufferedReader openFile() throws IOException {

	FileInputStream fileInputStream = new FileInputStream(fileOpen);

	InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

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