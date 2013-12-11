package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
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
	
	private JProgressBar progressBar;
	private JButton identifyButton;
	private JSeparator separator;
	private JLabel lblNewLabel_1;
	private JLabel progressBarDescr;
		
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
		setTitle("Anti-Pattern Search");
		setBounds(100, 100, 637, 437);
		
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
		
		JLabel lblChooseWhichAntipattern = new JLabel("Choose which anti-pattern do you want to search:");
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
       	});;
		
		separator = new JSeparator();
		
		lblNewLabel_1 = new JLabel("Version 1.0  ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(cbxMixIden, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxImpPart, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxImpAbs, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxHomoFunc, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxHetColl, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxGSRig, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxFreeRole, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxDepPhase, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxBinOver, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxAssCyc, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblChooseWhichAntipattern, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbxMixRig, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxMultiDep, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxRelComp, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxRelOver, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxRelRig, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxRelSpec, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxRepRel, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxUndefFormal, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxUndefPhase, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxWholeOver, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnEnableall)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDisableall)
									.addPreferredGap(ComponentPlacement.RELATED, 281, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblChooseWhichAntipattern)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxAssCyc)
						.addComponent(cbxMixRig))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxBinOver)
						.addComponent(cbxMultiDep))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxDepPhase)
						.addComponent(cbxRelComp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxFreeRole)
						.addComponent(cbxRelOver))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxGSRig)
						.addComponent(cbxRelRig))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxHetColl)
						.addComponent(cbxRelSpec))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxHomoFunc)
						.addComponent(cbxRepRel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxImpAbs)
						.addComponent(cbxUndefFormal))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxImpPart)
						.addComponent(cbxUndefPhase))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbxMixIden)
						.addComponent(cbxWholeOver))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnEnableall, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnDisableall, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setPreferredSize(new Dimension(60, 80));
		
		createIdentifyButton(buttonPane);
		createEnableallButton(buttonPane);
		createDisableallButton(buttonPane);
		createCancelButton(buttonPane);
		
		progressBar = new JProgressBar();		
				
		identifyButton = new JButton("Run");
		identifyButton.setIcon(null);
		
		identifyButton.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			IdentifyButtonActionPerformed(event);
       		}
       	});
		
		progressBarDescr = new JLabel("Click to start the search for anti-patterns...");		
		progressBarDescr.setForeground(Color.BLUE);
		progressBarDescr.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_buttonPane.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_buttonPane.createSequentialGroup()
							.addComponent(identifyButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(progressBarDescr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(80))
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 587, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(progressBarDescr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(identifyButton, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		buttonPane.setLayout(gl_buttonPane);
	}
			 
	/**
	 * Create a Identify Button.
	 * @param buttonPane
	 */
	public void createIdentifyButton(JPanel buttonPane)
	{
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
	}
	
	/**
	 * Identifying AntiPatterns...
	 * 
	 * @param event
	 */
	public void IdentifyButtonActionPerformed(ActionEvent event)
	{
		try{
									
			progressBar.setStringPainted(true);
			progressBar.setMinimum(0);
			progressBar.setMaximum(100);
			
			identifyButton.setEnabled(false);
			
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
			
			if (AssCycisSelected()) {
				progressBar.setValue(0);
				progressBarDescr.setText("Searching AssCyc...");
				assCyc.identify();
				progressBar.setValue(5);
				progressBarDescr.setText("Searching AssCyc... "+assCyc.getOccurrences().size()+" items found");
				
			}
			
			if (BinOverisSelected()){
				progressBarDescr.setText("Searching BinOver... ");
				binOver.identify();
				progressBar.setValue(10);
				progressBarDescr.setText("Searching BinOver... "+binOver.getOccurrences().size()+" items found");
			}
			
			if (DepPhaseisSelected()){		
				progressBarDescr.setText("Searching DepPhase... ");
				depPhase.identify();
				progressBar.setValue(15);
				progressBarDescr.setText("Searching DepPhase... "+depPhase.getOccurrences().size()+" items found");
			}
			
			if (FreeRoleisSelected()){			
				progressBarDescr.setText("Searching FreeRole... ");
				freeRole.identify();
				progressBar.setValue(20);
				progressBarDescr.setText("Searching FreeRole... "+freeRole.getOccurrences().size()+" items found");
			}
			
			if (GSRigisSelected()){
				progressBarDescr.setText("Searching GSRig... ");
				gsRig.identify();
				progressBar.setValue(25);
				progressBarDescr.setText("Searching GSRig... "+gsRig.getOccurrences().size()+" items found");
			}
			
			if (HetCollisSelected()){	
				progressBarDescr.setText("Searching HetColl... ");
				hetColl.identify();
				progressBar.setValue(30);
				progressBarDescr.setText("Searching HetColl... "+hetColl.getOccurrences().size()+" items found");
			}
			
			if (HomoFuncisSelected()){	
				progressBarDescr.setText("Searching HomoFunc... ");
				homoFunc.identify();
				progressBar.setValue(35);
				progressBarDescr.setText("Searching HomoFunc... "+homoFunc.getOccurrences().size()+" items found");
			}
			
			if (ImpAbsisSelected()){
				progressBarDescr.setText("Searching ImpAbs... ");
				impAbs.identify();
				progressBar.setValue(40);
				progressBarDescr.setText("Searching ImpAbs... "+impAbs.getOccurrences().size()+" items found");				
			}
			
			if (ImpPartisSelected()){	
				progressBarDescr.setText("Searching ImpPart... ");
				impPart.identify();
				progressBar.setValue(45);
				progressBarDescr.setText("Searching ImpPart... "+impPart.getOccurrences().size()+" items found");	
			}
			
			if (MixIdenisSelected()){	
				progressBarDescr.setText("Searching MixIden... ");
				mixIden.identify();
				progressBar.setValue(50);
				progressBarDescr.setText("Searching MixIden... "+mixIden.getOccurrences().size()+" items found");	
			}
			
			if (MixRigisSelected()){
				progressBarDescr.setText("Searching MixRig... ");
				mixRig.identify();
				progressBar.setValue(55);
				progressBarDescr.setText("Searching MixRig... "+mixRig.getOccurrences().size()+" items found");
			}
			
			if (MultiDepisSelected()){	
				progressBarDescr.setText("Searching MultiDep... ");
				multiDep.identify();
				progressBar.setValue(60);
				progressBarDescr.setText("Searching MultiDep... "+multiDep.getOccurrences().size()+" items found");
			}
			
			if (RelCompisSelected()){				
				progressBarDescr.setText("Searching RelComp... ");
				relComp.identify();
				progressBar.setValue(65);
				progressBarDescr.setText("Searching RelComp... "+relComp.getOccurrences().size()+" items found");
			}
			
			if (RelOverisSelected()){	
				progressBarDescr.setText("Searching RelOver... ");
				relOver.identify();
				progressBar.setValue(70);
				progressBarDescr.setText("Searching RelOver... "+relOver.getOccurrences().size()+" items found");
			}
			
			if (RelRigisSelected()){	
				progressBarDescr.setText("Searching RelRig... ");
				relRig.identify();
				progressBar.setValue(75);
				progressBarDescr.setText("Searching RelRig... "+relRig.getOccurrences().size()+" items found");
			}
			
			if (RelSpecisSelected()){	
				progressBarDescr.setText("Searching RelSpec... ");
				relSpec.identify();
				progressBar.setValue(80);
				progressBarDescr.setText("Searching RelSpec... "+relSpec.getOccurrences().size()+" items found");
			}
			
			if (RepRelisSelected()){	
				progressBarDescr.setText("Searching RepRel... ");
				repRel.identify();
				progressBar.setValue(85);
				progressBarDescr.setText("Searching RepRel... "+repRel.getOccurrences().size()+" items found");
			}
			
			if (UndefFormalisSelected()){	
				progressBarDescr.setText("Searching UndefFormal... ");
				undefFormal.identify();
				progressBar.setValue(90);
				progressBarDescr.setText("Searching UndefFormal... "+undefFormal.getOccurrences().size()+" items found");
			}
			
			if (UndefPhaseisSelected()){	
				progressBarDescr.setText("Searching UndefPhase... ");
				undefPhase.identify();
				progressBar.setValue(95);
				progressBarDescr.setText("Searching UndefPhase... "+undefPhase.getOccurrences().size()+" items found");
			}
			
			if (WholeOverisSelected()){	
				progressBarDescr.setText("Searching WholeOver... ");
				wholeOver.identify();				
				progressBar.setValue(100);
				progressBarDescr.setText("Searching WholeOver... "+wholeOver.getOccurrences().size()+" items found");
			}
			
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
		
		identifyButton.setEnabled(true);
		progressBarDescr.setText("done!");
		
		if (result.isEmpty()) JOptionPane.showMessageDialog(this,"No anti-pattern found.","Anti-Pattern Detection",JOptionPane.INFORMATION_MESSAGE); 
		else 
		{
			JOptionPane.showMessageDialog(this,result,"Anti-Pattern Detection",JOptionPane.INFORMATION_MESSAGE);			
			
			AntiPatternList antipatternList = new AntiPatternList (assCyc, binOver, depPhase, freeRole, gsRig, hetColl, homoFunc, impAbs, impPart, mixIden,
																	mixRig, multiDep, relComp, relOver, relRig, relSpec, repRel, undefFormal, undefPhase, wholeOver	);

			ProjectBrowser.setAntiPatternListFor(frame.getDiagramManager().getCurrentProject(),antipatternList);

			frame.getDiagramManager().openAntiPatternManager();
		}		 
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Anti-Pattern Detection",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}			
	}
}
