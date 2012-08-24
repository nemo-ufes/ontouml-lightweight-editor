package br.ufes.inf.nemo.refontouml2alloy;

/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import java.text.Normalizer;
import java.util.HashMap;


public class StringCheck {
        
        public int Relator=0, Mode=0, Kind=0, Quantity=0, Collective=0, Phase=0, Role=0, Category=0, RoleMixin=0, Mixin=0;
        
        public int componentOf=0, subQuantityOf=0, subCollectionOf=0, memberOf=0, Mediation=0, Characterization=0,
                           Derivation=0, FomalAssociation=0, MaterialAssociation=0;
        
        public HashMap<String, WordCounter> names = new HashMap<String, WordCounter>();
                        
        public String removeSpecialNames(String str) {
                int cont=-1;
                
                String[] keywords  = {
                                "World","abstract","all","and","as","assert","but","check","disj","else","exactly","extends","fact",
                                "for","fun","iden","iff","implies","in","Int","let","lone","module","no","none","not","one","open",
                                "or","pred","run","set","sig","some","sum","univ","int","Int"
                };
                
                for(int i=0;i<keywords.length;i++)
                {
                        if(str==keywords[i])
                        {
                                str = "keyword";
                                break;
                        }
                }
                
                str = str.replaceAll("class RefOntoUML.impl.","");
                str = str.replaceAll("Impl","");
                str = Normalizer.normalize(str, Normalizer.Form.NFD);
                str = str.replaceAll("[^\\p{ASCII}]", "");
                str = str.replaceAll(" ", "");
                str = str.replaceAll(",", "");
                str = str.replaceAll("!", "");
                str = str.replaceAll("@", "");
                str = str.replaceAll("#", "");
                str = str.replaceAll("\\$", "");
                str = str.replaceAll("%", "");
                str = str.replaceAll("\"", "");
                str = str.replaceAll("'", "");
                str = str.replaceAll("&", "");
                str = str.replaceAll("\\*", "");
                str = str.replaceAll("-", "");
                str = str.replaceAll("=", "");
                str = str.replaceAll("\\+", "");
                str = str.replaceAll(";", "");
                str = str.replaceAll(":", "");
                str = str.replaceAll("-", "");
                str = str.replaceAll("<", "");
                str = str.replaceAll(">", "");
                str = str.replaceAll("\\?", "");
                str = str.replaceAll("\\.", "");
                str = str.replaceAll("\\{", "");
                str = str.replaceAll("\\}", "");
                str = str.replaceAll("\\(", "");
                str = str.replaceAll("\\)", "");
                str = str.replaceAll("\\[", "");
                str = str.replaceAll("\\]", "");
                str = str.replaceAll("\\\\", "");
                str = str.replaceAll("/", "");
                str = str.replaceAll("\\|", "");
                
                if(str==null)
                {}
                else if(str.equals(""))
                {}
                
                if(names.get(str)==null)
                {
                        names.put(str, new WordCounter(str, 0));
                        cont = 0;
                }
                else
                {
                        names.get(str).setCounter(names.get(str).getCounter()+1);
                        cont = names.get(str).getCounter();
                }
                
                if(cont!=0)
                {
                        names.put(str+Integer.toString(cont), new WordCounter(str+Integer.toString(cont), 0));
                        return str+Integer.toString(cont);
                }
                return str;
        }
}