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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
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
	private JScrollPane treeScrollPane;
	private JButton importButton;
	private JTextPane infoPane;
	private JButton deleteButton;
	private JPanel buttonsPanel;
	
	Mediator transfManager;
	CheckboxTree chckTree;
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
				treeScrollPane = new JScrollPane();
				getContentPane().add(treeScrollPane, BorderLayout.WEST);
				treeScrollPane.setPreferredSize(new java.awt.Dimension(200, 239));
				treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				treeScrollPane.getViewport().add(chckTree);
			}
			{
				buttonsPanel = new JPanel();
				getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
				buttonsPanel.setPreferredSize(new java.awt.Dimension(398, 36));
				{
					deleteButton = new JButton();
					buttonsPanel.add(deleteButton);
					deleteButton.setText("Delete Selected Elements");
					deleteButton.addActionListener(this);
				}
				{
					importButton = new JButton();
					buttonsPanel.add(importButton);
					importButton.setText("Import Model");
					importButton.addActionListener(this);
				}
			}
			{
				infoPane = new JTextPane();
				getContentPane().add(infoPane, BorderLayout.EAST);
				infoPane.setPreferredSize(new java.awt.Dimension(183, 226));
				infoPane.setEditable(false);
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
        			TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_CHECK);
        	modelTree.addTreeSelectionListener(this);
        	
//        	CheckboxTree diagramTree = RefOntoUMLUtil.createSelectionTreeByDiagram(transfManager.mapper);
//        	diagramTree.getCheckingModel().setCheckingMode(
//        			TreeCheckingModel.CheckingMode.PROPAGATE_PRESERVING_CHECK);
//        	diagramTree.addTreeSelectionListener(this);
        	
        	this.chckTree = modelTree;
        	//this.chckTree = diagramTree;
            this.transfManager = transfManager;
            this.model = model;
            
        } 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == deleteButton) {
			RefOntoUMLUtil.filter(chckTree);
			chckTree.clearChecking();
			
		} else if (e.getSource() == importButton) {
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
