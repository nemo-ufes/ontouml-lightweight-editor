package br.ufes.inf.nemo.instancevisualizer.graph;

import java.io.Serializable;

public class NodeLegend implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9019940159565305668L;
	private String type;
	private String stereotype;
	private String prefix;
    //private String style;
    private Integer amount;
    
    private String fillMode = "image-scaled";
    private String fillImage;
    private String iconMode = "none";
    private String icon = "";
    private String padding = "0px";
    private String shape = "circle";
    private String size;
    private String sizeMode = "normal";
    private String strokeMode = "none";
    private String strokeColor = "black";
    private String strokeWidth = "1px";
    private String textMode = "normal";
    private String textColor = "black";
    private String textStyle = "normal";
    private String textSize = "10";
    private String textAlignment = "under";
    private String textVisibilityMode = "under-zoom";
    private String textVisibility = "0.5";
    private String textBackgroundMode = "plain";
    private String textBackgroundColor = "rgba(255,255,255,192)";
    private String textOffset = "0px, 0px";
    private String textPadding = "0px, 0px";
    private String visibilityMode = "normal";
    private String visibility = "0";
    private String zIndex = "2";
    
    //private String textFont = "sans-serif";
    //private String arrowImage;		UNUSED
    //private String arrowSize;			UNUSED
    //private String arrowShape;		UNUSED
    //private String spriteOrientation;	UNUSED
    //private String canvasColor;		UNUSED
    //private String shadowMode;		UNUSED
    //private String shadowColor;		UNUSED
    //private String shadowWidth;		UNUSED
    //private String shadowOffset;		UNUSED
    //private String shapePoints;	EXPERIMENTAL
    //private String jcomponent;	EXPERIMENTAL
    
    public NodeLegend(String type, String stereotype, String fillImage, String style, String prefixMode) {
	    // Default Style:
    	switch(stereotype) {
	        case "Mode":
	        	this.size = "16px";
	        	break;
	        case "Relator":
	        	this.size = "48px";
	        	break;
	        default:
	        	this.size = "32px";
	        	break;
    	}
    	this.type = type;
    	this.stereotype = stereotype;
    	//this.style = style;
    	this.fillImage = fillImage;
    	this.amount = 0;
    	this.prefix = createPrefix(prefixMode);
    }
    
    public String createPrefix(String mode) {
    	String prefix = "";
    	switch(mode) {
	    	case "reduced":
	    		prefix = "" + type.charAt(0);
	        	for(int i=1; i<type.length(); i++) {
	        		String verify = "" + type.charAt(i);
	        		if(!verify.matches("a|e|i|o|u|á|é|í|ó|ú|â|ê|ô|ã|A|E|I|O|U|Á|É|Í|Ó|Ú|Â|Ê|Ã| ")) {
	        			if(verify.matches(" ")){
	        				verify.toUpperCase();
	        			}
	        			prefix = prefix + verify;
	        		}
	        	}
	    		break;
	    	case "oneWord":
	    		int spaceIndex = type.indexOf(" ");
	    		if(spaceIndex > 0) {
	    			prefix = type.substring(0, spaceIndex);
	    		}else{
	    			prefix = type;
	    		}
	    		break;
	    	default:
	    		prefix = mode;
	    		break;
    	}
    	return prefix;
    }
    
    public String createLabel() {
    	return prefix + (++amount);
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getAmount() {
		return amount;
	}

	
	public String getFillMode() {
		return fillMode;
	}

	public void setFillMode(String fillMode) {
		this.fillMode = fillMode;
	}

	public String getFillImage() {
		return fillImage;
	}

	public void setFillImage(String fillImage) {
		this.fillImage = fillImage;
	}

	public String getIconMode() {
		return iconMode;
	}

	public void setIconMode(String iconMode) {
		this.iconMode = iconMode;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPadding() {
		return padding;
	}

	public void setPadding(String padding) {
		this.padding = padding;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSizeMode() {
		return sizeMode;
	}

	public void setSizeMode(String sizeMode) {
		this.sizeMode = sizeMode;
	}

	public String getStrokeMode() {
		return strokeMode;
	}

	public void setStrokeMode(String strokeMode) {
		this.strokeMode = strokeMode;
	}

	public String getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}

	public String getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(String strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public String getTextMode() {
		return textMode;
	}

	public void setTextMode(String textMode) {
		this.textMode = textMode;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getTextStyle() {
		return textStyle;
	}

	public void setTextStyle(String textStyle) {
		this.textStyle = textStyle;
	}

	public String getTextSize() {
		return textSize;
	}

	public void setTextSize(String textSize) {
		this.textSize = textSize;
	}

	public String getTextAlignment() {
		return textAlignment;
	}

	public void setTextAlignment(String textAlignment) {
		this.textAlignment = textAlignment;
	}

	public String getTextVisibilityMode() {
		return textVisibilityMode;
	}

	public void setTextVisibilityMode(String textVisibilityMode) {
		this.textVisibilityMode = textVisibilityMode;
	}

	public String getTextVisibility() {
		return textVisibility;
	}

	public void setTextVisibility(String textVisibility) {
		this.textVisibility = textVisibility;
	}

	public String getTextBackgroundMode() {
		return textBackgroundMode;
	}

	public void setTextBackgroundMode(String textBackgroundMode) {
		this.textBackgroundMode = textBackgroundMode;
	}

	public String getTextBackgroundColor() {
		return textBackgroundColor;
	}

	public void setTextBackgroundColor(String textBackgroundColor) {
		this.textBackgroundColor = textBackgroundColor;
	}

	public String getTextOffset() {
		return textOffset;
	}

	public void setTextOffset(String textOffset) {
		this.textOffset = textOffset;
	}

	public String getTextPadding() {
		return textPadding;
	}

	public void setTextPadding(String textPadding) {
		this.textPadding = textPadding;
	}

	public String getVisibilityMode() {
		return visibilityMode;
	}

	public void setVisibilityMode(String visibilityMode) {
		this.visibilityMode = visibilityMode;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getzIndex() {
		return zIndex;
	}

	public void setzIndex(String zIndex) {
		this.zIndex = zIndex;
	}
	
	public String getStyle() {
		String style = "fill-mode: " + fillMode + ";\n" +
				"fill-image: url('" + fillImage + "');\n" +
				"icon-mode: " + iconMode + ";\n" +
				"icon: url('" + icon + "');\n" +
				"padding: " + padding + ";\n" +
				"shape: " + shape + ";\n" +
				"size: " + size + ";\n" +
				"size-mode: " + sizeMode + ";\n" +
				"stroke-mode: " + strokeMode + ";\n" +
				"stroke-color: " + strokeColor + ";\n" +
				"stroke-width: " + strokeWidth + ";\n" +
				"text-mode: " + textMode + ";\n" +
				"text-color: " + textColor + ";\n" +
				"text-style: " + textStyle + ";\n" +
				//"text-font: " + textFont + ";\n" +
				"text-size: " + textSize + ";\n" +
				"text-alignment: " + textAlignment + ";\n" +
				"text-visibility-mode: " + textVisibilityMode + ";\n" +
				"text-visibility: " + textVisibility + ";\n" +
				"text-background-mode: " + textBackgroundMode + ";\n" +
				"text-background-color: " + textBackgroundColor + ";\n" +
				"text-offset: " + textOffset + ";\n" +
				"text-padding: " + textPadding + ";\n" +
				"visibility-mode: " + visibilityMode + ";\n" +
				"visibility: " + visibility + ";\n" +
				"z-index: " + zIndex + ";\n";
		return style;
	}
	
}
