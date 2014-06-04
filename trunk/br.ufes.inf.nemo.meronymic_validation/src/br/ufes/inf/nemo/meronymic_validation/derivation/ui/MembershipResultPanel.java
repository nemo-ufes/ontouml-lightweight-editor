package br.ufes.inf.nemo.meronymic_validation.derivation.ui;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.derivation.MembershipDerivationTask;
import br.ufes.inf.nemo.meronymic_validation.derivation.DerivationTask;

public class MembershipResultPanel extends DerivedResultPanel{

	private static final long serialVersionUID = 8900588864733339651L;

	public MembershipResultPanel(OntoUMLParser parser) {
		super(parser);
	}

	@Override
	public DerivationTask<?> createDerivationTask() {
		return new MembershipDerivationTask(parser, table);
	}

	@Override
	protected String getRelationTypeString() {
		return "SubCollectionOf and MemberOf";
	}

}
