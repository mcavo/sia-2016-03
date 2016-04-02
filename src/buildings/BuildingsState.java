package buildings;

import models.CardinalDirection;

public class BuildingsState {
	
	private static int LENGTH = 5;
	private static int DIRECTIONS = 4;
	
	private int buildingsSeen[][] = new int[DIRECTIONS][LENGTH];
	private int map[][] = new int[LENGTH][LENGTH];
	
	public BuildingsState(int south[], int north[], int east[], int west[]) {
		if(checkLength(south, north, east, west))
			throw new IllegalStateException();
		if(checkValues(south, north, east, west))
			throw new IllegalStateException();
		buildingsSeen[CardinalDirection.SOUTH.ordinal()]=south;
		buildingsSeen[CardinalDirection.NORTH.ordinal()]=north;
		buildingsSeen[CardinalDirection.EAST.ordinal()]=east;
		buildingsSeen[CardinalDirection.WEST.ordinal()]=west;
		map = initialMap();
	}

	private int[][] initialMap() {
		int imap[][] = new int[LENGTH][LENGTH];
		for(int i=0; i<LENGTH ;i++) {
			for(int j=0; j<LENGTH ;j++) {
				imap[i][j] = j+1;
			}
		}
		return imap;
	}
	
	private boolean checkLength(int south[], int north[], int east[], int west[]) {
		return south.length != LENGTH || north.length != LENGTH || east.length != LENGTH || west.length != LENGTH;
	}
	
	private boolean checkValues(int south[], int north[], int east[], int west[]) {
		for (int i=0; i<LENGTH ; i++)
			if (south[i]>LENGTH || south[i]<1 || north[i]>LENGTH || north[i]<1 || east[i]>LENGTH || east[i]<1 || west[i]>LENGTH || west[i]<1)
				return true;
		return false;
	}
	
	//TODO: Hacer que los -- sirvan para un LENGTH generico y no para 5
	@Override 
	public String toString() {
		StringBuffer state = new StringBuffer("    ");
		for(int i=0; i<LENGTH ; i++) {
			state.append(buildingsSeen[CardinalDirection.NORTH.ordinal()][i]+" ");
		}
		state.append("\n   -----------\n");
		for(int i=0; i<LENGTH ; i++) {
			state.append(buildingsSeen[CardinalDirection.WEST.ordinal()][i]+" | ");
			for(int j=0; j<LENGTH ; j++)
				state.append(map[i][j]+" ");
			state.append("| "+buildingsSeen[CardinalDirection.EAST.ordinal()][i]+"\n");
		}
		state.append("   -----------\n    ");
		for(int i=0; i<LENGTH ; i++) {
			state.append(buildingsSeen[CardinalDirection.SOUTH.ordinal()][i]+" ");
		}
		
		return state.toString();
	}
		
}
