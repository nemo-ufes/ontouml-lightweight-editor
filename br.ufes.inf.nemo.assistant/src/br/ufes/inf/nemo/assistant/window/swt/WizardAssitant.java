package br.ufes.inf.nemo.assistant.window.swt;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import br.ufes.inf.nemo.assistant.window.swt.GraphAssistant.GraphAssistant;
import br.ufes.inf.nemo.assistant.window.swt.GraphAssistant.NodeAssistant;

//http://krishnanmohan.wordpress.com/2011/11/22/jface-wizard-explained-enablingdisabling-next-finish-buttons/
public class WizardAssitant extends Wizard {

	private WizardPageAssistant thisPage;
	private GraphAssistant graph;

	public WizardAssitant(GraphAssistant graph) {
		super();
		setNeedsProgressMonitor(true);
		setHelpAvailable(true);

		this.graph = graph;
	}

	@Override
	public void addPages() {
		for(NodeAssistant node:graph.getNodeList()){
			addPage(node.getPage());
		}
	}

	private boolean canFinish = false;
	
	@Override
	public boolean performFinish() {
		return canFinish;
	}

	public void setCurrentPage(WizardPageAssistant page){
		thisPage = page;
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage currentPage) {
		WizardPageAssistant nextPage = null;

		System.out.println("getNextPage - "+graph.getCurrentNode().getPage().getName());
		
		if(graph.getCurrentNode().getPage().next()){
			nextPage = graph.getCurrentNode().getNextNode().getPage();
		}else if(graph.getCurrentNode().getPage().nextTrue()){
			nextPage = graph.getCurrentNode().getTrueNode().getPage();
		}else if(graph.getCurrentNode().getPage().nextFalse()){
			nextPage = graph.getCurrentNode().getFalseNode().getPage();
		}

		if(graph.getCurrentNode().isEndNode()){
			canFinish = true;
		}
		
		//Get all necessaries variables
		processGets(currentPage);
		
		//Set all necessaries variables
		processSets(nextPage);
		
		return nextPage;
	} 
	
	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		return page;
	}
	
	private void processGets(IWizardPage page) {
		if(page instanceof NewClass){
			getVariablesFromNewClass();
		}else if(page instanceof NewGenericRelation){
			getVariablesFromNewGenericRelation();
		}
	}
	
	private void processSets(WizardPageAssistant page) {	
		if(page instanceof NewClass){
			setVariablesFromNewClass();
		}else if(page instanceof NewGenericRelation){
			setVariablesFromNewGenericRelation();
		}
	}
	
	/**
	 * Variables for all WizardPageAssistants
	 * */

	private String currentClass;
	private String currentStereotype;

	private void getVariablesFromNewClass(){
		NewClass page = (NewClass) thisPage;
//		currentClass = page.getCurrentClass();
//		currentStereotype = page.getCurrentStereotype();
	}

	private void getVariablesFromNewGenericRelation(){
		NewGenericRelation page = (NewGenericRelation) thisPage;
	}


	private void setVariablesFromNewClass(){
		//Nothing to do
	}

	private void setVariablesFromNewGenericRelation(){
		
	}

	public static void main(String[] args) {
		GraphAssistant graph = new GraphAssistant();

		//Node 1
		NewClass nc = new NewClass();
		nc.setStereotypes(new String[]{"Kind","Sukind","Role"});
		NodeAssistant n1 = new NodeAssistant(graph, nc);
		
		//Node 2
		Question q = new Question();
		q.setQuestion("chose your destination");
		NodeAssistant n2 = new NodeAssistant(graph, q);
		
		//Node 3
		NewGenericRelation ngr = new NewGenericRelation();
		NodeAssistant n3 = new NodeAssistant(graph, ngr);
		
		//Node 4
		NewClass nc2 = new NewClass();
		nc2.setStereotypes(new String[]{"Phase","Collective","Role"});
		NodeAssistant n4 = new NodeAssistant(graph, nc2);
		
		n1.setNextNode(n2);
		n2.setTrueNode(n3);
		n2.setFalseNode(n4);
		n3.setNextNode(null);
		n4.setNextNode(null);
		
		graph.setStartNode(n1);
		graph.updateNodeList();
		
		for(NodeAssistant node:graph.getNodeList()){
			System.out.println(node.getPage().getName());
		}
		
		
		WizardDialog wizardDialog = new WizardDialog(new Shell(),new WizardAssitant(graph));
		if (wizardDialog.open() == Window.OK) {
			System.out.println("Ok pressed");
		} else {
			System.out.println("Cancel pressed");
		}
	}

}
