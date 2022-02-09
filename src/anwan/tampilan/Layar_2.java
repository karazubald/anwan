package anwan.tampilan;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import anwan.PenataLayar;
import anwan.data.DataBase;
import anwan.proses.PengangkutObjek;
import anwan.proses.PenghitungData;
import anwan.proses.Proses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	@FXML
	private TextField UrutanData;
	
	private Stage aplikasi;
	private Scene tampilan;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Proses.pesan("Metode Initialize di Layar 2"); //TODO: hapus ini
		Proses.hitungWaktu(true, this.getClass()); //TODO: hapus ini
		
		animasiHapus(IkonHapusSemua, IkonHapusTema, IkonHapusKoding, IkonHapusIdeUtama, IkonHapusJawaban, IkonHapusPertanyaan, IkonHapusImpresi);	
		animasiDataSelanjutnya();
		animasiDataSebelumnya();
		
		PenghitungData.setNomorData(1);
		
		// TODO: Memuat data dari Layar 3 dan Database
		initIsiKotakTulisan(PenghitungData.getNomorData());
		
		Proses.hitungWaktu(false, this.getClass()); //TODO: hapus ini
	}
	
	/**
	 * Memuat data dari kelas DataBase atau kelas PengangkutObjek ke kotak tulisan UrutanData, Tema, Koding, IdeUtama, Jawaban, Pertanyaan, dan Impresi.
	 */
	private void initIsiKotakTulisan(int nomorData) {
		UrutanData.setText(String.valueOf(nomorData));
		if(PengangkutObjek.getNomorID() != null) UrutanData.setText(PengangkutObjek.getNomorID().toString());
		
		Integer dataSekarang =  Integer.valueOf(UrutanData.getText());
		
		// TODO: Galat dalam memuat database
		String TemaSekarang = DataBase.muatData(DataBase.idData.Tema, dataSekarang);
		String KodingSekarang = DataBase.muatData(DataBase.idData.Koding, dataSekarang); 
		String IdeUtamaSekarang = DataBase.muatData(DataBase.idData.Ide_Utama, dataSekarang);
		String JawabanSekarang = DataBase.muatData(DataBase.idData.Jawaban, dataSekarang); 
		String PertanyaanSekarang = DataBase.muatData(DataBase.idData.Pertanyaan, dataSekarang);
		String ImpresiSekarang = DataBase.muatData(DataBase.idData.Impresi, dataSekarang);
		
		if(TemaSekarang != null) Tema.setText(TemaSekarang);
		if(KodingSekarang != null) Koding.setText(KodingSekarang);
		if(IdeUtamaSekarang != null) IdeUtama.setText(IdeUtamaSekarang);
		if(JawabanSekarang != null) Jawaban.setText(JawabanSekarang);
		if(PertanyaanSekarang != null) Pertanyaan.setText(PertanyaanSekarang);
		if(ImpresiSekarang != null) Impresi.setText(ImpresiSekarang);
		
		if(PengangkutObjek.getIdObjek() == null) return;
		if(PengangkutObjek.getIdObjek().equals(Tema.getId())) Tema.setText(PengangkutObjek.getIsiObjek());
		if(PengangkutObjek.getIdObjek().equals(Koding.getId())) Koding.setText(PengangkutObjek.getIsiObjek());
		if(PengangkutObjek.getIdObjek().equals(IdeUtama.getId())) IdeUtama.setText(PengangkutObjek.getIsiObjek());
		if(PengangkutObjek.getIdObjek().equals(Jawaban.getId())) Jawaban.setText(PengangkutObjek.getIsiObjek());
		if(PengangkutObjek.getIdObjek().equals(Pertanyaan.getId())) Pertanyaan.setText(PengangkutObjek.getIsiObjek());
		if(PengangkutObjek.getIdObjek().equals(Impresi.getId())) Impresi.setText(PengangkutObjek.getIsiObjek());
	}
	
	/**
	 * Mengaktifkan data impresi dan mengalihkan ke layar input impresi.
	 * @param klik
	 */
	public void inputImpresi(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass());//TODO: hapus ini
		
		int urutandata = Integer.valueOf(UrutanData.getText());
		
		PengangkutObjek.setNomorID(urutandata);
		PengangkutObjek.setIdObjek(Impresi.getId());
		PengangkutObjek.setIsiObjek(Impresi.getText());
		
		DataBase.rekamData(urutandata, Tema.getText(), Koding.getText(), IdeUtama.getText(), Jawaban.getText(), Pertanyaan.getText(), Impresi.getText());

		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
		
		aktifkanInputData(klik);
	}
	
	/**
	 * Mengaktifkan data pertanyaan dan mengalihkan ke layar input pertanyaan.
	 * @param klik
	 */
	public void inputPertanyaan(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass());//TODO: hapus ini
		
		int urutandata = Integer.valueOf(UrutanData.getText());
		
		PengangkutObjek.setNomorID(urutandata);
		PengangkutObjek.setIdObjek(Pertanyaan.getId());
		PengangkutObjek.setIsiObjek(Pertanyaan.getText());
		
		DataBase.rekamData(urutandata, Tema.getText(), Koding.getText(), IdeUtama.getText(), Jawaban.getText(), Pertanyaan.getText(), Impresi.getText());

		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
		
		aktifkanInputData(klik);
	}
	
	/**
	 * Mengaktifkan data jawaban dan mengalihkan ke layar input jawaban.
	 * @param klik
	 */
	public void inputJawaban(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass());//TODO: hapus ini
		
		int urutandata = Integer.valueOf(UrutanData.getText());
		
		PengangkutObjek.setNomorID(urutandata);
		PengangkutObjek.setIdObjek(Jawaban.getId());
		PengangkutObjek.setIsiObjek(Jawaban.getText());
		
		DataBase.rekamData(urutandata, Tema.getText(), Koding.getText(), IdeUtama.getText(), Jawaban.getText(), Pertanyaan.getText(), Impresi.getText());

		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
		
		aktifkanInputData(klik);
	}
	
	/**
	 * Mengaktifkan data ide utama dan mengalihkan ke layar input ide utama.
	 * @param klik
	 */
	public void inputIdeUtama(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass());//TODO: hapus ini
		
		int urutandata = Integer.valueOf(UrutanData.getText());
		
		PengangkutObjek.setNomorID(urutandata);
		PengangkutObjek.setIdObjek(IdeUtama.getId());
		PengangkutObjek.setIsiObjek(IdeUtama.getText());
		
		DataBase.rekamData(urutandata, Tema.getText(), Koding.getText(), IdeUtama.getText(), Jawaban.getText(), Pertanyaan.getText(), Impresi.getText());

		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
		
		aktifkanInputData(klik);
	}
	
	/**
	 * Mengaktifkan data koding dan mengalihkan ke layar input koding.
	 * @param klik
	 */
	public void inputKoding(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass());//TODO: hapus ini
		
		int urutandata = Integer.valueOf(UrutanData.getText());
		
		PengangkutObjek.setNomorID(urutandata);
		PengangkutObjek.setIdObjek(Koding.getId());
		PengangkutObjek.setIsiObjek(Koding.getText());
		
		DataBase.rekamData(urutandata, Tema.getText(), Koding.getText(), IdeUtama.getText(), Jawaban.getText(), Pertanyaan.getText(), Impresi.getText());

		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
		
		aktifkanInputData(klik);
	}
	
	/**
	 * Mengaktifkan data tema dan mengalihkan ke layar input tema.
	 * @param klik
	 */
	public void inputTema(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass());//TODO: hapus ini
		
		int urutandata = Integer.valueOf(UrutanData.getText());
		
		PengangkutObjek.setNomorID(urutandata);
		PengangkutObjek.setIdObjek(Tema.getId());
		PengangkutObjek.setIsiObjek(Tema.getText());
		
		DataBase.rekamData(urutandata, Tema.getText(), Koding.getText(), IdeUtama.getText(), Jawaban.getText(), Pertanyaan.getText(), Impresi.getText());

		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
		
		aktifkanInputData(klik);
	}
	
	/**
	 * Mengalihkan ke layar analisis data (Layar 4).
	 * @param klik
	 */
	public void lanjutAnalisis(ActionEvent klik) {
		Proses.hitungWaktu(true, this.getClass());//TODO: hapus ini
		
		int urutandata = Integer.valueOf(UrutanData.getText());
		DataBase.rekamData(urutandata, Tema.getText(), Koding.getText(), IdeUtama.getText(), Jawaban.getText(), Pertanyaan.getText(), Impresi.getText());
		
		tampilan = PenataLayar.munculkanTampilan("Layar Analisis Data", 4);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
		
		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
	}
	
	/**
	 * Mengalihkan ke layar menu utama (Layar 1).
	 * @param klik
	 */
	public void menuUtama(ActionEvent klik) {
		Proses.hitungWaktu(true, this.getClass());//TODO: hapus ini
		
		tampilan = PenataLayar.munculkanTampilan("Layar Menu Utama", 1);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
		
		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
	}
	/**
	 * Mengalihkan ke layar input data (Layar 3).
	 * @param klik
	 */
	private void aktifkanInputData(MouseEvent klik) {
		Proses.hitungWaktu(true, klik.getClass()); //TODO: hapus ini
		
		System.out.println(klik.getSource().toString());
		
		tampilan = PenataLayar.munculkanTampilan("Layar Analisis Data", 3);
		
		aplikasi = (Stage) ((Node) klik.getSource()).getScene().getWindow();
		aplikasi.setScene(tampilan);
		aplikasi.show();
		
		Proses.hitungWaktu(false, klik.getClass()); //TODO: hapus ini
	}
	
	/**
	 * Mempersiapkan animasi hapus dan mengaplikasikannya ke KelompokIkon.
	 * @param KelompokIkon satu atau lebih objek JFXHamburger
	 * @see <a href="https://javadoc.io/doc/com.jfoenix/jfoenix/latest/com/jfoenix/controls/JFXHamburger.html">JFXHamburger</a>
	 */
	private void animasiHapus(JFXHamburger... KelompokIkon) {
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
	
	/**
	 * Mempersiapkan animasi panah lanjut untuk mengindikasikan perpindahan data.
	 * Metode ini hanya dapat diaplikasikan pada JFXHamburger.
	 * @see <a href="https://javadoc.io/doc/com.jfoenix/jfoenix/latest/com/jfoenix/controls/JFXHamburger.html">JFXHamburger</a>
	 */
	private void animasiDataSelanjutnya() {	
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
	
	/**
	 * Mempersiapkan animasi panah kembali untuk mengindikasikan perpindahan data.
	 * Metode ini hanya dapat diaplikasikan pada JFXHamburger.
	 * @see <a href="https://javadoc.io/doc/com.jfoenix/jfoenix/latest/com/jfoenix/controls/JFXHamburger.html">JFXHamburger</a>
	 */
	private void animasiDataSebelumnya() {
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
	 * Menghapus tulisan di semua kotak tulisan.
	 * @param klik
	 */
	public void hapusSemua(MouseEvent klik) {
		Proses.hitungWaktu(true, this.getClass());//TODO: hapus ini
		
		Tema.setText("");
		Koding.setText("");
		IdeUtama.setText("");
		Jawaban.setText("");
		Pertanyaan.setText("");
		Impresi.setText("");
		
		Proses.hitungWaktu(false, this.getClass());//TODO: hapus ini
	}
	
	/**
	 * Menghapus tulisan di kotak tulisan tema.
	 * @param klik
	 */
	public void hapusTema(MouseEvent klik) {
		Tema.setText("");
	}
	
	/**
	 * Menghapus tulisan di kotak tulisan koding.
	 * @param klik
	 */
	public void hapusKoding(MouseEvent klik) {
		Koding.setText("");
	}
	
	/**
	 * Menghapus tulisan di kotak tulisan ide utama.
	 * @param klik
	 */
	public void hapusIdeUtama(MouseEvent klik) {
		IdeUtama.setText("");
	}
	
	/**
	 * Menghapus tulisan di kotak tulisan jawaban.
	 * @param klik
	 */
	public void hapusJawaban(MouseEvent klik) {
		Jawaban.setText("");
	}
	
	/**
	 * Menghapus tulisan di kotak tulisan pertanyaan.
	 * @param klik
	 */
	public void hapusPertanyaan(MouseEvent klik) {
		Pertanyaan.setText("");
	}
	
	/**
	 * Menghapus tulisan di kotak tulisan impresi.
	 * @param klik
	 */
	public void hapusImpresi(MouseEvent klik) {
		Impresi.setText("");
	}
	
	/**
	 * Memulai input di data selanjutnya dengan menambah angka di urutan data.
	 * @param klik
	 */
	public void dataSelanjutnya(MouseEvent klik) {
		int urutandata = Integer.valueOf(UrutanData.getText());
		urutandata += 1;
		UrutanData.setText(String.valueOf(urutandata));
	}
	
	/**
	 * Memulai input di data sebelumnya dengan mengurangi angka di urutan data.
	 * @param klik
	 */
	public void dataSebelumnya(MouseEvent klik) {
		int urutandata = Integer.valueOf(UrutanData.getText());
		if(urutandata <= 1) urutandata = 1; else urutandata -= 1;
		UrutanData.setText(String.valueOf(urutandata));
	}
}
