package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import br.ufes.inf.nemo.ontouml2alloy.scenarios.CustomQuantification.Mode;


public class StoryScenario extends Scenario{
	
	enum Type {LINEAR, FUTURES, COUNTER, UNDEF}
	private Type type;
	
	Comparator comparator;
	int numberOfWorlds;
	
	enum Limit {UPPER, LOWER, UNDEF}
	private Limit limit;
	int depth;
	CustomQuantification quant;
	
	public StoryScenario (Type type, int numberOfWorlds, Operator op, int depth, Limit limit){
		
		setType(type);
		
		comparator = new Comparator(op);
		setComparator(op);
		setNumberOfWorlds(numberOfWorlds);
		
		
		setStoryDepth(depth);
		this.limit=limit;
		depth=checkStoryDepth(depth);
		quant = new CustomQuantification(getQuantificationMode());
		quant.addQuantificationData("w", "World", getAdjustedDepth());
		
	}
	
	public void setLimit(Limit limit) {
		this.limit=limit;
		depth=checkStoryDepth(depth);
		quant.mode = getQuantificationMode();
		
	}

	public void setComparator(Operator op) {
		comparator.op = op;
		numberOfWorlds = checkNumberOfWorlds(numberOfWorlds);
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Mode getQuantificationMode(){
		if(limit==Limit.UPPER)
			return Mode.NO;
		if(limit==Limit.LOWER)
			return Mode.SOME;
		return null;
	}
	
	public int getNumberOfWorlds() {
		return numberOfWorlds;
	}
	
	public void setNumberOfWorlds(int numberOfWorlds) {
		this.numberOfWorlds = checkNumberOfWorlds(numberOfWorlds);
	}
	
	public int getStoryDepth() {
		return depth;
	}
	public void setStoryDepth(int storyDepth) {
		this.depth = checkStoryDepth(storyDepth);
	}

	public int checkNumberOfWorlds(int value){
		return value;
//		switch (type) {
//		case COUNTER:
//			
//			if (comparator.op==Operator.EQUAL || comparator.op==Operator.GREATER_EQ){
//				if(value<4)
//					return 4;
//				return value;
//			}
//			
//			if (comparator.op==Operator.GREATER){
//				if(value<3)
//					return value;
//				return 4;
//			}
//			
//			if (comparator.op==Operator.L || comparator.op==Operator.GREATER_EQ){
//				if(value>4)
//					return value;
//				return 4;
//			}
//			
//		case FUTURES:
//			if (value>3)
//				return value;
//			return 3;
//		case LINEAR:
//		case UNDEF:
//		default:
//			if (value>1)
//				return value;
//			return 1;
//		}
				
	}
	
	//TODO: fix according to operator
	public int checkStoryDepth(int value) {
		return value;
//		switch (type) {
//		case COUNTER:
//			if (value>2)
//				return value;
//			return 2;
//		case FUTURES:
//			return 1;
//		case LINEAR:
//		case UNDEF:
//		default:
//			if (value>1)
//				return value;
//			return 1;
//		}
	}

	@Override
	public String getString() {
		switch (type) {
			case COUNTER:
				return "things may have taken a different outcome in the past";
			case FUTURES:
				return "different outcomes for a given situation";
			case LINEAR:
				return "linear story";
			default:
				break;
		}
		
		return "";
	}

	@Override
	public String getAlloy() {
		String alloy = "";
		
		if(type==Type.COUNTER || type==Type.FUTURES || type==Type.LINEAR)
			alloy+=getStructureExpression()+"\n\t";
		
		alloy+=getSizeExpression();
		
		if(limit!=Limit.UNDEF)
			alloy+="\n\t"+getDepthExpression();
		
		return alloy;
	}
	
	private String getDepthExpression() {
		return quant.addQuantification(getRestriction());
	}
	
	private String getRestriction() {
		String expr="";
				
		for (int i = 1; i < getAdjustedDepth(); i++) {
			String var1 = quant.getVariable(0,i);
			String var2 = quant.getVariable(0,i+1);
			
			expr += var2+" in "+var1+".next";
			
			if(i<getAdjustedDepth()-1)
				expr+=" and ";
		}
		
		return expr;
	}
	
	public int getAdjustedDepth(){
		if(limit==Limit.UPPER)
			return depth+1;
		return depth;
	}

	private String getSizeExpression() {
		return comparator.getExpression("#World", Integer.toString(numberOfWorlds));
	}

	private String getStructureExpression() {
		switch (type) {
		case COUNTER:
			return "some w1,w2:World | w1!=w2 && next.w1=next.w2 && (some w1.next or some w2.next)";
		case FUTURES:
			return "one w:World | no next.w && all w2:World | w!=w2 implies w2 in w.next";
		case LINEAR:
			return "one w1,w2:World | no w1.next and no next.w2";
		case UNDEF:
		default:
			return "";
		}
	}

	@Override
	public String getScenarioName() {
		return "Story";
	}
}
