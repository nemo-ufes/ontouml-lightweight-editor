 package br.ufes.inf.nemo.temporalocl2alloy.pivot;

public class TemporalOCLToAlloyTest {

		public static void main (String[] args)
		{
			String result = new String();
			
			result += WorldStructureFunctionsGenerator.run();
						
			System.out.println(result);			
		}
}
