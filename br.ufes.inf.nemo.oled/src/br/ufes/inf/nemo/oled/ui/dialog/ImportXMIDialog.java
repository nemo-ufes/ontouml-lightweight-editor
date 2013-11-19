package br.ufes.inf.nemo.oled.ui.dialog;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

import RefOntoUML.Model;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.LoadingPane;
import br.ufes.inf.nemo.xmi2ontouml.Creator;
import br.ufes.inf.nemo.xmi2refontouml.util.ChckBoxTreeNodeElem;
import br.ufes.inf.nemo.xmi2refontouml.util.RefOntoUMLUtil;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ImportXMIDialog extends JDialog implements ActionListener, TreeSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5072745232874390321L;
	private JScrollPane modelTreeScrollPane, diagrTreeScrollPane;
	private JButton importButton;
	private JTextArea infoPane;
	private JPanel labelPane, panel;
	private JTabbedPane treeTabbedPane;
	private JLabel lblDetails, lblTitle;
	private Component horizontalStrut;
	private LoadingPane glassPane;
	private JCheckBox verfInconsistency;
	
	Creator transfManager;
	File file;
	CheckboxTree modelChckTree, diagrChckTree;
	DiagramManager diagManager;
	Model model;
	
	public ImportXMIDialog(JFrame frame, File file, DiagramManager diagMngr, boolean modal) {
		super(frame, modal);
		
		this.diagManager = diagMngr;
		this.glassPane = new LoadingPane("Importing File", 14, 0.5f);
		frame.setGlassPane(glassPane);
		frame.validate();
		this.file = file;
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                glassPane.start();
	            Thread performer = new Thread(new Runnable() {
	                public void run() {
	                	try
	                	{
							initVariables();
							initGUI();
						} catch (Exception e)
						{
							e.printStackTrace();
							ErrorInfo info = new ErrorInfo("Error", "Parsing not done.",
				        			null, "category", e, Level.SEVERE, null);
				        	JXErrorPane.showDialog(diagManager, info);
						}
	                	finally
	                	{
	                		glassPane.stop();
	                	}
	                }
	            }, "Performer");
	            performer.start();
            }
        });
	}
	
	private void initGUI() {
		try {
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Import from XMI"); //TODO adicionar no arquivo de captions
			setPreferredSize(new Dimension(800, 600));
			setBounds(new Rectangle(0, 0, 800, 600));
			{
				labelPane = new JPanel();
				labelPane.setPreferredSize(new Dimension(800, 40));
				getContentPane().add(labelPane, BorderLayout.NORTH);
				{
					lblTitle = new JLabel("Select the classes to import");
					lblTitle.setHorizontalTextPosition(SwingConstants.LEADING);
					lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
					labelPane.add(lblTitle);
				}
			}
			{
				treeTabbedPane = new JTabbedPane();
				getContentPane().add(treeTabbedPane, BorderLayout.WEST);
				{
					modelTreeScrollPane = new JScrollPane();
					modelTreeScrollPane.setPreferredSize(new java.awt.Dimension(400, 360));
					modelTreeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					modelTreeScrollPane.setViewportView(modelChckTree);
				}
				{
					diagrTreeScrollPane = new JScrollPane();
					diagrTreeScrollPane.setPreferredSize(new java.awt.Dimension(400, 360));
					diagrTreeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					diagrTreeScrollPane.setViewportView(diagrChckTree);
				}
				treeTabbedPane.addTab("Model", modelTreeScrollPane);
				treeTabbedPane.addTab("Diagram", diagrTreeScrollPane);
			}
			{
				panel = new JPanel();
				panel.setPreferredSize(new Dimension(370, 220));
				getContentPane().add(panel, BorderLayout.EAST);
				{
					lblDetails = new JLabel("Details:");
					lblDetails.setHorizontalTextPosition(SwingConstants.LEADING);
					lblDetails.setHorizontalAlignment(SwingConstants.LEFT);
					panel.add(lblDetails);
				}
				{
					horizontalStrut = Box.createHorizontalStrut(20);
					horizontalStrut.setPreferredSize(new Dimension(320, 0));
					panel.add(horizontalStrut);
				}
				{
					infoPane = new JTextArea();
					panel.add(infoPane);
					infoPane.setPreferredSize(new Dimension(370, 200));
					infoPane.setEditable(false);
				}
				{
					verfInconsistency = new JCheckBox();
					panel.add(verfInconsistency);
					verfInconsistency.setText("Verify inconsistencies when importing");
				}
				{
					importButton = new JButton();
					panel.add(importButton);
					importButton.setText("Import Model");
					importButton.addActionListener(this);
				}
			}
			
			setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initVariables() throws Exception {
		
		Creator transfManager = new Creator();
		model = transfManager.parse(file.getAbsolutePath());
        
        if (Creator.warningLog != "") {
        	//TODO essa lib que usei tem muita coisa desnecess�ria, talvez fosse melhor copiar o source
        	ErrorInfo info = new ErrorInfo("Warning", "Parsing done with warnings",
        			null, "category", new Exception(Creator.warningLog), Level.WARNING, null);
        	JXErrorPane.showDialog(diagManager, info);
    	}
        
        if (model != null) {
        	CheckboxTree modelTree = RefOntoUMLUtil.createSelectionTreeFromModel(model);
        	modelTree.getCheckingModel().setCheckingMode(
        			TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_UNCHECK);
        	modelTree.addTreeSelectionListener(this);
        	
        	CheckboxTree diagramTree = RefOntoUMLUtil.createSelectionTreeByDiagram(transfManager.mapper, model);

        	diagramTree.getCheckingModel().setCheckingMode(
        			TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_UNCHECK);
        	diagramTree.addTreeSelectionListener(this);
        	
        	this.modelChckTree = modelTree;
        	this.diagrChckTree = diagramTree;
        	
            this.transfManager = transfManager;
            
        } 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == importButton) {
			switch (treeTabbedPane.getSelectedIndex()) {
			case 0:
				if (modelChckTree.getCheckingPaths().length == 0) {
					JOptionPane.showMessageDialog(this, "No Element is selected " +
							"in the active tree. Please select at least one Element");
					return;
				} else {
					this.model = RefOntoUMLUtil.Filter(modelChckTree);
				}
				break;
			case 1:
				if (diagrChckTree.getCheckingPaths().length == 0) {
					JOptionPane.showMessageDialog(this, "No Element is selected " +
							"in the active tree. Please select at least one Element");
					return;
				} else {
					this.model = RefOntoUMLUtil.Filter(diagrChckTree);
				}
				break;
			}
			modelChckTree.clearChecking();
			diagrChckTree.clearChecking();
			
			if (verfInconsistency.isSelected())
			{
				String inconsistencyLog = RefOntoUMLUtil.verifyInconsistency(this.model);
				if (inconsistencyLog != "") {
		        	ErrorInfo info = new ErrorInfo("Warning", "Inconsistencies found",
		        			null, "category", new Exception(inconsistencyLog), Level.WARNING, null);
		        	JXErrorPane.showDialog(diagManager, info);
		    	}
			}			
			this.dispose();
			
			diagManager.closeCurrentProject();
			diagManager.createCurrentProject(this.model);
//			String oclContent = new String("Sobral");
//			diagManager.getFrame().getInfoManager().getOcleditor().addText(oclContent);
		}
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
		ChckBoxTreeNodeElem chckNode = (ChckBoxTreeNodeElem) node.getUserObject();
		String info = chckNode.getInfo();
		infoPane.setText(info);
	}

}
