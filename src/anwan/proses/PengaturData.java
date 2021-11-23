package anwan.proses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

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
	private static Data dataLama, dataSekarang, dataKlasifikasi;
	
	/**
	 * Inisialisasis tiga jenis data (dataLama, dataBaru, dan dataKlasifikasi) dari kelas Data.
	 * Metode ini harus menjadi metode pertama yang dipanggil.
	 * @see Data
	 */
	public static void initData() {
		dataLama = new Data();
		dataSekarang = new Data();
		dataKlasifikasi = new Data();
	}
	
	public static void simpanManual(int nomorData, String...data) {
		dataSekarang.simpan(nomorData, data);
	}
	
	public static void simpanOtomatis(int nomorData, String...data) {
		dataSekarang.simpan(nomorData, data);
		dataLama = dataSekarang;
	}
	
	public static String[] dataSelanjutnya(int nomorData) {
		return dataSekarang.ambilData(nomorData+1);
	}
	
	public static String[] dataSebelumnya(int nomorData) {
		if (nomorData >= 1)	return dataSekarang.ambilData(nomorData-1);
		return dataSekarang.ambilData(nomorData);
	}
	/**
	 * Menyimpan hasil wawancara ke berkas CSV dengan format nomor,tema,koding,ide_utama,jawaban,pertanyaan,impresi.
	 * @param namaBerkas nama dari berkas CSV; nama ini akan dikonversi menjadi huruf nonkapital.
	 * @throws Exception: memunculkan info galat yang terjadi selama proses pembuatan berkas.
	 */
	public static void simpanCSV(String namaBerkas) {
		File csv = new File(namaBerkas.toLowerCase()+".csv");
		try {
			if(!csv.exists()) csv.createNewFile();
			
			FileOutputStream fileCSV = new FileOutputStream(csv);
			OutputStreamWriter writer = new OutputStreamWriter(fileCSV, StandardCharsets.UTF_8);
			
			StringBuilder sb = new StringBuilder();
			String barisBaru = System.lineSeparator();
			
			for(int i = 0; i < dataSekarang.banyakData(); i++) {
				sb.append(i);
				sb.append(",");
				for(int j = 0; j < dataSekarang.ambilData(i).length; j++) {
					int ujungData = dataSekarang.ambilData(i).length-1;
					String dataUjungBaris = dataSekarang.ambilData(i)[ujungData].replace(",", "^").replace("\"", "!");
					
					String kategoriData = dataSekarang.ambilData(i)[j].replace(",", "^").replace("\"", "!");
					sb.append(kategoriData);
					
					if(kategoriData.equals(dataUjungBaris))	sb.append(barisBaru); else sb.append(","); 
				}
			}
			
			writer.write(barisBaru.toString());
			
			writer.flush();
			writer.close();
		} catch (Exception galat) {
			galat.printStackTrace();
		}
	}
	
	public static void siapkanAnalisis() {
		StringBuilder sb = new StringBuilder();	
		
		int banyakData = dataSekarang.banyakData();
		String[] dataTerakhir = dataSekarang.ambilData(banyakData-1);
		
		for(int i = 0; i < banyakData; i++) {				
			String tema = dataSekarang.ambilData(i)[5];
			for(int baris = 0; baris < banyakData; baris++) {
				if(dataSekarang.ambilData(baris)[5].equals(tema)) sb.append(dataSekarang.ambilData(baris)[4]);
				if(dataSekarang.ambilData(baris).equals(dataTerakhir)) sb.append("."); else sb.append(","); 
			}				
		dataKlasifikasi.simpan(i, tema, sb.toString());
		}
	}
	
	public static void muatHasilAnalisis(String tema, JFXTextArea kotakAnalisisTema) {
		String analisisTema = "";
		int letakTema = dataKlasifikasi.ambilDatum().indexOf(tema);
		
		for(String s : dataKlasifikasi.ambilData(letakTema)) {
			analisisTema = "Tema " + Terbilang.sebut(letakTema) + " adalah " + tema;
			analisisTema += "Tema ini memiliki koding: " + dataKlasifikasi.ambilData(letakTema)[1];
		}
		kotakAnalisisTema.setText(analisisTema);
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
