package com.cluster.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.cluster.parser.trees.IParseTreeNode;

public class TerminalElement implements IStackElement{

	private String _data;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public TerminalElement(String data){
		this._data = data;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return this._data;
	}

}
