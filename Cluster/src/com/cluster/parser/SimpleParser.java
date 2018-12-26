package com.cluster.parser;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.cluster.grammar.Element;
import com.cluster.grammar.Grammar;
import com.cluster.grammar.GrammarParseTable;
import com.cluster.grammar.exceptions.IncorrectGrammarFileNameException;
import com.cluster.grammarloader.GrammarLoader;
import com.cluster.lexer.LongestMatchLexer;
import com.cluster.parser.trees.IParseTreeNode;
import com.cluster.tokens.Token;
import com.owl.trees.ITreeNode;

public class SimpleParser implements IParser{
	
	private ArrayDeque<String> _stack;
	
	private List<Token> _tokens;
	private Grammar _grammar;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputProgram = "token3token5token7token6token4token0token5token7token6token1token7token2";
		String grammarFilePath = ".\\tests\\grammars\\grammar20.clg";
		LongestMatchLexer lexer = new LongestMatchLexer(inputProgram, grammarFilePath);
		List<Token> tokensList = new ArrayList<Token>();
		
		Token token = lexer.getNextToken();
		while(token!=null){
			tokensList.add(0, token);
			System.out.println(token);
			token = lexer.getNextToken();
		}
		
		SimpleParser parser = new SimpleParser(tokensList, lexer.getGrammarLoader().getGrammar());
		
		parser.parse();
		
	}
	
	public SimpleParser(List<Token> tokens, Grammar grammar){
		this._tokens = tokens;
		this._grammar = grammar;
		this._stack = new ArrayDeque<String>();
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

	@Override
	public IParseTreeNode parse() {
		// TODO Auto-generated method stub
		this._stack.push(this._grammar.getStartSymbol());
		Token token = consumeToken();
		
		while(!(this._stack.size() == 0 && this._tokens.size() == 0)){
			String stackElement = this._stack.peek();
			
			if(this._grammar.isTerminal(stackElement)){
				//check if this matches the current token
				if(token.getTokenType().equals(stackElement)){
					this._stack.pop();
					token = consumeToken();
				}
			}else{
				Element actionElement = 
						this._grammar.getParseTable()
						.getElement(stackElement, token.getTokenType());
				
				if(actionElement!=null && actionElement.StateNumberOrProductionNumber!=null){
					List<ITreeNode> rightHandSymbols = 
							this._grammar.getAllRightHandSymbolsOfProduction(actionElement.StateNumberOrProductionNumber);
					List<String> rightHandSymbolsAsStrings = new ArrayList<String>();
					for(ITreeNode node : rightHandSymbols){
						rightHandSymbolsAsStrings.add(node.GetValue());
					}
					
					this._stack.pop();
					
					for(int i=rightHandSymbolsAsStrings.size()-1;i>=0;i--){
						this._stack.push(rightHandSymbolsAsStrings.get(i));
					}
				}
			}
		}
		
		System.out.println("true");
		
		return null;
	}

}
