package anwan.tampilan;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import anwan.PenataLayar;
import anwan.proses.Proses;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * <h2>Layar 0</h2>
 * Layar pendahuluan dari aplikasi ini.
 * 
 * @author karazubald
 *
 */
public class Layar_0 implements Initializable {
	@FXML
	private JFXTextArea ta;
    
    private Stage aplikasi;
    private Scene tampilan;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
    	Proses.pesan("Metode Initialize di Layar 0"); //TODO: hapus ini
    	Proses.hitungWaktu(false, this.getClass()); //TODO: hapus ini
	}

    /**
     * Metode untuk beralih ke layar menu utama (Layar 1).
     * @param klik
     */
	public void setuju(ActionEvent klik) {
		Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
		
		tampilan = PenataLayar.munculkanTampilan("Layar Menu Utama", 1);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
		
		Proses.hitungWaktu(false, this.getClass()); //TODO: hapus ini
	}
	
	/**
	 * Metode untuk menapilkan lisensi dalam kotak tulisan.
	 * @param klik
	 */
	public void lisensi(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass()); //TODO hapus ini
		
		try {
			InputStream lisensi = getClass().getResourceAsStream("LICENSE-ID.txt");
			BufferedReader pembacaIsi = new BufferedReader(new InputStreamReader(lisensi));
			
			StringBuilder isiLisensi = new StringBuilder();
			
			while(pembacaIsi.readLine() != null) {
				isiLisensi.append(pembacaIsi.readLine());
			}
			
			ta.setText(isiLisensi.toString());

			pembacaIsi.close();
			
		} catch (Exception e) {
			ta.setText(e.getMessage());
		}
		
		Proses.hitungWaktu(false, this.getClass()); //TODO: hapus ini
	}
}
