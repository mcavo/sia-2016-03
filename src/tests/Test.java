package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import buildings.BuildingsEngine;
import buildings.BuildingsProblem;
import utils.BuildingsParser;
import utils.OutputManager;

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
					p.getHeuristic(), p.getInitialBoard());

			BuildingsEngine engine = new BuildingsEngine();
			start = System.currentTimeMillis();
			try {
				engine.bEngine(problem, p.getSearchStrategy());
				output.writeln("Strategy used : "+p.getSearchStrategy().name());
			} catch(IllegalArgumentException e) {
				output.writeln("Invalid Strategy.");
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
