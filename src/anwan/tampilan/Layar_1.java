package anwan.tampilan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import anwan.PenataLayar;
import anwan.data.DataBase;
import anwan.proses.Proses;
import anwan.tampilan.lainnya.Informasi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * <h2>Layar 1</h2>
 * Layar utama dari aplikasi Anwan.
 * @author karazubald
 *
 */
public class Layar_1 implements Initializable {

	@FXML
	private JFXButton buat;
	@FXML
	private JFXButton muat;
	@FXML
	private JFXButton keluar;
	@FXML
	private Label bantuan;
	@FXML
	private ImageView logo;
	
	private Stage aplikasi;
	private Scene tampilan;
	
	/**
	 * Membuat data baru dan mengarahkan ke layar input data.
	 * @param klik id dari event tetikus
	 */
	public void buatBaru(ActionEvent klik) {
		Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
		
		DataBase.dataBaru();
		
		tampilan = PenataLayar.munculkanTampilan("Layar Input Data", 2);
		//tampilan = penataTampilan.munculkanTampilan("Layar Input Data", 2);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
		
		
		Proses.hitungWaktu(false, this.getClass()); //TODO: hapus ini
	}

	/**
	 * Memuat data dari SQLite dan mengarahkan ke layar input data.
	 * @param ae
	 */
	public void muatData(ActionEvent ae) {
		Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
		
		DataBase.muatData();
		
		tampilan = PenataLayar.munculkanTampilan("Layar Input Data", 2);
		//tampilan = penataTampilan.munculkanTampilan("Layar Input Data", 2);
		
		aplikasi = (Stage) ((Node) ae.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
		
		Proses.hitungWaktu(false, this.getClass()); //TODO: hapus ini
	}
	
	/**
	 * Mengaktifkan layar tutorial.
	 * @param ae
	 */
	public void tutorial(ActionEvent ae) {
		// TODO: Mengarahkan ke tutorial (gambar / video)
	}
	
	/**
	 * Segera menghentikan aplikasi ketika diaktifkan.
	 * @param ae
	 */
	public void keluarAplikasi(ActionEvent ae) {
		System.exit(0);
	}
	
	/**
	 * Metode untuk aksi setelah mengklik logo.
	 * @param me
	 */
	public void klikLogo(MouseEvent me) {
		Informasi.Tampilkan();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Proses.pesan("Metode Initialize di Layar 1"); //TODO: hapus ini
		Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
		
		Image logoAnwan = null;
		
		String namaLogo = "anwanLogo.png";
		String lokasiLogo = System.getProperty("user.dir")+"/src/anwan/resource/".replace("/", File.separator)+namaLogo;
		File berkasLogo = new File(lokasiLogo);
		
		URL urlLogo = null;
		try {
			urlLogo = berkasLogo.toURI().toURL();
		} catch (MalformedURLException galat) {
			Proses.pesan("Galat di inisialisasi URL:");
			galat.printStackTrace();
		}
		
		try (InputStream streamLogo = urlLogo.openStream()){
			logoAnwan = new Image(streamLogo);
		} catch (IOException galat) {
			Proses.pesan("Galat dalam pemrosesan berkas:");
			galat.printStackTrace();
		}
		
		logo.setImage(logoAnwan);
		
		Proses.hitungWaktu(false, this.getClass()); //TODO: hapus ini
	}
}
