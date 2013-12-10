package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsAntipattern;
import br.ufes.inf.nemo.antipattern.imppart.ImpPartAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompAntipattern;
import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.AntiPatternList;
import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import javax.swing.JLabel;

/**
 * @author John Guerson
 */

public class AntiPatternListDialog extends JDialog {

	private static final long serialVersionUID = 1L;
		
	private AppFrame frame;
	
	private final JPanel contentPanel = new JPanel();	
	
	private JCheckBox cbxAssCyc;
	private JCheckBox cbxBinOver;
	private JCheckBox cbxDepPhase;
	private JCheckBox cbxFreeRole;
	private JCheckBox cbxGSRig;
	private JCheckBox cbxHetColl;
	private JCheckBox cbxHomoFunc;
	private JCheckBox cbxImpAbs;
	private JCheckBox cbxImpPart;
	private JCheckBox cbxMixIden;
	private JCheckBox cbxMixRig;
	private JCheckBox cbxMultiDep;
	private JCheckBox cbxRelComp;
	private JCheckBox cbxRelOver;
	private JCheckBox cbxRelRig;
	private JCheckBox cbxRelSpec;
	private JCheckBox cbxRepRel;
	private JCheckBox cbxUndefFormal;
	private JCheckBox cbxUndefPhase;
	private JCheckBox cbxWholeOver;
	
	private JButton identifyButton;
	private JButton cancelButton;
		
	/** 
	 * Check if AntiPattern is selected.
	 */
	public Boolean AssCycisSelected() { return cbxAssCyc.isSelected(); }
	public Boolean BinOverisSelected() { return cbxBinOver.isSelected(); }
	public Boolean DepPhaseisSelected() { return cbxDepPhase.isSelected(); }
	public Boolean FreeRoleisSelected() { return cbxFreeRole.isSelected(); }
	public Boolean GSRigisSelected() { return cbxGSRig.isSelected(); }
	public Boolean HetCollisSelected() { return cbxHetColl.isSelected(); }
	public Boolean HomoFuncisSelected() { return cbxHomoFunc.isSelected(); }
	public Boolean ImpAbsisSelected() { return cbxImpAbs.isSelected(); }
	public Boolean ImpPartisSelected() { return cbxImpPart.isSelected(); }
	public Boolean MixIdenisSelected() { return cbxMixIden.isSelected(); }
	public Boolean MixRigisSelected() { return cbxMixRig.isSelected(); }
	public Boolean MultiDepisSelected() { return cbxMultiDep.isSelected(); }
	public Boolean RelCompisSelected() { return cbxRelComp.isSelected(); }
	public Boolean RelOverisSelected() { return cbxRelOver.isSelected(); }
	public Boolean RelRigisSelected() { return cbxRelRig.isSelected(); }
	public Boolean RelSpecisSelected() { return cbxRelSpec.isSelected(); }
	public Boolean RepRelisSelected() { return cbxRepRel.isSelected(); }
	public Boolean UndefFormalisSelected() { return cbxUndefFormal.isSelected(); }
	public Boolean UndefPhaseisSelected() { return cbxUndefPhase.isSelected(); }
	public Boolean WholeOverisSelected() { return cbxWholeOver.isSelected(); }
	
		
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
		setTitle("Anti-Pattern Detection");
		setBounds(100, 100, 332, 629);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		cbxAssCyc = new JCheckBox(AssCycAntipattern.getAntipatternInfo().getAcronym()+" : "+AssCycAntipattern.getAntipatternInfo().getName());
		cbxAssCyc.setBackground(UIManager.getColor("Panel.background"));
		
		cbxBinOver = new JCheckBox(BinOverAntipattern.getAntipatternInfo().getAcronym()+" : "+BinOverAntipattern.getAntipatternInfo().getName());
		cbxBinOver.setBackground(UIManager.getColor("Panel.background"));

		cbxDepPhase = new JCheckBox(DepPhaseAntipattern.getAntipatternInfo().getAcronym()+" : "+DepPhaseAntipattern.getAntipatternInfo().getName());
		cbxDepPhase.setBackground(UIManager.getColor("Panel.background"));
		
