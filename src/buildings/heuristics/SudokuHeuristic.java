package buildings.heuristics;

import gps.api.GPSState;
import buildings.BuildingsState;

public class SudokuHeuristic implements Heuristic {

	@Override
	public int getH(GPSState state) {
		int[][] map = ((BuildingsState)state).getMap();
		int length = map.length;
		int counter =0;
		for(int i=0; i<length; i++){
			int[] flags = new int[length];
			for(int j=0; j<length ; j++){
				if(flags[map[j][i]-1] == 0){
					flags[map[j][i]-1]=1;					
				}else {
					counter++;					
				}				
			}
		}
		return (int) Math.ceil(((double)counter)/2);
	}

}
