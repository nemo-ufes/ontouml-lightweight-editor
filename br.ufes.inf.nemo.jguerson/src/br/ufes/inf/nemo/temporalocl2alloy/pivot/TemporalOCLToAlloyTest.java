 package br.ufes.inf.nemo.temporalocl2alloy.pivot;

public class TemporalOCLToAlloyTest {

		public static void main (String[] args)
		{
			String result = new String();
			
			result += WorldStructureFunctionsGenerator.run();
			
			result += "pred"+" alwaysGlobally"+"{\n"+
					  TemporalOCLToAlloyGenerator.alwaysGlobally("w.Person", "self in w.Child")+
					  "}\n";
			
			result += "pred"+" alwaysBefore"+"{\n"+
					  TemporalOCLToAlloyGenerator.alwaysBefore("w.Person", "self in w.Child", "self in w.Teenager")+
					  "}\n";
			
			result += "pred"+" alwaysAfter"+"{\n"+
					  TemporalOCLToAlloyGenerator.alwaysAfter("w.Person", "self in w.Adult", "self in w.Teenager")+
					  "}\n";
			
			result += "pred"+" alwaysBetweenAnd"+"{\n"+
					  TemporalOCLToAlloyGenerator.alwaysBetweenAnd("w.Person", "self in w.Teenager", "self in w.Child","self in w.Adult",false)+
					  "}\n";
			
			result += "pred"+" alwaysAfterUnless"+"{\n"+
					  TemporalOCLToAlloyGenerator.alwaysAfterUnless("w.Person", "self in w.Teenager", "self in w.Child","self in w.Adult",false)+
					  "}\n\n";
			
			result += "run alwaysGlobally for 10 but 1 Object, 0 DataType, 7 Int, 7 World"+"\n";
			result += "run alwaysBefore for 10 but 1 Object, 0 DataType, 7 Int, 7 World"+"\n";
			result += "run alwaysAfter for 10 but 1 Object, 0 DataType, 7 Int, 7 World"+"\n";
			result += "run alwaysBetweenAnd for 10 but 1 Object, 0 DataType, 7 Int, 7 World"+"\n";
			result += "run alwaysAfterUnless for 10 but 1 Object, 0 DataType, 7 Int, 7 World"+"\n";
						
			System.out.println(result);			
		}
}
