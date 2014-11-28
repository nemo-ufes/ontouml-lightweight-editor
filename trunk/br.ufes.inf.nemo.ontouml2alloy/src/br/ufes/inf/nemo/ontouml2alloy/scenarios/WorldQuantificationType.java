package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum WorldQuantificationType {
	STORY{
		@Override
		public String toString() {
	        return "Whole Story";
	    }

		@Override
		public boolean isNumeric() {
			return false;
		}
	}, 
	EVERY{
		@Override
		public String toString() {
	        return "Every World";
	    }

		@Override
		public boolean isNumeric() {
			return false;
		}
	}, 
	NO{
		@Override
		public String toString() {
	        return "No World";
	    }

		@Override
		public boolean isNumeric() {
			return false;
		}
	}, 
	SOME{
		@Override
		public String toString() {
	        return "Some World";
	    }

		@Override
		public boolean isNumeric() {
			return false;
		}
	}, 
	ATLEAST{
		@Override
		public String toString() {
	        return "At Least <n> Worlds";
	    }

		@Override
		public boolean isNumeric() {
			return true;
		}
	}, 
	ATMOST{
		@Override
		public String toString() {
	        return "At Most <n> Worlds";
	    }

		@Override
		public boolean isNumeric() {
			return true;
		}
	}, 
	EXACTLY{
		@Override
		public String toString() {
	        return "Exactly <n> Worlds";
	    }

		@Override
		public boolean isNumeric() {
			return true;
		}
	};

	public abstract boolean isNumeric();
}