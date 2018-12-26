package com.cluster.parser;

import com.cluster.parser.trees.IParseTreeNode;
import com.cluster.tokens.Token;

public interface IParser {
	
	public Token consumeToken();
	
	public IParseTreeNode parse();
}
