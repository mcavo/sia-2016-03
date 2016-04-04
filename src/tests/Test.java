package tests;

import buildings.BuildingsEngine;
import buildings.BuildingsProblem;
import gps.SearchStrategy;
import models.CardinalDirection;
import utils.Parser;

public class Test {

	
	public static void main(String[] args) {
		int[][] dir = Parser.parseDirections();
		BuildingsProblem problem = new BuildingsProblem(dir[CardinalDirection.SOUTH.ordinal()], dir[CardinalDirection.NORTH.ordinal()], dir[CardinalDirection.EAST.ordinal()], dir[CardinalDirection.WEST.ordinal()]);
		BuildingsEngine engine = new BuildingsEngine();
		engine.engine(problem, SearchStrategy.DFS);
	}
	
	
}
