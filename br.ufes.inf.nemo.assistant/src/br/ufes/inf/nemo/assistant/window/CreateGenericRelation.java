package br.ufes.inf.nemo.assistant.window;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.ufes.inf.nemo.assistant.graph.Node;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateGenericRelation extends AbstractWindow{

	private JTextField tfSource;
	private JTextField tfTarget;
	private JTextField tfRelationName;
	private JLabel lbTop = new JLabel("Create a <Relation> between <SourceClass> and <TargetClass>");
	private JLabel lbSource = new JLabel("<SourceClass>");
	private JLabel lbTarget = new JLabel("<TargetClass>");
	private JLabel lbMiddle = new JLabel("Set the cardinality and the name for the <Relation>");
	private JLabel lbRelation = new JLabel("<RelationStereotype>");
	private JButton btCreate = new JButton("Create <RelationStereotype>");

	/**
	 * Create the application.
	 */
	public CreateGenericRelation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 507, 192);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		lbTop.setBounds(12, 12, 470, 15);
		this.getContentPane().add(lbTop);

		lbSource.setBounds(37, 66, 115, 15);
		this.getContentPane().add(lbSource);

		lbTarget.setBounds(345, 66, 122, 15);
		this.getContentPane().add(lbTarget);

		JLabel label_2 = new JLabel("------------------------------------------");
		label_2.setBounds(146, 82, 220, 15);
		this.getContentPane().add(label_2);

		tfSource = new JTextField();
		tfSource.setBounds(74, 80, 46, 19);
		this.getContentPane().add(tfSource);
		tfSource.setColumns(10);

		tfTarget = new JTextField();
		tfTarget.setColumns(10);
		tfTarget.setBounds(375, 80, 46, 19);
		this.getContentPane().add(tfTarget);

		lbMiddle.setBounds(60, 39, 382, 15);
		this.getContentPane().add(lbMiddle);

		lbRelation.setBounds(164, 66, 168, 15);
		this.getContentPane().add(lbRelation);

		tfRelationName = new JTextField();
		tfRelationName.setBounds(199, 100, 114, 19);
		this.getContentPane().add(tfRelationName);
		tfRelationName.setColumns(10);

		btCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO Validar as cardinalidades
				
			}
		});
		btCreate.setBounds(132, 130, 262, 25);
		this.getContentPane().add(btCreate);
	}

	private String stereotype;
	private String srcCls;
	private String trgCls;
	
	@Override
	public void run(Node n) {
		super.run(n);
		lbTop.setText("Create a "+stereotype+" between "+srcCls+" and "+trgCls);
		lbMiddle.setText("Set the cardinality and the name for the "+stereotype);
		lbRelation.setText(stereotype);
		btCreate.setText("Create a "+stereotype);
		lbSource.setText(srcCls);
		lbTarget.setText(trgCls);
	}
	
	public void setRelation(String stereotype){
		this.stereotype = stereotype;
	}

	public void setSourceClass(String srcCls){
		this.srcCls = srcCls;
	}

	public void setTargetClass(String trgCls){
		this.trgCls = trgCls;
	}
}
