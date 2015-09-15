package sml2.util;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;

public class Sml2Util
{
	public static String getVerificationMessage(EClass eclass, Object [] substitutions)
	{
		EAnnotation comments = eclass.getEAnnotation("Comments");
		String expr = comments.getDetails().get(substitutions[0]);
		
		return MessageFormat.format("{0} - {1}", substitutions[1], expr);
	}
}
