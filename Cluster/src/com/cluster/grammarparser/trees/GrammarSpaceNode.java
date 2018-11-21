package com.cluster.grammarparser.trees;

import com.owl.automata.IFiniteAutomata;
import com.owl.trees.ITreeNode;

public class GrammarSpaceNode implements ITreeNode {
	
	private ITreeNode nNode;
	private ITreeNode fNode;
	
	public GrammarSpaceNode(ITreeNode nNode, ITreeNode fNode){
		this.nNode = nNode;
		this.fNode = fNode;
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
		return " ";
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
