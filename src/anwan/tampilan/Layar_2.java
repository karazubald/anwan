package anwan.tampilan;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import anwan.PenataLayar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * <h2>Layar 2</h2>
 * Layar untuk melakukan penyuntingan dan input data dari transkrip wawancara.
 * 
 * @author karazubald
 *
 */
public class Layar_2 implements Initializable {
	@FXML
	private Label LabelTema;
	@FXML
	private Label LabelKoding;
	@FXML
	private Label LabelIdeUtama;
	@FXML
	private Label LabelJawaban;
	@FXML
	private Label LabelPertanyaan;
	@FXML
	private Label LabelImpresi;
	@FXML
	private TextArea Tema;
	@FXML
	private TextArea Koding;
	@FXML
	private TextArea IdeUtama;
	@FXML
	private TextArea Jawaban;
	@FXML
	private TextArea Pertanyaan;
	@FXML
	private TextArea Impresi;
	@FXML
	private JFXHamburger IkonHapusSemua;
	@FXML
	private JFXHamburger IkonHapusTema;
	@FXML
	private JFXHamburger IkonHapusKoding;
	@FXML
	private JFXHamburger IkonHapusIdeUtama;
	@FXML
	private JFXHamburger IkonHapusJawaban;
	@FXML
	private JFXHamburger IkonHapusPertanyaan;
	@FXML
	private JFXHamburger IkonHapusImpresi;
	@FXML
	private JFXHamburger IkonLanjut;
	@FXML
	private JFXHamburger IkonKembali;
	
	private Stage aplikasi;
	private Scene tampilan;
	
	public void hapusSemua(ActionEvent klik) {
		// TODO: Illegal akses? Argument MisMatch?
		Tema.setText("");
		Koding.setText("");
		IdeUtama.setText("");
		Jawaban.setText("");
		Pertanyaan.setText("");
		Impresi.setText("");
	}
	
	public void inputTextBox(ActionEvent klik) {
		tampilan = PenataLayar.munculkanTampilan("Layar Analisis Data", 4);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
	}
	
	public void lanjutAnalisis(ActionEvent klik) {
		tampilan = PenataLayar.munculkanTampilan("Layar Analisis Data", 4);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
	}
	
	public void menuUtama(ActionEvent klik) {
		tampilan = PenataLayar.munculkanTampilan("Layar Menu Utama", 1);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inisialisiAnimasiHapus(IkonHapusSemua, IkonHapusTema, IkonHapusKoding, IkonHapusIdeUtama, IkonHapusJawaban, IkonHapusPertanyaan, IkonHapusImpresi);	
		inisialisiAnimasiLanjut();
		inisialisiAnimasiKembali();
	}
	
	private void inisialisiAnimasiHapus(JFXHamburger... KelompokIkon) {
		for(JFXHamburger ikon : KelompokIkon) {
			if(ikon.equals(KelompokIkon[0])) {
				HamburgerSlideCloseTransition animasi = new HamburgerSlideCloseTransition(ikon);
				animasi.setRate(-1);
				
				ikon.addEventFilter(MouseEvent.MOUSE_ENTERED, (sentuh) -> {
					animasi.setRate(-1);
					animasi.setRate(animasi.getRate()*(-1));
					animasi.play();
				});
				
				ikon.addEventFilter(MouseEvent.MOUSE_EXITED, (sentuh) -> {
					animasi.setRate(1);
					animasi.setRate(animasi.getRate()*(-1));
					animasi.play();
				});
				
				ikon.addEventFilter(MouseEvent.MOUSE_CLICKED, (klik) -> {
					animasi.setRate(-1);
					animasi.setRate(animasi.getRate()*(-1));
					animasi.play();
				});
			} else {
				HamburgerBasicCloseTransition animasi = new HamburgerBasicCloseTransition(ikon);
				animasi.setRate(-1);
				
				ikon.addEventFilter(MouseEvent.MOUSE_ENTERED, (sentuh) -> {
					animasi.setRate(-1);
					animasi.setRate(animasi.getRate()*(-1));
					animasi.play();
				});
				
				ikon.addEventFilter(MouseEvent.MOUSE_EXITED, (sentuh) -> {
					animasi.setRate(1);
					animasi.setRate(animasi.getRate()*(-1));
					animasi.play();
				});
				
				ikon.addEventFilter(MouseEvent.MOUSE_CLICKED, (klik) -> {
					animasi.setRate(-1);
					animasi.setRate(animasi.getRate()*(-1));
					animasi.play();
				});
			}
		}
	}
	
	private void inisialisiAnimasiLanjut() {
		
		HamburgerNextArrowBasicTransition animasi = new HamburgerNextArrowBasicTransition(IkonLanjut);
		animasi.setRate(-1);
		
		IkonLanjut.addEventFilter(MouseEvent.MOUSE_ENTERED, (sentuh) -> {
			animasi.setRate(-1);
			animasi.setRate(animasi.getRate()*(-1));
			animasi.play();
		});
		
		IkonLanjut.addEventFilter(MouseEvent.MOUSE_EXITED, (menjauh) -> {
			animasi.setRate(1);
			animasi.setRate(animasi.getRate()*(-1));
			animasi.play();
		});
	
		IkonLanjut.addEventFilter(MouseEvent.MOUSE_CLICKED, (klik) -> {
			animasi.setRate(-1);
			animasi.setRate(animasi.getRate()*(-1));
			animasi.play();
		});
	}
	
	private void inisialisiAnimasiKembali() {
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
}
