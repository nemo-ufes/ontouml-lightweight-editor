package br.ufes.inf.nemo.common.ontouml2graph;

public class GraphAlgo {

public static void fundamentalCycles(int n, int m, int nodei[], int nodej[], int fundcycle[][]) 
{ 
	int i,j,k,nodeu,nodev,components,numcycles,root,index,edges; 
	int low,len,up,node1,node2,node3; 
	int endnode[] = new int[m+1]; 
	int firstedges[] = new int[n+1]; 
	int nextnode[] = new int[n+1]; 
	int pointer[] = new int[n+1]; 
	int currentcycle[] = new int[n+1]; 
	boolean join; 

	// set up the forward star representation of the graph 
	k = 0; 
	for (i=1; i<=n-1; i++) { 
		firstedges[i] = k + 1; 
		for (j=1; j<=m; j++) { 
			nodeu = nodei[j]; 
			nodev = nodej[j]; 
			if ((nodeu == i) && (nodeu < nodev)) { 
				k++; 
				endnode[k] = nodev; 
			} 
			else { 
				if ((nodev == i) && (nodev < nodeu)) { 
					k ++; 
					endnode[k] = nodeu; 
				} 
			} 
		} 
	} 
	firstedges[n] = m + 1; 
	for (i=1; i<=n; i++)  
		nextnode[i] = 0; 
	components = 0; 
	numcycles = 0; 
	for (root=1; root<=n; root++)  
		if (nextnode[root] == 0) { 
			components++; 
			nextnode[root] = -1; 
			index = 1; 
			pointer[1] = root; 
			edges = 2; 
			do { 
				node3 = pointer[index]; 
				index--; 
				nextnode[node3] = -nextnode[node3]; 
				for (node2=1; node2<=n; node2++) { 
					join = false; 
					if (node2 != node3) { 
						if (node2 < node3) { 
							nodeu = node2; 
							nodev = node3; 
						} 
						else { 
							nodeu = node3; 
							nodev = node2; 
						} 
						low = firstedges[nodeu]; 
						up = firstedges[nodeu + 1]; 
						if (up > low) { 
							up--; 
							for (k=low; k<=up; k++) 
								if (endnode[k] == nodev) { 
									join = true; 
									break; 
								} 
						} 
					} 
					if (join) { 
						node1 = nextnode[node2]; 
						if (node1 == 0) { 
							nextnode[node2] = -node3; 
							index++; 
							pointer[index] = node2; 
						} 
						else { 
							if (node1 < 0) { 
								//generate the next cycle 
								numcycles++; 
								len = 3; 
								node1 = -node1; 
								currentcycle[1] = node1; 
								currentcycle[2] = node2; 
								currentcycle[3] = node3; 
								i = node3; 
								while (true) { 
									j = nextnode[i]; 
									if (j == node1) break; 
									len++; 
									currentcycle[len] = j; 
									i = j; 
								} 
								//store the current fundamental cycle 
								fundcycle[numcycles][0] = len; 
								for (i=1; i<=len; i++) 
									fundcycle[numcycles][i] = currentcycle[i]; 
							} 
						} 
					} 
				} 
				edges++; 
			} while ((edges <= n) && (index > 0)); 
			nextnode[root] = 0; 
			node3 = pointer[1]; 
			nextnode[node3] = Math.abs(nextnode[node3]); 
		} 
		fundcycle[0][0] = numcycles; 
		fundcycle[0][1] = components; 
	}
	
}
