/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Keons
 * @see https://stackoverflow.com/questions/54035976/javafx-with-fxml-adding-action-events-for-buttons
 * @version 11M15A
 */


public class Controller implements Initializable  {

    Lista listaFiles = new Lista();
    private int filesNames = 0 ;
    double x = 20;
    double y = 10;
    @FXML
    Button addFile;
    @FXML
    Button parse;
    @FXML
    Button deleteFile;
    @FXML
    Pane filesPane;

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {

        /**
         *  
         * @param event
         * @see https://docs.oracle.com/cd/E17802_01/javafx/javafx/1.3/docs/api/javafx.scene/doc-files/cssref.html#typeeffect
         */
        addFile.setOnAction((ActionEvent event) -> {
            event.consume();
            listaFiles.insertAtLast("Archivo1");
            listaFiles.insertAtLast("Archivo2");
            listaFiles.insertAtLast("Archivo3");
            listaFiles.insertAtLast("Archivo4");
            Nodo current = listaFiles.getHead();
            
            
            
            while(current != null){
                Label labelFile = new Label((String)current.getData());
                labelFile.setLayoutX(x);
                labelFile.setLayoutY(y);
                labelFile.setPrefSize(100, 30);
                
                labelFile.setOnMouseEntered((MouseEvent eventLabel) -> {
                    labelFile.setStyle("-fx-background-color: rgba(13, 13, 13, 0.5); -fx-text-fill: rgba(93, 183, 252, 0.5); -fx-font-weight: bold; " );
                });
                labelFile.setOnMouseExited((MouseEvent eventLabel) -> {
                    labelFile.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
                });
                filesPane.getChildren().add(labelFile);
                current = current.getNext();
                y += 30;
                
                
            }
        });
     
        
        
    }
}
