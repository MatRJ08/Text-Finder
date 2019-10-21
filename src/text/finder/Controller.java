/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package text.finder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 *
 * @author Keons
 * @see https://stackoverflow.com/questions/54035976/javafx-with-fxml-adding-action-events-for-buttons
 * @version 11M15C
 */


public class Controller implements Initializable  {

    Lista listaFiles = new Lista();
    Lista listaParsedFiles = new Lista();
    private int filesNames = 0 ;
    private String lastSelected;
    @FXML
    TextField textToSearch;
    @FXML
    Button search;
    @FXML
    Button sort;
    @FXML
    ChoiceBox sortMethods;
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
         */
        addFile.setOnAction((ActionEvent event) -> {
            addFile();
        });
        
        parse.setOnAction((ActionEvent event) -> {
            parseFile();
        });
        
        search.setOnAction((ActionEvent event) -> {
            searchWordInFile();
        });
        
        
        sort.setOnAction((ActionEvent event) -> {
            sortBy();
        });
        
        sortMethods.getItems().addAll("QuickSort","BubbleSort","RadixSort");
        sortMethods.setValue("QuickSort");
        
        
      
     
        
    }
   
    
    
    /**
     * 
     * @see https://docs.oracle.com/cd/E17802_01/javafx/javafx/1.3/docs/api/javafx.scene/doc-files/cssref.html#typeeffect
     * @see http://lineadecodigo.com/java/como-ejecutar-un-comando-del-sistema-desde-java/
     */  
    private void addFile(){
        
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("TXT", "*.txt"),
                    new ExtensionFilter("PDF", "*.pdf"),
                    new ExtensionFilter("DOCX", "*.docx")
            );
            
            File selectedFile = fileChooser.showOpenDialog(TextFinder.getStage());
            String data = selectedFile.toString();
            System.out.println(data);
            String separator = "\\";
            String dataAux[] = data.replaceAll(Pattern.quote(separator), "\\\\").split("\\\\");
            System.out.println(Arrays.toString(dataAux));
                        
            data = dataAux[dataAux.length -1];
            listaFiles.insertAtLast(data);
            
            try {
                
                copyFile(selectedFile, data);
                
            } catch (IOException ex) {
                
                System.err.print(ex);
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
            addElements();
        
       
    }   
    
    
    
    
    
    /**
     * 
     * https://www.tutorialspoint.com/create-a-new-empty-file-in-java
     * https://www.journaldev.com/861/java-copy-file
     * @param source
     * @param stringDest
     * @throws IOException 
     */    
    private static void copyFile(File source, String stringDest) throws IOException {
       
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
    
    
    
    
    private void searchWordInFile(){
        System.out.println("Buscando "+textToSearch.getText());
        NodoLista current = listaParsedFiles.getHead();
        while(current != null){
            Arbol arbol = (Arbol)current.getData();
            Word finded = arbol.ifNodoExists(arbol.getRoot(), textToSearch.getText().toLowerCase());
            if( finded != null){
                System.out.println(finded.getWord() + "Encontrado en " + finded.getFile());
            }
            current = current.getNext();
        }
    }
            
            
            
            
            
    private void addElements(){
        
        NodoLista current = listaFiles.getHead();          
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
            
            labelFile.setOnMouseClicked((MouseEvent event) -> {
                lastSelected = labelFile.getText();
                System.out.println("Did you click me?");
            });
            
            filesPane.getChildren().add(labelFile);
            current = current.getNext();
            y += 30;
            
        }

    }
    
    
    
    
    
    private void parseFile(){
        String FieldDelimiter = " ";
 
        
        if(lastSelected.contains("txt")){        
            BufferedReader br;
            try {

                br = new BufferedReader(new FileReader("src\\library\\"+lastSelected.replace("  ", "")));
                String line;     
                
                Arbol arbol = new Arbol();
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(FieldDelimiter, -1);
                    insertLineInTree(fields,arbol);
                }
                listaParsedFiles.insertAtLast(arbol);
                inOrder(arbol.getRoot());

                

            } catch (FileNotFoundException ex) {
                Logger.getLogger(TextFinder.class.getName())
                        .log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(TextFinder.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
            
            
            
        }else if(lastSelected.contains("docx")){
            
            try {
                File file = new File("src\\library\\"+lastSelected.replace("  ", ""));
                FileInputStream fis = new FileInputStream(file.getAbsolutePath());

                XWPFDocument document = new XWPFDocument(fis);

                List<XWPFParagraph> paragraphs = document.getParagraphs();

                Arbol arbol = new Arbol();
                for (XWPFParagraph para : paragraphs) {
                    
                    String sPara = para.getText().toString();
                    String[] fields = sPara.split(FieldDelimiter, -1);
                    insertLineInTree(fields,arbol);
//                    System.out.println(para.getText());
                }
                listaParsedFiles.insertAtLast(arbol);
                inOrder(arbol.getRoot());
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    
    
    
    
    private void sortBy(){
        String sortMethod = sortMethods.getValue().toString();
        System.out.println("Metodo " + sortMethod);
    }
    
    
    
    
    
    public void insertLineInTree(String[] fields,Arbol arbol){        
            for(int i = 0; i < fields.length; i++){
                arbol.insert(fields[i],i, lastSelected);
                System.out.println(fields[i]+" Added");
            }
    }
    
    
    
    
    
    public void inOrder(NodoArbol root) {
        if(root !=  null) {
            inOrder(root.getIzq());
            //Visit the node by Printing the node data  
            System.out.println(root.getData().getWord()+" "+root.getData().getRepetition());
            inOrder(root.getDer());
        }
    }


}
