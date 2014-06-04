package br.ufes.inf.nemo.meronymic_validation.derivation.ui;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.derivation.FunctionalParthoodDerivationTask;
import br.ufes.inf.nemo.meronymic_validation.derivation.DerivationTask;

public class FunctionalParthoodResultPanel extends DerivedResultPanel{

	private static final long serialVersionUID = 2173651994065644181L;

	public FunctionalParthoodResultPanel(OntoUMLParser parser) {
		super(parser);
	}

	@Override
	public DerivationTask<?> createDerivationTask() {
		return new FunctionalParthoodDerivationTask(parser, table);
	}

	@Override
	protected String getRelationTypeString() {
		return "ComponentOf";
	}

}
