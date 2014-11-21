package br.ufes.inf.nemo.ontouml2alloy.scenarios;

enum Operator {EQUAL, LESSER, LESSER_EQ, GREATER, GREATER_EQ, DIF}

public class Comparator {
	Operator op;
	
	public Comparator(Operator op) {
		this.op = op;
	}

	public String getExpression(String leftExpr, String rightExpr){
	
		switch (op) {
			case EQUAL:
				return leftExpr+" = "+rightExpr;
			case DIF:
				return leftExpr+" != "+rightExpr;
			case GREATER:
				return leftExpr+" > "+rightExpr;
			case GREATER_EQ:
				return leftExpr+" >= "+rightExpr;
			case LESSER:
				return leftExpr+" < "+rightExpr;
			case LESSER_EQ:
				return leftExpr+" =< "+rightExpr;
		}
		return "NO OPERATOR DEFINED!";
	}

}


