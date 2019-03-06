package com.cluster.parser.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

import com.cluster.grammar.Grammar;
import com.cluster.grammarloader.GrammarLoader;
import com.cluster.parser.trees.IParseTreeNode;

public class ParseTreeHelperSourcesGenerator implements IGenerator {
	
	private Grammar _grammar;
	
	public ParseTreeHelperSourcesGenerator(Grammar grammar){
		this._grammar = grammar;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GrammarLoader loader = null;
		try {
			loader = new GrammarLoader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ParseTreeHelperSourcesGenerator generator = 
				new ParseTreeHelperSourcesGenerator(loader.getGrammar());
		generator.generateParseTreeNodeClasses();
		generator.generateVisitorInterface();
		generator.generateParseTreeWalkerAbstractClass();
		
		/*
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		File file = 
				new File(".\\src\\com\\cluster\\parser\\trees\\generated","ParseTree_S_NTNode.java");
		
		compiler.run(null, null, null, file.getAbsolutePath());
		
		try {
			ClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()});
			Class test = loader.loadClass("com.cluster.parser.trees.generated.ParseTree_S_NTNode");
			Class[] constructorArgs = new Class[2];
			constructorArgs[0] = String.class;
			constructorArgs[1] = List.class;
			IParseTreeNode node = 
					(IParseTreeNode) test.getDeclaredConstructor(constructorArgs).newInstance("testData", new ArrayList<IParseTreeNode>());
			System.out.println(node.getID()+" "+node.getValue());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

	@Override
	public void generateParseTreeNodeClasses() {
		// TODO Auto-generated method stub
		String packagePath = ".\\src\\com\\cluster\\parser\\trees\\generated";
		
		//Set<String> nonterminals = this._grammar.getAllNonTerminals();
		
		Set<String> nonterminals = this._grammar.getAllNonTerminals();
		
		Set<String> terminals = this._grammar.getAllTerminals();
		
		for(String nonterminal: nonterminals){
			try {
				generateNonTerminalClassFile(nonterminal, packagePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(String terminal: terminals){
			try {
				generateTerminalClassFile(terminal, packagePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void generateNonTerminalClassFile(String nonterminal, String packagePath) throws IOException {
		// TODO Auto-generated method stub
		List<String> javaFileAsListOfStrings = new ArrayList<String>();
		javaFileAsListOfStrings.add("package com.cluster.parser.trees.generated;");
		
		List<String> javaFileTemplate = 
				Files.readAllLines(Paths.get(".\\src\\com\\cluster\\parser\\trees"
						+ "\\templates\\ParseTreeNonTerminalNodeTemplate.txt"));
		
		javaFileAsListOfStrings.addAll(javaFileTemplate);
		
		File file = 
				new File(packagePath,"ParseTree_"+nonterminal+"_NTNode.java");
		File parentDirectory = new File(packagePath);
		String[] existingFiles = parentDirectory.list();
		FileWriter writer;
		
		boolean doesFileExist = false;
		for(String fileName: existingFiles){
			if(fileName.equals("ParseTree_"+nonterminal+"_NTNode.java")){
				doesFileExist = true;
				break;
			}
		}
		
		if(doesFileExist){
			writer = new FileWriter(file, false);
		}else{
			file.getParentFile().mkdirs();
			System.out.println(file.getAbsolutePath());
			file.createNewFile();
			writer = new FileWriter(file);
		}
		
		for(String javaLine: javaFileAsListOfStrings){
			javaLine = javaLine.replaceAll("%nonterminal%", nonterminal);
			writer.write(javaLine+"\n");
		}
		
		writer.close();
		
	}
	
	private void generateTerminalClassFile(String terminal, String packagePath) throws IOException {
		// TODO Auto-generated method stub
		List<String> javaFileAsListOfStrings = new ArrayList<String>();
		javaFileAsListOfStrings.add("package com.cluster.parser.trees.generated;");
		
		List<String> javaFileTemplate = 
				Files.readAllLines(Paths.get(".\\src\\com\\cluster\\parser\\trees"
						+ "\\templates\\ParseTreeTerminalNodeTemplate.txt"));
		
		javaFileAsListOfStrings.addAll(javaFileTemplate);
		
		File file = 
				new File(packagePath,"ParseTree_"+terminal+"_TNode.java");
		File parentDirectory = new File(packagePath);
		String[] existingFiles = parentDirectory.list();
		FileWriter writer;
		
		boolean doesFileExist = false;
		for(String fileName: existingFiles){
			if(fileName.equals("ParseTree_"+terminal+"_TNode.java")){
				doesFileExist = true;
				break;
			}
		}
		
		if(doesFileExist){
			writer = new FileWriter(file, false);
		}else{
			file.getParentFile().mkdirs();
			System.out.println(file.getAbsolutePath());
			file.createNewFile();
			writer = new FileWriter(file);
		}
		
		for(String javaLine: javaFileAsListOfStrings){
			javaLine = javaLine.replaceAll("%terminal%", terminal);
			writer.write(javaLine+"\n");
		}
		
		writer.close();
		
	}

	@Override
	public void generateVisitorInterface() {
		// TODO Auto-generated method stub
		String packagePath = ".\\src\\com\\cluster\\parser\\trees\\generated";
		
		List<String> javaFileAsListOfStrings = new ArrayList<String>();
		//javaFileAsListOfStrings.add("package com.cluster.parser.trees.generated;");
		
		List<String> javaFileTemplate = null;
		try {
			javaFileTemplate = Files.readAllLines(Paths.get(".\\src\\com\\cluster\\parser\\trees"
					+ "\\templates\\ParseTreeVisitorInterfaceTemplate.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		javaFileAsListOfStrings.addAll(javaFileTemplate);
		
		int codeInsertionIndex = 0;
		String statementTemplate = "";
		for(int i=0;i<javaFileAsListOfStrings.size();i++){
			if(javaFileAsListOfStrings.get(i).contains("{REPEATSTMT}")){
				codeInsertionIndex = i;
				statementTemplate = 
						javaFileAsListOfStrings.remove(codeInsertionIndex);
				break;
			}
		}
		
		statementTemplate = 
				statementTemplate.substring(statementTemplate.indexOf(':')+1);
		
		File parentDirectory = new File(packagePath);
		String[] existingFiles = parentDirectory.list();
		
		for(String fileName: existingFiles){
			if(fileName.contains("NTNode.java") || fileName.contains("TNode.java")){
				String nodeType = fileName.substring(0,fileName.length()-5); //remove .java extension
				String interfaceMethodDeclaration = 
						statementTemplate.replaceAll("%ParseTreeNodeType%", nodeType);
				javaFileAsListOfStrings.add(codeInsertionIndex, interfaceMethodDeclaration);
			}
		}
		
		File file = 
				new File(packagePath,"IParseTreeVisitor.java");
		FileWriter writer = null;
		
		if(file.exists()){
			try {
				writer = new FileWriter(file, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			file.getParentFile().mkdirs();
			System.out.println(file.getAbsolutePath());
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer = new FileWriter(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(String javaLine: javaFileAsListOfStrings){
			try {
				writer.write(javaLine+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void generateParseTreeWalkerAbstractClass() {
		// TODO Auto-generated method stub
		String packagePath = ".\\src\\com\\cluster\\parser\\trees\\generated";
		
		Set<String> nonterminals = this._grammar.getAllNonTerminals();
		
		Set<String> terminals = this._grammar.getAllTerminals();
		
		List<String> javaFileAsListOfStrings = new ArrayList<String>();
		//javaFileAsListOfStrings.add("package com.cluster.parser.trees.generated;");
		
		List<String> javaFileTemplate = null;
		try {
			javaFileTemplate = Files.readAllLines(Paths.get(".\\src\\com\\cluster\\parser\\trees"
					+ "\\templates\\ParseTreeBasicWalkerTemplate.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		javaFileAsListOfStrings.addAll(javaFileTemplate);
		
		boolean repeatBlocksFinished = false;
		
		while(!repeatBlocksFinished){
			int repeatBlockStart = 0;
			int repeatBlockEnd = 0;
			for(int i=0;i<javaFileAsListOfStrings.size();i++){
				if(javaFileAsListOfStrings.get(i).contains("{REPEATSTMT}")){
					repeatBlockStart = i;
				}
				
				if(javaFileAsListOfStrings.get(i).contains("{ENDREPEATSTMT}")){
					repeatBlockEnd = i;
					break;
				}
			}
			
			if(repeatBlockEnd == 0){ // means no repeat block is found
				break;
			}
			
			String statement = "";
			
			javaFileAsListOfStrings.remove(repeatBlockEnd);
			for(int i=repeatBlockEnd-1;i>repeatBlockStart;i--){
				statement=javaFileAsListOfStrings.remove(i)+"\n"+statement;
			}
			javaFileAsListOfStrings.remove(repeatBlockStart);
			ArrayList<String> expandedStatements = new ArrayList<String>();
			if(statement.contains("%nonterminal%")){
				
				for(String nonterminal : nonterminals){
					expandedStatements.add(statement.replaceAll("%nonterminal%", nonterminal));
				}
				javaFileAsListOfStrings.addAll(repeatBlockStart, expandedStatements);
			}
			
			if(statement.contains("%terminal%")){
				
				for(String terminal : terminals){
					expandedStatements.add(statement.replaceAll("%terminal%", terminal));
				}
				javaFileAsListOfStrings.addAll(repeatBlockStart, expandedStatements);
			}
			
		}
		
		File file = 
				new File(packagePath,"ParseTreeBasicWalker.java");
		FileWriter writer = null;
		
		if(file.exists()){
			try {
				writer = new FileWriter(file, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			file.getParentFile().mkdirs();
			System.out.println(file.getAbsolutePath());
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer = new FileWriter(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(String javaLine : javaFileAsListOfStrings){
			try {
				writer.write(javaLine+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
