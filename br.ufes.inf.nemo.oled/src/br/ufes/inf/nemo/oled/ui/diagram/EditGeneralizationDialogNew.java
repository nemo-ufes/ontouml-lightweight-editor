package br.ufes.inf.nemo.oled.ui.diagram;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;

import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.DiagramManager;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.oled.umldraw.structure.GeneralizationElement;
import br.ufes.inf.nemo.oled.util.ApplicationResources;
import br.ufes.inf.nemo.oled.util.ModelHelper;


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
public class EditGeneralizationDialogNew extends javax.swing.JDialog {

	private static final long serialVersionUID = -6707497708908105957L;
	private GeneralizationElement generalizationElement;
	private DiagramManager diagramManager;
	private JLabel addToLabel;
	private JPanel memberPanel;
	private JTextField nameText;
	private JLabel nameLabel;
	@SuppressWarnings("rawtypes")
	private JComboBox chooseGenSetCombo;
	private JLabel chooseLabel;
	private JPanel genSetPropertiesPanel;
	private JButton removeButton;
	private JButton addButton;
	@SuppressWarnings("rawtypes")
	private JComboBox addGenSetCombo;
	private JPanel parentPanel;
	private JCheckBox coveringCheck;
	private JButton saveButton;
	private JCheckBox disjointCheck;
	@SuppressWarnings("rawtypes")
	private JList memberList;
	private JScrollPane memberScroll;
	@SuppressWarnings("rawtypes")
	private JList parentList;
	private JScrollPane parentScroll;
	private JPanel parentTablePanel;
	private JPanel memberTablePanel;
	private JButton deleteButton;
	private JPanel generalizationSetPanel;
	private JPanel generalizationPanel;
	private JTabbedPane contentTabbedPane;
	private JButton cancelButton;
	private JButton okButton;
	private JPanel buttonPanel;
	private Map<String, GeneralizationSet> modelGeneralizationSets;
	private boolean needsSaveGenSet;	
	private String chooseOpt = ApplicationResources.getInstance().getString("dialog.gen.chooseopt");
	private String newOpt = ApplicationResources.getInstance().getString("dialog.gen.newopt");
	
	public EditGeneralizationDialogNew(JFrame frame, GeneralizationElement ageneralization, DiagramManager amanager, boolean modal) {
		super(frame, modal);
		generalizationElement = ageneralization;
		diagramManager = amanager;
		
		initGUI();
		myPostInit();
	}
	
	private void  myPostInit()
	{
		loadModelGeneralizationSets();
		populateAddGenSetCombo();
		populateParentList();
		populateChooseGenSetCombo();
				
		getRootPane().setDefaultButton(okButton);
	}
	
	private void loadModelGeneralizationSets() {
		modelGeneralizationSets = new HashMap<String, GeneralizationSet>();
		for (GeneralizationSet item : diagramManager.getCurrentProject().getGeneralizationSets()) {
			modelGeneralizationSets.put(item.getName(), item);
		}
	}

