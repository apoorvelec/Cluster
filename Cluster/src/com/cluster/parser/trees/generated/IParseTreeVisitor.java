package com.cluster.parser.trees.generated;
import com.cluster.parser.trees.*;

public interface IParseTreeVisitor<R,P> {
	
public R visit(ParseTree_token9_TNode node, P p);
public R visit(ParseTree_token8_TNode node, P p);
public R visit(ParseTree_token7_TNode node, P p);
public R visit(ParseTree_token6_TNode node, P p);
public R visit(ParseTree_token5_TNode node, P p);
public R visit(ParseTree_token4_TNode node, P p);
public R visit(ParseTree_token3_TNode node, P p);
public R visit(ParseTree_token2_TNode node, P p);
public R visit(ParseTree_token1_TNode node, P p);
public R visit(ParseTree_token0_TNode node, P p);
public R visit(ParseTree_S_NTNode node, P p);
public R visit(ParseTree_G_NTNode node, P p);
public R visit(ParseTree_F_NTNode node, P p);
public R visit(ParseTree_E_NTNode node, P p);
	public R visit(IParseTreeNode node, P p);
}
