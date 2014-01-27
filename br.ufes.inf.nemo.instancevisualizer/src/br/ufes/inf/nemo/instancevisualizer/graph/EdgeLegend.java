package br.ufes.inf.nemo.instancevisualizer.graph;

public class EdgeLegend {
	private String type;
	private String stereotype;
	private String imagePath;
	private String style;
	
	public EdgeLegend(String type, String stereotype, String imagePath, String style) {
		this.type = type;
		this.stereotype = stereotype;
		this.imagePath = imagePath;
		this.style = style;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getStereotype() {
		return stereotype;
	}

	public void setStereotype(String stereotype) {
		this.stereotype = stereotype;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
}