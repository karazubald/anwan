package kualin.fitur;

import java.util.ArrayList;

public class Data {
	private ArrayList<String[]> datum;
	
	public Data() {
		datum = new ArrayList<String[]>();
	}
	
	public String[] ambilData(int nomorData) {
		return datum.get(nomorData);
	}
	
	public void simpan(int nomorData, String...data) {
		datum.set(nomorData, data);
	}
}
