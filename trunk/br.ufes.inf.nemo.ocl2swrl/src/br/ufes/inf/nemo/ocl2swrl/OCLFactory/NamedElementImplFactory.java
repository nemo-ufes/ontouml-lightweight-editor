package br.ufes.inf.nemo.ocl2swrl.OCLFactory;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.ocl.uml.impl.OperationCallExpImpl;
import org.eclipse.ocl.uml.impl.PropertyCallExpImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;
import org.semanticweb.owlapi.model.SWRLAtom;

import br.ufes.inf.nemo.ocl2swrl.exceptions.NonImplemented;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonSupported;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:13
 */
public class NamedElementImplFactory {

	public NamedElementImpl m_NamedElementImpl;
	
	public Set<SWRLAtom> antecedent;
	public Set<SWRLAtom> consequent;
	
	public NamedElementImplFactory(NamedElementImpl m_NamedElementImpl){
		this.m_NamedElementImpl = m_NamedElementImpl;
		antecedent = new HashSet<SWRLAtom>();
		consequent = new HashSet<SWRLAtom>();
	}

	public void finalize() throws Throwable {

	}

	public void solve() {
		throw new NonImplemented("solve()");
	}
	
	public Set<SWRLAtom> getAntecedent() {
		return antecedent;
	}
	
	public Set<SWRLAtom> getConsequent() {
		return consequent;
	}
	

	
	public static OCLExpressionImplFactory constructor(OCLExpressionImpl m_OCLExpressionImpl){
		Class<? extends OCLExpressionImpl> c = m_OCLExpressionImpl.getClass();
		if(c.equals(PropertyCallExpImpl.class)){
			return new PropertyCallExpImplFactory((PropertyCallExpImpl) m_OCLExpressionImpl);
		}else if(c.equals(OperationCallExpImpl.class)){
			return new OperationCallExpImplFactory((OperationCallExpImpl) m_OCLExpressionImpl);
		}else{
			throw new NonSupported(c.getName());
		}
	}
}