module anwan {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires com.jfoenix;
	requires javafx.base;
	
	opens anwan to javafx.graphics, javafx.fxml;
}
