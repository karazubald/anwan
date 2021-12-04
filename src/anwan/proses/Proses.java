package anwan.proses;

import java.util.Timer;
import java.util.TimerTask;

public class Proses {
	public static long durasiOtomatis = 300000;
	
	public static void mulai(int durasiMenit, TimerTask kegiatan) {
		long durasi;
		if (durasiMenit <= durasiOtomatis) {
			durasi = durasiOtomatis;
		} else {
			durasi = durasiMenit * 60000;
		}
		
		Timer waktu = new Timer();
		waktu.schedule(kegiatan, durasi);
	}
}
