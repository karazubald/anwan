module anwan {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires com.jfoenix;
	requires javafx.base;
	requires org.xerial.sqlitejdbc;
	requires java.sql;
	
	exports anwan.data;
	exports anwan.proses;
	exports anwan.tampilan;
	exports anwan.tampilan.lainnya;
	
	opens anwan to javafx.graphics, javafx.fxml;
	opens anwan.tampilan to javafx.fxml;
}
