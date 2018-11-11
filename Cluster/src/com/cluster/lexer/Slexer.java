package com.cluster.lexer;

import java.util.LinkedHashMap;

import com.cluster.grammarloader.GrammarLoader;
import com.cluster.tokens.Token;
import com.owl.main.RegexMatcher;

public class Slexer implements ILexer{
	
	private String _programInput;
	private GrammarLoader _loader;
	LinkedHashMap<String, RegexMatcher> _map;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String program = "if  else ab";
		
		Slexer lexer = new Slexer(program);
		
		Token token = lexer.getNextToken();
		System.out.println(token.getTokenValue());
		token = lexer.getNextToken();
		System.out.println(token.getTokenValue());
		token = lexer.getNextToken();
		System.out.println(token.getTokenValue());
		token = lexer.getNextToken();
		System.out.println(token.getTokenValue());
		token = lexer.getNextToken();
		System.out.println(token.getTokenValue());
		token = lexer.getNextToken();
		System.out.println(token.getTokenValue());
		//token = lexer.getNextToken();
		//System.out.println(token.getTokenValue());
	}
	
	public Slexer(String input){
		this._programInput = input;
		this._loader = new GrammarLoader();
		
		this._map = new LinkedHashMap<String, RegexMatcher>();
		
		for(String tokenType : this._loader.TOKEN_DEFINITIONS_MAP.keySet()){
			String regex = this._loader.TOKEN_DEFINITIONS_MAP.get(tokenType);
			this._map.put(tokenType, 
					new RegexMatcher(regex));
		}
	}

	@Override
	public Token getNextToken() {
		// TODO Auto-generated method stub
		String buffer = this._programInput.substring(0, 1);
		int index = 1;
		while(!matchesAnyToken(buffer)){
			index++;
			buffer = this._programInput.substring(0, index);
		}
		this._programInput = this._programInput.substring(index);
		return new Token("NONE", buffer);
	}

	private boolean matchesAnyToken(String buffer) {
		
		// TODO Auto-generated method stub
		for(String tokenType : this._map.keySet()){
			if(this._map.get(tokenType).matchesString(buffer)){
				return true;
			}
		}
		return false;
	}
	
	

}
