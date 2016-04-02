package buildings;

import utils.PermutationsQuery;

public class BuildingsProblem {
	public static void main(String[] args) {
		int[] south = {1,1,1,1,1};
		int[] north = {2,2,2,2,2};
		int[] east = {3,3,3,3,3};
		int[] west = {4,4,4,4,4};
		//Test del toString
		BuildingsState state = new BuildingsState(south, north, east, west);
		System.out.println(state);
		
		Integer[] cond = {null,null,null,5,null};
		PermutationsQuery query = new PermutationsQuery(4,null,cond);
		System.out.println(query);
	}
}
