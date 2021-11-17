package kualin.fitur;

public class PengaturData {
	private static Data dataLama, dataSekarang;
	
	public static void initData() {
		dataLama = new Data();
		dataSekarang = new Data();
	}
	
	public void simpanManual(int nomorData, String...data) {
		dataSekarang.simpan(nomorData, data);
	}
	
	public void simpanOtomatis(int nomorData, String...data) {
		dataSekarang.simpan(nomorData, data);
		dataLama = dataSekarang;
	}
	
	public String[] dataSelanjutnya(int nomorData) {
		return dataSekarang.ambilData(nomorData+1);
	}
	
	public String[] dataSebelumnya(int nomorData) {
		if (nomorData >= 1)	return dataSekarang.ambilData(nomorData-1);
		return dataSekarang.ambilData(nomorData);
	}
}
