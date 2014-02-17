package br.ufes.inf.nemo.assistant.wizard;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.graph.NodeAssistant;
import br.ufes.inf.nemo.assistant.manager.PageProcessor;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.NewGeneralizationSet;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.Question;
import br.ufes.inf.nemo.assistant.wizard.pageassistant.WizardPageAssistant;

public class WizardAssitant extends Wizard {

	private GraphAssistant graph;

	public WizardAssitant(GraphAssistant graph) {
		super();
		setNeedsProgressMonitor(false);
		setHelpAvailable(true);

		this.graph = graph;
	}

	@Override
	public void addPages() {
		ArrayList<NodeAssistant> nodes = graph.getNodeList(); 
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).isAction()){
				if(i==0){
					//TODO se for outra action vai dar problema
					addPage(nodes.get(0).getNextNode().getPage());
				}
			}else{
				addPage(nodes.get(i).getPage());
			}
		}
	}

	@Override
	public void addPage(IWizardPage page) {
		IWizardPage[] pages = getPages();
		for (IWizardPage pg : pages) {
			if(page.equals(pg)){
				return;
			}
		}
		super.addPage(page);
	}
	
	private boolean canFinish = false;
	@Override
	public boolean performFinish() {
		if(graph.getCurrentNode().isEndNode()){
			graph.getManagerPattern().run(graph.getCurrentNode().getPage());
			return true;
		}
		return canFinish;
	}

	/**
	 * Used to prevent the back button perform
	 * */
	@Override
	public IWizardPage getPreviousPage(IWizardPage page) {
		return page;
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

		//Set currentNode
		graph.setCurrentNode(nextNode);

		//Special treats
		if(nextPage instanceof Question){
			PageProcessor.treatPage(nextNode, (Question)nextPage);
		}else if(nextPage instanceof NewGeneralizationSet){
			PageProcessor.treatPage(nextNode, (NewGeneralizationSet)nextPage);
		}

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
}
