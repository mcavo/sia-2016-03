package buildings.heuristics;

import gps.api.GPSState;

public interface Heuristic {

	public int getH(GPSState state);
	
}
