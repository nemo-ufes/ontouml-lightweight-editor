package graph;

public class EdgeStereotypeLegend {
	private String stereotype;
	private String imagePath;
	private String style;
	
	public EdgeStereotypeLegend(String stereotype, String imagePath, String style) {
		this.stereotype = stereotype;
		this.imagePath = imagePath;
		this.style = style;
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
