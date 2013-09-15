package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.MRBSAntiPattern;
import br.ufes.inf.nemo.antipattern.ac.ACAntiPattern;
import br.ufes.inf.nemo.antipattern.ia.IAAntiPattern;
import br.ufes.inf.nemo.antipattern.rbos.RBOSAntiPattern;
import br.ufes.inf.nemo.antipattern.rs.RSAntiPattern;
import br.ufes.inf.nemo.antipattern.rwor.RWORAntiPattern;
import br.ufes.inf.nemo.antipattern.rwrt.RWRTAntiPattern;
import br.ufes.inf.nemo.antipattern.ssr.SSRAntiPattern;
import br.ufes.inf.nemo.antipattern.str.STRAntiPattern;
import br.ufes.inf.nemo.antipattern.tri.TRIAntiPattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.AntiPatternList;
import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.ModelTree;

/**
 * @author John Guerson
 */

public class AntiPatternListDialog extends JDialog {

	private static final long serialVersionUID = 1L;
		
	private AppFrame frame;
	
	private final JPanel contentPanel = new JPanel();	
	private JCheckBox cbxSTR;
	private JCheckBox cbxIA;
	private JCheckBox cbxRWOR;
	private JCheckBox cbxRBOS;
	private JCheckBox cbxAC;
	private JCheckBox cbxRS;
	private JCheckBox cbxRWRT;
	private JCheckBox cbxTRI;
	private JButton identifyButton;
	private JButton cancelButton;
	private JCheckBox cbxMRBS;
	private JCheckBox cbxSSR;
		
	/** 
	 * Check if AntiPattern is selected.
	 */
	public Boolean ACisSelected() { return cbxAC.isSelected(); }	
	public Boolean STRisSelected() { return cbxSTR.isSelected(); }
	public Boolean RBOSisSelected() { return cbxRBOS.isSelected(); }
	public Boolean IAisSelected() { return cbxIA.isSelected(); }
	public Boolean RSisSelected() { return cbxRS.isSelected(); }
	public Boolean RWORisSelected() { return cbxRWOR.isSelected(); }
	public Boolean RWRTisSelected() { return cbxRWRT.isSelected(); }
	public Boolean TRIisSelected() { return cbxTRI.isSelected(); }
	public Boolean MRBSisSelected() { return cbxMRBS.isSelected(); }
	public Boolean SSRisSelected() { return cbxSSR.isSelected(); }
		
