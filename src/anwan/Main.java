package anwan;
	
import anwan.proses.DirektoriInisiator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage aplikasi) {
		String fxml = DirektoriInisiator.getFXMLDir("Layar_0");
		String css = DirektoriInisiator.getCSSDir("Layar_0");
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource(fxml));
			Scene tampilan = new Scene(parent);
			
			tampilan.getStylesheets().add(getClass().getResource(css).toExternalForm());

			aplikasi.setScene(tampilan);
			aplikasi.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
