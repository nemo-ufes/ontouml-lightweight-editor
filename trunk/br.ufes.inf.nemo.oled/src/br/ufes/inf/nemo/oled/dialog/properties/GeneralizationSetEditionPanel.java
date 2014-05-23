package br.ufes.inf.nemo.oled.dialog.properties;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.explorer.CustomOntoUMLElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;

public class GeneralizationSetEditionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private DiagramManager diagramManager;
	private GeneralizationSet genSet;
	private JTextField textField;
	@SuppressWarnings("unused")
	private Component parent;
	private JButton btnAdd;
	private JButton btnDelete;
	private JScrollPane scrollPane;
	private JCheckBox cbxDisjoint;
	private JCheckBox cbxCovering;
	private JLabel lblName;
	@SuppressWarnings("rawtypes")
	private DefaultListModel genModel;
	private JList<CustomOntoUMLElement> genList;
	private JLabel lblParticipatingGeneralizations;
	
	public GeneralizationSetEditionPanel(JDialog parent, final DiagramManager diagramManager, final GeneralizationSet genSet) 
	{		
		initData(parent,diagramManager,genSet);
		initGUI();		
	}
	
	/**
	 * @wbp.parser.constructor 
	 */
	public GeneralizationSetEditionPanel(JFrame parent, final DiagramManager diagramManager, final GeneralizationSet genSet) 
	{		
		initData(parent,diagramManager,genSet);
		initGUI();		
	}		
	
	public GeneralizationSetEditionPanel(final Component parent, final DiagramManager diagramManager, final GeneralizationSet genSet) 
	{
		initData(parent,diagramManager,genSet);
		initGUI();
	}
	
	public void initData(final Component parent,final DiagramManager diagramManager, GeneralizationSet genSet)
	{
		this.parent = parent;
		this.diagramManager = diagramManager;
		this.genSet = genSet;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initGUI()
	{
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder(""));
		
		genModel = new DefaultListModel();
		for(Generalization gen: genSet.getGeneralization())
		{
			genModel.addElement(new CustomOntoUMLElement(gen,""));
		}		
		genList = new JList<CustomOntoUMLElement>(genModel);
		
		scrollPane = new JScrollPane(genList);
		
		lblParticipatingGeneralizations = new JLabel("Participating generalizations:");
		
		btnAdd = new JButton("");		
		btnAdd.setIcon(new ImageIcon(GeneralizationSetEditionPanel.class.getResource("/resources/icons/x16/add.png")));
		btnAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OntoUMLParser refparser = ProjectBrowser.getParserFor(diagramManager.getCurrentProject());
				ArrayList<CustomOntoUMLElement> list = new ArrayList<CustomOntoUMLElement>();
				for(Generalization g: refparser.getAllInstances(Generalization.class))
				{
					if (!(genSet.getGeneralization().contains(g))) list.add(new CustomOntoUMLElement(g,""));
				}				
				if (list.size()==0) {
					JOptionPane.showMessageDialog(GeneralizationSetEditionPanel.this, "No generalization left in the model.", "Add", JOptionPane.INFORMATION_MESSAGE);
				}else{
					CustomOntoUMLElement selected = (CustomOntoUMLElement) JOptionPane.showInputDialog(GeneralizationSetEditionPanel.this, 
					        "Which generalization do you want to include in Generalization Set"+genSet.getName(),
					        "Add",
					        JOptionPane.QUESTION_MESSAGE, 
					        null, 
					        list.toArray(), 
					        list.toArray()[0]
					);
					if(selected!=null){
						genModel.addElement(selected);
					
						// add
						genSet.getGeneralization().add(((Generalization)selected.getElement()));
						((Generalization)selected.getElement()).getGeneralizationSet().add(genSet);
					}
				}				
			}
		});		
		
		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(GeneralizationSetEditionPanel.class.getResource("/resources/icons/x16/cross.png")));
		btnDelete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				CustomOntoUMLElement gen = (CustomOntoUMLElement)genList.getSelectedValue();
				
				//remove
				genSet.getGeneralization().remove(((Generalization)gen.getElement()));
				((Generalization)gen.getElement()).getGeneralizationSet().remove(genSet);
				
				genModel.removeElement(genList.getSelectedValue());				

			}
		});
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 428, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblParticipatingGeneralizations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 183, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblParticipatingGeneralizations, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addComponent(btnAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addComponent(btnDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 431, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		
		lblName = new JLabel("Name:  ");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		cbxCovering = new JCheckBox("Covering/Complete");		
		cbxDisjoint = new JCheckBox("Disjoint");
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(cbxDisjoint)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbxCovering))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxCovering)
						.addComponent(cbxDisjoint))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		setInitialData();
	}
	
	public class CustomComparator implements Comparator<CustomOntoUMLElement> 
    {
        @Override
        public int compare(CustomOntoUMLElement o1, CustomOntoUMLElement o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    }
	
	public void setInitialData()
	{
		cbxCovering.setSelected(genSet.isIsCovering());
		cbxDisjoint.setSelected(genSet.isIsDisjoint());
		textField.setText(genSet.getName());				
	}
	
	public void transferGenSetData()
	{
		boolean redesign = false;
		
		genSet.setIsCovering(cbxCovering.isSelected());
		genSet.setIsDisjoint(cbxDisjoint.isSelected());
		genSet.setName(textField.getText());	
		
		diagramManager.updateOLEDFromModification(genSet, redesign);
	}
	
}
