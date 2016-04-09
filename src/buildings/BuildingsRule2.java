package buildings;

import utils.Copies;
import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

public class BuildingsRule2 implements GPSRule {

	private boolean swapRow;
	private int x1;
	private int x2;

	public BuildingsRule2(boolean swapRow, int x1, int x2) {
		this.swapRow = swapRow;
		this.x1 = x1;
		this.x2 = x2;
	}
	
	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		String ans;
		if(swapRow){
			ans = "Swap rows " + x1 + " and " + x2 + ".\n";
		}else{
			ans = "Swap columns " + x1 + " and " + x2 + ".\n";
		}
		
		return null;
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		BuildingsState s = (BuildingsState) state;
		int[][] newMap = Copies.deepCopy(((BuildingsState) state).getMap());
		if(swapRow){
			swapRow(newMap);
		}else{
			swapColumn(newMap);
		}
		BuildingsState ans = new BuildingsState(s.getSouth(), s.getNorth(),
				s.getEast(), s.getWest(), newMap);
		return ans;
	}

	private void swapColumn(int[][] newMap) {
		for(int i=0; i<newMap.length; i++){
			int aux = newMap[i][x1];
			newMap[i][x1] = newMap[i][x2];
			newMap[i][x2] = aux;
		}
		
	}

	private void swapRow(int[][] newMap) {
		for(int i=0; i<newMap.length; i++){
			int aux = newMap[x1][i];
			newMap[x1][i] = newMap[x2][i];
			newMap[x2][i] = aux;
		}
	}

}
