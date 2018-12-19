package com.cluster.grammar;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.monitor.StringMonitor;

import com.cluster.grammarparser.trees.GrammarArrowNode;
import com.cluster.grammarparser.trees.GrammarNonTerminalNode;
import com.cluster.grammarparser.trees.GrammarTerminalNode;
import com.cluster.tokens.*;
import com.owl.trees.ITreeNode;

public class Grammar {
	
	private final List<TokenDefinition> _tokenDefinitionsList;
	private final ITreeNode _rootNodeOfCFG;
	
	private final LinkedHashMap<String, String> _tokenDefinitionsMap;
	private final HashMap<Integer, ITreeNode> _productionsIndexMap;
	private final Set<String> _nonTerminals;
	private final Set<String> _terminals;
	private final Map<String, Set<String>> _firstSets;
	private final Map<String, Set<String>> _followSets;
	private final Map<String, Boolean> _nullableSymbolInfo;
	private final String _startSymbol;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public Grammar(List<TokenDefinition> tokenDefinitionList, ITreeNode rootNodeOfCFG){
		this._tokenDefinitionsList = tokenDefinitionList;
		this._rootNodeOfCFG = rootNodeOfCFG;
		this._tokenDefinitionsMap = populateTokenDefinitions();
		this._productionsIndexMap = indexProductions();
		this._nonTerminals = getAllNonTerminals();
		this._terminals = getAllTerminals();
		
		String startSymbol = null; 
		try{
			//TODO: refactor with usage of Optional class
			startSymbol = this._productionsIndexMap.get(0).GetLeftNode().GetValue();
		}catch(Exception e){
			System.out.println("Could not assign start symbol! ...");
		}
		this._startSymbol = startSymbol;
		
		this._nullableSymbolInfo = computeNullableSymbols();
		this._firstSets = computeFirstSets();
		this._followSets = computeFollowSets();
	}
	
	public String getStartSymbol(){
		return this._startSymbol;
	}
	
	/**
	 * Attempts to give an index to all the productions in the context-free grammar.
	 * The algorithm used is plain DFS to traverse the tree represented by the 
	 * context-free grammar. Indices start from 0 and are assigned in the way productions
	 * are written in the context-free grammar specification file
	 * @return: HashMap<Integer, ITreeNode>
	 */
	private HashMap<Integer, ITreeNode> indexProductions() {
		// TODO Auto-generated method stub
		HashMap<Integer, ITreeNode> map = new HashMap<Integer, ITreeNode>();
		
		ArrayDeque<ITreeNode> stack = new ArrayDeque<ITreeNode>();
		
		if(this._rootNodeOfCFG!=null){
			stack.push(_rootNodeOfCFG); //initialize DFS stack
		}
		
		int productionIndex = 0;
		while(stack.size()!=0){
			ITreeNode node = stack.pop();
			
			if(!(node instanceof GrammarArrowNode)){
				//then search this node
				//no need to search the GrammarArrowNode because
				//it corresponds to a production
				
				ITreeNode rightNode = node.GetRightNode();
				if(rightNode!=null){
					stack.push(rightNode);
				}
				
				ITreeNode leftNode = node.GetLeftNode();
				if(leftNode!=null){
					stack.push(leftNode);
				}
			}else{
				map.put(productionIndex, node);
				productionIndex++;
			}
		}
		
		return map;
	}

	private LinkedHashMap<String, String> populateTokenDefinitions() {
		// TODO Auto-generated method stub
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		if(this._tokenDefinitionsList!=null){
			for(TokenDefinition tokenDefinition : this._tokenDefinitionsList){
				if(map.containsKey(tokenDefinition.getTokenType())){
					//throw an error here ... this shouldn't happen
				}else{
					map.put(tokenDefinition.getTokenType(), 
							tokenDefinition.getRegexDefinitionForTokenType());
				}
			}
		}
		return map;
	}
	
	public List<TokenDefinition> getAllTokenDefinitions(){
		return this._tokenDefinitionsList;
	}
	
	public HashMap<String, String> getTokenDefinitionsMap(){
		return this._tokenDefinitionsMap;
	}
	
	public Set<String> getAllNonTerminals(){
		Set<String> resultGrammarNonTerminalNodes 
		= new HashSet<String>();
		
		if(this._productionsIndexMap == null){
			return resultGrammarNonTerminalNodes;
		}
		
		for(Integer index : this._productionsIndexMap.keySet()){
			ITreeNode productionNode = this._productionsIndexMap.get(index);
			resultGrammarNonTerminalNodes.add(productionNode.GetLeftNode().GetValue());
		}
		
		return resultGrammarNonTerminalNodes;
	}
	
	public Set<String> getAllTerminals(){
		return this._tokenDefinitionsMap.keySet();
	}
	
	/**
	 * Returns the ith production defined in the context-free-grammar
	 * @param i - index of the production
	 * @return - root node of the production
	 */
	public ITreeNode getProduction(int i){
		return this._productionsIndexMap.get(i);
	}
	
