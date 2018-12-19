package com.cluster.grammarloader;

import java.util.List;

import com.cluster.grammarparser.GrammarParser;
import com.owl.trees.ITreeNode;

public class CFGLoader {

	// This field holds the cfg grammar definition as a single string
	// as needed by the GrammarParser class
	private String _cfgGrammarDefinition;
	private GrammarParser _cfgGrammarParser;
	
	public ITreeNode rootNodeForCFG;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public CFGLoader(List<String> cfgGrammarLines){
		StringBuilder builder = new StringBuilder("");
		
		for(String cfgGrammarLine : cfgGrammarLines){
			builder.append(cfgGrammarLine);
		}
		
		this._cfgGrammarDefinition = builder.toString();
		this._cfgGrammarParser = new GrammarParser(this._cfgGrammarDefinition);
		
		this.rootNodeForCFG = this._cfgGrammarParser.parse();
	}

}
