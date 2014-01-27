package br.ufes.inf.nemo.instancevisualizer.graph;

public class DataType {

	private String dataTypeName;
	private String dataTypeValue;
	
	public DataType(String dataTypeName, String dataTypeValue) {
		this.dataTypeName = dataTypeName;
		this.dataTypeValue = dataTypeValue;
	}
	
	public String getName() {
		return dataTypeName;
	}
	
	public String getValue() {
		return dataTypeValue;
	}
}
