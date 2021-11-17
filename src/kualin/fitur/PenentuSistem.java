package kualin.fitur;

import java.io.File;
import java.util.ArrayList;

public class PenentuSistem {
	private static String[] lokasiDirektori = {"res/fxml","res/fonts"};
	private static String separator = File.pathSeparator;

	public static void init() {
		for(String lokasi : lokasiDirektori) {
			lokasi.replaceAll("/", separator);
		}
	}
}
