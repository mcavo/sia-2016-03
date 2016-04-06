package utils;

import java.io.FileWriter;
import java.io.IOException;

public class OutputManager {

	
	private static OutputManager instance;
	private FileWriter wr;
	
	private OutputManager(){
	}
	
	public static OutputManager getInstance(){
		if(instance == null){
			instance = new OutputManager();
		}
		return instance;
	}
	
	public void setFile(FileWriter wr){
		this.wr = wr;
	}
	
	public void write(String msg){
		try {
			wr.write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean writeln(String msg){
		if(msg==null){
			return false;
		}
		try {	
			wr.write(msg+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public void close(){
		try {
			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
