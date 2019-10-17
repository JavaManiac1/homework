package controller;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import static data.DAOaccess.databaseAccess;


public class Main extends Application {
	private Stage primaryStage;	// dodajemy sk�adow� do klasy Main
	@Override
	public void start(Stage primaryStage) { //modyfikujemy metod� start
		this.primaryStage=primaryStage;
		mainWindow();
	}
	
	//dodajemy metod� mainWindow odpowiedzialn� za tworzenie nowego okna
	public void mainWindow(){
		//ładowanie obiektów z pliku fxml znajdującego się w pakiecie view
		//opisującego strukturę okna
		try{
			FXMLLoader loader = 
				new FXMLLoader(
						Main.class.getResource("/view/MainWindowView.fxml"));
			
			AnchorPane pane = loader.load(); //inicjacja AnchorPane
			
			primaryStage.setMinWidth(793);
			primaryStage.setMinHeight(630);
			primaryStage.setMaxWidth(793);
			Scene scene = new Scene(pane);
			
			//tworzymy instancję kontrolera w funkcji mainWindow
			//ustawiamy scenę i wyświetlamy okno
			MainWindowController mainWindowController = 
				loader.getController();
			
			
			mainWindowController.setMain(this,primaryStage); //Dodanie powiązania kontrolera z klasą Main
			primaryStage.setScene(scene);
			primaryStage.show();
			databaseAccess();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		databaseAccess();
	}
}
