package anwan.tampilan;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

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
	
	private Stage aplikasi;
	private Scene tampilan;
	
	public void hapus(ActionEvent klik) {
		TextBox.setText("");
	}
	
	public void lanjutAnalisis(ActionEvent klik) {
		tampilan = PenataLayar.munculkanTampilan("Layar Input Data", 2);
		//tampilan = penataTampilan.munculkanTampilan("Layar Input Data", 2);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO: Inisialisasi isi TextBox dari Layar 2;
		TextBox.setText("");
	}

}
