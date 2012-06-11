package br.com.feob.indices;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TabExpander;

import br.com.feob.indices.repository.IndiceRepository;
import br.com.feob.lancamento.LancamentoTableModel;
import br.com.feob.lancamento.repository.LancamentoRepository;
import br.com.feob.singleton.DadosSistema;
import br.com.feob.util.ColorRenderer;

public class FrmIndices extends JDialog implements ActionListener, RowSorterListener {

    private JTable tbIndices;
    private JScrollPane spIndices;
    private JLayeredPane lpIndices;
    private JPanel frameButtons;
    private JButton btnAtualizar;
    private TableRowSorter sorter;
    private JComboBox<String> cbAnos;

    private DadosSistema dadosSistema = DadosSistema.getIstance();
    private IndiceRepository indiceRepository = new IndiceRepository();
    private List<Indice> indices = new ArrayList<Indice>();
    private IndicesTableModel indicesTableModel = new IndicesTableModel();

    public FrmIndices() {

	GridBagConstraints gridBagConstraints;

	super.setTitle("Indicadores Gerenciais - Índices");
	super.getContentPane().setLayout(new GridBagLayout());
	super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	super.setResizable(false);

	List<Integer> anos = dadosSistema.retornaAnosValidados();

	for (Integer ano : anos) {
	    indices.addAll(indiceRepository.getIndecesPorAno(ano));
	}

	indicesTableModel.setData(indices);

	lpIndices = new JLayeredPane();
	lpIndices.setBorder(BorderFactory.createTitledBorder("Indices"));

	tbIndices = new JTable();
	spIndices = new JScrollPane(tbIndices);

	tbIndices.setShowGrid(true);
	tbIndices.setModel(indicesTableModel);
	tbIndices.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
	TableColumn column = tbIndices.getColumnModel().getColumn(0);
	column.setCellRenderer(new ColorRenderer());
	column.setPreferredWidth(200);
	sorter = new TableRowSorter<IndicesTableModel>(indicesTableModel);
	sorter.setRowFilter(null);
	sorter.addRowSorterListener(this);
	tbIndices.setRowSorter(sorter);

	spIndices.setBounds(10, 20, 780, 490);
	lpIndices.add(spIndices, JLayeredPane.DEFAULT_LAYER);

	gridBagConstraints = new GridBagConstraints();
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 0;
	gridBagConstraints.ipadx = 800;
	gridBagConstraints.ipady = 520;
	gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
	super.getContentPane().add(lpIndices, gridBagConstraints);

	frameButtons = new JPanel();
	btnAtualizar = new JButton();
	cbAnos = new JComboBox<String>();

	cbAnos.addItem("Filtar por ano");
	
	for (int i = 0; i < anos.size(); i++) {
	    cbAnos.addItem(anos.get(i).toString());
	}

	frameButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));

	frameButtons.add(cbAnos);
	
	btnAtualizar.setText("Filtrar");
	btnAtualizar.setIcon(new ImageIcon(getClass().getResource("/assets/images/update.png")));
	btnAtualizar.setFocusable(false);
	btnAtualizar.addActionListener(this);
	frameButtons.add(btnAtualizar);

	gridBagConstraints = new GridBagConstraints();
	gridBagConstraints.gridx = 0;
	gridBagConstraints.gridy = 1;
	gridBagConstraints.ipadx = 580;
	gridBagConstraints.ipady = 10;
	gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
	super.getContentPane().add(frameButtons, gridBagConstraints);

	pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	if(e.getSource() == this.btnAtualizar){
	    
	    String ano = cbAnos.getSelectedItem().toString();
	    List filters = new ArrayList();

	    if (ano.equals("Filtar por ano")) {
		sorter.setRowFilter(null);
	    }

	    if (!ano.equals("Filtar por ano")) {
		filters.add(RowFilter.regexFilter(ano));
	    }
	    sorter.setRowFilter(RowFilter.andFilter(filters));
	}
	
    }

    @Override
    public void sorterChanged(RowSorterEvent e) {

	
    }

}