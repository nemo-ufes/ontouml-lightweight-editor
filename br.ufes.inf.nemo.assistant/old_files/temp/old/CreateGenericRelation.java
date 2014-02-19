package temp.old;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class CreateGenericRelation extends AbstractWindow{

	private JTextField tfScrMax;
	private JTextField tfRelationName;
	private JLabel lbTop = new JLabel("Create a association for class <SourceClass>");
	private JLabel lbSource = new JLabel("<SourceClass>");
	private JLabel lbTarget = new JLabel("Target Class");
	private JLabel lbRelation = new JLabel("<RelationStereotype>");
	private JButton btCreate = new JButton("Create <RelationStereotype>");
	private JComboBox<String> cbClasses = new JComboBox<String>();
	private NewRelator nr;

	public void setNewRelator(NewRelator newRelator){
		nr = newRelator;
	}

	/**
	 * Create the application.
	 */
	public CreateGenericRelation() {
		instance = this;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 489, 191);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		lbTop.setBounds(12, 12, 470, 15);
		this.getContentPane().add(lbTop);

		lbSource.setBounds(17, 52, 115, 15);
		this.getContentPane().add(lbSource);

		lbTarget.setBounds(357, 27, 103, 15);
		this.getContentPane().add(lbTarget);

		JLabel label_2 = new JLabel("------------------------------------------");
		label_2.setBounds(129, 65, 220, 15);
		this.getContentPane().add(label_2);

		tfScrMax = new JTextField();
		tfScrMax.setText("1");
		tfScrMax.setHorizontalAlignment(SwingConstants.CENTER);
		tfScrMax.setBounds(56, 82, 21, 19);
		this.getContentPane().add(tfScrMax);
		tfScrMax.setColumns(10);

		lbRelation.setBounds(147, 49, 168, 15);
		this.getContentPane().add(lbRelation);

		tfRelationName = new JTextField();
		tfRelationName.setBounds(136, 82, 157, 19);
		this.getContentPane().add(tfRelationName);
		tfRelationName.setColumns(10);

		btCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nr.updateTable(srcCls, tfSrcMin.getText()+".."+tfScrMax.getText(),tfRelationName.getText(), tfTrgMin.getText()+".."+tfTrgMax.getText(), (String)cbClasses.getSelectedItem());
				killMySelf();
			}
		});
		btCreate.setBounds(107, 113, 262, 25);
		this.getContentPane().add(btCreate);
		tfSrcMin.setText("1");
		tfSrcMin.setHorizontalAlignment(SwingConstants.CENTER);
		tfSrcMin.setColumns(10);
		tfSrcMin.setBounds(21, 82, 21, 19);

		getContentPane().add(tfSrcMin);
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMin.setBounds(20, 69, 21, 14);

		getContentPane().add(lblMin);
		lblMax.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMax.setBounds(55, 69, 30, 14);

		getContentPane().add(lblMax);
		label.setBounds(43, 82, 12, 14);

		getContentPane().add(label);
		tfTrgMax.setText("*");
		tfTrgMax.setHorizontalAlignment(SwingConstants.CENTER);
		tfTrgMax.setColumns(10);
		tfTrgMax.setBounds(374, 82, 21, 19);

		getContentPane().add(tfTrgMax);
		tfTrgMin.setHorizontalAlignment(SwingConstants.CENTER);
		tfTrgMin.setText("2");
		tfTrgMin.setColumns(10);
		tfTrgMin.setBounds(339, 82, 21, 19);

		getContentPane().add(tfTrgMin);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(338, 69, 21, 14);

		getContentPane().add(label_1);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(373, 69, 30, 14);

		getContentPane().add(label_3);
		label_4.setBounds(361, 82, 12, 14);

		getContentPane().add(label_4);

		cbClasses.setBounds(321, 49, 144, 20);
		getContentPane().add(cbClasses);
	}

	private String stereotype;
	private String srcCls;
	private final JTextField tfSrcMin = new JTextField();
	private final JLabel lblMin = new JLabel("min");
	private final JLabel lblMax = new JLabel("max");
	private final JLabel label = new JLabel("...");
	private final JTextField tfTrgMax = new JTextField();
	private final JTextField tfTrgMin = new JTextField();
	private final JLabel label_1 = new JLabel("min");
	private final JLabel label_3 = new JLabel("max");
	private final JLabel label_4 = new JLabel("...");

	public void setRelation(String stereotype){
		this.stereotype = stereotype;
	}

	public void setSourceClass(String srcCls){
		this.srcCls = srcCls;
	}

	public void setClassesOption(String[] classes){
		cbClasses.setModel(new DefaultComboBoxModel<String>(classes));
	}

	public void updateLabels(){
		lbTop.setText("Create a association for class "+srcCls);
		lbRelation.setText(stereotype);
		btCreate.setText("Create a "+stereotype);
		lbSource.setText(srcCls);
	}
}
