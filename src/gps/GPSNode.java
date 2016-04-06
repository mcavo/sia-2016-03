package gps;

import gps.api.GPSRule;
import gps.api.GPSState;

public class GPSNode {

	private GPSState state;

	private GPSNode parent;

	private Integer cost;
	
	private Integer depth;
	
	private GPSRule rule;

	public GPSNode(GPSState state, Integer cost, Integer depth, GPSRule rule) {
		this.state = state;
		this.cost = cost;
		this.rule = rule;
		this.depth = depth;
	}

	
	public Integer getDepth(){
		return depth;
	}
	
	public GPSRule getRule(){
		return rule;
	}
	
	public GPSNode getParent() {
		return parent;
	}

	public void setParent(GPSNode parent) {
		this.parent = parent;
	}

	public GPSState getState() {
		return state;
	}

	public Integer getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return state.toString();
	}

	public String getSolution() {
		if (this.parent == null) {
			return this.state.toString();
		}
		return this.parent.getSolution() +"\n\n"+"Applying rule: "+rule.getName()+"\n"+ this.state.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GPSNode other = (GPSNode) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	public int hashCode(){
		return state.hashCode();
	}
	
}
