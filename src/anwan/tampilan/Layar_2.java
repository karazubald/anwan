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
import javafx.stage.Stage;
/**
 * <h2>Layar 2</h2>
 * Layar untuk melakukan penyuntingan dan input data dari transkrip wawancara.
 * 
 * @author karazubald
 *
 */
public class Layar_2 implements Initializable {
	
	private Stage aplikasi;
	private Scene tampilan;
	
	public void hapusSemua(ActionEvent klik) {
		// TODO: Membuat isi semua textbox menjadi kosong ("");
	}
	
	public void lanjutAnalisis(ActionEvent klik) {
		PenataLayar penataTampilan = new PenataLayar();
		penataTampilan.siapkanTampilan(4);
		
		tampilan = penataTampilan.munculkanTampilan("Layar Menu Utama", 1);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}
