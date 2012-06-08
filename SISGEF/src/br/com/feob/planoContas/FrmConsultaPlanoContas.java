package br.com.feob.planoContas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import br.com.feob.singleton.DadosSistema;

public class FrmConsultaPlanoContas extends JFrame implements ActionListener, ListSelectionListener, WindowListener{
    
    private JTable tbPlanoContas;
    private JScrollPane spPlacoContas;
    private JButton btnSelecionar;
    private JLayeredPane lpPlanoContas;
    private PlanoContasTableModel planoContasTableModel;
    private PlanoConta planoConta;
    

    private List<PlanoConta> planoContas;
    private Integer rowSelected = -1;
    
    public FrmConsultaPlanoContas() {
	
	GridBagConstraints gridBagConstraints;
	
	super.setTitle("Consulta plano de contas");
	super.setSize(400, 400);
	super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	super.getContentPane().setLayout(new GridBagLayout());
	super.setResizable(false);

	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	
	this.setLocation((int) (dimension.getWidth() - this.getWidth()) / 2, (int) (dimension.getHeight() - this.getHeight()) / 2);
	
	tbPlanoContas = new JTable();
	spPlacoContas = new JScrollPane(tbPlanoContas);
	btnSelecionar = new JButton();
	lpPlanoContas = new JLayeredPane();
	
	getTabelModel();
	
	tbPlanoContas.setModel(planoContasTableModel);
	tbPlanoContas.setGridColor(new Color(204, 204, 204));
	tbPlanoContas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tbPlanoContas.getSelectionModel().addListSelectionListener(this);
	
	lpPlanoContas.setBorder(BorderFactory.createTitledBorder("Plano de contas"));
	
	spPlacoContas.setBounds(10, 20, 380, 350);
	lpPlanoContas.add(spPlacoContas, JLayeredPane.DEFAULT_LAYER);
	
	btnSelecionar.setText("Selecionar");
	btnSelecionar.setBounds(295, 370, 100, 28);
	lpPlanoContas.add(btnSelecionar, JLayeredPane.DEFAULT_LAYER);
	
	btnSelecionar.addActionListener(this);
	
	gridBagConstraints = new GridBagConstraints();
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 0;
	gridBagConstraints.ipadx = 400;
	gridBagConstraints.ipady = 400;
	gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
	gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	super.getContentPane().add(lpPlanoContas, gridBagConstraints);
	
	pack();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
	if(e.getSource() == this.btnSelecionar){
	   
	    if(planoConta.getNivel().equals("S")){
		JOptionPane.showMessageDialog(null, "Conta sintética não pode receber lançamento!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
	    }else{
		this.dispose();
	    }
	}
    }
    
    private TableModel getTabelModel(){
	planoContas = DadosSistema.getIstance().getPlanoContas();
	planoContasTableModel = new PlanoContasTableModel(planoContas);
	return planoContasTableModel;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
	
	if(e.getSource() == tbPlanoContas.getSelectionModel() && tbPlanoContas.getRowSelectionAllowed()){
	    rowSelected = tbPlanoContas.getSelectedRow();
	    planoConta = new PlanoConta();
	    planoConta.setConta(planoContas.get(rowSelected).getConta());
	    planoConta.setDescricao(planoContas.get(rowSelected).getDescricao());
	    planoConta.setNivel(planoContas.get(rowSelected).getNivel());
	}
	
    }


    @Override
    public void windowOpened(WindowEvent e) {
	// TODO Auto-generated method stub
	
    }


    @Override
    public void windowClosing(WindowEvent e) {
	// TODO Auto-generated method stub
	
    }


    @Override
    public void windowClosed(WindowEvent e) {
	super.dispatchEvent(e);
	
    }


    @Override
    public void windowIconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
    }


    @Override
    public void windowDeiconified(WindowEvent e) {
	// TODO Auto-generated method stub
	
    }


    @Override
    public void windowActivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
    }


    @Override
    public void windowDeactivated(WindowEvent e) {
	// TODO Auto-generated method stub
	
    }
    
    public PlanoConta getPlanoConta() {
        return planoConta;
    }

}
