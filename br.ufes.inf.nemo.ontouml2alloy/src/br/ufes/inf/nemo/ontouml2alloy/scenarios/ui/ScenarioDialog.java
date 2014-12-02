package br.ufes.inf.nemo.ontouml2alloy.scenarios.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AntiRigidityScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AssociationDepthScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AssociationMultiplicityScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.AssociationVariabilityScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ComparisonScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.InstantiationScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ParagraphType;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.Scenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentSizeScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.SegmentVariabilityScenario;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryScenario;

public class ScenarioDialog extends JDialog {
	
	private static final long serialVersionUID = -5996120494613921097L;

	private JPanel cards;
	final static String STORY = "Story", SIZE = "Segment Size", SEGVAR = "Segment Variability ", COMP = "Segment Comparison", 
						INST = "Instantiation", ANTI = "Anti-Rigidity", MULT = "Association Multiplicity", 
						ASSVAR = "Association Variability", DEPTH = "Association Depth", EMPTY = "Empty";
	
	
	private final JPanel contentPanel = new JPanel();
	private StoryPanel storyCard;
	private AntiRigidityPanel antiRigidCard;
	
	private JComboBox<String> scenarioCombo;
	private JComboBox<ParagraphType> modeCombo;
	private JButton newButton;
	private JButton delButton;
	private JButton saveButton;
	private JButton checkButton;
	private JButton exploreButton;
	private JButton okButton;
	private JButton cancelButton;
	private JList<Scenario> scenarioList;

	private ActionListener scenarioComboListener = new ActionListener () {
		@Override
		public void actionPerformed(ActionEvent event) {
			CardLayout cl = (CardLayout)(cards.getLayout());
			cl.show(cards, (String) scenarioCombo.getSelectedItem());	
			enableSaveAndDelete();
		}
	};
	
	private ActionListener modeComboListener = new ActionListener () {
		@Override
		public void actionPerformed(ActionEvent event) {
			enableSaveAndDelete();
		}
	};
	
	private ActionListener deleteAction = new ActionListener () {
		@Override
		public void actionPerformed(ActionEvent event) {
			DefaultListModel<Scenario> model = (DefaultListModel<Scenario>) scenarioList.getModel();
			int selectedIndex = scenarioList.getSelectedIndex();
			if (selectedIndex != -1) {
			    model.remove(selectedIndex);
			}
			
			setDefaultUI();
			scenarioList.clearSelection();
		}
	};
	
	private ActionListener newAction = new ActionListener() { 
		public void actionPerformed(ActionEvent e) {
			setDefaultUI();
			scenarioList.clearSelection();
	    }
	};
	
	private ActionListener cancelAction = new ActionListener() { 
		public void actionPerformed(ActionEvent e) {
			setDefaultUI();
			scenarioList.clearSelection();
	    }
	};
	
	private ListSelectionListener listSelection = new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			int selectedIndex = scenarioList.getSelectedIndex();
			
			if(selectedIndex==-1){
				setDefaultUI();
				return;
			}
				
			cancelEditionButton.setEnabled(true);
			delButton.setEnabled(true);
			saveButton.setEnabled(true);
			
			Scenario sc = scenarioList.getModel().getElementAt(selectedIndex);
			modeCombo.setSelectedItem(sc.getParagraphType());
			CardLayout cl = (CardLayout)(cards.getLayout());
			
			if(sc instanceof StoryScenario){
				cl.show(cards, STORY);
				scenarioCombo.setSelectedItem(STORY);
			}
			else if(sc instanceof SegmentSizeScenario){
				cl.show(cards, SIZE);
				scenarioCombo.setSelectedItem(SIZE);
			}
			else if(sc instanceof SegmentVariabilityScenario){
				cl.show(cards, SEGVAR);
				scenarioCombo.setSelectedItem(SEGVAR);
			}
			else if(sc instanceof ComparisonScenario){
				cl.show(cards, COMP);
				scenarioCombo.setSelectedItem(COMP);
			}
			else if(sc instanceof InstantiationScenario){
				cl.show(cards, INST);
				scenarioCombo.setSelectedItem(INST);
			}
			else if(sc instanceof AntiRigidityScenario){
				cl.show(cards, ANTI);
				scenarioCombo.setSelectedItem(ANTI);
			}
			else if(sc instanceof AssociationMultiplicityScenario){
				cl.show(cards, MULT);
				scenarioCombo.setSelectedItem(MULT);
			}
			else if(sc instanceof AssociationVariabilityScenario){
				cl.show(cards, ASSVAR);
				scenarioCombo.setSelectedItem(ASSVAR);
			}
			else if(sc instanceof AssociationDepthScenario){
				cl.show(cards, DEPTH);
				scenarioCombo.setSelectedItem(DEPTH);
			}
			
