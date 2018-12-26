package com.cluster.grammar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cluster.grammar.Element;
import com.cluster.grammar.exceptions.UnrecognizedParseTableIndex;

public class GrammarParseTable {

	private HashMap<String, HashMap<String, Element>> _internalDataStructureForHoldingMatrix;
	private Set<String> _rowIndices;
	private Set<String> _columnIndices;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrammarParseTable pt = 
				new GrammarParseTable(new HashSet<String>(Arrays.asList("S", "T")), 
						new HashSet<String>(Arrays.asList("S", "T")));
		System.out.println(pt);
	}
	
	public GrammarParseTable(Set<String> rowIndices, Set<String> columnIndices){
		this._rowIndices = rowIndices;
		this._columnIndices = columnIndices;
		this._internalDataStructureForHoldingMatrix = 
				new HashMap<String, HashMap<String, Element>>();
		
		for(String rowIndex : this._rowIndices){
			this._internalDataStructureForHoldingMatrix.put(rowIndex, 
					new HashMap<String, Element>());
			for(String columnIndex : this._columnIndices){
				HashMap<String, Element> map = 
						this._internalDataStructureForHoldingMatrix.get(rowIndex);
				map.put(columnIndex, new Element());
			}
		}
	}
	
	public Element getElement(String row, String column){
		if(!this._internalDataStructureForHoldingMatrix.containsKey(row)){
			throw new UnrecognizedParseTableIndex("The provided index:"+row+" is not recognized!");
		}
		
		if(!this._internalDataStructureForHoldingMatrix.get(row).containsKey(column)){
			throw new UnrecognizedParseTableIndex("The provided index:"+column+" is not recognized!");
		}
		
		return this._internalDataStructureForHoldingMatrix.get(row).get(column);
	}
	
	public boolean setElement(String row, String column, Element element){
		if(!this._rowIndices.contains(row)){
			throw new UnrecognizedParseTableIndex("The provided index:"+row+" is not recognized!");
		}
		
		if(!this._columnIndices.contains(column)){
			throw new UnrecognizedParseTableIndex("The provided index:"+column+" is not recognized!");
		}
		
		Element elemReference = 
				this._internalDataStructureForHoldingMatrix.get(row).get(column);
		
		if(elemReference.equals(element)){
			return false;
		}
		
		this._internalDataStructureForHoldingMatrix.get(row).put(column, element);
		
		return true;
	}
	
	@Override
	public String toString(){
		String[][] matrixForPrint = new String[this._rowIndices.size()+1][this._columnIndices.size()+1];
		matrixForPrint[0][0] = "";
		
		int index = 1;
		for(String column : this._columnIndices){
			matrixForPrint[0][index] = column;
			index++;
		}
		
		index = 1;
		for(String row : this._rowIndices){
			matrixForPrint[index][0] = row;
			index++;
		}
		
		for(int i=1;i<matrixForPrint.length;i++){
			for(int j=1;j<matrixForPrint[i].length;j++){
				String rowIndex = matrixForPrint[i][0];
				String columnIndex = matrixForPrint[0][j];
				matrixForPrint[i][j] = this._internalDataStructureForHoldingMatrix
						.get(rowIndex).get(columnIndex).toString();
			}
		}
		
		String result = "";
		
		for(int i=0;i<matrixForPrint.length;i++){
			for(int j=0;j<matrixForPrint[i].length;j++){
				result+=String.format("%15s", matrixForPrint[i][j])+"|";
			}
			if(i==0){
				result+="\n";
				for(int j=0;j<matrixForPrint[i].length;j++){
					result+=String.format("%15s", "_______________")+"|";
				}
			}
			result+="\n";
		}
		//return this._internalDataStructureForHoldingMatrix.toString();
		return result;
	}
}
