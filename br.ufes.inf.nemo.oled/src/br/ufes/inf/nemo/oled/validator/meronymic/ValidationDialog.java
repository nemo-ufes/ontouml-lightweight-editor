package br.ufes.inf.nemo.oled.validator.meronymic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.AppFrame;
import java.awt.Toolkit;

public class ValidationDialog extends JDialog {

	private static final long serialVersionUID = -5936280584643585555L;
	
	private JTabbedPane tabbedPane;

	private JPanel contentPane;
	private PreConditionPanel prePanel;
	private ForbiddenPanel forbiddenPanel;
	private DerivedPanel derivedPanel; 
	
	private JTextPane consoleTextPane;

	private JButton saveButton;

	private JButton closeButton;
	private JButton applyButton;

	private AppFrame appFrame;


	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public ValidationDialog(OntoUMLParser parser) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ValidationDialog.class.getResource("/resources/icons/x16/diamond.png")));
		setTitle("Validation of Part-Whole Relations");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 847, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JLabel lblOutputConsole = new JLabel("Output Console:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		closeButton = new JButton("Close");
		closeButton.addActionListener(closeAction);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(saveAction);
		saveButton.setEnabled(false);
		
		applyButton = new JButton("Apply");
		applyButton.addActionListener(applyAction);
		applyButton.setEnabled(false);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(483, Short.MAX_VALUE)
					.addComponent(applyButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(saveButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(closeButton))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblOutputConsole)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblOutputConsole)
					.addGap(3)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(closeButton)
						.addComponent(saveButton)
						.addComponent(applyButton)))
		);
		
		prePanel = new PreConditionPanel(this, parser,saveButton,applyButton);
		tabbedPane.addTab("Pre-Condition", null, prePanel, null);
		
		forbiddenPanel = new ForbiddenPanel(this, parser,saveButton,applyButton);
		tabbedPane.addTab("Forbidden", null, forbiddenPanel, null);
		tabbedPane.setEnabledAt(1, false);
		
		derivedPanel = new DerivedPanel(this, parser,saveButton,applyButton);
		tabbedPane.addTab("Derived", null, derivedPanel, null);
		tabbedPane.setEnabledAt(2, false);
		
		consoleTextPane = new JTextPane();
		consoleTextPane.setBackground(new Color(255, 248, 220));
		scrollPane.setViewportView(consoleTextPane);
		contentPane.setLayout(gl_contentPane);
	}

	public ValidationDialog(OntoUMLParser parser, AppFrame parent) {
		this(parser);
		this.appFrame = parent;
	}

	private ActionListener applyAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Fix fix = fixAllPanels();
			appFrame.getDiagramManager().updateOLED(fix);
			clearAllTables();
			saveButton.setEnabled(false);
			applyButton.setEnabled(false);
		}
	};
	
	private ActionListener saveAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Fix fix = fixAllPanels();
			appFrame.getDiagramManager().updateOLED(fix);
			dispose();
		}
	};
	
	private ActionListener closeAction = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	};
	
	 /** Open the Dialog.
	 */
	public static void open(OntoUMLParser parser, AppFrame parent)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			ValidationDialog frame = new ValidationDialog( parser, parent);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			frame.setModalityType(ModalityType.APPLICATION_MODAL);
			frame.setLocationRelativeTo(parent);
			
			MessageConsole mc = new MessageConsole(frame.consoleTextPane);
			mc.redirectOut();
			mc.setMessageLines(100);
			
			frame.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /** Open the Dialog.
	 */
	public static void open(OntoUMLParser parser)
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			ValidationDialog frame = new ValidationDialog( parser/*parent*/);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			
			MessageConsole mc = new MessageConsole(frame.consoleTextPane);
			mc.redirectOut();
			mc.setMessageLines(100);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Fix fixAllPanels(){
		Fix fix = prePanel.runFixes();
		
		if(tabbedPane.isEnabledAt(1))
			fix.addAll(forbiddenPanel.runFixes());
		
		if(tabbedPane.isEnabledAt(2))
			fix.addAll(derivedPanel.runFixes());
		
		return fix;
	}
	
	public void clearAllTables(){
		prePanel.clearTable();
		forbiddenPanel.clearTable();
		derivedPanel.clearTable();
	}
}
