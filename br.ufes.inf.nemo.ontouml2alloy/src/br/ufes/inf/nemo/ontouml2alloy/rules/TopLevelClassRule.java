package br.ufes.inf.nemo.ontouml2alloy.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.BinaryOperation;
import br.ufes.inf.nemo.alloy.DisjointExpression;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyAPI;

public class TopLevelClassRule {
        
        /**
         * Verify if the lists have at least one element in common.
         */
        public static Classifier isOverlapping (Set<Classifier> list1, Set<Classifier> list2)
        {
                for (Classifier c1: list1)
                {
                        for (Classifier c2: list2)
                        {
                                if (c2.equals(c1)) return c1;
                        }
                }
                return null;
        }
        
        /**
         *  Create Rule for Top Levels Classifiers in the model.
         * 
         *  Should be more of a description here....
         */
        public static ArrayList<DisjointExpression> createTopLevelDisjointExpressions (OntoUMLParser ontoparser, AlloyFactory factory, ArrayList<Classifier> toplevels)
        {
                HashMap< ArrayList<Classifier>, Integer > listsHashMap = new HashMap< ArrayList<Classifier>,Integer >();
                ArrayList<DisjointExpression> result = new ArrayList<DisjointExpression>();
                
                for (Classifier c1: toplevels)
                {                       
                        Set<Classifier> descendants1 = ontoparser.getAllChildren(c1);

                        // creates a single List containing the topLevel classifier 'c1' 
                        // and the top levels classifiers that have their descendants overlapping 
                        // with the descendants of the classifier 'c1'
                        
                        ArrayList<Classifier> singleList = new ArrayList<Classifier>();
                        singleList.add(c1);
                        
                        for (Classifier c2: toplevels)
                        {
                                if (!c2.equals(c1)) 
                                {
                                        Set<Classifier> descendants2 = ontoparser.getAllChildren(c2);
                                                                                
                                        Classifier overlap = isOverlapping(descendants1, descendants2);
                                        if (overlap == null) singleList.add(c2);                                                
                                }
                        }
                                                
                        listsHashMap.put(singleList,0);
                }
                                
                // create a union (+) String expression for every singleList 
                // starting at the second element
                
                for (ArrayList<Classifier> singleList : listsHashMap.keySet())
                {
                        if (listsHashMap.get(singleList)==0)
                        {
                                // create a list of the elements starting at the second element
                                int count = 0;
                                ArrayList<String> exprList = new ArrayList<String>();
                                for(Classifier c: singleList) 
                                {
                                        if (count>=1) exprList.add(ontoparser.getAlias(c));                                     
                                        count++;
                                }
                                
                                ArrayList<String> paramList = new ArrayList<String>();
                                paramList.add(ontoparser.getAlias(singleList.get(0)));
                                
                                if (exprList.size()>1) 
                                {
                                        // create a union(+) operation for the exprList
                                        BinaryOperation bo = AlloyAPI.createUnionExpression(factory, exprList);
                                        paramList.add(bo.toString());                                   
                                }else if (exprList.size()==1){
                                        paramList.add(ontoparser.getAlias(singleList.get(1)));                                  
                                }else {
                                        return result;
                                }
                                
                                //add Top Level Disjoint Expression Rule to to the List
                                result.add( AlloyAPI.createDisjointExpression(factory,paramList) );                             
                        }                       
                }
                return result;
        }
}