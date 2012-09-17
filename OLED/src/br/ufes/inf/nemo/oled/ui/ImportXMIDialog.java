package br.ufes.inf.nemo.oled.ui;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;
import it.cnr.imaa.essi.lablib.gui.checkboxtree.TreeCheckingModel;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

import RefOntoUML.Model;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation.Mediator;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.ChckBoxTreeNodeElem;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.RefOntoUMLUtil;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;


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
	
	Mediator transfManager;
	CheckboxTree modelChckTree, diagrChckTree;
	DiagramManager diagManager;
	Model model;

//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		ImportXMIFrame inst = new ImportXMIFrame();
//		JDesktopPane jdp = new JDesktopPane();
//		jdp.add(inst);
//		jdp.setPreferredSize(inst.getPreferredSize());
//		frame.setContentPane(jdp);
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
//	}
	
	public ImportXMIDialog(JFrame frame, File file, DiagramManager diagManager, boolean modal) throws Exception {
		super(frame, modal);
		
		initVariables(file);
		this.diagManager = diagManager;
        
        initGUI();
	}
	
	private void initGUI() {
		try {
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Import from XMI"); //TODO adicionar no arquivo de captions
			setPreferredSize(new Dimension(400, 300));
			setBounds(new Rectangle(0, 0, 400, 300));
			{
				labelPane = new JPanel();
				labelPane.setPreferredSize(new Dimension(398, 36));
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
					modelTreeScrollPane.setPreferredSize(new java.awt.Dimension(200, 239));
					modelTreeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					modelTreeScrollPane.setViewportView(modelChckTree);
				}
				{
					diagrTreeScrollPane = new JScrollPane();
					diagrTreeScrollPane.setPreferredSize(new java.awt.Dimension(200, 239));
					diagrTreeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					diagrTreeScrollPane.setViewportView(diagrChckTree);
				}
				treeTabbedPane.addTab("Model", modelTreeScrollPane);
				if (diagrChckTree != null) //TODO
				treeTabbedPane.addTab("Diagram", diagrTreeScrollPane);
			}
			{
				panel = new JPanel();
				panel.setPreferredSize(new Dimension(179, 220));
				getContentPane().add(panel, BorderLayout.EAST);
				{
					lblDetails = new JLabel("Details:");
					lblDetails.setHorizontalTextPosition(SwingConstants.LEADING);
					lblDetails.setHorizontalAlignment(SwingConstants.LEFT);
					panel.add(lblDetails);
				}
				{
					horizontalStrut = Box.createHorizontalStrut(20);
					horizontalStrut.setPreferredSize(new Dimension(120, 0));
					panel.add(horizontalStrut);
				}
				{
					infoPane = new JTextArea();
					panel.add(infoPane);
					infoPane.setPreferredSize(new Dimension(170, 150));
					infoPane.setEditable(false);
				}
				{
					importButton = new JButton();
					panel.add(importButton);
					importButton.setText("Import Model");
					importButton.addActionListener(this);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initVariables(File file) throws Exception {
		
		Mediator transfManager = new Mediator();
		transfManager.READ_FILE_ADDRESS = file.getAbsolutePath();
		transfManager.SAVE_FILE_ADDRESS = file.getAbsolutePath().split("\\.")[0] + ".refontouml";
        RefOntoUML.Model model = transfManager.parse();
        
        if (Mediator.warningLog != "") {
        	//TODO essa lib que usei tem muita coisa desnecessária, talvez fosse melhor copiar o source
        	ErrorInfo info = new ErrorInfo("Warning", "Parsing done with warnings",
        			null, "category", new Exception(Mediator.warningLog), Level.WARNING, null);
        	JXErrorPane.showDialog(diagManager, info);
    	}
        
        if (model != null) {
        	CheckboxTree modelTree = RefOntoUMLUtil.createSelectionTreeFromModel(model);
        	modelTree.getCheckingModel().setCheckingMode(
        			TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_UNCHECK);
        	modelTree.addTreeSelectionListener(this);
        	
        	CheckboxTree diagramTree = RefOntoUMLUtil.createSelectionTreeByDiagram(transfManager.mapper, model);

        	if (diagramTree != null) { //TODO
        	diagramTree.getCheckingModel().setCheckingMode(
        			TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_UNCHECK);
        	diagramTree.addTreeSelectionListener(this);
        	this.diagrChckTree = diagramTree;
        	}
        	
        	this.modelChckTree = modelTree;
        	
            this.transfManager = transfManager;
            this.model = model;
            
        } 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == importButton) {
			switch (treeTabbedPane.getSelectedIndex()) {
			case 0:
				if (modelChckTree.getCheckingPaths().length == 0) {
					JOptionPane.showMessageDialog(this, "No Element is selected in the active tree. Please select at least one Element");
					return;
				} else {
					RefOntoUMLUtil.Filter(modelChckTree);
				}
				break;
			case 1:
				if (diagrChckTree.getCheckingPaths().length == 0) {
					JOptionPane.showMessageDialog(this, "No Element is selected in the active tree. Please select at least one Element");
					return;
				} else {
					RefOntoUMLUtil.Filter(diagrChckTree);
				}
				break;
			}
			modelChckTree.clearChecking();
			if (diagrChckTree != null) //TODO
			diagrChckTree.clearChecking();
		
			UmlProject project = new UmlProject(this.model);
			StructureDiagram diagram = new StructureDiagram(project);
			project.addDiagram(diagram);
			diagram.setLabelText("Imported Diagram");		
			project.setSaveModelNeeded(true);
			diagram.setSaveNeeded(true);
			diagManager.createEditor(diagram);
			this.dispose();
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
