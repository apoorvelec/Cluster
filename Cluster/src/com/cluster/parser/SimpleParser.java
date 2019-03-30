package com.cluster.parser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.cluster.grammar.Element;
import com.cluster.grammar.Grammar;
import com.cluster.lexer.LongestMatchLexer;
import com.cluster.parser.trees.IParseTreeNode;
import com.cluster.parser.trees.ParseTreeNodeFactory;
import com.cluster.parser.trees.generated.ParseTreeBasicWalker;
import com.cluster.parser.trees.generated.ParseTree_EP_NTNode;
import com.cluster.parser.trees.generated.ParseTree_E_NTNode;
import com.cluster.parser.trees.generated.ParseTree_FSLASH_TNode;
import com.cluster.parser.trees.generated.ParseTree_F_NTNode;
import com.cluster.parser.trees.generated.ParseTree_INT_TNode;
import com.cluster.parser.trees.generated.ParseTree_LPAREN_TNode;
import com.cluster.parser.trees.generated.ParseTree_MINUS_TNode;
import com.cluster.parser.trees.generated.ParseTree_MULT_TNode;
import com.cluster.parser.trees.generated.ParseTree_PLUS_TNode;
import com.cluster.parser.trees.generated.ParseTree_RPAREN_TNode;
import com.cluster.parser.trees.generated.ParseTree_TP_NTNode;
import com.cluster.parser.trees.generated.ParseTree_T_NTNode;
import com.cluster.tokens.Token;
import com.owl.trees.ITreeNode;

public class SimpleParser implements IParser{
	
	private ArrayDeque<String> _stack;
	
	private List<Token> _tokens;
	private Grammar _grammar;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputProgram = "1+1";
		String grammarFilePath = ".\\tests\\grammars\\grammar20.clg";
		//LongestMatchLexer lexer = new LongestMatchLexer(inputProgram, grammarFilePath);
		LongestMatchLexer lexer = new LongestMatchLexer(inputProgram);
		List<Token> tokensList = new ArrayList<Token>();
		
		Token token = lexer.getNextToken();
		while(token!=null){
			tokensList.add(0, token);
			System.out.println(token);
			token = lexer.getNextToken();
		}
		
		SimpleParser parser = new SimpleParser(tokensList, lexer.getGrammarLoader().getGrammar());
		//System.out.println(lexer.getGrammarLoader().getGrammar().getParseTable());
		IParseTreeNode root = parser.parse();
		ParseTreeBasicWalker<Object, Object> walker = new ParseTreeBasicWalker<Object, Object>() {

			@Override
			public <P> void enterT(ParseTree_T_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void exitT(ParseTree_T_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void enterE(ParseTree_E_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void exitE(ParseTree_E_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void enterF(ParseTree_F_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void exitF(ParseTree_F_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void enterEP(ParseTree_EP_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void exitEP(ParseTree_EP_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void enterTP(ParseTree_TP_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void exitTP(ParseTree_TP_NTNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void visitPLUS(ParseTree_PLUS_TNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void visitMULT(ParseTree_MULT_TNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void visitLPAREN(ParseTree_LPAREN_TNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void visitRPAREN(ParseTree_RPAREN_TNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void visitINT(ParseTree_INT_TNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void visitMINUS(ParseTree_MINUS_TNode node, P p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public <P> void visitFSLASH(ParseTree_FSLASH_TNode node, P p) {
				// TODO Auto-generated method stub
				
			}


		};
		
		((ParseTree_E_NTNode)root).accept(walker, null);
		System.out.println(root.getID()+" "+root.getValue());
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
		ArrayDeque<IParseTreeNode> parseTreeNodeStack = new ArrayDeque<IParseTreeNode>();
		IParseTreeNode root = ParseTreeNodeFactory.getNodeForNonTerminal(this._grammar.getStartSymbol());
		parseTreeNodeStack.push(root);
		this._stack.push(this._grammar.getStartSymbol());
		Token token = consumeToken();
		
		while(!(this._stack.size() == 0 && this._tokens.size() == 0)){
			String stackElement = this._stack.peek();
			
			if(this._grammar.isTerminal(stackElement)){
				//check if this matches the current token
				if(token.getTokenType().equals(stackElement)){
					this._stack.pop();
					parseTreeNodeStack.pop();
					token = consumeToken();
				}
			}else{
				Element actionElement = null;
				if(token!=null){
					actionElement = 
							this._grammar.getParseTable()
							.getElement(stackElement, token.getTokenType());
				}else{
					actionElement = 
							this._grammar.getParseTable()
							.getElement(stackElement, "$");
				}
				if(actionElement.Action == null || actionElement.StateNumberOrProductionNumber == null){
					try {
						throw new Exception();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				}
				if(actionElement!=null && actionElement.StateNumberOrProductionNumber!=null){
					List<ITreeNode> rightHandSymbols = 
							this._grammar.getAllRightHandSymbolsOfProduction(actionElement.StateNumberOrProductionNumber);
					List<String> rightHandSymbolsAsStrings = new ArrayList<String>();
					for(ITreeNode node : rightHandSymbols){
						rightHandSymbolsAsStrings.add(node.GetValue());
					}
					
					this._stack.pop();
					IParseTreeNode node = parseTreeNodeStack.pop();
					
					for(int i=rightHandSymbolsAsStrings.size()-1;i>=0;i--){
						this._stack.push(rightHandSymbolsAsStrings.get(i));
						
						if(this._grammar.isTerminal(rightHandSymbolsAsStrings.get(i))){
							IParseTreeNode terminalChild = ParseTreeNodeFactory.getNodeForTerminal(rightHandSymbolsAsStrings.get(i));
							node.addChildNode(terminalChild);
							parseTreeNodeStack.push(terminalChild);
						}else{
							IParseTreeNode nonterminalChild = ParseTreeNodeFactory.getNodeForNonTerminal(rightHandSymbolsAsStrings.get(i));
							node.addChildNode(nonterminalChild);
							parseTreeNodeStack.push(nonterminalChild);
						}
					}
				}
			}
		}
		
		System.out.println("true");
		
		return root;
	}

}
