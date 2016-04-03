package buildings;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

import java.util.LinkedList;
import java.util.List;

public class BuildingsProblem implements GPSProblem{
	
	
	List<GPSRule> rules ;
	
	private int[] south ;
	private int[] north ;
	private int[] east ;
	private int[] west ;
	private int length;
	
	
	public static void main(String[] args) {
	
	}
	
	public BuildingsProblem(int[] south, int[] north, int[] east, int[] west) {
		this.south = south;
		this.north = north;
		this.east = east;
		this.west = west;
		length = west.length;
		createRules();
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
		int imap[][] = new int[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				imap[i][j] = j + 1;
			}
		}
		return new BuildingsState(south, north, east, west, imap);
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
		return 1;
	}
}
