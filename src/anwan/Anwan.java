package anwan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

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

public class Anwan extends Application implements Initializable {
	@FXML 
	private AnchorPane anchorpane;
	@FXML
	private JFXButton tombol;
	@FXML
	private Label label;
	@FXML
	private JFXTextArea ta;
	
    private Stage aplikasi;
    private Scene tampilan;
    private Parent root;
    
	@Override
	public void start(Stage aplikasi) {
		String fxml = "tampilan/Layar_0.fxml".replace("/", File.separator);
		String css = "tampilan/Layar_0.css".replace("/", File.separator);

		try {
			root = FXMLLoader.load(getClass().getResource(fxml));
			tampilan = new Scene(root);
			tampilan.getStylesheets().add(getClass().getResource(css).toExternalForm());
			
			aplikasi.setScene(tampilan);
			aplikasi.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setuju(ActionEvent klik) {
		
		String fxml = "tampilan/Layar_1.fxml".replace("/", File.separator);
		String css = "tampilan/Layar_1.css".replace("/", File.separator);

		try {
			root = FXMLLoader.load(getClass().getResource(fxml));
			aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();

			tampilan = new Scene(root);
			tampilan.getStylesheets().add(getClass().getResource(css).toExternalForm());

			aplikasi.setScene(tampilan);
			aplikasi.show();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	}
	
	public void lisensi(MouseEvent me) {
		try {
			var lisensi = getClass().getResourceAsStream("LICENSE-ID");
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
	
	public static void main(String[] args) {
		launch(args);
	}
}
