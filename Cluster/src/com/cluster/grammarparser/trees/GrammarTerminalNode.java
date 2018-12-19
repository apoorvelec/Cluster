package com.cluster.grammarparser.trees;

import com.owl.automata.IFiniteAutomata;
import com.owl.trees.ITreeNode;

public class GrammarTerminalNode implements ITreeNode {
	
	private String _terminal;
	
	public GrammarTerminalNode(String terminal){
		this._terminal = terminal;
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
		return this._terminal;
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
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof GrammarTerminalNode)){
			return false;
		}
		
		GrammarTerminalNode node = (GrammarTerminalNode) o;
		
		return this._terminal.equals(node._terminal);
		
	}

}
