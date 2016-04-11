package buildings.heuristics;

import buildings.BuildingsState;
import gps.api.GPSState;

public class SkylineHeuristic2 implements Heuristic {

	private int LENGTH;
	int[][] map;
	private int[] south;
	private int[] north;
	private int[] east;
	private int[] west;
	private int[] horizontal;
	private int[] vertical;
	
	@Override
	public int getH(GPSState state) {
		
		map = ((BuildingsState)state).getMap();
		south = ((BuildingsState)state).getSouth();
		north = ((BuildingsState)state).getNorth();
		east = ((BuildingsState)state).getEast();
		west = ((BuildingsState)state).getWest();
		LENGTH = south.length;
		horizontal = new int[LENGTH];
		vertical = new int[LENGTH];
		difWest();
		difNorth();
		difEast();
		difSouth();
		return (Math.max(difWest()+difEast(), difNorth()+difSouth()));
		
	}

	private int difWest() {
		int counter =0;
		for (int i = 0; i < LENGTH; i++) {			
			if (west[i] > 0) {
				int count = 0, max = 0;
				for (int j = 0; j < LENGTH; j++) {
					if (map[i][j] > max) {
						max = map[i][j];
						count++;
					}
				}
				if (west[i] != count) {
					horizontal[i]=1;
					counter++;
				}
			}
		}
		return counter;
	}

	private int difEast() {
		int counter =0;
		for (int i = 0; i < LENGTH; i++) {
			if (east[i] > 0) {
				int count = 0, max = 0;
				for (int j = LENGTH - 1; j >= 0; j--) {
					if (map[i][j] > max) {
						max = map[i][j];
						count++;
					}
				}
				if (east[i] != count) {
					horizontal[i]=1;
					counter++;
				}
			}
		}
		return counter;
	}

	private int difNorth() {
		int counter = 0;
		for (int i = 0; i < LENGTH; i++) {
			if (north[i] > 0) {
				int count = 0, max = 0;
				for (int j = 0; j < LENGTH; j++) {
					if (map[j][i] > max) {
						max = map[j][i];
						count++;
					}
				}
				if (north[i] != count) {
					vertical[i]=1;
					counter++;
				}
			}
		}
		return counter;
	}

	private int difSouth() {
		int counter = 0;
		for (int i = 0; i < LENGTH; i++) {
			if (south[i] > 0) {
				int count = 0, max = 0;
				for (int j = LENGTH - 1; j >= 0; j--) {
					if (map[j][i] > max) {
						max = map[j][i];
						count++;
					}
				}
				if (south[i] != count) {
					vertical[i]=1;
					counter++;
				}
			}
		}
		return counter;
	}

}