package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
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
	
	private JButton lblAssCycIco;	
	private JButton lblBinOverIco;	
	private JButton lblDepPhaseIco;
	private JButton lblFreeRoleIco;
	private JButton lblGSRigIco;
	private JButton lblHetCollIco;
	private JButton lblHomoFuncIco;
	private JButton lblImpAbsIco;
	private JButton lblImpPartIco;
	private JButton lblMixIdenIco;
	private JButton lblMixRigIco;
	private JButton lblMultiDepIco;
	private JButton lblRelCompIco;
	private JButton lblRelOverIco;
	private JButton lblRelRigIco;
	private JButton lblRelSpecIco;
	private JButton lblRepRelIco;
	private JButton lblUndefFormalIco;
	private JButton lblUndefPhaseIco;
	private JButton lblWholeOverIco;
		
	private DefaultBoundedRangeModel progressModel = new DefaultBoundedRangeModel();
	private JProgressBar progressBar = new JProgressBar(progressModel);
	private JLabel progressBarDescr;	
	private JButton identifyButton;
	
	private JLabel lblAssCycRes;
	private JLabel lblBinOverRes;
	private JLabel lblDepPhaseRes;
	private JLabel lblFreeRoleRes;
	private JLabel lblGSRigRes;
	private JLabel lblHetCollRes;
	private JLabel lblHomoFuncRes;
	private JLabel lblImpAbsRes;
	private JLabel lblImpPartRes;
	private JLabel lblMixIdenRes;
	private JLabel lblMixRigRes;
	private JLabel lblMultiDepRes;
	private JLabel lblRelCompRes;
	private JLabel lblRelOverRes;
	private JLabel lblRelRigRes;
	private JLabel lblRelSpecRes;
	private JLabel lblRepRelRes;
	private JLabel lblUndefFormalRes;
	private JLabel lblUndefPhaseRes;
	private JLabel lblWholeOverRes;
	private JButton btnNewButton_1;
	
			
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
	
	public void cleanAllResultlabels ()
	{
		lblAssCycRes.setText("");
		lblBinOverRes.setText("");
		lblDepPhaseRes.setText("");
		lblFreeRoleRes.setText("");
		lblGSRigRes.setText("");
		lblHetCollRes.setText("");
		lblHomoFuncRes.setText("");
		lblImpAbsRes.setText("");
		lblImpPartRes.setText("");
		lblMixIdenRes.setText("");
		lblMixRigRes.setText("");
		lblMultiDepRes.setText("");
		lblRelCompRes.setText("");
		lblRelOverRes.setText("");
		lblRelRigRes.setText("");
		lblRelSpecRes.setText("");
		lblRepRelRes.setText("");
		lblUndefFormalRes.setText("");
		lblUndefPhaseRes.setText("");
		lblWholeOverRes.setText("");
	}
		
	public void ShowAllAntiPatternIconLabels(boolean show)
	{
		lblAssCycIco.setVisible(show);
		lblBinOverIco.setVisible(show);
		lblDepPhaseIco.setVisible(show);
		lblFreeRoleIco.setVisible(show);
		lblGSRigIco.setVisible(show);
		lblHetCollIco.setVisible(show);
		lblHomoFuncIco.setVisible(show);
		lblImpAbsIco.setVisible(show);
		lblImpPartIco.setVisible(show);
		lblMixIdenIco.setVisible(show);
		lblMixRigIco.setVisible(show);
		lblMultiDepIco.setVisible(show);
		lblRelCompIco.setVisible(show);
		lblRelOverIco.setVisible(show);
		lblRelRigIco.setVisible(show);
		lblRelSpecIco.setVisible(show);
		lblRepRelIco.setVisible(show);
		lblUndefFormalIco.setVisible(show);
		lblUndefPhaseIco.setVisible(show);
		lblWholeOverIco.setVisible(show);
	}
	
	public void HideBoldnessOnAllCheckBoxes()
	{
		cbxAssCyc.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxBinOver.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbxDepPhase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbxFreeRole.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbxGSRig.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbxHetColl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbxHomoFunc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbxImpAbs.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxImpPart.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxMixIden.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxMixRig.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxMultiDep.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxRelComp.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxRelOver.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxRelRig.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxRelSpec.setFont(new Font("Tahoma", Font.PLAIN, 11));		
		cbxRepRel.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxUndefFormal.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxUndefPhase.setFont(new Font("Tahoma", Font.PLAIN, 11));	
		cbxWholeOver.setFont(new Font("Tahoma", Font.PLAIN, 11));		
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
		setBounds(100, 100, 757, 469);
		 
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(180, 350));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JLabel lblChooseWhichAntipattern = new JLabel("   Choose which anti-pattern do you want to search:");
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
		
		JPanel leftPanel = new JPanel();
		
		JPanel rightPanel = new JPanel();
		rightPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(btnEnableall)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDisableall))
						.addComponent(lblChooseWhichAntipattern, GroupLayout.PREFERRED_SIZE, 698, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 690, GroupLayout.PREFERRED_SIZE)))
					.addGap(21))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblChooseWhichAntipattern)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnableall)
						.addComponent(btnDisableall))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		
		cbxMixRig = new JCheckBox(MixRigAntipattern.getAntipatternInfo().getAcronym()+" : "+MixRigAntipattern.getAntipatternInfo().getName());
		cbxMixRig.setPreferredSize(new Dimension(180, 20));
		cbxMixRig.setBackground(UIManager.getColor("Panel.background"));
		
		cbxMultiDep = new JCheckBox("MultiDep : Multiple Relational Dependecy\r\n");
		cbxMultiDep.setPreferredSize(new Dimension(220, 20));
		cbxMultiDep.setBackground(UIManager.getColor("Panel.background"));
	
		lblMixRigIco  = new JButton();
		lblMixRigIco.setPreferredSize(new Dimension(20, 20));
		lblMixRigIco.setOpaque(false);
		lblMixRigIco.setContentAreaFilled(false);
		lblMixRigIco.setBorderPainted(false);
		lblMixRigIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblMixRigIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
				
		lblMultiDepIco = new JButton();
		lblMultiDepIco.setPreferredSize(new Dimension(20, 20));
		lblMultiDepIco.setOpaque(false);
		lblMultiDepIco.setContentAreaFilled(false);
		lblMultiDepIco.setBorderPainted(false);
		lblMultiDepIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblMultiDepIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
				
		cbxRelComp = new JCheckBox(RelCompAntipattern.getAntipatternInfo().getAcronym()+" : "+RelCompAntipattern.getAntipatternInfo().getName());
		cbxRelComp.setPreferredSize(new Dimension(180, 20));
		cbxRelComp.setBackground(UIManager.getColor("Panel.background"));
		
		lblRelCompIco = new JButton();
		lblRelCompIco.setPreferredSize(new Dimension(20, 20));
		lblRelCompIco.setOpaque(false);
		lblRelCompIco.setContentAreaFilled(false);
		lblRelCompIco.setBorderPainted(false);
		lblRelCompIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblRelCompIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
				
		cbxRelOver = new JCheckBox(RelOverAntipattern.getAntipatternInfo().getAcronym()+" : "+RelOverAntipattern.getAntipatternInfo().getName());
		cbxRelOver.setPreferredSize(new Dimension(252, 20));
		cbxRelOver.setBackground(UIManager.getColor("Panel.background"));
		
		lblRelOverIco  = new JButton();
		lblRelOverIco.setPreferredSize(new Dimension(20, 20));
		lblRelOverIco.setOpaque(false);
		lblRelOverIco.setContentAreaFilled(false);
		lblRelOverIco.setBorderPainted(false);
		lblRelOverIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblRelOverIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
				
		cbxRelRig = new JCheckBox(RelRigAntipattern.getAntipatternInfo().getAcronym()+" : "+RelRigAntipattern.getAntipatternInfo().getName());
		cbxRelRig.setPreferredSize(new Dimension(210, 20));
		cbxRelRig.setBackground(UIManager.getColor("Panel.background"));
				
		cbxRelSpec = new JCheckBox(RelSpecAntipattern.getAntipatternInfo().getAcronym()+" : "+RelSpecAntipattern.getAntipatternInfo().getName());
		cbxRelSpec.setPreferredSize(new Dimension(180, 20));
		cbxRelSpec.setBackground(UIManager.getColor("Panel.background"));
		
		lblRelSpecIco = new JButton();
		lblRelSpecIco.setPreferredSize(new Dimension(20, 20));
		lblRelSpecIco.setOpaque(false);
		lblRelSpecIco.setContentAreaFilled(false);
		lblRelSpecIco.setBorderPainted(false);
		lblRelSpecIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblRelSpecIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
				
		cbxRepRel = new JCheckBox(RepRelAntipattern.getAntipatternInfo().getAcronym()+" : "+RepRelAntipattern.getAntipatternInfo().getName());
		cbxRepRel.setPreferredSize(new Dimension(215, 20));
		cbxRepRel.setBackground(UIManager.getColor("Panel.background"));
		
		lblRepRelIco = new JButton();
		lblRepRelIco.setPreferredSize(new Dimension(20, 20));
		lblRepRelIco.setOpaque(false);
		lblRepRelIco.setContentAreaFilled(false);
		lblRepRelIco.setBorderPainted(false);
		lblRepRelIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblRepRelIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
				
		cbxUndefFormal = new JCheckBox(UndefFormalAntipattern.getAntipatternInfo().getAcronym()+" : "+UndefFormalAntipattern.getAntipatternInfo().getName());
		cbxUndefFormal.setPreferredSize(new Dimension(260, 20));
		cbxUndefFormal.setBackground(UIManager.getColor("Panel.background"));
		
		lblUndefFormalIco = new JButton();
		lblUndefFormalIco.setPreferredSize(new Dimension(20, 20));
		lblUndefFormalIco.setOpaque(false);
		lblUndefFormalIco.setContentAreaFilled(false);
		lblUndefFormalIco.setBorderPainted(false);
		lblUndefFormalIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblUndefFormalIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
				
		cbxUndefPhase = new JCheckBox(UndefPhaseAntipattern.getAntipatternInfo().getAcronym()+" : "+UndefPhaseAntipattern.getAntipatternInfo().getName());
		cbxUndefPhase.setPreferredSize(new Dimension(220, 20));
		cbxUndefPhase.setBackground(UIManager.getColor("Panel.background"));
		
		lblUndefPhaseIco = new JButton();
		lblUndefPhaseIco.setPreferredSize(new Dimension(20, 20));
		lblUndefPhaseIco.setOpaque(false);
		lblUndefPhaseIco.setContentAreaFilled(false);
		lblUndefPhaseIco.setBorderPainted(false);
		lblUndefPhaseIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblUndefPhaseIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
				
		lblMixRigRes = new JLabel("");
		lblMixRigRes.setPreferredSize(new Dimension(120, 20));
		lblMixRigRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMixRigRes.setForeground(Color.BLUE);
		
		lblMultiDepRes = new JLabel("");
		lblMultiDepRes.setPreferredSize(new Dimension(80, 20));
		lblMultiDepRes.setForeground(Color.BLUE);
		lblMultiDepRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblRelCompRes = new JLabel("");
		lblRelCompRes.setPreferredSize(new Dimension(120, 20));
		lblRelCompRes.setForeground(Color.BLUE);
		lblRelCompRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblRelOverRes = new JLabel("");
		lblRelOverRes.setPreferredSize(new Dimension(48, 20));
		lblRelOverRes.setForeground(Color.BLACK);
		lblRelOverRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblRelSpecRes = new JLabel("");
		lblRelSpecRes.setPreferredSize(new Dimension(120, 20));
		lblRelSpecRes.setForeground(Color.BLUE);
		lblRelSpecRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblRepRelRes = new JLabel("");
		lblRepRelRes.setPreferredSize(new Dimension(85, 20));
		lblRepRelRes.setForeground(Color.BLUE);
		lblRepRelRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblUndefFormalRes = new JLabel("");
		lblUndefFormalRes.setPreferredSize(new Dimension(40, 20));
		lblUndefFormalRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUndefFormalRes.setForeground(Color.BLUE);
		
		lblUndefPhaseRes = new JLabel("");
		lblUndefPhaseRes.setPreferredSize(new Dimension(80, 20));
		lblUndefPhaseRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUndefPhaseRes.setForeground(Color.BLUE);
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		rightPanel.add(cbxMixRig);
		rightPanel.add(lblMixRigIco);
		rightPanel.add(lblMixRigRes);
		rightPanel.add(cbxMultiDep);
		rightPanel.add(lblMultiDepIco);
		rightPanel.add(lblMultiDepRes);
		rightPanel.add(cbxRelComp);
		rightPanel.add(lblRelCompIco);
		rightPanel.add(lblRelCompRes);
		rightPanel.add(cbxRelOver);
		rightPanel.add(lblRelOverIco);
		rightPanel.add(lblRelOverRes);
		rightPanel.add(cbxRepRel);
		rightPanel.add(lblRepRelIco);
		rightPanel.add(lblRepRelRes);
		rightPanel.add(cbxRelRig);
		
		lblRelRigIco = new JButton();
		lblRelRigIco.setPreferredSize(new Dimension(20, 20));
		lblRelRigIco.setOpaque(false);
		lblRelRigIco.setContentAreaFilled(false);
		lblRelRigIco.setBorderPainted(false);
		lblRelRigIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblRelRigIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		rightPanel.add(lblRelRigIco);
		
		lblRelRigRes = new JLabel("");
		lblRelRigRes.setPreferredSize(new Dimension(90, 20));
		lblRelRigRes.setForeground(Color.BLUE);
		lblRelRigRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		rightPanel.add(lblRelRigRes);
		rightPanel.add(cbxRelSpec);
		rightPanel.add(lblRelSpecIco);
		rightPanel.add(lblRelSpecRes);
		rightPanel.add(cbxUndefPhase);
		rightPanel.add(lblUndefPhaseIco);
		rightPanel.add(lblUndefPhaseRes);
		rightPanel.add(cbxUndefFormal);
		rightPanel.add(lblUndefFormalIco);
		rightPanel.add(lblUndefFormalRes);
		
		cbxWholeOver = new JCheckBox(WholeOverAntipattern.getAntipatternInfo().getAcronym()+" : "+WholeOverAntipattern.getAntipatternInfo().getName());
		cbxWholeOver.setPreferredSize(new Dimension(275, 20));
		cbxWholeOver.setBackground(UIManager.getColor("Panel.background"));
		rightPanel.add(cbxWholeOver);
		
		lblWholeOverIco = new JButton();
		lblWholeOverIco.setPreferredSize(new Dimension(20, 20));
		lblWholeOverIco.setOpaque(false);
		lblWholeOverIco.setContentAreaFilled(false);
		lblWholeOverIco.setBorderPainted(false);
		lblWholeOverIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblWholeOverIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		rightPanel.add(lblWholeOverIco);
		
		lblWholeOverRes = new JLabel("");
		lblWholeOverRes.setPreferredSize(new Dimension(25, 20));
		lblWholeOverRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWholeOverRes.setForeground(Color.BLUE);
		rightPanel.add(lblWholeOverRes);
				
		cbxDepPhase = new JCheckBox(DepPhaseAntipattern.getAntipatternInfo().getAcronym()+" : "+DepPhaseAntipattern.getAntipatternInfo().getName());
		cbxDepPhase.setBackground(UIManager.getColor("Panel.background"));
				
		cbxHetColl = new JCheckBox(HetCollAntipattern.getAntipatternInfo().getAcronym()+" : "+HetCollAntipattern.getAntipatternInfo().getName());
		cbxHetColl.setPreferredSize(new Dimension(195, 20));
		cbxHetColl.setBackground(UIManager.getColor("Panel.background"));
		
		lblHetCollIco = new JButton();
		lblHetCollIco.setPreferredSize(new Dimension(20, 20));
		lblHetCollIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblHetCollIco.setOpaque(false);
		lblHetCollIco.setContentAreaFilled(false);
		lblHetCollIco.setBorderPainted(false);
		lblHetCollIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
				
		cbxMixIden = new JCheckBox(MixIdenAntipattern.getAntipatternInfo().getAcronym()+" : "+MixIdenAntipattern.getAntipatternInfo().getName());
		cbxMixIden.setPreferredSize(new Dimension(190, 20));
		cbxMixIden.setBackground(UIManager.getColor("Panel.background"));
		
		lblMixIdenIco = new JButton();
		lblMixIdenIco.setPreferredSize(new Dimension(20, 20));
		lblMixIdenIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblMixIdenIco.setOpaque(false);
		lblMixIdenIco.setContentAreaFilled(false);
		lblMixIdenIco.setBorderPainted(false);
		lblMixIdenIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		
		lblHetCollRes = new JLabel("");
		lblHetCollRes.setPreferredSize(new Dimension(105, 20));
		lblHetCollRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHetCollRes.setForeground(Color.BLUE);		
		
		lblMixIdenRes = new JLabel("");
		lblMixIdenRes.setPreferredSize(new Dimension(110, 20));
		lblMixIdenRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMixIdenRes.setForeground(Color.BLUE);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		cbxAssCyc = new JCheckBox(AssCycAntipattern.getAntipatternInfo().getAcronym()+" : "+AssCycAntipattern.getAntipatternInfo().getName());
		cbxAssCyc.setPreferredSize(new Dimension(155, 20));
		cbxAssCyc.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxAssCyc);
		
		lblAssCycIco = new JButton();
		lblAssCycIco.setPreferredSize(new Dimension(20, 20));
		lblAssCycIco.setOpaque(false);
		lblAssCycIco.setContentAreaFilled(false);
		lblAssCycIco.setBorderPainted(false);
		lblAssCycIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblAssCycIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		leftPanel.add(lblAssCycIco);
		
		lblAssCycRes = new JLabel("");		
		lblAssCycRes.setPreferredSize(new Dimension(145, 20));
		lblAssCycRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAssCycRes.setForeground(Color.BLUE);
		leftPanel.add(lblAssCycRes);
		
		cbxBinOver = new JCheckBox(BinOverAntipattern.getAntipatternInfo().getAcronym()+" : "+BinOverAntipattern.getAntipatternInfo().getName());
		cbxBinOver.setPreferredSize(new Dimension(260, 20));
		cbxBinOver.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxBinOver);
		
		lblBinOverIco = new JButton();
		lblBinOverIco.setPreferredSize(new Dimension(20, 20));
		lblBinOverIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblBinOverIco.setOpaque(false);
		lblBinOverIco.setContentAreaFilled(false);
		lblBinOverIco.setBorderPainted(false);
		lblBinOverIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		leftPanel.add(lblBinOverIco);
		
		lblBinOverRes = new JLabel("");		
		lblBinOverRes.setPreferredSize(new Dimension(40, 20));
		lblBinOverRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBinOverRes.setForeground(Color.BLUE);
		leftPanel.add(lblBinOverRes);
		
		cbxDepPhase = new JCheckBox(DepPhaseAntipattern.getAntipatternInfo().getAcronym()+" : "+DepPhaseAntipattern.getAntipatternInfo().getName());
		cbxDepPhase.setPreferredSize(new Dimension(230, 20));
		leftPanel.add(cbxDepPhase);
		
		lblDepPhaseIco = new JButton();
		lblDepPhaseIco.setPreferredSize(new Dimension(20, 20));
		lblDepPhaseIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblDepPhaseIco.setOpaque(false);
		lblDepPhaseIco.setContentAreaFilled(false);
		lblDepPhaseIco.setBorderPainted(false);
		lblDepPhaseIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		leftPanel.add(lblDepPhaseIco);
		
		lblDepPhaseRes = new JLabel("");		
		lblDepPhaseRes.setPreferredSize(new Dimension(70, 20));
		lblDepPhaseRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepPhaseRes.setForeground(Color.BLUE);
		leftPanel.add(lblDepPhaseRes);
		
		cbxFreeRole = new JCheckBox(FreeRoleAntipattern.getAntipatternInfo().getAcronym()+" : "+FreeRoleAntipattern.getAntipatternInfo().getName());
		cbxFreeRole.setPreferredSize(new Dimension(195, 20));
		cbxFreeRole.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxFreeRole);
		
		lblFreeRoleIco = new JButton();
		lblFreeRoleIco.setPreferredSize(new Dimension(20, 20));
		lblFreeRoleIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblFreeRoleIco.setOpaque(false);
		lblFreeRoleIco.setContentAreaFilled(false);
		lblFreeRoleIco.setBorderPainted(false);
		lblFreeRoleIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		leftPanel.add(lblFreeRoleIco);
		
		lblFreeRoleRes = new JLabel("");		
		lblFreeRoleRes.setPreferredSize(new Dimension(105, 20));
		lblFreeRoleRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFreeRoleRes.setForeground(Color.BLUE);
		leftPanel.add(lblFreeRoleRes);
		
		cbxGSRig = new JCheckBox(GSRigAntipattern.getAntipatternInfo().getAcronym()+" : "+GSRigAntipattern.getAntipatternInfo().getName());
		cbxGSRig.setPreferredSize(new Dimension(245, 20));
		cbxGSRig.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxGSRig);
		
		lblGSRigIco = new JButton();
		lblGSRigIco.setPreferredSize(new Dimension(20, 20));
		lblGSRigIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblGSRigIco.setOpaque(false);
		lblGSRigIco.setContentAreaFilled(false);
		lblGSRigIco.setBorderPainted(false);
		lblGSRigIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		leftPanel.add(lblGSRigIco);
		
		lblGSRigRes = new JLabel("");
		lblGSRigRes.setPreferredSize(new Dimension(55, 20));
		lblGSRigRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGSRigRes.setForeground(Color.BLUE);		
		leftPanel.add(lblGSRigRes);
		leftPanel.add(cbxHetColl);
		leftPanel.add(lblHetCollIco);
		leftPanel.add(lblHetCollRes);
		
		cbxHomoFunc = new JCheckBox(HomoFuncAntipattern.getAntipatternInfo().getAcronym()+" : "+HomoFuncAntipattern.getAntipatternInfo().getName());
		cbxHomoFunc.setPreferredSize(new Dimension(250, 20));
		cbxHomoFunc.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxHomoFunc);
		
		lblHomoFuncIco = new JButton();
		lblHomoFuncIco.setPreferredSize(new Dimension(20, 20));
		lblHomoFuncIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblHomoFuncIco.setOpaque(false);
		lblHomoFuncIco.setContentAreaFilled(false);
		lblHomoFuncIco.setBorderPainted(false);
		lblHomoFuncIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		leftPanel.add(lblHomoFuncIco);
		
		lblHomoFuncRes = new JLabel("");
		lblHomoFuncRes.setPreferredSize(new Dimension(50, 20));
		lblHomoFuncRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHomoFuncRes.setForeground(Color.BLUE);		
		leftPanel.add(lblHomoFuncRes);
		
		cbxImpAbs = new JCheckBox(ImpAbsAntipattern.getAntipatternInfo().getAcronym()+" : "+ImpAbsAntipattern.getAntipatternInfo().getName());
		cbxImpAbs.setPreferredSize(new Dimension(175, 20));
		cbxImpAbs.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxImpAbs);
		
		lblImpAbsIco  = new JButton();
		lblImpAbsIco.setPreferredSize(new Dimension(20, 20));
		lblImpAbsIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblImpAbsIco.setOpaque(false);
		lblImpAbsIco.setContentAreaFilled(false);
		lblImpAbsIco.setBorderPainted(false);
		lblImpAbsIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		leftPanel.add(lblImpAbsIco);
		
		lblImpAbsRes = new JLabel("");
		lblImpAbsRes.setPreferredSize(new Dimension(125, 20));
		lblImpAbsRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImpAbsRes.setForeground(Color.BLUE);		
		leftPanel.add(lblImpAbsRes);
		
		cbxImpPart = new JCheckBox(ImpPartAntipattern.getAntipatternInfo().getAcronym()+" : "+ImpPartAntipattern.getAntipatternInfo().getName());
		cbxImpPart.setPreferredSize(new Dimension(205, 20));
		cbxImpPart.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxImpPart);
		
		lblImpPartIco = new JButton();
		lblImpPartIco.setPreferredSize(new Dimension(20, 20));
		lblImpPartIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		lblImpPartIco.setOpaque(false);
		lblImpPartIco.setContentAreaFilled(false);
		lblImpPartIco.setBorderPainted(false);
		lblImpPartIco.setRolloverIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation-rollover.png")));
		leftPanel.add(lblImpPartIco);
		
		lblImpPartRes = new JLabel("");
		lblImpPartRes.setPreferredSize(new Dimension(95, 20));
		lblImpPartRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImpPartRes.setForeground(Color.BLUE);		
		leftPanel.add(lblImpPartRes);
		leftPanel.add(cbxMixIden);
		leftPanel.add(lblMixIdenIco);
		leftPanel.add(lblMixIdenRes);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.CENTER);
		buttonPane.setPreferredSize(new Dimension(60, 80));
						
		identifyButton = new JButton("Search");
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
		progressBarDescr.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnNewButton = new JButton("Show Result");
		btnNewButton.setEnabled(false);
		
		btnNewButton_1 = new JButton("Close");
		
		btnNewButton_1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 686, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_buttonPane.createSequentialGroup()
							.addComponent(identifyButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(progressBarDescr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)))
					.addGap(310))
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(progressBarDescr)
						.addComponent(identifyButton, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		buttonPane.setLayout(gl_buttonPane);
		
		ShowAllAntiPatternIconLabels(true);
	}
		
	public void updateStatus(String text)
	{
		progressBarDescr.setText(text);
		progressBarDescr.repaint();
		progressBarDescr.revalidate();	
	}
	
	/**
	 * Identifying AntiPatterns...
	 * 
	 * @param event
	 */
	public void IdentifyButtonActionPerformed(ActionEvent event)
	{
		try{
						
			cleanAllResultlabels();
			HideBoldnessOnAllCheckBoxes();
			
			frame.focusOnOutput();
					
			progressBar.setStringPainted(true);
			progressBar.setMinimum(0);
			progressBar.setMaximum(100);
					
			identifyButton.setEnabled(false);
			
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
						
			OntoUMLParser parser = ProjectBrowser.getParserFor(frame.getDiagramManager().getCurrentProject());
		
			 final AssCycAntipattern assCyc = new AssCycAntipattern(parser); 	
			 final BinOverAntipattern binOver = new BinOverAntipattern(parser);		
			 final DepPhaseAntipattern depPhase = new DepPhaseAntipattern(parser);
			 final FreeRoleAntipattern freeRole = new FreeRoleAntipattern(parser);
			 final GSRigAntipattern gsRig = new GSRigAntipattern(parser);
			 final HetCollAntipattern hetColl = new HetCollAntipattern(parser);
			 final HomoFuncAntipattern homoFunc = new HomoFuncAntipattern(parser);
			 final ImpAbsAntipattern impAbs = new ImpAbsAntipattern(parser);
			 final ImpPartAntipattern impPart = new ImpPartAntipattern(parser);
			 final MixIdenAntipattern mixIden = new MixIdenAntipattern(parser);
			 final MixRigAntipattern mixRig = new MixRigAntipattern(parser);
			 final MultiDepAntipattern multiDep = new MultiDepAntipattern(parser);
			 final RelCompAntipattern relComp = new RelCompAntipattern(parser);
			 final RelOverAntipattern relOver = new RelOverAntipattern(parser);
			 final RelRigAntipattern relRig = new RelRigAntipattern(parser);
			 final RelSpecAntipattern relSpec = new RelSpecAntipattern(parser);
			 final RepRelAntipattern repRel = new RepRelAntipattern(parser);
			 final UndefFormalAntipattern undefFormal = new UndefFormalAntipattern(parser);
			 final UndefPhaseAntipattern undefPhase = new UndefPhaseAntipattern(parser);
			 final WholeOverAntipattern wholeOver = new WholeOverAntipattern(parser);	
		
			 frame.getDiagramManager().autoCompleteSelection(OntoUMLParser.NO_HIERARCHY,frame.getDiagramManager().getCurrentProject());
			
			if (parser.getElements() == null) return;
			
			int totalItemsSelected = 0;			
			if (AssCycisSelected()) totalItemsSelected++;
			if (BinOverisSelected()) totalItemsSelected++;
			if (DepPhaseisSelected()) totalItemsSelected++;
			if (FreeRoleisSelected()) totalItemsSelected++;
			if (GSRigisSelected()) totalItemsSelected++;
			if (HetCollisSelected()) totalItemsSelected++;
			if (HomoFuncisSelected()) totalItemsSelected++;
			if (ImpAbsisSelected()) totalItemsSelected++;
			if (ImpPartisSelected()) totalItemsSelected++;
			if (MixIdenisSelected()) totalItemsSelected++;
			if (MixRigisSelected()) totalItemsSelected++;
			if (MultiDepisSelected()) totalItemsSelected++;
			if (RelCompisSelected()) totalItemsSelected++;
			if (RelOverisSelected()) totalItemsSelected++;
			if (RelRigisSelected()) totalItemsSelected++;
			if (RelSpecisSelected()) totalItemsSelected++;
			if (RepRelisSelected()) totalItemsSelected++;			
			if (UndefFormalisSelected()) totalItemsSelected++;
			if (UndefPhaseisSelected()) totalItemsSelected++;
			if (WholeOverisSelected()) totalItemsSelected++;
			final int incrementalValue=100/totalItemsSelected;		
			
			if (AssCycisSelected()) {	
				updateStatus("Identifying AssCyc...");
				assCyc.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);						
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("AssCyc: "+assCyc.getOccurrences().size()+" items found");
			}			
			
			if (BinOverisSelected()){
				updateStatus("Identifying BinOver... ");
				binOver.identify();				
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("BinOver: "+binOver.getOccurrences().size()+" items found");
			}
			
			if (DepPhaseisSelected()){		
				updateStatus("Identifying DepPhase... ");
				depPhase.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("DepPhase: "+depPhase.getOccurrences().size()+" items found");				
			}
			
			if (FreeRoleisSelected()){			
				updateStatus("Identifying FreeRole... ");
				freeRole.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("FreeRole: "+freeRole.getOccurrences().size()+" items found");				
			}
			
			if (GSRigisSelected()){
				updateStatus("Identifying GSRig... ");
				gsRig.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("GSRig: "+gsRig.getOccurrences().size()+" items found");				
			}
			
			if (HetCollisSelected()){	
				updateStatus("Identifying HetColl... ");
				hetColl.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("HetColl: "+hetColl.getOccurrences().size()+" items found");				
			}
			
			if (HomoFuncisSelected()){	
				updateStatus("Identifying HomoFunc... ");
				homoFunc.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("HomoFunc: "+homoFunc.getOccurrences().size()+" items found");				
			}
			
			if (ImpAbsisSelected()){
				updateStatus("Identifying ImpAbs... ");
				impAbs.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("ImpAbs: "+impAbs.getOccurrences().size()+" items found");				
			}
			
			if (ImpPartisSelected()){	
				updateStatus("Identifying ImpPart... ");
				impPart.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("ImpPart: "+impPart.getOccurrences().size()+" items found");				
			}
			
			if (MixIdenisSelected()){	
				updateStatus("Identifying MixIden... ");
				mixIden.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("MixIden: "+mixIden.getOccurrences().size()+" items found");				
			}
			
			if (MixRigisSelected()){
				updateStatus("Identifying MixRig... ");
				mixRig.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("MixRig: "+mixRig.getOccurrences().size()+" items found");				
			}
			
			if (MultiDepisSelected()){	
				updateStatus("Identifying MultiDep... ");
				multiDep.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("MultiDep: "+multiDep.getOccurrences().size()+" items found");				
			}
			
			if (RelCompisSelected()){				
				updateStatus("Identifying RelComp... ");
				relComp.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("RelComp: "+relComp.getOccurrences().size()+" items found");				
			}
			
			if (RelOverisSelected()){	
				updateStatus("Identifying RelOver... ");
				relOver.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("RelOver: "+relOver.getOccurrences().size()+" items found");				
			}
			
			if (RelRigisSelected()){	
				updateStatus("Identifying RelRig... ");
				relRig.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("RelRig... "+relRig.getOccurrences().size()+" items found");				
			}
			
			if (RelSpecisSelected()){	
				updateStatus("Identifying RelSpec... ");
				relSpec.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("RelSpec: "+relSpec.getOccurrences().size()+" items found");				
			}
			
			if (RepRelisSelected()){	
				updateStatus("Identifying RepRel... ");
				repRel.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("RepRel: "+repRel.getOccurrences().size()+" items found");				
			}
			
			if (UndefFormalisSelected()){	
				updateStatus("Identifying UndefFormal... ");
				undefFormal.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("UndefFormal: "+undefFormal.getOccurrences().size()+" items found");				
			}
			
			if (UndefPhaseisSelected()){	
				updateStatus("Identifying UndefPhase... ");
				undefPhase.identify();
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("UndefPhase: "+undefPhase.getOccurrences().size()+" items found");				
			}
			
			if (WholeOverisSelected()){	
				updateStatus("Identifying WholeOver... ");
				wholeOver.identify();				
				progressBar.setValue(progressBar.getValue()+incrementalValue);
				progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
				updateStatus("WholeOver: "+wholeOver.getOccurrences().size()+" items found");				
			}
			
		@SuppressWarnings("unused")
		String result = new String();
		int totalOccurrences = 0;
		
		if (assCyc.getOccurrences().size()>0) { 
			result += AssCycAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+assCyc.getOccurrences().size()+" items found.\n";
			totalOccurrences += assCyc.getOccurrences().size();
			lblAssCycRes.setText("("+assCyc.getOccurrences().size()+")");
			cbxAssCyc.setFont(new Font("Tahoma", Font.BOLD, 11));
		}
		
		if (binOver.getOccurrences().size()>0) { 
			result += BinOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+binOver.getOccurrences().size()+" items found.\n"; 
			totalOccurrences += binOver.getOccurrences().size();
			lblBinOverRes.setText("("+binOver.getOccurrences().size()+")");
			cbxBinOver.setFont(new Font("Tahoma", Font.BOLD, 11));
		}  
		
		if (depPhase.getOccurrences().size()>0) {
			result += DepPhaseAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+depPhase.getOccurrences().size()+" items found.\n";
			totalOccurrences += depPhase.getOccurrences().size();
			lblDepPhaseRes.setText("("+depPhase.getOccurrences().size()+")");
			cbxDepPhase.setFont(new Font("Tahoma", Font.BOLD, 11));
		} 
		
		if (freeRole.getOccurrences().size()>0) {
			result += FreeRoleAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+freeRole.getOccurrences().size()+" items found.\n";
			totalOccurrences += freeRole.getOccurrences().size();
			lblFreeRoleRes.setText("("+freeRole.getOccurrences().size()+")");
			cbxFreeRole.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (gsRig.getOccurrences().size()>0) {
			result += GSRigAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+gsRig.getOccurrences().size()+" items found.\n";		
			totalOccurrences += gsRig.getOccurrences().size();
			lblGSRigRes.setText("("+gsRig.getOccurrences().size()+")");			
			cbxGSRig.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (hetColl.getOccurrences().size()>0) {
			result += HetCollAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+hetColl.getOccurrences().size()+" items found.\n";
			totalOccurrences += hetColl.getOccurrences().size();
			lblHetCollRes.setText("("+hetColl.getOccurrences().size()+")");		
			cbxHetColl.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (homoFunc.getOccurrences().size()>0) {
			result += HomoFuncAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+homoFunc.getOccurrences().size()+" items found.\n";
			totalOccurrences += homoFunc.getOccurrences().size();
			lblHomoFuncRes.setText("("+homoFunc.getOccurrences().size()+")");
			cbxHomoFunc.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (impAbs.getOccurrences().size()>0) {
			result += ImpAbsAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+impAbs.getOccurrences().size()+" items found.\n";
			totalOccurrences += impAbs.getOccurrences().size();
			lblImpAbsRes.setText("("+impAbs.getOccurrences().size()+")");
			cbxImpAbs.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (impPart.getOccurrences().size()>0) {
			result += ImpPartAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+impPart.getOccurrences().size()+" items found.\n";
			totalOccurrences += impPart.getOccurrences().size();
			lblImpPartRes.setText("("+impPart.getOccurrences().size()+")");		
			cbxImpPart.setFont(new Font("Tahoma", Font.BOLD, 11));
		}  
		
		if (mixIden.getOccurrences().size()>0) {
			result += MixIdenAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+mixIden.getOccurrences().size()+" items found.\n";
			totalOccurrences += mixIden.getOccurrences().size();
			lblMixIdenRes.setText("("+mixIden.getOccurrences().size()+")");
			cbxMixIden.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (mixRig.getOccurrences().size()>0) {
			result += MixRigAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+mixRig.getOccurrences().size()+" items found.\n";
			totalOccurrences += mixRig.getOccurrences().size();
			lblMixRigRes.setText("("+mixRig.getOccurrences().size()+")");
			cbxMixRig.setFont(new Font("Tahoma", Font.BOLD, 11));
		}  
		
		if (multiDep.getOccurrences().size()>0) {
			result += MultiDepAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+multiDep.getOccurrences().size()+" items found.\n";
			totalOccurrences += multiDep.getOccurrences().size();
			lblMultiDepRes.setText("("+multiDep.getOccurrences().size()+")");
			cbxMultiDep.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (relComp.getOccurrences().size()>0) {
			result += RelCompAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relComp.getOccurrences().size()+" items found.\n";
			totalOccurrences += relComp.getOccurrences().size();
			lblRelCompRes.setText("("+relComp.getOccurrences().size()+")");	
			cbxRelComp.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (relOver.getOccurrences().size()>0) {
			result += RelOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relOver.getOccurrences().size()+" items found.\n";
			totalOccurrences += relOver.getOccurrences().size();
			lblRelOverRes.setText("("+relOver.getOccurrences().size()+")");
			cbxRelOver.setFont(new Font("Tahoma", Font.BOLD, 11));
		}  
		
		if (relRig.getOccurrences().size()>0) {
			result += RelRigAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relRig.getOccurrences().size()+" items found.\n";
			totalOccurrences += relRig.getOccurrences().size();
			lblRelRigRes.setText("("+relRig.getOccurrences().size()+")");
			cbxRelRig.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (relSpec.getOccurrences().size()>0) {
			result += RelSpecAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relSpec.getOccurrences().size()+" items found.\n";
			totalOccurrences += relSpec.getOccurrences().size();
			lblRelSpecRes.setText("("+relSpec.getOccurrences().size()+")");
			cbxRelSpec.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (repRel.getOccurrences().size()>0) {
			result += RepRelAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+repRel.getOccurrences().size()+" items found.\n";
			totalOccurrences += repRel.getOccurrences().size();
			lblRepRelRes.setText("("+repRel.getOccurrences().size()+")");		
			cbxRepRel.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (undefFormal.getOccurrences().size()>0) {
			result += UndefFormalAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+undefFormal.getOccurrences().size()+" items found.\n";
			totalOccurrences += undefFormal.getOccurrences().size();
			lblUndefFormalRes.setText("("+undefFormal.getOccurrences().size()+")");
			cbxUndefFormal.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (undefPhase.getOccurrences().size()>0) {
			result += UndefPhaseAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+undefPhase.getOccurrences().size()+" items found.\n";
			totalOccurrences += undefPhase.getOccurrences().size();
			lblUndefPhaseRes.setText("("+undefPhase.getOccurrences().size()+")");
			cbxUndefPhase.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		if (wholeOver.getOccurrences().size()>0) {
			result += WholeOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+wholeOver.getOccurrences().size()+" items found.\n";
			totalOccurrences += wholeOver.getOccurrences().size();
			lblWholeOverRes.setText("("+wholeOver.getOccurrences().size()+")");
			cbxWholeOver.setFont(new Font("Tahoma", Font.BOLD, 11));
		}   
		
		frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		identifyButton.setEnabled(true);
		progressBar.setValue(100);
		progressBarDescr.setText("Completed. "+totalOccurrences+" occurrences found.");
						
		AntiPatternList antipatternList = new AntiPatternList (assCyc, binOver, depPhase, freeRole, gsRig, hetColl, homoFunc, impAbs, impPart, mixIden,
															   mixRig, multiDep, relComp, relOver, relRig, relSpec, repRel, undefFormal, undefPhase, wholeOver	);

		ProjectBrowser.setAntiPatternListFor(frame.getDiagramManager().getCurrentProject(),antipatternList);

		//frame.getDiagramManager().openAntiPatternManager();
				
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Anti-Pattern Search",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}			
	}
}
