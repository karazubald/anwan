package anwan.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

import anwan.proses.Proses;
import anwan.proses.Terbilang;

/**
 * <h2>Kelas DataBase</h2>
 * Kelas ini menyajikan fungsi membuat (CREATE), membaca (READ), mengubah (UPDATE), dan menghapus (DELETE) 
 * data di database. Jenis database yang digunakan dalam kelas ini adalah database SQLite.
 * <p>
 * 
 * @author karazubald
 */
public class DataBase {
	private static SQLiteDataSource sumberData = null;
	private static Connection koneksiDataBase = null;
	private static Statement eksekusi = null;
	private static PreparedStatement perintah = null;
	private static String query = null;
	private static ResultSet hasilQuery = null;
	private static final String DEFAULT_DATABASE = "ANWAN_DATABASE";
	private static final String DIREKTORI_DATA = "src/anwan/data/".replace("/", File.separator);
	
	public static enum idData {
		Tema, Koding, Ide_Utama, Jawaban, Pertanyaan, Impresi;
	}
	
	/**
	 * Membuat database baru dengan nama ANWAN_DATABASE.
	 * Database baru secara otomatis membuat tabel dengan nama data_mentah.
	 * Berikut format dari tabel yang dibuat menggunakan metode ini.
	 * <p>
	 * <TABLE>
	 *  <TR>
	 *  <TD>Baris</TD>
	 *  <TD>UrutanData</TD>
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
	 */
	public static void dataBaru() {
		try {
			
			bukaKoneksiDB(true);
			
			eksekusi = koneksiDataBase.createStatement();
			
			query = "DROP TABLE IF EXISTS data_mentah";
			
			eksekusi.execute(query);
			
			query = "CREATE TABLE IF NOT EXISTS data_mentah "
					+ "(UrutanData INT NOT NULL,"
					+ "Tema VARCHAR(255),"
					+ "Koding VARCHAR(255),"
					+ "Ide_Utama MEDIUMTEXT,"
					+ "Jawaban MEDIUMTEXT,"
					+ "Pertanyaan MEDIUMTEXT,"
					+ "Impresi MEDIUMTEXT,"
					+ "PRIMARY KEY (UrutanData))";
			
			eksekusi.execute(query);
		
			bukaKoneksiDB(false);
			
		} catch (SQLException galat) {
			System.out.println("Galat dalam membuat database baru."); //TODO: Hapus ini
			galat.printStackTrace();
		}
	}
	
	/**
	 * Memuat database yang sudah pernah dibuat. Apabila data belum ada maka memanggil metode dataBaru.
	 * @see #dataBaru()
	 */
	public static void muatData() {
		try {
			bukaKoneksiDB(true);
			
			query = "SELECT * FROM data_mentah";
			
			perintah = koneksiDataBase.prepareStatement(query);
			perintah.execute(query);
			
			bukaKoneksiDB(false);
		} catch (SQLException galat) {
			System.out.println("Galat dalam memuat database yang ada."); //TODO: Hapus ini
			galat.printStackTrace();
		}
	}
	
	/**
	 * Memuat data tertentu dari database
	 * @param id jenis data yang akan dimuat
	 * @param nomorData nomor data yang akan dimuat
	 * @return data dalam bentuk String (kata/kalimat)
	 */
	public static String muatData(idData id, Integer nomorData) {
		String data = null;
		String namaKolomData = id.toString();
		
		try {
			bukaKoneksiDB(true);
			
			query = "SELECT " +namaKolomData+ " FROM data_mentah WHERE UrutanData="+nomorData;
			
			eksekusi = koneksiDataBase.createStatement();
			hasilQuery = eksekusi.executeQuery(query);
			data = hasilQuery.getString(namaKolomData);
			
			bukaKoneksiDB(false);
		} catch (SQLException galat) {
			System.out.println("Galat dalam memuat data "+ namaKolomData+"..."); //TODO: Hapus ini
			galat.printStackTrace();
			
		}
		
		return data;
	}
	
