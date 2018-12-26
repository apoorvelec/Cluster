package com.cluster.lexer;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.cluster.grammar.exceptions.IncorrectGrammarFileNameException;
import com.cluster.grammarloader.GrammarLoader;
import com.cluster.grammarloader.TokenDefinitionLoader;
import com.cluster.tokens.Token;
import com.owl.main.RegexMatcher;

public class LongestMatchLexer implements ILexer {

	private String _programInput;
	private GrammarLoader _loader;
	LinkedHashMap<String, RegexMatcher> _map;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LongestMatchLexer lexer = new LongestMatchLexer("if   else ab else ifif");
		
		Token token = lexer.getNextToken();
		while(token!=null){
			System.out.println(token);
			token = lexer.getNextToken();
		}
		
	}
	
	public LongestMatchLexer(String input){
		this._programInput = input;
		try {
			this._loader = new GrammarLoader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, String> tokenDefinitionsMap = 
				this._loader.getGrammar().getTokenDefinitionsMap();
		this._map = new LinkedHashMap<String, RegexMatcher>();
		
		for(String tokenType : tokenDefinitionsMap.keySet()){
			String regex = tokenDefinitionsMap.get(tokenType);
			this._map.put(tokenType, 
					new RegexMatcher(regex));
		}
	}
	
	public LongestMatchLexer(String input, String grammarFilePath){
		this._programInput = input;
		try {
			this._loader = new GrammarLoader(grammarFilePath);
		} catch (IOException | IncorrectGrammarFileNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<String, String> tokenDefinitionsMap = 
				this._loader.getGrammar().getTokenDefinitionsMap();
		this._map = new LinkedHashMap<String, RegexMatcher>();
		
		for(String tokenType : tokenDefinitionsMap.keySet()){
			String regex = tokenDefinitionsMap.get(tokenType);
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
	
	public GrammarLoader getGrammarLoader(){
		return this._loader;
	}
	
}
