package com.cluster.grammarparser;

import com.cluster.grammarparser.trees.GrammarArrowNode;
import com.cluster.grammarparser.trees.GrammarNonTerminalNode;
import com.cluster.grammarparser.trees.GrammarSemiColonNode;
import com.cluster.grammarparser.trees.GrammarSpaceNode;
import com.cluster.grammarparser.trees.GrammarTerminalNode;
import com.owl.trees.ITreeNode;
import com.owl.trees.draw.ITreePrinter;
import com.owl.trees.draw.WideLayoutPrinter;

public class GrammarParser {
	
	String _inputGrammar;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String input = "S->;S->T a ;";
		GrammarParser parser = new GrammarParser(input);
		ITreeNode root = parser.parse();
		ITreePrinter printer = new WideLayoutPrinter();
		printer.displayTree(root);
	}
	
	public GrammarParser(String inputGrammar){
		this._inputGrammar = inputGrammar;
	}
	
	private String eatNextCharacterInString(){
		String nextCharacter = this._inputGrammar.substring(0, 1);
		this._inputGrammar = this._inputGrammar.substring(1);
		return nextCharacter;
	}
	
	private String peekNextCharacterInString(){
		String nextCharacter = this._inputGrammar.substring(0, 1);
		return nextCharacter;
	}
	
	private boolean inputHasMoreUnseenCharacters(){
		boolean result = this._inputGrammar.length()>0;
		return result;
	}
	
	public ITreeNode parse(){
		if(this._inputGrammar == null || this._inputGrammar.equals("")){
			return null;
		}
		ITreeNode node = G();
		
		return node;
	}
	
	public ITreeNode G(){
		if(this._inputGrammar == null || this._inputGrammar.equals("")){
			return null;
		}
		
		ITreeNode pNode = P();
		this.eatNextCharacterInString(); //eats ;
		ITreeNode gNode = G();
		
		return new GrammarSemiColonNode(pNode, gNode);
	}
	
	public ITreeNode P(){
		ITreeNode nNode = N();
		this.eatNextCharacterInString(); // eats -
		this.eatNextCharacterInString(); // eats >
		ITreeNode fNode = F();
		
		return new GrammarArrowNode(nNode, fNode);
	}
	
	public ITreeNode F(){
		
		if(this._inputGrammar == null || this._inputGrammar.equals("")){
			return null;
		}
		
		String nextCharacter = this.peekNextCharacterInString();
		
		if(!Character.isLetter(nextCharacter.charAt(0))){
			return null;
		}
		
		if(nextCharacter.equals(nextCharacter.toUpperCase())){
			// Follow F->N F rule
			ITreeNode nNode = N();
			this.eatNextCharacterInString(); //eats space
			ITreeNode fNode = F();
			
			return new GrammarSpaceNode(nNode, fNode);
		}else{
			// Follow F->T F rule
			ITreeNode tNode = T();
			this.eatNextCharacterInString(); //eats space
			ITreeNode fNode = F();
			
			return new GrammarSpaceNode(tNode, fNode);
		}
	}
	
	public ITreeNode N(){
		int indexOfSpace = this._inputGrammar.indexOf(" ");
		int indexOfArrow = this._inputGrammar.indexOf("->");
		int index = 0;
		if(indexOfSpace!=-1 && indexOfArrow!=-1){
			index = Math.min(indexOfSpace, indexOfArrow);
		}else{
			if(indexOfSpace!=-1){
				index = indexOfSpace;
			}else if(indexOfArrow!=-1){
				index = indexOfArrow;
			}else{
				index = this._inputGrammar.length();
			}
		}
		String nonterminal = this._inputGrammar.substring(0, index);
		this._inputGrammar = this._inputGrammar.substring(index);
		return new GrammarNonTerminalNode(nonterminal);
	}
	
	public ITreeNode T(){
		int index = this._inputGrammar.indexOf(" ");
		String terminal = this._inputGrammar.substring(0, index);
		this._inputGrammar = this._inputGrammar.substring(index);
		return new GrammarTerminalNode(terminal);
	}

}
