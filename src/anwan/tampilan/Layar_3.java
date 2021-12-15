package anwan.tampilan;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import anwan.PenataLayar;
import anwan.proses.DataTextBox;
import anwan.proses.Proses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * <h2>Layar 3</h2>
 * Layar yang menampilkan TextBox untuk menginput data dari transkrip wawancara.
 * 
 * @author karazubald
 *
 */
public class Layar_3 implements Initializable {
	@FXML
	private Label namaTextBox;
	@FXML
	private JFXTextArea TextBox;
	@FXML
	private JFXHamburger IkonKembali;
	@FXML
	private JFXHamburger IkonHapus;
	
	private Stage aplikasi;
	private Scene tampilan;

	/**
	 * Menghapus tulisan di kotak tulisan.
	 * @param klik
	 */
	public void hapus(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
		
		TextBox.setText("");
		
		Proses.hitungWaktu(false, this.getClass()); //TODO: hapus ini
	}
	
	/**
	 * Kembali ke layar input awal (Layar 2).
	 * @param klik
	 */
	public void kembali(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
		// TODO: Simpan otomatis sebelum kembali ke layar 2
		
		tampilan = PenataLayar.munculkanTampilan("Layar Input Data", 2);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();

		DataTextBox.kosongkanData();
		
		Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Proses.pesan("Metode Initialize di Layar 3"); //TODO: hapus ini
		Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
		
		animasiKembali();
		animasiHapus();
		
		namaTextBox.setText(DataTextBox.getId());
		TextBox.setText(DataTextBox.getData());
		
		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
	}
	
	/**
	 * Mempersiapkan animasi panah kembali ke IkonKembali (objek JFXHamburger).
	 * @see JFXHamburger
	 */
	private void animasiKembali() {
		HamburgerBackArrowBasicTransition animasi = new HamburgerBackArrowBasicTransition(IkonKembali);
		animasi.setRate(1);
		
		IkonKembali.addEventFilter(MouseEvent.MOUSE_ENTERED, (sentuh) -> {
			animasi.setRate(-1);
			animasi.setRate(animasi.getRate()*(-1));
			animasi.play();
		});
		
		IkonKembali.addEventFilter(MouseEvent.MOUSE_EXITED, (menjauh) -> {
			animasi.setRate(1);
			animasi.setRate(animasi.getRate()*(-1));
			animasi.play();
		});
		
		IkonKembali.addEventFilter(MouseEvent.MOUSE_CLICKED, (klik) -> {
			animasi.setRate(-1);
			animasi.setRate(animasi.getRate()*(-1));
			animasi.play();
		});
	}
	
	/**
	 * Mempersiapkan animasi hapus dan mengaplikasikannya ke IkonHapus (objek JFXHamburger).
	 * @see JFXHamburger
	 */
	private void animasiHapus() {
		HamburgerBasicCloseTransition animasi = new HamburgerBasicCloseTransition(IkonHapus);
		animasi.setRate(1);
		
		IkonHapus.addEventFilter(MouseEvent.MOUSE_ENTERED, (sentuh) -> {
			animasi.setRate(-1);
			animasi.setRate(animasi.getRate()*(-1));
			animasi.play();
		});
		
		IkonHapus.addEventFilter(MouseEvent.MOUSE_EXITED, (sentuh) -> {
			animasi.setRate(1);
			animasi.setRate(animasi.getRate()*(-1));
			animasi.play();
		});
		
		IkonHapus.addEventFilter(MouseEvent.MOUSE_CLICKED, (klik) -> {
			animasi.setRate(-1);
			animasi.setRate(animasi.getRate()*(-1));
			animasi.play();
		});
	}
}
