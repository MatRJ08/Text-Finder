/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

/**
 *
 * @author Keons
 * @see https://stackoverflow.com/questions/54035976/javafx-with-fxml-adding-action-events-for-buttons
 * @version 11M15C
 */


public class Controller implements Initializable  {

    Lista listaFiles = new Lista();
    private int filesNames = 0 ;
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
         * @see http://lineadecodigo.com/java/como-ejecutar-un-comando-del-sistema-desde-java/
         */
        
        addFile.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(TextFinder.getStage());
            String data = selectedFile.toString();
            System.out.println(data);
            String separator = "\\";
            String dataAux[] = data.replaceAll(Pattern.quote(separator), "\\\\").split("\\\\");
            System.out.println(Arrays.toString(dataAux));
                        
            data = dataAux[dataAux.length -1];
            listaFiles.insertAtLast(data);
            try {
                copyFileUsingStream(selectedFile, data);
            } catch (IOException ex) {
                System.err.print(ex);
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            addElements();
//            try {
//                String [] cmd = {"copy " + selectedFile.toString() + "  ..\\library "}; //Comando de apagado en windows
//                Runtime.getRuntime().exec(cmd);
//            } catch (IOException ioe) {
//                System.out.println (ioe);
//            }
        });
     
        
        
    }
    
    private static void copyFileUsingStream(File source, String stringDest) throws IOException {
       
    InputStream is = null;
    OutputStream os = null;
    try {        
        File dest = new File("src\\library\\"+stringDest);
        dest.createNewFile();
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
}
    
    private void addElements(){
        
        Nodo current = listaFiles.getHead();          
        double y = 10;
        filesPane.getChildren().clear();
        
        while(current != null){
            
            Label labelFile = new Label("  "+(String)current.getData());
            Tooltip tooltip = new Tooltip((String)current.getData());
            
            labelFile.setTooltip(tooltip);
            labelFile.setPrefSize(170, 30);
            labelFile.setLayoutX((200-labelFile.getPrefWidth())/2);
            labelFile.setLayoutY(y);
            labelFile.setTextAlignment(TextAlignment.CENTER);

            labelFile.setOnMouseEntered((MouseEvent eventLabel) -> {
                labelFile.setStyle("-fx-background-color: rgba(13, 13, 13, 0.5); -fx-text-fill: rgba(93, 183, 252, 0.5); -fx-text-alignment: center;" );
            });
            
            labelFile.setOnMouseExited((MouseEvent eventLabel) -> {
                labelFile.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);");
            });
            
            filesPane.getChildren().add(labelFile);
            current = current.getNext();
            y += 30;
            
        }

    }
}