		cbxFreeRole = new JCheckBox(FreeRoleAntipattern.getAntipatternInfo().getAcronym()+" : "+FreeRoleAntipattern.getAntipatternInfo().getName());
		cbxFreeRole.setBackground(UIManager.getColor("Panel.background"));

		cbxGSRig = new JCheckBox(GSRigAntipattern.getAntipatternInfo().getAcronym()+" : "+GSRigAntipattern.getAntipatternInfo().getName());
		cbxGSRig.setBackground(UIManager.getColor("Panel.background"));

		cbxHetColl = new JCheckBox(HetCollAntipattern.getAntipatternInfo().getAcronym()+" : "+HetCollAntipattern.getAntipatternInfo().getName());
		cbxHetColl.setBackground(UIManager.getColor("Panel.background"));

		cbxHomoFunc = new JCheckBox(HomoFuncAntipattern.getAntipatternInfo().getAcronym()+" : "+HomoFuncAntipattern.getAntipatternInfo().getName());
		cbxHomoFunc.setBackground(UIManager.getColor("Panel.background"));

		cbxImpAbs = new JCheckBox(ImpAbsAntipattern.getAntipatternInfo().getAcronym()+" : "+ImpAbsAntipattern.getAntipatternInfo().getName());
		cbxImpAbs.setBackground(UIManager.getColor("Panel.background"));
		
		cbxImpPart = new JCheckBox(ImpPartAntipattern.getAntipatternInfo().getAcronym()+" : "+ImpPartAntipattern.getAntipatternInfo().getName());
		cbxImpPart.setBackground(UIManager.getColor("Panel.background"));

		cbxMixIden = new JCheckBox(MixIdenAntipattern.getAntipatternInfo().getAcronym()+" : "+MixIdenAntipattern.getAntipatternInfo().getName());
		cbxMixIden.setBackground(UIManager.getColor("Panel.background"));

		cbxMixRig = new JCheckBox(MixRigAntipattern.getAntipatternInfo().getAcronym()+" : "+MixRigAntipattern.getAntipatternInfo().getName());
		cbxMixRig.setBackground(UIManager.getColor("Panel.background"));

		cbxMultiDep = new JCheckBox(MultiDepAntipattern.getAntipatternInfo().getAcronym()+" : "+MultiDepAntipattern.getAntipatternInfo().getName());
		cbxMultiDep.setBackground(UIManager.getColor("Panel.background"));

		cbxRelComp = new JCheckBox(RelCompAntipattern.getAntipatternInfo().getAcronym()+" : "+RelCompAntipattern.getAntipatternInfo().getName());
		cbxRelComp.setBackground(UIManager.getColor("Panel.background"));

		cbxRelOver = new JCheckBox(RelOverAntipattern.getAntipatternInfo().getAcronym()+" : "+RelOverAntipattern.getAntipatternInfo().getName());
		cbxRelOver.setBackground(UIManager.getColor("Panel.background"));

		cbxRelRig = new JCheckBox(RelRigAntipattern.getAntipatternInfo().getAcronym()+" : "+RelRigAntipattern.getAntipatternInfo().getName());
		cbxRelRig.setBackground(UIManager.getColor("Panel.background"));

		cbxRelSpec = new JCheckBox(RelSpecAntipattern.getAntipatternInfo().getAcronym()+" : "+RelSpecAntipattern.getAntipatternInfo().getName());
		cbxRelSpec.setBackground(UIManager.getColor("Panel.background"));

		cbxRepRel = new JCheckBox(RepRelAntipattern.getAntipatternInfo().getAcronym()+" : "+RepRelAntipattern.getAntipatternInfo().getName());
		cbxRepRel.setBackground(UIManager.getColor("Panel.background"));
		
		cbxUndefFormal = new JCheckBox(UndefFormalAntipattern.getAntipatternInfo().getAcronym()+" : "+UndefFormalAntipattern.getAntipatternInfo().getName());
		cbxUndefFormal.setBackground(UIManager.getColor("Panel.background"));
		
