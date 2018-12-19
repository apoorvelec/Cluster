package com.cluster.grammarloader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.cluster.tokens.TokenDefinition;

public class TokenDefinitionLoader{
	
	public static final String TOKEN_DEFINTION_DELIMITER = ":=";
	public final List<TokenDefinition> TOKEN_DEFINITIONS_LIST;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public TokenDefinitionLoader(List<String> tokenGrammarLines){
		this.TOKEN_DEFINITIONS_LIST = loadTokenDefinitions(tokenGrammarLines);
	}

	/*
	 * 
	 */
	private List<TokenDefinition> loadTokenDefinitions(List<String> tokenGrammarLines) {
		// TODO Auto-generated method stub
		ArrayList<TokenDefinition> tokenDefinitionList = 
				new ArrayList<TokenDefinition>();
		for(String line : tokenGrammarLines){
			TokenDefinition tokenDefinition = processGrammarLine(line);
			if(tokenDefinition!=null){
				tokenDefinitionList.add(tokenDefinition);
			}
		}
		
		return tokenDefinitionList;
	}

	private TokenDefinition processGrammarLine(String line) {
		// TODO Auto-generated method stub
		TokenDefinition tokenDefinition = null;
		if(!line.contains(TokenDefinitionLoader.TOKEN_DEFINTION_DELIMITER)){
			return tokenDefinition;
		}
		
		String[] splitGrammarLine = line.split(TokenDefinitionLoader.TOKEN_DEFINTION_DELIMITER);
		
		if(splitGrammarLine.length>2){
			return tokenDefinition;
		}
		splitGrammarLine[0] = splitGrammarLine[0].trim();
		splitGrammarLine[1] = splitGrammarLine[1].trim();
		if(splitGrammarLine[0].equals("") || splitGrammarLine[1].equals("")){
			return tokenDefinition;
		}else{
			tokenDefinition = 
					new TokenDefinition(splitGrammarLine[0], splitGrammarLine[1]);
			return tokenDefinition;
		}
	}

}
