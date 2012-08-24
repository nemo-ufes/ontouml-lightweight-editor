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

public class LibraryFiles {
	
	public static String ontological_properties = 
			
			"module ontological_properties[World]\n\n" +
			"/*run this predicate to verify if there the class is actually anti-rigid. Parametrized the predicate with the class name.*/\n" +
			"pred antirigidity[class:set univ -> univ, class_type: univ, exists:univ->univ] {\n" +
			"\tsome x:class_type, disj w1,w2:World | x in w1.exists and x in w1.class and x in w2.exists and x not in w2.class\n" +
			"}\n\n" +
			"/*this predicate states that a class is rigid.*/\n" +
			"pred rigidity[class: univ->univ,  class_type: univ, exists:univ->univ] {\n" +
			"\tall w1:World,  p:univ | p in w1.exists and p in w1.class implies all w2:World | w1!=w2 and p in w2.exists implies p in w2.class\n" +
			"}\n\n" +
			"/*Predicate that states that all elements must exists in at least one world*/\n" +
			"pred all_elements_exists [elements:univ, exists: World->univ]{\n" +
			"\tall x: elements | some w: World | x in w.exists\n" +
			"}\n\n" +
			"/*classes that must exist in all worlds*/\n" +
			"pred always_exists[class_type: set univ, exists: World->univ] {\n" +
			"\tall w:World, x:class_type | x in w.exists\n" +
			"}\n\n" +
			"/*predicate to make the target association end is readOnly*/\n" +
			"pred immutable_target [source_class:World->univ, assoc: univ->univ->univ]{\n" +
			"\tall w1:World, x:univ |x in w1.source_class implies all w2:World | x in w2.source_class implies  x.(w1.assoc) = x.(w2.assoc)\n" +
			"}\n\n" +
			"/*predicate to make the target association end is readOnly*/\n" +
			"pred immutable_source [target_class:World->univ, assoc: univ->univ->univ]{\n" +
			"\tall w1:World, x:univ |x in w1.target_class implies all w2:World | x in w2.target_class implies (w1.assoc).x = (w2.assoc).x\n" +
			"}\n\n" +
			"/*states that the material relation is derived from the relator*/\n" +
			"pred derivation [material: World->univ->univ, mediation1: World->univ->univ, mediation2: World->univ->univ] {\n" +
			"\tall w:World | (w.material) = (~(w.mediation1)).(w.mediation2) or (w.material) = (~(w.mediation2)).(w.mediation1)\n" +
			"}\n\n";
	
	public static String world_structure = 
			
			"module world_structure[World]\n\n" +
			"some abstract sig TemporalWorld extends World{\n" +
			"\tnext: set TemporalWorld -- Immediate next moments.\n" +
			"}{\n" +
			"\tthis not in this.^(@next) -- There are no temporal cicles.\n" +
			"\tlone ((@next).this) -- A world can be the immediate next momment of at maximum one world.\n" +
			"}\n\n" +
			"one sig CurrentWorld extends TemporalWorld {} {\n" +
			"\tnext in FutureWorld\n" +
			"}\n\n" +
			"sig PastWorld extends TemporalWorld {} {\n" +
			"\tnext in (PastWorld + CounterfactualWorld + CurrentWorld)\n" +
			"\tCurrentWorld in this.^@next -- All past worlds can reach the current moment.\n" +
			"}\n" +
			"sig FutureWorld extends TemporalWorld {} {\n" +
			"\tnext in FutureWorld\n" +
			"\tthis in CurrentWorld.^@next -- All future worlds can be reached by the current moment.\n" +
			"}\n\n" +
			"sig CounterfactualWorld extends TemporalWorld {} {\n" +
			"\tnext in CounterfactualWorld\n" +
			"\tthis in PastWorld.^@next -- All past worlds can reach the counterfactual moment.\n" +
			"}\n\n" +
			"/*Objects can't die and come to life later*/\n" +
			"pred linear_existence [exists: World->univ] {\n" +
			"\tall w : World, x: (@next.w).exists | (x not in w.exists) => (x not in (( w. ^next).exists))\n" +
			"}\n\n" +
			"assert non_local_accessibility {\n" +
			"\tno w: TemporalWorld | w in w.next\n" +
			"}\n\n" +
			"assert no_temporal_cicles {\n" +
			"\tno w: TemporalWorld | w in w.^next\n" +
			"}\n\n" +
			"assert no_joining_histories {\n" +
			"\tall w: TemporalWorld | #(next.w) <= 1\n" +
			"}\n\n" +
			"assert every_world_reach_the_current_world {\n" +
			"\tall w: TemporalWorld | CurrentWorld in w.^(next + ~next)\n" +
			"}\n\n" +
			"assert future_worlds_cannot_reach_the_current_world_by_the_future{\n" +
			"\tno w: FutureWorld | CurrentWorld in w.^next\n" +
			"}\n\n" +
			"check non_local_accessibility for 10\n" +
			"check no_temporal_cicles for 10\n" +
			"check no_joining_histories for 10\n" +
			"check every_world_reach_the_current_world for 10\n" +
			"check future_worlds_cannot_reach_the_current_world_by_the_future for 10\n";

}
			
	
