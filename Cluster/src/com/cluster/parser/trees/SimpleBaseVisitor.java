package com.cluster.parser.trees;

public class SimpleBaseVisitor<R, P> extends SimpleVisitor<R, P> {

	@Override
	public <P> void enterS(ParseTreeSNode node, P p) {
		// TODO Auto-generated method stub
		System.out.println("Visiting S node ...");
	}

	@Override
	public <P> void exitS(ParseTreeSNode node, P p) {
		// TODO Auto-generated method stub
		System.out.println("Exiting S node ...");
	}

	@Override
	public <P> void enterT(ParseTreeTNode node, P p) {
		// TODO Auto-generated method stub
		System.out.println("Visiting T node ...");
	}

	@Override
	public <P> void exitT(ParseTreeTNode node, P p) {
		// TODO Auto-generated method stub
		System.out.println("Exiting T node ...");
	}

}
