package testing;

import solution.MaxCut;

public class MaxCutTests {
	public static void main(String[] args) {
		
		int edges[][] = new int [][] {{1,2},{2,3},{3,5},{4,1},{5,4},{4,2}};
		int weights[] = new int [] {2, 1, 4, 3, 2, 4};
		int nVertices = 5;
		
		MaxCut.maximumCut(edges,weights,nVertices);
		
		int edges2[][] = new int [][] {{1,2},{2,3},{3,4},{4,5},{1,4},{2,5},{3,5},{5,1}};
		int weights2[] = new int []   {2, 5, 4, 3, 2, 4, 7, 3};
		int nVertices2 = 5;
		
		MaxCut.maximumCut(edges2,weights2,nVertices2);
	}
}
