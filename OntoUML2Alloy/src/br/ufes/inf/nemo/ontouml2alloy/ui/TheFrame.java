package br.ufes.inf.nemo.ontouml2alloy.ui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.eclipse.emf.ecore.resource.Resource;

import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.ResourceUtil;

/**
 * This Frame was created using the Windows Builder in Eclipse. 
 *
 * @author John Guerson
 */

public class TheFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public JPanel contentPane;
	
	public JTabbedPane tabbedPane;
	
	public RulesPanel rpanel;
	
	public FilesPanel fpanel;	
		
	/**
	 * Create the frame for OLED.
	 */
	public TheFrame (RefOntoUML.Model model, String alsPath)
	{
		this();
		fpanel.configurePanelForOLED(model, alsPath);
	}
	
	/**
	 * Create the frame.
	 */
	
	public TheFrame() 
	{							
		setTitle("OntoUML2Alloy");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 391);
		setLocationRelativeTo(null); 
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		fpanel = new FilesPanel();		
		tabbedPane.addTab("Files", null, fpanel, null);
		
		rpanel = new RulesPanel();
		tabbedPane.addTab("Rules", null, rpanel, null);
		
		contentPane.add(tabbedPane);
		
		fpanel.btnBrowseOntoUML.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				BrowseOntoUMLActionPerformed(arg0);
			}
		});
		
		fpanel.btnExecute.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{								
				ExecuteButtonActionPerformed (arg0);				
			}
		});

		fpanel.btnBrowseAlloy.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				BrowseAlloyActionPerformed(arg0);
			}
		});		
				
		String iconPath = "/resources/br/ufes/inf/nemo/ontouml2alloy/window.png";
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Image.class.getResource(iconPath)));
	}
		
	/**
	 *	Action Performed for Browse OntoUML JButton in FilesPanel. 
	 */
	public void BrowseOntoUMLActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open");
		FileNameExtensionFilter ontoumlFilter = new FileNameExtensionFilter(
				"Eclipse Ecore-based Model (*.refontouml)", "refontouml");
		fileChooser.addChoosableFileFilter(ontoumlFilter);
		fileChooser.setFileFilter(ontoumlFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == ontoumlFilter) 
			{				
				fpanel.txtOntoUML.setText( fileChooser.getSelectedFile().getPath() );
			}
		}
	}
	
	/**
	 *	Action Performed for Browse Alloy JButton in FilesPanel. 
	 */
	public void BrowseAlloyActionPerformed (ActionEvent arg0)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open");
		FileNameExtensionFilter alloyFilter = new FileNameExtensionFilter(
				"Eclipse Ecore-based Model (*.als)", "als");
		fileChooser.addChoosableFileFilter(alloyFilter);
		fileChooser.setFileFilter(alloyFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
		{
			if (fileChooser.getFileFilter() == alloyFilter) 
			{				
				fpanel.txtAlloy.setText( fileChooser.getSelectedFile().getPath() );
			}
		}
	}
	
	/**
	 *	Action Performed for Execute JButton in FilesPanel. 	
	 */
	public void ExecuteButtonActionPerformed (ActionEvent arg0)
	{
		try {

		boolean weakSuppl = rpanel.cbxWeakSupplementation.isSelected();
		boolean relatorsRule = rpanel.cbxRelators.isSelected();
				
		if (fpanel.txtOntoUML.isEnabled()) 
		{
			Resource resource = ResourceUtil.loadOntoUML(fpanel.txtOntoUML.getText());
			fpanel.refmodel = (RefOntoUML.Model) resource.getContents().get(0);
			fpanel.alsPath = fpanel.txtAlloy.getText();
		}
				
		OntoUML2Alloy.Transformation(fpanel.refmodel, fpanel.alsPath, relatorsRule, weakSuppl);

		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(contentPane,e.getLocalizedMessage(),"Error",JOptionPane.ERROR_MESSAGE);					
			e.printStackTrace();
		}
	}
	
}
