package anwan.proses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Kelas untuk melakukan pencatatan banyak data yang telah terekam dalam database
 * @author karazubald
 *
 */
public class PenghitungData {
	private static int nomorData = 1;
	
	/**
	 * Membaca nomor data dari berkas nomordata.txt
	 * @return nomor data terakhir dalam bentuk integer (bilangan bulat)
	 */
	public static int getNomorData() {
		File berkas = new File("nomordata.txt");
		
		if(!berkas.exists()) rekamNomorData();
		
		String isiBerkas = null;
		
		
		try (Scanner pembacaBerkas = new Scanner(berkas)){
			while(pembacaBerkas.hasNext())	
				isiBerkas = String.valueOf(pembacaBerkas.nextInt());
		} catch (FileNotFoundException galat) {
			Proses.pesan("Berkas nomordata.txt tidak dapat ditemukan...");
			galat.printStackTrace();
		}
		
		nomorData = Integer.valueOf(isiBerkas);
		return nomorData;
	}

	/**
	 * Menetapkan nomor data terakhir dan memanggil metode rekamNomorData()
	 * @param nomorData nomor data terakhir
	 * 
	 * @see #rekamNomorData()
	 */
	public static void setNomorData(int nomorData) {
		PenghitungData.nomorData = nomorData;
		rekamNomorData();
	}
	
	/**
	 * Menyimpan nomor data terkahir dalam berkas nomordata.txt
	 */
	private static void rekamNomorData() {
		File berkas = new File("nomordata.txt");
		
		try (BufferedWriter penulisBerkas = new BufferedWriter(new FileWriter(berkas))){			
			penulisBerkas.write(String.valueOf(nomorData));
		} catch (IOException galat) {
			Proses.pesan("Berkas tidak dapat dibuat...");
			galat.printStackTrace();
		}
		
	}
		
}

