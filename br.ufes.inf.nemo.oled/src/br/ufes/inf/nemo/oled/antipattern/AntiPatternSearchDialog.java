package br.ufes.inf.nemo.oled.antipattern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.decint.DecIntAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.partover.PartOverAntipattern;
import br.ufes.inf.nemo.antipattern.relcomp.RelCompAntipattern;
import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.wholeover.WholeOverAntipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.AntiPatternList;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class AntiPatternSearchDialog extends JDialog {

	private static final long serialVersionUID = 1L;
		
	private AppFrame frame;	
	private final JPanel contentPanel = new JPanel();
	
	private Thread searchThread;
	
	private Thread AssCycThread;
	private Thread BinOverThread;
	private Thread DepPhaseThread;
	private Thread FreeRoleThread;
	private Thread GSRigThread;
	private Thread HetCollThread;
	private Thread HomoFuncThread;
	private Thread ImpAbsThread;
	private Thread MixIdenThread;
	private Thread MixRigThread;
	private Thread MultiDepThread;
	private Thread RelCompThread;
	private Thread RelOverThread;
	private Thread RelRigThread;
	private Thread RelSpecThread;
	private Thread RepRelThread;
	private Thread UndefFormalThread;
	private Thread UndefPhaseThread;
	private Thread WholeOverThread;
	private Thread PartOverThread;
	private Thread DecIntThread;
	
	private JCheckBox cbxAssCyc;	
	private JCheckBox cbxBinOver;	
	private JCheckBox cbxDepPhase;
	private JCheckBox cbxFreeRole;
	private JCheckBox cbxGSRig;
	private JCheckBox cbxHetColl;
	private JCheckBox cbxHomoFunc;
	private JCheckBox cbxImpAbs;
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
	private JCheckBox cbxPartOver;
	private JCheckBox cbxDecInt;
	
	private JButton lblAssCycIco;	
	private JButton lblBinOverIco;	
	private JButton lblDepPhaseIco;
	private JButton lblFreeRoleIco;
	private JButton lblGSRigIco;
	private JButton lblHetCollIco;
	private JButton lblHomoFuncIco;
	private JButton lblImpAbsIco;
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
	private JButton lblPartOverIco;
	private JButton lblDecIntIco;
		
	private DefaultBoundedRangeModel progressModel = new DefaultBoundedRangeModel();
	private JProgressBar progressBar = new JProgressBar(progressModel);
	private JLabel progressBarDescr;
		
	private JLabel lblAssCycRes;
	private JLabel lblBinOverRes;
	private JLabel lblDepPhaseRes;
	private JLabel lblFreeRoleRes;
	private JLabel lblGSRigRes;
	private JLabel lblHetCollRes;
	private JLabel lblHomoFuncRes;
	private JLabel lblImpAbsRes;
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
	private JLabel lblPartOverRes;
	private JLabel lblDecIntRes;
	
	private JButton identifyButton;
	private JButton btnNewButton_1;
	private JButton btnShowResult;
	
	@SuppressWarnings("unused")
	private String result = new String();
	private int totalOccurrences = 0;
	private JPanel panel_1;
	
	/** 
	 * Check if AntiPattern is selected.
	 */
	
	public Boolean AssCycisSelected() { return cbxAssCyc.isSelected(); }
	public Boolean BinOverisSelected() { return cbxBinOver.isSelected(); }
	public Boolean DecIntisSelected() { return cbxDecInt.isSelected(); }
	public Boolean DepPhaseisSelected() { return cbxDepPhase.isSelected(); }
	public Boolean FreeRoleisSelected() { return cbxFreeRole.isSelected(); }
	public Boolean GSRigisSelected() { return cbxGSRig.isSelected(); }
	public Boolean HetCollisSelected() { return cbxHetColl.isSelected(); }
	public Boolean HomoFuncisSelected() { return cbxHomoFunc.isSelected(); }
	public Boolean ImpAbsisSelected() { return cbxImpAbs.isSelected(); }
	public Boolean MixIdenisSelected() { return cbxMixIden.isSelected(); }
	public Boolean MixRigisSelected() { return cbxMixRig.isSelected(); }
	public Boolean MultiDepisSelected() { return cbxMultiDep.isSelected(); }
	public Boolean PartOverisSelected() { return cbxPartOver.isSelected(); }
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
			
			AntiPatternSearchDialog dialog = new AntiPatternSearchDialog(parent);
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
		lblPartOverRes.setText("");
		lblDecIntRes.setText("");
	}
		
	public void ShowAllAntiPatternIconLabels(boolean show)
	{
		lblAssCycIco.setVisible(show);
		lblBinOverIco.setVisible(show);
		lblDecIntIco.setVisible(show);
		lblDepPhaseIco.setVisible(show);
		lblFreeRoleIco.setVisible(show);
		lblGSRigIco.setVisible(show);
		lblHetCollIco.setVisible(show);
		lblHomoFuncIco.setVisible(show);
		lblImpAbsIco.setVisible(show);
		lblMixIdenIco.setVisible(show);
		lblMixRigIco.setVisible(show);
		lblMultiDepIco.setVisible(show);
		lblPartOverIco.setVisible(show);
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
		cbxAssCyc.setFont(new Font(cbxAssCyc.getFont().getName(), Font.PLAIN, cbxAssCyc.getFont().getSize()));	
		cbxBinOver.setFont(new Font(cbxBinOver.getFont().getName(), Font.PLAIN, cbxBinOver.getFont().getSize()));
		cbxDecInt.setFont(new Font(cbxDecInt.getFont().getName(), Font.PLAIN, cbxDecInt.getFont().getSize()));	
		cbxDepPhase.setFont(new Font(cbxDepPhase.getFont().getName(), Font.PLAIN, cbxDepPhase.getFont().getSize()));
		cbxFreeRole.setFont(new Font(cbxFreeRole.getFont().getName(), Font.PLAIN, cbxFreeRole.getFont().getSize()));
		cbxGSRig.setFont(new Font(cbxGSRig.getFont().getName(), Font.PLAIN, cbxGSRig.getFont().getSize()));
		cbxHetColl.setFont(new Font(cbxHetColl.getFont().getName(), Font.PLAIN, cbxHetColl.getFont().getSize()));
		cbxHomoFunc.setFont(new Font(cbxHomoFunc.getFont().getName(), Font.PLAIN, cbxHomoFunc.getFont().getSize()));
		cbxImpAbs.setFont(new Font(cbxImpAbs.getFont().getName(), Font.PLAIN, cbxImpAbs.getFont().getSize()));	
		cbxMixIden.setFont(new Font(cbxMixIden.getFont().getName(), Font.PLAIN, cbxMixIden.getFont().getSize()));	
		cbxMixRig.setFont(new Font(cbxMixRig.getFont().getName(), Font.PLAIN, cbxMixRig.getFont().getSize()));	
		cbxMultiDep.setFont(new Font(cbxMultiDep.getFont().getName(), Font.PLAIN, cbxMultiDep.getFont().getSize()));
		cbxPartOver.setFont(new Font(cbxPartOver.getFont().getName(), Font.PLAIN, cbxPartOver.getFont().getSize()));
		cbxRelComp.setFont(new Font(cbxRelComp.getFont().getName(), Font.PLAIN, cbxRelComp.getFont().getSize()));	
		cbxRelOver.setFont(new Font(cbxRelOver.getFont().getName(), Font.PLAIN, cbxRelOver.getFont().getSize()));	
		cbxRelRig.setFont(new Font(cbxRelRig.getFont().getName(), Font.PLAIN, cbxRelRig.getFont().getSize()));	
		cbxRelSpec.setFont(new Font(cbxRelSpec.getFont().getName(), Font.PLAIN, cbxRelSpec.getFont().getSize()));	
		cbxRepRel.setFont(new Font(cbxRepRel.getFont().getName(), Font.PLAIN, cbxRepRel.getFont().getSize()));	
		cbxUndefFormal.setFont(new Font(cbxUndefFormal.getFont().getName(), Font.PLAIN, cbxUndefFormal.getFont().getSize()));	
		cbxUndefPhase.setFont(new Font(cbxUndefPhase.getFont().getName(), Font.PLAIN, cbxUndefPhase.getFont().getSize()));
		cbxWholeOver.setFont(new Font(cbxWholeOver.getFont().getName(), Font.PLAIN, cbxWholeOver.getFont().getSize()));	
	}
	
	/**
	 * Create the dialog.
	 */
	public AntiPatternSearchDialog(AppFrame frame) 
	{
		super(frame);
		
		this.frame = frame;
		
//		setIconImage(Toolkit.getDefaultToolkit().getImage(AntiPatternSearchDialog.class.getResource("/resources/icons/antipattern36.png")));
		Image icon = new BufferedImage(1, 1,BufferedImage.TYPE_INT_ARGB_PRE);
		setIconImage(icon);
		setTitle("Anti-Pattern Identification");
		setBounds(100, 100, 854, 511);
		 
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setPreferredSize(new Dimension(180, 410));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		
		JLabel lblChooseWhichAntipattern = new JLabel("    Choose which anti-pattern do you want to search:");;
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createTitledBorder(""));
		JPanel rightPanel = new JPanel();
		rightPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		rightPanel.setBorder(BorderFactory.createTitledBorder(""));
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		panel_1 = new JPanel();
		panel_1.setBorder(BorderFactory.createTitledBorder(""));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 806, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 806, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblChooseWhichAntipattern, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)))
					.addGap(3))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChooseWhichAntipattern)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(rightPanel, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
						.addComponent(leftPanel, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		identifyButton = new JButton("Search");
		panel_1.add(identifyButton);
		identifyButton.setIcon(null);
		
		btnNewButton_1 = new JButton("Close");
		panel_1.add(btnNewButton_1);
		
		JButton btnStop = new JButton("Stop");
		panel_1.add(btnStop);
		
		btnShowResult = new JButton("Show Result");
		panel_1.add(btnShowResult);
		btnShowResult.setEnabled(false);		
		btnShowResult.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				//dispose();
				showResult();
			}
		});
		btnStop.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				interruptAll();
				if (searchThread!=null) searchThread.interrupt();				
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				interruptAll();
				if (searchThread!=null) searchThread.interrupt();				
				dispose();
				
			}
		});
		
		identifyButton.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			IdentifyButtonActionPerformed(event);				
       		}
       	});
		JButton btnEnableall = new JButton("Enable All");
		panel.add(btnEnableall);
		JButton btnDisableall = new JButton("Disable All");
		panel.add(btnDisableall);
		
		btnDisableall.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if (AssCycisSelected()) cbxAssCyc.setSelected(false);
       			if (BinOverisSelected()) cbxBinOver.setSelected(false);
       			if (DecIntisSelected()) cbxDecInt.setSelected(false);
       			if (DepPhaseisSelected()) cbxDepPhase.setSelected(false);
       			if (FreeRoleisSelected()) cbxFreeRole.setSelected(false);
       			if (GSRigisSelected()) cbxGSRig.setSelected(false);
       			if (HetCollisSelected()) cbxHetColl.setSelected(false);
       			if (HomoFuncisSelected()) cbxHomoFunc.setSelected(false);
       			if (ImpAbsisSelected()) cbxImpAbs.setSelected(false);
       			if (MixIdenisSelected()) cbxMixIden.setSelected(false);
       			if (MixRigisSelected()) cbxMixRig.setSelected(false);
       			if (MultiDepisSelected()) cbxMultiDep.setSelected(false);
       			if (PartOverisSelected()) cbxPartOver.setSelected(false);
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
		
		btnEnableall.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			if (!AssCycisSelected()) cbxAssCyc.setSelected(true);
       			if (!BinOverisSelected()) cbxBinOver.setSelected(true);
       			if (!DecIntisSelected()) cbxDecInt.setSelected(true);
       			if (!DepPhaseisSelected()) cbxDepPhase.setSelected(true);
       			if (!FreeRoleisSelected()) cbxFreeRole.setSelected(true);
       			if (!GSRigisSelected()) cbxGSRig.setSelected(true);
       			if (!HetCollisSelected()) cbxHetColl.setSelected(true);
       			if (!HomoFuncisSelected()) cbxHomoFunc.setSelected(true);
       			if (!ImpAbsisSelected()) cbxImpAbs.setSelected(true);
       			if (!MixIdenisSelected()) cbxMixIden.setSelected(true);
       			if (!MixRigisSelected()) cbxMixRig.setSelected(true);
       			if (!MultiDepisSelected()) cbxMultiDep.setSelected(true);
       			if (!PartOverisSelected()) cbxPartOver.setSelected(true);
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
		
		cbxMultiDep = new JCheckBox(MultiDepAntipattern.getAntipatternInfo().getAcronym()+": "+MultiDepAntipattern.getAntipatternInfo().getName());
		cbxMultiDep.setPreferredSize(new Dimension(265, 20));
		cbxMultiDep.setBackground(UIManager.getColor("Panel.background"));
		lblMultiDepRes = new JLabel("");
		lblMultiDepRes.setPreferredSize(new Dimension(75, 20));
		lblMultiDepRes.setForeground(Color.BLUE);
		lblMultiDepIco = new JButton();
		lblMultiDepIco.setPreferredSize(new Dimension(20, 20));
		lblMultiDepIco.setOpaque(false);
		lblMultiDepIco.setContentAreaFilled(false);
		lblMultiDepIco.setBorderPainted(false);
		lblMultiDepIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblMultiDepIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblMultiDepIco);
		rightPanel.add(cbxMultiDep);
		rightPanel.add(lblMultiDepRes);
		
		cbxRelComp = new JCheckBox(RelCompAntipattern.getAntipatternInfo().getAcronym()+": "+RelCompAntipattern.getAntipatternInfo().getName());
		cbxRelComp.setPreferredSize(new Dimension(225, 20));
		cbxRelComp.setBackground(UIManager.getColor("Panel.background"));
		lblRelCompRes = new JLabel("");
		lblRelCompRes.setPreferredSize(new Dimension(115, 20));
		lblRelCompRes.setForeground(Color.BLUE);
		lblPartOverIco = new JButton();
		lblPartOverIco.setPreferredSize(new Dimension(20, 20));
		lblPartOverIco.setOpaque(false);
		lblPartOverIco.setContentAreaFilled(false);
		lblPartOverIco.setBorderPainted(false);
		lblPartOverIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblPartOverIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblPartOverIco);
		
		cbxPartOver = new JCheckBox(PartOverAntipattern.getAntipatternInfo().getAcronym()+": "+PartOverAntipattern.getAntipatternInfo().getName());
		cbxPartOver.setPreferredSize(new Dimension(320, 20));
		cbxPartOver.setBackground(UIManager.getColor("Panel.background"));
		rightPanel.add(cbxPartOver);
		lblPartOverRes = new JLabel("");
		lblPartOverRes.setPreferredSize(new Dimension(20, 20));		
		lblPartOverRes.setForeground(Color.BLUE);
		rightPanel.add(lblPartOverRes);
		lblRelCompIco = new JButton();
		lblRelCompIco.setPreferredSize(new Dimension(20, 20));
		lblRelCompIco.setOpaque(false);
		lblRelCompIco.setContentAreaFilled(false);
		lblRelCompIco.setBorderPainted(false);
		lblRelCompIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblRelCompIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblRelCompIco);
		rightPanel.add(cbxRelComp);
		rightPanel.add(lblRelCompRes);
		
		cbxRelOver = new JCheckBox(RelOverAntipattern.getAntipatternInfo().getAcronym()+": "+RelOverAntipattern.getAntipatternInfo().getName());
		cbxRelOver.setPreferredSize(new Dimension(297, 20));
		cbxRelOver.setBackground(UIManager.getColor("Panel.background"));
		lblRelOverRes = new JLabel("");
		lblRelOverRes.setPreferredSize(new Dimension(43, 20));
		lblRelOverRes.setForeground(Color.BLUE);		
		lblRelOverIco  = new JButton();
		lblRelOverIco.setPreferredSize(new Dimension(20, 20));
		lblRelOverIco.setOpaque(false);
		lblRelOverIco.setContentAreaFilled(false);
		lblRelOverIco.setBorderPainted(false);
		lblRelOverIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblRelOverIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblRelOverIco);
		rightPanel.add(cbxRelOver);
		rightPanel.add(lblRelOverRes);
		lblRelRigIco = new JButton();
		lblRelRigIco.setPreferredSize(new Dimension(20, 20));
		lblRelRigIco.setOpaque(false);
		lblRelRigIco.setContentAreaFilled(false);
		lblRelRigIco.setBorderPainted(false);
		lblRelRigIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblRelRigIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblRelRigIco);
		
		cbxRelRig = new JCheckBox(RelRigAntipattern.getAntipatternInfo().getAcronym()+": "+RelRigAntipattern.getAntipatternInfo().getName());
		cbxRelRig.setPreferredSize(new Dimension(255, 20));
		cbxRelRig.setBackground(UIManager.getColor("Panel.background"));
		rightPanel.add(cbxRelRig);
		lblRelRigRes = new JLabel("");
		lblRelRigRes.setPreferredSize(new Dimension(85, 20));
		lblRelRigRes.setForeground(Color.BLUE);		
		rightPanel.add(lblRelRigRes);
		
		cbxRelSpec = new JCheckBox(RelSpecAntipattern.getAntipatternInfo().getAcronym()+": "+RelSpecAntipattern.getAntipatternInfo().getName());
		cbxRelSpec.setPreferredSize(new Dimension(225, 20));
		cbxRelSpec.setBackground(UIManager.getColor("Panel.background"));
		lblRelSpecRes = new JLabel("");
		lblRelSpecRes.setPreferredSize(new Dimension(115, 20));
		lblRelSpecRes.setForeground(Color.BLUE);
		lblRelSpecIco = new JButton();
		lblRelSpecIco.setPreferredSize(new Dimension(20, 20));
		lblRelSpecIco.setOpaque(false);
		lblRelSpecIco.setContentAreaFilled(false);
		lblRelSpecIco.setBorderPainted(false);
		lblRelSpecIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblRelSpecIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblRelSpecIco);
		rightPanel.add(cbxRelSpec);
		rightPanel.add(lblRelSpecRes);
		
		cbxRepRel = new JCheckBox(RepRelAntipattern.getAntipatternInfo().getAcronym()+": "+RepRelAntipattern.getAntipatternInfo().getName());
		cbxRepRel.setPreferredSize(new Dimension(260, 20));
		cbxRepRel.setBackground(UIManager.getColor("Panel.background"));
		lblRepRelRes = new JLabel("");
		lblRepRelRes.setPreferredSize(new Dimension(80, 20));
		lblRepRelRes.setForeground(Color.BLUE);	
		lblRepRelIco = new JButton();
		lblRepRelIco.setPreferredSize(new Dimension(20, 20));
		lblRepRelIco.setOpaque(false);
		lblRepRelIco.setContentAreaFilled(false);
		lblRepRelIco.setBorderPainted(false);
		lblRepRelIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblRepRelIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblRepRelIco);
		rightPanel.add(cbxRepRel);
		rightPanel.add(lblRepRelRes);
		
		cbxUndefFormal = new JCheckBox(UndefFormalAntipattern.getAntipatternInfo().getAcronym()+": "+UndefFormalAntipattern.getAntipatternInfo().getName());	
		cbxUndefFormal.setPreferredSize(new Dimension(320, 20));
		cbxUndefFormal.setBackground(UIManager.getColor("Panel.background"));
		lblUndefFormalRes = new JLabel("");
		lblUndefFormalRes.setPreferredSize(new Dimension(20, 20));		
		lblUndefFormalRes.setForeground(Color.BLUE);
		lblUndefFormalIco = new JButton();
		lblUndefFormalIco.setPreferredSize(new Dimension(20, 20));
		lblUndefFormalIco.setOpaque(false);
		lblUndefFormalIco.setContentAreaFilled(false);
		lblUndefFormalIco.setBorderPainted(false);
		lblUndefFormalIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblUndefFormalIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblUndefFormalIco);
		rightPanel.add(cbxUndefFormal);
		rightPanel.add(lblUndefFormalRes);
		
		cbxUndefPhase = new JCheckBox(UndefPhaseAntipattern.getAntipatternInfo().getAcronym()+": "+UndefPhaseAntipattern.getAntipatternInfo().getName());
		cbxUndefPhase.setPreferredSize(new Dimension(265, 20));
		cbxUndefPhase.setBackground(UIManager.getColor("Panel.background"));
		lblUndefPhaseRes = new JLabel("");
		lblUndefPhaseRes.setPreferredSize(new Dimension(75, 20));		
		lblUndefPhaseRes.setForeground(Color.BLUE);
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblUndefPhaseIco = new JButton();
		lblUndefPhaseIco.setPreferredSize(new Dimension(20, 20));
		lblUndefPhaseIco.setOpaque(false);
		lblUndefPhaseIco.setContentAreaFilled(false);
		lblUndefPhaseIco.setBorderPainted(false);
		lblUndefPhaseIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblUndefPhaseIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblUndefPhaseIco);
		rightPanel.add(cbxUndefPhase);
		rightPanel.add(lblUndefPhaseRes);
		lblWholeOverIco = new JButton();
		lblWholeOverIco.setPreferredSize(new Dimension(20, 20));
		lblWholeOverIco.setOpaque(false);
		lblWholeOverIco.setContentAreaFilled(false);
		lblWholeOverIco.setBorderPainted(false);
		lblWholeOverIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblWholeOverIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		rightPanel.add(lblWholeOverIco);
		
		cbxWholeOver = new JCheckBox(WholeOverAntipattern.getAntipatternInfo().getAcronym()+": "+WholeOverAntipattern.getAntipatternInfo().getName());
		cbxWholeOver.setPreferredSize(new Dimension(320, 20));
		cbxWholeOver.setBackground(UIManager.getColor("Panel.background"));
		rightPanel.add(cbxWholeOver);
		lblWholeOverRes = new JLabel("");
		lblWholeOverRes.setPreferredSize(new Dimension(20, 20));		
		lblWholeOverRes.setForeground(Color.BLUE);
		rightPanel.add(lblWholeOverRes);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPanel.setLayout(gl_contentPanel);		
		lblAssCycIco = new JButton();
		lblAssCycIco.setPreferredSize(new Dimension(20, 20));
		lblAssCycIco.setOpaque(false);
		lblAssCycIco.setContentAreaFilled(false);
		lblAssCycIco.setBorderPainted(false);
		lblAssCycIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblAssCycIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblAssCycIco);
		
		cbxAssCyc = new JCheckBox(AssCycAntipattern.getAntipatternInfo().getAcronym()+": "+AssCycAntipattern.getAntipatternInfo().getName());
		cbxAssCyc.setPreferredSize(new Dimension(185, 20));
		cbxAssCyc.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxAssCyc);
		lblAssCycRes = new JLabel("");		
		lblAssCycRes.setPreferredSize(new Dimension(155, 20));		
		lblAssCycRes.setForeground(Color.BLUE);
		leftPanel.add(lblAssCycRes);
		lblBinOverIco = new JButton();
		lblBinOverIco.setPreferredSize(new Dimension(20, 20));
		lblBinOverIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblBinOverIco.setOpaque(false);
		lblBinOverIco.setContentAreaFilled(false);
		lblBinOverIco.setBorderPainted(false);
		lblBinOverIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblBinOverIco);
		
		cbxBinOver = new JCheckBox(BinOverAntipattern.getAntipatternInfo().getAcronym()+": "+BinOverAntipattern.getAntipatternInfo().getName());	
		cbxBinOver.setPreferredSize(new Dimension(305, 20));
		cbxBinOver.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxBinOver);
		lblBinOverRes = new JLabel("");		
		lblBinOverRes.setPreferredSize(new Dimension(35, 20));		
		lblBinOverRes.setForeground(Color.BLUE);
		leftPanel.add(lblBinOverRes);
		
		lblDecIntIco = new JButton();
		lblDecIntIco.setPreferredSize(new Dimension(20, 20));
		lblDecIntIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblDecIntIco.setOpaque(false);
		lblDecIntIco.setContentAreaFilled(false);
		lblDecIntIco.setBorderPainted(false);
		lblDecIntIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblDecIntIco);
		
		cbxDecInt = new JCheckBox(DecIntAntipattern.getAntipatternInfo().getAcronym()+": "+DecIntAntipattern.getAntipatternInfo().getName());		
		cbxDecInt.setPreferredSize(new Dimension(250, 20));
		cbxDecInt.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxDecInt);
		lblDecIntRes = new JLabel("");
		lblDecIntRes.setPreferredSize(new Dimension(90, 20));		
		lblDecIntRes.setForeground(Color.BLUE);
		leftPanel.add(lblDecIntRes);
		
		lblDepPhaseIco = new JButton();
		lblDepPhaseIco.setPreferredSize(new Dimension(20, 20));
		lblDepPhaseIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblDepPhaseIco.setOpaque(false);
		lblDepPhaseIco.setContentAreaFilled(false);
		lblDepPhaseIco.setBorderPainted(false);
		lblDepPhaseIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblDepPhaseIco);
		
		cbxDepPhase = new JCheckBox(DepPhaseAntipattern.getAntipatternInfo().getAcronym()+": "+DepPhaseAntipattern.getAntipatternInfo().getName());
		cbxDepPhase.setPreferredSize(new Dimension(275, 20));
		cbxDepPhase.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxDepPhase);
		lblDepPhaseRes = new JLabel("");		
		lblDepPhaseRes.setPreferredSize(new Dimension(65, 20));		
		lblDepPhaseRes.setForeground(Color.BLUE);
		leftPanel.add(lblDepPhaseRes);
		lblFreeRoleIco = new JButton();
		lblFreeRoleIco.setPreferredSize(new Dimension(20, 20));
		lblFreeRoleIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblFreeRoleIco.setOpaque(false);
		lblFreeRoleIco.setContentAreaFilled(false);
		lblFreeRoleIco.setBorderPainted(false);
		lblFreeRoleIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblFreeRoleIco);
		
		cbxFreeRole = new JCheckBox(FreeRoleAntipattern.getAntipatternInfo().getAcronym()+": "+FreeRoleAntipattern.getAntipatternInfo().getName());	
		cbxFreeRole.setPreferredSize(new Dimension(230, 20));
		cbxFreeRole.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxFreeRole);
		lblFreeRoleRes = new JLabel("");		
		lblFreeRoleRes.setPreferredSize(new Dimension(110, 20));		
		lblFreeRoleRes.setForeground(Color.BLUE);
		leftPanel.add(lblFreeRoleRes);
		lblGSRigIco = new JButton();
		lblGSRigIco.setPreferredSize(new Dimension(20, 20));
		lblGSRigIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblGSRigIco.setOpaque(false);
		lblGSRigIco.setContentAreaFilled(false);
		lblGSRigIco.setBorderPainted(false);
		lblGSRigIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblGSRigIco);
		
		cbxGSRig = new JCheckBox("GSRig: Generalization Set with Mixed Rigidity");		
		cbxGSRig.setPreferredSize(new Dimension(300, 20));
		cbxGSRig.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxGSRig);
		lblGSRigRes = new JLabel("");
		lblGSRigRes.setPreferredSize(new Dimension(40, 20));		
		lblGSRigRes.setForeground(Color.BLUE);		
		leftPanel.add(lblGSRigRes);
		lblHetCollIco = new JButton();
		lblHetCollIco.setPreferredSize(new Dimension(20, 20));
		lblHetCollIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblHetCollIco.setOpaque(false);
		lblHetCollIco.setContentAreaFilled(false);
		lblHetCollIco.setBorderPainted(false);
		lblHetCollIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblHetCollIco);
		
		cbxHetColl = new JCheckBox(HetCollAntipattern.getAntipatternInfo().getAcronym()+": "+HetCollAntipattern.getAntipatternInfo().getName());		
		cbxHetColl.setPreferredSize(new Dimension(230, 20));
		cbxHetColl.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxHetColl);
		lblHetCollRes = new JLabel("");
		lblHetCollRes.setPreferredSize(new Dimension(110, 20));		
		lblHetCollRes.setForeground(Color.BLUE);
		leftPanel.add(lblHetCollRes);
		lblHomoFuncIco = new JButton();
		lblHomoFuncIco.setPreferredSize(new Dimension(20, 20));
		lblHomoFuncIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblHomoFuncIco.setOpaque(false);
		lblHomoFuncIco.setContentAreaFilled(false);
		lblHomoFuncIco.setBorderPainted(false);
		lblHomoFuncIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblHomoFuncIco);
		
		cbxHomoFunc = new JCheckBox(HomoFuncAntipattern.getAntipatternInfo().getAcronym()+": "+HomoFuncAntipattern.getAntipatternInfo().getName());	
		cbxHomoFunc.setPreferredSize(new Dimension(300, 20));
		cbxHomoFunc.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxHomoFunc);
		lblHomoFuncRes = new JLabel("");
		lblHomoFuncRes.setPreferredSize(new Dimension(40, 20));		
		lblHomoFuncRes.setForeground(Color.BLUE);		
		leftPanel.add(lblHomoFuncRes);
		lblImpAbsIco  = new JButton();
		lblImpAbsIco.setPreferredSize(new Dimension(20, 20));
		lblImpAbsIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblImpAbsIco.setOpaque(false);
		lblImpAbsIco.setContentAreaFilled(false);
		lblImpAbsIco.setBorderPainted(false);
		lblImpAbsIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblImpAbsIco);
		
		cbxImpAbs = new JCheckBox(ImpAbsAntipattern.getAntipatternInfo().getAcronym()+": "+ImpAbsAntipattern.getAntipatternInfo().getName());		
		cbxImpAbs.setPreferredSize(new Dimension(220, 20));
		cbxImpAbs.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxImpAbs);
		lblImpAbsRes = new JLabel("");
		lblImpAbsRes.setPreferredSize(new Dimension(120, 20));		
		lblImpAbsRes.setForeground(Color.BLUE);		
		leftPanel.add(lblImpAbsRes);
		lblMixIdenIco = new JButton();
		lblMixIdenIco.setPreferredSize(new Dimension(20, 20));
		lblMixIdenIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblMixIdenIco.setOpaque(false);
		lblMixIdenIco.setContentAreaFilled(false);
		lblMixIdenIco.setBorderPainted(false);
		lblMixIdenIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		leftPanel.add(lblMixIdenIco);
		
		cbxMixIden = new JCheckBox(MixIdenAntipattern.getAntipatternInfo().getAcronym()+": "+MixIdenAntipattern.getAntipatternInfo().getName());	
		cbxMixIden.setPreferredSize(new Dimension(230, 20));
		cbxMixIden.setBackground(UIManager.getColor("Panel.background"));
		leftPanel.add(cbxMixIden);
		lblMixIdenRes = new JLabel("");
		lblMixIdenRes.setPreferredSize(new Dimension(110, 20));		
		lblMixIdenRes.setForeground(Color.BLUE);
		leftPanel.add(lblMixIdenRes);
		lblMixRigIco  = new JButton();
		leftPanel.add(lblMixRigIco);
		lblMixRigIco.setPreferredSize(new Dimension(20, 20));
		lblMixRigIco.setOpaque(false);
		lblMixRigIco.setContentAreaFilled(false);
		lblMixRigIco.setBorderPainted(false);
		lblMixRigIco.setIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help.png")));
		lblMixRigIco.setRolloverIcon(new ImageIcon(AntiPatternSearchDialog.class.getResource("/resources/icons/x16/help-rollover.png")));
		
		cbxMixRig = new JCheckBox(MixRigAntipattern.getAntipatternInfo().getAcronym()+": "+MixRigAntipattern.getAntipatternInfo().getName());		
		leftPanel.add(cbxMixRig);
		cbxMixRig.setPreferredSize(new Dimension(220, 20));
		cbxMixRig.setBackground(UIManager.getColor("Panel.background"));
		lblMixRigRes = new JLabel("");
		leftPanel.add(lblMixRigRes);
		lblMixRigRes.setPreferredSize(new Dimension(120, 20));		
		lblMixRigRes.setForeground(Color.BLUE);
	
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.CENTER);
		buttonPane.setPreferredSize(new Dimension(60, 65));
		
		progressBarDescr = new JLabel("Click in SEARCH to detect anti-patterns in the model.");		
		progressBarDescr.setForeground(Color.BLUE);		
		
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_buttonPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(progressBarDescr, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(progressBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_buttonPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBarDescr)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		buttonPane.setLayout(gl_buttonPane);
		
		ShowAllAntiPatternIconLabels(true);
	}
		
	public void updateStatus(final String text)
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				progressBarDescr.setText(text);
				progressBarDescr.repaint();
				progressBarDescr.revalidate();
			}
		});
	}
	
	public void activateShowResult()
	{
		btnShowResult.setEnabled(true);
	}
	
	/**
	 * Identifying AntiPatterns...
	 * 
	 * @param event
	 */
	public void IdentifyButtonActionPerformed(ActionEvent event)
	{
		try{
		
		totalOccurrences=0;
		interruptAll();
		if (searchThread!=null) searchThread.interrupt();
		
		searchThread= new Thread(new Runnable() {			
			@Override
			public void run() {
				
				 SwingUtilities.invokeLater(new Runnable() {					
					@Override
					public void run() {
						cleanAllResultlabels();
						HideBoldnessOnAllCheckBoxes();		
						frame.focusOnOutput();				
						progressBar.setStringPainted(true);
						progressBar.setMinimum(0);
						progressBar.setMaximum(100);								
						progressBar.setValue(0);
						progressBar.setString("0%");
						identifyButton.setEnabled(false);			
						setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));						
					}
				 });
				
				 OntoUMLParser parser = ProjectBrowser.getParserFor(frame.getDiagramManager().getCurrentProject());

				 SwingUtilities.invokeLater(new Runnable() {			
					@Override
					public void run() {
						frame.getDiagramManager().autoCompleteSelection(
							OntoUMLParser.NO_HIERARCHY,
							frame.getDiagramManager().getCurrentProject()
						);							
					}
				 });				 
				 
				 final AssCycAntipattern assCyc = new AssCycAntipattern(parser); 	
				 final BinOverAntipattern binOver = new BinOverAntipattern(parser);		
				 final DepPhaseAntipattern depPhase = new DepPhaseAntipattern(parser);
				 final FreeRoleAntipattern freeRole = new FreeRoleAntipattern(parser);
				 final GSRigAntipattern gsRig = new GSRigAntipattern(parser);
				 final HetCollAntipattern hetColl = new HetCollAntipattern(parser);
				 final HomoFuncAntipattern homoFunc = new HomoFuncAntipattern(parser);
				 final ImpAbsAntipattern impAbs = new ImpAbsAntipattern(parser);
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
				 final PartOverAntipattern partOver = new PartOverAntipattern(parser);
				 final DecIntAntipattern decInt = new DecIntAntipattern(parser);
			
				if (parser.getElements() == null) return;
				
				int totalItemsSelected = getTotalSelected();				
				int incValue=0;
				if(totalItemsSelected==0) incValue=100;
				else incValue=100/totalItemsSelected; 				
				final int incrementalValue = incValue;
				
				if (AssCycisSelected()) 
				{
					AssCycThread = new Thread(new Runnable() {						
						@Override
						public void run() {
							updateStatus("Identifying AssCyc...");
							assCyc.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {
									progressBar.setValue(progressBar.getValue()+incrementalValue);						
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("AssCyc: "+assCyc.getOccurrences().size()+" items found");
									
									if (assCyc.getOccurrences().size()>0) { 
										result += AssCycAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+assCyc.getOccurrences().size()+" items found.\n";
										totalOccurrences += assCyc.getOccurrences().size();
										lblAssCycRes.setText("("+assCyc.getOccurrences().size()+")");						
										cbxAssCyc.setFont(new Font(cbxAssCyc.getFont().getFontName(), Font.BOLD,cbxAssCyc.getFont().getSize()));
									}
								}
							});
						}
					});		
					AssCycThread.start();
				}			
				
				if (BinOverisSelected())
				{
					BinOverThread = new Thread(new Runnable() {						
						@Override
						public void run() {
							updateStatus("Identifying BinOver... ");
							binOver.identify();				
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("BinOver: "+binOver.getOccurrences().size()+" items found");						
				
									if (binOver.getOccurrences().size()>0) { 
										result += BinOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+binOver.getOccurrences().size()+" items found.\n"; 
										totalOccurrences += binOver.getOccurrences().size();
										lblBinOverRes.setText("("+binOver.getOccurrences().size()+")");
										cbxBinOver.setFont(new Font(cbxBinOver.getFont().getFontName(), Font.BOLD,cbxBinOver.getFont().getSize()));
									}
								}
							});		
						}
					});
					BinOverThread.start();
				}
				
				if (DepPhaseisSelected())
				{		
					DepPhaseThread = new Thread(new Runnable() {				
						@Override
						public void run() {
							updateStatus("Identifying DepPhase... ");
							depPhase.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("DepPhase: "+depPhase.getOccurrences().size()+" items found");			
									
									if (depPhase.getOccurrences().size()>0) {
										result += DepPhaseAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+depPhase.getOccurrences().size()+" items found.\n";
										totalOccurrences += depPhase.getOccurrences().size();
										lblDepPhaseRes.setText("("+depPhase.getOccurrences().size()+")");
										cbxDepPhase.setFont(new Font(cbxDepPhase.getFont().getFontName(), Font.BOLD,cbxDepPhase.getFont().getSize()));
									}								
								}
							});
						}
					});
					DepPhaseThread.start();
				}
				
				if (FreeRoleisSelected())
				{
					FreeRoleThread = new Thread(new Runnable() {				
						@Override
						public void run() {			
							updateStatus("Identifying FreeRole... ");
							freeRole.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("FreeRole: "+freeRole.getOccurrences().size()+" items found");									
									if (freeRole.getOccurrences().size()>0) {
										result += FreeRoleAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+freeRole.getOccurrences().size()+" items found.\n";
										totalOccurrences += freeRole.getOccurrences().size();
										lblFreeRoleRes.setText("("+freeRole.getOccurrences().size()+")");
										cbxFreeRole.setFont(new Font(cbxFreeRole.getFont().getFontName(), Font.BOLD,cbxFreeRole.getFont().getSize()));
									}									
								}
							});
						}
					});
					FreeRoleThread.start();
				}
				
				if (GSRigisSelected())
				{
					GSRigThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying GSRig... ");
							gsRig.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("GSRig: "+gsRig.getOccurrences().size()+" items found");									
									if (gsRig.getOccurrences().size()>0) {
										result += GSRigAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+gsRig.getOccurrences().size()+" items found.\n";		
										totalOccurrences += gsRig.getOccurrences().size();
										lblGSRigRes.setText("("+gsRig.getOccurrences().size()+")");	
										cbxGSRig.setFont(new Font(cbxGSRig.getFont().getFontName(), Font.BOLD,cbxGSRig.getFont().getSize()));
									}
								}
							});									
						}
					});
					GSRigThread.start();
				}
				
				if (HetCollisSelected())
				{	
					HetCollThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying HetColl... ");
							hetColl.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("HetColl: "+hetColl.getOccurrences().size()+" items found");									
									if (hetColl.getOccurrences().size()>0) {
										result += HetCollAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+hetColl.getOccurrences().size()+" items found.\n";
										totalOccurrences += hetColl.getOccurrences().size();
										lblHetCollRes.setText("("+hetColl.getOccurrences().size()+")");	
										cbxHetColl.setFont(new Font(cbxHetColl.getFont().getFontName(), Font.BOLD,cbxHetColl.getFont().getSize()));
									}   
								}
							});
						}
					});
					HetCollThread.start();					
				}
				
				if (HomoFuncisSelected())
				{
					HomoFuncThread = new Thread(new Runnable() {				
						@Override
						public void run() {						
							updateStatus("Identifying HomoFunc... ");
							homoFunc.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("HomoFunc: "+homoFunc.getOccurrences().size()+" items found");		
									if (homoFunc.getOccurrences().size()>0) {
										result += HomoFuncAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+homoFunc.getOccurrences().size()+" items found.\n";
										totalOccurrences += homoFunc.getOccurrences().size();
										lblHomoFuncRes.setText("("+homoFunc.getOccurrences().size()+")");
										cbxHomoFunc.setFont(new Font(cbxHomoFunc.getFont().getFontName(), Font.BOLD,cbxHomoFunc.getFont().getSize()));
									}									
								}
							});
						}
					});
					HomoFuncThread.start();	
				}
				
				if (ImpAbsisSelected())
				{					
					ImpAbsThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying ImpAbs... ");
							impAbs.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("ImpAbs: "+impAbs.getOccurrences().size()+" items found");							
									if (impAbs.getOccurrences().size()>0) {
										result += ImpAbsAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+impAbs.getOccurrences().size()+" items found.\n";
										totalOccurrences += impAbs.getOccurrences().size();
										lblImpAbsRes.setText("("+impAbs.getOccurrences().size()+")");
										cbxImpAbs.setFont(new Font(cbxImpAbs.getFont().getFontName(), Font.BOLD,cbxImpAbs.getFont().getSize()));
									}    
								}
							});
						}
					});
					ImpAbsThread.start();	
				}
				
				
				if (MixIdenisSelected())
				{				
					MixIdenThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying MixIden... ");
							mixIden.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("MixIden: "+mixIden.getOccurrences().size()+" items found");							
									if (mixIden.getOccurrences().size()>0) {
										result += MixIdenAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+mixIden.getOccurrences().size()+" items found.\n";
										totalOccurrences += mixIden.getOccurrences().size();
										lblMixIdenRes.setText("("+mixIden.getOccurrences().size()+")");
										cbxMixIden.setFont(new Font(cbxMixIden.getFont().getFontName(), Font.BOLD,cbxMixIden.getFont().getSize()));
									}									
								}
							});
						}
					});
					MixIdenThread.start();
				}
				
				if (MixRigisSelected())
				{			
					MixRigThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying MixRig... ");
							mixRig.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("MixRig: "+mixRig.getOccurrences().size()+" items found");							
									if (mixRig.getOccurrences().size()>0) {
										result += MixRigAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+mixRig.getOccurrences().size()+" items found.\n";
										totalOccurrences += mixRig.getOccurrences().size();
										lblMixRigRes.setText("("+mixRig.getOccurrences().size()+")");
										cbxMixRig.setFont(new Font(cbxMixRig.getFont().getFontName(), Font.BOLD,cbxMixRig.getFont().getSize()));
									}  
								}
							});
						}
					});
					MixRigThread.start();
				}
				
				if (MultiDepisSelected())
				{			
					MultiDepThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying MultiDep... ");
							multiDep.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("MultiDep: "+multiDep.getOccurrences().size()+" items found");							
									if (multiDep.getOccurrences().size()>0) {
										result += MultiDepAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+multiDep.getOccurrences().size()+" items found.\n";
										totalOccurrences += multiDep.getOccurrences().size();
										lblMultiDepRes.setText("("+multiDep.getOccurrences().size()+")");
										cbxMultiDep.setFont(new Font(cbxMultiDep.getFont().getFontName(), Font.BOLD,cbxMultiDep.getFont().getSize()));
									}									
								}
							});
						}
					});
					MultiDepThread.start();
				}
				
				if (RelCompisSelected())
				{				
					RelCompThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying RelComp... ");
							relComp.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("RelComp: "+relComp.getOccurrences().size()+" items found");							
									if (relComp.getOccurrences().size()>0) {
										result += RelCompAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relComp.getOccurrences().size()+" items found.\n";
										totalOccurrences += relComp.getOccurrences().size();
										lblRelCompRes.setText("("+relComp.getOccurrences().size()+")");	
										cbxRelComp.setFont(new Font(cbxRelComp.getFont().getFontName(), Font.BOLD,cbxRelComp.getFont().getSize()));
									}									
								}
							});
						}
					});
					RelCompThread.start();
				}
				
				if (RelOverisSelected())
				{				
					RelOverThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying RelOver... ");
							relOver.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("RelOver: "+relOver.getOccurrences().size()+" items found");							
									if (relOver.getOccurrences().size()>0) {
										result += RelOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relOver.getOccurrences().size()+" items found.\n";
										totalOccurrences += relOver.getOccurrences().size();
										lblRelOverRes.setText("("+relOver.getOccurrences().size()+")");
										cbxRelOver.setFont(new Font(cbxRelOver.getFont().getFontName(), Font.BOLD,cbxRelOver.getFont().getSize()));
									}								
								}
							});
						}
					});
					RelOverThread.start();
				}
				
				if (RelRigisSelected())
				{			
					RelRigThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying RelRig... ");
							relRig.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("RelRig... "+relRig.getOccurrences().size()+" items found");							
									if (relRig.getOccurrences().size()>0) {
										result += RelRigAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relRig.getOccurrences().size()+" items found.\n";
										totalOccurrences += relRig.getOccurrences().size();
										lblRelRigRes.setText("("+relRig.getOccurrences().size()+")");
										cbxRelRig.setFont(new Font(cbxRelRig.getFont().getFontName(), Font.BOLD,cbxRelRig.getFont().getSize()));
									}									
								}
							});
						}
					});
					RelRigThread.start();					
				}
				
				if (RelSpecisSelected())
				{	
					RelSpecThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying RelSpec... ");
							relSpec.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("RelSpec: "+relSpec.getOccurrences().size()+" items found");							
									if (relSpec.getOccurrences().size()>0) {
										result += RelSpecAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+relSpec.getOccurrences().size()+" items found.\n";
										totalOccurrences += relSpec.getOccurrences().size();
										lblRelSpecRes.setText("("+relSpec.getOccurrences().size()+")");
										cbxRelSpec.setFont(new Font(cbxRelSpec.getFont().getFontName(), Font.BOLD,cbxRelSpec.getFont().getSize()));
									}									
								}
							});
						}
					});
					RelSpecThread.start();		
				}
				
				if (RepRelisSelected())
				{	
					RepRelThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying RepRel... ");
							repRel.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("RepRel: "+repRel.getOccurrences().size()+" items found");							
									if (repRel.getOccurrences().size()>0) {
										result += RepRelAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+repRel.getOccurrences().size()+" items found.\n";
										totalOccurrences += repRel.getOccurrences().size();
										lblRepRelRes.setText("("+repRel.getOccurrences().size()+")");	
										cbxRepRel.setFont(new Font(cbxRepRel.getFont().getFontName(), Font.BOLD,cbxRepRel.getFont().getSize()));
									}
								}
							});
						}						
					});
					RepRelThread.start();	
				}
				
				if (UndefFormalisSelected())
				{	
					UndefFormalThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying UndefFormal... ");
							undefFormal.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("UndefFormal: "+undefFormal.getOccurrences().size()+" items found");							
									if (undefFormal.getOccurrences().size()>0) {
										result += UndefFormalAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+undefFormal.getOccurrences().size()+" items found.\n";
										totalOccurrences += undefFormal.getOccurrences().size();
										lblUndefFormalRes.setText("("+undefFormal.getOccurrences().size()+")");
										cbxUndefFormal.setFont(new Font(cbxUndefFormal.getFont().getFontName(), Font.BOLD,cbxUndefFormal.getFont().getSize()));
									}
								}
							});							
						}
					});
					UndefFormalThread.start();						
				}
				
				if (UndefPhaseisSelected())
				{	
					UndefPhaseThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying UndefPhase... ");
							undefPhase.identify();
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("UndefPhase: "+undefPhase.getOccurrences().size()+" items found");							
									if (undefPhase.getOccurrences().size()>0) {
										result += UndefPhaseAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+undefPhase.getOccurrences().size()+" items found.\n";
										totalOccurrences += undefPhase.getOccurrences().size();
										lblUndefPhaseRes.setText("("+undefPhase.getOccurrences().size()+")");
										cbxUndefPhase.setFont(new Font(cbxUndefPhase.getFont().getFontName(), Font.BOLD,cbxUndefPhase.getFont().getSize()));
									}   	
								}
							});
						}
					});
					UndefPhaseThread.start();		
				}
				
				if (WholeOverisSelected())
				{	
					WholeOverThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying WholeOver... ");
							wholeOver.identify();				
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("WholeOver: "+wholeOver.getOccurrences().size()+" items found");							
									if (wholeOver.getOccurrences().size()>0) {
										result += WholeOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+wholeOver.getOccurrences().size()+" items found.\n";
										totalOccurrences += wholeOver.getOccurrences().size();
										lblWholeOverRes.setText("("+wholeOver.getOccurrences().size()+")");
										cbxWholeOver.setFont(new Font(cbxWholeOver.getFont().getFontName(), Font.BOLD,cbxWholeOver.getFont().getSize()));
									}								
								}
							});
						}
					});
					WholeOverThread.start();									
				}
				
				if (PartOverisSelected())
				{	
					PartOverThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying PartOver... ");
							partOver.identify();				
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("PartOver: "+partOver.getOccurrences().size()+" items found");							
									if (partOver.getOccurrences().size()>0) {
										result += PartOverAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+partOver.getOccurrences().size()+" items found.\n";
										totalOccurrences += partOver.getOccurrences().size();
										lblPartOverRes.setText("("+partOver.getOccurrences().size()+")");
										cbxPartOver.setFont(new Font(cbxPartOver.getFont().getFontName(), Font.BOLD,cbxPartOver.getFont().getSize()));
									}								
								}
							});
						}
					});
					PartOverThread.start();									
				}
				
				if (DecIntisSelected())
				{	
					DecIntThread = new Thread(new Runnable() {				
						@Override
						public void run() {	
							updateStatus("Identifying DecInt... ");
							decInt.identify();				
							SwingUtilities.invokeLater(new Runnable() {
								@Override
								public void run() {									
									progressBar.setValue(progressBar.getValue()+incrementalValue);
									progressBar.setString(Integer.toString(progressBar.getValue()) + "%");
									updateStatus("DecInt: "+decInt.getOccurrences().size()+" items found");							
									if (decInt.getOccurrences().size()>0) {
										result += DecIntAntipattern.getAntipatternInfo().getAcronym()+" AntiPattern : "+decInt.getOccurrences().size()+" items found.\n";
										totalOccurrences += decInt.getOccurrences().size();
										lblDecIntRes.setText("("+decInt.getOccurrences().size()+")");
										cbxDecInt.setFont(new Font(cbxDecInt.getFont().getFontName(), Font.BOLD,cbxDecInt.getFont().getSize()));
									}								
								}
							});
						}
					});
					DecIntThread.start();									
				}
			
				joinAll();
				
				AntiPatternList antipatternList = new AntiPatternList (assCyc, binOver, depPhase, freeRole, gsRig, hetColl, homoFunc, impAbs, mixIden,
						   mixRig, multiDep, relComp, relOver, relRig, relSpec, repRel, undefFormal, undefPhase, wholeOver, partOver, decInt);

				ProjectBrowser.setAntiPatternListFor(frame.getDiagramManager().getCurrentProject(),antipatternList);
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));				
						identifyButton.setEnabled(true);
						progressBar.setValue(100);
						progressBar.setString(Integer.toString(progressBar.getValue()) + "%");												
						btnShowResult.setEnabled(true);
						updateStatus("Completed. "+totalOccurrences+" occurrences found.");
					}
				});				
			}
		});		
		searchThread.start();		
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Anti-Pattern Search",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public int getTotalSelected()
	{
		int totalItemsSelected = 0;			
		if (AssCycisSelected()) totalItemsSelected++;
		if (BinOverisSelected()) totalItemsSelected++;
		if (DecIntisSelected()) totalItemsSelected++;
		if (DepPhaseisSelected()) totalItemsSelected++;
		if (FreeRoleisSelected()) totalItemsSelected++;
		if (GSRigisSelected()) totalItemsSelected++;
		if (HetCollisSelected()) totalItemsSelected++;
		if (HomoFuncisSelected()) totalItemsSelected++;
		if (ImpAbsisSelected()) totalItemsSelected++;
		if (MixIdenisSelected()) totalItemsSelected++;
		if (MixRigisSelected()) totalItemsSelected++;
		if (MultiDepisSelected()) totalItemsSelected++;
		if (PartOverisSelected()) totalItemsSelected++;
		if (RelCompisSelected()) totalItemsSelected++;
		if (RelOverisSelected()) totalItemsSelected++;
		if (RelRigisSelected()) totalItemsSelected++;
		if (RelSpecisSelected()) totalItemsSelected++;
		if (RepRelisSelected()) totalItemsSelected++;			
		if (UndefFormalisSelected()) totalItemsSelected++;
		if (UndefPhaseisSelected()) totalItemsSelected++;
		if (WholeOverisSelected()) totalItemsSelected++;

		return totalItemsSelected;
	}
	public void joinAll()
	{
		try {
			if(AssCycThread!=null)AssCycThread.join();				
			if(BinOverThread!=null) BinOverThread.join();
			if(DecIntThread!=null) DecIntThread.join();
			if(DepPhaseThread!=null) DepPhaseThread.join();
			if(FreeRoleThread!=null) FreeRoleThread.join();
			if(GSRigThread!=null) GSRigThread.join();
			if(HetCollThread!=null) HetCollThread.join();
			if(HomoFuncThread!=null) HomoFuncThread.join();
			if(ImpAbsThread!=null) ImpAbsThread.join();
			if(MixIdenThread!=null) MixIdenThread.join();
			if(MixRigThread!=null) MixRigThread.join();
			if(MultiDepThread!=null) MultiDepThread.join();
			if(PartOverThread!=null) PartOverThread.join();
			if(RelCompThread!=null) RelCompThread.join();
			if(RelOverThread!=null) RelOverThread.join();
			if(RelRigThread!=null) RelRigThread.join();
			if(RelSpecThread!=null) RelSpecThread.join();
			if(RepRelThread!=null) RepRelThread.join();
			if(UndefFormalThread!=null) UndefFormalThread.join();
			if(UndefPhaseThread!=null) UndefPhaseThread.join();
			if(WholeOverThread!=null) WholeOverThread.join();
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}
	}
	
	public void interruptAll()
	{
		if(AssCycThread!=null)AssCycThread.interrupt();				
		if(BinOverThread!=null) BinOverThread.interrupt();
		if(DecIntThread!=null) DecIntThread.interrupt();
		if(DepPhaseThread!=null) DepPhaseThread.interrupt();
		if(FreeRoleThread!=null) FreeRoleThread.interrupt();
		if(GSRigThread!=null) GSRigThread.interrupt();
		if(HetCollThread!=null) HetCollThread.interrupt();
		if(HomoFuncThread!=null) HomoFuncThread.interrupt();
		if(ImpAbsThread!=null) ImpAbsThread.interrupt();
		if(MixIdenThread!=null) MixIdenThread.interrupt();
		if(MixRigThread!=null) MixRigThread.interrupt();
		if(MultiDepThread!=null) MultiDepThread.interrupt();
		if(PartOverThread!=null) PartOverThread.interrupt();
		if(RelCompThread!=null) RelCompThread.interrupt();
		if(RelOverThread!=null) RelOverThread.interrupt();
		if(RelRigThread!=null) RelRigThread.interrupt();
		if(RelSpecThread!=null) RelSpecThread.interrupt();
		if(RepRelThread!=null) RepRelThread.interrupt();
		if(UndefFormalThread!=null) UndefFormalThread.interrupt();
		if(UndefPhaseThread!=null) UndefPhaseThread.interrupt();
		if(WholeOverThread!=null) WholeOverThread.interrupt();
	}
	
	/**
	 * Show Result
	 */
	public void showResult()
	{
		if(Main.onMac()){
			com.apple.concurrent.Dispatch.getInstance().getNonBlockingMainQueueExecutor().execute( new Runnable(){        	
				@Override
				public void run() {
			    	AntiPatternList apList = ProjectBrowser.getAntiPatternListFor(ProjectBrowser.frame.getDiagramManager().getCurrentProject());
			    	AntiPatternResultDialog.openDialog(apList,frame);
				}
			});
		}else{
			AntiPatternList apList = ProjectBrowser.getAntiPatternListFor(ProjectBrowser.frame.getDiagramManager().getCurrentProject());
	    	AntiPatternResultDialog.openDialog(apList,frame);
		}
	}
}
