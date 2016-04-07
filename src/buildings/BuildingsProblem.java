package buildings;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

import java.util.LinkedList;
import java.util.List;

import buildings.heuristics.Heuristic;

public class BuildingsProblem implements GPSProblem{
	
	
	List<GPSRule> rules ;
	
	private int[] south ;
	private int[] north ;
	private int[] east ;
	private int[] west ;
	private int length;
	private Heuristic h;
	private int[][] initialMap;
	
	
	public static void main(String[] args) {
	
	}
	
	public BuildingsProblem(int[] south, int[] north, int[] east, int[] west, Heuristic h, int[][] initialMap) {
		this.south = south;
		this.north = north;
		this.east = east;
		this.west = west;
		length = west.length;
		this.h = h;
		createRules();
		this.initialMap = initialMap;
	}

	private void createRules() {
		rules = new LinkedList<GPSRule>();
		for(int i=0; i<length;i++){
			for(int j=0; j<length-1;j++){
				for(int k=j+1; k<length; k++){
					rules.add(new BuildingsRule(i,j,k));
				}
			}
		}		
	}

	@Override
	public GPSState getInitState() {
		return new BuildingsState(south, north, east, west, initialMap);
	}

	@Override
	public boolean isGoal(GPSState state) {
		return state.isGoal();
	}

	@Override
	public List<GPSRule> getRules() {
		return rules;
	}

	@Override
	public Integer getHValue(GPSState state) {
		return h.getH(state);
	}
}
