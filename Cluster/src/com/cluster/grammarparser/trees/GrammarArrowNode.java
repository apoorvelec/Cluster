package com.cluster.grammarparser.trees;

import com.owl.automata.IFiniteAutomata;
import com.owl.trees.ITreeNode;

public class GrammarArrowNode implements ITreeNode {
	
	private ITreeNode nNode;
	private ITreeNode fNode;
	private static int _ID = 0;
	private int _id;
	
	public GrammarArrowNode(ITreeNode nNode, ITreeNode fNode){
		this.nNode = nNode;
		this.fNode = fNode;
		_ID++;
		this._id = _ID;
	}

	@Override
	public ITreeNode GetLeftNode() {
		// TODO Auto-generated method stub
		return this.nNode;
	}

	@Override
	public ITreeNode GetRightNode() {
		// TODO Auto-generated method stub
		return this.fNode;
	}

	@Override
	public String GetValue() {
		// TODO Auto-generated method stub
		return this._id+": "+"->";
	}

	@Override
	public ITreeNode GetSingleChildNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void PrintInorderTraversal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PrintPreorderTraversal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void PrintPostOrderTraversal() {
		// TODO Auto-generated method stub

	}

	@Override
	public IFiniteAutomata GetEquivalentNFA() {
		// TODO Auto-generated method stub
		return null;
	}

}
