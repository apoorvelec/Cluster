package com.cluster.grammar;

import java.util.HashMap;
import java.util.List;

import com.cluster.tokens.*;
import com.owl.trees.ITreeNode;

public class Grammar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public List<TokenDefinition> getAllTokenDefinitions(){
		return null;
	}
	
	public HashMap<String, String> getTokenDefinitions(){
		return null;
	}
	
	public ITreeNode getProduction(int i){
		return null;
	}
	
	public ITreeNode getNonTerminalStartingProduction(int i){
		return null;
	}
	
	public List<ITreeNode> getAllRightHandSymbolsOfProduction(int i){
		return null;
	}
	
	public boolean IsNullable(String symbol){
		return false;
	}
	
	public List<TokenDefinition> getFirstSet(String symbol){
		return null;
	}
	
	public List<TokenDefinition> getFollowSet(String symbol){
		return null;
	}
}
