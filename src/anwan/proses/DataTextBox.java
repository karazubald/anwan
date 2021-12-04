/**
 * 
 */
package anwan.proses;

/**
 * <h2> Data Text Box </h2>
 * Pengantar data dari TextBox Layar_2 ke TextBox Layar_3.
 * Kelas ini bersifat <i>static</i>, tidak perlu diinisialisasikan.
 * @author karazubald
 *
 */
public class DataTextBox {
	private static volatile String id;
	private static volatile String data;
	
	public static void setId(String idTextBox) {
		id = idTextBox;
	}
	
	public static void setData(String tulisan) {
		data = tulisan;
	}
	
	public static String getId() {
		return id;
	}
	
	public static String getData() {
		return data;
	}
	
	public static void kosongkanData() {
		id = null;
		data = null;
	}
}
