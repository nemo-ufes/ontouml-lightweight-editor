package br.ufes.inf.nemo.oled.ui.diagram;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import edu.mit.csail.sdg.alloy4graph.DotColor;
import edu.mit.csail.sdg.alloy4graph.DotPalette;
import edu.mit.csail.sdg.alloy4graph.DotShape;
import edu.mit.csail.sdg.alloy4graph.DotStyle;

import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.umldraw.structure.StructureDiagram;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.SimulationAttribute;
import br.ufes.inf.nemo.oled.util.SimulationElement;


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
public class VerificationSettingsDialog extends JDialog {

	private static final long serialVersionUID = -6707497708908105957L;
	private DiagramManager diagramManager;
	private JButton cancelButton;
	private JButton okButton;
	private JPanel buttonPanel;	
	private JPanel elementsPanel;
	private JCheckBox genThemeCheck;
	private JTable elementTable;
	private JScrollPane elementScroll;
	private JLabel modelElementsLabel;
	private JPanel selectionPanel;
	private VerificationModel simulationModel;
	
	public VerificationSettingsDialog(JFrame frame, DiagramManager amanager, boolean modal) {
		super(frame, modal);
		diagramManager = amanager;
		
		List<SimulationElement> simulationElements =  ((StructureDiagram) diagramManager.getCurrentWrapper().getDiagram()).getSimulationElements();
		simulationModel = new VerificationModel(simulationElements);
			
		initGUI();
		myPostInit();
	}
	
	private void  myPostInit()
	{
		loadModelElements();
		getRootPane().setDefaultButton(okButton);
	}
	
	private Object[] getArrayWithNullElement(Object[] objs)
	{
		Object[] ret = new Object[objs.length + 1];
		ret[0] = null;
		for (int i = 1; i < ret.length; i++) {
			ret[i] = objs[i-1];
		}
		
		return ret;
	}
	
	private void loadModelElements()
	{				
		Object[] colors = getArrayWithNullElement(DotColor.valuesWithout(DotColor.MAGIC));
		Object[] styles = getArrayWithNullElement(DotStyle.values());
		Object[] shapes = getArrayWithNullElement(DotShape.values());
		
		TableColumn colorColumn = elementTable.getColumnModel().getColumn(2);	
		colorColumn.setCellEditor(createEditor(colors));
		
		TableColumn styleColumn = elementTable.getColumnModel().getColumn(3);	
		styleColumn.setCellEditor(createEditor(styles));
		
		TableColumn shapeColumn = elementTable.getColumnModel().getColumn(4);	
		shapeColumn.setCellEditor(createEditor(shapes));
		
		elementTable.setDefaultRenderer(SimulationAttribute.class, new IconRenderer(true));
		elementTable.setDefaultRenderer(String.class, new StringRenderer(true));
		
		genThemeCheck.setSelected(((StructureDiagram) diagramManager.getCurrentWrapper().getDiagram()).isGenerateTheme());
	}
		
