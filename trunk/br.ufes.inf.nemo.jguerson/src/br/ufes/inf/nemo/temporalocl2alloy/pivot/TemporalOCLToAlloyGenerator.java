package br.ufes.inf.nemo.temporalocl2alloy.pivot;

public class TemporalOCLToAlloyGenerator {

	/**
	 * Replace variable "w" for a new variable "new_w".
	 * 
	 * @param expression: Expression in which the variable is to be replaced
	 * @param new_w: the new variable
	 * @return
	 */
	public static String replace_w (String expression, String new_w)
	{
		expression = expression.replace("w.", new_w+".");
		expression = expression.replace(".w", "."+new_w);
		expression = expression.replace("[w]", "["+new_w+"]");
		return expression;
	}
	
	/**
	 * Append a new line including a tab character before it.
	 * 
	 * @param new_line
	 * @return
	 */
	public static String appendLine (String new_line)
	{
		return "\t"+new_line+"\n";
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
		appendLine("all w: World | all self: "+C+" | ")+
		appendLine("{ "+P+" }");
	}
	
	/**
	 * context C never P globally.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @return 
	 */
	public static String neverGlobally (String C, String P)
	{
		return 
		appendLine("all w: World | all self: "+C+" | ") +
		appendLine("{ not "+P+"}");
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
		appendLine("all b: Branch | ") +
		appendLine("{ all w: World[b] | all self: "+C+" | "+P+" } or ") + 
		appendLine("{ all w: World[b] | all self: "+C+" | not "+R+" } or ") +
		appendLine("{ all w: World[b] | all self: "+C+" | "+R+" and ") +
		appendLine("( all fw: Former[w] | not "+replace_w(R,"fw")+") implies ") +
		appendLine("( all fw: Former[w] | "+replace_w(P,"fw")+") } ");
	}
	
	/**
	 * context C never P before R.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param R - R(w, self)
	 * @return
	 */
	public static String neverBefore (String C, String P, String R)
	{
		return
		appendLine("all b: Branch | ") +
		appendLine("{ all w: World[b] | all self: "+C+" | not "+P+" } or ") + 
		appendLine("{ all w: World[b] | all self: "+C+" | not "+R+" } or ") +
		appendLine("{ all w: World[b] | all self: "+C+" | "+R+" and ") +
		appendLine("( all fw: Former[w] | not "+replace_w(R,"fw")+") implies ") +
		appendLine("( all fw: Former[w] | not "+replace_w(P,"fw")+") } ");
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
		appendLine("all b: Branch | all w: World[b] | all self: "+C+" | ") + 
		appendLine("{ "+Q+" implies all lw: Latter[w] | "+replace_w(P,"lw")+" }");
	}
	
	/**
	 * context C never P after Q.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param Q - Q(w, self)
	 * @return
	 */
	public static String neverAfter (String C, String P, String Q)
	{
		return 
		appendLine("all b: Branch | all w: World[b] | all self: "+C+" | ") + 
		appendLine("{ "+Q+" implies all lw: Latter[w] | not "+replace_w(P,"lw")+" }");
	}
		
	/**
	 * context C always P between (last) Q and R.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param Q - Q(w, self)
	 * @param R - R(w, self)
	 * @param isLastQ  
	 * @return
	 */
	public static String alwaysBetweenAnd (String C, String P, String Q, String R, boolean isLastQ)
	{
		String result = new String();
		
		result += appendLine("all b: Branch | all w: World[b] | all self: "+C+" | ");
		result += appendLine(Q+" and not "+R+" ");		
		
		if (isLastQ) result += appendLine("and (all lw: Latter[w] | not "+replace_w(Q,"lw")+") implies ");
		
		result += appendLine("{ all b2: Branch[w] | ");
		result += appendLine("{ all w2: Latter[w,b2] | "+replace_w(P,"w2")+" } or ");
		result += appendLine("{ all w2: Latter[w,b2] | not "+replace_w(R,"w2")+" } or");
		result += appendLine("{ all w2: Latter[w,b2] | "+replace_w(R,"w2")+" and (all fw: Between[w,w2] | ");
		result += appendLine("not "+replace_w(R,"fw")+") implies (all fw: Between[w,w2] | "+replace_w(P,"fw")+") }}");
				
		return result;
	}
 	
	/**
	 * context C never P between (last) Q and R.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param Q - Q(w, self)
	 * @param R - R(w, self)
	 * @param isLastQ  
	 * @return
	 */
	public static String neverBetweenAnd (String C, String P, String Q, String R, boolean isLastQ)
	{
		String result = new String();
		
		result += appendLine("all b: Branch | all w: World[b] | all self: "+C+" | ");
		result += appendLine(Q+" and not "+R+" ");		
		
		if (isLastQ) result += appendLine("and (all lw: Latter[w] | not "+replace_w(Q,"lw")+") implies ");
		
		result += appendLine("{ all b2: Branch[w] | ");
		result += appendLine("{ all w2: Latter[w,b2] | not "+replace_w(P,"w2")+" } or ");
		result += appendLine("{ all w2: Latter[w,b2] | not "+replace_w(R,"w2")+" } or");
		result += appendLine("{ all w2: Latter[w,b2] | "+replace_w(R,"w2")+" and (all fw: Between[w,w2] | ");
		result += appendLine("not "+replace_w(R,"fw")+") implies (all fw: Between[w,w2] | not "+replace_w(P,"fw")+") }}");
		
		return result;
	}
		
