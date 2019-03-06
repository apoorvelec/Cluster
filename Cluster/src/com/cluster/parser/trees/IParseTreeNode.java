package com.cluster.parser.trees;

import java.util.List;

public interface IParseTreeNode {
	
	public List<IParseTreeNode> getAllChildNodes();
	
	public Boolean addChildNode(IParseTreeNode child);
	public Boolean removeChildNode(IParseTreeNode child);
	
	public String getValue();
	public int getID();
	
	//public <R, P> R accept(IParseTreeVisitor<R, P> visitor, P p);
	
}
