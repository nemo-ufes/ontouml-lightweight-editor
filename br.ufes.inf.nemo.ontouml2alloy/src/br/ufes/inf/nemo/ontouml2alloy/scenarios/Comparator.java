package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public class Comparator {
	BinaryOperator op;
	
	public Comparator(BinaryOperator op) {
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


