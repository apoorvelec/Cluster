package com.cluster.parser.trees.generated;
import java.util.List;
import com.cluster.parser.trees.*;

/*Auto Generated class file. Please do not modify!!*/
public class ParseTree_PLUS_TNode extends ParseTreeTerminalNode{

	public ParseTree_PLUS_TNode(String data, List<IParseTreeNode> children) {
		super(data, children);
		// TODO Auto-generated constructor stub
	}
	
	public <R, P> R accept(IParseTreeVisitor<R, P> visitor, P p){
		return visitor.visit(this, p);
	}

}
