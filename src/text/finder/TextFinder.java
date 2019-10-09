/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

import javafx.application.Application;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Keons
 */
public class TextFinder extends Application {
    @Override
    public void start(Stage primaryStage) {      
        try{
        FXMLLoader loadLoadingWindow = new FXMLLoader();
        loadLoadingWindow.setLocation(getClass().getResource("Grafica.fxml"));
        Parent root = loadLoadingWindow.load();
        
        Scene scene = new Scene(root, 800, 450);
        primaryStage.setTitle("Text Finder");
        primaryStage.setScene(scene);
        primaryStage.show();
        }catch(Exception e){
            System.err.println("Error "+e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
