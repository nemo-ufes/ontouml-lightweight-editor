package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum ComparisonType {
	SIZE {
		@Override
		public String toString() {
			return "Size";
		}
	}, 
	EXT{
		@Override
		public String toString() {
			return "Extension";
		}
	}
}