	public int getTotalNumberOfProductions(){
		if(this._productionsIndexMap == null){
			return 0;
		}
		
		return this._productionsIndexMap.size();
	}
	
	public ITreeNode getNonTerminalStartingProduction(int i){
		return this._productionsIndexMap.get(i).GetLeftNode();
	}
	
	public List<ITreeNode> getAllRightHandSymbolsOfProduction(int i){
		List<ITreeNode> inOrderTraversalList = new ArrayList<ITreeNode>();
		ITreeNode productionNode = this._productionsIndexMap.get(i);
		performInOrderTraversal(productionNode.GetRightNode(), inOrderTraversalList);
		return inOrderTraversalList;
	}
	
	private void performInOrderTraversal(ITreeNode startNode, List<ITreeNode> nodeContainer){
		
		if(startNode == null){
			return;
		}
		
		performInOrderTraversal(startNode.GetLeftNode(), nodeContainer);
		if(startNode instanceof GrammarNonTerminalNode 
				|| startNode instanceof GrammarTerminalNode){
			nodeContainer.add(startNode);
		}
		performInOrderTraversal(startNode.GetRightNode(), nodeContainer);
		
	}
	
	/**
	 * This method checks whether the given symbol in the context-free grammar 
	 * has a null production or not, i.e, whether a production 'symbol -> ɛ' exists.
	 * @param symbol - A string representing the symbol used in the context-free grammar
	 * @return - True if the symbol has a null production, False if not. If the symbol 
	 * is not present in the context-free grammar, null is returned
	 */
	public Boolean HasNullProduction(String symbol){
		Boolean result = null;
		boolean isValidSymbol = false;
		if(symbol == null){
			return result;
		}
		
		Set<Integer> productionIndices = this._productionsIndexMap.keySet();
		for(Integer index : productionIndices){
			ITreeNode productionNode = this._productionsIndexMap.get(index);
			
			ITreeNode leftHandOfProduction = productionNode.GetLeftNode();
			ITreeNode rightHandOfProduction = productionNode.GetRightNode();
			
			if(leftHandOfProduction!=null){
				String leftHandNonTerminalString = leftHandOfProduction.GetValue();
				boolean givenSymbolStartsProduction 
					= leftHandNonTerminalString.equals(symbol);
				if(givenSymbolStartsProduction){
					isValidSymbol = true;
				}
				if(givenSymbolStartsProduction && 
						rightHandOfProduction == null){
					result = true;
					return result;
				}
			}
			
		}
		
		//In the above for loop, it has already been checked whether
		//the given symbol is a valid non-terminal. We just have to 
		//check whether it a valid terminal or not
		if(!isValidSymbol){
			isValidSymbol = this._terminals.contains(symbol);
		}
		
		return isValidSymbol?false:null;
	}
	
	public Map<String, Set<String>> computeFirstSets() {
		// TODO Auto-generated method stub
		Map<String, Set<String>> resultMap = new HashMap<String, Set<String>>();
		
		//Initialize the empty sets for non-terminals
		//Initialize the first sets for terminals. Every terminal has only one element
		//in its first set.
		for(String symbol : this._nonTerminals){
			resultMap.put(symbol, new HashSet<String>());
		}
		
		for(String symbol : this._terminals){
			Set<String> set = new HashSet<String>();
			set.add(symbol);
			resultMap.put(symbol, set);
		}
		
		boolean haveSetsConverged = false;
		while(!haveSetsConverged){
			haveSetsConverged = true;
			for(Integer index : this._productionsIndexMap.keySet()){
				ITreeNode productionNode = this._productionsIndexMap.get(index);
				String leftHandNonTerminal = productionNode.GetLeftNode().GetValue();
				List<ITreeNode> rightHandSymbols = getAllRightHandSymbolsOfProduction(index);
				Boolean sequenceNullableTillNow = true;
				for(int i=0;i<rightHandSymbols.size();i++){
					if(sequenceNullableTillNow){
						ITreeNode symbolOnRightHandSide = rightHandSymbols.get(i);
						String symbolOnRightHandSideString 
						= symbolOnRightHandSide.GetValue();
						Set<String> firstSet = resultMap.get(symbolOnRightHandSideString);
						haveSetsConverged = haveSetsConverged && 
								!resultMap.get(leftHandNonTerminal).addAll(firstSet);
						sequenceNullableTillNow = IsNullable(symbolOnRightHandSideString);
					}else{
						break;
					}
				}
			}
		}
		
		for(String symbol : resultMap.keySet()){
			if(this._nullableSymbolInfo.get(symbol)){
				resultMap.get(symbol).add(""); // add epsilon terminal
			}
		}
		return resultMap;
	}
	
