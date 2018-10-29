package com.cluster.tokens;

public class TokenDefinition {
	
	private String _tokenType;
	private String _regexDefinitionForTokenType;
	
	public TokenDefinition(String tokenType, String regex){
		this._tokenType = tokenType;
		this._regexDefinitionForTokenType = regex;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// Getters and Setters
	
	public String getTokenType(){
		return this._tokenType;
	}
	
	public String getRegexDefinitionForTokenType(){
		return this._regexDefinitionForTokenType;
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
	
	public boolean setRegexDefinitionForTokenType(String regex){
		if(this._regexDefinitionForTokenType!=null){
			if(this._regexDefinitionForTokenType.equals(regex)){
				return false;
			}else{
				this._regexDefinitionForTokenType = regex;
				return true;
			}
		}else{
			if(regex == null){
				return false;
			}else{
				this._regexDefinitionForTokenType = regex;
				return true;
			}
		}
	}

}
