package anwan.proses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PenghitungData {
	private static int nomorData = 1;

	public static int getNomorData() {
		nomorData = bacaNomorData();
		return nomorData;
	}

	public static void setNomorData(int nomorData) {
		PenghitungData.nomorData = nomorData;
		rekamNomorData();
	}
	
	private static void rekamNomorData() {
		File berkas = new File("nomordata.txt");
		
		try (BufferedWriter penulisBerkas = new BufferedWriter(new FileWriter(berkas))){			
			penulisBerkas.write(String.valueOf(nomorData));
		} catch (IOException galat) {
			Proses.pesan("Berkas tidak dapat dibuat...");
			galat.printStackTrace();
		}
		
	}
	
	private static int bacaNomorData() {
		File berkas = new File("nomordata.txt");
		
		if(!berkas.exists()) rekamNomorData();
		
		int nomor = 1;
		String isiBerkas = null;
		
		
		
		try (Scanner pembacaBerkas = new Scanner(berkas)){
			while(pembacaBerkas.hasNext())	
				isiBerkas = String.valueOf(pembacaBerkas.nextInt());
		} catch (FileNotFoundException galat) {
			Proses.pesan("Berkas nomordata.txt tidak dapat ditemukan...");
			galat.printStackTrace();
		}
		
		nomor = Integer.valueOf(isiBerkas);
		return nomor;
	}
	
}

