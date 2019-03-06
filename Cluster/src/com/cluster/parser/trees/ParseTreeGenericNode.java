package com.cluster.parser.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public abstract class ParseTreeGenericNode implements IParseTreeNode {
	
	private List<IParseTreeNode> _childrenNodes;
	private static int _ID = 0;
	private int _id;
	
	private String _data;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParseTreeSNode root = 
				new ParseTreeSNode("0", new ArrayList<IParseTreeNode>());
		ParseTreeSNode child1 = 
				new ParseTreeSNode("1", new ArrayList<IParseTreeNode>());
		ParseTreeTNode child2 = 
				new ParseTreeTNode("2", new ArrayList<IParseTreeNode>());
		ParseTreeSNode child3 = 
				new ParseTreeSNode("3", new ArrayList<IParseTreeNode>());
		child2.addChildNode(child3);
		ParseTreeSNode child4 = 
				new ParseTreeSNode("4", new ArrayList<IParseTreeNode>());
		child2.addChildNode(child3);
		child1.addChildNode(child4);
		
		root.addChildNode(child1);
		root.addChildNode(child2);
		
		SimpleBaseVisitor<Object, Object> visitor = new SimpleBaseVisitor<Object, Object>();
		root.accept(visitor, null);
	}
	
	public void testBFS(){
		ArrayDeque<IParseTreeNode> q = new ArrayDeque<IParseTreeNode>();
		ArrayDeque<Integer> l = new ArrayDeque<Integer>();
		q.offer(this);
		l.offer(0);
		
		while(q.size()!=0){
			IParseTreeNode node = q.poll();
			Integer level = l.poll(); 
			System.out.println("node: "+node.getID()+" "+"level: "+level);
			for(IParseTreeNode child: node.getAllChildNodes()){
				q.offer(child);
				l.offer(level+1);
			}
		}
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
	public int getID(){
		return this._id;
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

	//public abstract <R, P> R accept(IParseTreeVisitor<R, P> visitor, P p);

}
