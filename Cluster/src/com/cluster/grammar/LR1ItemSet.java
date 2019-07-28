package com.cluster.grammar;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.cluster.grammarloader.GrammarLoader;

public class LR1ItemSet {
	
	private HashSet<LR1Item> _items;

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
		
		LR1Item item = new LR1Item(loader.getGrammar(), 0, 0, "PLUS");
		LR1Item item1 = new LR1Item(grammar, 0, 0, "PLUS");
		LR1Item item2 = new LR1Item(grammar, 0, 0, "INT");
		
		LR1ItemSet itemSet1 = new LR1ItemSet();
		LR1ItemSet itemSet2 = new LR1ItemSet();
		itemSet1.addItem(item);
		itemSet2.addItem(item1);
		
		System.out.println(itemSet1.isASubSetOf(itemSet2));
		System.out.println(itemSet1.isASuperSetOf(itemSet2));
	}
	
	public LR1ItemSet(){
		this._items = new HashSet<LR1Item>();
	}
	
	public LR1ItemSet(LR1ItemSet itemSet){
		this._items = new HashSet<LR1Item>(itemSet._items);
	}
	
	public void computeClosure(){
		HashSet<LR1Item> resultSet = new HashSet<LR1Item>();
		
		while(this._items.size()>0){
			LR1Item item = this.getAnItem();
			Set<LR1Item> itemExpansions = item.getItemExpansions();
			while(itemExpansions.remove(item)){}
			resultSet.add(item);
			// remove the items which have already been expanded
			itemExpansions.removeAll(resultSet); 
			this._items.addAll(itemExpansions);
		}
		
		this._items = resultSet;
	}
	
	public LR1Item getAnItem(){
		
		for(Iterator<LR1Item> iterator = this._items.iterator();iterator.hasNext();){
			LR1Item itm = iterator.next();
			iterator.remove();
			return itm;
		}
		
		return null;
	}
	
	public boolean isASubSetOf(LR1ItemSet lr1itemset){
		return lr1itemset._items.containsAll(this._items);
	}
	
	public boolean isASuperSetOf(LR1ItemSet lr1itemset){
		return this._items.containsAll(lr1itemset._items);
	}
	
	public boolean addItem(LR1Item item){
		return this._items.add(item);
	}
	
	public boolean removeItem(LR1Item item){
		return this._items.remove(item);
	}
	
	public boolean addItemSet(LR1ItemSet lr1itemset){
		boolean result = false;
		for(LR1Item item : lr1itemset._items){
			result = result || this._items.add(item);
		}
		return result;
	}
	
	public boolean removeItemSet(LR1ItemSet lr1itemset){
		boolean result = false;
		for(LR1Item item : lr1itemset._items){
			result = result || this._items.remove(item);
		}
		
		return result;
	}
	
	public boolean contains(LR1Item item){
		return this._items.contains(item);
	}

	public HashSet<LR1Item> getAllItems(){
		return this._items;
	}
	
	public int getSize(){
		return this._items.size();
	}
	
	@Override
	public String toString(){
		return this._items.toString();
	}
	
	@Override
	public int hashCode(){
		int hashcode = 0;
		for(LR1Item item: this._items){
			hashcode+=item.hashCode();
		}
		return hashcode;
	}
	
}
