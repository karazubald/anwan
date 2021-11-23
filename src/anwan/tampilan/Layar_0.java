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
		// TODO Auto-generated method stub
	}
	
	public void setuju(ActionEvent klik) {
		tampilan = PenataLayar.munculkanTampilan("Layar Menu Utama", 1);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
	}
	
	public void lisensi(MouseEvent me) {
		try {
			InputStream lisensi = getClass().getResourceAsStream("LICENSE-ID.txt");
			BufferedReader pembacaIsi = new BufferedReader(new InputStreamReader(lisensi));
			String isiLisensi = "";
			while(pembacaIsi.readLine() != null) {
				isiLisensi += pembacaIsi.readLine();
			}
			ta.setText(isiLisensi);
			pembacaIsi.close();
			
		} catch (Exception e) {
			ta.setText(e.getMessage());
		}
	}
}
