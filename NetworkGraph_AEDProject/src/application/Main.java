package application;


import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Principal;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;


public class Main extends Application {

	private Graph graph;
	private static Principal principal;
	
	
	public static void main(String[] args) {
		principal = new Principal();
		launch(args);
	}
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane root = new BorderPane();
		ToolBar toolbar = new ToolBar();
		FileChooser filechooser = new FileChooser();
		Button reset = new Button("Reiniciar");
		Button loadFile = new Button("Cargar Archivo");
		Button changeToMatrix = new Button("Cambiar a Matriz");
		Button changeToList = new Button("Cambiar a Lista");
		graph = new Graph();
		loadFile.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					File selected = filechooser.showOpenDialog(primaryStage);
					principal.readFile(selected);
					Main.this.addGraphComponents();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		changeToMatrix.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					boolean value = principal.isMatrix();
					if(value==false) {
						principal.changeValueMatrix();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		changeToList.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					boolean value = principal.isMatrix();
					if(value==true) {
						principal.changeValueMatrix();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		reset.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					primaryStage.close();
					Main.this.start(primaryStage);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		TextField textfield = new TextField();
		toolbar.getItems().add(reset);
		toolbar.getItems().add(textfield);
		toolbar.getItems().add(loadFile);
		root.setBottom(toolbar);
		
        

        root.setCenter(graph.getScrollPane());

        Scene scene = new Scene(root, 1024, 768);
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
    
        primaryStage.setScene(scene);
        primaryStage.show();

        addGraphComponents();

        Layout layout = new RandomLayout(graph);
        layout.execute();
		
	}
	
	private void addGraphComponents() {

        Model model = graph.getModel();

        graph.beginUpdate();
        
//        model.addCell("Cell A", CellType.RECTANGLE);
//        model.addCell("Cell B", CellType.RECTANGLE);
//        model.addCell("Cell C", CellType.RECTANGLE);
//        model.addCell("Cell D", CellType.TRIANGLE);
//        model.addCell("Cell E", CellType.TRIANGLE);
//        model.addCell("Cell F", CellType.RECTANGLE);
//        model.addCell("Cell G", CellType.RECTANGLE);
//
//        model.addEdge("Cell A", "Cell B");
//        model.addEdge("Cell A", "Cell C");
//        model.addEdge("Cell B", "Cell C");
//        model.addEdge("Cell C", "Cell D");
//        model.addEdge("Cell B", "Cell E");
//        model.addEdge("Cell D", "Cell F");
//        model.addEdge("Cell D", "Cell G");

        graph.endUpdate();

    }
	
	

}
