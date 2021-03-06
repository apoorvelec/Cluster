package com.cluster.grammar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cluster.grammarloader.GrammarLoader;
import com.owl.trees.ITreeNode;

public class EarleyItem {
	
	private Grammar _grammar;
	private int _productionIndex;
	private int _dotIndex;
	private int _startPointIndex;
	private String _symbolAfterDot;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrammarLoader loader = null;
		try {
			loader = new GrammarLoader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Grammar grammar = loader.getGrammar();
		
		EarleyItem item = new EarleyItem(loader.getGrammar(), 0, 0, 0);
		EarleyItem item1 = new EarleyItem(grammar, 0, 1, 0);
		EarleyItem item2 = new EarleyItem(grammar, 0, 0, 2);
		
		System.out.println(item.equals(item1));
		System.out.println(item.equals(item2));
		
		System.out.println(item);
		System.out.println(item.shiftCore());
		System.out.println(item.shiftCore());
		//System.out.println(item.shiftCore().shiftCore().shiftCore());
		
		System.out.println(item.getItemExpansions(5));
		
	}
	
	public EarleyItem(Grammar grammar, int productionIndex, int dotIndex, int startPointIndex){
		this._grammar = grammar;
		this._productionIndex = productionIndex;
		this._dotIndex = dotIndex;
		this._startPointIndex = startPointIndex;
		this._symbolAfterDot = determineSymbolAfterDot();
	}
	
	private String determineSymbolAfterDot() {
		// TODO Auto-generated method stub
		String symbol = null;
		List<ITreeNode> rhsSymbols = 
				this._grammar.getAllRightHandSymbolsOfProduction(_productionIndex);
		if(!isDotAtEnd()){
			symbol = rhsSymbols.get(_dotIndex).GetValue();
		}
		
		return symbol;
	}
	
	/**
	 * A new EarleyItem which gets generated by shifting the dot
	 * one position to the right
	 * @return
	 */
	public EarleyItem shiftCore(){
		EarleyItem result = null;
		
		if(isDotAtEnd()){
			// no shift can occur
			return result;
		}
		
		result = new EarleyItem(_grammar, _productionIndex, _dotIndex+1, _startPointIndex);
		return result;
	}
	
	public Set<EarleyItem> getItemExpansions(int currentState){
		Set<EarleyItem> resultSet = new HashSet<EarleyItem>();
		if(this.isDotAtEnd()){
			return resultSet;
		}
		String symbol = getSymbolAfterDot();
		if(this._grammar.isTerminal(symbol)){
			return resultSet;
		}
		
		//If code comes here, symbol is nonterminal
		List<Integer> productionIndices = 
				this._grammar.getAllProductionsStartingWith(symbol);
		
		for(Integer productionIndex: productionIndices){
			// create an item
			EarleyItem expandedItem = 
					new EarleyItem(this._grammar, productionIndex, 0, currentState);
			resultSet.add(expandedItem);
		}
		
		return resultSet;
	}
	
	public boolean isDotAtEnd(){
		int noOfRightHandSymbols = 
				this._grammar.
				getAllRightHandSymbolsOfProduction(_productionIndex).size();
		if(this._dotIndex>=noOfRightHandSymbols){
			return true;
		}
		
		return false;
	}
	
	public String getSymbolAfterDot(){
		return this._symbolAfterDot;
	}
	
	public Grammar getGrammar(){
		return this._grammar;
	}
	
	public int getProductionIndex(){
		return this._productionIndex;
	}
	
	public int getDotIndex(){
		return this._dotIndex;
	}
	
	public int getStartPointIndex(){
		return this._startPointIndex;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof EarleyItem)){
			return false;
		}
		
		EarleyItem earleyItem = (EarleyItem) o;
		if(this._productionIndex == earleyItem._productionIndex &&
				this._dotIndex == earleyItem._dotIndex &&
				this._startPointIndex==earleyItem._startPointIndex){
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode(){
		return this._productionIndex+this._dotIndex+this._startPointIndex;
	}
	
	@Override
	public String toString(){
		ITreeNode startingNonTerminal = 
				this._grammar.getNonTerminalStartingProduction(this._productionIndex);
		List<ITreeNode> rhsSymbols = 
				this._grammar.getAllRightHandSymbolsOfProduction(_productionIndex);
		String itemStringRepresentation = startingNonTerminal.GetValue()+"->";
		for(int i=0;i<rhsSymbols.size();i++){
			if(i==this._dotIndex){
				itemStringRepresentation+=".";
			}
			itemStringRepresentation+=rhsSymbols.get(i).GetValue()+" ";
		}
		if(isDotAtEnd()){
			itemStringRepresentation+=".";
		}
		itemStringRepresentation = "{"+itemStringRepresentation
				+", ("+this._startPointIndex+") }";
		
		return itemStringRepresentation;
	}
	
}
