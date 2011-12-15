package br.inf.ufes.nemo.transformation.ontouml2alloy.v3.base;

public class AlloySignature extends AlloyBaseElement {

	private String name;
	private boolean isAbstract;
	
	public AlloySignature(String name, boolean isAbstract) {
		super();
		this.name = name;
		this.isAbstract = isAbstract;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(isAbstract ? "abstract " : "");
		sb.append("sig " + name + " ");
		sb.append("{}\n");
		
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

}
