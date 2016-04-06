package utils;

import java.util.Scanner;

import models.CardinalDirection;

public class Parser {
	private static int DIRECTIONS = 4;
//  Example	
//5
//N 2 5 2 2 1
//S 3 1 2 2 4
//E 1 2 2 2 4
//W 4 1 2 3 2
//3
//N 2 2 1
//S 2 1 3
//E 1 2 2
//W 3 1 2
//3
//N 2 2 1
//S 2 1 3
//W 3 1 2
//E 1 2 2
	public static int[][] parseDirections() {
		Scanner in = new Scanner(System.in);
		try {
			int length = Integer.parseInt(in.nextLine());
			int[][] dirMap = new int[DIRECTIONS][length];
			
			for(int i=0 ; i<DIRECTIONS ; i++) {
				if(!in.hasNextLine()) {
					throw new IllegalArgumentException();
				}
				String[] line = in.nextLine().split(" ");
				if(line.length!=length+1) {
					throw new IllegalArgumentException();
				}
				for(int j=0 ; j<length ; j++) {
					fill(line[0].toCharArray()[0],Integer.parseInt(line[j+1]),j,dirMap);
				}
			}
			return dirMap;
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException();
		} catch(IllegalArgumentException e) {
			throw e;
		}
		
		
	}
	
	
	private static void fill(char cardinalDirection, int value, int index, int[][] map) {
		switch(cardinalDirection) {
			case 'N': {
				map[CardinalDirection.NORTH.ordinal()][index] = value;
				break;
			}
			case 'S': {
				map[CardinalDirection.SOUTH.ordinal()][index] = value;
				break;
			}
			case 'W': {
				map[CardinalDirection.WEST.ordinal()][index] = value;
				break;
			}
			case 'E': {
				map[CardinalDirection.EAST.ordinal()][index] = value;
				break;
			}
			default:
				throw new IllegalArgumentException();
		}
	}

}


