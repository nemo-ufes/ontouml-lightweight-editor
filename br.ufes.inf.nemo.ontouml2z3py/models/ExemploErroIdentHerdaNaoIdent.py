from z3 import * 

''' ----- Constants definitions -----''' 
C1 = Int('C1')
C2 = Int('C2')
C3 = Int('C3')

''' ----- Functions definitions -----''' 
World = Function ('World', IntSort(), BoolSort())
CurrentWorld = Function ('CurrentWorld', IntSort(), BoolSort())
PastWorld = Function ('PastWorld', IntSort(), BoolSort())
FutureWorld = Function ('FutureWorld', IntSort(), BoolSort())
next = Function ('next', IntSort(), IntSort(), BoolSort())
recursiveNext = Function ('recursiveNext', IntSort(), IntSort(), BoolSort())
exists = Function ('exists', IntSort(), IntSort(), BoolSort())
Aluno = Function ('Aluno', IntSort(), IntSort(), BoolSort())
Pessoa = Function ('Pessoa', IntSort(), IntSort(), BoolSort())

''' ----- Formulas definitions -----''' 

'''The two arguments fo the function next are worlds''' 
F1 = ForAll ([C1,C2], Implies(next(C1,C2), And(World(C1), World(C2))))

'''Every world is the next of, at most, one world''' 
F2 = ForAll ([C1,C2,C3], Implies(And(next(C1,C2), next(C3,C2)), C1 == C3))

'''In a Linear Time Structure, Every world has at most one next world''' 
F3 = ForAll ([C1,C2,C3], Implies(And(next(C1,C2), next(C1,C3)), C2 == C3))

'''The function next is irreflexive''' 
F4 = ForAll (C1, Not(next(C1,C1)))

'''Here we defined the recursiveNext function as a helper to express the transitive closure of the function next''' 
F5 = ForAll ([C1,C3], recursiveNext(C1,C3) == Or(next(C1,C3), Exists (C2, And(recursiveNext(C1,C2), next(C2,C3)))))

'''The next function is assimetric when considering its transitive closure''' 
F6 = ForAll ([C1,C2], Implies(recursiveNext(C1,C2), Not(recursiveNext(C2,C1))))

'''CurrentWorld, FutureWorld, PastWorld are subtypes of World''' 
F7 = ForAll (C1, Implies(Or(PastWorld(C1), Or(FutureWorld(C1), CurrentWorld(C1))), World(C1)))

'''The Specializazion from World in CurrentWorld, FutureWorld, PastWorld is complete''' 
F8 = ForAll (C1, Implies(World(C1), Or(PastWorld(C1), Or(FutureWorld(C1), CurrentWorld(C1)))))

'''Disjoint Specialization Set: The Specializazion from World in CurrentWorld, FutureWorld, PastWorld is disjoint''' 
F9 = ForAll (C1, Not(Or(Or(And(CurrentWorld(C1), FutureWorld(C1)), And(CurrentWorld(C1), PastWorld(C1))), And(FutureWorld(C1), PastWorld(C1)))))

'''There is at least one current world''' 
F10 = Exists (C1, CurrentWorld(C1))

'''There is at most one Current World''' 
F11 = ForAll ([C1,C2], Implies(And(CurrentWorld(C1), CurrentWorld(C2)), C1 == C2))

'''The next world of a current world is a future world''' 
F12 = ForAll ([C1,C2], Implies(And(CurrentWorld(C1), next(C1,C2)), FutureWorld(C2)))

'''The next world of a future world is a future world''' 
F13 = ForAll ([C1,C2], Implies(And(FutureWorld(C1), next(C1,C2)), FutureWorld(C2)))

'''Every future world is acessible from the current world by the recursiveNext transitive closure''' 
F14 = ForAll (C2, Implies(FutureWorld(C2), Exists (C1, And(CurrentWorld(C1), recursiveNext(C1,C2)))))

'''The next world of a past world may be a cuurent world, a counterfactual world (only in branch in time structures) or another past world''' 
F15 = ForAll ([C1,C2], Implies(And(PastWorld(C1), next(C1,C2)), Or(CurrentWorld(C2), PastWorld(C2))))

'''The current world is acessible from all past worlds by the recursiveNext transitive closure''' 
F16 = ForAll (C1, Implies(PastWorld(C1), Exists (C2, And(CurrentWorld(C2), recursiveNext(C1,C2)))))

'''Typing the arguments: If Aluno(x,y) holds then x is a world and y exists in x as a Aluno''' 
F17 = ForAll ([C1,C2], Implies(Aluno(C1,C2), And(World(C1), exists(C1,C2))))

'''Formula to avoid Trivialization: Exists at least one Aluno in one world''' 
F18 = Exists ([C1,C2], Aluno(C1,C2))

'''Typing the arguments: If Pessoa(x,y) holds then x is a world and y exists in x as a Pessoa''' 
F19 = ForAll ([C1,C2], Implies(Pessoa(C1,C2), And(World(C1), exists(C1,C2))))

'''Formula to avoid Trivialization: Exists at least one Pessoa in one world''' 
F20 = Exists ([C1,C2], Pessoa(C1,C2))

'''Pessoa is subtype of Aluno''' 
F21 = ForAll ([C1,C2], Implies(Pessoa(C1,C2), Aluno(C1,C2)))

'''Pessoa is a rigid type''' 
F22 = ForAll ([C1,C2,C3], Implies(And(Pessoa(C1,C2), exists(C3,C2)), Pessoa(C3,C2)))

'''Aluno is a anti-rigid type (this formula considers a branch-in-time structure and so, that counterfactual worlds exist''' 
F23 = ForAll ([C1,C3], Implies(Aluno(C1,C3), Exists (C2, And(exists(C2,C3), Not(Aluno(C2,C3))))))

'''Everything that exists in a world must be of a type that provides an identity principle''' 
F24 = ForAll ([C1,C2], Implies(exists(C1,C2), And(World(C1), Pessoa(C1,C2))))

''' ----- Solver Configuration -----''' 

a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24 = Bools('a1 a2 a3 a4 a5 a6 a7 a8 a9 a10 a11 a12 a13 a14 a15 a16 a17 a18 a19 a20 a21 a22 a23 a24')

s=Solver()
s.add(Implies(a1, F1))
s.add(Implies(a2, F2))
s.add(Implies(a3, F3))
s.add(Implies(a4, F4))
s.add(Implies(a5, F5))
s.add(Implies(a6, F6))
s.add(Implies(a7, F7))
s.add(Implies(a8, F8))
s.add(Implies(a9, F9))
s.add(Implies(a10, F10))
s.add(Implies(a11, F11))
s.add(Implies(a12, F12))
s.add(Implies(a13, F13))
s.add(Implies(a14, F14))
s.add(Implies(a15, F15))
s.add(Implies(a16, F16))
s.add(Implies(a17, F17))
s.add(Implies(a18, F18))
s.add(Implies(a19, F19))
s.add(Implies(a20, F20))
s.add(Implies(a21, F21))
s.add(Implies(a22, F22))
s.add(Implies(a23, F23))
s.add(Implies(a24, F24))
resp = s.check(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24)
if resp == sat:
	print 'Modelo satisfativel'
else:
	print 'Modelo insatisfativel'
	print s.unsat_core()
