/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Mauricio
 */
public class XMLFile {
    private ArrayList<Atom> atomList;
    private ArrayList<Sig> sigList;
    private ArrayList<Field> fieldList;
    private ArrayList<Skolem> skolemList;
    
    private ArrayList<Atom> worldList;
    private ArrayList<Atom> objectList;
    private ArrayList<Atom> propertyList;
    private ArrayList<Atom> dataTypeList;
    
    public XMLFile(java.io.File file) throws ParserConfigurationException, SAXException, IOException {
        
        // Initialization of parameters
        skolemList = new ArrayList();
        atomList = new ArrayList();
        sigList = new ArrayList();
        fieldList = new ArrayList();
        
        worldList = new ArrayList();
        objectList = new ArrayList();
        propertyList = new ArrayList();
        dataTypeList = new ArrayList();
        
        //integers = new ArrayList();
        
        // Auxiliary variables
        int i, j, k;
        ReadXML xml = new ReadXML(file);
        Sig sig;
        Field field;
        org.w3c.dom.Element aux;
        
        // Getting elements from .xml file
        NodeList sigl = xml.getDoc().getElementsByTagName("sig");
        NodeList atoml;	// This one will change on each sig
        NodeList fieldl = xml.getDoc().getElementsByTagName("field");
        
        // Getting sigs
        for(i=0; i<sigl.getLength(); i++) {
            //System.out.println("NUM");
            aux = (org.w3c.dom.Element) sigl.item(i);
            sig = new Sig(aux);
            atoml = aux.getElementsByTagName("atom");
            
            sigList.add(sig);
            
            System.out.println(sig.getLabel());
            for(j=0; j<atoml.getLength(); j++) {
                aux = (org.w3c.dom.Element) atoml.item(j);
                //System.out.println(atoml.getLength());
                Atom atom = new Atom(aux, sig);
                System.out.println("    " + atom.getLabel());
                atomList.add(atom);
                if(sig.isWorld()) {
                    // Could be a world atom... if it is, adding it to worldList
                    worldList.add(atom);
                }
                //System.out.println(aux.getAttribute("label"));
            }
        }
        
        // Getting fields and its tuples. 
        for(i=0; i<fieldl.getLength(); i++) {
            //System.out.println("NUM2");
            fieldList.add(new Field((org.w3c.dom.Element) fieldl.item(i), atomList));
        }
        
        // Getting parent ids:
        for(i=0; i<sigList.size(); i++){
        	for(j=0; j<sigList.size(); j++){
        		if(sigList.get(i).getParentId() == sigList.get(j).getId()) {
        			sigList.get(i).setParentSig(sigList.get(j));
        			break;
        		}
        	}
        	if(sigList.get(i).getParentSig() != null) {
    			break;
    		}
        	for(j=0; j<fieldList.size(); j++){
        		if(sigList.get(i).getParentId() == fieldList.get(j).getId()) {
        			sigList.get(i).setParentSig(fieldList.get(j));
        			break;
        		}
        	}
        }
        
        for(i=0; i<fieldList.size(); i++){
        	for(j=0; j<fieldList.size(); j++){
        		if(fieldList.get(i).getParentId() == sigList.get(j).getId()) {
        			fieldList.get(i).setParentSig(sigList.get(j));
        			break;
        		}
        	}
        	if(fieldList.get(i).getParentSig() != null) {
    			break;
    		}
        	for(j=0; j<fieldList.size(); j++){
        		if(fieldList.get(i).getParentId() == fieldList.get(j).getId()) {
        			fieldList.get(i).setParentSig(fieldList.get(j));
        			break;
        		}
        	}
        }
        
    }
    
    public ArrayList<String> getAtomTypeOnWorld(String atomLabel, String worldLabel) {
        ArrayList<String> stringList = new ArrayList();	// The list of types. One atom can have multiple types.
        int i, j, k;
        for(i=0; i<fieldList.size(); i++) {
        	System.out.println("FIELD NAME: " + fieldList.get(i).getLabel());
            if(!fieldList.get(i).getTuples().isEmpty()) {
                if(fieldList.get(i).getTuple(0).size() == 2) {
                    System.out.println("FOI");
                    for(j=0; j<fieldList.get(i).getTuples().size(); j++) {
                        System.out.println("FOI2");
                        //for(k=0; k<fieldList.get(i).getTuple(j).size(); k++) {
                            System.out.println("FOI3");
                            if(fieldList.get(i).getTuple(j).get(0).equals(worldLabel)
                                    && fieldList.get(i).getTuple(j).get(1).equals(atomLabel)) {
                                stringList.add(fieldList.get(i).getLabel());
                            }
                        //}
                    }
                }
            }
        }
        return stringList;
    }
    
    public Atom findAtom(String label) {
        int i;
        for(i=0; i<atomList.size(); i++) {
            if(atomList.get(i).getLabel().equals(label)) {
                return atomList.get(i);
            }
        }
        return null;
    }
    
    public Sig findSigById(int id) {
        int i;
        for(i=0; i<sigList.size(); i++) {
            if(sigList.get(i).getId() == id) {
                return sigList.get(i);
            }
        }
        return null;
    }

    public ArrayList<Atom> getAtomList() {
        return atomList;
    }

    public void setAtomList(ArrayList<Atom> atomList) {
        this.atomList = atomList;
    }

    public ArrayList<Sig> getSigList() {
        return sigList;
    }

    public void setSigList(ArrayList<Sig> sigList) {
        this.sigList = sigList;
    }

    public ArrayList<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(ArrayList<Field> fieldList) {
        this.fieldList = fieldList;
    }

    public ArrayList<Skolem> getSkolemList() {
        return skolemList;
    }

    public void setSkolemList(ArrayList<Skolem> skolemList) {
        this.skolemList = skolemList;
    }

    public ArrayList<Atom> getWorldList() {
        return worldList;
    }

    public void setWorldList(ArrayList<Atom> worldList) {
        this.worldList = worldList;
    }

    public ArrayList<Atom> getObjectList() {
        return objectList;
    }

    public void setObjectList(ArrayList<Atom> objectList) {
        this.objectList = objectList;
    }

    public ArrayList<Atom> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(ArrayList<Atom> propertyList) {
        this.propertyList = propertyList;
    }

    public ArrayList<Atom> getDataTypeList() {
        return dataTypeList;
    }

    public void setDataTypeList(ArrayList<Atom> dataTypeList) {
        this.dataTypeList = dataTypeList;
    }
    
    
    
}