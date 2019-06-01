package com.cluster.grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LR1State {
	
	private LR1ItemSet _itemSet;
	private HashMap<String, LR1State> _transitionMap;
	private LR1ItemSet _kernel;
	private static int _ID = -1;
	private int _id;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public LR1State(LR1ItemSet itemSet, HashMap<String, LR1State> transitionMap){
		this._itemSet = itemSet;
		this._kernel = new LR1ItemSet(itemSet); //should not have the same reference as this._itemSet
		this._transitionMap = transitionMap;
		this._ID++;
		this._id = this._ID;
		//System.out.println("Created state with ID:"+this._id);
	}
	
	public LR1State(LR1ItemSet itemSet){
		this._itemSet = itemSet;
		this._kernel = new LR1ItemSet(itemSet);
		this._transitionMap = new HashMap<String, LR1State>();
		this._ID++;
		this._id = this._ID;
		//System.out.println("Created state with ID:"+this._id);
	}
	
	public int getID(){
		return this._id;
	}
	
	public HashMap<String, LR1State> getTransitionMap(){
		return this._transitionMap;
	}
	
	public LR1ItemSet getKernel(){
		return this._kernel;
	}
	
	public LR1ItemSet getItemSet(){
		return this._itemSet;
	}
	
	private HashSet<String> getAllSymbolsForTransitions(){
		HashSet<String> setOfTransitionSymbols = new HashSet<String>();
		
		this._itemSet.computeClosure();
		for(LR1Item item: this._itemSet.getAllItems()){
			if(item.getSymbolAfterDot()!=null){
				setOfTransitionSymbols.add(item.getSymbolAfterDot());
			}
		}
		
		return setOfTransitionSymbols;
	}
	
	public HashMap<String, LR1State> computeTransitionStates(){
		HashMap<String, LR1State> result = new HashMap<String, LR1State>();
		
		HashSet<String> setOfTransitionSymbols = this.getAllSymbolsForTransitions();
		for(String symbol: setOfTransitionSymbols){
			//create a new state
			//this state might be a duplicate one while LR1 FSM is being
			//built. This check for duplicates is done in the LR1FSMBuilder.java
			//which creates the FSM machine
			LR1ItemSet newStateKernel = new LR1ItemSet();
			for(LR1Item item: this._itemSet.getAllItems()){
				if(symbol.equals(item.getSymbolAfterDot())){
					newStateKernel.addItem(item.shiftCore());
				}
			}
			
			LR1State newState = new LR1State(newStateKernel);
			result.put(symbol, newState);
		}
		
		return result;
	}
	
	public void computeClosure(){
		this._itemSet.computeClosure();
	}
	
	public void addTransition(String symbol, LR1State state){
		this._transitionMap.put(symbol, state);
	}
	
	public void removeTransition(String symbol){
		this._transitionMap.remove(symbol);
	}
	
	@Override
	public boolean equals(Object lr1state){
		if(!(lr1state instanceof LR1State)){
			return false;
		}
		LR1State lr1stateObj = (LR1State) lr1state;
		return this._kernel.isASubSetOf(lr1stateObj._kernel)&&
				this._kernel.isASuperSetOf(lr1stateObj._kernel);
		
	}
	
	@Override
	public int hashCode(){
		return this._kernel.hashCode();
	}

}