	@SuppressWarnings("unchecked")
	private void populateAddGenSetCombo()
 	{
 		Object[] items = new Object[modelGeneralizationSets.size() + 1];
 		items[0] = chooseOpt;

 		if(modelGeneralizationSets.size() > 0)
 		{
 			int i = 0;
 	 		for (String item : modelGeneralizationSets.keySet()) {
 				i++;
 				items[i] = item;
 			}	
 		}
 		
		@SuppressWarnings("rawtypes")
		ComboBoxModel comboModel = new DefaultComboBoxModel(items);
		addGenSetCombo.setModel(comboModel);
 	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void populateParentList()
	{
		List<String> genSetsNames = new ArrayList<String>();
		for (GeneralizationSet item : generalizationElement.getGeneralization().getGeneralizationSet()) {
			genSetsNames.add(item.getName());
		}

		parentList.setModel(new DefaultComboBoxModel(genSetsNames.toArray()));
	}
	
	@SuppressWarnings("unchecked")
	private void populateChooseGenSetCombo()
 	{
 		Object[] items = new Object[modelGeneralizationSets.size() + 2];
 		items[0] = chooseOpt;
 		items[1] = newOpt;

 		if(modelGeneralizationSets.size() > 0)
 		{
 			int i = 1;
 	 		for (String item : modelGeneralizationSets.keySet()) {
 				i++;
 				items[i] = item;
 			}	
 		}
 		
		@SuppressWarnings({ "rawtypes" })
		ComboBoxModel comboModel = new DefaultComboBoxModel(items);
		chooseGenSetCombo.setModel(comboModel);
 	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void populateMemberList(GeneralizationSet genSet)
	{
		List<String> generalizations = new ArrayList<String>();
		for (Generalization item : genSet.getGeneralization()) {
			generalizations.add(item.getSpecific().getName() + "->" + item.getGeneral().getName());
		}
		memberList.setModel(new DefaultComboBoxModel(generalizations.toArray()));
	}
	
 	@SuppressWarnings("rawtypes")
	private void initGUI() {
		try {
			{
				setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
				setTitle(ApplicationResources.getInstance().getString("dialog.gen.title"));
				
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
					contentTabbedPane = new JTabbedPane();
					getContentPane().add(contentTabbedPane, BorderLayout.CENTER);
					{
						generalizationPanel = new JPanel();
						GroupLayout generalizationPanelLayout = new GroupLayout((JComponent)generalizationPanel);
						generalizationPanel.setLayout(generalizationPanelLayout);
						contentTabbedPane.addTab(ApplicationResources.getInstance().getString("dialog.gen.generalization"), null, generalizationPanel, null);
						{
							parentPanel = new JPanel();
							GroupLayout jPanel1Layout = new GroupLayout((JComponent)parentPanel);
							parentPanel.setLayout(jPanel1Layout);
							parentPanel.setBorder(BorderFactory.createTitledBorder(null, ApplicationResources.getInstance().getString("dialog.gen.parentgensets"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
							{
								parentTablePanel = new JPanel();
								BorderLayout parentTablePanelLayout = new BorderLayout();
								parentTablePanel.setLayout(parentTablePanelLayout);
								{
									parentScroll = new JScrollPane();
									parentTablePanel.add(parentScroll, BorderLayout.CENTER);
									{
										parentList = new JList();
										parentScroll.setViewportView(parentList);
										parentList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
									}
								}
							}
							jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(parentTablePanel, 0, 381, Short.MAX_VALUE)
								.addContainerGap());
							jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(parentTablePanel, 0, 263, Short.MAX_VALUE)
								.addContainerGap());
						}
						{
							addToLabel = new JLabel();
							addToLabel.setText(ApplicationResources.getInstance().getString("stdcaption.addto"));
						}
						{
							//ComboBoxModel generalizationSetsComboModel = new DefaultComboBoxModel(new String[] { "Item One", "Item Two" });
							addGenSetCombo = new JComboBox();
							//addGenSetCombo.setModel(generalizationSetsComboModel);
						}
						{
							addButton = new JButton();
							addButton.setText(ApplicationResources.getInstance().getString("stdcaption.add"));
							addButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									addButtonActionPerformed(evt);
								}
							});
						}
						{
							removeButton = new JButton();
							removeButton.setText(ApplicationResources.getInstance().getString("stdcaption.remove"));
							removeButton.setSize(80, 23);
							removeButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									removeButtonActionPerformed(evt);
								}
							});
						}
						generalizationPanelLayout.setHorizontalGroup(generalizationPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(generalizationPanelLayout.createParallelGroup()
							    .addComponent(parentPanel, GroupLayout.Alignment.LEADING, 0, 415, Short.MAX_VALUE)
							    .addGroup(GroupLayout.Alignment.LEADING, generalizationPanelLayout.createSequentialGroup()
							        .addComponent(addToLabel, 0, 71, Short.MAX_VALUE)
							        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							        .addComponent(addGenSetCombo, 0, 239, Short.MAX_VALUE)
							        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							        .addGroup(generalizationPanelLayout.createParallelGroup()
							            .addComponent(addButton, GroupLayout.Alignment.LEADING, 0, 81, Short.MAX_VALUE)
							            .addComponent(removeButton, GroupLayout.Alignment.LEADING, 0, 81, Short.MAX_VALUE))))
							.addContainerGap());
						generalizationPanelLayout.setVerticalGroup(generalizationPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(generalizationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							    .addComponent(addGenSetCombo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(addToLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(addButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(parentPanel, 0, 141, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, GroupLayout.PREFERRED_SIZE)
							.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap());
					}
					{
						generalizationSetPanel = new JPanel();
						GroupLayout generalizationSetPanelLayout = new GroupLayout((JComponent)generalizationSetPanel);
						generalizationSetPanel.setLayout(generalizationSetPanelLayout);
						contentTabbedPane.addTab(ApplicationResources.getInstance().getString("dialog.gen.generalizationset"), null, generalizationSetPanel, null);
						{
							genSetPropertiesPanel = new JPanel();
							GroupLayout createGeneratizationSetPanelLayout = new GroupLayout((JComponent)genSetPropertiesPanel);
							genSetPropertiesPanel.setLayout(createGeneratizationSetPanelLayout);
							genSetPropertiesPanel.setBorder(BorderFactory.createTitledBorder(ApplicationResources.getInstance().getString("dialog.gen.properties")));
							{
								nameLabel = new JLabel();
								nameLabel.setText(ApplicationResources.getInstance().getString("stdcaption.name"));
								nameLabel.setEnabled(false);
							}
							{
								deleteButton = new JButton();
								deleteButton.setText(ApplicationResources.getInstance().getString("stdcaption.delete"));
								deleteButton.setSize(80, 23);
								deleteButton.setEnabled(false);
								deleteButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										deleteButtonActionPerformed(evt);
									}
								});
							}
							{
								nameText = new JTextField();
								nameText.setEnabled(false);
							}
							{
								coveringCheck = new JCheckBox();
								coveringCheck.setText(ApplicationResources.getInstance().getString("dialog.gen.covering"));
								coveringCheck.setEnabled(false);
							}
							{
								disjointCheck = new JCheckBox();
								disjointCheck.setText(ApplicationResources.getInstance().getString("dialog.gen.disjoint"));
								disjointCheck.setEnabled(false);
							}
							{
								saveButton = new JButton();
								saveButton.setText(ApplicationResources.getInstance().getString("dialog.gen.save"));
								saveButton.setEnabled(false);
								saveButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										saveButtonActionPerformed(evt);
									}
								});
							}
							createGeneratizationSetPanelLayout.setHorizontalGroup(createGeneratizationSetPanelLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
								.addGap(24)
								.addGroup(createGeneratizationSetPanelLayout.createParallelGroup()
								    .addGroup(GroupLayout.Alignment.LEADING, createGeneratizationSetPanelLayout.createSequentialGroup()
								        .addGap(0, 0, Short.MAX_VALUE)
								        .addComponent(coveringCheck, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								        .addGap(53)
								        .addGroup(createGeneratizationSetPanelLayout.createParallelGroup()
								            .addGroup(GroupLayout.Alignment.LEADING, createGeneratizationSetPanelLayout.createSequentialGroup()
								                .addComponent(disjointCheck, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								                .addGap(10))
								            .addGroup(GroupLayout.Alignment.LEADING, createGeneratizationSetPanelLayout.createSequentialGroup()
								                .addGap(0, 23, GroupLayout.PREFERRED_SIZE)
								                .addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
								        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								        .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
								    .addComponent(nameText, GroupLayout.Alignment.LEADING, 0, 319, Short.MAX_VALUE))
								.addContainerGap());
							createGeneratizationSetPanelLayout.setVerticalGroup(createGeneratizationSetPanelLayout.createSequentialGroup()
								.addGroup(createGeneratizationSetPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								    .addComponent(nameText, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								    .addComponent(nameLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(createGeneratizationSetPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								    .addComponent(disjointCheck, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
								    .addComponent(coveringCheck, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(21)
								.addGroup(createGeneratizationSetPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								    .addComponent(deleteButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								    .addComponent(saveButton, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap());
						}
						{
							memberPanel = new JPanel();
							GroupLayout memberPanelLayout = new GroupLayout((JComponent)memberPanel);
							memberPanel.setLayout(memberPanelLayout);
							memberPanel.setBorder(BorderFactory.createTitledBorder(ApplicationResources.getInstance().getString("dialog.gen.membergeneralizations")));
							{
								memberTablePanel = new JPanel();
								BorderLayout jPanel1Layout1 = new BorderLayout();
								memberTablePanel.setLayout(jPanel1Layout1);
								{
									memberScroll = new JScrollPane();
									memberTablePanel.add(memberScroll, BorderLayout.CENTER);
									{
										memberList = new JList();
										memberScroll.setViewportView(memberList);
										memberList.setPreferredSize(new java.awt.Dimension(378, 148));
										memberList.setEnabled(false);
										memberList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
									}
								}
							}
							memberPanelLayout.setHorizontalGroup(memberPanelLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(memberTablePanel, 0, 381, Short.MAX_VALUE)
								.addContainerGap());
							memberPanelLayout.setVerticalGroup(memberPanelLayout.createSequentialGroup()
								.addComponent(memberTablePanel, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
						}
						{
							//ComboBoxModel chooseGenSetComboModel = new DefaultComboBoxModel(new String[] { "Item One", "Item Two" });
							chooseGenSetCombo = new JComboBox();
							//chooseGenSetCombo.setModel(chooseGenSetComboModel);
							chooseGenSetCombo.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									chooseGenSetComboActionPerformed(evt);
								}
							});
						}
						{
							chooseLabel = new JLabel();
							chooseLabel.setText(ApplicationResources.getInstance().getString("dialog.gen.choose"));
						}
						generalizationSetPanelLayout.setHorizontalGroup(generalizationSetPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(generalizationSetPanelLayout.createParallelGroup()
							    .addGroup(GroupLayout.Alignment.LEADING, generalizationSetPanelLayout.createSequentialGroup()
							        .addComponent(chooseLabel, 0, 70, Short.MAX_VALUE)
							        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							        .addComponent(chooseGenSetCombo, 0, 333, Short.MAX_VALUE))
							    .addComponent(genSetPropertiesPanel, GroupLayout.Alignment.LEADING, 0, 415, Short.MAX_VALUE)
							    .addComponent(memberPanel, GroupLayout.Alignment.LEADING, 0, 415, Short.MAX_VALUE))
							.addContainerGap());
						generalizationSetPanelLayout.setVerticalGroup(generalizationSetPanelLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(generalizationSetPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							    .addComponent(chooseGenSetCombo, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
							    .addComponent(chooseLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(genSetPropertiesPanel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(memberPanel, 0, 189, Short.MAX_VALUE)
							.addContainerGap());
					}
				}
			}
			this.setSize(460, 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
 	private void okButtonActionPerformed(ActionEvent evt) {
 		if(needsSaveGenSet)
 		{
 			//saveButtonActionPerformed(evt);
 		}
 		dispose();
	}

	private void cancelButtonActionPerformed(ActionEvent evt) {
 		dispose();
	}
	
	private void addButtonActionPerformed(ActionEvent evt) {
		String selected = (String) addGenSetCombo.getSelectedItem();
		if(!selected.equals(chooseOpt))
		{
			GeneralizationSet genSet = modelGeneralizationSets.get(selected);
			Generalization generalization =  generalizationElement.getGeneralization();
			generalization.getGeneralizationSet().add(genSet);
			genSet.getGeneralization().add(generalization);
		}
		populateParentList();
	}
	
	private void removeButtonActionPerformed(ActionEvent evt) {
		String selected = (String) parentList.getSelectedValue();
		GeneralizationSet genSet = modelGeneralizationSets.get(selected);
		Generalization generalization = generalizationElement.getGeneralization();
		generalization.getGeneralizationSet().remove(genSet);
		genSet.getGeneralization().remove(generalization);
		populateParentList();
	}
 
	private void chooseGenSetComboActionPerformed(ActionEvent evt) {
		String selected  = (String) chooseGenSetCombo.getSelectedItem();
		if(!selected.equals(chooseOpt)){
			if(!selected.equals(newOpt))
			{
				setGenSetPropertiesEnabled(true);
				GeneralizationSet genSet = modelGeneralizationSets.get(selected);
				nameText.setText(genSet.getName());
				disjointCheck.setSelected(genSet.isIsDisjoint());
				coveringCheck.setSelected(genSet.isIsCovering());
				populateMemberList(genSet);
			}
			else
			{
				setGenSetPropertiesEnabled(true);
				resetGenSetProperties();
			}
			needsSaveGenSet = true;
		}
		else
		{
			setGenSetPropertiesEnabled(false);
			resetGenSetProperties();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void resetGenSetProperties() {
		nameText.setText(null);
		disjointCheck.setSelected(false);
		coveringCheck.setSelected(false);
		memberList.setModel(new DefaultComboBoxModel());
	}

	private void setGenSetPropertiesEnabled(boolean flag)
 	{
		for (Component item : genSetPropertiesPanel.getComponents()) {
			item.setEnabled(flag);
		}
		
		for (Component item : memberPanel.getComponents()) {
			item.setEnabled(flag);
		}
 	}
	
 	protected void saveButtonActionPerformed(ActionEvent evt) {
 		String selected = (String) chooseGenSetCombo.getSelectedItem();
 		if(selected.equals(newOpt))
		{
 			if(nameText.getText().trim().length() > 0)
 	 		{
 	 			GeneralizationSet genSet = ModelHelper.getFactory().createGeneralizationSet();
 	 	 		genSet.setName(nameText.getText().trim());	
 	 	 		genSet.setIsCovering(coveringCheck.isSelected());
 	 	 		genSet.setIsDisjoint(disjointCheck.isSelected());
 	 	 		
 	 	 		//diagramManager.getCurrentProject().addElement(genSet);
 	 	 		//loadModelGeneralizationSets();
 	 	 		
 	 	 		UmlProject project = diagramManager.getCurrentProject();
				
				AddCommand cmd = new AddCommand(project.getEditingDomain(), project.getModel().getPackagedElement(), genSet);
				project.getEditingDomain().getCommandStack().execute(cmd);
 	 	 		 
 	 	 		loadModelGeneralizationSets();
 	 	 		populateChooseGenSetCombo();
 	 	 		chooseGenSetCombo.setSelectedItem(genSet.getName());
 	 	 		populateAddGenSetCombo();
 	 	 		
 	 	 		needsSaveGenSet = false; 	
 	 	 		
 	 	 		// Guarantee that the GenSet is added to the parser in the current Project (UmlProject)
 	 	 		OntoUMLParser ontoparser = ProjectBrowser.getParserFor(diagramManager.getCurrentProject());
 	 	 		ontoparser.addElement(genSet);
 	 	 		ProjectBrowser.updateModelTree(diagramManager.getCurrentProject());
 	 		}
 	 		else
 	 		{
 	 			nameText.grabFocus();
 	 		}
		}
 		else
 		{
 			GeneralizationSet genSet = modelGeneralizationSets.get(selected);
 	 		genSet.setName(nameText.getText().trim());	
 	 		genSet.setIsCovering(coveringCheck.isSelected());
 	 		genSet.setIsDisjoint(disjointCheck.isSelected());
 	 		
 	 		loadModelGeneralizationSets();
 	 		populateChooseGenSetCombo();
 	 		chooseGenSetCombo.setSelectedItem(genSet.getName());
 	 		populateAddGenSetCombo();
 	 		
 	 		needsSaveGenSet = false; 	 		
 		}
	}
	
	protected void deleteButtonActionPerformed(ActionEvent evt) {
		String selected = (String) chooseGenSetCombo.getSelectedItem();
		if(!selected.equals(newOpt) && !selected.equals(chooseOpt))
		{
			GeneralizationSet genSet = modelGeneralizationSets.get(selected);
			UmlProject project = diagramManager.getCurrentProject();
			
			DeleteCommand cmd = (DeleteCommand) DeleteCommand.create(project.getEditingDomain(), genSet);
			project.getEditingDomain().getCommandStack().execute(cmd);
			
			loadModelGeneralizationSets();
			populateChooseGenSetCombo();
			
			// Guarantee that the GenSet is removed in the parser in the current Project (UmlProject)
 	 	 	OntoUMLParser ontoparser = ProjectBrowser.getParserFor(diagramManager.getCurrentProject());
 	 	 	ontoparser.removeElement(genSet);
 	 	 	ProjectBrowser.updateModelTree(diagramManager.getCurrentProject());
		}
	}
	
}
