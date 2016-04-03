package tests;

import gps.SearchStrategy;
import gps.api.GPSState;
import buildings.BuildingsEngine;
import buildings.BuildingsProblem;

public class Test {

	
	public static void main(String[] args) {
		int[] south = new int[]{0,0,0,0,0};
		int[] north = new int[]{0,0,0,0,0};
		int[] west = new int[]{0,0,0,0,0};
		int[] east = new int[]{0,0,0,0,0};
		BuildingsProblem problem = new BuildingsProblem(south, north, east, west);
		BuildingsEngine engine = new BuildingsEngine();
		engine.engine(problem, SearchStrategy.DFS);
	}
	
	
}
