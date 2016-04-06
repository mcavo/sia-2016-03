package tests;

import gps.SearchStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import utils.BuildingsParser;
import utils.OutputManager;
import buildings.BuildingsEngine;
import buildings.BuildingsProblem;
import buildings.heuristics.CompoundHeuristic;
import buildings.heuristics.SkylineHeuristic;
import buildings.heuristics.SudokuHeuristic;

public class Test {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		if(args.length != 2){
			System.out.println("Invalid parameters");
			return;
		}
			
		long end = 0;
		long start = 0;
		BufferedReader br = null;
		OutputManager output = OutputManager.getInstance();

		try {

			br = new BufferedReader(new FileReader(args[0]));
			BuildingsParser p = new BuildingsParser(br);
			output.setFile(new FileWriter(args[1]));
			output.writeln("INPUT\n");
			BufferedReader br2 = new BufferedReader(new FileReader(args[0]));
			while(output.writeln(br2.readLine())){
				//Do nothing.
			}
			output.writeln("\nOUTPUT\n");
			BuildingsProblem problem = new BuildingsProblem(p.getSouth(),
					p.getNorth(), p.getEast(), p.getWest(),
					new CompoundHeuristic(new SudokuHeuristic(),
							new SkylineHeuristic()), p.getInitialBoard());

			BuildingsEngine engine = new BuildingsEngine();
			start = System.currentTimeMillis();
			switch (p.getSearchStrategy()) {
			case "DFS": {
				engine.engine(problem, SearchStrategy.DFS, 0);
				output.writeln("Strategy used : DFS");
				break;
			}
			case "BFS": {
				engine.engine(problem, SearchStrategy.BFS, 0);
				output.writeln("Strategy used : BFS");
				break;
			}
			case "IDDFS": {
				int i = 1;
				while (!(engine.engine(problem, SearchStrategy.DFS, i))) {
					engine = new BuildingsEngine();
					i++;
				}
				output.writeln("Strategy used : IDDFS");
				break;
			}
			case "GREEDY": {
				engine.engine(problem, SearchStrategy.GREEDY, 0);
				output.writeln("Strategy used : GREEDY");
				break;
			}
			case "ASTAR": {
				engine.engine(problem, SearchStrategy.ASTAR, 0);
				output.writeln("Strategy used : ASTAR");
				break;
			}
			default: {
				output.writeln("Invalid Strategy");
			}
			}

			end = System.currentTimeMillis();
			output.writeln("It took " + (end - start)
					+ " milliseconds to finish.");
			output.close();

		} catch (IOException e) {
			System.out.println("");
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();

			}
		}

	}

}