		cbxUndefPhase = new JCheckBox(UndefPhaseAntipattern.getAntipatternInfo().getAcronym()+" : "+UndefPhaseAntipattern.getAntipatternInfo().getName());
		cbxUndefPhase.setBackground(UIManager.getColor("Panel.background"));
		
		cbxWholeOver = new JCheckBox(WholeOverAntipattern.getAntipatternInfo().getAcronym()+" : "+WholeOverAntipattern.getAntipatternInfo().getName());
		cbxWholeOver.setBackground(UIManager.getColor("Panel.background"));
		
		JLabel lblChooseWhichAntipattern = new JLabel("Choose which anti-pattern do you want to detect:");
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxRelOver, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxRelRig, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxWholeOver, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxUndefPhase, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxUndefFormal, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxRepRel, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxRelSpec, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxRelComp, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxMultiDep, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxMixRig, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxMixIden, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxImpPart, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxImpAbs, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxHomoFunc, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxHetColl, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxGSRig, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxFreeRole, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxDepPhase, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxBinOver, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(cbxAssCyc, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
						.addComponent(lblChooseWhichAntipattern, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblChooseWhichAntipattern)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbxAssCyc)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxBinOver)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxDepPhase)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxFreeRole)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxGSRig)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxHetColl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxHomoFunc)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxImpAbs)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxImpPart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxMixIden)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxMixRig)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxMultiDep)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRelComp)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRelOver)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRelRig)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRelSpec)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxRepRel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxUndefFormal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxUndefPhase)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cbxWholeOver)
					.addGap(0, 0, Short.MAX_VALUE))
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
       			if (!AssCycisSelected()) cbxAssCyc.setSelected(true);
       			if (!BinOverisSelected()) cbxBinOver.setSelected(true);
       			if (!DepPhaseisSelected()) cbxDepPhase.setSelected(true);
       			if (!FreeRoleisSelected()) cbxFreeRole.setSelected(true);
       			if (!GSRigisSelected()) cbxGSRig.setSelected(true);
       			if (!HetCollisSelected()) cbxHetColl.setSelected(true);
       			if (!HomoFuncisSelected()) cbxHomoFunc.setSelected(true);
       			if (!ImpAbsisSelected()) cbxImpAbs.setSelected(true);
       			if (!ImpPartisSelected()) cbxImpPart.setSelected(true);
       			if (!MixIdenisSelected()) cbxMixIden.setSelected(true);
       			if (!MixRigisSelected()) cbxMixRig.setSelected(true);
       			if (!MultiDepisSelected()) cbxMultiDep.setSelected(true);
       			if (!RelCompisSelected()) cbxRelComp.setSelected(true);
       			if (!RelOverisSelected()) cbxRelOver.setSelected(true);
       			if (!RelRigisSelected()) cbxRelRig.setSelected(true);
       			if (!RelSpecisSelected()) cbxRelSpec.setSelected(true);
       			if (!RepRelisSelected()) cbxRepRel.setSelected(true);
       			if (!UndefFormalisSelected()) cbxUndefFormal.setSelected(true);
       			if (!UndefPhaseisSelected()) cbxUndefPhase.setSelected(true);
       			if (!WholeOverisSelected()) cbxWholeOver.setSelected(true);
       		}
       	});
		JButton btnDisableall = new JButton("Disable All");
		
		btnDisableall.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if (AssCycisSelected()) cbxAssCyc.setSelected(false);
       			if (BinOverisSelected()) cbxBinOver.setSelected(false);
       			if (DepPhaseisSelected()) cbxDepPhase.setSelected(false);
       			if (FreeRoleisSelected()) cbxFreeRole.setSelected(false);
       			if (GSRigisSelected()) cbxGSRig.setSelected(false);
       			if (HetCollisSelected()) cbxHetColl.setSelected(false);
       			if (HomoFuncisSelected()) cbxHomoFunc.setSelected(false);
       			if (ImpAbsisSelected()) cbxImpAbs.setSelected(false);
       			if (ImpPartisSelected()) cbxImpPart.setSelected(false);
       			if (MixIdenisSelected()) cbxMixIden.setSelected(false);
       			if (MixRigisSelected()) cbxMixRig.setSelected(false);
       			if (MultiDepisSelected()) cbxMultiDep.setSelected(false);
       			if (RelCompisSelected()) cbxRelComp.setSelected(false);
       			if (RelOverisSelected()) cbxRelOver.setSelected(false);
       			if (RelRigisSelected()) cbxRelRig.setSelected(false);
       			if (RelSpecisSelected()) cbxRelSpec.setSelected(false);
       			if (RepRelisSelected()) cbxRepRel.setSelected(false);
       			if (UndefFormalisSelected()) cbxUndefFormal.setSelected(false);
       			if (UndefPhaseisSelected()) cbxUndefPhase.setSelected(false);
       			if (WholeOverisSelected()) cbxWholeOver.setSelected(false);
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
			
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						
			OntoUMLParser parser = ProjectBrowser.getParserFor(frame.getDiagramManager().getCurrentProject());
		
			 AssCycAntipattern assCyc = new AssCycAntipattern(parser); 	
			 BinOverAntipattern binOver = new BinOverAntipattern(parser);		
			 DepPhaseAntipattern depPhase = new DepPhaseAntipattern(parser);
			 FreeRoleAntipattern freeRole = new FreeRoleAntipattern(parser);
			 GSRigAntipattern gsRig = new GSRigAntipattern(parser);
			 HetCollAntipattern hetColl = new HetCollAntipattern(parser);
			 HomoFuncAntipattern homoFunc = new HomoFuncAntipattern(parser);
			 ImpAbsAntipattern impAbs = new ImpAbsAntipattern(parser);
			 ImpPartAntipattern impPart = new ImpPartAntipattern(parser);
			 MixIdenAntipattern mixIden = new MixIdenAntipattern(parser);
			 MixRigAntipattern mixRig = new MixRigAntipattern(parser);
			 MultiDepAntipattern multiDep = new MultiDepAntipattern(parser);
			 RelCompAntipattern relComp = new RelCompAntipattern(parser);
			 RelOverAntipattern relOver = new RelOverAntipattern(parser);
			 RelRigAntipattern relRig = new RelRigAntipattern(parser);
			 RelSpecAntipattern relSpec = new RelSpecAntipattern(parser);
			 RepRelAntipattern repRel = new RepRelAntipattern(parser);
			 UndefFormalAntipattern undefFormal = new UndefFormalAntipattern(parser);
			 UndefPhaseAntipattern undefPhase = new UndefPhaseAntipattern(parser);
			 WholeOverAntipattern wholeOver = new WholeOverAntipattern(parser);	
		
			 frame.getDiagramManager().autoCompleteSelection(OntoUMLParser.NO_HIERARCHY,frame.getDiagramManager().getCurrentProject());
			
			if (parser.getElements() == null) return;
			
			if (AssCycisSelected()) 
				assCyc.identify();
			
			if (BinOverisSelected())	
				binOver.identify();
			
			if (DepPhaseisSelected())	
				depPhase.identify();
			
			if (FreeRoleisSelected())	
				freeRole.identify();
			
			if (GSRigisSelected())	
				gsRig.identify();
			
			if (HetCollisSelected())	
				hetColl.identify();
			
			if (HomoFuncisSelected())	
				homoFunc.identify();
			
			if (ImpAbsisSelected())	
				impAbs.identify();
			
			if (ImpPartisSelected())	
				impPart.identify();
			
			if (MixIdenisSelected())	
				mixIden.identify();
			
			if (MixRigisSelected())	
				mixRig.identify();
			
			if (MultiDepisSelected())	
				relSpec.identify();
			
			if (RelCompisSelected())	
				relComp.identify();
			
			if (RelOverisSelected())	
				relOver.identify();
			
			if (RelRigisSelected())	
				relRig.identify();
			
			if (RelSpecisSelected())	
				relSpec.identify();
			
			if (RepRelisSelected())	
				repRel.identify();
			
			if (UndefFormalisSelected())	
				undefFormal.identify();
			
			if (UndefPhaseisSelected())	
				undefPhase.identify();
			
			if (WholeOverisSelected())	
				wholeOver.identify();
			
		String result = new String();
		
		if (assCyc.getOccurrences().size()>0) result += AssCycAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+assCyc.getOccurrences().size()+" items found.\n";
		if (binOver.getOccurrences().size()>0) result += BinOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+binOver.getOccurrences().size()+" items found.\n";
		if (depPhase.getOccurrences().size()>0) result += DepPhaseAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+depPhase.getOccurrences().size()+" items found.\n";
		if (freeRole.getOccurrences().size()>0) result += FreeRoleAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+freeRole.getOccurrences().size()+" items found.\n";
		if (gsRig.getOccurrences().size()>0) result += GSRigAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+gsRig.getOccurrences().size()+" items found.\n";
		if (hetColl.getOccurrences().size()>0) result += HetCollAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+hetColl.getOccurrences().size()+" items found.\n";
		if (homoFunc.getOccurrences().size()>0) result += HomoFuncAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+homoFunc.getOccurrences().size()+" items found.\n";
		if (impAbs.getOccurrences().size()>0) result += ImpAbsAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+impAbs.getOccurrences().size()+" items found.\n";
		if (impPart.getOccurrences().size()>0) result += ImpPartAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+impPart.getOccurrences().size()+" items found.\n";
		if (mixIden.getOccurrences().size()>0) result += MixIdenAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+mixIden.getOccurrences().size()+" items found.\n";
		if (mixRig.getOccurrences().size()>0) result += MixRigAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+mixRig.getOccurrences().size()+" items found.\n";
		if (multiDep.getOccurrences().size()>0) result += MultiDepAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+multiDep.getOccurrences().size()+" items found.\n";
		if (relComp.getOccurrences().size()>0) result += RelCompAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relComp.getOccurrences().size()+" items found.\n";
		if (relOver.getOccurrences().size()>0) result += RelOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relOver.getOccurrences().size()+" items found.\n";
		if (relRig.getOccurrences().size()>0) result += RelRigAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relRig.getOccurrences().size()+" items found.\n";
		if (relSpec.getOccurrences().size()>0) result += RelSpecAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relSpec.getOccurrences().size()+" items found.\n";
		if (repRel.getOccurrences().size()>0) result += RepRelAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+repRel.getOccurrences().size()+" items found.\n";
		if (undefFormal.getOccurrences().size()>0) result += UndefFormalAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+undefFormal.getOccurrences().size()+" items found.\n";
		if (undefPhase.getOccurrences().size()>0) result += UndefPhaseAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+undefPhase.getOccurrences().size()+" items found.\n";
		if (wholeOver.getOccurrences().size()>0) result += WholeOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+wholeOver.getOccurrences().size()+" items found.\n";
		
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		if (result.isEmpty()) JOptionPane.showMessageDialog(this,"No anti-patterns found.","Managing AntiPatterns",JOptionPane.INFORMATION_MESSAGE); 
		else 
		{
			JOptionPane.showMessageDialog(this,result,"Managing Anti-Patterns",JOptionPane.INFORMATION_MESSAGE);			
			
			AntiPatternList antipatternList = new AntiPatternList (assCyc, binOver, depPhase, freeRole, gsRig, hetColl, homoFunc, impAbs, impPart, mixIden,
																	mixRig, multiDep, relComp, relOver, relRig, relSpec, repRel, undefFormal, undefPhase, wholeOver	);

			ProjectBrowser.setAntiPatternListFor(frame.getDiagramManager().getCurrentProject(),antipatternList);

			frame.getDiagramManager().openAntiPatternManager();
		}		 
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Managing Anti-Pattern",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		dispose();		
	}
}
