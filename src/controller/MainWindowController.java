package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Person;

public class MainWindowController {
	
	private Person p = new Person();
	Scanner in=null;
	private Main main;
	private Stage primaryStage;
	
	private String saveURL;
	private String loadURL;
	
	@FXML private Path pathRoom1;
	@FXML private Path pathRoom2;
	@FXML private Path pathRoom3;
	@FXML private Path pathRoom4;
	@FXML private Path pathRoom5;
	
	@FXML private Button buttonLoad;
	@FXML private Button buttonSave;
	@FXML private Button buttonAdd;
	@FXML private Button buttonRaport;
	
	@FXML private TextField textFieldFirstName; 
	@FXML private TextField textFieldLastName;
	@FXML private TextField textFieldRoom;
	@FXML private TextField textFieldStartHour;
	@FXML private TextField textFieldFinishHour;
	
	//tableview fields
	
	@FXML private TableView<Person> tableView;
	@FXML private TableColumn<Person, String> firstNameColumn;
	@FXML private TableColumn<Person, String> lastNameColumn;
	@FXML private TableColumn<Person, String> roomColumn;


	
	//dodanie listy przechowujcej dane z tabeli

//	public String toString() {
//    	    return p.firstName + p.getLastName() + p.getRoom();
//    	}
     
     
	private ObservableList<Person> personList = 
			FXCollections.observableArrayList();
	
	
	public void initialize(){
		firstNameColumn.setCellValueFactory(
				new PropertyValueFactory<Person,String> ("firstName")
				);
		lastNameColumn.setCellValueFactory(
				new PropertyValueFactory<Person,String> ("lastName")
				);
		roomColumn.setCellValueFactory(
				new PropertyValueFactory<Person,String>("room")
				);
		tableView.getSelectionModel().
					selectedItemProperty().addListener(
							(ov,oldVal,newVal)->
							System.out.println(
									newVal.getFirstName()+ " " +
									newVal.getLastName())
									);
		
		
		tableView.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
		    public void handle(MouseEvent event) {
		    	
		    	pathRoom1.setFill(Color.DODGERBLUE);
		    	pathRoom2.setFill(Color.DODGERBLUE);
		    	pathRoom3.setFill(Color.DODGERBLUE);
		    	pathRoom4.setFill(Color.DODGERBLUE);
		    	pathRoom5.setFill(Color.DODGERBLUE);
		    	
		    	if(event.MOUSE_PRESSED != null) {
		    	
		    		if(!tableView.getSelectionModel().isEmpty()){
		    		if (tableView.getSelectionModel().getSelectedItem().getRoom().matches("1"))
		    			pathRoom1.setFill(Color.YELLOW);
		    		if (tableView.getSelectionModel().getSelectedItem().getRoom().matches("2"))
			    		pathRoom2.setFill(Color.YELLOW);
		    		if (tableView.getSelectionModel().getSelectedItem().getRoom().matches("3"))
			    		pathRoom3.setFill(Color.YELLOW);
		    		if (tableView.getSelectionModel().getSelectedItem().getRoom().matches("4"))
			    		pathRoom4.setFill(Color.YELLOW);
		    		if (tableView.getSelectionModel().getSelectedItem().getRoom().matches("5"))
			    		pathRoom5.setFill(Color.YELLOW);
		         }   }
		    	
		    }
		});
	

	}
	

	
	public void setTable(){
		 try {
				in=new Scanner(Paths.get(loadURL));
				while (in.hasNext())
				{

					p.firstName=in.next();
					p.lastName=in.next();
					p.room=in.next();
					p.startHour=in.next();
					p.finishHour=in.next();

					personList.add(new Person
							(p.firstName,p.lastName,p.room,p.startHour,p.finishHour));

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally
		    {
		    	if (in!=null)
		    		in.close();
		    }
	}
	
	public void saveFile(){
	 PrintWriter out=null;
	    
	    try {
			out=new PrintWriter(loadURL);
			
			for (int i=0; i<personList.size(); i++)
			{

				out.printf(
						"%s\n",
						personList.get(i).toString()
				);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally {
	    	if (out!=null) 
	    		out.close();
	    }
	
	}
	
	/////////////save raport
	
	public void saveRaport(){
		 PrintWriter out=null;
		    
		    try {
				out=new PrintWriter(saveURL);
				
				for (int i=0; i<personList.size(); i++)
				{

					out.printf(
							 "%s\n",
							personList.get(i).toString()
					   );
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    finally {
		    	if (out!=null) 
		    		out.close();
		    }
		
		}
	
	
	
	


	//dodanie metody obs�ugi przycisku w klasie kontrollera
	@FXML 
	public void handleButtonLoad(){
		
		//pathRoom1.setFill(Color.RED);
		loadFile();
		setTable();
		
		System.out.println("button wczytaj  pressed");
	}
	
	
	
	
	
	@FXML 
	public void handleButtonSave(){
		System.out.println("save button pressed");
		saveFile();
	}
	
	@FXML 
	public void handleButtonAdd(){
		System.out.println("button add pressed");

		if( textFieldFirstName.getText().isEmpty() || textFieldLastName.getText().isEmpty() || textFieldRoom.getText().isEmpty()){
			System.out.println("kupa");
		}
		else {
			personList.add(new Person
					(textFieldFirstName.getText(),
							textFieldLastName.getText(),
							textFieldRoom.getText(),
							textFieldStartHour.getText(),
							textFieldFinishHour.getText()
					));


			textFieldFirstName.clear();
			textFieldLastName.clear();
			textFieldRoom.clear();
			textFieldStartHour.clear();
			textFieldFinishHour.clear();
		}
	}
	
	@FXML public void handleButtonRaport(){
		System.out.println("button raport pressed");
		saveFileTo();
		saveRaport();
	}
	
	
	public void setMain(Main main,Stage primaryStage){
		this.main=main;
		
		this.primaryStage=primaryStage;
		
		tableView.setItems(personList);
	}
	
	
	public void saveFileTo(){
		FileChooser fileChooser=new FileChooser();
		fileChooser.setTitle("Zapisz raport do: ");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Pliki tekstowe","*.txt")
				);
		File selectedFile = fileChooser.showSaveDialog(primaryStage); 
		saveURL= selectedFile.getAbsolutePath();
		if (selectedFile!= null)
		{
	System.out.println("zapisano do: "+ selectedFile);
		}
	}
	
	
	
	public void loadFile(){
		personList.clear();
		FileChooser fileChooser=new FileChooser();
		fileChooser.setTitle("otw�rz plik z danymi");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Pliki tekstowe","*.txt")
				);
		File selectedFile = fileChooser.showOpenDialog(primaryStage); 
		loadURL= selectedFile.getAbsolutePath();
		if (selectedFile!= null)
		{
	System.out.println("wczytano z: "+ selectedFile);
		}
	}
    public void handleButtonDelete(){
		deleteButton();
	}
	public void deleteButton(){

		for (Person person :personList) {
			if (person.getRoom() == tableView.getSelectionModel().getSelectedItem().getRoom()
				&& person.getFirstName() == tableView.getSelectionModel().getSelectedItem().getFirstName()
				&& person.getLastName()  == tableView.getSelectionModel().getSelectedItem().getLastName()) {
				personList.remove(person);
				tableView.getSelectionModel().clearSelection();
			}
			if(person.getRoom().isEmpty()
					&& person.getFirstName().isEmpty()
					&& person.getLastName().isEmpty())
			{
				personList.remove(person);
			}
		}

//		tableView.getSelectionModel().getSelectedItem().getRoom();
//		tableView.getSelectionModel().getSelectedItem().getFirstName();
//		tableView.getSelectionModel().getSelectedItem().getLastName();
	}
	public void markOnList(){


	//	tableView.focusModelProperty(personList.get(1));
	//	tableView.
	}
	
	@FXML void closeStage()
	{
	primaryStage.close();
	}
}
