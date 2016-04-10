package buildings;

import gps.GPSEngine;
import gps.GPSNode;
import gps.SearchStrategy;
import gps.api.GPSProblem;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BuildingsEngine extends GPSEngine {

	public BuildingsEngine() {
		clean();
	}
	
	private void clean() {
		open = new PriorityQueue<GPSNode>(new Comparator<GPSNode>() {
			@Override
			public int compare(GPSNode o1, GPSNode o2) {
				switch (strategy) {
				case BFS:
					return o1.getCost() - o2.getCost();
				case DFS:
					return o2.getCost() - o1.getCost();
				case IDDFS:
					// IDDFS Condition - Unreachable
					return 0;
				case GREEDY:
					return (problem.getHValue(o1.getState()))
							- (problem.getHValue(o2.getState()));
				case ASTAR:
					return (problem.getHValue(o1.getState()) + o1.getCost())
							- (problem.getHValue(o2.getState()) + o2.getCost());
				default:
					return 0;
				}
			}
		});
	}
	// output.writeln("Strategy used : "+p.getSearchStrategy().name());
	public boolean bEngine(GPSProblem myProblem, SearchStrategy myStrategy) {
		if(myStrategy.equals(SearchStrategy.IDDFS)) {
			int i = 1;
			while (!(this.engine(myProblem, SearchStrategy.DFS, i))) {
				clean();
				i++;
			}
			return true;
		}
		if(myStrategy.equals(SearchStrategy.ASTAR) || myStrategy.equals(SearchStrategy.BFS) || myStrategy.equals(SearchStrategy.DFS) || myStrategy.equals(SearchStrategy.GREEDY)) {
			return this.engine(myProblem, myStrategy, 0);
		}
		throw new IllegalArgumentException();
	}

}
