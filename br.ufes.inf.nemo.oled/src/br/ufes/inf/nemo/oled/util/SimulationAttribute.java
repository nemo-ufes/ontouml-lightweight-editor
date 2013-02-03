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