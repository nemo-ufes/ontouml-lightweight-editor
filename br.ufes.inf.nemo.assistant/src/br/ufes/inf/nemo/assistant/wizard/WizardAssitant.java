package br.ufes.inf.nemo.assistant.wizard;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.graph.NodeAssistant;
import br.ufes.inf.nemo.assistant.manager.ManagerPattern;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewClass;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGenericRelation;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewPhase;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewRelator;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.Question;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.WizardPageAssistant;

//http://krishnanmohan.wordpress.com/2011/11/22/jface-wizard-explained-enablingdisabling-next-finish-buttons/
public class WizardAssitant extends Wizard {

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
			if(!node.isAction())
				addPage(node.getPage());
		}
	}

	private boolean canFinish = false;
	@Override
	public boolean performFinish() {
		if(graph.getCurrentNode().isEndNode()){
			graph.getManagerPattern().run(graph.getCurrentNode().getPage());
		}
		return canFinish;
	}

	@Override
	public IWizardPage getNextPage(IWizardPage currentPage) {
		NodeAssistant nextNode = null;
		WizardPageAssistant nextPage = null;
		
		if(graph.getCurrentNode().getPage().next()){
			nextNode = graph.getCurrentNode().getNextNode();
		}else if(graph.getCurrentNode().getPage().nextTrue()){
			nextNode = graph.getCurrentNode().getTrueNode();
		}else if(graph.getCurrentNode().getPage().nextFalse()){
			nextNode = graph.getCurrentNode().getFalseNode();
		}

		if(nextNode.isAction()){
			//The ActionNode treat is doing in getNextNode method
			nextNode = nextNode.getNextNode();
		}
		
		//Set the next page
		nextPage = nextNode.getPage();
		
		//Execute all alterations in RefOntoUML object from the Manager Pattern 
		graph.getManagerPattern().run(currentPage);
		
		if(graph.getCurrentNode().isEndNode()){
			canFinish = true;
		}
		
		/*
		 * All variables are static fields in WizardPageAssistant class than,
		 * we do not need to get the current variables from the current page
		 * and set in the next page, we just need to override setVisible from
		 * the pages to update the for the current value.
		 * 
		 * Implicit actions:
		 * nextPage.setVariables(currentPage.getVariables());
		 * 
		 */
		
		return nextPage;
	} 
	
	/**
	 * Used to prevent the back button perform
	 * */
	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		return page;
	}
	
	public static void main(String[] args) {
		GraphAssistant graph = new GraphAssistant();

		ManagerPattern mp = new ManagerPattern();
		graph.setManagerPattern(mp);

		//Node 1
//		NewClass nc = new NewClass();
//		nc.setStereotypes(new String[]{"Kind","Sukind","Role"});
//		NodeAssistant n1 = new NodeAssistant(graph, nc);
//		
		//Node 2
		NewPhase np = new NewPhase();
		NodeAssistant n1 = new NodeAssistant(graph, np);
		
//		Node 2
//		NewRelator nr = new NewRelator();
//		NodeAssistant n1 = new NodeAssistant(graph, nr);
		
		//Node 2
//		Question q = new Question();
//		q.setQuestion("chose your destination");
//		NodeAssistant n2 = new NodeAssistant(graph, q);
		
//		//Node 2
//		NodeAction n2 = new NodeAction(graph);
//		n2.setAction(ActionEnum.EXIST_SOME_ULTIMATE_SORTAL);
		
		//Node 3
		NewGenericRelation ngr = new NewGenericRelation();
		NodeAssistant n3 = new NodeAssistant(graph, ngr);
		
//		//Node 4
		NewClass nc2 = new NewClass();
		nc2.setStereotypes(new String[]{"Phase","Collective","Role"});
		NodeAssistant n4 = new NodeAssistant(graph, nc2);
		
		n1.setNextNode(n3);
//		n2.setNextNode(n3);
		n3.setNextNode(null);
		
//		n1.setNextNode(n2);
//		n2.setTrueNode(n3);
//		n2.setFalseNode(n4);
//		n3.setNextNode(null);
//		n4.setNextNode(null);
		
		graph.setStartNode(n1);
		graph.updateNodeList();
		
		Display display = Display.getDefault();	    	
		Shell shell = display.getActiveShell();
		WizardDialog wizardDialog = new WizardDialog(shell,new WizardAssitant(graph));
		if (wizardDialog.open() == Window.OK) {
			System.out.println("Ok pressed");
		} else {
			System.out.println("Cancel pressed");
		}
	}

}
