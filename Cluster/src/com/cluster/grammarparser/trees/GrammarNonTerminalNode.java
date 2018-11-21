package com.cluster.grammarparser.trees;

import com.owl.automata.IFiniteAutomata;
import com.owl.trees.ITreeNode;

public class GrammarNonTerminalNode implements ITreeNode {
	
	private String _nonTerminal;
	
	public GrammarNonTerminalNode(String nonTerminal){
		this._nonTerminal = nonTerminal;
	}
	
	@Override
	public ITreeNode GetLeftNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITreeNode GetRightNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetValue() {
		// TODO Auto-generated method stub
		return this._nonTerminal;
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
