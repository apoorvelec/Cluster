package com.cluster.parser.trees;

import java.util.List;

public class ParseTreeGenericNode implements IParseTreeNode {
	
	private List<IParseTreeNode> _childrenNodes;
	private static int _ID = 0;
	private int _id;
	
	private String _data;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public ParseTreeGenericNode(String data, List<IParseTreeNode> children) {
		// TODO Auto-generated constructor stub
		_ID++;
		this._id = _ID;
		this._data = data;
		this._childrenNodes = children;
	}

	@Override
	public List<IParseTreeNode> getAllChildNodes() {
		// TODO Auto-generated method stub
		return this._childrenNodes;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this._data;
	}

	@Override
	public void visitInorderTraversal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitPreorderTraversal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitPostOrderTraversal() {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean addChildNode(IParseTreeNode child) {
		// TODO Auto-generated method stub
		if(child == null){
			return null;
		}
		
		if(this._childrenNodes.contains(child)){
			return false;
		}
		
		return this._childrenNodes.add(child);
	}

	@Override
	public Boolean removeChildNode(IParseTreeNode child) {
		// TODO Auto-generated method stub
		if(child == null){
			return null;
		}
		
		if(this._childrenNodes.contains(child)){
			return this._childrenNodes.remove(child);
		}
		
		return false;
	}
	
	@Override
	public boolean equals(Object node){
		if(node instanceof ParseTreeGenericNode){
			return this._id == ((ParseTreeGenericNode)node)._id;
		}
		return false;
	}

}
