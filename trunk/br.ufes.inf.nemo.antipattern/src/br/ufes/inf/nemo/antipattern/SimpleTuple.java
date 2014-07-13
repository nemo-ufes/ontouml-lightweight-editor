package br.ufes.inf.nemo.antipattern;


public class SimpleTuple<T,S>{

	T first;
	S second;
	
	public SimpleTuple(T first, S second) {
		this.first = first;
		this.second = second;
	}
	
	public SimpleTuple() {
		first = null;
		second = null;
	}

	public void put(T first, S second) {
		this.first = first;
		this.second = second;
	}

	public void setFirst(T first) {
		this.first = first;
	}
	
	public T getFirst() {
		return first;
	}
	
	public void setSecond(S second) {
		this.second = second;
	}
	
	public S getSecond() {
		return second;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SimpleTuple){
			SimpleTuple<?,?> tuple = (SimpleTuple<?, ?>)obj;
			return tuple.first.equals(first) && tuple.second.equals(second);
		}
		return false;
	}
	
}
