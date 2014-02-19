package temp.old;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.ufes.inf.nemo.assistant.manager.ManagerNode;

public class NewGeneralizationSet extends AbstractWindow{
	private JComboBox<String> cbGeneralizationSet = new JComboBox<String>();
	private JComboBox<String> cbGeneral = new JComboBox<String>();
	private JLabel lbConceptName = new JLabel(" <ConceptName>");
	private JCheckBox chckbxDisjoint = new JCheckBox("Disjoint");
	private JCheckBox chckbxComplete = new JCheckBox("Complete");
	private JButton btnInsert = new JButton("Insert ");
	private JButton btnNewGeneralization = new JButton("New GeneralizationSet");
	private JLabel lblSetTheMetaproperties = new JLabel("Set the Meta-properties from the GeneralizationSet:");


	/**
	 * Create the application.
	 */
	public NewGeneralizationSet() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		this.setBounds(100, 100, 673, 300);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Select the General for the concept:");
		lblNewLabel.setBounds(32, 12, 265, 15);
		this.getContentPane().add(lblNewLabel);

		JLabel lblSelectWhichGeneralizationset = new JLabel("Which GeneralizationSet will be included this concept:");
		lblSelectWhichGeneralizationset.setBounds(12, 112, 508, 15);
		this.getContentPane().add(lblSelectWhichGeneralizationset);
		cbGeneralizationSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Procurar o disjoint e complete
				boolean[] metaPropertiesGeneralizationSets = myNode.getTree().getManagerPatern().getStringMetaPropertiesGeneralizationSet((String)cbGeneral.getSelectedItem());
				chckbxDisjoint.setSelected(metaPropertiesGeneralizationSets[0]);
				chckbxComplete.setSelected(metaPropertiesGeneralizationSets[1]);
			}
		});

		cbGeneralizationSet.setBounds(416, 107, 222, 24);
		this.getContentPane().add(cbGeneralizationSet);

		JLabel lblWhoWillBe = new JLabel("Who will be the General for this concept:");
		lblWhoWillBe.setBounds(12, 57, 508, 15);
		this.getContentPane().add(lblWhoWillBe);
		cbGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] generalizationSets = myNode.getTree().getManagerPatern().getStringGeneralizationSet((String)cbGeneral.getSelectedItem());
				cbGeneralizationSet.setModel(new DefaultComboBoxModel<String>(generalizationSets));
				if(generalizationSets.length > 0){
					boolean[] metaPropertiesGeneralizationSets = myNode.getTree().getManagerPatern().getStringMetaPropertiesGeneralizationSet((String)cbGeneral.getItemAt(0));
					chckbxDisjoint.setSelected(metaPropertiesGeneralizationSets[0]);
					chckbxComplete.setSelected(metaPropertiesGeneralizationSets[1]);
					btnInsert.setEnabled(true);
				}
			}
		});

		cbGeneral.setBounds(416, 52, 222, 24);
		this.getContentPane().add(cbGeneral);

		lblSetTheMetaproperties.setBounds(12, 169, 393, 15);
		this.getContentPane().add(lblSetTheMetaproperties);
		chckbxDisjoint.setSelected(true);

		chckbxDisjoint.setBounds(416, 161, 129, 23);
		this.getContentPane().add(chckbxDisjoint);
		chckbxComplete.setSelected(true);

		chckbxComplete.setBounds(416, 181, 154, 23);
		this.getContentPane().add(chckbxComplete);

		btnInsert.setEnabled(false);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myNode.getTree().getManagerPatern().callback_newGeneralizationSet((String)cbGeneral.getSelectedItem(),(String)cbGeneralizationSet.getSelectedItem(),chckbxDisjoint.isSelected(),chckbxComplete.isSelected());
				ManagerNode.goNext(myNode);
				instance.killMySelf();
			}
		});
		btnInsert.setBounds(208, 234, 117, 25);
		this.getContentPane().add(btnInsert);

		btnNewGeneralization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ret = JOptionPane.showInputDialog("What is the name of the new GeneralizationSet?");
				if(ret != null){
					if(!ret.equals(" ")){
						myNode.getTree().getManagerPatern().createNewGeneralizationSet(ret);
						String[] generalizationSets = myNode.getTree().getManagerPatern().getStringGeneralizationSet((String)cbGeneral.getSelectedItem());
						cbGeneralizationSet.setModel(new DefaultComboBoxModel<String>(generalizationSets));
						boolean[] metaPropertiesGeneralizationSets = myNode.getTree().getManagerPatern().getStringMetaPropertiesGeneralizationSet((String)cbGeneral.getItemAt(0));
						chckbxDisjoint.setSelected(metaPropertiesGeneralizationSets[0]);
						chckbxComplete.setSelected(metaPropertiesGeneralizationSets[1]);
						btnNewGeneralization.setEnabled(false);
					}
				}
			}
		});
		btnNewGeneralization.setBounds(73, 139, 222, 23);
		this.getContentPane().add(btnNewGeneralization);

		lbConceptName.setBounds(289, 12, 256, 15);
		getContentPane().add(lbConceptName);
	}
	/**
	 * Set all possible general classes
	 * */
	public void setGeneralClasses(String[] generalClasses){
		cbGeneral.setModel(new DefaultComboBoxModel<String>(generalClasses));
	}

	@Override
	public void run(Node n) {
		super.run(n);
		if(autoGetConceptName){
			lbConceptName.setText(conceptName);
		}
		cbGeneral.setSelectedIndex(0);
	}

	public void setEditableMetaProperties(boolean filter) {
			if(!filter){
				chckbxDisjoint.setSelected(true);
				chckbxComplete.setSelected(true);
				chckbxDisjoint.setVisible(false);
				chckbxComplete.setVisible(false);
				lblSetTheMetaproperties.setVisible(false);
			}
	}
}
