module world_structure[World]

some abstract sig TemporalWorld extends World{
	next: set TemporalWorld -- Immediate next moments.
}{
	this not in this.^(@next) -- There are no temporal cicles.
	lone ((@next).this) -- A world can be the immediate next momment of at maximum one world.
}

one sig CurrentWorld extends TemporalWorld {} {
	next in FutureWorld
}

sig PastWorld extends TemporalWorld {} {
	next in (PastWorld + CounterfactualWorld + CurrentWorld)
	CurrentWorld in this.^@next -- All past worlds can reach the current moment.
}
sig FutureWorld extends TemporalWorld {} {
	next in FutureWorld
	this in CurrentWorld.^@next -- All future worlds can be reached by the current moment.
}

sig CounterfactualWorld extends TemporalWorld {} {
	next in CounterfactualWorld
	this in PastWorld.^@next -- All past worlds can reach the counterfactual moment.
}

/*Objects can't die and come to life later*/
pred continuous_existence [exists: World->univ] {
	all w : World, x: (@next.w).exists | (x not in w.exists) => (x not in (( w. ^next).exists))
}

assert non_local_accessibility {
	no w: TemporalWorld | w in w.next
}

assert no_temporal_cicles {
	no w: TemporalWorld | w in w.^next
}

assert no_joining_histories {
	all w: TemporalWorld | #(next.w) <= 1
}

assert every_world_reach_the_current_world {
	all w: TemporalWorld | CurrentWorld in w.^(next + ~next)
}

assert future_worlds_cannot_reach_the_current_world_by_the_future{
	no w: FutureWorld | CurrentWorld in w.^next
}

check non_local_accessibility for 10
check no_temporal_cicles for 10
check no_joining_histories for 10
check every_world_reach_the_current_world for 10
check future_worlds_cannot_reach_the_current_world_by_the_future for 10
