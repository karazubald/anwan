package anwan.proses;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * <h2>Kelas DataBase</h2>
 * Kelas ini menyajikan fungsi membuat (CREATE), membaca (READ), mengubah (UPDATE), dan menghapus (DELETE) 
 * data di database. Database yang digunakan dalam kelas ini adalah MySQL versi 8 sehingga operasi dalam kelas ini
 * hanya dapat bekerja apabila pengguna sudah menginstal MySQL versi 8 ke atas.
 * 
 * @author karazubald
 *
 */
public class DataBase {
	static final String DEFAULT_USER = "anwan";
	static final String DEFAULT_PASSWORD = "12345";
	static final String LOKASI =  "jdbc:mysql://localhost/";
	static String query = null;
	static ResultSet hasilQuery = null;
	static String namaBerkasDB = null;
	
	/**
	 * Membuat database baru sesuai dengan NamaDataBase. Database baru secara otomatis membuat tabel data mentah 
	 * dengan ketentuan sebagai berikut.
	 * <p>
	 * <TABLE>
	 * <TR>
	 * <TD>Baris</TD>
	 * <TD>UrutanData</TD>
	 * <TD>Tema</TD>
	 * <TD>Koding</TD>
	 * <TD>Ide_Utama</TD>
	 * <TD>Jawaban</TD>
	 * <TD>Impresi</TD>
	 * </TR>
	 * <TR>
	 * <TD>Tipe Data (Ketentuan dalam SQL)</TD>
	 * <TD>Integer (Primary Key)</TD>
	 * <TD>String (VARCHAR[255])</TD>
	 * <TD>String (VARCHAR[255])</TD>
	 * <TD>String (VARCHAR[8000])</TD>
	 * <TD>String (VARCHAR[8000])</TD>
	 * <TD>String (VARCHAR[8000])</TD>
	 * </TR>
	 * </TABLE>
	 * 
	 * @param NamaDataBase nama yang akan digunakan sebagai berkas database.
	 */
	public static void dataBaru(String NamaDataBase) {
		namaBerkasDB = NamaDataBase.toUpperCase();
		try (Connection koneksiDataBase = DriverManager.getConnection(LOKASI, DEFAULT_USER, DEFAULT_PASSWORD);
				Statement perintah = koneksiDataBase.createStatement())
		{
			query = "CREATE DATABASE "+ namaBerkasDB;
			perintah.execute(query);
			
			query = "CREATE TABLE data_mentah "
					+ "(UrutanData INT NOT NULL,"
					+ "Tema VARCHAR(255),"
					+ "Koding VARCHAR(255),"
					+ "Ide_Utama MEDIUMTEXT,"
					+ "Jawaban MEDIUMTEXT,"
					+ "Pertanyaan MEDIUMTEXT,"
					+ "Impresi MEDIUMTEXT,"
					+ "PRIMARY KEY (UrutanData))";
			perintah.execute(query);
		} catch (Exception galat) {
			galat.printStackTrace();
		}
	}
	
	/**
	 * Memuat database yang sudah pernah dibuat.
	 */
	public static void muatData() {
		if(namaBerkasDB == null) {
			namaBerkasDB = "anwandb";
			dataBaru(namaBerkasDB);
			return;
		}
		try (Connection koneksiDataBase = DriverManager.getConnection(LOKASI + namaBerkasDB, DEFAULT_USER, DEFAULT_PASSWORD);
				Statement perintah = koneksiDataBase.createStatement())
		{	
			query = "SELECT * FROM data_mentah";
			perintah.execute(query);
		} catch (Exception galat) {
			galat.printStackTrace();
		}
	}
	
	/**
	 * Membuat pasangan tema-koding dan mengelompokkan berdasarkan tema dari tabel wawancara di database.
	 */
	public static void pairTK() {
		try (Connection koneksiDataBase = DriverManager.getConnection(LOKASI, DEFAULT_USER, DEFAULT_PASSWORD);
				Statement perintah = koneksiDataBase.createStatement())
		{
			query = "CREATE pairTK AS SELECT Tema AS Tema, UrutanData AS Urutan, COUNT(Tema) AS Frekuensi, Koding "
					+ "FROM data_mentah GROUP BY Tema, Urutan, Koding"
					+ "ORDER BY Urutan DESC, COUNT(Tema) DESC";
			perintah.execute(query);

		} catch (Exception galat) {
			galat.printStackTrace();
		}
	}
	
