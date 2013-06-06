module EA_Model

open world_structure[World]
open ontological_properties[World]
open util/relation
open util/boolean
open util/ternary

sig Object {}

sig Property {}

sig DataType {}

sig String_ {}

abstract sig World {
	exists: some Object+Property,
	DeceasedPerson: set exists:>Object,
	RoadTrafficAccident2: set exists:>Property,
	RearEndCollision: set exists:>Property,
	Roadway: set exists:>Object,
	Passenger: set exists:>Object,
	LivingPerson: set exists:>Object,
	Driver: set exists:>Object,
	Vehicle: set exists:>Object,
	CrashedVehicle: set exists:>Object,
	Travel: set exists:>Property,
	Person: set exists:>Object,
	TravelingVehicle: set exists:>Object,
	Victim: set exists:>Object,
	Traveler: set exists:>Object,
	RoadwayWithAccident: set exists:>Object,
	has1: set Travel one -> some Traveler,
	has: set RoadTrafficAccident2 one -> some Victim,
	occurson: set RoadTrafficAccident2 some -> one RoadwayWithAccident,
	involves: set RoadTrafficAccident2 one -> some CrashedVehicle,
	crasheson: set RoadwayWithAccident one -> some CrashedVehicle,
	madeby: set Travel one -> one TravelingVehicle,
	fatalvictims: set RoadTrafficAccident2 set -> one Int
}{
	disj[Person,Roadway+Vehicle]
	disj[Vehicle,Person+Roadway]
	disj[Roadway,Person+Vehicle]
	disj[Travel,RoadTrafficAccident2]
	disj[RoadTrafficAccident2,Travel]
	exists:>Object in Roadway+Vehicle+Person
	disj[Roadway,Vehicle,Person]
	exists:>Property in RoadTrafficAccident2+RearEndCollision+Travel
}

fact additionalFacts {
	linear_existence[exists]
	all_elements_exists[Object+Property,exists]
}

fact rigidity {
	rigidity[Person,Object,exists]
}

fact rigidity {
	rigidity[Roadway,Object,exists]
}

fact rigidity {
	rigidity[RoadTrafficAccident2,Property,exists]
}

fact rigidity {
	rigidity[RearEndCollision,Property,exists]
}

fact rigidity {
	rigidity[Vehicle,Object,exists]
}

fact rigidity {
	rigidity[Travel,Property,exists]
}

fact generalization {
	LivingPerson in Person
}

fact generalization {
	DeceasedPerson in Person
}

fact generalization {
	Driver in Traveler
}

fact generalization {
	RearEndCollision in RoadTrafficAccident2
}

fact generalization {
	Traveler in Person
}

fact generalization {
	RoadwayWithAccident in Roadway
}

fact generalization {
	Passenger in Traveler
}

fact generalization {
	CrashedVehicle in TravelingVehicle
}

fact generalization {
	Victim in Traveler
}

fact generalization {
	TravelingVehicle in Vehicle
}

fact generalizationSet {
	Traveler = Driver+Passenger
	disj[Driver,Passenger]
}

fact generalizationSet {
	Person = LivingPerson+DeceasedPerson
	disj[DeceasedPerson,LivingPerson]
}

fun visible : World some -> some univ {
	exists+select13[fatalvictims]
}

fun travel [x: World.Traveler,w: World] : set World.Travel {
	(w.has1).x
}

fun travelers [x: World.Travel,w: World] : set World.Traveler {
	x.(w.has1)
}

fun accident1 [x: World.Victim,w: World] : set World.RoadTrafficAccident2 {
	(w.has).x
}

fun victims [x: World.RoadTrafficAccident2,w: World] : set World.Victim {
	x.(w.has)
}

fun accident [x: World.CrashedVehicle,w: World] : set World.RoadTrafficAccident2 {
	(w.involves).x
}

fun vehicles [x: World.RoadTrafficAccident2,w: World] : set World.CrashedVehicle {
	x.(w.involves)
}

fact derivations {
	derivation[crasheson,occurson,involves]
}

fun travel1 [x: World.TravelingVehicle,w: World] : set World.Travel {
	(w.madeby).x
}

fun vehicle [x: World.Travel,w: World] : set World.TravelingVehicle {
	x.(w.madeby)
}

fact relatorConstraint {
	all w: World | all x: w.RoadTrafficAccident2 | # (x.(w.occurson)+x.(w.has)+x.(w.involves)) >= 2
}

fact relatorConstraint {
	all w: World | all x: w.RearEndCollision | # (x.(w.occurson)+x.(w.has)+x.(w.involves)) >= 2
}

fact relatorConstraint {
	all w: World | all x: w.Travel | # (x.(w.has1)+x.(w.madeby)) >= 2
}

fact associationProperties {
	immutable_target[Travel,has1]
	immutable_target[RoadTrafficAccident2,has]
	immutable_target[RoadTrafficAccident2,occurson]
	immutable_target[RoadTrafficAccident2,involves]
	immutable_target[Travel,madeby]
}

run { } for 10 but 3 World, 7 int


