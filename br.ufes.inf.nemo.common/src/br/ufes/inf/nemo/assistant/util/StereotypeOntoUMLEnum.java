package br.ufes.inf.nemo.assistant.util;

public enum StereotypeOntoUMLEnum {
	KIND,
	SUBKIND,
	CATEGORY,
	COLLECTIVE,
	QUANTITY,
	ROLE,
	PHASE,
	MIXIN,
	RELATOR,
	ROLEMIXIN;
	
	@Override
	  public String toString() {
	    switch(this) {
	      case KIND: return "Kind";
	      case SUBKIND: return "Subkind";
	      case CATEGORY: return "Category";
	      case COLLECTIVE: return "Collective";
	      case QUANTITY: return "Quantity";
	      case ROLE: return "Role";
	      case PHASE: return "Phase";
	      case MIXIN: return "Mixin";
	      case RELATOR: return "Relator";
	      case ROLEMIXIN: return "Rolemixin";
	      default: throw new IllegalArgumentException();
	    }
	  }
}
