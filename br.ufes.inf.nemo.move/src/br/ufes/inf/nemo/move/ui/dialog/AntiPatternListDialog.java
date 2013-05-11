package br.ufes.inf.nemo.move.ui.dialog;

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

import br.ufes.inf.nemo.antipattern.ACAntiPattern;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.IAAntiPattern;
import br.ufes.inf.nemo.antipattern.MRBSAntiPattern;
import br.ufes.inf.nemo.antipattern.RBOSAntiPattern;
import br.ufes.inf.nemo.antipattern.RSAntiPattern;
import br.ufes.inf.nemo.antipattern.RWORAntiPattern;
import br.ufes.inf.nemo.antipattern.SSRAntiPattern;
import br.ufes.inf.nemo.antipattern.STRAntiPattern;
import br.ufes.inf.nemo.antipattern.rwrt.RWRTAntiPattern;
import br.ufes.inf.nemo.antipattern.tri.TRIAntiPattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.move.mvc.model.ACAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.AntiPatternListModel;
import br.ufes.inf.nemo.move.mvc.model.IAAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.MRBSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.RBOSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.RSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.RWORAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.RWRTAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.SSRAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.STRAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.model.TRIAntiPatternModel;
import br.ufes.inf.nemo.move.ui.TheFrame;


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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AntiPatternListDialog.class.getResource("/resources/icon/search-red-24x24.png")));
		setTitle("Run AntiPatterns Manager");
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
       			if (!MRBSisSelected()) cbxMRBS.setSelected(true);
       			if (!SSRisSelected()) cbxSSR.setSelected(true);
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
		
		ArrayList<ACAntiPatternModel> acListModel = new ArrayList<ACAntiPatternModel>();
		ArrayList<RSAntiPatternModel> rsListModel = new ArrayList<RSAntiPatternModel>();
		ArrayList<RBOSAntiPatternModel> rbosListModel = new ArrayList<RBOSAntiPatternModel>();				
		ArrayList<STRAntiPatternModel> strListModel = new ArrayList<STRAntiPatternModel>();
		ArrayList<RWORAntiPatternModel> rworListModel = new ArrayList<RWORAntiPatternModel>();				
		ArrayList<IAAntiPatternModel> iaListModel = new ArrayList<IAAntiPatternModel>();
		ArrayList<RWRTAntiPatternModel> rwrtListModel = new ArrayList<RWRTAntiPatternModel>();
		ArrayList<TRIAntiPatternModel> triListModel = new ArrayList<TRIAntiPatternModel>();
		ArrayList<MRBSAntiPatternModel> mrbsListModel = new ArrayList<MRBSAntiPatternModel>();
		ArrayList<SSRAntiPatternModel> ssrListModel = new ArrayList<SSRAntiPatternModel>();
		
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
		if (RWRTisSelected()) 
		{
			int id=1;
			for(RWRTAntiPattern rwrt: AntiPatternIdentifier.identifyRWRT(parser)) 
			{
				RWRTAntiPatternModel rwrtModel = new RWRTAntiPatternModel(rwrt);
				rwrtModel.setId(id++);	
			
				rwrtListModel.add(rwrtModel);
			}	
		}
		if (TRIisSelected()) 
		{
			int id=1;
			for(TRIAntiPattern tri: AntiPatternIdentifier.identifyTRI(parser)) 
			{
				TRIAntiPatternModel triModel = new TRIAntiPatternModel(tri);
				triModel.setId(id++);	
			
				triListModel.add(triModel);
			}	
		}
		if (MRBSisSelected()) 
		{
			int id=1;
			for(MRBSAntiPattern mrbs: AntiPatternIdentifier.identifyMRBS(parser)) 
			{
				MRBSAntiPatternModel mrbsModel = new MRBSAntiPatternModel(mrbs);
				mrbsModel.setId(id++);	
			
				mrbsListModel.add(mrbsModel);
			}	
		}
		if (SSRisSelected()) 
		{
			int id=1;
			for(SSRAntiPattern ssr: AntiPatternIdentifier.identifySSR(parser)) 
			{
				SSRAntiPatternModel ssrModel = new SSRAntiPatternModel(ssr);
				ssrModel.setId(id++);	
			
				ssrListModel.add(ssrModel);
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
		
		if (result.isEmpty()) JOptionPane.showMessageDialog(this,"No antipatterns found.","Run AntiPatterns Manager",JOptionPane.INFORMATION_MESSAGE); 
		else 
		{			
			JOptionPane.showMessageDialog(this,result,"Run AntiPatterns Manager",JOptionPane.INFORMATION_MESSAGE);			
			
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