 	private void initGUI() {
		try {
			{
				setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
				setTitle(ApplicationResources.getInstance().getString("dialog.verifysettings.title"));
				
				BorderLayout layout = new BorderLayout();
				getContentPane().setLayout(layout);
				{
					buttonPanel = new JPanel();
					GroupLayout buttonPanelLayout = new GroupLayout((JComponent)buttonPanel);
					getContentPane().add(buttonPanel, BorderLayout.SOUTH);
					buttonPanel.setLayout(buttonPanelLayout);
					buttonPanel.setPreferredSize(new java.awt.Dimension(444, 46));
					{
						okButton = new JButton();
						okButton.setText(ApplicationResources.getInstance().getString("stdcaption.ok"));
						okButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								okButtonActionPerformed(evt);
							}
						});
					}
					{
						cancelButton = new JButton();
						cancelButton.setText(ApplicationResources.getInstance().getString("stdcaption.cancel"));
						cancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								cancelButtonActionPerformed(evt);
							}
						});
					}
					buttonPanelLayout.setHorizontalGroup(buttonPanelLayout.createSequentialGroup()
						.addContainerGap(262, Short.MAX_VALUE)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addContainerGap());
					buttonPanelLayout.setVerticalGroup(buttonPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(buttonPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						    .addComponent(okButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
						    .addComponent(cancelButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap());
				}
				{
					selectionPanel = new JPanel();
					GroupLayout elementSelectionPanelLayout = new GroupLayout((JComponent)selectionPanel);
					selectionPanel.setLayout(elementSelectionPanelLayout);
					getContentPane().add(selectionPanel, BorderLayout.CENTER);
					selectionPanel.setPreferredSize(new java.awt.Dimension(544, 352));
					{
						modelElementsLabel = new JLabel();
						modelElementsLabel.setText(ApplicationResources.getInstance().getString("dialog.verifysettings.elementssimulate"));
					}
					{
						elementsPanel = new JPanel();
						BorderLayout elementsPanelLayout = new BorderLayout();
						elementsPanel.setLayout(elementsPanelLayout);
						{
							elementScroll = new JScrollPane();
							elementsPanel.add(elementScroll, BorderLayout.CENTER);
							elementScroll.setPreferredSize(new java.awt.Dimension(520, 353));
							{
								
								
								elementTable = new JTable()
								{
									
									private static final long serialVersionUID = -1961384025968090226L;

									@Override
									public void changeSelection(int arg0, int arg1, boolean arg2, boolean arg3) { }
									
								};
								
								elementTable.setRowHeight(23);
								elementScroll.setViewportView(elementTable);
								elementTable.setModel(simulationModel);
								
								elementTable.getColumnModel().getColumn(0).setMaxWidth(55);
								elementTable.getColumnModel().getColumn(2).setPreferredWidth(75);
								elementTable.getColumnModel().getColumn(2).setMaxWidth(75);
								elementTable.getColumnModel().getColumn(3).setPreferredWidth(75);
								elementTable.getColumnModel().getColumn(3).setMaxWidth(75);
								elementTable.getColumnModel().getColumn(4).setPreferredWidth(110);
								elementTable.getColumnModel().getColumn(4).setMaxWidth(110);
							}
						}
					}
					{
						genThemeCheck = new JCheckBox();
						genThemeCheck.setText("Project over state");
					}
					elementSelectionPanelLayout.setHorizontalGroup(elementSelectionPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(elementSelectionPanelLayout.createParallelGroup()
						    .addComponent(modelElementsLabel, GroupLayout.Alignment.LEADING, 0, 520, Short.MAX_VALUE)
						    .addComponent(elementsPanel, GroupLayout.Alignment.LEADING, 0, 520, Short.MAX_VALUE)
						    .addGroup(GroupLayout.Alignment.LEADING, elementSelectionPanelLayout.createSequentialGroup()
						        .addComponent(genThemeCheck, 0, 387, Short.MAX_VALUE)
						        .addGap(0, 133, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap());
					elementSelectionPanelLayout.setVerticalGroup(elementSelectionPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(modelElementsLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(elementsPanel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(genThemeCheck, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(17, Short.MAX_VALUE));
				}
			}
			this.setSize(560, 550);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
 	private void okButtonActionPerformed(ActionEvent evt) {
 		((StructureDiagram) diagramManager.getCurrentWrapper().getDiagram()).setSimulationElements(simulationModel.getEntries());
 		((StructureDiagram) diagramManager.getCurrentWrapper().getDiagram()).setGenerateTheme(genThemeCheck.isSelected());
 		dispose();
	}

	private void cancelButtonActionPerformed(ActionEvent evt) {
 		dispose();
	}
	 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private TableCellEditor createEditor(Object[] objects) {
        JComboBox combo = new JComboBox(objects);
        combo.setEditable(false);
        combo.setRenderer(new IconRenderer(true));
        return new DefaultCellEditor(combo);
    }
	
	@SuppressWarnings("rawtypes")
	class IconRenderer extends JLabel implements TableCellRenderer, ListCellRenderer {

		private static final long serialVersionUID = 1L;
		Border unselectedBorder = null;
		Border selectedBorder = null;
		boolean isBordered = true;

		public IconRenderer(boolean isBordered) {
			this.isBordered = isBordered;
			//setOpaque(false);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			
			if (isBordered) {
				if (isSelected) {
					if (selectedBorder == null) {
						selectedBorder = BorderFactory.createMatteBorder(2, 5,
								2, 5, table.getSelectionBackground());
					}
					setBorder(selectedBorder);
				} else {
					if (unselectedBorder == null) {
						unselectedBorder = BorderFactory.createMatteBorder(2,
								5, 2, 5, table.getBackground());
					}
					setBorder(unselectedBorder);
				}
			}
			
			Icon icon = null;
			String text = null;
			
			if(value == null)
			{
				icon = DotColor.MAGIC.getIcon(DotPalette.CLASSIC);
				text = "Automatic";
			}
			else if(value instanceof DotColor)
			{
				icon = ((DotColor) value).getIcon(DotPalette.CLASSIC);
				text = ((DotColor) value).getDisplayedText();
			}
			else if(value instanceof DotShape)
			{
				icon = ((DotShape) value).getIcon();
				text = ((DotShape) value).getDisplayedText();
			}
			else if(value instanceof DotStyle)
			{
				icon = ((DotStyle) value).getIcon();
				text = ((DotStyle) value).getDisplayedText();
			}
			
			setIcon(icon);
			setText(text);
			
			return this;
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

			if (isBordered) {
				if (isSelected) {
					if (selectedBorder == null) {
						selectedBorder = BorderFactory.createMatteBorder(2, 5,
								2, 5, list.getSelectionBackground());
					}
					setBorder(selectedBorder);
				} else {
					if (unselectedBorder == null) {
						unselectedBorder = BorderFactory.createMatteBorder(2,
								5, 2, 5, list.getBackground());
					}
					setBorder(unselectedBorder);
				}
			}
			
			Icon icon = null;
			String text = null;
			
			if(value == null)
			{
				icon = DotColor.MAGIC.getIcon(DotPalette.CLASSIC);
				text = "Automatic";
			}
			else if(value instanceof DotColor)
			{
				icon = ((DotColor) value).getIcon(DotPalette.CLASSIC);
				text = ((DotColor) value).getDisplayedText();
			}
			else if(value instanceof DotShape)
			{
				icon = ((DotShape) value).getIcon();
				text = ((DotShape) value).getDisplayedText();
			}
			else if(value instanceof DotStyle)
			{
				icon = ((DotStyle) value).getIcon();
				text = ((DotStyle) value).getDisplayedText();
			}
			
			setIcon(icon);
			setText(text);
			
			return this;
		}
	}

	class StringRenderer extends JLabel implements TableCellRenderer
	{
		private static final long serialVersionUID = 1L;
		Border unselectedBorder = null;
		Border selectedBorder = null;
		boolean isBordered = true;

		public StringRenderer(boolean isBordered) {
			this.isBordered = isBordered;
		}

		public Component getTableCellRendererComponent(JTable table, Object text, boolean isSelected, boolean hasFocus, int row, int column) {
			
			if (isBordered) {
				if (isSelected) {
					if (selectedBorder == null) {
						selectedBorder = BorderFactory.createMatteBorder(2, 5,
								2, 5, table.getSelectionBackground());
					}
					setBorder(selectedBorder);
				} else {
					if (unselectedBorder == null) {
						unselectedBorder = BorderFactory.createMatteBorder(2,
								5, 2, 5, table.getBackground());
					}
					setBorder(unselectedBorder);
				}
			}
			
			setText((String) text);
			
			return this;
		}
	}
}
