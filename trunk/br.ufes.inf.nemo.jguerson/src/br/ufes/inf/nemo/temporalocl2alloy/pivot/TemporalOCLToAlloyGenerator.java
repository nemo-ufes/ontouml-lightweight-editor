package br.ufes.inf.nemo.temporalocl2alloy.pivot;

public class TemporalOCLToAlloyGenerator {

	public static String replace_w (String expression, String new_w)
	{
		expression = expression.replace("w.", new_w+".");
		expression = expression.replace(".w", "."+new_w);
		expression = expression.replace("[w]", "["+new_w+"]");
		return expression;
	}
	
	/**
	 * context C always P globally.
	 *
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @return
	 */
	public static String alwaysGlobally (String C, String P)
	{
		return 
		"all w: World | all self: "+C+" | "+P;
	}
	
	/**
	 * context C always P before R.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param R - R(w, self)
	 * @return
	 */
	public static String alwaysBefore (String C, String P, String R)
	{
		return 
		"not (some b: Branch | all w: World[b] | all self: "+C+" | "+ 
		"not "+R+" and not "+P+" and (some b2: Branch[w] | some w2: Between[w,b2] | "+
		replace_w(R,"w2")+") implies (all fw: Former[w] | not "+replace_w(R,"fw")+"))";
	}
	
	/**
	 * context C always P after Q.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param Q - Q(w, self)
	 * @return
	 */
	public static String alwaysAfter (String C, String P, String Q)
	{
		return 
		"all b: Branch | all w: World[b] | all self: "+C+" | "+ 
		Q+" implies (all lw: Latter[w] | "+replace_w(P,"lw")+")";
	}
}
