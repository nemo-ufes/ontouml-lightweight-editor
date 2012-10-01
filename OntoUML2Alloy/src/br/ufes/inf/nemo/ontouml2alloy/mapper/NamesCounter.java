package br.ufes.inf.nemo.ontouml2alloy.mapper;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OntoUML2Alloy (OntoUML to Alloy Transformation).
 *
 * OntoUML2Alloy is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OntoUML2Alloy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OntoUML2Alloy; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

/**
 *	This class is used as a name counter for names in the model. 
 *
 * 	@author John Guerson 
 *  @author Tiago Sales 
 *  @author Lucas Thom
 */

public class NamesCounter {
	
	private String name;
	
	private int counter;
	
	public NamesCounter(String name, int counter) 
	{
		super();
		this.name = name;
		this.counter = counter;
	}
	
	public String getWord() 
	{
		return name;
	}
	
	public void setWord(String name) 
	{
		this.name = name;
	}
	
	public int getCounter() 
	{
		return counter;
	}
	
	public void setCounter(int counter) 
	{
		this.counter = counter;
	}		
}
