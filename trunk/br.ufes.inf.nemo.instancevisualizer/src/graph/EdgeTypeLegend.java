package graph;

public class EdgeTypeLegend {
	private String type;
	private String imagePath;
	private String style;
	
	public EdgeTypeLegend(String type, String imagePath, String style) {
		this.type = type;
		this.imagePath = imagePath;
		this.style = style;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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