	/**
	 * Metode untuk membuka koneksi ke MySQL. Metode ini harus menjadi metode pertama di setiap metode kelas ini.
	 * Ubah nilai bukaKoneksi menjadi true untuk membuka dan memulai akses ke MySQL. 
	 * Ubah nilai bukaKoneksi menjadi false untuk menutup koneksi ke MySQL.
	 * @param bukaKoneksi bernilai antara true atau false
	 */
	private static void bukaKoneksiDB(boolean bukaKoneksi) {
		
		URL referensi = null;
		try {
			referensi = new File(DIREKTORI_DATA).toURI().toURL();
		} catch (MalformedURLException galat) {
			Proses.pesan("Terjadi galat dalam penyusunan URL."); //TODO: Hapus ini
			galat.printStackTrace();
			return;
		}
		
		if(sumberData == null) sumberData = new SQLiteDataSource();
		sumberData.setUrl("jdbc:sqlite:"+referensi+DEFAULT_DATABASE+".db");
		
		if(bukaKoneksi == true) {
			try {
				koneksiDataBase = sumberData.getConnection();
				Proses.pesan("Koneksi berhasil."); //TODO: Hapus ini
			} catch (SQLException galat) {
				System.out.println("Galat dalam pembukaan database."); //TODO: Hapus ini
				galat.printStackTrace();
			}
			return;
		}
		
		if(bukaKoneksi == false) {
			try {
				if(hasilQuery != null) hasilQuery.close();
				if(eksekusi != null) eksekusi.close();
				if(perintah != null) perintah.close();
				koneksiDataBase.close();
				
				Proses.pesan("Koneksi berhasil ditutup."); //TODO: Hapus ini
			} catch (SQLException galat) {
				System.out.println("Galat dalam penutupan database."); //TODO: Hapus ini
				galat.printStackTrace();
			}
			return;
		}
	}
	
	/**
	 * Merekam data tertentu ke dalam database
	 * @param NomorData nomor data yang akan direkam
	 * @param kolomData jenis data yang akan direkam
	 * @param data data dalam bentuk String (kata/kalimat)
	 */
	public static void rekamData(int NomorData, String kolomData, String data) {
		try {
			bukaKoneksiDB(true);
			
			query = "SELECT * FROM data_mentah WHERE UrutanData="+NomorData;
			
			eksekusi = koneksiDataBase.createStatement();
			hasilQuery = eksekusi.executeQuery(query);
			
			if (hasilQuery == null) {
				query = "INSERT INTO data_mentah (UrutanData, "+kolomData+") VALUES (?, ?)";
				} else {
					query = "UPDATE data_mentah SET UrutanData = ?, "+kolomData+" = ?";
			}
			
			perintah = koneksiDataBase.prepareStatement(query);
			perintah.setInt(1, NomorData);
			perintah.setString(2, data);
			perintah.executeUpdate();
			
			Proses.pesan("Berhasil merekam data "+kolomData+" di nomor "+NomorData+" ke dalam database!"); //TODO: Hapus ini
			
			bukaKoneksiDB(false);
		} catch (SQLException galat) {
			Proses.pesan("Galat dalam merekam data "+ kolomData +" di nomor "+ NomorData +"."); //TODO: Hapus ini
			galat.printStackTrace();
		}
	}
	/**
	 * Memasukkan semua data ke database.
	 * @param NomorData
	 * @param Tema
	 * @param Koding
	 * @param IdeUtama
	 * @param Jawaban
	 * @param Pertanyaan
	 * @param Impresi
	 */
	public static void rekamData(int NomorData, String Tema, String Koding, String IdeUtama, String Jawaban, String Pertanyaan, String Impresi) {
		
		bukaKoneksiDB(true);
		
		query = "SELECT * FROM data_mentah WHERE UrutanData="+NomorData;
		
		try {
			eksekusi = koneksiDataBase.createStatement();
			hasilQuery = eksekusi.executeQuery(query);
			
			if (hasilQuery == null) {
				query = "INSERT INTO data_mentah VALUES (?, ?, ?, ?, ?, ?, ?)";
				} else {
					query = "UPDATE data_mentah SET UrutanData = ?, Tema = ?, Koding = ?, Ide_Utama = ?, Jawaban = ?, Pertanyaan = ?, Impresi = ?";
			}
			
			perintah = koneksiDataBase.prepareStatement(query);
			perintah.setInt(1, NomorData);
			perintah.setString(2, Tema);
			perintah.setString(3, Koding);
			perintah.setString(4, IdeUtama);
			perintah.setString(5, Jawaban);
			perintah.setString(6, Pertanyaan);
			perintah.setString(7, Impresi);
			perintah.executeUpdate();			
			
			
		} catch (SQLException galat) {
			Proses.pesan("Galat dalam Operasi Database: "); //TODO: Hapus ini
			galat.printStackTrace();
		}
		
		bukaKoneksiDB(false);
	}
	
