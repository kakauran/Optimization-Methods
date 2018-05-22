package solution;

import gurobi.*;

public class MaxCut {
	public static void maximumCut(int edges[][],int weights[], int nVertices) {
		//edge(u,v), edge weights, number of vertices
	try {
		GRBEnv env = new GRBEnv("maxcut.log");
		GRBModel model = new GRBModel(env);
		
		model.set(GRB.StringAttr.ModelName, "MaxCut");
		
		//number of edges
		int nEdges = edges.length;
		
		GRBLinExpr constraint1;
		GRBLinExpr constraint2;
		
		//x[i] true if vertex in cut false if not 
		GRBVar[] x = new GRBVar[nVertices];
		for (int i = 0; i <nVertices; i++) {
			 int index = i+1;
			 x[i] = model.addVar(0.0, 1.0, i, GRB.BINARY, "x[" + index + "]");
		}

		
		//y[i] is true if edge is cut
		GRBVar[] y = new GRBVar[nEdges];
		for (int i = 0; i <nEdges; i++) {
			int index = i+1;
			 y[i] = model.addVar(0.0, 1.0, i, GRB.BINARY, "y[" + index +"]");
			 
			 //Add constraint1: y[(u,v)] <= x[u] + x[v]
			 constraint1 = new GRBLinExpr();
			 constraint1.addTerm(1.0, x[edges[i][0]-1]);
			 constraint1.addTerm(1.0, x[edges[i][1]-1]);
			 model.addConstr(y[i], GRB.LESS_EQUAL, constraint1, "");
			 
			 //Add constraint2: y[(u,v)] <= 2 - (x[u] + x[v])
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
		
		System.out.println("Cut Weight:" + " " + incut.getValue() + "\n");
		System.out.println("Vertex Set:");
		for (int i = 0; i <nVertices; i++) {
		  System.out.println(x[i].get(GRB.StringAttr.VarName) +
                  " " + x[i].get(GRB.DoubleAttr.X));
		}
		System.out.println("\n");
		System.out.println("Edges Cut: ");
		for (int i = 0; i <nEdges; i++) {
			  System.out.println(y[i].get(GRB.StringAttr.VarName) +
	                  " " + y[i].get(GRB.DoubleAttr.X));
		}
		
		System.out.println("--------------------------------------------------------");
		model.dispose();
		env.dispose();
		
		} catch (GRBException e) {
			System.out.println("Error code: " + e.getErrorCode() + ". " + e.getMessage());
			}
	}
}
