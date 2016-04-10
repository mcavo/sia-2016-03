package models;

import buildings.heuristics.CompoundHeuristic;
import buildings.heuristics.Heuristic;
import buildings.heuristics.SkylineHeuristic;
import buildings.heuristics.SudokuHeuristic;

public enum BuildingsHeuristic {
	Heuristic1, Heuristic2;
	
	public Heuristic heuristic() {
		switch(this) {
		case Heuristic1: return new CompoundHeuristic(new SudokuHeuristic(),
				new SkylineHeuristic());
		case Heuristic2: return new CompoundHeuristic(new SudokuHeuristic(),
				new SkylineHeuristic());
		default: throw new IllegalStateException();
		}
			
	}
}

