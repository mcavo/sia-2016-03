package tests;

import buildings.BuildingsEngine;
import buildings.BuildingsProblem;
import buildings.heuristics.CompoundHeuristic;
import buildings.heuristics.SkylineHeuristic;
import buildings.heuristics.SudokuHeuristic;
import gps.SearchStrategy;
import models.CardinalDirection;
import utils.Parser;

public class Test {

	public static void main(String[] args) {
		int[][] dir = Parser.parseDirections();
		for (int i = 0; i < dir.length; i++) {
			for (int j = 0; j < dir[0].length; j++) {
				System.out.print(dir[i][j]);
			}
			System.out.println(" ");
		}
		String strategy = args[0];
		BuildingsProblem problem = new BuildingsProblem(
				dir[CardinalDirection.SOUTH.ordinal()],
				dir[CardinalDirection.NORTH.ordinal()],
				dir[CardinalDirection.EAST.ordinal()],
				dir[CardinalDirection.WEST.ordinal()], 
				new CompoundHeuristic(new SudokuHeuristic(), new SkylineHeuristic()));
		
		BuildingsEngine engine = new BuildingsEngine();
		switch(strategy){
		case "DFS":{
			engine.engine(problem, SearchStrategy.DFS,0);
			break;
		}
		case "BFS":{
			engine.engine(problem, SearchStrategy.BFS,0);
			break;
		}
		case "IDDFS":{
			int i=1;
			while(!(engine.engine(problem, SearchStrategy.DFS,i))){
				engine = new BuildingsEngine();
				i++;				
			}
			break;
		}
		case "GREEDY":{
			engine.engine(problem, SearchStrategy.GREEDY,0);
			break;
		}
		case "ASTAR":{
			engine.engine(problem, SearchStrategy.ASTAR,0);
			break;
		}		
		default:{
			System.out.println("Parametro invalido.");
		}
		}

		
	}

}
