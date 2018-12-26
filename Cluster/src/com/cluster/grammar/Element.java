package com.cluster.grammar;

public class Element {
	
	public enum Action{
		EXPAND,
		SHIFT,
		REDUCE,
		GOTO
	}
	
	public Action Action;
	public Integer StateNumberOrProductionNumber;
	
	public Element(Action action, Integer integer){
		this.Action = action;
		this.StateNumberOrProductionNumber = integer;
	}
	
	public Element(){
		this.Action = null;
		this.StateNumberOrProductionNumber = null;
	}
	
	@Override
	public String toString(){
		return this.Action+","+this.StateNumberOrProductionNumber;
	}
	
	public boolean isEmpty(){
		return this.Action==null && this.StateNumberOrProductionNumber==null;
	}
	
	@Override
	public boolean equals(Object e){
		if(e instanceof Element){
			
			boolean isActionEqual = false;
			if(this.Action == null){
				if(((Element)e).Action == null){
					isActionEqual = true;
				}else{
					isActionEqual = false;
				}
			}else{
				isActionEqual = this.Action.equals(((Element)e).Action);
			}
			
			boolean isNumberFieldEqual = false;
			if(this.StateNumberOrProductionNumber == null){
				if(((Element)e).StateNumberOrProductionNumber == null){
					isNumberFieldEqual = true;
				}else{
					isNumberFieldEqual = false;
				}
			}else{
				isNumberFieldEqual = 
						this.StateNumberOrProductionNumber
						.equals(((Element)e).StateNumberOrProductionNumber);
			}
			
			return isActionEqual && isNumberFieldEqual;
			
		}else{
			return false;
		}
	}
}
