package solution;

import gurobi.*;

public class MaxCut {
	public static void main(String[] args) {
	try {

		GRBEnv env = new GRBEnv("maxcut.log");
		GRBModel model = new GRBModel(env);
		
		model.set(GRB.StringAttr.ModelName, "MaxCut");
		
		//number of vertices
		int vertices = 5;
		//number of edges
		int edges = 6;
		//edge(origin,destination)
		int origin[]      = new int [] {1, 2, 3, 4, 5, 4};
		int destination[] = new int [] {2, 3, 5, 1, 4, 2};
		//edge weights
		int weights[] =  new int [] {2, 1, 4, 3, 2, 4};
		
		//x[i] true if in cut false if not 
		GRBVar[] x = new GRBVar[edges];
		for (int i = 0; i < vertices; ++i) {
			x[i] = model.addVar(0.0, 1.0, i, GRB.BINARY, "x[" + i + "]");
		}
		//objective cutweight
		GRBVar cutWeight = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "cutWeight");
		
		
		
		
		} catch (GRBException e) {
			System.out.println("Error code: " + e.getErrorCode() + ". " + e.getMessage());
			}
	}
}
