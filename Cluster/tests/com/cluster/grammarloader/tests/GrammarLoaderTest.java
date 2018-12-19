package com.cluster.grammarloader.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cluster.grammar.Grammar;
import com.cluster.grammar.exceptions.IncorrectGrammarFileNameException;
import com.cluster.grammarloader.GrammarLoader;

public class GrammarLoaderTest {
	
	private GrammarLoader _loader;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void doesConstructorWithStringArgumentWork(){
		try {
			this._loader = new GrammarLoader(".\\tests\\grammars\\grammar1.clg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectGrammarFileNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(this._loader!=null, true);
	}
	
	@Test(expected=NoSuchFileException.class)
	public void incorrectGrammarPathThrowsException() throws Exception{
		this._loader = new GrammarLoader(".\\tests\\grammars\\bsbsj.clg");
	}
	
	@Test(expected=IncorrectGrammarFileNameException.class)
	public void incorrectGrammarFileNameThrowsException1() throws Exception{
		this._loader = new GrammarLoader(".\\tests\\grammars\\grammar1.txt");
	}
	
	@Test(expected=IncorrectGrammarFileNameException.class)
	public void incorrectGrammarFileNameThrowsException2() throws Exception{
		this._loader = new GrammarLoader(".\\tests\\grammars\\grammar1.nvdj");
	}
	
	@Test(expected=IncorrectGrammarFileNameException.class)
	public void incorrectGrammarFileNameThrowsException3() throws Exception{
		this._loader = new GrammarLoader(".\\tests\\grammars\\grammar1");
	}

}
