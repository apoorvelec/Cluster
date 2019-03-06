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
import com.cluster.parser.trees.ParseTreeNodeFactory;
import com.cluster.parser.trees.generated.ParseTreeBasicWalker;
import com.cluster.parser.trees.generated.ParseTree_E_NTNode;
import com.cluster.parser.trees.generated.ParseTree_F_NTNode;
import com.cluster.parser.trees.generated.ParseTree_G_NTNode;
import com.cluster.parser.trees.generated.ParseTree_S_NTNode;
import com.cluster.parser.trees.generated.ParseTree_token0_TNode;
import com.cluster.parser.trees.generated.ParseTree_token1_TNode;
import com.cluster.parser.trees.generated.ParseTree_token2_TNode;
import com.cluster.parser.trees.generated.ParseTree_token3_TNode;
import com.cluster.parser.trees.generated.ParseTree_token4_TNode;
import com.cluster.parser.trees.generated.ParseTree_token5_TNode;
import com.cluster.parser.trees.generated.ParseTree_token6_TNode;
import com.cluster.parser.trees.generated.ParseTree_token7_TNode;
import com.cluster.parser.trees.generated.ParseTree_token8_TNode;
import com.cluster.parser.trees.generated.ParseTree_token9_TNode;
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
		
		IParseTreeNode root = parser.parse();
		ParseTreeBasicWalker<Object, Object> walker = new ParseTreeBasicWalker<Object, Object>() {

			@Override
			public <P> void enterS(ParseTree_S_NTNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Entering S ...");
			}

			@Override
			public <P> void exitS(ParseTree_S_NTNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Exiting S ...");
			}

			@Override
			public <P> void enterE(ParseTree_E_NTNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Entering E ...");
			}

			@Override
			public <P> void exitE(ParseTree_E_NTNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Exiting E ...");
			}

			@Override
			public <P> void enterF(ParseTree_F_NTNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Entering F ...");
			}

			@Override
			public <P> void exitF(ParseTree_F_NTNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Exiting F ...");
			}

			@Override
			public <P> void enterG(ParseTree_G_NTNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Entering G ...");
			}

			@Override
			public <P> void exitG(ParseTree_G_NTNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Exiting G ...");
			}

			@Override
			public <P> void visittoken0(ParseTree_token0_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token0 ...");
			}

			@Override
			public <P> void visittoken1(ParseTree_token1_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token1 ...");
			}

			@Override
			public <P> void visittoken2(ParseTree_token2_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token2 ...");
			}

			@Override
			public <P> void visittoken3(ParseTree_token3_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token3 ...");
			}

			@Override
			public <P> void visittoken4(ParseTree_token4_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token4 ...");
			}

			@Override
			public <P> void visittoken5(ParseTree_token5_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token5 ...");
			}

			@Override
			public <P> void visittoken6(ParseTree_token6_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token6 ...");
			}

			@Override
			public <P> void visittoken7(ParseTree_token7_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token7 ...");
			}

			@Override
			public <P> void visittoken8(ParseTree_token8_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token8 ...");
			}

			@Override
			public <P> void visittoken9(ParseTree_token9_TNode node, P p) {
				// TODO Auto-generated method stub
				System.out.println("Visiting token9 ...");
			}

		};
		
		((ParseTree_S_NTNode)root).accept(walker, null);
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
