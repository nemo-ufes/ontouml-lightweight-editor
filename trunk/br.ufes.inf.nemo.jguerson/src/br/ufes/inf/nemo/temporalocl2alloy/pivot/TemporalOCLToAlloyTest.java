 package br.ufes.inf.nemo.temporalocl2alloy.pivot;

public class TemporalOCLToAlloyTest {

		public static void main (String[] args)
		{
			String result = new String();
			
			result += WorldStructureFunctionsGenerator.run();
			
			result += "fact"+" Offspring_Eventually"+"{\n"+
					  TemporalOCLToAlloyGenerator.eventuallyGlobally("w.Person", "self in w.Offspring")
			 	      +"\n}\n";
			 
			result += "pred"+" Offspring_Creation2"+"{\n"+
					  TemporalOCLToAlloyGenerator.alwaysBetweenAnd("w.Person", "(self in w.Offspring)", "(self in w.Person and self !in (next.w).Person)", "(self in w.Offspring and self !in (next.w).Offspring)",false)
					  +"\n}\n";
					  
			result += "fact {Offspring_Creation2}"+"\n";			
						
			result += "\ncheck { all b: Branch | all w:World[b] | all self: w.Person | self in w.Person and self !in (next.w).Person implies self in w.Offspring }\n"+
					  "for 10 but 5 Object, 0 DataType, 7 Int, 7 World";
			
			System.out.println(result);			
		}
}
