module associations[Object,Property,World]

pred mediates[p:Property,o:Object] { some f:World$.subfields | o in World.(f.value)[p] }

pred mat_related[o1,o2:Object]{
	o1 not = o2
	some p:Property | mediates[p,o1] and mediates[p,o2]
}


//Associação existe
pred direct_rel[x1,x2:Object+Property]{
	 direct_rel_in_w[x1,x2,World]
}

//Associação existe nesse mundo
pred direct_rel_in_w[x1,x2:Object+Property, w:World]{
	 some f:World$.subfields | 
			x1 in w.(f.value)[x2] 
			or
			x2 in w.(f.value)[x1]
}

//alpha de associação
pred direct_rel_alpha[x1,x2:Object+Property, w:World]{
	not direct_rel_in_w[x1,x2,w]
	direct_rel_in_w[x1,x2,w.next]
}

//alpha de associação estrito (nada mais muda)
pred direct_rel_alpha_strict[x1,x2:Object+Property, w:World]{
	not direct_rel_in_w[x1,x2,w]
	direct_rel_in_w[x1,x2,w.next]
	x1 in w.exists
	x1 in w.next.exists
}


//alpha de associação
pred direct_rel_gama[x1,x2:Object+Property, w:World]{
	direct_rel_in_w[x1,x2,w]
	not direct_rel_in_w[x1,x2,w.next]
}


//aqui, tentando fazer só referente ao tipo de relação e nao aos membros relacionados
//Associação existe nesse mundo
pred direct_rel_in_w[r:(Object+Property)->(Object+Property), w:World]{
	 some f:World$.subfields | 
			r in w.(f.value)
			or
			~r in w.(f.value)
}


