package tests;

import gps.SearchStrategy;
import models.CardinalDirection;
import utils.Parser;
import buildings.BuildingsEngine;
import buildings.BuildingsProblem;
import buildings.heuristics.CompoundHeuristic;
import buildings.heuristics.SkylineHeuristic;
import buildings.heuristics.SudokuHeuristic;

public class Test {

	public static void main(String[] args) {
		int[][] dir = Parser.parseDirections();
		for (int i = 0; i < dir.length; i++) {
			for (int j = 0; j < dir[0].length; j++) {
				System.out.print(dir[i][j]);
			}
			System.out.println(" ");
		}
		BuildingsProblem problem = new BuildingsProblem(
				dir[CardinalDirection.SOUTH.ordinal()],
				dir[CardinalDirection.NORTH.ordinal()],
				dir[CardinalDirection.EAST.ordinal()],
				dir[CardinalDirection.WEST.ordinal()], 
				new CompoundHeuristic(new SudokuHeuristic(), new SkylineHeuristic()));
		BuildingsEngine engine = new BuildingsEngine();
		engine.engine(problem, SearchStrategy.GREEDY);
	}

}
