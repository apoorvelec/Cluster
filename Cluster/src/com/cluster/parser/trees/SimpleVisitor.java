package com.cluster.parser.trees;

public abstract class SimpleVisitor<R, P> implements IParseTreeVisitor<R, P>{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public R visit(ParseTreeSNode node, P p) {
		// TODO Auto-generated method stub
		enterS(node, p);
		System.out.println(node.getValue());
		for(IParseTreeNode n : node.getAllChildNodes()){
			this.visit(n, p);
		}
		exitS(node, p);
		return null;
	}

	@Override
	public R visit(ParseTreeTNode node, P p) {
		// TODO Auto-generated method stub
		enterT(node, p);
		System.out.println(node.getValue());
		for(IParseTreeNode n : node.getAllChildNodes()){
			this.visit(n, p);
		}
		exitT(node, p);
		return null;
	}

	@Override
	public R visit(IParseTreeNode node, P p) {
		// TODO Auto-generated method stub
		if(node instanceof ParseTreeSNode){
			this.visit((ParseTreeSNode)node, p);
		}else if(node instanceof ParseTreeTNode){
			this.visit((ParseTreeTNode)node, p);
		}
		return null;
	}
	
	public abstract <P> void enterS(ParseTreeSNode node, P p);
	public abstract <P> void exitS(ParseTreeSNode node, P p);
	public abstract <P> void enterT(ParseTreeTNode node, P p);
	public abstract <P> void exitT(ParseTreeTNode node, P p);

}
