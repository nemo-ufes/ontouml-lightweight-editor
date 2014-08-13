package br.ufes.inf.nemo.assistant.pattern.window.selctionbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public abstract class ClassSelectionPanel extends JPanel {

	protected HashMap<JCheckBox, JTextField> hashChkEdit = new HashMap<>();
	protected HashMap<JTextField, JComboBox<String>> hashEditCombo = new HashMap<>();
	protected HashMap<JCheckBox, JComboBox<String>> hashChkCombo = new HashMap<>();

	protected OntoUMLParser parser;

	protected ActionListener getCheckBoxActionListener(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox chk = (JCheckBox)e.getSource();
				JTextField edit = hashChkEdit.get(chk);
				JComboBox<String> cb = hashChkCombo.get(chk);

				if(chk.isSelected()){
					edit.setVisible(false);
					edit.setEnabled(false);
					
					cb.setVisible(true);
					cb.setEnabled(true);
				}else{
					edit.setVisible(true);
					edit.setEnabled(true);
					
					cb.setVisible(false);
					cb.setEnabled(false);
				}
			}
		};
	}
	
	protected ActionListener getCheckBoxActionListener(final JComboBox<String> type, final JLabel lbType){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox chk = (JCheckBox)e.getSource();
				JTextField edit = hashChkEdit.get(chk);
				JComboBox<String> cb = hashChkCombo.get(chk);

				if(chk.isSelected()){
					edit.setVisible(false);
					edit.setEnabled(false);
					
					cb.setVisible(true);
					cb.setEnabled(true);
					
					type.setVisible(false);
					type.setEnabled(false);
					
					lbType.setVisible(false);
					lbType.setEnabled(false);
				}else{
					edit.setVisible(true);
					edit.setEnabled(true);
					
					cb.setVisible(false);
					cb.setEnabled(false);
					
					type.setVisible(true);
					type.setEnabled(true);
					
					lbType.setVisible(true);
					lbType.setEnabled(true);
				}
			}
		};
	}

	protected OutcomeFixer outcomeFixer;
	protected Fix fix;
	public Fix getFix(){
		return fix;
	}

	protected static final int horizontalDistance = 150;
	protected static final int verticalDistance = 330;

	public abstract void getRunPattern(double x, double y);
	protected abstract void getModelValues(OntoUMLParser parser);

	protected Classifier createClassifier(ClassStereotype stereotype, JCheckBox chk, double x, double y){
		if(chk.isSelected()){
			String _name = (String)hashChkCombo.get(chk).getSelectedItem();	
			Set<RefOntoUML.Class> setClasses = parser.getAllInstances(RefOntoUML.Class.class);
			Iterator<RefOntoUML.Class> clsIte = setClasses.iterator();

			while (clsIte.hasNext()) {
				RefOntoUML.Class cls = clsIte.next();
				if(UtilAssistant.getStringRepresentationClassStereotype(cls).equals(_name)){
					fix.includeAdded(cls,x,y);
					return cls;
				}
			}
			return null;
		}else{
			String _name = hashChkEdit.get(chk).getText();
			RefOntoUML.Classifier classifier = (Classifier) outcomeFixer.createClass(stereotype);
			classifier.setName(_name);
			parser.getModel().getPackagedElement().add(classifier);

			fix.includeAdded(classifier,x,y);

			return classifier;
		}
	}

	protected Classifier createClassifier(JCheckBox chk, double x, double y){
		if(chk.isSelected()){
			String _name = (String)hashChkCombo.get(chk).getSelectedItem();	
			Set<RefOntoUML.Class> setClasses = parser.getAllInstances(RefOntoUML.Class.class);
			Iterator<RefOntoUML.Class> clsIte = setClasses.iterator();

			while (clsIte.hasNext()) {
				RefOntoUML.Class cls = clsIte.next();
				if(UtilAssistant.getStringRepresentationClassStereotype(cls).equals(_name)){
					fix.includeAdded(cls);
					return cls;
				}
			}
			return null;
		}else{
			String _name = hashChkEdit.get(chk).getText();
			String stereo = (String) hashEditCombo.get(hashChkEdit.get(chk)).getSelectedItem();
			ClassStereotype stereotype = ClassStereotype.valueOf(stereo.toUpperCase());

			RefOntoUML.Classifier classifier = (Classifier) outcomeFixer.createClass(stereotype);
			classifier.setName(_name);
			parser.getModel().getPackagedElement().add(classifier);

			fix.includeAdded(classifier,x,y);

			return classifier;
		}
	}
	
	protected static DefaultComboBoxModel<String> getCBModelFromSets(Classifier current, Set<? extends Classifier>... sets){
	    DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>();
	    String currentStringRepresentation = UtilAssistant.getStringRepresentationStereotype(current) +" - "+ UtilAssistant.getStringRepresentationClass(current);
	    
		for(Set<? extends Classifier> set : sets){
	        if(!set.isEmpty()){
	        	String[] vector = UtilAssistant.getStringRepresentationClassStereotype(set);
	        	
	        	for(String p:vector){
	        		if(!p.equals(currentStringRepresentation))
	        			cbModel.addElement(p);
				}
	        }
	    }
		
		return cbModel;
	}
	
	protected DefaultComboBoxModel<String> getCBModelFromSets(Set<? extends Classifier>... sets){
	    DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<String>();
	    
		for(Set<? extends Classifier> set : sets){
	        if(!set.isEmpty()){
	        	String[] vector = UtilAssistant.getStringRepresentationClassStereotype(set);
	        	
	        	for(String p:vector){
        			cbModel.addElement(p);
				}
	        }
	    }
		
		return cbModel;
	}
}