	public Boolean IsNullable(String symbol){
		Boolean result = null;
		if(symbol == null 
				|| (this._nullableSymbolInfo!=null 
				&& !this._nullableSymbolInfo.keySet().contains(symbol))
				|| this._nullableSymbolInfo == null){
			return result;
		}
		
		return this._nullableSymbolInfo.get(symbol);
	}
	
	public Map<String, Boolean> computeNullableSymbols(){
		Set<String> nullableSymbolSet = new HashSet<String>();
		
		//Initialize the nullableSymbolSet with symbols which have nullable productions
		for(String symbolString : this._nonTerminals){
			Boolean hasNullProductionBoolean = HasNullProduction(symbolString);
			if(hasNullProductionBoolean!=null && hasNullProductionBoolean){
				nullableSymbolSet.add(symbolString);
			}
		}
		
		boolean hasSetConverged = false;
		while(!hasSetConverged){
			hasSetConverged = true;
			
			//loop through every production
			for(Integer index : this._productionsIndexMap.keySet()){
				ITreeNode productionNode = this._productionsIndexMap.get(index);
				String leftHandNonTerminal = productionNode.GetLeftNode().GetValue();
				List<ITreeNode> rightHandSymbols = getAllRightHandSymbolsOfProduction(index);
				
				boolean isRightHandSideNullable = true;
				for(int i=0;i<rightHandSymbols.size();i++){
					String rightHandSymbolString = rightHandSymbols.get(i).GetValue();
					if(!nullableSymbolSet.contains(rightHandSymbolString)){
						isRightHandSideNullable = false;
						break;
					}
				}
				
				if(isRightHandSideNullable){
					hasSetConverged = hasSetConverged 
							&& !nullableSymbolSet.add(leftHandNonTerminal);
				}
				
			}
		}
		
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		
		for(String symbol : this._nonTerminals){
			if(nullableSymbolSet.contains(symbol)){
				resultMap.put(symbol, true);
			}else{
				resultMap.put(symbol, false);
			}
		}
		
		for(String symbol : this._terminals){
			resultMap.put(symbol, false);
		}
		
		return resultMap;
		
	}
	
	public Map<String, Set<String>> computeFollowSets() {
		// TODO Auto-generated method stub
		Map<String, Set<String>> resultMap = new HashMap<String, Set<String>>();
		
		//Initialize the empty sets for non-terminals
		//Initialize the first sets for terminals. Every terminal has only one element
		//in its first set.
		for(String symbol : this._nonTerminals){
			if(this._startSymbol.equals(symbol)){
				Set<String> set = new HashSet<String>();
				set.add("$");
				resultMap.put(symbol, set);
			}else{
				resultMap.put(symbol, new HashSet<String>());
			}
		}
		
		for(String symbol : this._terminals){
			Set<String> set = new HashSet<String>();
			//set.add(symbol);
			resultMap.put(symbol, set);
		}
		
		boolean haveSetsConverged = false;
		while(!haveSetsConverged){
			haveSetsConverged = true;
			for(Integer index : this._productionsIndexMap.keySet()){
				ITreeNode productionNode = this._productionsIndexMap.get(index);
				String leftHandNonTerminal = productionNode.GetLeftNode().GetValue();
				List<ITreeNode> rightHandSymbols = getAllRightHandSymbolsOfProduction(index);
				Boolean sequenceNullableTillNow = true;
				for(int i=rightHandSymbols.size()-1;i>=0;i--){
					if(sequenceNullableTillNow){
						ITreeNode symbolOnRightHandSide = rightHandSymbols.get(i);
						String symbolOnRightHandSideString 
						= symbolOnRightHandSide.GetValue();
						Set<String> followSet = resultMap.get(symbolOnRightHandSideString);
						haveSetsConverged = haveSetsConverged && 
								!followSet.addAll(resultMap.get(leftHandNonTerminal));
						sequenceNullableTillNow = IsNullable(symbolOnRightHandSideString);
					}else{
						break;
					}
				}
				
				for(int i=0;i<rightHandSymbols.size();i++){
					for(int j=i+1;j<rightHandSymbols.size();j++){
						//if all the symbols from i+1 to j-1 are nullable then
						boolean seqIsNullable = true;
						for(int k = i+1;k<j;k++){
							seqIsNullable = seqIsNullable && IsNullable(rightHandSymbols.get(k).GetValue());
						}
						if(seqIsNullable){
							Set<String> set 
							= new HashSet<String>(this._firstSets.get(rightHandSymbols.get(j).GetValue()));
							set.remove("");
							haveSetsConverged = haveSetsConverged && 
									!resultMap.get(rightHandSymbols.get(i).GetValue())
							.addAll(set);
						}
					}
				}
			}
		}
		
		resultMap.keySet().removeAll(_terminals);
		return resultMap;
	}
	
	public Set<String> getFirstSet(String symbol){
		return this._firstSets.get(symbol);
	}
	
	public List<TokenDefinition> getFollowSet(String symbol){
		return null;
	}
}
