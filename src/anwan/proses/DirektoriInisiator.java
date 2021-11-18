package anwan.proses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.text.Font;

/**
 * @author karazubald
 * DirektoriInisiator berfungsi untuk menyiapkan folder rujukan sesuai sistem operasi. 
 * <p>
 * Folder yang disiapkan yang terdiri dari:
 * (1) Folder CSS
 * (2) Folder Font
 * (3) Folder FXML
 */
public class DirektoriInisiator {
	private static String[] lokasiRes = {"res/css/","res/fonts/","tampilan/"};
	private static String separator = File.pathSeparator;
	private static File pathToFile = null;

	public static void initDir() {
		for(String lokasi : lokasiRes) {
			lokasi.replaceAll("/", separator);
		}
	}
	
	public static String getFontDir(String namaFont) {
		initDir();
		
		pathToFile = new File(lokasiRes[1]+namaFont+".ttf");
		if(!pathToFile.exists()) pathToFile = new File(lokasiRes[1]+namaFont+".otf");
		
		return pathToFile.getPath();
	}
	
	public static String getCSSDir(String namaCSS) {
		initDir();
		
		pathToFile = new File(lokasiRes[0]+namaCSS+".css");
		
		return pathToFile.getPath();
	}
	
	public static String getFXMLDir(String namaFXML) {
		initDir();
		
		pathToFile = new File(lokasiRes[2]+namaFXML+".fxml");
		
		return pathToFile.getPath();
	}
}
