package ontouml2text.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import java.awt.GridLayout;

import javax.swing.JLabel;

import ontouml2text.core.Writer;

public class OntoUML2TextDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1567748492292733301L;
	
	private JPanel buttonPane;
	private AssociationsPanel assocPane;
	private JButton genTextButton, selecSTButton, selecTSButton, seleAllButton;
	private JLabel label, label_1;
	
	RefOntoUML.Model model;
	OntoUMLParser ontoParser;

	public OntoUML2TextDialog (JFrame frame, RefOntoUML.Model refmodel) {
		super(frame, true);
		
		initVariables(refmodel);
		
		initGUI();
	}
	
	private void initVariables(RefOntoUML.Model refmodel)
	{
		model = refmodel;
		ontoParser = new OntoUMLParser(model);
	}
	
	private void initGUI() {
		try {
			
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			setTitle("Model Text Description");
			setPreferredSize(new Dimension(600, 400));
			setBounds(new Rectangle(0, 0, 600, 400));
			{
				assocPane = new AssociationsPanel(ontoParser);
				getContentPane().add(assocPane, BorderLayout.CENTER);
			}
			{
				buttonPane = new JPanel();
				buttonPane.setPreferredSize(new Dimension(600, 40));
				buttonPane.setLayout(new GridLayout(0, 3, 0, 0));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					selecSTButton = new JButton("Source->Target only");
					selecSTButton.addActionListener(this);
					selecTSButton = new JButton("Target->Source only");
					selecTSButton.addActionListener(this);
					seleAllButton = new JButton("Select All");
					seleAllButton.addActionListener(this);
					genTextButton = new JButton("Generate Text");
					genTextButton.addActionListener(this);
				}
				buttonPane.add(selecSTButton);
				buttonPane.add(selecTSButton);
				buttonPane.add(seleAllButton);
				
				label = new JLabel("");
				buttonPane.add(label);
				buttonPane.add(genTextButton);
				label_1 = new JLabel("");
				buttonPane.add(label_1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == genTextButton)
		{
			Writer writer = new Writer(ontoParser);
			String modelDescr = new String();
			modelDescr += writer.runDescriptionGeneration();
			
//			Map<Association,Integer> assocMap = new HashMap<Association,Integer>();
//			for (SingleAssociationPanel assocPanel : assocPane.getAssocPanelList())
//			{
//				if (assocPanel.isSelectedBoth())
//				{
//					assocMap.put(assocPanel.getAssociation(), 2);
//				}
//				else if (assocPanel.isSelectedST())
//				{
//					assocMap.put(assocPanel.getAssociation(), 0);
//				}
//				else if (assocPanel.isSelectedTS())
//				{
//					assocMap.put(assocPanel.getAssociation(), 1);
//				}
//			}
//			
//			modelDescr += writer.processAssociations(assocMap);
//			
//			modelDescr += writer.processAttributes();
			
			System.out.println(modelDescr);
		}
		else if (a.getSource() == selecSTButton)
		{
			for (SingleAssociationPanel assocPanel : assocPane.getAssocPanelList())
			{
				assocPanel.clearTS();
				assocPanel.selectST();
			}
		}
		else if (a.getSource() == selecTSButton)
		{
			for (SingleAssociationPanel assocPanel : assocPane.getAssocPanelList())
			{
				assocPanel.clearST();
				assocPanel.selectTS();
			}
		}
		else if (a.getSource() == seleAllButton)
		{
			for (SingleAssociationPanel assocPanel : assocPane.getAssocPanelList())
			{
				assocPanel.selectST();
				assocPanel.selectTS();
			}
		}
	}

}
