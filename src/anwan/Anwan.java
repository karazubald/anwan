package anwan;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
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
/**
 * <h1>AnWan</h1>
 * Aplikasi ini dibuat untuk membantu menyiapkan laporan analisis wawancara dalam mata kuliah Psikodiagnostik: Wawancara.
 * <p>
 * Aplikasi ini mencatat kata/kalimat yang didapatkan dari transkrip wawancara. 
 * Hasil catatan kemudian disusun dari tema pertama hingga tema terakhir.
 * Setelah itu, pengguna dapat mengetikkan analisis atau interpretasi dari setiap tema.
 * Hasil akhir dari aplikasi utamanya adalah berkas dalam bentuk CSV dan TXT.
 * <p>
 * Berkas CSV adalah susunan tema yang sudah tercatat dari aplikasi.
 * <p>
 * Berkas TXT adalah hasil akhir dari analisis/interpretasi dari tema di CSV dalam bentuk paragraf.
 * @author karazubald
 * @version 0.1
 * @since 2019
 */
public class Anwan extends Application {
    private Stage aplikasi;
    private Scene tampilan;
    
	@Override
	public void start(Stage aplikasi) {
		tampilan = PenataLayar.munculkanTampilan("Layar Persetujuan", 0);
		//tampilan = penataTampilan.munculkanTampilan("Layar Persetujuan", 0);
		
		aplikasi.setScene(tampilan);
		aplikasi.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
