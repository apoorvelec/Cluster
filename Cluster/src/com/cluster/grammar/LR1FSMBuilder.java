package com.cluster.grammar;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.cluster.lexer.LongestMatchLexer;

public class LR1FSMBuilder {
	
	private LR1State _headState;
	private Grammar _grammar;
	
	private HashSet<LR1State> _setOfAllStates;
	private ArrayDeque<LR1State> _stackOfStates;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestMatchLexer lexer = new LongestMatchLexer("");
		Grammar grammar = lexer.getGrammarLoader().getGrammar();
		System.out.println(grammar.getLR1ParseTable());
	}
	
	public LR1FSMBuilder(Grammar grammar){
		this._grammar = grammar;
		this._setOfAllStates = new HashSet<LR1State>();
		this._stackOfStates = new ArrayDeque<LR1State>();
		this._headState = buildMachine();
	}

	private LR1State buildMachine() {
		// TODO Auto-generated method stub
		LR1State startState = this.getStartingState();
		
		//this._setOfAllStates.add(startState);
		this._stackOfStates.push(startState);
		
		while(this._stackOfStates.size()>0){
			//System.out.println(this._stackOfStates.size());
			LR1State currentState = this._stackOfStates.pop();
			currentState.computeClosure();
			//System.out.println(this._setOfAllStates.stream().map(LR1State::getID).collect(Collectors.toSet()));
			this._setOfAllStates.add(currentState);
			HashMap<String, LR1State> newStates = currentState.computeTransitionStates();
			
			for(String symbol: newStates.keySet()){
				boolean found = false;
				for(LR1State s: this._setOfAllStates){
					if(s.equals(newStates.get(symbol))){
						currentState.addTransition(symbol, s);
						found = true;
						break;
					}
				}
				if(!found){
					//search in stack
					for(LR1State s: this._stackOfStates){
						if(s.equals(newStates.get(symbol))){
							currentState.addTransition(symbol, s);
							found = true;
							break;
						}
					}
				}
				if(!found){
					//this._setOfAllStates.add(newStates.get(symbol));
					this._stackOfStates.push(newStates.get(symbol));
					currentState.addTransition(symbol, newStates.get(symbol));
				}
				
			}
		}
		return startState;
	}
	
	public LR1State getHeadState(){
		return this._headState;
	}
	
	public HashSet<LR1State> getSetOfAllStates(){
		return this._setOfAllStates;
	}
	
	//Currently, we have to manually append the grammar
	//with the augmented production, which is,
	//   SP->S (S is starting symbol here)
	private LR1State getStartingState(){
		LR1Item item = new LR1Item(_grammar, 0, 0, "$");
		LR1ItemSet itemSet = new LR1ItemSet();
		itemSet.addItem(item);
		LR1State state = new LR1State(itemSet);
		
		return state;
	}
	
}
