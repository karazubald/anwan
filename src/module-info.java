module anwan {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires com.jfoenix;
	requires javafx.base;
	requires java.sql;
	requires org.xerial.sqlitejdbc;
	
	exports anwan.tampilan;
	
	opens anwan to javafx.graphics, javafx.fxml;
	opens anwan.tampilan to javafx.fxml;
}
