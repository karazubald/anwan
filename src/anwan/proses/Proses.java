package anwan.proses;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <h2>Kelas Proses</h2>
 * 
 * Kelas ini digunakan untuk menyediakan proses dan pemeriksaan proses yang berjalan.
 * Kelas ini diperlukan untuk Testing dan Debugging.
 * 
 * @author karazubald
 *
 */
public class Proses {
	private static Instant waktuMulai, waktuSelesai;
	public static final long DURASI_DEFAULT = 5 * 60000;
	
	/**
	 * Menyimpan otomatis data dalam waktu yang ditentukan di durasiMenit. 
	 * Apabila nilai durasiMenit kurang dari 5, maka secara otomatis ditetapkan menjadi DURASI_DEFAULT (5 menit).
	 * @param statusQuery status dari proses penyimpanan data ke dalam DataBase yang bernilai true jika berhasil dan false jika terjadi galat.
	 * @see data.DataBase#rekamData(int NomorData, String Tema, String Koding, String IdeUtama, String Jawaban, String Pertanyaan, String Impresi)
	 */
	public static void simpanOtomatis(boolean statusQuery) {
		Timer waktu = new Timer();
		waktu.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO: kegiatan otomatis disini
			}
		}, 0, DURASI_DEFAULT);
	}
	
	/**
	 * Metode untuk menghitung durasi suatu metode/proses dalam kelas. Atur nilai penanda menjadi true 
	 * untuk memulai penghitungan waktu. Atur nilai penanda menjadi false untuk mengakhiri perhitungan waktu. 
	 * Setelah penanda diatur menjadi false, durasi suatu metode/proses di dalam kelas objek akan ditampilkan di konsol.
	 * Kelas objek akan ditampilkan sebagai String dengan memanggil metode toString() sebelum ditampilkan di konsol.
	 * 
	 * @param penanda boolean dengan nilai true atau false
	 * @param objek Diisi dengan this.getClass()
	 * 
	 * @see Object#getClass()
	 * @see Object#toString()
	 */
	public static void hitungWaktu(boolean penanda, Class objek) {
		String hasil = "Eksekusi proses di " +objek.toString()+ " berjalan selama ";
		
		if(penanda == true) {
			waktuMulai = Instant.now();
		}
		
		if(penanda == false) {
			
			
			waktuSelesai = Instant.now();
			long durasiNanodetik = Duration.between(waktuMulai, waktuSelesai).toNanos();
			long durasi = 0;
			
			if(durasiNanodetik <= 0) {
				durasi = durasiNanodetik * 1000;
				hasil += durasi + " picodetik";
				System.out.println(hasil);
				return;
			}
			
			if(durasiNanodetik > 0 && durasiNanodetik/1000l <= 0) {
				durasi = durasiNanodetik;
				hasil += durasi + " nanodetik";
				System.out.println(hasil);
				return;
			}
			
			if(durasiNanodetik/1000l > 0 && durasiNanodetik/1000000l <= 0) {
				durasi = durasiNanodetik/1000l;
				hasil += durasi + " mikrodetik";
				System.out.println(hasil);
				return;
			}
			
			if(durasiNanodetik/1000000l > 0 && durasiNanodetik/1000000000l <= 0) {
				durasi = durasiNanodetik/1000000l;
				hasil += durasi + " milidetik";
				System.out.println(hasil);
				return;
			}
			
			if(durasiNanodetik/1000000000l > 0 && durasiNanodetik/60000000000l <= 0) {
				durasi = durasiNanodetik/1000000000l;
				hasil += durasi + " detik";
				System.out.println(hasil);
				return;
			}
		}
	}
	
	/**
	 * Menampilkan pesan di console.
	 * @param isi isi pesan yang akan ditampilkan.
	 */
	public static void pesan(String isi) {
		System.out.println(isi);
	}
	
	
	
}
