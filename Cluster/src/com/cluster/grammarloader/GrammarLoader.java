package com.cluster.grammarloader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.cluster.grammar.Grammar;
import com.cluster.grammar.exceptions.IncorrectGrammarFileNameException;

public class GrammarLoader implements ILoader{

	private List<String> _tokenDefinitionSection;
	private List<String> _contextFreeGrammarDefinitionSection;
	private List<String> _grammarAsListOfStrings;
	private String _grammarFilePath;
	
	private static String _TOKEN_DEFINITION_SECTION_START_TAG = "#{";
	private static String _TOKEN_DEFINITION_SECTION_END_TAG = "}#";
	private static String _CFG_DEFINITION_SECTION_START_TAG = "${";
	private static String _CFG_DEFINITION_SECTION_END_TAG = "}$";
	
	private TokenDefinitionLoader _tokenSectionLoader;
	private CFGLoader _cfgSectionLoader;
	
	private Grammar _grammarObject;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrammarLoader loader = null;
		try {
			loader = new GrammarLoader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(String s : loader._grammarAsListOfStrings){
//			System.out.println(s);
//		}
//		
//		System.out.println("-------------------------------------");
//		
//		for(String s : loader._tokenDefinitionSection){
//			System.out.println(s);
//		}
//		
//		System.out.println("-------------------------------------");
//		
//		for(String s : loader._contextFreeGrammarDefinitionSection){
//			System.out.println(s);
//		}
		
		Grammar grammar = loader.getGrammar();
		System.out.println("Non-terminals:");
		for(String s : grammar.getAllNonTerminals()){
			System.out.println(s);
		}
		
		System.out.println("Terminals:");
		for(String s : grammar.getAllTerminals()){
			System.out.println(s);
		}
		
		System.out.println("First Sets:");
		Map<String, Set<String>> firstSets = grammar.computeFirstSets();
		for(String s : firstSets.keySet()){
			System.out.print(s + "->");
			System.out.println(firstSets.get(s));
		}
		
		System.out.println("-------------------------------------------");
		
		System.out.println("Follow Sets:");
		Map<String, Set<String>> followSets = grammar.computeFollowSets();
		for(String s : followSets.keySet()){
			System.out.print(s + "->");
			System.out.println(followSets.get(s));
		}
		
		System.out.println("------------------------------------------");
		
		System.out.println("LL1 table:");
		
		System.out.println(grammar.getParseTable());
	}
	
	public GrammarLoader() throws IOException{
		this._grammarFilePath = "Grammar.clg";
		try {
			this._grammarAsListOfStrings = loadGrammarFromFile();
		} catch (IncorrectGrammarFileNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this._tokenDefinitionSection = loadTokenDefinitionSection();
		this._contextFreeGrammarDefinitionSection = loadCFGSection();
		
		this._tokenSectionLoader 
		= new TokenDefinitionLoader(this._tokenDefinitionSection);
		
		this._cfgSectionLoader 
		= new CFGLoader(this._contextFreeGrammarDefinitionSection);
		
		this._grammarObject = populateGrammar();
	}
	
	public GrammarLoader(String grammarFilePath) throws IOException, IncorrectGrammarFileNameException{
		this._grammarFilePath = grammarFilePath;
		this._grammarAsListOfStrings = loadGrammarFromFile();
		this._tokenDefinitionSection = loadTokenDefinitionSection();
		this._contextFreeGrammarDefinitionSection = loadCFGSection();
		
		this._tokenSectionLoader 
		= new TokenDefinitionLoader(this._tokenDefinitionSection);
		
		this._cfgSectionLoader 
		= new CFGLoader(this._contextFreeGrammarDefinitionSection);
		
		this._grammarObject = populateGrammar();
	}
	
	private Grammar populateGrammar() {
		// TODO Auto-generated method stub
		return new Grammar(this._tokenSectionLoader.TOKEN_DEFINITIONS_LIST, 
				this._cfgSectionLoader.rootNodeForCFG);
	}

	private List<String> loadCFGSection() {
		// TODO Auto-generated method stub
		List<String> cfgDefinitions = new ArrayList<String>();
		
		boolean insideCFGDefinitionSection = false;
		for(String line : this._grammarAsListOfStrings){
			if(doesLineContainTag(line, _CFG_DEFINITION_SECTION_START_TAG)){ // line starts with ${ as the first non-whitespace character
				insideCFGDefinitionSection = true;
				continue;
			}
			if(doesLineContainTag(line, _CFG_DEFINITION_SECTION_END_TAG)){ // line starts with }$ as the first non-whitespace character
				insideCFGDefinitionSection = false;
				break;
			}
			if(insideCFGDefinitionSection){
				cfgDefinitions.add(line);
			}
		}
		
		return cfgDefinitions;
	}

	private List<String> loadTokenDefinitionSection() {
		// TODO Auto-generated method stub
		List<String> tokenDefinitions = new ArrayList<String>();
		
		boolean insideTokenDefinitionSection = false;
		for(String line : this._grammarAsListOfStrings){
			if(doesLineContainTag(line, _TOKEN_DEFINITION_SECTION_START_TAG)){ // line starts with #{ as the first non-whitespace character
				insideTokenDefinitionSection = true;
				continue;
			}
			if(doesLineContainTag(line, _TOKEN_DEFINITION_SECTION_END_TAG)){ // line starts with }# as the first non-whitespace character
				insideTokenDefinitionSection = false;
				break;
			}
			if(insideTokenDefinitionSection){
				tokenDefinitions.add(line);
			}
		}
		
		return tokenDefinitions;
	}

	private boolean doesLineContainTag(String line, String tag) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		char[] lineCharArray = line.toCharArray();
		
		// Initialize queue
		char[] tagArray 
		= tag.toCharArray();
		ArrayDeque<Character> queue = new ArrayDeque<Character>();
		for(char c : tagArray){
			queue.offer(c);
		}
		
		for(char c : lineCharArray){
			if(c == ' '){
				continue;
			}
			
			char topOfQueueElement = queue.poll();
			
			if(c != topOfQueueElement){
				result = false;
				return result;
			}
		}
		
		result = queue.size() == 0;
		return result;
	}

	private List<String> loadGrammarFromFile() throws IOException, IncorrectGrammarFileNameException {
		// TODO Auto-generated method stub
		List<String> grammarLines = new ArrayList<String>();
		
		try {
			grammarLines = Files.readAllLines(Paths.get(this._grammarFilePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//check if the grammar file specified is correct or not
			//i.e if the grammar file path doesn't end in .clg,
			//throw an IncorrectGrammarFileNameException
			if(this._grammarFilePath!=null 
					&& !this._grammarFilePath.endsWith(".clg")){
				String message = "Grammar file names must end with a .clg extension!";
				IncorrectGrammarFileNameException exception 
				= new IncorrectGrammarFileNameException(message, e);
				throw exception;
			}
			
			throw e;
		}
		return grammarLines;
	}

	@Override
	public Grammar getGrammar() {
		// TODO Auto-generated method stub
		return this._grammarObject;
	}
}
