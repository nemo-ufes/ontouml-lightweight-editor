package br.ufes.inf.nemo.assistant.binaryastah2graph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import br.ufes.inf.nemo.assistant.ModellingAssistant;
import br.ufes.inf.nemo.assistant.astah2graph.AstahParser;
import br.ufes.inf.nemo.assistant.graph.GraphAssistant;
import br.ufes.inf.nemo.assistant.util.StereotypeOntoUMLEnum;

public class BynaryAstah2Graph {
	/**
	 * Create a .ser from an Astah DWL model
	 * */
	public static void serialize(){
		HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashGraph = AstahParser.doParser("Patterns_NEMO.asta");

		for(StereotypeOntoUMLEnum stereotype : hashGraph.keySet()){
			hashGraph.get(stereotype).updateNodeList();
		}

		try
		{
			FileOutputStream fileOut = new FileOutputStream(ModellingAssistant.serializedObjectPath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(hashGraph);
			out.close();
			fileOut.close();
		}catch(Exception i){
			i.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static HashMap<StereotypeOntoUMLEnum,GraphAssistant> deserialize() throws Exception{
		HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashGraph = null;

		try
		{
			FileInputStream fileIn = new FileInputStream(ModellingAssistant.serializedObjectPath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			hashGraph = (HashMap<StereotypeOntoUMLEnum, GraphAssistant>) in.readObject();
			in.close();
			fileIn.close();
		}catch(Exception i)
		{
			throw i;
		}
		return hashGraph;
	}

	public static void main(String[] args) {
		BynaryAstah2Graph.serialize();
		System.out.println("Finished");
	}
	
//	public static void main(String[] args) {
//		try{
//			HashMap<StereotypeOntoUMLEnum, GraphAssistant> hashGraph = BynaryAstah2Graph.deserialize();
//			GraphAssistant graph = hashGraph.get(StereotypeOntoUMLEnum.KIND);
//			System.out.println(graph.toString());
//		}catch(Exception e ){
//			e.printStackTrace();
//		}
//		
//		System.out.println("Finished");
//	}
}
