package com.cluster.parser.trees;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ParseTreeNodeFactory {
	
	private ParseTreeNodeFactory(){
		
	}

	public static IParseTreeNode getNodeForTerminal(String terminal){
		try {
			Class<?> clazz = 
					Class.forName("com.cluster.parser.trees.generated."
							+ "ParseTree_"+terminal+"_TNode");
			Constructor<?> ctor = clazz.getConstructor(String.class, List.class);
			IParseTreeNode node = (IParseTreeNode) ctor.newInstance(new Object[] {terminal, new ArrayList<IParseTreeNode>() });
			return node;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
	public static IParseTreeNode getNodeForNonTerminal(String nonterminal){
		try {
			Class<?> clazz = 
					Class.forName("com.cluster.parser.trees.generated."
							+ "ParseTree_"+nonterminal+"_NTNode");
			Constructor<?> ctor = clazz.getConstructor(String.class, List.class);
			IParseTreeNode node = (IParseTreeNode) ctor.newInstance(new Object[] {nonterminal, new ArrayList<IParseTreeNode>() });
			return node;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
	
}
