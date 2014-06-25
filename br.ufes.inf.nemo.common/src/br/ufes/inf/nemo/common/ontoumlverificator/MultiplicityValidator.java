package br.ufes.inf.nemo.common.ontoumlverificator;

public class MultiplicityValidator {
	private String text;
	private int lower;
	private int upper;
	private boolean isValid;
	
	public MultiplicityValidator(String input){
		this.text = input;
		isValid = check();
	}
	
	public String getText() {
		return text;
	}

	public int getLower() {
		return lower;
	}

	public int getUpper() {
		return upper;
	}

	public boolean isValid() {
		return isValid;
	}

	private boolean check(){
		lower = 0;
		upper = 0;
		
		if(text==null){
//			System.out.println("FAILED: NULL");
			return false;
		}
		else if(text.trim().isEmpty()){
//			System.out.println("FAILED: EMPTY");
			return false;
		}
		
		char[] array = text.toCharArray();
		
		boolean afterDotDot = false;
		boolean hasDigitOnUpper = false;
		int upperStartIndex = 0;
		
		if(array.length==1){
			if(array[0]=='*'){
				lower = 0;
				upper = -1;
			}
			else if(Character.isDigit(array[0])){
				if(array[0]=='0'){
//					System.out.println("FAILED: CAN'T BE ZERO!");
					return false;
				}
				
				lower = Integer.parseInt(text);
				upper = lower;
			}
			else{
//				System.out.println("FAILED: size 1 - only digit or star");
				return false;
			}
			
//			System.out.println("SUCCESS: "+array[0]);
			return true;
		}
		
		for (int i = 0; i < array.length; i++) {
//			System.out.println("DefinedLower: "+afterDotDot);
			if(!Character.isDigit(array[i]) && array[i]!='*' && array[i]!='.'){
//				System.out.println("FAILED: INVALID CHAR AT POSITION "+i);
				return false;
			}
			
			if(i==0 && !Character.isDigit(array[i])){
				System.out.println("FAILED: MUST START WITH DIGIT");
				return false;
			}
			
			if(i>0){
				if(!afterDotDot && array[i]=='.'){
//					System.out.println("DOTDOT AT: "+i);
					afterDotDot = true;
					upperStartIndex = i+2;
					
					if(i==array.length-1 || array[i+1]!='.'){
//						System.out.println("FAILED: MUST HAVE TWO DOTS");
						return false;
					}
					else if (i==array.length-2){
//						System.out.println("FAILED: MUST HAVE SOMETHING AFTER TWO DOTS");
						return false;
					}
					else {
						lower = Integer.parseInt(text.substring(0, i));
						i++;
						continue;
					}	
				}
				else if(!afterDotDot && array[i]=='*'){
//					System.out.println("FAILED: STAR ONLY ON UPPER");
					return false;
				}
				else if(!afterDotDot && Character.isDigit(array[i]) && i==array.length-1){
//					System.out.println("SUCCES: ONLY DIGITS AND UPPER = LOWER");
					lower = Integer.parseInt(text);
					upper = lower;
					return true;
				}
				
				if(afterDotDot && array[i]=='.'){
//					System.out.println("FAILED: ONLY TWO DOTS");
					return false;
				}
				else if(afterDotDot && array[i]=='*'){
					if(hasDigitOnUpper){
//						System.out.println("FAILED: CANN ADD STAR AFTER DIGIT");
						return false;
					}
					else if(i!=array.length-1){
//						System.out.println("FAILED: MUST END AFTER STAR");
						return false;
					}
					
//					System.out.println("SUCCESS: COMPOSITE MULT ENDING WITH STAR");
					upper = -1;
					return true;
				}
				else if(afterDotDot && Character.isDigit(array[i])){
					hasDigitOnUpper = true;
					if(i==array.length-1){
						upper = Integer.parseInt(text.substring(upperStartIndex));
						if(upper==0){
//							System.out.println("FAILED: UPPER CAN'T BE ZERO.");
							return false;
						}
						else if(upper<lower){
//							System.out.println("FAILED: UPPER MUST BE GREATER OR EQUAL LOWER");
							return false;
						}
					}
				}
			}
		}
//		System.out.println("SUCCES: "+text);
		return true;
	}
	
}
