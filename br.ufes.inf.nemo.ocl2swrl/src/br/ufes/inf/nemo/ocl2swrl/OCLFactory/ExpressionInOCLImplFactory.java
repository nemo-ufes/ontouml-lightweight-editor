package br.ufes.inf.nemo.ocl2swrl.OCLFactory;

import org.eclipse.ocl.uml.impl.ExpressionInOCLImpl;
import org.eclipse.ocl.uml.impl.OCLExpressionImpl;
import org.eclipse.uml2.uml.internal.impl.NamedElementImpl;



/**
 * @author fredd_000
 * @version 1.0
 * @created 24-set-2013 09:16:12
 */
public class ExpressionInOCLImplFactory extends OpaqueExpressionImplFactory {

	public OCLExpressionImplFactory m_OCLExpressionImplFactory;
	public ExpressionInOCLImpl m_ExpressionInOCLImpl;

	public ExpressionInOCLImplFactory(NamedElementImpl m_NamedElementImpl){
		super(m_NamedElementImpl);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	
	@Override
	public void solve() {
		OCLExpressionImpl bodyExpression = (OCLExpressionImpl) m_ExpressionInOCLImpl.getBodyExpression();
		
		m_OCLExpressionImplFactory = OCLExpressionImplFactory.constructor(bodyExpression);
		m_OCLExpressionImplFactory.solve();
		
	}
}