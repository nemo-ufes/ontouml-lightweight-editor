package old;

import java.io.File;
import java.util.Arrays;

import br.ufes.inf.nemo.ontouml.xmi2refontouml.transformation.Mediator;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.ElementType;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.Mapper;
import br.ufes.inf.nemo.ontouml.xmi2refontouml.util.MapperEA;


public final class Main {
	
	//final static String READ_FILE_ADDRESS = "C:\\Users\\Vinicius\\Documents\\ANTT\\XMItoOntoRef\\Atos Normativos.xml";
	//final static String READ_FILE_ADDRESS = "PessoaSimplesEA2.1.xmi";
	//final static String SAVE_FILE_ADDRESS = "teste.refontouml";

	/**
	 * Private constructor.
	 */
	private Main() { }

	/**
	 * The start method for this application.
	 * @param args the command line parameters
	 */
	public static void main(String[] args) {
		
		//File f = new File("tests\\TesteEMF2.uml");
		//File f = new File("tests\\testeEA1.xml");
		//File f = new File("tests\\Profiles\\OntoUMLXMI2.1Profile.xml");
		
		//Mapper mf = new MapperEA(f.getAbsolutePath());
		
		String[] tag = {"UML:Namespace.ownedElement", "UML:Association"};
		
		int n = tag.length;
		if (n > 1) {
			System.out.println("entrou");
			tag = Arrays.copyOfRange(tag, 1, n);
		}
		
	}
	
}
