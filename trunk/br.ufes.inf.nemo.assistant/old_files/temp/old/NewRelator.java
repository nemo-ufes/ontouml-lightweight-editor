package temp.old;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class NewRelator extends AbstractWindow{

	
	public static void main(String[] args) {
		new NewRelator().setVisible(true);
	}
	
	private JTextField tfRelatorName;
	private JTable table = new JTable();
	private NewRelator _instance;
	/**
	 * Create the application.
	 */
	public NewRelator() {
		_instance = this;
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 715, 266);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 66, 525, 133);
		getContentPane().add(panel);
		// Create a new table instance
		
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Relator", "Cardinality", "Mediation", "Cardinality", "Class Destiny"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(126);
		table.getColumnModel().getColumn(1).setPreferredWidth(66);
		table.getColumnModel().getColumn(2).setPreferredWidth(182);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		panel.setLayout(new BorderLayout(0, 0));

		// Add the table to a scrolling pane
		JScrollPane scrollPane = new JScrollPane( table );
		panel.add( scrollPane );

		JLabel lblNameToRelator = new JLabel("Name to Relator");
		lblNameToRelator.setBounds(10, 23, 93, 14);
		getContentPane().add(lblNameToRelator);

		tfRelatorName = new JTextField();
		tfRelatorName.setBounds(100, 20, 161, 20);
		getContentPane().add(tfRelatorName);
		tfRelatorName.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 48, 658, 7);
		getContentPane().add(separator);
		
		JButton btAddClass = new JButton("Add Existining Class");
		btAddClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateGenericRelation cgr = new CreateGenericRelation();
				cgr.setSourceClass(tfRelatorName.getText());
				cgr.setRelation("mediation");
				cgr.setClassesOption(new String[]{"Kind - Casa", "Subkind - Mulher"});
				cgr.setNewRelator(_instance);
				cgr.updateLabels();
				cgr.setVisible(true);
			}
		});
		btAddClass.setBounds(547, 78, 133, 23);
		getContentPane().add(btAddClass);
		
		JButton btNewClass = new JButton("Add New Class");
		btNewClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewClass nc = new NewClass();
				//TODO os que o Relator pode ter na mediation
				nc.setStereotypes(new String[]{"Kind","Subkind"});
				Node node = new Node(myNode.getTree());
				node.setWin(nc);
				node.setNext(null);	
				node.run();
			}
		});
		btNewClass.setBounds(547, 108, 133, 23);
		getContentPane().add(btNewClass);
		
		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(566, 163, 89, 23);
		getContentPane().add(btnOk);
	}

	public void updateTable(String relator, String cardRel,String medName, String cardTarg, String target){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new String[]{relator,cardRel,medName, cardTarg, target});
	}
}
