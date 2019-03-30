package com.cluster.parser.trees.generated;
import com.cluster.parser.trees.*;

public interface IParseTreeVisitor<R,P> {
	
public R visit(ParseTree_T_NTNode node, P p);
public R visit(ParseTree_TP_NTNode node, P p);
public R visit(ParseTree_RPAREN_TNode node, P p);
public R visit(ParseTree_PLUS_TNode node, P p);
public R visit(ParseTree_MULT_TNode node, P p);
public R visit(ParseTree_MINUS_TNode node, P p);
public R visit(ParseTree_LPAREN_TNode node, P p);
public R visit(ParseTree_INT_TNode node, P p);
public R visit(ParseTree_F_NTNode node, P p);
public R visit(ParseTree_FSLASH_TNode node, P p);
public R visit(ParseTree_E_NTNode node, P p);
public R visit(ParseTree_EP_NTNode node, P p);
	public R visit(IParseTreeNode node, P p);
}
