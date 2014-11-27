package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum ExtensionOperator {
	INC{
		@Override
		public String toString() {
	        return "included";
	    }

		@Override
		public String getOperator() {
			return "in";
		}
	}, 
	NOT_INC{
		@Override
		public String toString() {
	        return "not included";
	    }

		@Override
		public String getOperator() {
			return "not in";
		}
	}, 
	DISJ{
		@Override
		public String toString() {
	        return "disjoint";
	    }

		@Override
		public String getOperator() {
			return "disj";
		}
		
		@Override
		public String getExpression(String leftExpression, String rightExpression){	
			return getOperator()+"["+leftExpression+", "+rightExpression+"]";
		}
	}, 
	INTER{
		@Override
		public String toString() {
	        return "intersection";
	    }
		
		@Override
		public String getOperator() {
			return "&";
		}
		
		@Override
		public String getExpression(String leftExpression, String rightExpression){	
			return "some ("+leftExpression+" "+getOperator()+" "+rightExpression+")";
		}

		
	}, 
	EQUAL{
		@Override
		public String toString() {
	        return "equal";
	    }

		@Override
		public String getOperator() {
			return "=";
		}
	}, 
	DIF{
		@Override
		public String toString() {
	        return "different";
	    }

		@Override
		public String getOperator() {
			return "!=";
		}
	};
	
	public String getExpression(String leftExpression, String rightExpression){	
		return leftExpression+" "+getOperator()+" "+rightExpression;
	}
	
	public abstract String getOperator();
}