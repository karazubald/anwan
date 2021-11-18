package anwan.tampilan;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import anwan.proses.DirektoriInisiator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Layar_0 implements Initializable {
	@FXML 
	private AnchorPane anchorpane;
	@FXML
	private JFXButton tombol;
	@FXML
	private Label label;
	@FXML
	private JFXTextArea ta;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setuju(ActionEvent ae) {
		String fxml = "Layar_1.fxml";
		
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource(fxml));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		Scene tampilan = new Scene(parent);
		Stage aplikasi = (Stage) anchorpane.getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
	}
	
	public void lisensi(ActionEvent ae) {
		
	}
}
