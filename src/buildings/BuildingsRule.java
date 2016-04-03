package buildings;

import utils.Copies;
import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

public class BuildingsRule implements GPSRule {

	private int row;
	private int x1;
	private int x2;

	public BuildingsRule(int row, int x1, int x2) {
		this.row = row;
		this.x1 = x1;
		this.x2 = x2;
	}

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Swaps positions " + x1 + " and " + x2 + " from row " + row
				+ ".";
	}

	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		BuildingsState s = (BuildingsState) state;
		int[][] newMap = Copies.deepCopy(((BuildingsState) state).getMap());
		int aux = newMap[row][x1];
		newMap[row][x1] = newMap[row][x2];
		newMap[row][x2] = aux;
		BuildingsState ans = new BuildingsState(s.getSouth(), s.getNorth(),
				s.getEast(), s.getWest(), newMap);
		System.out.println(ans);
		return ans;
	}

}
