package br.ufes.inf.nemo.oled.dialog;

public class MappingTypeComboItem {	
	
		public String value;
		public String displayName;
		public String description;
		
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getDisplayName() {
			return displayName;
		}
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		@Override
		public String toString()
		{
			if(displayName != null)
				return displayName;
			
			return value;
		}
	}
	