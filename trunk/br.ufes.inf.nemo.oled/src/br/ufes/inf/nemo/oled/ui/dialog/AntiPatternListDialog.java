package br.ufes.inf.nemo.oled.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
	
	private JLabel lblAssCycIco;	
	private JLabel lblBinOverIco;	
	private JLabel lblDepPhaseIco;
	private JLabel lblFreeRoleIco;
	private JLabel lblGSRigIco;
	private JLabel lblHetCollIco;
	private JLabel lblHomoFuncIco;
	private JLabel lblImpAbsIco;
	private JLabel lblImpPartIco;
	private JLabel lblMixIdenIco;
	private JLabel lblMixRigIco;
	private JLabel lblMultiDepIco;
	private JLabel lblRelCompIco;
	private JLabel lblRelOverIco;
	private JLabel lblRelRigIco;
	private JLabel lblRelSpecIco;
	private JLabel lblRepRelIco;
	private JLabel lblUndefFormalIco;
	private JLabel lblUndefPhaseIco;
	private JLabel lblWholeOverIco;
		
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
		setBounds(100, 100, 757, 485);
		 
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(200, 365));
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
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChooseWhichAntipattern, GroupLayout.PREFERRED_SIZE, 698, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 690, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnEnableall)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDisableall)))))
					.addGap(21))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblChooseWhichAntipattern)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(rightPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
						.addComponent(leftPanel, Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEnableall)
						.addComponent(btnDisableall))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		
		cbxMixRig = new JCheckBox(MixRigAntipattern.getAntipatternInfo().getAcronym()+" : "+MixRigAntipattern.getAntipatternInfo().getName());
		cbxMixRig.setBackground(UIManager.getColor("Panel.background"));
		
		cbxMultiDep = new JCheckBox("MultiDep : Multiple Relational Dependecy\r\n");
		cbxMultiDep.setBackground(UIManager.getColor("Panel.background"));
	
		lblMixRigIco = new JLabel();
		lblMixRigIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		lblMultiDepIco = new JLabel();
		lblMultiDepIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxRelComp = new JCheckBox(RelCompAntipattern.getAntipatternInfo().getAcronym()+" : "+RelCompAntipattern.getAntipatternInfo().getName());
		cbxRelComp.setBackground(UIManager.getColor("Panel.background"));
		
		lblRelCompIco = new JLabel();
		lblRelCompIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxRelOver = new JCheckBox(RelOverAntipattern.getAntipatternInfo().getAcronym()+" : "+RelOverAntipattern.getAntipatternInfo().getName());
		cbxRelOver.setBackground(UIManager.getColor("Panel.background"));
		
		lblRelOverIco = new JLabel();
		lblRelOverIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxRelRig = new JCheckBox(RelRigAntipattern.getAntipatternInfo().getAcronym()+" : "+RelRigAntipattern.getAntipatternInfo().getName());
		cbxRelRig.setBackground(UIManager.getColor("Panel.background"));
		
		lblRelRigIco = new JLabel();
		lblRelRigIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxRelSpec = new JCheckBox(RelSpecAntipattern.getAntipatternInfo().getAcronym()+" : "+RelSpecAntipattern.getAntipatternInfo().getName());
		cbxRelSpec.setBackground(UIManager.getColor("Panel.background"));
		
		lblRelSpecIco = new JLabel();
		lblRelSpecIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxRepRel = new JCheckBox(RepRelAntipattern.getAntipatternInfo().getAcronym()+" : "+RepRelAntipattern.getAntipatternInfo().getName());
		cbxRepRel.setBackground(UIManager.getColor("Panel.background"));
		
		lblRepRelIco = new JLabel();
		lblRepRelIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxUndefFormal = new JCheckBox(UndefFormalAntipattern.getAntipatternInfo().getAcronym()+" : "+UndefFormalAntipattern.getAntipatternInfo().getName());
		cbxUndefFormal.setBackground(UIManager.getColor("Panel.background"));
		
		lblUndefFormalIco = new JLabel();
		lblUndefFormalIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxUndefPhase = new JCheckBox(UndefPhaseAntipattern.getAntipatternInfo().getAcronym()+" : "+UndefPhaseAntipattern.getAntipatternInfo().getName());
		cbxUndefPhase.setBackground(UIManager.getColor("Panel.background"));
		
		lblUndefPhaseIco = new JLabel();
		lblUndefPhaseIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxWholeOver = new JCheckBox(WholeOverAntipattern.getAntipatternInfo().getAcronym()+" : "+WholeOverAntipattern.getAntipatternInfo().getName());
		cbxWholeOver.setBackground(UIManager.getColor("Panel.background"));
		
		lblWholeOverIco = new JLabel();
		lblWholeOverIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		lblMixRigRes = new JLabel("");
		lblMixRigRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMixRigRes.setForeground(Color.BLUE);
		
		lblMultiDepRes = new JLabel("");
		lblMultiDepRes.setForeground(Color.BLUE);
		lblMultiDepRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblRelCompRes = new JLabel("");
		lblRelCompRes.setForeground(Color.BLUE);
		lblRelCompRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblRelOverRes = new JLabel("");
		lblRelOverRes.setForeground(Color.BLACK);
		lblRelOverRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblRelRigRes = new JLabel("");
		lblRelRigRes.setForeground(Color.BLUE);
		lblRelRigRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblRelSpecRes = new JLabel("");
		lblRelSpecRes.setForeground(Color.BLUE);
		lblRelSpecRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblRepRelRes = new JLabel("");
		lblRepRelRes.setForeground(Color.BLUE);
		lblRepRelRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblUndefFormalRes = new JLabel("");
		lblUndefFormalRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUndefFormalRes.setForeground(Color.BLUE);
		
		lblUndefPhaseRes = new JLabel("");
		lblUndefPhaseRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUndefPhaseRes.setForeground(Color.BLUE);
		
		lblWholeOverRes = new JLabel("");
		lblWholeOverRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWholeOverRes.setForeground(Color.BLUE);
		
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(cbxMixRig)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMixRigIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMixRigRes, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(cbxMultiDep)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMultiDepIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMultiDepRes, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(cbxRelComp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRelCompIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRelCompRes, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(cbxRelOver)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRelOverIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRelOverRes, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(cbxRepRel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRepRelIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRepRelRes, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(cbxWholeOver)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblWholeOverIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblWholeOverRes, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(cbxRelRig)
								.addGroup(gl_rightPanel.createSequentialGroup()
									.addComponent(cbxRelSpec)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblRelSpecIco)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_rightPanel.createSequentialGroup()
									.addComponent(lblRelRigIco)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblRelRigRes, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblRelSpecRes, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(cbxUndefPhase)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUndefPhaseIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUndefPhaseRes, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(cbxUndefFormal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUndefFormalIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUndefFormalRes, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxMixRig)
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblMixRigRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblMixRigIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(1)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxMultiDep)
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblMultiDepRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblMultiDepIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxRelComp)
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblRelCompRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblRelCompIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxRelOver)
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblRelOverRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblRelOverIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addComponent(cbxRelRig)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRelSpecIco, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxRelSpec)))
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblRelRigRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblRelRigIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblRelSpecRes, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxRepRel)
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblRepRelRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblRepRelIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxUndefFormal)
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblUndefFormalRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblUndefFormalIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxUndefPhase)
						.addComponent(lblUndefPhaseIco)
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addGap(2)
							.addComponent(lblUndefPhaseRes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxWholeOver)
						.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblWholeOverRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addComponent(lblWholeOverIco, Alignment.LEADING)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		rightPanel.setLayout(gl_rightPanel);
		
		cbxAssCyc = new JCheckBox(AssCycAntipattern.getAntipatternInfo().getAcronym()+" : "+AssCycAntipattern.getAntipatternInfo().getName());
		cbxAssCyc.setBackground(UIManager.getColor("Panel.background"));
		
		lblAssCycIco = new JLabel();
		lblAssCycIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxBinOver = new JCheckBox(BinOverAntipattern.getAntipatternInfo().getAcronym()+" : "+BinOverAntipattern.getAntipatternInfo().getName());
		cbxBinOver.setBackground(UIManager.getColor("Panel.background"));
		
		lblBinOverIco = new JLabel();
		lblBinOverIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxDepPhase = new JCheckBox(DepPhaseAntipattern.getAntipatternInfo().getAcronym()+" : "+DepPhaseAntipattern.getAntipatternInfo().getName());
		cbxDepPhase.setBackground(UIManager.getColor("Panel.background"));
		
		lblDepPhaseIco = new JLabel();
		lblDepPhaseIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxFreeRole = new JCheckBox(FreeRoleAntipattern.getAntipatternInfo().getAcronym()+" : "+FreeRoleAntipattern.getAntipatternInfo().getName());
		cbxFreeRole.setBackground(UIManager.getColor("Panel.background"));
		
		lblFreeRoleIco = new JLabel();
		lblFreeRoleIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxGSRig = new JCheckBox(GSRigAntipattern.getAntipatternInfo().getAcronym()+" : "+GSRigAntipattern.getAntipatternInfo().getName());
		cbxGSRig.setBackground(UIManager.getColor("Panel.background"));
		
		lblGSRigIco = new JLabel();
		lblGSRigIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxHetColl = new JCheckBox(HetCollAntipattern.getAntipatternInfo().getAcronym()+" : "+HetCollAntipattern.getAntipatternInfo().getName());
		cbxHetColl.setBackground(UIManager.getColor("Panel.background"));
		
		lblHetCollIco = new JLabel();
		lblHetCollIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxHomoFunc = new JCheckBox(HomoFuncAntipattern.getAntipatternInfo().getAcronym()+" : "+HomoFuncAntipattern.getAntipatternInfo().getName());
		cbxHomoFunc.setBackground(UIManager.getColor("Panel.background"));
		
		lblHomoFuncIco = new JLabel();
		lblHomoFuncIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxImpAbs = new JCheckBox(ImpAbsAntipattern.getAntipatternInfo().getAcronym()+" : "+ImpAbsAntipattern.getAntipatternInfo().getName());
		cbxImpAbs.setBackground(UIManager.getColor("Panel.background"));
		
		lblImpAbsIco = new JLabel();
		lblImpAbsIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxImpPart = new JCheckBox(ImpPartAntipattern.getAntipatternInfo().getAcronym()+" : "+ImpPartAntipattern.getAntipatternInfo().getName());
		cbxImpPart.setBackground(UIManager.getColor("Panel.background"));
		
		lblImpPartIco = new JLabel();
		lblImpPartIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		cbxMixIden = new JCheckBox(MixIdenAntipattern.getAntipatternInfo().getAcronym()+" : "+MixIdenAntipattern.getAntipatternInfo().getName());
		cbxMixIden.setBackground(UIManager.getColor("Panel.background"));
		
		lblMixIdenIco = new JLabel();
		lblMixIdenIco.setIcon(new ImageIcon(AntiPatternListDialog.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/annotation.png")));
		
		lblAssCycRes = new JLabel("");		
		lblAssCycRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAssCycRes.setForeground(Color.BLUE);
		
		lblBinOverRes = new JLabel("");		
		lblBinOverRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBinOverRes.setForeground(Color.BLUE);
		
		lblDepPhaseRes = new JLabel("");		
		lblDepPhaseRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDepPhaseRes.setForeground(Color.BLUE);
		
		lblFreeRoleRes = new JLabel("");		
		lblFreeRoleRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFreeRoleRes.setForeground(Color.BLUE);
		
		lblGSRigRes = new JLabel("");
		lblGSRigRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGSRigRes.setForeground(Color.BLUE);		
		
		lblHetCollRes = new JLabel("");
		lblHetCollRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHetCollRes.setForeground(Color.BLUE);		
		
		lblHomoFuncRes = new JLabel("");
		lblHomoFuncRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHomoFuncRes.setForeground(Color.BLUE);		
		
		lblImpAbsRes = new JLabel("");
		lblImpAbsRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImpAbsRes.setForeground(Color.BLUE);		
		
		lblImpPartRes = new JLabel("");
		lblImpPartRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImpPartRes.setForeground(Color.BLUE);		
		
		lblMixIdenRes = new JLabel("");
		lblMixIdenRes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMixIdenRes.setForeground(Color.BLUE);
		
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addComponent(cbxAssCyc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAssCycIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAssCycRes, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addComponent(cbxBinOver)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBinOverIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBinOverRes, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addComponent(cbxDepPhase)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDepPhaseIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDepPhaseRes, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addComponent(cbxFreeRole)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFreeRoleIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFreeRoleRes, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addComponent(cbxGSRig)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblGSRigIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblGSRigRes, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addComponent(cbxHetColl)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHetCollIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHetCollRes, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addComponent(cbxHomoFunc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHomoFuncIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHomoFuncRes, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addComponent(cbxImpAbs)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblImpAbsIco)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblImpAbsRes, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_leftPanel.createSequentialGroup()
									.addComponent(cbxMixIden)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblMixIdenIco, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(cbxImpPart))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_leftPanel.createSequentialGroup()
									.addComponent(lblImpPartIco)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblImpPartRes, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblMixIdenRes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxAssCyc)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblAssCycRes, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAssCycIco, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxBinOver)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblBinOverRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblBinOverIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxDepPhase)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblDepPhaseRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblDepPhaseIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxFreeRole)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblFreeRoleRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblFreeRoleIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxGSRig)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblGSRigRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblGSRigIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(cbxHetColl)
								.addComponent(lblHetCollIco, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_leftPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHetCollRes, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxHomoFunc)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblHomoFuncRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblHomoFuncIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxImpAbs)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblImpAbsRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblImpAbsIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cbxImpPart)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblImpPartRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblImpPartIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_leftPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cbxMixIden, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_leftPanel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblMixIdenRes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblMixIdenIco, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		leftPanel.setLayout(gl_leftPanel);
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
