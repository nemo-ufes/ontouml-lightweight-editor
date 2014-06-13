package br.ufes.inf.nemo.meronymic_validation.zdeprecated;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.meronymic_validation.forbidden.ForbiddenMemberOf;
import javax.swing.JButton;

public class FixIntranstiveMemberOfFrame extends JFrame {

	private JPanel contentPane;
	private ForbiddenMemberOf forbidden;
	private AssociationCombo comboBox;
	private JRadioButton removeRelationRadio;
	private JRadioButton changeAllToSubCollectionOfRadio;
	private JRadioButton makeValidMemberOfRadio;
	private JRadioButton changeAllToComponentOfRadio;
	private JRadioButton changeAllToSubQuantityOfRadio;
	private JButton fixModelButton;
	private JButton btnClose;

	/**
	 * Create the frame.
	 */
	public FixIntranstiveMemberOfFrame(ForbiddenMemberOf forbidden) {
		this.forbidden = forbidden;
		
		String introduction = 	"The "+OntoUMLNameHelper.getTypeAndName(forbidden.getMeronymic(), true, true)+
								", which is defined between the collection "+OntoUMLNameHelper.getName(forbidden.getWhole(), true, false)+
								" and its members "+OntoUMLNameHelper.getName(forbidden.getPart(), true, false)+" cannot hold because all memberOfs are intranstive and :";
				
		for (Property p : forbidden.getPath()) {
			introduction += "\r\n"+OntoUMLNameHelper.getTypeAndName(p.getOpposite().getType(), true, true)+" is a collection of "+OntoUMLNameHelper.getTypeAndName(p.getType(), true, true);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextArea txtrMemberofRelationsAre = new JTextArea();
		txtrMemberofRelationsAre.setEditable(false);
		txtrMemberofRelationsAre.setText(introduction);
		txtrMemberofRelationsAre.setLineWrap(true);
		txtrMemberofRelationsAre.setBackground(UIManager.getColor("Button.background"));
		
		removeRelationRadio = new JRadioButton("Remove relation");
		
		comboBox = new AssociationCombo(forbidden);
		
		changeAllToSubCollectionOfRadio = new JRadioButton("Change to SubCollectionOf and Change all relations int the path to SubCollectionOf");
		
		makeValidMemberOfRadio = new JRadioButton("Keep as MemberOf and change all but the last relations in the path to SubCollection");
		
		changeAllToComponentOfRadio = new JRadioButton("Change all to componentOf and all types to functional complexes");
		
		changeAllToSubQuantityOfRadio = new JRadioButton("Change all to SubQuantityOf and all types to quantities");
		
		fixModelButton = new JButton("Fix Model");
		fixModelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(changeAllToComponentOfRadio.isSelected())
					FixIntranstiveMemberOfFrame.this.forbidden.setChangeAllToComponentOf();
				else if(changeAllToSubCollectionOfRadio.isSelected())
					FixIntranstiveMemberOfFrame.this.forbidden.setChangeAllToSubCollectionOf(); 
				else if(changeAllToSubQuantityOfRadio.isSelected())
					FixIntranstiveMemberOfFrame.this.forbidden.setChangeAllToSubQuantityOf();
				else if(makeValidMemberOfRadio.isSelected())
					FixIntranstiveMemberOfFrame.this.forbidden.setMakeMemberOfValid();
				else if(removeRelationRadio.isSelected())
					FixIntranstiveMemberOfFrame.this.forbidden.setRemoveMemberOf(comboBox.getSelectedAssociation());
				
				if(FixIntranstiveMemberOfFrame.this.forbidden.isActionSet())
					FixIntranstiveMemberOfFrame.this.forbidden.runFix();
				
				FixIntranstiveMemberOfFrame.this.dispose();
				
				
					
			}
		});
		
		btnClose = new JButton("Close");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addComponent(txtrMemberofRelationsAre, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(removeRelationRadio)
					.addGap(7)
					.addComponent(comboBox, 0, 534, Short.MAX_VALUE)
					.addGap(5))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(makeValidMemberOfRadio, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(changeAllToSubCollectionOfRadio)
					.addContainerGap(226, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(changeAllToComponentOfRadio, GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(changeAllToSubQuantityOfRadio, GroupLayout.PREFERRED_SIZE, 639, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(506, Short.MAX_VALUE)
					.addComponent(fixModelButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClose)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrMemberofRelationsAre, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(removeRelationRadio))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(3)
					.addComponent(makeValidMemberOfRadio)
					.addGap(3)
					.addComponent(changeAllToSubCollectionOfRadio)
					.addGap(2)
					.addComponent(changeAllToComponentOfRadio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(changeAllToSubQuantityOfRadio)
					.addGap(120)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(fixModelButton)
						.addComponent(btnClose))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
}
