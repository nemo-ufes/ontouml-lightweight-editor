package br.ufes.inf.nemo.move.panel.antipattern;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.ontouml.antipattern.ACAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.ontouml.antipattern.IAAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.RBOSAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.RSAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.RWORAntiPattern;
import br.ufes.inf.nemo.ontouml.antipattern.STRAntiPattern;

/**
 * @author John Guerson
 */

public class AntiPatternDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
	private JCheckBox cbxSTR;
	private JCheckBox cbxIA;
	private JCheckBox cbxRWOR;
	private JCheckBox cbxRBOS;
	private JCheckBox cbxAC;
	private JCheckBox cbxRS;
	
	private TheFrame frame;
	
	/** 
	 * Check if AntiPattern is selected.
	 */
	public Boolean ACisSelected() { return cbxAC.isSelected(); }	
	public Boolean STRisSelected() { return cbxSTR.isSelected(); }
	public Boolean RBOSisSelected() { return cbxRBOS.isSelected(); }
	public Boolean IAisSelected() { return cbxIA.isSelected(); }
	public Boolean RSisSelected() { return cbxRS.isSelected(); }
	public Boolean RWORisSelected() { return cbxRWOR.isSelected(); }
	
	/**
	 * Open the Dialog.
	 */
	public static void  open (TheFrame parent)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			AntiPatternDialog dialog = new AntiPatternDialog(parent);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(parent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the Dialog
	 * 
	 * @param parent
	 */
	public AntiPatternDialog(TheFrame parent)
	{
		this();
		frame = parent;
	}
	
	/**
	 * Create the dialog.
	 */
	public AntiPatternDialog() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(AntiPatternDialog.class.getResource("/resources/br/ufes/inf/nemo/move/search.png")));
		setTitle("Identify AntiPattern");
		setBounds(100, 100, 332, 256);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		cbxSTR = new JCheckBox("STR : Self-Type Relationship");
		cbxSTR.setBackground(UIManager.getColor("Panel.background"));
		
		cbxIA = new JCheckBox("IA : Imprecise Abstraction");
		cbxIA.setBackground(UIManager.getColor("Panel.background"));
		
		cbxRWOR = new JCheckBox("RWOR : Relator With Overlapping Roles");
		cbxRWOR.setBackground(UIManager.getColor("Panel.background"));
		
		cbxRBOS = new JCheckBox("RBOS : Relation Between Overlapping SubTypes");
		cbxRBOS.setBackground(UIManager.getColor("Panel.background"));
		
		cbxAC = new JCheckBox("AC : Association Cycle");
		cbxAC.setBackground(UIManager.getColor("Panel.background"));
		
		cbxRS = new JCheckBox("RS : Relation Specialization");
		cbxRS.setBackground(UIManager.getColor("Panel.background"));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxRS, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxAC, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxRBOS, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxRWOR, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxSTR, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxIA, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(cbxSTR)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxIA)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRWOR)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRBOS)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxAC)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRS)
					.addContainerGap(33, Short.MAX_VALUE))
		);		
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		createIdentifyButton(buttonPane);
		createEnableallButton(buttonPane);
		createDisableallButton(buttonPane);
		createCancelButton(buttonPane);		
	}
	
	/**
	 * Create a Identify Button.
	 * @param buttonPane
	 */
	public void createIdentifyButton(JPanel buttonPane)
	{
		JButton identifyButton = new JButton("Identify");
		buttonPane.add(identifyButton);		
		identifyButton.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			IdentifyButtonActionPerformed(event);
       		}
       	});
	}
	
	/**
	 * Create a EnableAll Button.
	 * @param buttonPane
	 */
	public void createEnableallButton (JPanel buttonPane)
	{
		JButton btnEnableall = new JButton("EnableAll");
		buttonPane.add(btnEnableall);
				
		btnEnableall.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if (!ACisSelected()) cbxAC.setSelected(true);
       			if (!STRisSelected()) cbxSTR.setSelected(true);
       			if (!RBOSisSelected()) cbxRBOS.setSelected(true);
       			if (!IAisSelected()) cbxIA.setSelected(true);
       			if (!RSisSelected()) cbxRS.setSelected(true);
       			if (!RWORisSelected()) cbxRWOR.setSelected(true);
       		}
       	});
	}
	
	/**
	 * Create a DisableAll Button.
	 * @param buttonPane
	 */
	public void createDisableallButton (JPanel buttonPane)
	{
		JButton btnDisableall = new JButton("DisableAll");
		buttonPane.add(btnDisableall);
		
		btnDisableall.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if (ACisSelected()) cbxAC.setSelected(false);
       			if (STRisSelected()) cbxSTR.setSelected(false);
       			if (RBOSisSelected()) cbxRBOS.setSelected(false);
       			if (IAisSelected()) cbxIA.setSelected(false);
       			if (RSisSelected()) cbxRS.setSelected(false);
       			if (RWORisSelected()) cbxRWOR.setSelected(false);
       		}
       	});
	}
	
	/**
	 * Create a Cancel Button.
	 * 
	 * @param buttonPane
	 */
	public void createCancelButton (JPanel buttonPane)
	{
		JButton cancelButton = new JButton("Cancel");		
		buttonPane.add(cancelButton);		
		
		cancelButton.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			dispose();
       		}
       	});
	}
	
	/**
	 * Identifying AntiPatterns...
	 * 
	 * @param event
	 */
	public void IdentifyButtonActionPerformed(ActionEvent event)
	{
		try{
		
		ArrayList<ACAntiPattern> aclist = new ArrayList<ACAntiPattern>();
		ArrayList<RSAntiPattern> rslist = new ArrayList<RSAntiPattern>();
		ArrayList<RBOSAntiPattern> rboslist = new ArrayList<RBOSAntiPattern>();
		ArrayList<RWORAntiPattern> rworlist = new ArrayList<RWORAntiPattern>();
		ArrayList<IAAntiPattern> ialist = new ArrayList<IAAntiPattern>();
		ArrayList<STRAntiPattern> strlist = new ArrayList<STRAntiPattern>();
		
		RefOntoUML.Package refmodel = frame.getTheModelBar().getOntoUMLModel();
		
		if (ACisSelected()) aclist = AntiPatternIdentifier.identifyAC(refmodel);		
		if (RSisSelected())	rslist = AntiPatternIdentifier.identifyRS(refmodel);		
		if (RBOSisSelected()) rboslist = AntiPatternIdentifier.identifyRBOS(refmodel);		
		if (RWORisSelected()) rworlist = AntiPatternIdentifier.identifyRWOR(refmodel);		
		if (IAisSelected()) ialist = AntiPatternIdentifier.identifyIA(refmodel);		
		if (STRisSelected()) strlist = AntiPatternIdentifier.identifySTR(refmodel);
		
		String result = new String();
		
		if (aclist.size()>0) result += "AC AntiPattern : "+aclist.size()+" items found.\n";
		if (rslist.size()>0) result += "RS AntiPattern : "+rslist.size()+" items found.\n";
		if (rboslist.size()>0) result += "RBOS AntiPattern : "+rboslist.size()+" items found.\n";
		if (rworlist.size()>0) result += "RWOR AntiPattern : "+rworlist.size()+" items found.\n";
		if (ialist.size()>0) result += "IA AntiPattern : "+ialist.size()+" items found.\n";
		if (strlist.size()>0) result += "STR AntiPattern : "+strlist.size()+" items found.\n";
		
		if (result.isEmpty()) JOptionPane.showMessageDialog(this,"No AntiPatterns Found.","Identifying AntiPatterns....",JOptionPane.INFORMATION_MESSAGE); 
		else 
		{			
			JOptionPane.showMessageDialog(this,result,"Identifying AntiPatterns....",JOptionPane.INFORMATION_MESSAGE);			
			frame.getAntiPatternPanel().Clear();
			
			if (aclist.size()>0) { frame.getAntiPatternPanel().createACTabPanel(); frame.getAntiPatternPanel().initializeACTabPanel(aclist);}
			if (rslist.size()>0) { frame.getAntiPatternPanel().createRSTabPanel(); frame.getAntiPatternPanel().initializeRSTabPanel(rslist);}
			if (rboslist.size()>0) { frame.getAntiPatternPanel().createRBOSTabPanel(); frame.getAntiPatternPanel().initializeRBOSTabPanel(rboslist);}
			if (strlist.size()>0) { frame.getAntiPatternPanel().createSTRTabPanel(); frame.getAntiPatternPanel().initializeSTRTabPanel(strlist); }			
			if (rworlist.size()>0) { frame.getAntiPatternPanel().createRWORTabPanel();  frame.getAntiPatternPanel().initializeRWORTabPanel(rworlist); }
			if (ialist.size()>0) { frame.getAntiPatternPanel().createIATabPanel();  frame.getAntiPatternPanel().initializeIATabPanel(ialist); }
			
		}		 
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		dispose();
	}
}
