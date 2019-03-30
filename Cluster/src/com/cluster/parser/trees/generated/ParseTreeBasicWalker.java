package com.cluster.parser.trees.generated;
import com.cluster.parser.trees.*;

public abstract class ParseTreeBasicWalker<R, P> implements IParseTreeVisitor<R, P>{

	public abstract <P> void enterT(ParseTree_T_NTNode node, P p);
	public abstract <P> void exitT(ParseTree_T_NTNode node, P p);

	public abstract <P> void enterE(ParseTree_E_NTNode node, P p);
	public abstract <P> void exitE(ParseTree_E_NTNode node, P p);

	public abstract <P> void enterF(ParseTree_F_NTNode node, P p);
	public abstract <P> void exitF(ParseTree_F_NTNode node, P p);

	public abstract <P> void enterEP(ParseTree_EP_NTNode node, P p);
	public abstract <P> void exitEP(ParseTree_EP_NTNode node, P p);

	public abstract <P> void enterTP(ParseTree_TP_NTNode node, P p);
	public abstract <P> void exitTP(ParseTree_TP_NTNode node, P p);

	public abstract <P> void visitPLUS(ParseTree_PLUS_TNode node, P p);

	public abstract <P> void visitMINUS(ParseTree_MINUS_TNode node, P p);

	public abstract <P> void visitMULT(ParseTree_MULT_TNode node, P p);

	public abstract <P> void visitLPAREN(ParseTree_LPAREN_TNode node, P p);

	public abstract <P> void visitRPAREN(ParseTree_RPAREN_TNode node, P p);

	public abstract <P> void visitFSLASH(ParseTree_FSLASH_TNode node, P p);

	public abstract <P> void visitINT(ParseTree_INT_TNode node, P p);

	
	@Override
	public R visit(ParseTree_T_NTNode node, P p) {
		enterT(node, p);
		for(IParseTreeNode n : node.getAllChildNodes()){
			this.visit(n, p);
		}
		exitT(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_E_NTNode node, P p) {
		enterE(node, p);
		for(IParseTreeNode n : node.getAllChildNodes()){
			this.visit(n, p);
		}
		exitE(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_F_NTNode node, P p) {
		enterF(node, p);
		for(IParseTreeNode n : node.getAllChildNodes()){
			this.visit(n, p);
		}
		exitF(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_EP_NTNode node, P p) {
		enterEP(node, p);
		for(IParseTreeNode n : node.getAllChildNodes()){
			this.visit(n, p);
		}
		exitEP(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_TP_NTNode node, P p) {
		enterTP(node, p);
		for(IParseTreeNode n : node.getAllChildNodes()){
			this.visit(n, p);
		}
		exitTP(node, p);
		return null;
	}

	
	@Override
	public R visit(ParseTree_PLUS_TNode node, P p) {
		visitPLUS(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_MINUS_TNode node, P p) {
		visitMINUS(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_MULT_TNode node, P p) {
		visitMULT(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_LPAREN_TNode node, P p) {
		visitLPAREN(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_RPAREN_TNode node, P p) {
		visitRPAREN(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_FSLASH_TNode node, P p) {
		visitFSLASH(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_INT_TNode node, P p) {
		visitINT(node, p);
		return null;
	}

	
	@Override
	public R visit(IParseTreeNode node, P p) {
		// TODO Auto-generated method stub
		if(node instanceof ParseTree_T_NTNode){
			this.visit((ParseTree_T_NTNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_E_NTNode){
			this.visit((ParseTree_E_NTNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_F_NTNode){
			this.visit((ParseTree_F_NTNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_EP_NTNode){
			this.visit((ParseTree_EP_NTNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_TP_NTNode){
			this.visit((ParseTree_TP_NTNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_PLUS_TNode){
			this.visit((ParseTree_PLUS_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_MINUS_TNode){
			this.visit((ParseTree_MINUS_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_MULT_TNode){
			this.visit((ParseTree_MULT_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_LPAREN_TNode){
			this.visit((ParseTree_LPAREN_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_RPAREN_TNode){
			this.visit((ParseTree_RPAREN_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_FSLASH_TNode){
			this.visit((ParseTree_FSLASH_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_INT_TNode){
			this.visit((ParseTree_INT_TNode)node, p);
			return null;
		}

		return null;
	}
}
