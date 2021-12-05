package anwan.proses;

import java.util.ArrayList;

/**
 * @author karazubald
 * <h2> Data </h2>
 * Kelas ini menyimpan unit data (datum) dalam bentuk String di ArrayList. 
 * Kelas ini harus diinisialisasikan terlebih dahulu melalui konstruktornya 
 * sebelum digunakan karena properti dan metodenya bersifat <i>nonstatic</i>.
 * @see Data#Data(String id)
 */
public class Data {	
	private String id;
	private ArrayList<String> datum;
	
	/**
	 * Konstruktor kelas Data.
	 * Konstruktor ini menyiapkan nama dari kelas Data (id) dan koleksi data (ArrayList).
	 * @param id nama unik untuk kelas Data ini
	 */
	public Data(String id) {
		this.id = id;
		datum = new ArrayList<>();
	}
	
	/**
	 * Memunculkan data dari nomor urut tertentu
	 * @param nomorData nomor urutan data yang akan dimunculkan
	 * @return Data dalam bentuk String
	 */
	public String ambilData(int nomorData) {
		return datum.get(nomorData);
	}

	/**
	 * Memunculkan koleksi data yang ada.
	 * @return koleksi data dalam bentuk ArrayList
	 */
	public ArrayList<String> ambilDatum(){
		return datum;
	}
	
	/**
	 * Menyimpan data di urutan tertentu.
	 * @param nomorData nomor urutan
	 * @param data Data dalam bentuk String
	 */
	public void simpan(int nomorData, String data) {
		datum.set(nomorData, data);
	}
	
	/**
	 * Menampilkan ukuran data.
	 * @return Integer yang menunjukkan ukuran data.
	 */
	public int banyakData() {
		return datum.size();
	}
	
	public String getID() {
		return this.id;
	}
}
