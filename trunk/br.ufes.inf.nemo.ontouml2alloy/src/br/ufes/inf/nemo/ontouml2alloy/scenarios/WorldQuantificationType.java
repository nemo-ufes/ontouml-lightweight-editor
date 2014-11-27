package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum WorldQuantificationType {
	STORY{
		@Override
		public String toString() {
	        return "Whole Story";
	    }
	}, 
	EVERY{
		@Override
		public String toString() {
	        return "Every World";
	    }
	}, 
	NO{
		@Override
		public String toString() {
	        return "No World";
	    }
	}, 
	SOME{
		@Override
		public String toString() {
	        return "Some World";
	    }
	}, 
	ATLEAST{
		@Override
		public String toString() {
	        return "At Least <n> Worlds";
	    }
	}, 
	ATMOST{
		@Override
		public String toString() {
	        return "At Most <n> Worlds";
	    }
	}, 
	EXACTLY{
		@Override
		public String toString() {
	        return "Exactly <n> Worlds";
	    }
	}
}