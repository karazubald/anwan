package anwan;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
	private Scene tampilanSekarang;
	private final String folderTampilan = "tampilan/".replace("/", File.separator);
	private String fxml, css;
	private HashMap<Integer, Parent> kumpulanTampilan = new HashMap<>();
	private FXMLLoader loader;
	
	/**
	 * Metode ini harus menjadi yang pertama dipanggil setelah inisialisasi. Metode ini menyiapkan layar dari 0 sampai ke bilangan yang disebutkan di jumlahTampilan.
	 * @param jumlahTampilan banyak layar aplikasi yang ditampilkan dalam bentuk bilangan cacah yang lebih besar dari 1.
	 */
	public void siapkanTampilan(int jumlahTampilan) {
		for(Integer nomorTampilan = 0; nomorTampilan <= Integer.valueOf(jumlahTampilan); nomorTampilan++) {
			fxml = folderTampilan+"Layar_"+nomorTampilan+".fxml";
			css = folderTampilan+"Layar_"+nomorTampilan+".css";
			System.out.println(fxml+" & "+css);
			try {
				loader = new FXMLLoader(getClass().getResource(fxml));
				kumpulanTampilan.put(nomorTampilan, loader.load());
				kumpulanTampilan.get(nomorTampilan).getStylesheets().add(getClass().getResource(css).toExternalForm());
				System.out.println(kumpulanTampilan.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Menyiapkan layar sesuai yang disebutkan dalam nomorTampilan.
	 * @param namaTampilan nama yang digunakan untuk menyebutkan layar yang akan ditampilkan.
	 * @param nomorTampilan nomor layar yang akan ditampilkan dalam bentuk bilangan cacah yang lebih besar dari 1.
	 * @return Layar yang siap ditampilkan.
	 */
	public Scene munculkanTampilan(String namaTampilan, Integer nomorTampilan) {
		tampilanSekarang = new Scene(kumpulanTampilan.get(nomorTampilan));
		return tampilanSekarang;
	}
	
}
