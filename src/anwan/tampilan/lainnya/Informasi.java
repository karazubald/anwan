package anwan.tampilan.lainnya;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

/**
 * Menampilkan kotak dialog yang berisi informasi tertentu terkait aplikasi.
 * @author karazubald
 *
 */
public class Informasi {
	private static String info = "";
	private static final String PEMBUAT = "KARA ZUBALD";
	private static final String VERSI = "0.0.1";
	private static final String NAMA_VERSI = "Alef";
	private static final String FITUR = "Paragrafisasi otomatis hasil analisis wawancara dalam bentuk berkas txt.";
	private static final String DESKRIPSI = "Alat bantu dalam analisis transkrip wawancara untuk mata kuliah Psikodiagnostik: Wawancara.";
	
	/**
	 * Menampilkan informasi aplikasi ke pengguna dalam bentuk kotak dialog.
	 */
	public static void Tampilkan() {
		info = PEMBUAT + "\n";
		info += VERSI + " (" + NAMA_VERSI + ") " + "\n";
		info += DESKRIPSI + "\n\n";
		info += "FITUR" + "\n" + FITUR;
		
		Dialog<String> kotakDialogInfo = new Dialog<>();
		ButtonType tombol_ok = new ButtonType("OK", ButtonData.OK_DONE);
		
		kotakDialogInfo.setTitle("INFORMASI");
		kotakDialogInfo.setContentText(info.replaceAll("\n", System.lineSeparator()));
		kotakDialogInfo.getDialogPane().getButtonTypes().add(tombol_ok);
		kotakDialogInfo.showAndWait();
	}
}
