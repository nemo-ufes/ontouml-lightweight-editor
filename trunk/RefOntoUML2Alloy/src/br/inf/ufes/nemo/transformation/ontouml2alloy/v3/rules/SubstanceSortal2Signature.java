package br.inf.ufes.nemo.transformation.ontouml2alloy.v3.rules;

import RefOntoUML.impl.SubstanceSortalImpl;
import br.inf.ufes.nemo.transformation.Rule;
import br.inf.ufes.nemo.transformation.Transformer;
import br.inf.ufes.nemo.transformation.ontouml2alloy.v3.base.AlloySubstanceSortal;

public class SubstanceSortal2Signature implements Rule<SubstanceSortalImpl, AlloySubstanceSortal> {

	@Override
	public boolean check(SubstanceSortalImpl source) {		
		return true;
	}

	@Override
	public AlloySubstanceSortal build(SubstanceSortalImpl source, Transformer t) {
		AlloySubstanceSortal target = new AlloySubstanceSortal(source.getName(), source.isIsAbstract());
		return target;
	}

	@Override
	public void setProperties(AlloySubstanceSortal target, SubstanceSortalImpl source, Transformer t) {
		target.setName(source.getName());
		target.setAbstract(source.isIsAbstract());
	}
}
