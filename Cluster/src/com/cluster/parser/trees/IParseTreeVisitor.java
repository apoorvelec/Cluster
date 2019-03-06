package com.cluster.parser.trees;

public interface IParseTreeVisitor<R,P> {
	
	public R visit(ParseTreeSNode node, P p);
	public R visit(ParseTreeTNode node, P p);
	public R visit(IParseTreeNode node, P p);
}
