package br.ufes.inf.nemo.oled.dialog.properties;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Element;
import RefOntoUML.Generalization;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.explorer.ProjectBrowser;
import br.ufes.inf.nemo.oled.palette.ColorPalette;
import br.ufes.inf.nemo.oled.palette.ColorPalette.ThemeColor;

public class RelatedElementsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private DiagramManager diagramManager;
	@SuppressWarnings("unused")
	private DiagramElement diagramElement;
	private Element element;
	private OntoUMLParser refparser;

	private JScrollPane topScroll;
	private JScrollPane bottomScroll;
	private JTable topTable;
	private JTable bottomTable;
	private JLabel lblTop;		
	private JLabel lblBottom; 
	
	
	/** Table Model for associations*/
	class AssociationTableModel extends AbstractTableModel 
	{
		private static final long serialVersionUID = 1L;
		
		private String[] columnNames = { "Direct", "Stereotype","Name","Source","Target" };
	    private ArrayList<Association> assocList; 
	    		
	    public AssociationTableModel(ArrayList<Association> assocList)
	    {
	    	this.assocList = assocList;
	    }
	    
	    @Override
	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    @Override
	    public int getRowCount() {
	        return assocList.size();
	    }
	    
	    @Override
	    public String getColumnName(int col) {
	        return columnNames[col];
	    }

	    @Override
	    public Object getValueAt(int row, int col) 
	    {
	    	Association assoc = (Association)assocList.get(row);
	    	if (col==1){
	    		return OntoUMLNameHelper.getTypeName(assoc, true);
	    	}
	    	else if(col==2){
	    		return assoc.getName();
	    	}
	    	else if(col==0){
	    		if (assoc.getMemberEnd().get(0).getType().equals(element) || assoc.getMemberEnd().get(1).getType().equals(element))
	    			return "Yes";
	    		else
	    			return "No";
	    	}
	    	else if(col==3){
	    		return assoc.getMemberEnd().get(0).getType().getName();
	    	}
	    	else if(col==4){
	    		return assoc.getMemberEnd().get(1).getType().getName();
	    	}
	    	else return new String("<empty entry>");
	    }
	    
	    @Override
		public Class<?> getColumnClass(int column) 
	    {
	       return String.class;
	    }
	}
	   
	/** Table Model for associations*/
	class GeneralizationTableModel extends AbstractTableModel 
	{
		private static final long serialVersionUID = 1L;
		
		private String[] columnNames = { "Direct","Stereotype","Specific","General" };
	    private ArrayList<Generalization> genList; 
	    		
	    public GeneralizationTableModel(ArrayList<Generalization> genList)
	    {
	    	this.genList = genList;
	    }

	    @Override
	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    @Override
	    public int getRowCount() {
	        return genList.size();
	    }

	    @Override
	    public String getColumnName(int col) {
	        return columnNames[col];
	    }

	    @Override
	    public Object getValueAt(int row, int col) 
	    {
	    	Generalization gen = (Generalization)genList.get(row);
	    	if (col==1){
	    		return OntoUMLNameHelper.getTypeName(gen, true);
	    	}	    	
	    	else if(col==0){
	    		if (gen.getGeneral().equals(element) || gen.getSpecific().equals(element))
	    			return "Yes";
	    		else
	    			return "No";
	    	}
	    	else if(col==2){
	    		return gen.getSpecific().getName();
	    	}
	    	else if(col==3){
	    		return gen.getGeneral().getName();
	    	}
	    	else return new String("<empty entry>");
	    }

	    @Override
		public Class<?> getColumnClass(int c) 
	    {
	        return String.class;
	    }
	}
	
	/**
	 * Create the panel.
	 */
	public RelatedElementsPanel(final DiagramManager diagramManager, final DiagramElement diagramElement, Classifier element)  
	{			
		this.diagramManager = diagramManager;
		this.diagramElement = diagramElement;
		this.element = element;
		this.refparser = ProjectBrowser.getParserFor(diagramManager.getCurrentProject());
		
		lblTop = new JLabel("Related associations:");		
		lblBottom = new JLabel("Related generalizations:");
		
		//==============
		
		ArrayList<Association> assocList = new ArrayList<Association>(); 
		assocList.addAll(refparser.getDirectAssociations(element));
		assocList.addAll(refparser.getIndirectAssociations(element));
		AssociationTableModel assocModel = new AssociationTableModel(assocList);
		topTable = new JTable(assocModel);
		
		topTable.setBorder(new EmptyBorder(0, 0, 0, 0));		
		topTable.setGridColor(Color.LIGHT_GRAY);		
		topTable.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM));
		topTable.setSelectionForeground(Color.BLACK);
		topTable.setFocusable(false);	    
		topTable.setRowHeight(23);
				
		topScroll = new JScrollPane(topTable);
		topScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		topTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		TableColumnAdjuster tca = new TableColumnAdjuster(topTable);
		tca.adjustColumns();
		
		//==================
		
		ArrayList<Generalization> genList = new ArrayList<Generalization>();
		genList.addAll(refparser.getDirectGeneralizations(element));
		genList.addAll(refparser.getIndirectGeneralizations(element));
		GeneralizationTableModel genModel = new GeneralizationTableModel(genList);
		bottomTable = new JTable(genModel);
		
		bottomTable.setBorder(new EmptyBorder(0, 0, 0, 0));		
		bottomTable.setGridColor(Color.LIGHT_GRAY);		
		bottomTable.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM));
		bottomTable.setSelectionForeground(Color.BLACK);
		bottomTable.setFocusable(false);	    
		bottomTable.setRowHeight(23);
		
		bottomScroll = new JScrollPane(bottomTable);
		bottomScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		bottomTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		tca = new TableColumnAdjuster(bottomTable);
		tca.adjustColumns();
		
		//==================
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(topScroll, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
						.addComponent(lblTop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblBottom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(bottomScroll, 0, 0, Short.MAX_VALUE))
					.addGap(23))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblTop)
					.addGap(3)
					.addComponent(topScroll, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblBottom)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bottomScroll, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
}
