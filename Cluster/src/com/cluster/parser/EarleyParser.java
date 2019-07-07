package com.cluster.parser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.cluster.grammar.EarleyItem;
import com.cluster.grammar.Grammar;
import com.cluster.lexer.LongestMatchLexer;
import com.cluster.parser.trees.IParseTreeNode;
import com.cluster.tokens.Token;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class EarleyParser implements IParser{
	
	private ArrayList<HashSet<EarleyItem>> _parseStateList;
	private ArrayList<HashSet<EarleyItem>> _reversedAndCompletedItemsParseStateList;
	
	private List<Token> _tokens;
	private List<Token> _tokensCopy;
	private Grammar _grammar;
	
	private int _numOfTokens = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputProgram1 = "1+1";
		LongestMatchLexer lexer = new LongestMatchLexer(inputProgram1);
		List<Token> tokensList = new ArrayList<Token>();
		
		Token token = lexer.getNextToken();
		while(token!=null){
			tokensList.add(0, token);
			System.out.println(token);
			token = lexer.getNextToken();
		}
		
		EarleyParser earleyParser = 
				new EarleyParser(tokensList, lexer.getGrammarLoader().getGrammar());
		
		ArrayDeque<EarleyItem> stack = new ArrayDeque<EarleyItem>();
		stack.push(new EarleyItem(lexer.getGrammarLoader().getGrammar(), 0, 0, inputProgram1.length()));
		
		earleyParser.earleyRecognizer();
		earleyParser.processParsedStateList();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);
		//System.out.println(stack.peek()+", "+list);
		earleyParser.getDerivation(list, stack, "", new ArrayList<EarleyItem>());
	}
	
	public EarleyParser(List<Token> tokens, Grammar grammar) {
		// TODO Auto-generated constructor stub
		this._tokens = tokens;
		this._tokensCopy = new ArrayList<Token>(this._tokens);
		this._numOfTokens = this._tokens.size();
		this._grammar = grammar;
		this._parseStateList = new ArrayList<HashSet<EarleyItem>>();
		this._reversedAndCompletedItemsParseStateList = 
				new ArrayList<HashSet<EarleyItem>>();
	}

	@Override
	public Token consumeToken() {
		// TODO Auto-generated method stub
		Token token = null;
		if(this._tokens.size()>0){
			token = this._tokens.remove(this._tokens.size()-1);
		}
		return token;
	}
	
	public Token peekToken() {
		// TODO Auto-generated method stub
		if(this._tokens == null){
			return null;
		}
		Token token = null;
		if(this._tokens.size()>0){
			token = this._tokens.get(this._tokens.size()-1);
		}
		return token;
	}
	///////////////////////////////////////////////////////////////////////
	public void getDerivation(ArrayList<Integer> state, ArrayDeque<EarleyItem> stack, String prepend, ArrayList<EarleyItem> resultContainer){
		prepend = prepend+"    ";
		String symbol = getSymbol(stack);
		
		ArrayList<Integer> stateCopy = new ArrayList<>(state);
		ArrayDeque<EarleyItem> stackCopy = getCopyOfStack(stack);
		ArrayList<EarleyItem> resultContainerCopy = 
				new ArrayList<EarleyItem>(resultContainer);
		if(this._grammar.isNonterminal(symbol)){
			ArrayList<EarleyItem> rules = getRelevantRules(state.get(0), symbol);

			
			for(EarleyItem item: rules){
				
				EarleyItem modItem = new EarleyItem(item.getGrammar(), item.getProductionIndex(), 0, item.getStartPointIndex());
				//System.out.println(prepend+modItem+", "+state);
				stack.push(modItem);
				resultContainer.add(modItem);
				getDerivation(state, stack, prepend, resultContainer);
				
				while(stack.size()>1){
					EarleyItem prevItm = stack.pop();
					
					if(!prevItm.isDotAtEnd()){
						//failure
						//System.out.println(prepend+"FAILURE1");
						state = new ArrayList<Integer>(stateCopy);
						stack = getCopyOfStack(stackCopy);
						resultContainer = 
								new ArrayList<EarleyItem>(resultContainerCopy);
						break;
					}
					if((int)state.get(0) != prevItm.getStartPointIndex()){
						//System.out.println(prepend+"FAILURE3");
						state = new ArrayList<Integer>(stateCopy);
						stack = getCopyOfStack(stackCopy);
						resultContainer = 
								new ArrayList<EarleyItem>(resultContainerCopy);
						break;
					}
					
					
					EarleyItem spItem = stack.pop();
					//System.out.println(prepend+spItem.shiftCore()+", "+state);
					stack.push(spItem.shiftCore());
					resultContainer.add(spItem.shiftCore());
					
					getDerivation(state, stack, prepend, resultContainer);
				}
				
				state = new ArrayList<Integer>(stateCopy);
				stack = getCopyOfStack(stackCopy);
				resultContainer = 
						new ArrayList<EarleyItem>(resultContainerCopy);
			}
		}else{
			if(symbol == null){
				EarleyItem itm = stack.pop();
//				System.out.println(itm);
				
				if(state.get(0) == itm.getStartPointIndex()){
					resultContainer.add(itm);
				}

				if(state.get(0) == itm.getStartPointIndex() && itm.getProductionIndex()==0){
					System.out.println("SUCCESS!!");
					resultContainer.add(itm);
					System.out.println(resultContainer);
				}
				stack.push(itm);
				//System.out.println(prepend+"EXIT:"+stack.peek()+", "+state);
				return;
			}
			
			//otherwise symbol is terminal
			if(this._tokensCopy.get(this._numOfTokens-1-state.get(0)).getTokenType().equals(symbol)){
				int num = state.remove(0);
				state.add(num+1);
				
				EarleyItem spItem = stack.pop();
				//System.out.println(prepend+spItem.shiftCore()+", "+state);
				stack.push(spItem.shiftCore());
				resultContainer.add(spItem.shiftCore());
				getDerivation(state, stack, prepend, resultContainer);
				//System.out.println(prepend+"EXIT:"+stack.peek()+", "+state);
				return;
			}
			
			EarleyItem itm = stack.pop();
//			System.out.println(itm);

			if(state.get(0) == itm.getStartPointIndex() && itm.getProductionIndex()==0){
				//System.out.println("SUCCESS!!");
				resultContainer.add(itm);
			}
			stack.push(itm);
			
			//System.out.println(prepend+"EXIT:"+stack.peek()+", "+state);
			return;
			
		}
		
		//System.out.println(prepend+"EXIT:"+stack.peek()+", "+state);
	}
	
	private String getSymbol(ArrayDeque<EarleyItem> stack){
		EarleyItem item = null;
		try{
			item = stack.pop();
		}catch(Exception e){
			return null;
		}
		String symbol = item.getSymbolAfterDot();
		stack.push(item);
		return symbol;
	}
	
	private ArrayList<EarleyItem> getRelevantRules(int state, String symbol){
		HashSet<EarleyItem> relevantState = 
				this._reversedAndCompletedItemsParseStateList.get(state);
		
		ArrayList<EarleyItem> result = new ArrayList<EarleyItem>();
		for(EarleyItem item: relevantState){
			if(this._grammar.getNonTerminalStartingProduction(item.getProductionIndex()).GetValue().equals(symbol)){
				result.add(item);
			}
		}
		
		return result;
	}
	
	private ArrayDeque<EarleyItem> getCopyOfStack(ArrayDeque<EarleyItem> stack){
		ArrayDeque<EarleyItem> result = new ArrayDeque<EarleyItem>();
		
		for(EarleyItem item: stack){
			EarleyItem copyItem = 
					new EarleyItem(item.getGrammar(), 
							item.getProductionIndex(), 
							item.getDotIndex(), 
							item.getStartPointIndex());
			result.add(copyItem);
		}
		return result;
	}
	
	///////////////////////////////////////////////////////////////////////
	
	private void processParsedStateList(){
		ArrayList<HashSet<EarleyItem>> parseStateList = this._parseStateList;
		ArrayList<HashSet<EarleyItem>> processedList = 
				new ArrayList<HashSet<EarleyItem>>();
		
		//initialize the processedList with empyt lists
		//equal to the size of the parseStateList
		for(int i=0;i<parseStateList.size();i++){
			processedList.add(new HashSet<EarleyItem>());
		}
		
		for(int i=0;i<parseStateList.size();i++){
			HashSet<EarleyItem> set = parseStateList.get(i);
			
			//for every item in this set, index i is the ending point
			//while the earley items contain their starting points
			for(EarleyItem item: set){
				if(!item.isDotAtEnd()){
					continue;
				}
				EarleyItem modifiedItem = new EarleyItem(_grammar, item.getProductionIndex(), item.getDotIndex(), i);
				processedList.get(item.getStartPointIndex()).add(modifiedItem);
			}
		}
		
		this._reversedAndCompletedItemsParseStateList = processedList;
	}

	public ArrayList<HashSet<EarleyItem>> earleyRecognizer() {
		// TODO Auto-generated method stub
		HashSet<EarleyItem> State0 = new HashSet<EarleyItem>();
		//Add the very first earley item
		State0.add(new EarleyItem(_grammar, 0, 0, 0));
		this._parseStateList.add(State0);
		
		//i index represents the state number in the list of parse states
		for(int i=0;i<this._numOfTokens+1;i++){
			Token consumedToken = this.consumeToken();
			if(consumedToken!=null){
				System.out.println("Consuming: "+consumedToken.getTokenValue());
			}
			
			HashSet<EarleyItem> state = this._parseStateList.get(i);
			HashSet<EarleyItem> containerStateForProcessing = this.getCopyOf(state);
			
			state.clear(); // clear the state
			
			while(containerStateForProcessing.size()>0){
				EarleyItem item = 
						this.getRandomElementFromSet(containerStateForProcessing);
				
				if(this._parseStateList.get(i).contains(item)){
					//we have already processed this
					continue;
				}
				
				//If we come here then the item has to be processed
				//Add the item the appropriate state first and then process it
				this._parseStateList.get(i).add(item);
				
				if(item.isDotAtEnd()){
					//completion step
					this.completeEarleyItem(item, containerStateForProcessing);
				}else if(this._grammar.isNonterminal(item.getSymbolAfterDot())){
					//prediction step
					int currentState = i;
					this.predictor(item, containerStateForProcessing, currentState);
				}else{
					//scanning step
					int currentState = i;
					this.scanner(item, currentState, consumedToken);
				}
				
			}
		}
		return this._parseStateList;
	}
	
	private void scanner(EarleyItem item, int currentState, Token consumedToken) {
		// TODO Auto-generated method stub
		if((consumedToken!=null 
				&& item.getSymbolAfterDot().equals(consumedToken.getTokenType()))
				||
				(consumedToken==null && item.getSymbolAfterDot()==null)){
			EarleyItem shiftedItem = item.shiftCore();
			
			//check if next state already exists
			if(this._parseStateList.size()<(currentState+2)){
				//if it does not, then create one and add to _parseStateList
				HashSet<EarleyItem> newStateSet = new HashSet<EarleyItem>();
				newStateSet.add(shiftedItem);
				this._parseStateList.add(newStateSet);
			}else{
				this._parseStateList.get((currentState+1)).add(shiftedItem);
			}
		}
	}

	private void predictor(EarleyItem item,
			HashSet<EarleyItem> containerStateForProcessing, int currentState) {
		// TODO Auto-generated method stub
		String itemSymbol = item.getSymbolAfterDot();
		
		List<Integer> productionsIndices = 
				this._grammar.getAllProductionsStartingWith(itemSymbol);
		
		for(int productionIndex: productionsIndices){
			//Construct the Earley item
			EarleyItem itm = 
					new EarleyItem(_grammar, productionIndex, 0, currentState);
			//add it to the container
			containerStateForProcessing.add(itm);
		}
		
	}

	private void completeEarleyItem(EarleyItem item,
			HashSet<EarleyItem> containerStateForProcessing) {
		// TODO Auto-generated method stub
		int stateToSearchInIndex = item.getStartPointIndex();
		String itemLHSSymbol 
		= this._grammar
		      .getNonTerminalStartingProduction(item.getProductionIndex())
		      .GetValue();
		HashSet<EarleyItem> stateToSearchIn = 
				this._parseStateList.get(stateToSearchInIndex);
		
		for(EarleyItem itm: stateToSearchIn){
			if(itm.getSymbolAfterDot()!=null 
					&& itm.getSymbolAfterDot().equals(itemLHSSymbol)){
				EarleyItem shiftedItm = itm.shiftCore();
				containerStateForProcessing.add(shiftedItm);
			}
		}
		
	}

	private HashSet<EarleyItem> getCopyOf(HashSet<EarleyItem> state){
		if(state == null){
			return null;
		}
		
		HashSet<EarleyItem> copyState = new HashSet<EarleyItem>();
		
		for(EarleyItem item: state){
			EarleyItem itemCopy = new EarleyItem(_grammar, item.getProductionIndex(), item.getDotIndex(), item.getStartPointIndex());
			copyState.add(itemCopy);
		}
		
		return copyState;
	}
	
	private <E> E getRandomElementFromSet(HashSet<E> set){
		if(set == null){
			return null;
		}
		
		E elem = null;
		for(E element: set){
			elem = element;
			break;
		}
		set.remove(elem);
		return elem;
	}

	@Override
	public IParseTreeNode parse() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
