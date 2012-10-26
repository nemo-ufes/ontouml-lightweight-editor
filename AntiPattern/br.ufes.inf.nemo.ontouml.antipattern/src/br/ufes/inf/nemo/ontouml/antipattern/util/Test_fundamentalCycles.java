package br.ufes.inf.nemo.ontouml.antipattern.util;

public class Test_fundamentalCycles extends Object { 
   
  public static void main(String args[]) { 
 
	boolean directed;
    //int n=9; 
    //int m=10; 
    //int nodei[] = {0,4,3,6,5,8,3,4,1,9,2}; 
    //int nodej[] = {0,2,7,2,7,4,5,9,5,6,8}; 
    
    int n = 10; 
    int m = 15;
    int nodei[] = {0,9,1,4,1,1,2,2,3,5,3,3,4, 7,10,10}; 
    int nodej[] = {0,5,4,7,6,9,6,8,5,8,6,7,8,10, 9,2}; 
    
    int fundcycle[][] = new int[m-1][n+1]; 
 
    GraphAlgo.fundamentalCycles(n,m,nodei,nodej,fundcycle); 
    System.out.println("number of components of the graph = " + 
                     fundcycle[0][1]  + "\n"); 
    for (int i=1; i<=fundcycle[0][0]; i++) { 
      System.out.print("nodes in cycle " + i + ": "); 
      for (int j=1; j<=fundcycle[i][0]; j++) 
        System.out.printf("%3d", fundcycle[i][j]); 
      System.out.println(); 
    } 
  } 
} 