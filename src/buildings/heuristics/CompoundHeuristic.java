package buildings.heuristics;

import gps.api.GPSState;

public class CompoundHeuristic implements Heuristic {

	Heuristic h1;
	Heuristic h2;	
	
	public CompoundHeuristic(Heuristic h1, Heuristic h2) {
		this.h1 = h1;
		this.h2 = h2;
	}


	@Override
	public int getH(GPSState state) {
		return Math.max(h1.getH(state), h2.getH(state));
	}

}
