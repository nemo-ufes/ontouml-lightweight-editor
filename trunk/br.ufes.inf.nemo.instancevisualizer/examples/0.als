module Model

open world_structure[World]
open ontological_properties[World]
open util/relation
open util/ternary
open util/boolean

sig Object {}

sig Property {}

sig DataType {}

abstract sig World {
	exists: some Object,
	Kind1: set exists:>Object,
	Kind2: set exists:>Object,
	Kind3: set exists:>Object,
	Association1: set Kind1 one -> one Kind2,
	Association2: set Kind1 one -> one Kind3
}{
	exists:>Object in Kind2+Kind3+Kind1
	disj[Kind2,Kind3,Kind1]
	disj[Kind1,Kind3+Kind2]
	disj[Kind3,Kind2+Kind1]
	disj[Kind2,Kind3+Kind1]
}

fact additionalFacts {
	continuous_existence[exists]
	elements_existence[Object+Property,exists]
}

fact rigidity {
	rigidity[Kind3,Object,exists]
}

fact rigidity {
	rigidity[Kind2,Object,exists]
}

fact rigidity {
	rigidity[Kind1,Object,exists]
}

fun visible : World->univ {
	exists
}


fun kind11 [x: World.Kind3,w: World] : set World.Kind1 {
	(w.Association2).x
}

fun kind1 [x: World.Kind2,w: World] : set World.Kind1 {
	(w.Association1).x
}

fun kind3 [x: World.Kind1,w: World] : set World.Kind3 {
	x.(w.Association2)
}

fun kind2 [x: World.Kind1,w: World] : set World.Kind2 {
	x.(w.Association1)
}

run { } for 10 but 3 World, 7 int


