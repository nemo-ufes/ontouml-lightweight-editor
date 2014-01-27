package br.ufes.inf.nemo.instancevisualizer.graph;

import java.util.ArrayList;

public class NodeLegend {
	private String type;
	private String stereotype;
	private String prefix;
    private String imagePath;
    private String style;
    private Integer amount;
    
    public NodeLegend(String type, String stereotype, String imagePath, String style, String prefixMode) {
    	this.type = type;
    	this.stereotype = stereotype;
    	this.imagePath = imagePath;
    	this.style = style;
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
    
    public void addStyle(String style) {
    	if(style.endsWith(";")) {
    		this.style += "\n" + style;
    	}else{
    		this.style += "\n" + style + ";";
    	}
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

	public Integer getAmount() {
		return amount;
	}

}
