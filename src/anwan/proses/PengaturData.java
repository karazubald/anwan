package anwan.proses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import com.jfoenix.controls.JFXTextArea;

/**
 * <h2> Pengatur Data </h2>
 * PengaturData secara spesifik berfungsi untuk : <p>
 * (1) Membuat dataLama dan dataBaru dari kelas Data. <p>
 * (2) Menyimpan dataLama dan dataBaru. <p>
 * (3) Mengakses data di dataLama dan dataBaru. <p>
 * (4) Menyimpan data ke dalam bentuk berkas CSV. <p>
 * (5) Menyimpan data ke dalam bentuk berkas TXT.
 * 
 * @author karazubald
 */
public class PengaturData {
	private static HashMap<String, String> pair;
	private static Data[] data;
	
	/**
	 * Membuat objek Data dan melakukan inisialisasi array Data sebanyak yang disebutkan dalam parameter namaData.
	 * @param namaData nama untuk objek kelas Data
	 * @see Data
	 */
	public static void initData(String...namaData) {
		String id;
		int banyakData = namaData.length;
		data = new Data[banyakData];
		
		for(int i = 0; i < banyakData; i++) {
			id = namaData[i];
			data[i] = new Data(id);
		}
	}
	
	/**
	 * Menyimpan data di array data sesuai dengan nomor urut yang ditentukan.
	 * @param namaData
	 * @param nomorUrut
	 * @param tulisan
	 */
	public static void simpanData(String namaData, int nomorUrut, String tulisan) {
		String idData;
		
		for(Data jenisData : data) {
			idData = jenisData.getID().toLowerCase();
			
			if(idData.equals(namaData.toLowerCase())) {
				jenisData.simpan(nomorUrut, tulisan);
			}
		}
	}

	/**
	 * Menyimpan hasil wawancara ke berkas CSV dengan format nomor,tema,koding,ide_utama,jawaban,pertanyaan,impresi.
	 * @param namaBerkas nama dari berkas CSV; nama ini akan dikonversi menjadi huruf nonkapital.
	 * @throws Exception: memunculkan info galat yang terjadi selama proses pembuatan berkas.
	 */
	public static void simpanCSV(String namaBerkas) {
		int jumlahData = 0;
		for(int n = 0; n < data.length; n++) {
			for(int m = 0; m < n; m++) {
				if(data[n].banyakData() >= data[m].banyakData()) {
					jumlahData = data[n].banyakData();
				}
			}
		}
		
		File csv = new File(namaBerkas.toLowerCase()+".csv");
		try {
			if(!csv.exists()) csv.createNewFile();
			
			FileOutputStream fileCSV = new FileOutputStream(csv);
			OutputStreamWriter penulisData = new OutputStreamWriter(fileCSV, StandardCharsets.UTF_8);
			
			StringBuilder isi = new StringBuilder();
			String barisBaru = System.lineSeparator();
			
			for(int baris = 0; baris < data.length; baris++) {
				for(int kolom = 0; kolom < jumlahData; kolom++) {
					isi.append(data[baris].ambilData(kolom));
					if (kolom == jumlahData) isi.append(barisBaru); else isi.append(",");
				}
			}
			
			penulisData.write(isi.toString());
			
			penulisData.flush();
			penulisData.close();
		} catch (Exception galat) {
			galat.printStackTrace();
		}
	}
	
	/**
	 * Menyiapkan HashMap untuk mencatat tema.
	 */
	public static void siapkanAnalisis() {
		pair = new HashMap<>();
		String tema = "";
		
		int letakTema = 0;
		for(Data data : data) {
			if(data.getID().toLowerCase().equals("tema")) {
				for(int n = 0; n < data.banyakData(); n++) {
					tema = data.ambilDatum().get(n);
					pair.put(tema, String.valueOf(data.ambilDatum().indexOf(tema)));
				}
			}
		}
	}
	
	public static void muatHasilAnalisis(String tema, JFXTextArea kotakAnalisisTema) {
		int urutanTema = Integer.valueOf(pair.get(tema));
		String kalimat = "";

		int jumlahData = 0;
		for(int n = 0; n < data.length; n++) {
			for(int m = 0; m < n; m++) {
				if(data[n].banyakData() >= data[m].banyakData()) {
					jumlahData = data[n].banyakData();
				}
			}
		}	
		
		kalimat = "Tema " + Terbilang.sebut(urutanTema) + "adalah " + tema;

		pair.put(tema, kalimat);
		
		// TODO : Mencari pasangan tema dan koding
		
		Set<String> koding = new LinkedHashSet<>();
		for(Data data : data) {
		
		}
		
		kalimat = "Tema ini memiliki koding: ";
		
		kalimat = pair.get(tema);
		
		kotakAnalisisTema.setText(kalimat);
	}
	
	public static void simpanAnalisis(String tema, JFXTextArea kotakAnalisisTema) {
		int letakTema = dataKlasifikasi.ambilDatum().indexOf(tema);
		String hasilAnalisis = kotakAnalisisTema.getText();
		dataKlasifikasi.simpan(letakTema, tema, hasilAnalisis);
	}
	
	public static void simpanTXT(String namaBerkas) {
		StringBuilder paragrafAnalisis = new StringBuilder();
		String barisBaru = System.lineSeparator();
		
		int banyakData = dataKlasifikasi.ambilDatum().size();
		for(int i = 0; i < banyakData; i++) {
			String analisis = dataKlasifikasi.ambilData(i)[1];
			paragrafAnalisis.append(analisis);
			paragrafAnalisis.append(barisBaru);
		}
		
		File txt = new File(namaBerkas.toLowerCase()+".txt");
		
		try {
			if(!txt.exists()) txt.createNewFile();
			
			FileOutputStream fileTXT = new FileOutputStream(txt);
			OutputStreamWriter writer = new OutputStreamWriter(fileTXT, StandardCharsets.UTF_8);
			
			writer.write(paragrafAnalisis.toString());
			writer.flush();
			writer.close();
			
		} catch (Exception galat) {
			galat.printStackTrace();
		}
	}
}
