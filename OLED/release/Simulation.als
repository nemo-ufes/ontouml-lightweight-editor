open util/ordering[State]
sig Quantity1{}
sig State
{
	exists: set Quantity1,

}	{
	all x: exists | x not in this.next.@exists implies x not in this.^next.@exists
	}
fun visible : State->univ
{
	exists
}
fact all_individuals_exist_at_some_point
{
	Quantity1 = State.exists
}
run{} for 5