			JPanel current = getCurrentPanel();
			if(!(current instanceof ScenarioPanel))
				return;
			
			ScenarioPanel sp = (ScenarioPanel) current;
			sp.setScenario(sc);
			sp.loadScenarioUIData();
			
		}
	};
	
	private ActionListener saveScenario = new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	JPanel currentPanel = getCurrentPanel();
	    	
	    	if(!(currentPanel instanceof ScenarioPanel))
	    		return;
	    	
	  		ScenarioPanel<?> sp = (ScenarioPanel<?>) currentPanel;
	    	
	  		if(sp.canSave()){
	    		DefaultListModel<Scenario> model = (DefaultListModel<Scenario>) scenarioList.getModel();
		    	
	    		Scenario sc = sp.saveScenario();
	    		sc.setParagraphType((ParagraphType) modeCombo.getSelectedItem());
	    		
	    		if(!model.contains(sc))
		    		model.addElement(sc);
	  		}
	  		
	  		setDefaultUI();
	  		
	  		scenarioList.clearSelection();
	  		scenarioList.repaint();
	      }
	};
	
	private ActionListener enableButtonsFromCombo = new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	    	  enableSaveAndDelete();
	      }
	};
	
	private ItemListener enableButtonsFromCheckboxes = new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			enableSaveAndDelete();
		}
	};
	
	private ChangeListener enableButtonsFromSpinners = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent arg0) {
			enableSaveAndDelete();
		}
	};

	private ComparisonPanel comparisonCard;

	private JButton cancelEditionButton;

	private SegmentSizePanel segmentSizeCard;

	private SegmentVariabilityPanel segmentVariabilityCard;

	private InstantiationPanel instantiationCard;

	private AssociationMultiplicityPanel associationMultiplicityCard;

	private AssociationVariabilityPanel associationVariabilityCard;

	private AssociationDepthPanel depthCard;

	private JPanel getCurrentPanel() {
		
		for (Component comp : cards.getComponents()) {
		    if (comp.isVisible() == true && comp instanceof JPanel) {
		    	return (JPanel) comp;
		    }
		}
		
		return null;
	}

	public void addEnableListeners(){
		for (Component comp : cards.getComponents()) {
			if(comp instanceof ScenarioPanel){
				ScenarioPanel<?> sp = ((ScenarioPanel<?>) comp);
				sp.addActionListerToCombos(enableButtonsFromCombo);
				sp.addChangeListenerToSpinners(enableButtonsFromSpinners);
				sp.addItemListerToCheckboxes(enableButtonsFromCheckboxes);
			}
		}
	}
	
	private void enableSaveAndDelete() {
		JPanel currentPanel = getCurrentPanel();
		
		if(!(currentPanel instanceof ScenarioPanel)){
			saveButton.setEnabled(false);
			delButton.setEnabled(false);
			return;
		}

		ScenarioPanel<?> sp = (ScenarioPanel<?>) currentPanel;
		
		if(scenarioCombo.getSelectedIndex()==-1 || modeCombo.getSelectedIndex()==-1){
			saveButton.setEnabled(false);
		}
		else{ 
			saveButton.setEnabled(sp.canSave());
		   	delButton.setEnabled(sp.canDelete());
		}
	}

	/**
	 * Create the dialog.
	 */
	public ScenarioDialog(OntoUMLParser parser) {
		setAlwaysOnTop(true);
		setBounds(100, 100, 554, 692);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JTextPane intro = new JTextPane();
		intro.setBackground(SystemColor.control);
		intro.setEditable(false);
		intro.setText("Select a recurrent constraint you want to add to the simulation of your model, choose how you want to apply it, and then fill the particular information. ");
		
		JLabel lblNewLabel = new JLabel("Constraint Type:");
		
		scenarioCombo = new JComboBox<String>();
		scenarioCombo.setModel(new DefaultComboBoxModel<String>(new String[] {STORY, SIZE, SEGVAR, COMP, INST, ANTI, MULT, ASSVAR, DEPTH}));
		
		scenarioCombo.addActionListener(scenarioComboListener);
		
		JLabel lblAddAs = new JLabel("Add as:");
		
		modeCombo = new JComboBox<ParagraphType>();
		modeCombo.setModel(new DefaultComboBoxModel<ParagraphType>(ParagraphType.values()));
		modeCombo.addActionListener(modeComboListener);
		
		 //Create the "cards".
        storyCard = new StoryPanel(null);
        segmentSizeCard = new SegmentSizePanel(parser);
        segmentVariabilityCard = new SegmentVariabilityPanel(parser);
        comparisonCard = new ComparisonPanel(parser);
        instantiationCard = new InstantiationPanel(parser);
        antiRigidCard = new AntiRigidityPanel(parser);
        associationMultiplicityCard = new AssociationMultiplicityPanel(parser);
        associationVariabilityCard = new AssociationVariabilityPanel(parser);
        depthCard = new AssociationDepthPanel(parser);
        JPanel empty = new JPanel();
		
        //Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.setBorder(new TitledBorder(null, "Constraint Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cards.add(empty, EMPTY);
		cards.add(storyCard, STORY);
		cards.add(segmentSizeCard, SIZE);
		cards.add(segmentVariabilityCard,SEGVAR);
		cards.add(comparisonCard, COMP);
		cards.add(instantiationCard, INST);
		cards.add(antiRigidCard, ANTI);
		cards.add(associationMultiplicityCard, MULT);
		cards.add(associationVariabilityCard, ASSVAR);
		cards.add(depthCard, DEPTH);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Created constraints:");
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		flowLayout.setVgap(0);
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(cards, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
						.addComponent(intro, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblAddAs, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(modeCombo, 0, 406, Short.MAX_VALUE)
								.addComponent(scenarioCombo, 0, 417, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(intro, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(scenarioCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddAs)
						.addComponent(modeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(cards, GroupLayout.PREFERRED_SIZE, 241, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		newButton = new JButton("New");
		newButton.setEnabled(true);
		newButton.addActionListener(newAction);
		panel_1.add(newButton);
		
		delButton = new JButton("Del");
		delButton.addActionListener(deleteAction);
		panel_1.add(delButton);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(saveScenario);
		panel_1.add(saveButton);
		
		cancelEditionButton = new JButton("Cancel");
		cancelEditionButton.addActionListener(cancelAction);
		panel_1.add(cancelEditionButton);
		
		scenarioList = new JList<Scenario>();
		scenarioList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scenarioList.setModel(new DefaultListModel<Scenario>());
		scenarioList.addListSelectionListener(listSelection);
		
		scrollPane.setViewportView(scenarioList);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			checkButton = new JButton("Check");
			buttonPane.add(checkButton);
			
			exploreButton = new JButton("Explore");
			buttonPane.add(exploreButton);
			{
				okButton = new JButton("Open Analyzer");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Close");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		addEnableListeners();
		setDefaultUI();
	}

	private void setDefaultUI() {
		
		saveButton.setEnabled(false);
		cancelButton.setEnabled(false);
		delButton.setEnabled(false);
		
		modeCombo.setSelectedItem(null);
		scenarioCombo.setSelectedItem(null);
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, EMPTY);
		
		JPanel currentPanel = getCurrentPanel();
    	
    	if(currentPanel instanceof ScenarioPanel){
    		((ScenarioPanel<?>) currentPanel).setScenario(null);
    		((ScenarioPanel<?>) currentPanel).loadDefaultUIData(true);
    	}
    	
    	setDefaultUIInAllPanes();
	}
	
	public void setDefaultUIInAllPanes(){
		for (Component comp : cards.getComponents()) {
			if(comp instanceof ScenarioPanel){
				ScenarioPanel<?> sp = ((ScenarioPanel<?>) comp);
				sp.loadDefaultUIData();
			}
		}
	}
}
