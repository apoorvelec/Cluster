package com.cluster.parser.trees.generated;
import com.cluster.parser.trees.*;

public abstract class ParseTreeBasicWalker<R, P> implements IParseTreeVisitor<R, P>{

	public abstract <P> void enterS(ParseTree_S_NTNode node, P p);
	public abstract <P> void exitS(ParseTree_S_NTNode node, P p);

	public abstract <P> void enterE(ParseTree_E_NTNode node, P p);
	public abstract <P> void exitE(ParseTree_E_NTNode node, P p);

	public abstract <P> void enterF(ParseTree_F_NTNode node, P p);
	public abstract <P> void exitF(ParseTree_F_NTNode node, P p);

	public abstract <P> void enterG(ParseTree_G_NTNode node, P p);
	public abstract <P> void exitG(ParseTree_G_NTNode node, P p);

	public abstract <P> void visittoken0(ParseTree_token0_TNode node, P p);

	public abstract <P> void visittoken1(ParseTree_token1_TNode node, P p);

	public abstract <P> void visittoken2(ParseTree_token2_TNode node, P p);

	public abstract <P> void visittoken3(ParseTree_token3_TNode node, P p);

	public abstract <P> void visittoken4(ParseTree_token4_TNode node, P p);

	public abstract <P> void visittoken5(ParseTree_token5_TNode node, P p);

	public abstract <P> void visittoken6(ParseTree_token6_TNode node, P p);

	public abstract <P> void visittoken7(ParseTree_token7_TNode node, P p);

	public abstract <P> void visittoken8(ParseTree_token8_TNode node, P p);

	public abstract <P> void visittoken9(ParseTree_token9_TNode node, P p);

	
	@Override
	public R visit(ParseTree_S_NTNode node, P p) {
		enterS(node, p);
		for(IParseTreeNode n : node.getAllChildNodes()){
			this.visit(n, p);
		}
		exitS(node, p);
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
	public R visit(ParseTree_G_NTNode node, P p) {
		enterG(node, p);
		for(IParseTreeNode n : node.getAllChildNodes()){
			this.visit(n, p);
		}
		exitG(node, p);
		return null;
	}

	
	@Override
	public R visit(ParseTree_token0_TNode node, P p) {
		visittoken0(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_token1_TNode node, P p) {
		visittoken1(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_token2_TNode node, P p) {
		visittoken2(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_token3_TNode node, P p) {
		visittoken3(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_token4_TNode node, P p) {
		visittoken4(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_token5_TNode node, P p) {
		visittoken5(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_token6_TNode node, P p) {
		visittoken6(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_token7_TNode node, P p) {
		visittoken7(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_token8_TNode node, P p) {
		visittoken8(node, p);
		return null;
	}

	@Override
	public R visit(ParseTree_token9_TNode node, P p) {
		visittoken9(node, p);
		return null;
	}

	
	@Override
	public R visit(IParseTreeNode node, P p) {
		// TODO Auto-generated method stub
		if(node instanceof ParseTree_S_NTNode){
			this.visit((ParseTree_S_NTNode)node, p);
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

		if(node instanceof ParseTree_G_NTNode){
			this.visit((ParseTree_G_NTNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token0_TNode){
			this.visit((ParseTree_token0_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token1_TNode){
			this.visit((ParseTree_token1_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token2_TNode){
			this.visit((ParseTree_token2_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token3_TNode){
			this.visit((ParseTree_token3_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token4_TNode){
			this.visit((ParseTree_token4_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token5_TNode){
			this.visit((ParseTree_token5_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token6_TNode){
			this.visit((ParseTree_token6_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token7_TNode){
			this.visit((ParseTree_token7_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token8_TNode){
			this.visit((ParseTree_token8_TNode)node, p);
			return null;
		}

		if(node instanceof ParseTree_token9_TNode){
			this.visit((ParseTree_token9_TNode)node, p);
			return null;
		}

		return null;
	}
}
