module testeee

open world_structure[World]
open ontological_properties[World]

sig Substantial {
}

sig Substantial {
}

abstract sig World {
	exists: some (Substantial),
	Person: set (exists :> Substantial),
	Student: set (exists :> Substantial),
	Man: set (exists :> Substantial),
	Woman: set (exists :> Substantial),
	Worker: set (exists :> Substantial),
	MscStudent: set (exists :> Substantial),
	PhdStudent: set (exists :> Substantial),
	Footballer: set (exists :> Substantial),
	Basketballer: set (exists :> Substantial),
	BallSportsPlayer: set (exists :> Substantial),
	SpeedSportsPlayer: set (exists :> Substantial),
	Swimmer: set (exists :> Substantial),
	Runner: set (exists :> Substantial)
}
{
	Student in (Person)
	Man in (Person)
	Woman in (Person)
	Worker in (Person)
	MscStudent in (Student)
	PhdStudent in (Student)
	Footballer in (BallSportsPlayer)
	Basketballer in (BallSportsPlayer)
	BallSportsPlayer in (Person)
	SpeedSportsPlayer in (Person)
	Swimmer in (SpeedSportsPlayer)
	Runner in (SpeedSportsPlayer)
	exists :> Substantial in (Person + Student + Man + Woman + Worker + MscStudent + PhdStudent + Footballer + Basketballer + BallSportsPlayer + SpeedSportsPlayer + Swimmer + Runner)

}

fact additional_facts {
	linear_existence[exists]
	all_elements_exists[Substantial,exists]
}

fact all_rigid_classes {
	rigidity[Person,Substantial,exists]
	rigidity[Man,Substantial,exists]
	rigidity[Woman,Substantial,exists]
}

fun visible : World->univ{
	exists
}

run {
} for 5
