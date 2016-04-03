package buildings;

import gps.api.GPSState;

import java.util.Arrays;

import models.CardinalDirection;

public class BuildingsState implements GPSState {

	private static int LENGTH;
	private static int DIRECTIONS = 4;

	private int buildingsSeen[][] = new int[DIRECTIONS][];
	private int map[][];

	public BuildingsState(int south[], int north[], int east[], int west[],
			int[][] map) {
		LENGTH = south.length;
		if (checkLength(south, north, east, west))
			throw new IllegalStateException();
		if (checkValues(south, north, east, west))
			throw new IllegalStateException();

		buildingsSeen[CardinalDirection.SOUTH.ordinal()] = south;
		buildingsSeen[CardinalDirection.NORTH.ordinal()] = north;
		buildingsSeen[CardinalDirection.EAST.ordinal()] = east;
		buildingsSeen[CardinalDirection.WEST.ordinal()] = west;
		this.map = map;
	}

	public static void main(String[] args) {
		int[] south = new int[] { 3, 1, 2, 2, 4 };
		int[] north = new int[] { 2, 5, 2, 2, 1 };
		int[] west = new int[] { 4, 1, 2, 3, 2 };
		int[] east = new int[] { 1, 2, 2, 2, 4 };
		BuildingsState s = new BuildingsState(south, north, east, west,
				new int[][] { new int[] { 2, 1, 3, 4, 5 },
						new int[] { 5, 2, 1, 3, 4 },
						new int[] { 4, 3, 5, 1, 2 },
						new int[] { 1, 4, 2, 5, 3 },
						new int[] { 3, 5, 4, 2, 1 } });
	}

	public int[] getSouth() {
		return buildingsSeen[CardinalDirection.SOUTH.ordinal()];
	}

	public int[] getNorth() {
		return buildingsSeen[CardinalDirection.NORTH.ordinal()];
	}

	public int[] getEast() {
		return buildingsSeen[CardinalDirection.EAST.ordinal()];
	}

	public int[] getWest() {
		return buildingsSeen[CardinalDirection.WEST.ordinal()];
	}

	public int[][] getMap() {
		return map;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BuildingsState other = (BuildingsState) obj;
		int[][] otherMap = other.getMap();
		for (int i = 0; i < LENGTH; i++) {
			for (int j = 0; j < LENGTH; j++) {
				if (otherMap[i][j] != map[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		int i = 0;
		for (int[] row : map) {
			for (int slot : row) {
				if (slot != -1) {
					hash += Math.pow(10, i) * slot;
				}
				i++;
			}
		}
		return hash;
	}

	private boolean checkLength(int south[], int north[], int east[],
			int west[]) {
		return south.length != LENGTH || north.length != LENGTH
				|| east.length != LENGTH || west.length != LENGTH;
	}

	private boolean checkValues(int south[], int north[], int east[],
			int west[]) {
		for (int i = 0; i < LENGTH; i++)
			if (south[i] > LENGTH || south[i] < 0 || north[i] > LENGTH
					|| north[i] < 0 || east[i] > LENGTH || east[i] < 0
					|| west[i] > LENGTH || west[i] < 0)
				return true;
		return false;
	}

	// TODO: Hacer que los -- sirvan para un LENGTH generico y no para 5
	@Override
	public String toString() {
		StringBuffer state = new StringBuffer("    ");
		for (int i = 0; i < LENGTH; i++) {
			state.append(buildingsSeen[CardinalDirection.NORTH.ordinal()][i]
					+ " ");
		}
		state.append("\n   -----------\n");
		for (int i = 0; i < LENGTH; i++) {
			state.append(buildingsSeen[CardinalDirection.WEST.ordinal()][i]
					+ " | ");
			for (int j = 0; j < LENGTH; j++)
				state.append(map[i][j] + " ");
			state.append("| "
					+ buildingsSeen[CardinalDirection.EAST.ordinal()][i] + "\n");
		}
		state.append("   -----------\n    ");
		for (int i = 0; i < LENGTH; i++) {
			state.append(buildingsSeen[CardinalDirection.SOUTH.ordinal()][i]
					+ " ");
		}

		return state.toString();
	}

	@Override
	public boolean isGoal() {
		return isSudoku() && validDirections();
	}

	private boolean validDirections() {

		return checkSouth() && checkNorth() && checkEast() && checkWest();
	}

	private boolean checkWest() {
		for (int i = 0; i < LENGTH; i++) {
			if (buildingsSeen[CardinalDirection.WEST.ordinal()][i] > 0) {
				int count = 0, max = 0;
				for (int j = 0; j < LENGTH; j++) {
					if (map[i][j] > max) {
						max = map[i][j];
						count++;
					}
				}
				if (buildingsSeen[CardinalDirection.WEST.ordinal()][i] != count) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkEast() {
		for (int i = 0; i < LENGTH; i++) {
			if (buildingsSeen[CardinalDirection.EAST.ordinal()][i] > 0) {
				int count = 0, max = 0;
				for (int j = LENGTH - 1; j >= 0; j--) {
					if (map[i][j] > max) {
						max = map[i][j];
						count++;
					}
				}
				if (buildingsSeen[CardinalDirection.EAST.ordinal()][i] != count) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkNorth() {
		for (int i = 0; i < LENGTH; i++) {
			if (buildingsSeen[CardinalDirection.NORTH.ordinal()][i] > 0) {
				int count = 0, max = 0;
				for (int j = 0; j < LENGTH; j++) {
					if (map[j][i] > max) {
						max = map[j][i];
						count++;
					}
				}
				if (buildingsSeen[CardinalDirection.NORTH.ordinal()][i] != count) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkSouth() {
		for (int i = 0; i < LENGTH; i++) {
			if (buildingsSeen[CardinalDirection.SOUTH.ordinal()][i] > 0) {
				int count = 0, max = 0;
				for (int j = LENGTH - 1; j >= 0; j--) {
					if (map[j][i] > max) {
						max = map[j][i];
						count++;
					}
				}
				if (buildingsSeen[CardinalDirection.SOUTH.ordinal()][i] != count) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isSudoku() {
		for (int i = 0; i < LENGTH; i++) {
			int[] seen = new int[LENGTH];
			for (int j = 0; j < LENGTH; j++) {
				if (seen[map[i][j] - 1] == 1) {
					return false;
				}
				seen[map[i][j] - 1] = 1;
			}
		}
		for (int i = 0; i < LENGTH; i++) {
			int[] seen = new int[LENGTH];
			for (int j = 0; j < LENGTH; j++) {
				if (seen[map[j][i] - 1] == 1) {
					return false;
				}
				seen[map[j][i] - 1] = 1;
			}
		}
		return true;
	}

}
