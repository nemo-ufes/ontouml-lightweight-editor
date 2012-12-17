package br.ufes.inf.nemo.move.ui.dialog;

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

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.move.mvc.model.antipattern.ACAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.IAAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RBOSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RWORAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.STRAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.list.AntiPatternListModel;
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

public class AntiPatternListDialog extends JDialog {

	private static final long serialVersionUID = 1L;
		
	private TheFrame frame;
	
	private final JPanel contentPanel = new JPanel();	
	private JCheckBox cbxSTR;
	private JCheckBox cbxIA;
	private JCheckBox cbxRWOR;
	private JCheckBox cbxRBOS;
	private JCheckBox cbxAC;
	private JCheckBox cbxRS;
	private JButton identifyButton;
		
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
			
			AntiPatternListDialog dialog = new AntiPatternListDialog(parent);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(parent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Create the dialog.
	 */
	public AntiPatternListDialog(TheFrame frame) 
	{
		super(frame);
		
		this.frame = frame;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AntiPatternListDialog.class.getResource("/resources/icon/search-24x24.png")));
		setTitle("Search AntiPatterns");
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
		identifyButton = new JButton("Search");
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
		JButton btnEnableall = new JButton("Enable All");
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
		JButton btnDisableall = new JButton("Disable All");
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
		
		ArrayList<ACAntiPatternModel> acListModel = new ArrayList<ACAntiPatternModel>();
		ArrayList<RSAntiPatternModel> rsListModel = new ArrayList<RSAntiPatternModel>();
		ArrayList<RBOSAntiPatternModel> rbosListModel = new ArrayList<RBOSAntiPatternModel>();				
		ArrayList<STRAntiPatternModel> strListModel = new ArrayList<STRAntiPatternModel>();
		ArrayList<RWORAntiPatternModel> rworListModel = new ArrayList<RWORAntiPatternModel>();				
		ArrayList<IAAntiPatternModel> iaListModel = new ArrayList<IAAntiPatternModel>();
		
		frame.getManager().doAutoSelectionCompletion(OntoUMLParser.NO_HIERARCHY);
		
		OntoUMLParser parser = frame.getManager().getOntoUMLModel().getOntoUMLParser();
		if (parser.getElements() == null) return;
		
		if (ACisSelected()) 
		{
			int id=1;
			for(ACAntiPattern ac: AntiPatternIdentifier.identifyAC(parser)) 
			{
				ACAntiPatternModel acModel = new ACAntiPatternModel(ac);
				acModel.setId(id++);				
				
				acListModel.add(acModel);
			}
		}
		if (RSisSelected())	
		{
			int id=1;
			for(RSAntiPattern rs: AntiPatternIdentifier.identifyRS(parser)) 
			{
				RSAntiPatternModel rsModel = new RSAntiPatternModel(rs);
				rsModel.setId(id++);	
				
				rsListModel.add(rsModel);
			}
		}
		if (RBOSisSelected()) 
		{
			int id=1;
			for(RBOSAntiPattern rbos: AntiPatternIdentifier.identifyRBOS(parser)) 
			{
				RBOSAntiPatternModel rbosModel = new RBOSAntiPatternModel(rbos);
				rbosModel.setId(id++);	
				
				rbosListModel.add(rbosModel);
			}		
		}				
		if (STRisSelected()) 
		{
			int id=1;
			for(STRAntiPattern str: AntiPatternIdentifier.identifySTR(parser)) 
			{
				STRAntiPatternModel strModel = new STRAntiPatternModel(str);
				strModel.setId(id++);	
				
				strListModel.add(strModel);
			}	
		}		
		if (RWORisSelected()) 
		{
			int id=1;
			for(RWORAntiPattern rwor: AntiPatternIdentifier.identifyRWOR(parser)) 
			{
				RWORAntiPatternModel rworModel = new RWORAntiPatternModel(rwor);
				rworModel.setId(id++);	
			
				rworListModel.add(rworModel);
			}	
		}
		if (IAisSelected()) 
		{
			int id=1;
			for(IAAntiPattern ia: AntiPatternIdentifier.identifyIA(parser)) 
			{
				IAAntiPatternModel iaModel = new IAAntiPatternModel(ia);
				iaModel.setId(id++);	
			
				iaListModel.add(iaModel);
			}	
		}
		
		
		String result = new String();
		
		if (acListModel.size()>0) result += "AC AntiPattern : "+acListModel.size()+" items found.\n";
		if (rsListModel.size()>0) result += "RS AntiPattern : "+rsListModel.size()+" items found.\n";
		if (rbosListModel.size()>0) result += "RBOS AntiPattern : "+rbosListModel.size()+" items found.\n";
		if (strListModel.size()>0) result += "STR AntiPattern : "+strListModel.size()+" items found.\n";
		if (rworListModel.size()>0) result += "RWOR AntiPattern : "+rworListModel.size()+" items found.\n";
		if (iaListModel.size()>0) result += "IA AntiPattern : "+iaListModel.size()+" items found.\n";
				
		if (result.isEmpty()) JOptionPane.showMessageDialog(this,"No AntiPatterns Found.","Search AntiPatterns",JOptionPane.INFORMATION_MESSAGE); 
		else 
		{			
			JOptionPane.showMessageDialog(this,result,"Search AntiPatterns",JOptionPane.INFORMATION_MESSAGE);			
			
			AntiPatternListModel antipatternListModel = new AntiPatternListModel
			(
					acListModel,rbosListModel,strListModel,rsListModel,rworListModel,iaListModel
			);
			
			frame.getManager().setAntiPatternListModel(antipatternListModel);			
			frame.ShowAntiPatternView();
		}		 
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		dispose();		
	}
}
