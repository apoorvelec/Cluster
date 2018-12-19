package com.cluster.grammar.exceptions;

public class IncorrectGrammarFileNameException extends Exception{

	public IncorrectGrammarFileNameException(String message){
		super(message);
	}
	
	public IncorrectGrammarFileNameException(String message, Throwable cause){
		super(message, cause);
	}
	
}
