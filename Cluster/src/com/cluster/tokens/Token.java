package com.cluster.tokens;

public class Token {
	
	private String _tokenType;
	private String _tokenValue;
	
	public Token(String tokenType, String tokenValue){
		this._tokenType = tokenType;
		this._tokenValue = tokenValue;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//Getters and Setters
	
	public String getTokenType(){
		return this._tokenType;
	}
	
	public String getTokenValue(){
		return this._tokenValue;
	}
	
	public boolean setTokenType(String tokenType){
		if(this._tokenType!=null){
			if(this._tokenType.equals(tokenType)){
				return false;
			}else{
				this._tokenType = tokenType;
				return true;
			}
		}else{
			if(tokenType == null){
				return false;
			}else{
				this._tokenType = tokenType;
				return true;
			}
		}
	}
	
	public boolean setTokenValue(String tokenValue){
		if(this._tokenValue!=null){
			if(this._tokenValue.equals(tokenValue)){
				return false;
			}else{
				this._tokenValue = tokenValue;
				return true;
			}
		}else{
			if(tokenValue == null){
				return false;
			}else{
				this._tokenValue = tokenValue;
				return true;
			}
		}
	}
	
	@Override
	public String toString(){
		return "{type: "+this._tokenType + ", " + "value: " + this._tokenValue + "}";
	}

}
