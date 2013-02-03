package br.ufes.inf.nemo.oled.util;

public class OperationResult {

	public enum ResultType
	{
		SUCESS,
		WARNING,
		ERROR
	}
	
	private Object[] data;
	private ResultType resultType;
	private String description;
	
	public OperationResult(ResultType resultType, String description, Object[] data)
	{
		this.data = data;
		this.resultType = resultType;
		this.description = description;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString()
	{
		String ans = "";
		if(resultType != ResultType.SUCESS)
			ans += resultType.name() + ": ";
		
		ans += description;
		return ans;
	}
}
