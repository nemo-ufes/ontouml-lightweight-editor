package br.ufes.inf.nemo.temporalocl2alloy.pivot;

public class WorldStructureFunctionsGenerator {

	public static String run()
	{
		return 
		"-- Definition: Every Branch is represented as a terminal World" + "\n\n" +
	
		"-- Returns all terminal Worlds of the structure" + "\n" +				
		"fun Branch : set World {  World.next - (World.next & next.World) }" + "\n\n" +
		
		"-- Returns the Branches in which a particular World is at" + "\n" +	
		"fun Branch [w: World] : set World { w.(^next) & Branch }"+ "\n\n" +

		"-- Returns the Origin World of the structure" + "\n" +	
		"fun Origin : set World { next.World - (World.next & next.World) }"+ "\n\n" +

		"-- Returns all the Worlds contained in a given Branch" + "\n" +	
		"fun World [branch: World] : set World { (^next).branch + branch.(^next) + branch}"+ "\n\n" +

		"-- Returns the set of Worlds before w" + "\n" +	
		"fun Former [w: World] : set World { (^next).w }"+ "\n\n" +

		"-- Returns the set of Worlds after w" + "\n" +	
		"fun Latter [w: World] : set World { w.(^next) }"+ "\n\n" +

		"-- Return the set of Worlds after w in a given Branch"+ "\n" +	
		"fun Latter [w: World, branch: World] : set World { Between[w, branch]+branch }"+ "\n\n" +

		
		"-- Returns the set of Worlds between w1 and w2, where w1 happens before w2" + "\n" +	
		"fun Between [w1,w2: World] : set World { (^next).w2 - (^next).w1 - w1}"+ "\n\n";
	}
}
