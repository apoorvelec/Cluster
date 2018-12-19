package com.cluster.grammar.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.cluster.grammar.Grammar;
import com.cluster.grammar.exceptions.IncorrectGrammarFileNameException;
import com.cluster.grammarloader.GrammarLoader;

@RunWith(Parameterized.class)
public class GrammarTests {
	
	private static class GrammarTestObject{
		
		//Input parameter
		public String grammarFileName;
		
		//Expected parameters
		public int numberOfNonTerminals;
		public int numberOfTerminals;
		public Set<String> nonTerminalsSet;
		public Set<String> terminalsSet;
		public Map<String, Boolean> nullableSymbolsMap;
		public Map<String, Set<String>> firstSet;
		public Map<String, Set<String>> followSet;
		public int numberOfProductions;
		public String startSymbol;
		
		@Rule
	     public ExpectedException exception= ExpectedException.none();
		
		public GrammarTestObject(String grammarFileName, int numberOfNonTerminals
				, int numberOfTerminals, Set<String> nonTerminalsSet
				, Set<String> terminalsSet, Map<String, Boolean> nullableSymbolsMap
				, Map<String, Set<String>> firstSet, Map<String, Set<String>> followSet
				, int numberOfProductions, String startSymbol, ExpectedException exception){
			this.grammarFileName = grammarFileName;
			this.numberOfNonTerminals = numberOfNonTerminals;
			this.numberOfTerminals = numberOfTerminals;
			this.nonTerminalsSet = nonTerminalsSet;
			this.terminalsSet = terminalsSet;
			this.nullableSymbolsMap = nullableSymbolsMap;
			this.firstSet = firstSet;
			this.followSet = followSet;
			this.numberOfProductions = numberOfProductions;
			this.startSymbol = startSymbol;
			this.exception = exception;
		}
		
		public GrammarTestObject(String grammarFileName, int numberOfNonTerminals
				, int numberOfTerminals, Set<String> nonTerminalsSet
				, Set<String> terminalsSet, Map<String, Boolean> nullableSymbolsMap
				, Map<String, Set<String>> firstSet, Map<String, Set<String>> followSet
				, int numberOfProductions, String startSymbol){
			this.grammarFileName = grammarFileName;
			this.numberOfNonTerminals = numberOfNonTerminals;
			this.numberOfTerminals = numberOfTerminals;
			this.nonTerminalsSet = nonTerminalsSet;
			this.terminalsSet = terminalsSet;
			this.nullableSymbolsMap = nullableSymbolsMap;
			this.firstSet = firstSet;
			this.followSet = followSet;
			this.numberOfProductions = numberOfProductions;
			this.startSymbol = startSymbol;
			this.exception = null;
		}
	}
	