	/**
	 * Membuat pasangan tema-koding dan mengelompokkan berdasarkan tema dari tabel wawancara di database.
	 */
	public static void pairTK() {
		try {
			bukaKoneksiDB(true);
			
			eksekusi = koneksiDataBase.createStatement();
			query = "CREATE pairTK AS SELECT Tema AS Tema, UrutanData AS Urutan, COUNT(Tema) AS Frekuensi, Koding "
					+ "FROM data_mentah GROUP BY Tema, Urutan, Koding"
					+ "ORDER BY Urutan DESC, COUNT(Tema) DESC";
			eksekusi.execute(query);
			Proses.pesan("Berhasil membuat tabel pairTK!"); //TODO: Hapus ini
			bukaKoneksiDB(false);
		} catch (SQLException galat) {
			Proses.pesan("Galat dalam operasi pembuatan tabel pasangan tema-koding: "); //TODO: Hapus ini
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
		
		try {
			bukaKoneksiDB(true);
			
			query = "SELECT Urutan FROM pairTK WHERE Tema="+tema+" LIMIT 1";
			eksekusi = koneksiDataBase.createStatement();
			hasilQuery = eksekusi.executeQuery(query);
			
			kalimat = "Tema "+Terbilang.sebut(hasilQuery.getInt("UrutanData"))+" adalah "+tema;
			
			Proses.pesan("Berhasil mengurut Tema!"); //TODO: Hapus ini
			
			bukaKoneksiDB(false);
			
		} catch (SQLException galat) {
			Proses.pesan("Terjadi galat dalam mengurut Tema di database."); //TODO: Hapus ini
			galat.printStackTrace();
		}
		return kalimat;
	}
	
	/**
	 * Metode untuk mengekstrak koding sesuai dengan tema.
	 * @param tema
	 * @return Kumpulan koding yang ada di suatu tema
	 */
	public static String sebutKoding(String tema) {
		String kalimat = null;
		try {
			bukaKoneksiDB(true);
			
			query = "SELECT Koding FROM pairTK WHERE Tema="+tema+" GROUP BY Koding";
			perintah = koneksiDataBase.prepareStatement(query);
			hasilQuery = perintah.executeQuery(query);
			while(hasilQuery.next()) {
				kalimat += hasilQuery.getString("Koding")+",";
			}
			Proses.pesan("Berhasil mengurut koding!"); //TODO: Hapus ini
			bukaKoneksiDB(true);
			
		} catch (SQLException galat) {
			Proses.pesan("Galat dalam operasi pemilihan koding dari tema."); //TODO: Hapus ini
			galat.printStackTrace();
		}
		return kalimat;
	}
	
	/**
	 * Metode ini dibuat untuk menyimpan hasil query MySQL ke berkas CSV. 
	 * Metode ini merupakan modifikasi dari kode yang dibuat oleh Nam Ha Minh dari codejava.net.
	 * 
	 * @see <a href="https://www.codejava.net/coding/java-code-example-to-export-from-database-to-csv-file">Nam Ha Minh</a>
	 */
	public static void simpanCSV() {
		try (BufferedWriter penulisData = new BufferedWriter(new FileWriter(DEFAULT_DATABASE+".csv"))) 
		{	
			bukaKoneksiDB(true);
			
			query = "SELECT * FROM "+DEFAULT_DATABASE;
			eksekusi = koneksiDataBase.createStatement();
			hasilQuery = eksekusi.executeQuery(query);
			
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
			Proses.pesan("Galat dalam mengurutkan data di database."); //TODO: Hapus ini
			galat.printStackTrace();
		} catch (IOException galat) {
			Proses.pesan("Galat dalam penyimpanan berkas."); //TODO: Hapus ini
			galat.printStackTrace();
		}
	}
}
