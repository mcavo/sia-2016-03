package utils;

import java.util.ArrayList;

public class PermutationsQuery {
	
	private ArrayList<State> states = new ArrayList<State>();
	private Integer front;
	private Integer back;
	private Integer[] conditions = new Integer[5];
	
	
	@Override 
	public String toString(){
		String str="";
		for(State state : states) {
			str = str + state.toString() + "\n";
		}
		return str;
	}
	
	public PermutationsQuery(Integer front, Integer back, Integer[]list) {
		this.front=front;
		this.back=back;
		this.conditions=list;
		generateList();
	}
	
	private void generateList() {
		int[] init = {1,2,3,4,5};
		permutations(0,init);
	}
	
	private boolean condition(int[] vec) {
		if (front!=null)
			if(bSeenFront(vec)!=front)
				return false;
		if (back!=null)
			if(bSeenBack(vec)!=back)
				return false;
		for(int i=0; i<conditions.length; i++) {
			if(conditions[i]!=null)
				if(conditions[i]!=vec[i])
					return false;
		}
		return true;
			
	}
	
	private void permutations(int n, int[] string){
		if(n == string.length){
			if(condition(string)) {
				states.add(new State(string));
			}
			return;
		}
		for(int i = n; i < string.length; i++){
			if(i != n && string[i] == string[n])
				i++;
			if(i >= string.length)
				return;
			swap(string, i, n);
			permutations(n+1,string);
			swap(string, i, n);
		}
	}
	private void swap(int[] str, int i, int n){	
		int aux = str[i];
		str[i] = str[n];
		str[n] = aux;
	}

	int bSeenFront(int[] buildings){
		int count = 0, max = 0;
		for(int i=0; i<buildings.length; i++) {
			if(buildings[i]>max) {
				max = buildings[i];
				count++;
			}
		}
		return count;
	}
	private int bSeenBack(int[] buildings){
		int count = 0, max = 0;
		for(int i=buildings.length-1; i>=0; i--) {
			if(buildings[i]>max) {
				max = buildings[i];
				count++;
			}
		}
		return count;
	}
	
	private class State {
		int buildingsSeenFront = 0;
		int buildingsSeenBack = 0;
		int buildings[] = new int[5];
		State(int[] build) {	
			for(int i=0; i<build.length; i++)
				buildings[i] = build[i];
			buildingsSeenBack = bSeenBack(build);
			buildingsSeenFront = bSeenFront(build);
		}
		
		@Override 
		public String toString() {
			String str = String.valueOf(buildingsSeenFront) + " | ";
			for (int i=0; i<5; i++) {
				str = str + String.valueOf(buildings[i]) + " ";
			}
			str = str + "| "+String.valueOf(buildingsSeenBack);
			return str;
		}
		
	}
}
