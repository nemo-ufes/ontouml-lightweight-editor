package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public enum SegmentType {
	
	POPULATION{
		public String toString() {
	        return "Population";
	    }
	},
	OBJECT{
		public String toString() {
	        return "Object Population";
	    }
	},  
	PROPERTY{
		public String toString() {
	        return "Property Population";
	    }
	},  
	STEREOTYPE{
		public String toString() {
	        return "Stereotype";
	    }
	},  
	CLASS{
		public String toString() {
	        return "Class";
	    }
	},
	ASSOCIATION{
		public String toString() {
	        return "Association";
	    }
	};  
	
}