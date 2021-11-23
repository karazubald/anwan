package anwan.proses;

/**
 * <h2> Kelas untuk Membilang Angka </h2>
 * 
 * Kode ini merupakan salinan dari kode yang dibuat oleh Agung Setiawan.
 * Hanya sedikit modifikasi yang dilakukan pada kode ini.
 * @author Agung Setiawan
 * @see <a href="https://agung-setiawan.com/program-terbilang-java">Program Terbilang</a>
 */
public class Terbilang {
	private static String[] nomina = {
			"nol","satu","dua","tiga","empat","lima","enam","tujuh","delapan","sembilan","sepuluh", "sebelas"
	};
	
	public static String sebut(int angka) {
		if(angka < 12) return nomina[angka];
		if(angka >= 12 && angka <= 19) return nomina[angka % 10]+" belas";
		if(angka >= 20 && angka <= 99) return nomina[(int) angka/10] + " puluh " + nomina[angka % 10];
		if(angka >= 100 && angka < 199) return "seratus " + sebut(angka % 100);
		if(angka >= 200 && angka <= 999) return nomina[(int) angka/100] + " ratus " + sebut(angka % 100);
		if(angka >= 1000 && angka <= 1999) return "seribu " + sebut(angka % 1000);
		if(angka >= 2000 && angka <= 9999) return sebut((int) angka / 1000) + " ribu " + sebut(angka % 1000);
		if(angka >= 1000000 && angka <= 999099) return sebut((int) angka / 1000000) + " juta " + sebut(angka % 1000000);
		return "error";
	}
}
