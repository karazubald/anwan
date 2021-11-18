package anwan.proses;

import java.util.ArrayList;

/**
 * @author karazubald
 * Kelas untuk menyimpan data.
 * <p>
 * Kelas ini menyimpan datum (unit data) dalam array String di ArrayList. Kelas ini harus diinisialisasikan terlebih dahulu melalui konstruktornya sebelum digunakan karena properti dan metodenya bersifat <i>nonstatic</i>.
 * @see Data#Data()
 */
public class Data {
	private ArrayList<String[]> datum;
	
	/**
	 * Konstruktor kelas Data.
	 */
	public Data() {
		datum = new ArrayList<String[]>();
	}
	/**
	 * Mengakses String[] berdasarkan nomor indeks data
	 * @param nomorData nomor indeks data
	 * @return data dalam bentuk array String
	 */
	public String[] ambilData(int nomorData) {
		return datum.get(nomorData);
	}
	
	public void simpan(int nomorData, String...data) {
		datum.set(nomorData, data);
	}
	
	public int banyakData() {
		return datum.size();
	}
}
