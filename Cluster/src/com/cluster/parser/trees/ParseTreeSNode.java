package com.cluster.parser.trees;

import java.util.List;

public class ParseTreeSNode extends ParseTreeNonTerminalNode{

	public ParseTreeSNode(String data, List<IParseTreeNode> children) {
		super(data, children);
		// TODO Auto-generated constructor stub
	}
	
	public <R, P> R accept(IParseTreeVisitor<R, P> visitor, P p){
		return visitor.visit(this, p);
	}

}
