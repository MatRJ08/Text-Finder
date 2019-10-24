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
import java.util.Scanner;
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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
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
    Button searchWord;
    @FXML
    Button searchPhrase;
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
        
        searchWord.setOnAction((ActionEvent event) -> {
            searchWordInFile();
        });        
        
        searchPhrase.setOnAction((ActionEvent event) -> {
            searchPhraseInFile();
        });
        
        
        sort.setOnAction((ActionEvent event) -> {
            sortBy();
        });
        
        
        deleteFile.setOnAction((ActionEvent event) -> {
            removeFile();
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
            
            addLibraryElements();
        
       
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
    
    
    
    
    private void searchPhraseInFile(){
        System.out.println("Buscando "+textToSearch.getText());
        NodoLista current = listaParsedFiles.getHead();
        while (current != null){
            Lista finded =(Lista) searchPhraseInFileAux(current, textToSearch.getText().split(" "), 0, 0, new Lista());
            if( finded != null){
                NodoLista findedPositions = finded.getHead();
                Lista wordPositions = (Lista)findedPositions.getData();
                NodoLista wordPosition = wordPositions.getHead();
                while(wordPosition != null){
                    int wordPositionIndex = (int)wordPosition.getData();
                    NodoLista next = findedPositions.getNext();
                    for(int i=1;next != null;i++){
                        if(!listContainsPos((Lista)next.getData(), wordPositionIndex+i)){
                           break;
                        }else if(next.getNext() == null){

                            System.out.println("Frase "+textToSearch.getText()+" encontrada en "+current.getName());
                            addFindedElements((Label)current.getData());
                        }
                        next = next.getNext();
                    }
                    wordPosition = wordPosition.getNext();
                }
            }else{
                System.out.println("Frase no encontrada en "+current.getName());
            }
            current = current.getNext();
        }
    } 
    
    
    
    
    
    private void addFindedElements(Label label){
        
    }
            
      
    
    
    
    private void addLibraryElements(){
        
        NodoLista current = listaFiles.getHead();          
        double y = 10;
        filesPane.getChildren().clear();
        
        while(current != null){
            
            Label labelFile = new Label((String)current.getData());
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
    
    
    
    
    
    
    private Object searchPhraseInFileAux(NodoLista current, String[] words, int wordIndex, int nextIndex, Lista wordsPosition){
        
        Arbol arbol = (Arbol)current.getData();
        Word finded = arbol.ifNodoExists(arbol.getRoot(), words[wordIndex].toLowerCase());

        if( finded != null){
            
            wordsPosition.insertAtLast(finded.getIndex());
            
            if(wordIndex == words.length-1)
                return wordsPosition;
            
            else 
                return searchPhraseInFileAux(current, words, wordIndex+1, nextIndex, wordsPosition);
//      
        }else
            return null;
       
    }
    
        
    public boolean listContainsPos(Lista list, int pos){
        NodoLista current =  list.getHead();
        while (current != null){
            int posCurrent = (int)current.getData();
            if(posCurrent == pos){
                return true;
            }else
                current = current.getNext();
        }
        return false;
    }        
    
    
   private void removeFile(){       
        listaFiles.delete(lastSelected.replace("  ",""));
        addLibraryElements();
           
    
   }
    
    private void parseFile(){
        
        lastSelected = lastSelected.replace("  ", "");
        Parser parser = new Parser();
        Arbol arbol = null;
        
        if(lastSelected.contains("txt"))             
            arbol = parser.txtParser(lastSelected);
            
        
        else if(lastSelected.contains("docx"))
            arbol = parser.docxParser(lastSelected);
                
        
        else if(lastSelected.contains("pdf"))            
            arbol = parser.pdfParser(lastSelected);            
			
        
        listaParsedFiles.insertAtLast(arbol,lastSelected);
        inOrder(arbol.getRoot());
        
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
