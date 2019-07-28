package com.cluster.parser.trees;

import java.util.List;

public interface IParseTreeNode extends com.owl.trees.IParseTreeNode{
	
	//The following methods are inherited from com.owl.trees.IParseTreeNode
	//public List<IParseTreeNode> getAllChildNodes();
	//public String getValue();
	
	public Boolean addChildNode(IParseTreeNode child);
	public Boolean removeChildNode(IParseTreeNode child);
	
	public int getID();
	
	//public <R, P> R accept(IParseTreeVisitor<R, P> visitor, P p);
	
}
