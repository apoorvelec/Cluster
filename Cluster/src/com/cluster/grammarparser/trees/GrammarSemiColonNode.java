package com.cluster.grammarparser.trees;

import com.owl.automata.IFiniteAutomata;
import com.owl.trees.ITreeNode;

public class GrammarSemiColonNode implements ITreeNode {
	
	private ITreeNode pNode;
	private ITreeNode gNode;
	
	public GrammarSemiColonNode(ITreeNode pNode, ITreeNode gNode){
		this.pNode = pNode;
		this.gNode = gNode;
	}

	@Override
	public ITreeNode GetLeftNode() {
		// TODO Auto-generated method stub
		return this.pNode;
	}

	@Override
	public ITreeNode GetRightNode() {
		// TODO Auto-generated method stub
		return this.gNode;
	}

	@Override
	public String GetValue() {
		// TODO Auto-generated method stub
		return ";";
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
