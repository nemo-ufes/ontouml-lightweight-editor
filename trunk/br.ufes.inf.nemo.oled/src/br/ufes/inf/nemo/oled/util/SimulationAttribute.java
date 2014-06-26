/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.util;

public enum SimulationAttribute {
	
	COLOR_INHERIT("Inherit"),
	COLOR_BLACK("Black"),
	COLOR_BLUE("Blue"),
	COLOR_RED("Red"),
	COLOR_YELLOW("Yellow"),
	COLOR_WHITE("White"),
	COLOR_GRAY("Gray"),
	COLOR_GREEN("Green"),
	
	SHAPE_INHERIT("Inherit"),
	SHAPE_ELLISPSE("Ellipse"),
	SHAPE_BOX("Box"),
	SHAPE_CIRCLE("Circle"),
	SHAPE_EGG("Egg"),
	SHAPE_TRIANGLE("Triangle"),
	SHAPE_DIAMOND("Diamond"),
	SHAPE_TRAPEZOID("Trapezoid"),
	SHAPE_PARALLELOGRAM("Parallelogram"),
	SHAPE_HOUSE("House"),
	SHAPE_HEXAGON("Hexagon"),
	SHAPE_OCTAGON("Octagon"),
	SHAPE_DOUBLECIRCLE("Dbl Circle"),
	SHAPE_DOUBLEOCTAGON("Dbl Octagon"),
	SHAPE_TRIPLEOCTAGON("Tpl Octagon"),
	SHAPE_INVTRIANGLE("Inv Triangle"),
	SHAPE_INVHOUSE("Inv House"),
	SHAPE_INVTRAPEZOID("Inv Trapezoid"),
	SHAPE_LINEDCIRCLE("Lined Circle"),
	SHAPE_LINEDDIAMOND("Lined Diamond"),
	SHAPE_LINEDSQUARE("Lined Square"),
	
	STYLE_INHERIT("Inherit"),
	STYLE_SOLID("Solid"),
	STYLE_DASHED("Dashed"),
	STYLE_DOTTED("Dotted"),
	STYLE_BOLD("Bold");
	
	private final String value;
	
	private SimulationAttribute(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
}