	/**
	 * context C always P after (last) Q unless R.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param Q - Q(w, self)
	 * @param R - R(w, self)
	 * @param isLastQ
	 * @return
	 */
	public static String alwaysAfterUnless (String C, String P, String Q, String R, boolean isLastQ)
	{
		String result = new String();
		
		result += appendLine("all b: Branch | all w: World[b] | all self: "+C+" | ");
		result += appendLine(Q+" and not "+R+" ");		
		
		if (isLastQ) result += appendLine("and (all lw: Latter[w] | not "+replace_w(Q,"lw")+") implies ");
		
		result += appendLine("{ all b2: Branch[w] | ");
		result += appendLine("{ all w2: Latter[w,b2] | "+replace_w(P,"w2")+" } or ");
		result += appendLine("{ all w2: Latter[w,b2] | "+replace_w(R,"w2")+" and (all fw: Between[w,w2] | ");
		result += appendLine("not "+replace_w(R,"fw")+") implies (all fw: Between[w,w2] | "+replace_w(P,"fw")+") }}");
		
		return result;
	}	
	
	/**
	 * context C never P after (last) Q unless R.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param Q - Q(w, self)
	 * @param R - R(w, self)
	 * @param isLastQ
	 * @return
	 */
	public static String neverAfterUnless (String C, String P, String Q, String R, boolean isLastQ)
	{
		String result = new String();
		
		result += appendLine("all b: Branch | all w: World[b] | all self: "+C+" | ");
		result += appendLine(Q+" and not "+R+" ");		
		
		if (isLastQ) result += appendLine("and (all lw: Latter[w] | not "+replace_w(Q,"lw")+") implies ");
		
		result += appendLine("{ all b2: Branch[w] | ");
		result += appendLine("{ all w2: Latter[w,b2] | not "+replace_w(P,"w2")+" } or ");
		result += appendLine("{ all w2: Latter[w,b2] | "+replace_w(R,"w2")+" and (all fw: Between[w,w2] | ");
		result += appendLine("not "+replace_w(R,"fw")+") implies (all fw: Between[w,w2] | not "+replace_w(P,"fw")+") }}");
		
		return result;
	}	
	
	/**
	 * context C eventually P globally.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @return
	 */
	public static String eventuallyGlobally (String C, String P)
	{
		return
		appendLine("all b: Branch | some w: World[b] | all self: "+C+" | ") +
		appendLine("{ "+P+"}");
	}
	
	/**
	 * context C eventually P before R.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param R - R(w, self)
	 * @return
	 */
	public static String eventuallyBefore (String C, String P, String R)
	{
		return
		appendLine("not (some b: Branch | all w: World[b] | all self: "+C+" | ") + 
		appendLine(R+" implies (all fw: Former[w] | not "+replace_w(P,"fw")+") ") +
		appendLine("or (all fw:	Former[w] | "+replace_w(R,"fw")+"))");		
	}
	
	/**
	 * context C eventually P after Q.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param Q - Q(w, self)
	 * @return
	 */
	public static String eventuallyAfter (String C, String P, String Q)
	{
		return
		appendLine("(all b: Branch | all w: World[b] | all self: "+C+" | not "+Q+") ") +
		appendLine("or (all b: Branch | all w: World[b] | all self: "+C+" | ") +
		appendLine(Q+" and (all b2: Branch[w] | some w2: Between[w,b2] | "+replace_w(P,"w2")+") ") +
		appendLine("implies (all fw: Former[w] | not "+replace_w(Q,"fw")+"))");
	}
	
	/**
	 * context C eventually P between (last) Q and R.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param Q - Q(w, self)
	 * @param R - R(w, self)
	 * @param isLastQ
	 * @return
	 */
	public static String eventuallyBetweenAnd (String C, String P, String Q, String R, boolean isLastQ)
	{
		String result = new String();
		
		result += appendLine("all b: Branch | all w: World[b] | all self: "+C+" | "); 
		result += appendLine(Q+" and not "+R+" ");
		
		if (isLastQ) result += appendLine("and (all lw: Latter[w] | not "+replace_w(Q,"lw")+") ");
		
		result += appendLine("implies not (some b2: Branch[w] | all w2: Between[w,b2] | "+replace_w(R,"w2")+" ");
		result += appendLine("implies (all fw: Between[w,w2] | "+replace_w(R,"fw")+") ");
		result += appendLine("or (all fw: Between[w,w2] | not "+replace_w(P,"fw")+"))");
				
		return result;
	}
	
	/**
	 * context C eventually P after (last) Q unless R.
	 * 
	 * @param C - Context(w)
	 * @param P - P(w, self)
	 * @param Q - Q(w, self)
	 * @param R - R(w, self)
	 * @param isLastQ
	 * @return
	 */
	public static String eventuallyAfterUnless (String C, String P, String Q, String R, boolean isLastQ)
	{
		String result = new String();
		
		result += appendLine("all b: Branch | all w: World[b] | all self: "+C+" | ");
		result += appendLine(Q+" and not "+R+" ");
		
		if (isLastQ) result += appendLine("and (all lw: Latter[w] | not "+replace_w(Q,"lw")+") ");
				
		result += appendLine("implies (all b2: Branch[w] | all w2: Between[w,b2] | "+replace_w(P,"w2"));
		result += appendLine("and not "+replace_w(R,"w2")+" implies (all fw: Between[w,w2] | not "+replace_w(R,"fw")+"))");
		
		return result;
	}
}
