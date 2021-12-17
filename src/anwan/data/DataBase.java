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
 * data di database. Database yang digunakan dalam kelas ini adalah MySQL versi 8 sehingga operasi dalam kelas ini
 * hanya dapat bekerja apabila pengguna sudah menginstal MySQL versi 8 ke atas.
 * 
 * @author karazubald
 *
 */
public class DataBase {
	private static SQLiteDataSource sumberData = null;
	private static Connection koneksiDataBase = null;
	private static PreparedStatement perintah = null;
	private static String query = null;
	private static ResultSet hasilQuery = null;
	private static String namaBerkasDB = null;
	private static final String DEFAULT_DATABASE = "ANWAN_DATABASE";
	private static final String DIREKTORI_DATA = System.getProperty("user.dir")+"/src/anwan/data/".replace("/", File.separator);
	
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
			Proses.pesan("Terjadi galat dalam penyusunan URL: ");
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
				System.out.println("Galat dalam Operasi Database: "); //TODO: Hapus ini
				galat.printStackTrace();
			}
			return;
		}
		
		if(bukaKoneksi == false) {
			try {
				if(hasilQuery != null) hasilQuery.close();
				if(perintah != null) perintah.close();
				koneksiDataBase.close();
				
				Proses.pesan("Koneksi berhasil ditutup."); //TODO: Hapus ini
			} catch (SQLException galat) {
				System.out.println("Galat dalam Operasi Database: "); //TODO: Hapus ini
				galat.printStackTrace();
			}
			return;
		}
	}
	
	/**
	 * Membuat database baru sesuai dengan NamaDataBase. 
	 * Database baru secara otomatis membuat tabel data mentah dengan ketentuan sebagai berikut.
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
	public static void dataBaru() {
		try {
			
			bukaKoneksiDB(true);
			
			query = "CREATE TABLE data_mentah "
					+ "(UrutanData INT NOT NULL,"
					+ "Tema VARCHAR(255),"
					+ "Koding VARCHAR(255),"
					+ "Ide_Utama MEDIUMTEXT,"
					+ "Jawaban MEDIUMTEXT,"
					+ "Pertanyaan MEDIUMTEXT,"
					+ "Impresi MEDIUMTEXT,"
					+ "PRIMARY KEY (UrutanData))";
			
			perintah = koneksiDataBase.prepareStatement(query);
			perintah.execute(query);
			
			bukaKoneksiDB(false);
			
		} catch (SQLException galat) {
			System.out.println("Galat dalam Operasi Database: "); //TODO: Hapus ini
			galat.printStackTrace();
		}
	}
	
	/**
	 * Memuat database yang sudah pernah dibuat.
	 */
	public static void muatData() {
		try {
			bukaKoneksiDB(true);
			
			query = "SELECT * FROM data_mentah";
			
			perintah = koneksiDataBase.prepareStatement(query);
			perintah.execute(query);
			
			bukaKoneksiDB(false);
		} catch (SQLException galat) {
			System.out.println("Galat dalam Operasi Database: "); //TODO: Hapus ini
			galat.printStackTrace();
		}
	}
	
	public static void merekamData(int NomorData, String Tema, String Koding, String IdeUtama, String Jawaban, String Pertanyaan, String Impresi) {
		
		bukaKoneksiDB(true);
		query = "SELECT * FROM data_mentah WHERE UrutanData="+NomorData;
		
		try {
			perintah = koneksiDataBase.prepareStatement(query);
			hasilQuery = perintah.executeQuery(query);
			
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
			System.out.println("Galat dalam Operasi Database: "); //TODO: Hapus ini
			galat.printStackTrace();
		}
	}
	
	/**
	 * Membuat pasangan tema-koding dan mengelompokkan berdasarkan tema dari tabel wawancara di database.
	 */
	public static void pairTK() {
		try {
			bukaKoneksiDB(true);
			
			query = "CREATE pairTK AS SELECT Tema AS Tema, UrutanData AS Urutan, COUNT(Tema) AS Frekuensi, Koding "
					+ "FROM data_mentah GROUP BY Tema, Urutan, Koding"
					+ "ORDER BY Urutan DESC, COUNT(Tema) DESC";
			
			perintah = koneksiDataBase.prepareStatement(query);
			perintah.execute(query);
			
			bukaKoneksiDB(false);

		} catch (SQLException galat) {
			System.out.println("Galat dalam Operasi Database: "); //TODO: Hapus ini
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
			
			query = "SELECT UrutanData FROM pairTK WHERE Tema="+tema+" LIMIT 1";
			perintah = koneksiDataBase.prepareStatement(query);
			hasilQuery = perintah.executeQuery(query);
			
			kalimat = "Tema "+Terbilang.sebut(hasilQuery.getInt("UrutanData"))+" adalah "+tema;
			
			bukaKoneksiDB(false);
			
		} catch (SQLException galat) {
			System.out.println("Terjadi galat: ");
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
			query = "SELECT Koding FROM pairTK WHERE Tema="+tema+" GROUP BY Koding";
			perintah = koneksiDataBase.prepareStatement(query);
			hasilQuery = perintah.executeQuery(query);
			while(hasilQuery.next()) {
				kalimat += hasilQuery.getString("Koding")+",";
			}
		} catch (SQLException galat) {
			System.out.println("Galat dalam Operasi Database: "); //TODO: Hapus ini
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
		namaBerkasDB = DEFAULT_DATABASE;
		try (BufferedWriter penulisData = new BufferedWriter(new FileWriter(namaBerkasDB+".csv"))) 
		{	
			bukaKoneksiDB(true);
			
			query = "SELECT * FROM "+namaBerkasDB;
			perintah = koneksiDataBase.prepareStatement(query);
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
			System.out.println("Galat dalam Operasi Database: "); //TODO: Hapus ini
			galat.printStackTrace();
		} catch (IOException galat) {
			System.out.println("Galat dalam Operasi Berkas: "); //TODO: Hapus ini
			galat.printStackTrace();
		}
	}
}