	/**
	 * Menyebutkan tema dan urutannya dalam data hasil wawancara.
	 * @param tema Tema yang akan dianalisis
	 * @return kalimat yang menyatakan tema dan urutan tema
	 */
	public static String urutanTema(String tema) {
		String kalimat = null;
		try (Connection koneksiDataBase = DriverManager.getConnection(LOKASI, DEFAULT_USER, DEFAULT_PASSWORD);
				Statement perintah = koneksiDataBase.createStatement())
		{
			query = "SELECT UrutanData FROM pairTK WHERE Tema="+tema+" LIMIT 1";
			hasilQuery = perintah.executeQuery(query);
			kalimat = "Tema "+Terbilang.sebut(hasilQuery.getInt("UrutanData"))+" adalah "+tema;
		} catch (Exception galat) {
			System.out.println("Terjadi galat: ");
			galat.printStackTrace();
		}
		return kalimat;
	}
	
	public static String sebutKoding(String tema) {
		String kalimat = null;
		try (Connection koneksiDataBase = DriverManager.getConnection(LOKASI, DEFAULT_USER, DEFAULT_PASSWORD);
				Statement perintah = koneksiDataBase.createStatement())
		{
			query = "SELECT Koding FROM pairTK WHERE Tema="+tema+" GROUP BY Koding";
			hasilQuery = perintah.executeQuery(query);
			while(hasilQuery.next()) {
				kalimat += hasilQuery.getString("Koding")+",";
			}
		} catch (Exception galat) {
			System.out.println("Terjadi galat: ");
			galat.printStackTrace();
		}
		return kalimat;
	}
	
	/**
	 * Metode ini dibuat untuk menyimpan hasil query MySQL ke berkas CSV. 
	 * Metode ini merupakan modifikasi dari kode yang dibuat oleh Nam Ha Minh dari codejava.net.
	 * 
	 * @author Nam Ha Minh
	 * (C) Copyright codejava.net
	 * 
	 * @see <a href="https://www.codejava.net/coding/java-code-example-to-export-from-database-to-csv-file">SimpleDb2CsvExporter</a>
	 */
	public static void simpanCSV() {
		try (Connection koneksiDataBase = DriverManager.getConnection(LOKASI, DEFAULT_USER, DEFAULT_PASSWORD);
				Statement perintah = koneksiDataBase.createStatement();
				BufferedWriter penulisData = new BufferedWriter(new FileWriter(namaBerkasDB+".csv"));) {
			
			query = "SELECT * FROM "+namaBerkasDB;
			hasilQuery = perintah.executeQuery(query);
			
			penulisData.write("UrutanData,Tema,Koding,Ide_Utama,Jawaban,Pertanyaan,Impresi");
			
			while(hasilQuery.next()) {
				String nomor = String.valueOf(hasilQuery.getInt("UrutanData"));
				String tema = hasilQuery.getString("Tema");
				String koding = hasilQuery.getString("Koding");
				String ide_utama = hasilQuery.getString("Ide_Utama");
				String jawaban = hasilQuery.getString("Jawaban");
				String pertanyaan = hasilQuery.getString("Pertanyaan");
				String impresi = hasilQuery.getString("Impresi");
				
				String data = String.format("%s,%s,%s,%s,%s,%s,%s", 
						nomor, tema, koding, ide_utama, jawaban, pertanyaan, impresi);
				
				penulisData.newLine();
				penulisData.write(data);
			}
		} catch (SQLException galat) {
			System.out.println("Galat dalam Operasi Database: ");
			galat.printStackTrace();
		} catch (IOException galat) {
			System.out.println("Galat dalam Operasi Berkas: ");
			galat.printStackTrace();
		} catch (Exception galat) {
			System.out.println("Terjadi galat: ");
			galat.printStackTrace();
		}
	}
}
