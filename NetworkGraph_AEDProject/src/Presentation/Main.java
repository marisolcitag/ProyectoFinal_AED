package Presentation;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Model.NetworkDevice;
import Model.Principal;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
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
		Button loadFile = new Button("Cargar Archivo");
		Button ReleaseBroadCast = new Button("Encontrar redes");
		Button changeToMatrix = new Button("Cambiar a Matriz");
		Button changeToList = new Button("Cambiar a Lista");
		graph = new Graph();
		toolbar.getItems().add(loadFile);
		toolbar.getItems().add(changeToList);
		toolbar.getItems().add(changeToMatrix);
		toolbar.getItems().add(ReleaseBroadCast);
		root.setBottom(toolbar);
		
        

        root.setCenter(graph.getScrollPane());

        Scene scene = new Scene(root, 1024, 768);
        
    
        primaryStage.setScene(scene);
        primaryStage.show();

        addGraphComponents();

        Layout layout = new RandomLayout(graph);
        layout.execute();
		
		ReleaseBroadCast.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					int length = principal.getNetworkList().getCurrentSize();
					List<String> choices = new ArrayList<>();
					for (int i = 0; i < length; i++) {
						choices.add(i+"");
					}
					
					ChoiceDialog<String> dialog = new ChoiceDialog<>("0", choices);
					dialog.setTitle("BFS");
					dialog.setHeaderText("Selecci�n de nodo");
					dialog.setContentText("Elige el ID del nodo");
					Optional<String> result = dialog.showAndWait();
					boolean value = principal.isMatrix();
					if(result.isPresent()) {
						int choose = Integer.parseInt(result.get());
						if(value==true) {
							principal.BFSAdjacencyMatrix((NetworkDevice)principal.getNetworkMatrix().getNode(choose));
						}else {
							
						}
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		loadFile.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					principal = new Principal();
					File selected = filechooser.showOpenDialog(primaryStage);
					principal.readFile(selected);
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
		
		
		
	}
	
	private void addGraphComponents() {

        Model model = graph.getModel();

        graph.beginUpdate();
        
        
        ArrayList<Integer> edges = principal.transformData();
        if(edges.size()>0) {
        	int length = edges.get(0);
            
            for (int i = 0; i < length; i++) {
    			model.addCell(i+"", CellType.RECTANGLE);
    		}
            
            for (int i = 1; i < edges.size()-1; i++) {
    			model.addEdge(edges.get(i)+"", edges.get(i+1)+"");
    		}
        }
        
        
		/*
		 * model.addCell("Cell A", CellType.RECTANGLE); model.addCell("Cell B",
		 * CellType.RECTANGLE); model.addCell("Cell C", CellType.RECTANGLE);
		 * model.addCell("Cell D", CellType.TRIANGLE); model.addCell("Cell E",
		 * CellType.TRIANGLE); model.addCell("Cell F", CellType.RECTANGLE);
		 * model.addCell("Cell G", CellType.RECTANGLE);
		 * 
		 * model.addEdge("Cell A", "Cell B"); model.addEdge("Cell A", "Cell C");
		 * model.addEdge("Cell B", "Cell C"); model.addEdge("Cell C", "Cell D");
		 * model.addEdge("Cell B", "Cell E"); model.addEdge("Cell D", "Cell F");
		 * model.addEdge("Cell D", "Cell G");
		 */

        graph.endUpdate();

    }
	
	

}
