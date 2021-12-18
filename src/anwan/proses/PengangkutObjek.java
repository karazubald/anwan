package anwan.proses;
/**
 * <h2> Pengangkut Objek </h2>
 * 
 * Kelas ini digunakan untuk membawa suatu objek dari satu layar ke layar lainnya.
 * Objek yang dibawa oleh kelas ini hanya 1 saja.
 * 
 * @author karazubald
 */
public class PengangkutObjek {
	private static volatile String idObjek;
	private static volatile String isiObjek;
	
	/**
	 * Mendapatkan identifier dari objek yang dibawa oleh kelas ini.
	 * @return identitas objek dalam bentuk String
	 */
	public static String getIdObjek() {
		return idObjek;
	}
	/**
	 * Menetapkan idObjek yang akan dibawa oleh kelas ini dalam bentuk String.
	 * @param idObjek identitas suatu objek
	 */
	public static void setIdObjek(String idObjek) {
		PengangkutObjek.idObjek = idObjek;
	}
	/**
	 * Mendapatkan isi dari objek yang dibawa oleh kelas ini.
	 * @return isi objek dalam bentuk String
	 */
	public static String getIsiObjek() {
		return isiObjek;
	}
	/**
	 * Menetapkan isiObjek yang akan dibawa oleh kelas ini dalam bentuk String.
	 * @param isiObjek isi dari objek yang akan dibawa
	 */
	public static void setIsiObjek(String isiObjek) {
		PengangkutObjek.isiObjek = isiObjek;
	}
	
}