	@Parameters
	public static Collection<GrammarTestObject[]> setupTestGrammarData(){
		List<GrammarTestObject[]> testData = new ArrayList<GrammarTestObject[]>();
		
		/*Grammar 1*/
		String grammarFileName = "grammar1.clg";
		int noOfNonTerminals = 1;
		int noOfTerminals = 4;
		Set<String> setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		Set<String> setOfTerminals = Stream.of("if", "else", "id", "space")
				.collect(Collectors.toCollection(HashSet::new));
		
		Map<String, Boolean> mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("if", false);
		mapOfNullables.put("else", false);
		mapOfNullables.put("id", false);
		mapOfNullables.put("space", false);
		
		HashMap<String, Set<String>> firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("if", Stream.of("if")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("else", Stream.of("else")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("id", Stream.of("id")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("space", Stream.of("space")
				.collect(Collectors.toCollection(HashSet::new)));
		
		HashMap<String, Set<String>> followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		int noOfProductions = 1;
		
		String startSymbol = "S"; 
		GrammarTestObject grammarTestObject1 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest1Array = {grammarTestObject1};
		testData.add(grammarTest1Array);
		/*End Grammar 1*/
		
		/*Grammar 2*/
		grammarFileName = "grammar2.clg";
		noOfNonTerminals = 2;
		noOfTerminals = 4;
		setOfNonTerminals = Stream.of("S", "T")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("if", "else", "id", "space")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("T", true);
		mapOfNullables.put("if", false);
		mapOfNullables.put("else", false);
		mapOfNullables.put("id", false);
		mapOfNullables.put("space", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("T", Stream.of("")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("if", Stream.of("if")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("else", Stream.of("else")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("id", Stream.of("id")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("space", Stream.of("space")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("T", new HashSet<String>());
		
		noOfProductions = 2;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject2 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest2Array = {grammarTestObject2};
		testData.add(grammarTest2Array);
		/*End Grammar 2*/
		
		/*Grammar 3*/
		grammarFileName = "grammar3.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 1;
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("if")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", false);
		mapOfNullables.put("if", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("if")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("if", Stream.of("if")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 1;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject3 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest3Array = {grammarTestObject3};
		testData.add(grammarTest3Array);
		/*End Grammar 3*/
		
		/*Grammar 4*/
		grammarFileName = "grammar4.clg";
		noOfNonTerminals = 0;
		noOfTerminals = 0;
		setOfNonTerminals = new HashSet<String>();
		setOfTerminals = new HashSet<String>();
		
		mapOfNullables = new HashMap<String, Boolean>();
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		
		noOfProductions = 0;
		
		startSymbol = null; 
		GrammarTestObject grammarTestObject4 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest4Array = {grammarTestObject4};
		testData.add(grammarTest4Array);
		/*End Grammar 4*/
		
		/*Grammar 5*/
		grammarFileName = "grammar5.clg";
		noOfNonTerminals = 0;
		noOfTerminals = 1;
		setOfNonTerminals = new HashSet<String>();
		setOfTerminals = Stream.of("token")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("token", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("token", Stream.of("token")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		
		noOfProductions = 0;
		
		startSymbol = null; 
		GrammarTestObject grammarTestObject5 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest5Array = {grammarTestObject5};
		testData.add(grammarTest5Array);
		/*End Grammar 5*/
		
		/*Grammar 6*/
		grammarFileName = "grammar6.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 4;
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token1", "token2", "token3", "token4")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("", "token1", "token2", "token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 4;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject6 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest6Array = {grammarTestObject6};
		testData.add(grammarTest6Array);
		/*End Grammar 6*/
		
		/*Grammar 7*/
		grammarFileName = "grammar7.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 4;
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token1", "token2", "token3", "token4")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("", "token1", "token2", "token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 4;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject7 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest7Array = {grammarTestObject7};
		testData.add(grammarTest7Array);
		/*End Grammar 7*/
		
		/*Grammar 8*/
		grammarFileName = "grammar8.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 4;
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token1", "token2", "token3", "token4")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("", "token1", "token2", "token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 4;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject8 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest8Array = {grammarTestObject8};
		testData.add(grammarTest8Array);
		/*End Grammar 8*/
		
		/*Grammar 9*/
		grammarFileName = "grammar9.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 1;
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("token1", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 1;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject9 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest9Array = {grammarTestObject9};
		testData.add(grammarTest9Array);
		/*End Grammar 9*/
		
		/*Grammar 10*/
		grammarFileName = "grammar10.clg";
		noOfNonTerminals = 4;
		noOfTerminals = 10;
		setOfNonTerminals = Stream.of("S", "E", "T", "F")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("E", false);
		mapOfNullables.put("T", true);
		mapOfNullables.put("F", true);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("", "token0", "token2", "token3", "token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("E", Stream.of("token2", "token3", "token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("T", Stream.of("", "token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("F", Stream.of("", "token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$", "token4")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("E", Stream.of("$", "token1", "token4")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("T", Stream.of("$", "token4")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("F", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 9;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject10 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest10Array = {grammarTestObject10};
		testData.add(grammarTest10Array);
		/*End Grammar 10*/
		
		/*Grammar 11*/
		grammarFileName = "grammar11.clg";
		noOfNonTerminals = 4;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S", "E", "T", "F")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", false);
		mapOfNullables.put("E", false);
		mapOfNullables.put("T", false);
		mapOfNullables.put("F", false);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", new HashSet<String>());
		firstSetsInfo.put("E", new HashSet<String>());
		firstSetsInfo.put("T", new HashSet<String>());
		firstSetsInfo.put("F", new HashSet<String>());
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("E", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("T", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("F", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 4;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject11 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest11Array = {grammarTestObject11};
		testData.add(grammarTest11Array);
		/*End Grammar 11*/
		
		/*Grammar 12*/
		grammarFileName = "grammar12.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 2;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject12 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest12Array = {grammarTestObject12};
		testData.add(grammarTest12Array);
		/*End Grammar 12*/
		
		/*Grammar 13*/
		grammarFileName = "grammar13.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", false);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", new HashSet<String>());
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 1;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject13 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest13Array = {grammarTestObject13};
		testData.add(grammarTest13Array);
		/*End Grammar 13*/
		
		/*Grammar 14*/
		grammarFileName = "grammar14.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("", "token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$","token0")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 2;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject14 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest14Array = {grammarTestObject14};
		testData.add(grammarTest14Array);
		/*End Grammar 14*/
		
		/*Grammar 15*/
		grammarFileName = "grammar15.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", false);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 1;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject15 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest15Array = {grammarTestObject15};
		testData.add(grammarTest15Array);
		/*End Grammar 15*/
		
		/*Grammar 16*/
		grammarFileName = "grammar16.clg";
		noOfNonTerminals = 1;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("", "token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 2;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject16 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest16Array = {grammarTestObject16};
		testData.add(grammarTest16Array);
		/*End Grammar 16*/
		
		/*Grammar 17*/
		grammarFileName = "grammar17.clg";
		noOfNonTerminals = 2;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S", "T")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", false);
		mapOfNullables.put("T", true);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("T", Stream.of("", "token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("T", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 3;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject17 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest17Array = {grammarTestObject17};
		testData.add(grammarTest17Array);
		/*End Grammar 17*/
		
		/*Grammar 18*/
		grammarFileName = "grammar18.clg";
		noOfNonTerminals = 2;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S", "E")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", false);
		mapOfNullables.put("E", false);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", new HashSet<String>());
		firstSetsInfo.put("E", new HashSet<String>());
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("E", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 2;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject18 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest18Array = {grammarTestObject18};
		testData.add(grammarTest18Array);
		/*End Grammar 18*/
		
		/*Grammar 19*/
		grammarFileName = "grammar19.clg";
		noOfNonTerminals = 2;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S", "E")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("E", true);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("", "token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("E", Stream.of("", "token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("E", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 4;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject19 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest19Array = {grammarTestObject19};
		testData.add(grammarTest19Array);
		/*End Grammar 19*/
		
		/*Grammar 20*/
		grammarFileName = "grammar20.clg";
		noOfNonTerminals = 4;
		noOfTerminals = 10;
		
		
		setOfNonTerminals = Stream.of("S", "E", "F", "G")
				.collect(Collectors.toCollection(HashSet::new));
		setOfTerminals = Stream.of("token0", "token1", "token2", 
				"token3", "token4", "token5", "token6", "token7", 
				"token8", "token9")
				.collect(Collectors.toCollection(HashSet::new));
		
		mapOfNullables = new HashMap<String, Boolean>();
		mapOfNullables.put("S", true);
		mapOfNullables.put("E", false);
		mapOfNullables.put("F", false);
		mapOfNullables.put("G", false);
		mapOfNullables.put("token0", false);
		mapOfNullables.put("token1", false);
		mapOfNullables.put("token2", false);
		mapOfNullables.put("token3", false);
		mapOfNullables.put("token4", false);
		mapOfNullables.put("token5", false);
		mapOfNullables.put("token6", false);
		mapOfNullables.put("token7", false);
		mapOfNullables.put("token8", false);
		mapOfNullables.put("token9", false);
		
		firstSetsInfo  = 
				new HashMap<String, Set<String>>();
		firstSetsInfo.put("S", Stream.of("", "token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("E", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("F", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("G", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token0", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token1", Stream.of("token1")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token2", Stream.of("token2")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token3", Stream.of("token3")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token4", Stream.of("token4")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token5", Stream.of("token5")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token6", Stream.of("token6")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token7", Stream.of("token7")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token8", Stream.of("token8")
				.collect(Collectors.toCollection(HashSet::new)));
		firstSetsInfo.put("token9", Stream.of("token9")
				.collect(Collectors.toCollection(HashSet::new)));
		
		followSetsInfo  = 
				new HashMap<String, Set<String>>();
		followSetsInfo.put("S", Stream.of("$")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("E", Stream.of("token0")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("F", Stream.of("token1", "token4")
				.collect(Collectors.toCollection(HashSet::new)));
		followSetsInfo.put("G", Stream.of("token2", "token6")
				.collect(Collectors.toCollection(HashSet::new)));
		
		noOfProductions = 5;
		
		startSymbol = "S"; 
		GrammarTestObject grammarTestObject20 = 
				new GrammarTestObject(grammarFileName, //grammar file name
									  noOfNonTerminals, // no. of nonterminals
									  noOfTerminals, // no.of terminals
									  setOfNonTerminals, // nonterminals set 
									  setOfTerminals, // terminals set
									  mapOfNullables, // nullable set
									  firstSetsInfo, // first set
									  followSetsInfo, //follow set
									  noOfProductions, // no. of productions
									  startSymbol); // start symbol
		GrammarTestObject[] grammarTest20Array = {grammarTestObject20};
		testData.add(grammarTest20Array);
		/*End Grammar 20*/
		
		return testData;
	}
	
	private GrammarLoader _loader;
	private GrammarTestObject _testObject;
	
	public GrammarTests(GrammarTestObject testObject){
		this._testObject = testObject;
		try {
			_loader = 
					new GrammarLoader(".\\tests\\grammars\\"
			+this._testObject.grammarFileName);
		} catch (IOException | IncorrectGrammarFileNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findsCorrectNumberOfNonTerminals(){
		if(this._testObject.exception!=null){
			this._testObject.exception.expect(Exception.class);
		}
		Grammar g = _loader.getGrammar();
		Set<String> nonterminals = g.getAllNonTerminals();
		assertEquals(this._testObject.numberOfNonTerminals, nonterminals.size());
	}
	
	@Test
	public void findsCorrectNonTerminals(){
		if(this._testObject.exception!=null){
			this._testObject.exception.expect(Exception.class);
		}
		Grammar g = _loader.getGrammar();
		Set<String> nonterminals = g.getAllNonTerminals();
		assertEquals(this._testObject.nonTerminalsSet, nonterminals);
	}
	
	@Test
	public void findsCorrectNumberOfTerminals(){
		if(this._testObject.exception!=null){
			this._testObject.exception.expect(Exception.class);
		}
		Grammar g = _loader.getGrammar();
		Set<String> terminals = g.getAllTerminals();
		assertEquals(this._testObject.numberOfTerminals, terminals.size());
	}
	
	@Test
	public void findsCorrectTerminals(){
		if(this._testObject.exception!=null){
			this._testObject.exception.expect(Exception.class);
		}
		Grammar g = _loader.getGrammar();
		Set<String> terminals = g.getAllTerminals();
		assertEquals(this._testObject.terminalsSet, terminals);
	}
	
	@Test
	public void findsCorrectNumberOfProductions(){
		if(this._testObject.exception!=null){
			this._testObject.exception.expect(Exception.class);
		}
		Grammar g = _loader.getGrammar();
		int totalNoOfProductions = g.getTotalNumberOfProductions();
		assertEquals(this._testObject.numberOfProductions, totalNoOfProductions);
	}
	
	@Test
	public void findsCorrectStartSymbol(){
		if(this._testObject.exception!=null){
			this._testObject.exception.expect(Exception.class);
		}
		Grammar g = _loader.getGrammar();
		String startSymbol = g.getStartSymbol();
		assertEquals(this._testObject.startSymbol, startSymbol);
	}
	
	@Test
	public void findsCorrectFirstSets(){
		if(this._testObject.exception!=null){
			this._testObject.exception.expect(Exception.class);
		}
		Grammar g = _loader.getGrammar();
		Map<String, Set<String>> firstSet = g.computeFirstSets();
		assertEquals(this._testObject.firstSet, firstSet);
	}
	
	@Test
	public void findsCorrectFollowSets(){
		if(this._testObject.exception!=null){
			this._testObject.exception.expect(Exception.class);
		}
		Grammar g = _loader.getGrammar();
		Map<String, Set<String>> followSet = g.computeFollowSets();
		assertEquals(this._testObject.followSet, followSet);
	}
	
	@Test
	public void findsCorrectNullableSymbolMap(){
		if(this._testObject.exception!=null){
			this._testObject.exception.expect(Exception.class);
		}
		Grammar g = _loader.getGrammar();
		Map<String, Boolean> nullableSymbolMap = g.computeNullableSymbols();
		assertEquals(this._testObject.nullableSymbolsMap, nullableSymbolMap);
	}

}
