package br.ufes.inf.nemo.meronymic_validation.forbidden;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import RefOntoUML.memberOf;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.forbidden.ui.ForbiddenTable;
import br.ufes.inf.nemo.meronymic_validation.graph.EdgePath;

public class IntransitiveMemberOfTask extends ForbiddenTask<memberOf>{
		
	public IntransitiveMemberOfTask(OntoUMLParser parser, ForbiddenTable table) {
		super(parser, table);
		existing.addAll(parser.getAllInstances(memberOf.class));
	}
	
	public void findIntransitiveMemberOf(){

		if(!arePathsSet())
			setPaths();
		
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
		ForbiddenMemberOf forbidden = new ForbiddenMemberOf(direct);
		forbidden.setPath(path.getEdgeIdsOfType(Property.class));
		
		publish(forbidden);
		return forbidden;
	}


	@Override
	protected Boolean doInBackground() throws Exception {
		setProgress(1);
		setPaths();
		setProgress(40);
		findIntransitiveMemberOf();
		setProgress(100);
		return true;
	}
	
}
