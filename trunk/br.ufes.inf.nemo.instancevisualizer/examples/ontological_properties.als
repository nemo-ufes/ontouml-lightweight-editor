module ontological_properties[World]

/*run this predicate to verify if there the class is actually anti-rigid. Parametrized the predicate with the class name.*/
pred antirigidity[class:set univ -> univ, class_type: univ, exists:univ->univ] {
	some x:class_type, disj w1,w2:World | x in w1.exists and x in w1.class and x in w2.exists and x not in w2.class
}

/*this predicate states that a class is rigid.*/
pred rigidity[class: univ->univ,  class_type: univ, exists:univ->univ] {
	all w1:World,  p:univ | p in w1.exists and p in w1.class implies all w2:World | w1!=w2 and p in w2.exists implies p in w2.class
}

/*Predicate that states that all elements must exists in at least one world*/
pred elements_existence [elements:univ, exists: World->univ]{
	all x: elements | some w: World | x in w.exists
}

/*classes that must exist in all worlds*/
pred necessarily_exist[class_type: set univ, exists: World->univ] {
	all w:World, x:class_type | x in w.exists
}

/*predicate to make the target association end is readOnly*/
pred immutable_target [source_class:World->univ, assoc: univ->univ->univ]{
	all w1:World, x:univ |x in w1.source_class implies all w2:World | x in w2.source_class implies  x.(w1.assoc) = x.(w2.assoc)
}

/*predicate to make the target association end is readOnly*/
pred immutable_source [target_class:World->univ, assoc: univ->univ->univ]{
	all w1:World, x:univ |x in w1.target_class implies all w2:World | x in w2.target_class implies (w1.assoc).x = (w2.assoc).x
}

/*states that the material relation is derived from the relator*/
pred derivation [material: World->univ->univ, mediation1: World->univ->univ, mediation2: World->univ->univ] {
	all w:World | (w.material) = (~(w.mediation1)).(w.mediation2) or (w.material) = (~(w.mediation2)).(w.mediation1)
}

