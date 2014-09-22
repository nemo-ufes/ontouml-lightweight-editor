package br.ufes.inf.nemo.validator.meronymic.forbidden;

import javax.swing.JTabbedPane;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.memberOf;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.validator.meronymic.forbidden.ui.ForbiddenTableModel;

public class ForbiddenMemberOfTask extends ForbiddenTask<memberOf>{

	public ForbiddenMemberOfTask(OntoUMLParser parser, ForbiddenTableModel tableModel, JTabbedPane tabbedPane) {
		super(parser, tableModel);
		existing.addAll(parser.getAllInstances(memberOf.class));
	}
	
	public void findIntransitiveMemberOf(){

		if(!arePathsSet())
			setPaths(false, false);
		
		for (EdgePath path : paths) {
			if(path.isCycle() || path.getEdges().size()==1)
				continue;
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			for (memberOf direct : getDirect(whole, part, memberOf.class)) {
				forbidden.add(createForbiddenMemberOf(direct,path));
			}
		}
	}

	private ForbiddenMemberOf createForbiddenMemberOf(memberOf direct, EdgePath path) {
		ForbiddenMemberOf forbidden = new ForbiddenMemberOf(direct, parser);
		forbidden.setPath(path.getEdgeIdsOfType(Property.class));
		
		publish(forbidden);
		return forbidden;
	}


	@Override
	protected Boolean doInBackground() throws Exception {
		System.out.println("Intransitive MemberOf: Analyzing model...");
		System.out.println("Intransitive MemberOf: Creating meronymic graph...");
		setPaths(false, false);
		System.out.println("Intransitve MemberOf: Analyzing graph...");
		findIntransitiveMemberOf();
		setProgress(50);
		return true;
	}
	
	@Override
	protected void done() {
		System.out.println("Intransitve MemberOf: Analysis successfuly concluded!");
		System.out.println("Intransitve MemberOf: "+forbidden.size()+" forbidden relations found!");
	}
	
}
