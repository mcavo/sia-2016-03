package utils;

import java.io.BufferedReader;
import java.io.IOException;

import gps.SearchStrategy;
import models.CardinalDirection;

public class BuildingsParser {
	
	private static int DIRECTIONS = 4;
	
	private String searchStrategy;
	private int[][] dirMap;
	private int[][] initialBoard;
	
	public BuildingsParser(BufferedReader br) throws IOException{
		try {
			br.readLine(); //Search Strategy
			searchStrategy = br.readLine();
			if(!validSearchStrategy(searchStrategy))
				throw new IllegalArgumentException();
			br.readLine(); //Board Size
			int length = Integer.parseInt(br.readLine());
			dirMap = new int[DIRECTIONS][length];
			br.readLine(); //Buildings Seen
			for(int i=0 ; i<DIRECTIONS ; i++) {
				if(!br.ready()) {
					throw new IllegalArgumentException();
				}
				String[] line = br.readLine().split(" ");
				if(line.length!=length+1) {
					throw new IllegalArgumentException();
				}
				for(int j=0 ; j<length ; j++) {
					fill(line[0].toCharArray()[0],Integer.parseInt(line[j+1]),j,dirMap);
				}
			}
			br.readLine(); //Initial Board
			initialBoard = new int[length][length];
			for(int i=0; i<length ; i++) {
				if(!br.ready()) {
					throw new IllegalArgumentException();
				}
				String[] line = br.readLine().split(" ");
				if(line.length!=length) {
					throw new IllegalArgumentException();
				}
				for(int j=0 ; j<length ; j++) {
					initialBoard[i][j] = Integer.parseInt(line[j]);
				}
			}
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException();
		} catch(IllegalArgumentException e) {
			throw e;
		}
	}
	
	private boolean validSearchStrategy(String searchStrategy) {
		for(SearchStrategy ss : SearchStrategy.values())
			if(ss.name().equals(searchStrategy))
				return true;
		return false;
	}

	private void fill(char cardinalDirection, int value, int index, int[][] map) {
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
	
	public String getSearchStrategy() {
		return searchStrategy;
	}
	
	public int[] getSouth() {
		return dirMap[CardinalDirection.SOUTH.ordinal()];
	}
	
	public int[] getNorth() {
		return dirMap[CardinalDirection.NORTH.ordinal()];
	}
	
	public int[] getWest() {
		return dirMap[CardinalDirection.WEST.ordinal()];
	}
	
	public int[] getEast() {
		return dirMap[CardinalDirection.EAST.ordinal()];
	}
	
	public int[][] getInitialBoard() {
		return initialBoard;
	}

}
