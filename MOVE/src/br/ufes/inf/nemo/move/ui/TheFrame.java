package br.ufes.inf.nemo.move.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.ufes.inf.nemo.move.panel.ConsolePanel;
import br.ufes.inf.nemo.move.panel.ModelsPanel;
import br.ufes.inf.nemo.move.panel.OptionsPanel;
import br.ufes.inf.nemo.move.panel.TreePanel;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JToolBar toolBar; 
	
	private JMenuBar menuBar;
	
	private JButton btnExecute;
	
	private JPanel contentPane;	
	
		
	public JTabbedPane tabbedPane;	
		
	public ConsolePanel consolepanel;
	
	public TreePanel treepanel;	
	
	public JSplitPane innerSplitPane;	
	
	public JSplitPane outterSplitPane;

	public ModelsPanel modelspanel;
	
	public OptionsPanel optionspanel;
	
	public static String dirPath;	
		
	/**
	 * Create the frame
	 */
	public TheFrame (RefOntoUML.Package model, String oclConstraints, String alsPath) throws IOException
	{
		this();
		modelspanel.setOntoUMLModel(model);
		modelspanel.setOCLModel(oclConstraints,2);
		modelspanel.setAlloyPath(alsPath);
		dirPath = alsPath.substring(0, alsPath.lastIndexOf(File.separator)+1);
	}	
		
	/**
	 * Create the frame.
	 */
	public TheFrame() 
	{		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(UIManager.getColor("ToolBar.dockingBackground"));
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
				
		createExecuteButton();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TheFrame.class.getResource("/resources/br/ufes/inf/nemo/move/window.png")));
		setTitle("OntoUML Model Validation Environment - MOVE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 710);
				
		createContentPane();
	}
	
	/**
	 * Create Execute Button
	 */	
	public void createExecuteButton ()
	{
		btnExecute = new JButton("");
		btnExecute.setFocusable(false);
		btnExecute.setIcon(new ImageIcon(TheFrame.class.getResource("/resources/br/ufes/inf/nemo/move/play.png")));
		toolBar.add(btnExecute);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);
		
		btnExecute.addActionListener(new ActionListener() 
		{
       		public void actionPerformed(ActionEvent event) 
       		{
       			 ExecuteButtonActionPerformed(event);					
       		}
       	});		
	}	

	/**	
	 * Executing Validation. 
	 */
	public void ExecuteButtonActionPerformed (ActionEvent arg0)
	{				
		try {
		
		Options opt = optionspanel.getOptions();
		String alsPath = modelspanel.getAlloyPath();
		RefOntoUML.Package refmodel = modelspanel.getOntoUMLModel();
		String oclConstraints = modelspanel.getOCLModel();				
		
		if(oclConstraints == null || oclConstraints.equals("")) OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
		
		/*
		// run verifier...
		OntoUMLVerifier verifier = new OntoUMLVerifier(refmodel);
		verifier.initialize();
		
		// no substance sortal warning...
		if (!verifier.haveSubstanceSortal && opt.identityPrinciple) 
		{
			opt.identityPrinciple=false;
			enforcepanel.cbxIdentityPrinciple.setSelected(false);
			JOptionPane.showMessageDialog(this, "No Substance Sortals in the model.\n\nThe Identity Principle Axiom should not be enforced."
					+"\nThis option was unchecked by default.\n ", "Warning",JOptionPane.WARNING_MESSAGE);
		}
		
		// dispose window...
		dispose();
		*/
		
		if (opt.openAnalyzer)
		{
			// Copy "alloy4.2.jar" to the destination directory 
			InputStream is = TheFrame.class.getClassLoader().getResourceAsStream("alloy4.2.jar");
			if(is == null) is = new FileInputStream("lib/alloy4.2.jar");
			File alloyJarFile = new File(dirPath + "alloy4.2.jar");
			alloyJarFile.deleteOnExit();
			OutputStream out = new FileOutputStream(alloyJarFile);

			// copy data flow -> MB x MB
			byte[] src = new byte[1024];
			int read = 0;
			while ((read = is.read(src)) != -1) {
				out.write(src, 0, read);
			}		
			is.close();
			out.flush();
			out.close();
			
			String[] argsAnalyzer = { "","" };
			argsAnalyzer[0] = alsPath;
			argsAnalyzer[1] = dirPath + "standart_theme.thm"	;	
			SimpleGUICustom.main(argsAnalyzer);
		}		
		
		} catch (Exception e) {			
			JOptionPane.showMessageDialog(this,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}		
	}
	
	/**
	 * Create ContentPane
	 */
	public void createContentPane ()
	{		
		getContentPane().setBackground(UIManager.getColor("inactiveCaption"));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		optionspanel = new OptionsPanel();
		
		modelspanel = new ModelsPanel();
						
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		treepanel = new TreePanel();
		
		innerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,treepanel,tabbedPane);
		innerSplitPane.setOneTouchExpandable(true);
		innerSplitPane.setDividerLocation(250);
		
		consolepanel = new ConsolePanel();
		
		outterSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,innerSplitPane,consolepanel);
		outterSplitPane.setOneTouchExpandable(true);
		outterSplitPane.setDividerLocation(350);
										
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 1067, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(177, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(outterSplitPane, Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(modelspanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(optionspanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(193))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(modelspanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(optionspanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(outterSplitPane, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		getContentPane().setLayout(groupLayout);		
	}	
}
