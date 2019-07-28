package com.cluster.parser;

import com.cluster.grammar.Grammar;
import com.cluster.grammarloader.GrammarLoader;
import com.cluster.lexer.LongestMatchLexer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputProgram = "print(\"Hello World\")";
		String grammarFilePath = ".\\LuaGrammar.clg";
		LongestMatchLexer lexer = new LongestMatchLexer(inputProgram, grammarFilePath);
		
		Grammar grammar = lexer.getGrammarLoader().getGrammar();
		System.out.println(grammar.getLR1ParseTable().toString());
	}

}
