package anwan.tampilan;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import anwan.proses.Proses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
/**
 * <h2>Layar 4</h2>
 * Layar yang menampilkan data wawancara yang sudah dikelompokkan dalam database.
 * 
 * @author karazubald
 *
 */
public class Layar_4 implements Initializable {
	
	@FXML
	private AnchorPane background0;
	@FXML
	private Pane header;
	@FXML
	private AnchorPane background1;
	@FXML
	private Label tema;
	@FXML
	private JFXComboBox<String> ComboBoxTema;
	@FXML
	private JFXButton tombol_ubah;
	@FXML
	private JFXTextArea kotakTulisan;
	@FXML
	private JFXButton verifikasi;
	@FXML
	private Pane footer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO: Memuat data tema yang sudah ada di SQLite DataBase
	}
	
	/**
	 * Melakukan pembaruan data di database.
	 * @param klik
	 */
	public void ubahData(ActionEvent klik) {
		// TODO: Mengubah data di SQLite DataBase
	}
	
	/**
	 * Melakukan finalisasi terhadap data yang sudah disunting.
	 * Data yang sudah difinalisasi hanya dapat ditampilkan, tidak dapat disunting.
	 * @param klik
	 */
	public void verifikasiData(ActionEvent klik) {
		// TODO: Melakukan pembaruan (update) dan mengakhiri penyuntingan lebih lanjut
	}

}
