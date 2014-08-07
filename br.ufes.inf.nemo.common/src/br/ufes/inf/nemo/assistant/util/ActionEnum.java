package br.ufes.inf.nemo.assistant.util;

public enum ActionEnum {
	EXIST_SOME_ULTIMATE_SORTAL_OR_SUBKIND,
	EXIST_SOME_CATEGORY_OR_MIXIN,
	EXIST_SOME_SUBSTANCE_SORTAL;
	
	@Override
	  public String toString() {
	    switch(this) {
	      case EXIST_SOME_ULTIMATE_SORTAL_OR_SUBKIND: return "EXIST_SOME_ULTIMATE_SORTAL_OR_SUBKIND";
	      case EXIST_SOME_SUBSTANCE_SORTAL: return "EXIST_SOME_SUBSTANCE_SORTAL";
	      case EXIST_SOME_CATEGORY_OR_MIXIN: return "EXIST_SOME_CATEGORY_OR_MIXIN";
	      default: throw new IllegalArgumentException();
	    }
	  }
}
