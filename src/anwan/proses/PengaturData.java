package anwan.proses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author karazubald
 * PengaturData berfungsi untuk :
 * (1) Membuat dataLama dan dataBaru dari kelas Data
 * (2) Menyimpan dataLama dan dataBaru
 * (3) Mengakses data di dataLama dan dataBaru
 * (4) Menyimpan data ke dalam bentuk file CSV
 */
public class PengaturData {
	private static Data dataLama, dataSekarang;
	
	/**
	 * Inisialisasis dua jenis data (dataLama dan dataBaru) dari kelas Data.
	 * @see Data
	 */
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
	
	public void simpanCSV() {
		File csv = new File("QueryDataWawancara.csv");
		try {
			if(!csv.exists()) csv.createNewFile();
			FileWriter csvWriter = new FileWriter(csv);
			BufferedWriter writer = new BufferedWriter(csvWriter);
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < dataSekarang.banyakData(); i++) {
				sb.append(i);
				sb.append(",");
				for(int j = 0; j < dataSekarang.ambilData(i).length; j++) {
					boolean ujungData = (j == dataSekarang.ambilData(i)[j].length()-1);
					String kategoriData = dataSekarang.ambilData(i)[j].replace(",", "^").replace("\"", "");
					sb.append(kategoriData);
					if(ujungData) sb.append(System.lineSeparator()); else sb.append(",");
				}
			}
			
			csvWriter.close();
			writer.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
