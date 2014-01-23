package br.ufes.inf.nemo.assistant.util;

public enum ActionEnum {
	EXIST_SOME_ULTIMATE_SORTAL_OR_SUBKIND,
	CONNECT_LAST_CLASSES,
	EXIST_SOME_CATEGORY_OR_MIXIN,
	EXIST_SOME_ULTIMATE_SORTAL;
	
	@Override
	  public String toString() {
	    switch(this) {
	      case EXIST_SOME_ULTIMATE_SORTAL_OR_SUBKIND: return "EXIST_SOME_ULTIMATE_SORTAL_OR_SUBKIND";
	      case EXIST_SOME_ULTIMATE_SORTAL: return "EXIST_SOME_ULTIMATE_SORTAL";
	      case CONNECT_LAST_CLASSES: return "CONNECT_LAST_CLASSES";
	      case EXIST_SOME_CATEGORY_OR_MIXIN: return "EXIST_SOME_CATEGORY_OR_MIXIN";
	      default: throw new IllegalArgumentException();
	    }
	  }
}
