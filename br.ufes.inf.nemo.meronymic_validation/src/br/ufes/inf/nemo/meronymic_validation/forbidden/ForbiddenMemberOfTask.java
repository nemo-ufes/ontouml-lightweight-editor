package br.ufes.inf.nemo.meronymic_validation.forbidden;

import javax.swing.JTabbedPane;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.memberOf;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.graph.EdgePath;
import br.ufes.inf.nemo.meronymic_validation.userinterface.ForbiddenTableModel;

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
		setProgress(1);
		System.out.println("Intransitive MemberOf: Creating meronymic graph...");
		setPaths(false, false);
		setProgress(40);
		System.out.println("Intransitve MemberOf: Analyzing graph...");
		findIntransitiveMemberOf();
		setProgress(100);
		return true;
	}
	
	@Override
	protected void done() {
		System.out.println("Intransitve MemberOf: Analysis successfuly concluded!");
		System.out.println("Intransitve MemberOf: "+forbidden.size()+" forbidden relations found!");
	}
	
}
