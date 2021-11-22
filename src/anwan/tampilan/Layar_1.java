package anwan.tampilan;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import anwan.PenataLayar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
	 * Membuat data baru dengan mengarahkan ke layar input data.
	 * @param klik id dari event tetikus
	 */
	public void buatBaru(ActionEvent klik) {
		PenataLayar penataTampilan = new PenataLayar();
		penataTampilan.siapkanTampilan(4);
		
		tampilan = penataTampilan.munculkanTampilan("Layar Menu Utama", 1);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
	}

	public void muatData(ActionEvent ae) {
		// TODO: Memuat data dari file TXT.
	}
	
	public void tutorial(ActionEvent ae) {
		// TODO: Mengarahkan ke tutorial (gambar / video)
	}
	
	public void keluarAplikasi(ActionEvent ae) {
		System.exit(0);
	}
	
	public void klikLogo(ActionEvent ae) {
		// TODO: Memuat gambar setelah logo diklik
		logo.setImage(null);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
