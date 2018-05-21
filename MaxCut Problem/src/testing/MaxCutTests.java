package testing;

import solution.MaxCut;

public class MaxCutTests {
	public static void main(String[] args) {
		
		int edges[][] = new int [][] {{1,2},{2,3},{3,5},{4,1},{5,4},{4,2}};
		int weights[] = new int [] {2, 1, 4, 3, 2, 4};
		int nVertices = 5;
		
		MaxCut.maximumCut(edges,weights,nVertices);
		
		
		
	}
}
