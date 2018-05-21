package solution;

import gurobi.*;

public class MaxCut {
	public static void main(String[] args) {
	try {

		GRBEnv env = new GRBEnv("maxcut.log");
		GRBModel model = new GRBModel(env);
		
		model.set(GRB.StringAttr.ModelName, "MaxCut");
		
		//edge(origin,destination)
		int edges[][]  = new int [][] {{1,2},{2,3},{3,5},{4,1},{5,4},{4,2}};
		//edge weights
		int weights[] =  new int [] {2, 1, 4, 3, 2, 4};
		//number of vertices
		int nVertices = 5;
		//number of edges
		int nEdges = edges.length;
		
		GRBLinExpr constraint1;
		GRBLinExpr constraint2;
		
		//x[i] true if in cut false if not 
		GRBVar[] x = new GRBVar[nVertices];
		for (int i = 0; i <nVertices; i++) {
			 x[i] = model.addVar(0.0, 1.0, i, GRB.BINARY, "x[" + i + "]");
		}
		GRBLinExpr maxCut = new GRBLinExpr();
		for (int i = 0; i <nVertices; i++) {
			maxCut.addTerm(1.0, x[i]);
		}
		
		//incut[i] is true if edge is cut
		GRBVar[] y = new GRBVar[nEdges];
		for (int i = 0; i <nEdges; i++) {
			 y[i] = model.addVar(0.0, 1.0, weights[i], GRB.BINARY, "incut[" + i + "]");
			 constraint1 = new GRBLinExpr();
			 constraint1.addTerm(1.0, x[edges[i][0]-1]);
			 constraint1.addTerm(1.0, x[edges[i][1]-1]);
			 model.addConstr(y[i], GRB.LESS_EQUAL, constraint1, "");
			 constraint2 = new GRBLinExpr();
			 constraint2.multAdd(-1, constraint1);
			 constraint2.addConstant(2);
			 model.addConstr(y[i], GRB.LESS_EQUAL, constraint2, "");
		}
		GRBLinExpr incut = new GRBLinExpr();
		for (int i = 0; i <nEdges; i++) {
			incut.addTerm(weights[i], y[i]);
		}
		

		model.setObjective(incut,GRB.MAXIMIZE);	
		model.optimize();
		
		
		System.out.println("Cut Weight:" + " " + incut.getValue());
		
		for (int i = 0; i <nVertices; i++) {
		  System.out.println(x[i].get(GRB.StringAttr.VarName) +
                  " " + x[i].get(GRB.DoubleAttr.X));
		}
		
		for (int i = 0; i <nEdges; i++) {
			  System.out.println(y[i].get(GRB.StringAttr.VarName) +
	                  " " + y[i].get(GRB.DoubleAttr.X));
			}
		
		model.dispose();
		env.dispose();
		
		} catch (GRBException e) {
			System.out.println("Error code: " + e.getErrorCode() + ". " + e.getMessage());
			}
	}
}
