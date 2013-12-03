package br.ufes.inf.nemo.antipattern;

public class AntipatternInfo {
	public final String name;
	public final String acronym;
	public final String description;
	public final String oclQuery;
	
	public AntipatternInfo(String name, String acronym, String description, String oclQuery) {
		this.name = name;
		this.acronym = acronym;
		this.description = description;
		this.oclQuery = oclQuery;	
	}
	
	public String getName() {
		return this.name;
	}
	public String getAcronym() {
		return this.acronym;
	}
	public String getDescription() {
		return this.description;
	}
	public String getOclQuery() {
		return this.oclQuery;
	}
}
