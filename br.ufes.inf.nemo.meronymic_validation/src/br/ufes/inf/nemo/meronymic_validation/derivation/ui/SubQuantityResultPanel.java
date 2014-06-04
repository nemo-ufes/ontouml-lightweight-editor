package br.ufes.inf.nemo.meronymic_validation.derivation.ui;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.meronymic_validation.derivation.DerivationTask;
import br.ufes.inf.nemo.meronymic_validation.derivation.SubQuantityDerivationTask;

public class SubQuantityResultPanel extends DerivedResultPanel{

	private static final long serialVersionUID = 2173651994065644181L;

	public SubQuantityResultPanel(OntoUMLParser parser) {
		super(parser);
	}

	@Override
	public DerivationTask<?> createDerivationTask() {
		return new SubQuantityDerivationTask(parser, table);
	}

	@Override
	protected String getRelationTypeString() {
		return "SubQuantityOf";
	}

}