	/**
	 * Open the Dialog.
	 */
	public static void  open (AppFrame parent)
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
	public AntiPatternListDialog(AppFrame frame) 
	{
		super(frame);
		
		this.frame = frame;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/antipattern.png")));
		setTitle("Detect AntiPatterns");
		setBounds(100, 100, 332, 377);
		
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
		
		cbxRWRT = new JCheckBox("RWRT : Relator With Rigid Types");
		cbxRWRT.setBackground(UIManager.getColor("Panel.background"));
		
		cbxTRI = new JCheckBox("TRI : Twin Relator Instances");
		cbxTRI.setBackground(UIManager.getColor("Panel.background"));
		
		cbxMRBS = new JCheckBox("MRBS : Multiple Relators Between Sortals");
		cbxMRBS.setEnabled(false);
		cbxMRBS.setBackground(UIManager.getColor("Panel.background"));
		
		cbxSSR = new JCheckBox("SSR : Super and Sub Relations");
		cbxSSR.setEnabled(false);
		cbxSSR.setBackground(UIManager.getColor("Panel.background"));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxSSR)
						.addComponent(cbxMRBS)
						.addComponent(cbxSTR, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxIA, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxRWOR, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxRBOS, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxAC, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxRS, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addComponent(cbxRWRT)
						.addComponent(cbxTRI, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(17)
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRWRT)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxTRI)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxMRBS)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxSSR)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setPreferredSize(new Dimension(60, 70));
		
		createIdentifyButton(buttonPane);
		createEnableallButton(buttonPane);
		createDisableallButton(buttonPane);
		createCancelButton(buttonPane);		
		JButton btnEnableall = new JButton("Enable All");
		
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
       			if (!RWRTisSelected()) cbxRWRT.setSelected(true);
       			if (!TRIisSelected()) cbxTRI.setSelected(true);
       			if (!MRBSisSelected()) cbxMRBS.setSelected(false);
       			if (!SSRisSelected()) cbxSSR.setSelected(false);
       		}
       	});
		JButton btnDisableall = new JButton("Disable All");
		
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
       			if (RWRTisSelected()) cbxRWRT.setSelected(false);
       			if (TRIisSelected()) cbxTRI.setSelected(false);
       			if (MRBSisSelected()) cbxMRBS.setSelected(false);
       			if (SSRisSelected()) cbxSSR.setSelected(false);
       		}
       	});
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
					.addGap(69)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnEnableall, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(identifyButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDisableall, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDisableall)
						.addComponent(btnEnableall))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton)
						.addComponent(identifyButton))
					.addContainerGap())
		);
		buttonPane.setLayout(gl_buttonPane);
	}
	
	/**
	 * Create a Identify Button.
	 * @param buttonPane
	 */
	public void createIdentifyButton(JPanel buttonPane)
	{
		identifyButton = new JButton("Detect");
		
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
	}
	
	/**
	 * Create a DisableAll Button.
	 * @param buttonPane
	 */
	public void createDisableallButton (JPanel buttonPane)
	{
	}
	
	/**
	 * Create a Cancel Button.
	 * 
	 * @param buttonPane
	 */
	public void createCancelButton (JPanel buttonPane)
	{
		cancelButton = new JButton("Cancel");
		
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
		
		ArrayList<ACAntiPattern> acListModel = new ArrayList<ACAntiPattern>();
		ArrayList<RSAntiPattern> rsListModel = new ArrayList<RSAntiPattern>();
		ArrayList<RBOSAntiPattern> rbosListModel = new ArrayList<RBOSAntiPattern>();				
		ArrayList<STRAntiPattern> strListModel = new ArrayList<STRAntiPattern>();
		ArrayList<RWORAntiPattern> rworListModel = new ArrayList<RWORAntiPattern>();				
		ArrayList<IAAntiPattern> iaListModel = new ArrayList<IAAntiPattern>();
		ArrayList<RWRTAntiPattern> rwrtListModel = new ArrayList<RWRTAntiPattern>();
		ArrayList<TRIAntiPattern> triListModel = new ArrayList<TRIAntiPattern>();
		ArrayList<MRBSAntiPattern> mrbsListModel = new ArrayList<MRBSAntiPattern>();
		ArrayList<SSRAntiPattern> ssrListModel = new ArrayList<SSRAntiPattern>();
		
		DiagramManager.autoCompleteSelection(OntoUMLParser.NO_HIERARCHY,frame.getDiagramManager().getCurrentProject());
		
		OntoUMLParser parser = ModelTree.getParserFor(frame.getDiagramManager().getCurrentProject());
		
		if (parser.getElements() == null) return;
		
		if (ACisSelected()) 
		{			
			for(ACAntiPattern ac: AntiPatternIdentifier.identifyAC(parser)) 
			{				
				acListModel.add(ac);
			}
		}
		if (RSisSelected())	
		{			
			for(RSAntiPattern rs: AntiPatternIdentifier.identifyRS(parser)) 
			{				
				rsListModel.add(rs);
			}
		}
		if (RBOSisSelected()) 
		{			
			for(RBOSAntiPattern rbos: AntiPatternIdentifier.identifyRBOS(parser)) 
			{				
				rbosListModel.add(rbos);
			}		
		}				
		if (STRisSelected()) 
		{			
			for(STRAntiPattern str: AntiPatternIdentifier.identifySTR(parser)) 
			{
				strListModel.add(str);
			}	
		}		
		if (RWORisSelected()) 
		{			
			for(RWORAntiPattern rwor: AntiPatternIdentifier.identifyRWOR(parser)) 
			{
				rworListModel.add(rwor);
			}	
		}
		if (IAisSelected()) 
		{			
			for(IAAntiPattern ia: AntiPatternIdentifier.identifyIA(parser)) 
			{
				iaListModel.add(ia);
			}	
		}
		if (RWRTisSelected()) 
		{			
			for(RWRTAntiPattern rwrt: AntiPatternIdentifier.identifyRWRT(parser)) 
			{
				rwrtListModel.add(rwrt);
			}	
		}
		if (TRIisSelected()) 
		{			
			for(TRIAntiPattern tri: AntiPatternIdentifier.identifyTRI(parser)) 
			{
				triListModel.add(tri);
			}	
		}
		if (MRBSisSelected()) 
		{		
			for(MRBSAntiPattern mrbs: AntiPatternIdentifier.identifyMRBS(parser)) 
			{
				mrbsListModel.add(mrbs);
			}	
		}
		if (SSRisSelected()) 
		{			
			for(SSRAntiPattern ssr: AntiPatternIdentifier.identifySSR(parser)) 
			{
				ssrListModel.add(ssr);
			}	
		}
		
		String result = new String();
		
		if (acListModel.size()>0) result += "AC AntiPattern : "+acListModel.size()+" items found.\n";
		if (rsListModel.size()>0) result += "RS AntiPattern : "+rsListModel.size()+" items found.\n";
		if (rbosListModel.size()>0) result += "RBOS AntiPattern : "+rbosListModel.size()+" items found.\n";
		if (strListModel.size()>0) result += "STR AntiPattern : "+strListModel.size()+" items found.\n";
		if (rworListModel.size()>0) result += "RWOR AntiPattern : "+rworListModel.size()+" items found.\n";
		if (iaListModel.size()>0) result += "IA AntiPattern : "+iaListModel.size()+" items found.\n";
		if (rwrtListModel.size()>0) result += "RWRT AntiPattern : "+rwrtListModel.size()+" items found.\n";
		if (triListModel.size()>0) result += "TRI AntiPattern : "+triListModel.size()+" items found.\n";
		if (mrbsListModel.size()>0) result += "MRBS AntiPattern : "+mrbsListModel.size()+" items found.\n";
		if (ssrListModel.size()>0) result += "SSR AntiPattern : "+ssrListModel.size()+" items found.\n";
		
		if (result.isEmpty()) JOptionPane.showMessageDialog(this,"No antipatterns found.","Detecting AntiPatterns",JOptionPane.INFORMATION_MESSAGE); 
		else 
		{
			JOptionPane.showMessageDialog(this,result,"Detecting AntiPatterns",JOptionPane.INFORMATION_MESSAGE);			
			
			AntiPatternList antipatternList = new AntiPatternList
			(
					acListModel,rbosListModel,strListModel,rsListModel,rworListModel,iaListModel,ssrListModel,rwrtListModel,triListModel
			);

			ModelTree.setAntiPatternListFor(frame.getDiagramManager().getCurrentProject(),antipatternList);

			frame.getDiagramManager().openAntiPatternManager();
		}		 
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Managing AntiPattern",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		dispose();		
	}
}
