package com.cluster.grammarloader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.cluster.tokens.TokenDefinition;

public class GrammarLoader implements ILoader{
	
	public static final String TOKEN_DEFINTION_DELIMITER = ":=";
	public final List<TokenDefinition> TOKEN_DEFINITIONS_LIST;
	public final LinkedHashMap<String, String> TOKEN_DEFINITIONS_MAP;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrammarLoader gl = new GrammarLoader();
		LinkedHashMap<String, String> map = gl.getTokenDefinitions();
		for(String key : map.keySet()){
			System.out.println(key+" "+map.get(key));
		}
	}
	
	public GrammarLoader(){
		this.TOKEN_DEFINITIONS_LIST = loadGrammar();
		this.TOKEN_DEFINITIONS_MAP = populateTokenDefinitions();
	}

	/*Implicitly assumes that the grammar file is
	 * present in main project directory. Also assumes 
	 * that the name of the grammar file is TokenDef.clg
	 * */
	private List<TokenDefinition> loadGrammar() {
		// TODO Auto-generated method stub
		List<String> grammarlines = new ArrayList<String>();
		String grammarfileName = "TokenDef.clg";
		ArrayList<TokenDefinition> tokenDefinitionList = 
				new ArrayList<TokenDefinition>();
		try
	    { 
			grammarlines = 
	       Files.readAllLines(Paths.get(grammarfileName), StandardCharsets.UTF_8); 
	    } 
	    catch (IOException e) 
	    { 
	      // do something 
	      e.printStackTrace();
	      return tokenDefinitionList;
	    } 
		
		for(String line : grammarlines){
			TokenDefinition tokenDefinition = processGrammarLine(line);
			tokenDefinitionList.add(tokenDefinition);
		}
		
		return tokenDefinitionList;
	}

	private TokenDefinition processGrammarLine(String line) {
		// TODO Auto-generated method stub
		TokenDefinition tokenDefinition = null;
		if(!line.contains(GrammarLoader.TOKEN_DEFINTION_DELIMITER)){
			return tokenDefinition;
		}
		
		String[] splitGrammarLine = line.split(GrammarLoader.TOKEN_DEFINTION_DELIMITER);
		
		if(splitGrammarLine.length>2){
			return tokenDefinition;
		}
		
		if(splitGrammarLine[0].equals("") || splitGrammarLine[1].equals("")){
			return tokenDefinition;
		}else{
			tokenDefinition = 
					new TokenDefinition(splitGrammarLine[0], splitGrammarLine[1]);
			return tokenDefinition;
		}
	}

	private LinkedHashMap<String, String> populateTokenDefinitions() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		if(this.TOKEN_DEFINITIONS_LIST!=null){
			for(TokenDefinition tokenDefinition : this.TOKEN_DEFINITIONS_LIST){
				if(map.containsKey(tokenDefinition.getTokenType())){
					//throw an error here ... this shouldn't happen
				}else{
					map.put(tokenDefinition.getTokenType(), 
							tokenDefinition.getRegexDefinitionForTokenType());
				}
			}
		}
		return map;
	}
	
	@Override
	public LinkedHashMap<String, String> getTokenDefinitions(){
		return this.TOKEN_DEFINITIONS_MAP;
	}

}
