package com.cluster.lexer;

import java.util.LinkedHashMap;

import com.cluster.grammarloader.GrammarLoader;
import com.cluster.tokens.Token;
import com.owl.main.RegexMatcher;

public class LongestMatchLexer implements ILexer {

	private String _programInput;
	private GrammarLoader _loader;
	LinkedHashMap<String, RegexMatcher> _map;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LongestMatchLexer lexer = new LongestMatchLexer("if   else ab else if");
		
		Token token = lexer.getNextToken();
		System.out.println(token);
		token = lexer.getNextToken();
		System.out.println(token);
		token = lexer.getNextToken();
		System.out.println(token);
		token = lexer.getNextToken();
		System.out.println(token);
		token = lexer.getNextToken();
		System.out.println(token);
		token = lexer.getNextToken();
		System.out.println(token);
		token = lexer.getNextToken();
		System.out.println(token);
		token = lexer.getNextToken();
		System.out.println(token);
		token = lexer.getNextToken();
		System.out.println(token);
		
	}
	
	public LongestMatchLexer(String input){
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
		//Always start lexing from the start of the _programInput string
		//Once you find the match of a token, remove it from the _programInput string
		
		Token nextToken = null;
		
		if(this._programInput == null || this._programInput.equals("")){
			return nextToken;
		}
		
		int index = 0;
		nextToken = returnPossibleTokenMatch(_programInput, index);
		while(nextLongerTokenIsPossible(_programInput, index)){
			index++;
			Token potentialTokenString = returnPossibleTokenMatch(_programInput, index);
			
			
			if(potentialTokenString!=null){
				nextToken = potentialTokenString;
			}
		}
		
		if(nextToken!=null){
			this._programInput = 
					this._programInput.substring(nextToken.getTokenValue().length());
		}
		
		
		return nextToken;
	}

	private Token returnPossibleTokenMatch(String programInput, int index) {
		// TODO Auto-generated method stub
		Token token = null;
		
		String programInputSubstring = programInput.substring(0, index+1);
		
		for(String tokenType : this._map.keySet()){
			RegexMatcher tokenTypeMatcher = this._map.get(tokenType);
			if(tokenTypeMatcher.matchesString(programInputSubstring)){
				token = new Token(tokenType, programInputSubstring);
				break;
			}
		}
		
		return token;
	}

	private boolean nextLongerTokenIsPossible(String programInput, int index) {
		// TODO Auto-generated method stub
		
		if(index == programInput.length()-1){
			return false;
		}
		
		String programInputSubstring = programInput.substring(0, index+1);
		
		for(RegexMatcher matcher : this._map.values()){
			matcher.matchesString(programInputSubstring);
			if(!matcher.stringMatchHitDeadNode()){
				return true;
			}
		}
		
		return false;
	}

	

}