package anwan;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import anwan.proses.Proses;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 *<h2>Penata Layar</h2>
 * Kelas yang berfungsi untuk menyiapkan dan mengatur layar yang akan ditampilkan di aplikasi. 
 * Kelas ini harus diinisialisasikan terlebih dahulu melalui konstruktornya di setiap Layar
 * sebelum digunakan karena properti dan metodenya bersifat <i>nonstatic</i>.
 * @author karazubald
 *
 */
public class PenataLayar {
	private static Scene TampilanSekarang;
	private static final String FolderTampilan = "src/anwan/tampilan/".replace("/", File.separator);
	private static String LokasiFXML, LokasiCSS;
	private static HashMap<Integer, Parent> KumpulanTampilan = new HashMap<>();
	private static FXMLLoader PembacaFXML;
	
	/**
	 * Metode ini adalah metode yang pertama dipanggil. 
	 * Metode ini menyiapkan layar dari 0 sampai ke bilangan yang disebutkan di jumlahTampilan.
	 * @param jumlahTampilan banyak layar aplikasi yang ditampilkan dalam bentuk bilangan cacah yang lebih besar dari 1.
	 * @throws Exception: memunculkan info galat yang terjadi selama proses inisialisasi.
	 */
	private static void initTampilan(int jumlahTampilan) {
		Proses.hitungWaktu(true, PenataLayar.class); //TODO: hapus ini
		
		URL referensi;
		for(Integer nomorTampilan = 0; nomorTampilan <= Integer.valueOf(jumlahTampilan); nomorTampilan++) {
			LokasiFXML = FolderTampilan + "Layar_" + nomorTampilan+".fxml";
			LokasiCSS = FolderTampilan + "Layar_" + nomorTampilan+".css";
			try {
				referensi = new File(LokasiFXML).toURI().toURL();
				PembacaFXML = new FXMLLoader(referensi);
				KumpulanTampilan.put(nomorTampilan, PembacaFXML.load());
				
				referensi = new File(LokasiCSS).toURI().toURL();
				KumpulanTampilan.get(nomorTampilan).getStylesheets().add(referensi.toExternalForm());
				
			} catch (Exception galat) {
				galat.printStackTrace();
			}
		}
		
		Proses.hitungWaktu(false, PenataLayar.class); //TODO: hapus ini
	}
	
	/**
	 * Menyiapkan layar sesuai yang disebutkan dalam nomorTampilan.
	 * @param namaTampilan nama yang digunakan untuk menyebutkan layar yang akan ditampilkan.
	 * @param nomorTampilan nomor layar yang akan ditampilkan dalam bentuk bilangan cacah yang lebih besar dari 1.
	 * @return Layar yang siap ditampilkan.
	 */
	public static Scene munculkanTampilan(String namaTampilan, Integer nomorTampilan) {
		Proses.pesan("Metode tampilkanTampilan di Penata Layar");//TODO: hapus ini
		Proses.hitungWaktu(true, PenataLayar.class); //TODO: hapus ini
		
		initTampilan(4);
		TampilanSekarang = new Scene(KumpulanTampilan.get(nomorTampilan));
		TampilanSekarang.setRoot(KumpulanTampilan.get(nomorTampilan));
		
		Proses.hitungWaktu(false, PenataLayar.class);//TODO: hapus ini
		
		return TampilanSekarang;
	}
}
