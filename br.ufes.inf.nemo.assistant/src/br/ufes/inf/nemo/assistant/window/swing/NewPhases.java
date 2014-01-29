package br.ufes.inf.nemo.assistant.window.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.ufes.inf.nemo.assistant.manager.ManagerNode;

public class NewPhases  extends AbstractWindow{

	private JButton btAddRow = new JButton("Add new row");
	private DefaultTableModel model;

	/**
	 * Create the application.
	 */
	public NewPhases() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private	JPanel		topPanel;
	private	JTable		table;
	private	JScrollPane scrollPane;
	private JButton btAddPhases;
	private void initialize() {
		// Set the frame characteristics
		setTitle( "Simple Table Application" );
		setSize( 415, 300 );
		getContentPane().setLayout(null);

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setBounds(0, 0, 413, 150);
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create a new table instance
		table = new JTable();
		model = new DefaultTableModel(
				new String[][] {
						{"", ""},
				},
				new String[] {
						"Phase Name", "Rules"
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(255);
		table.getColumnModel().getColumn(1).setPreferredWidth(448);

		// Add the table to a scrolling pane
		scrollPane = new JScrollPane( table );
		topPanel.add( scrollPane, BorderLayout.CENTER );

		btAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.addRow(new String[]{"", ""});
			}
		});
		btAddRow.setBounds(101, 162, 159, 25);
		getContentPane().add(btAddRow);

		btAddPhases = new JButton("Add new Phases");
		btAddPhases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> phases = new ArrayList<>();
				ArrayList<String> rules = new ArrayList<>();
				for (int i = 0; i < model.getRowCount(); i++) {
					phases.add((String)table.getValueAt(i, 0));
					rules.add((String)table.getValueAt(i, 1));
				}
				myNode.getTree().getManagerPatern().callback_newPhases(phases, rules);

				ManagerNode.goNext(myNode);
				instance.killMySelf();
			}
		});
		btAddPhases.setBounds(90, 199, 180, 25);
		getContentPane().add(btAddPhases);
	}
}
