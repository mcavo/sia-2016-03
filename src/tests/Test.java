package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import buildings.BuildingsEngine;
import buildings.BuildingsProblem;
import buildings.heuristics.CompoundHeuristic;
import buildings.heuristics.SkylineHeuristic;
import buildings.heuristics.SudokuHeuristic;
import gps.SearchStrategy;
import utils.BuildingsParser;

public class Test {

	public static void main(String[] args) {
		
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(args[0]));
			BuildingsParser p = new BuildingsParser(br);
			
			BuildingsProblem problem = new BuildingsProblem(
					p.getSouth(),
					p.getNorth(),
					p.getEast(),
					p.getWest(), 
					new CompoundHeuristic(new SudokuHeuristic(), new SkylineHeuristic()));
			
			BuildingsEngine engine = new BuildingsEngine();
			switch(p.getSearchStrategy()){
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
			
			

		} catch (IOException e) {
			System.out.println("");
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
		
//		for (int i = 0; i < dir.length; i++) {
//			for (int j = 0; j < dir[0].length; j++) {
//				System.out.print(dir[i][j]);
//			}
//			System.out.println(" ");
//		}

		
	}

}
