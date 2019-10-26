package text.finder;

import java.awt.Desktop;
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
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import static javafx.scene.input.MouseButton.PRIMARY;
import static javafx.scene.input.MouseButton.SECONDARY;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;




/**
 * Controller de el FXML
 * @author Keons
 * @see <a href = "https://stackoverflow.com/questions/54035976/javafx-with-fxml-adding-action-events-for-buttons"></a>
 * @version 10V26C
 */
public class Controller implements Initializable  {

    private Lista listaFiles = new Lista();
    private Lista listaParsedFiles = new Lista();      
    private Lista findedElements = new Lista();    
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
    private Button addFile;
    @FXML
    Button parse;
    @FXML
    Button deleteFile;
    @FXML
    Pane filesPane;
    @FXML
    Pane principalPane;

    
    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {

        /**
         *  
         * @param event
         */
        addFile.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton() == SECONDARY)
                addDirectory();
            else if(event.getButton() == PRIMARY)
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
   
    
    /***
     * Metodo que busca el directorio que desea agregar el usuario a la libreria
     * @see <a href = "https://docs.oracle.com/javase/8/javafx/api/javafx/stage/DirectoryChooser.html"></a>
     */
    private void addDirectory(){
        DirectoryChooser dc = new DirectoryChooser();
        File path =  dc.showDialog(TextFinder.getStage());
        if(path != null){
            String[] selectedFile = path.list();
            for (String selectedFile1 : selectedFile) {
                if (selectedFile1.contains("docx") || selectedFile1.contains("pdf") || selectedFile1.contains("txt")) {
                    File file = new File(path+"\\" + selectedFile1);
                    System.out.println(selectedFile1);
                    addFiles(path+"\\" + selectedFile1, file);
                }
            }
        }else
            JOptionPane.showMessageDialog(null, "Debe seleccionar una carpeta para poder continuar", "Seleccion cancelada", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    /**
     * Metodo que busca el directorio que desea agregar el usuario a la libreria
     */
    private void addFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("TXT", "*.txt"),
                new ExtensionFilter("PDF", "*.pdf"),
                new ExtensionFilter("DOCX", "*.docx")
        );

        File selectedFile = fileChooser.showOpenDialog(TextFinder.getStage());
        if(selectedFile != null)
            addFiles(selectedFile.toString(), selectedFile);
        else
            JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo para poder continuar", "Seleccion cancelada", JOptionPane.INFORMATION_MESSAGE);
    
    }
    
    /**
     * @param data 
     * Strign de donde se encuentra el archivo
     * @param selectedFile 
     * File de donde se encuentra el archivo
     * @see <a href = "https://docs.oracle.com/cd/E17802_01/javafx/javafx/1.3/docs/api/javafx.scene/doc-files/cssref.html#typeeffect"></a>
     * @see <a href = "https://lineadecodigo.com/java/como-ejecutar-un-comando-del-sistema-desde-java/"></a>
     * @see <a href = "https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html"></a>
     */  
    private void addFiles(String data, File selectedFile){
        
            
            
//            Desktop desktop = Desktop.getDesktop();
//            try {
//            	desktop.open(selectedFile);
//            }catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
            
            if(data.contains("\\")){
                String separator = "\\";
                String dataAux[] = data.replaceAll(Pattern.quote(separator), "\\\\").split("\\\\");
                System.out.println(Arrays.toString(dataAux));                        
                data = dataAux[dataAux.length -1];
            }
            System.out.println(data);
            
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
     * Metodo que copia los archivos seleccionados por el usuario a la carpeta library
     * @see <a href = "https://www.tutorialspoint.com/create-a-new-empty-file-in-java"></a>
     * @see <a href = "https://www.journaldev.com/861/java-copy-file"></a>
     * @param source
     * archivo el cual se desea copiar
     * @param stringDest
     * Path donde se quiere agregar el nuevo archivo
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
    
    
    
    /**
     * Metodo para buscar palabras en un archivo, si encuentra las palabras llama a addFindedElements
     * para hacer display de los archivos donde se encuentra la palabra
     * Si encuentra que se esta buscando sobre una frase divide la frase en palabras y hace una busqueda 
     * individual de cada palabra
     */
    private void searchWordInFile(){
        principalPane.getChildren().clear();
        String[] wordsToSearch = textToSearch.getText().split(" ");
        double x = 45;
        for(int i = 0; i < wordsToSearch.length; i++){
            System.out.println("Buscando "+wordsToSearch[i]);
            NodoLista current = listaParsedFiles.getHead();        
            findedElements = new Lista();        
            boolean displayed = false;
            
            while(current != null){

                Arbol arbol = (Arbol)current.getData();
                Word finded = arbol.ifNodoExists(arbol.getRoot(), wordsToSearch[i].toLowerCase());

                if( finded != null){
                    if(!displayed){
                        Label subTitle = new Label("Palabra "+ wordsToSearch[i]+ " encontarda en: ");
                        subTitle.setMinSize(200, 30);
                        subTitle.setLayoutX(x);
                        subTitle.setLayoutY((principalPane.getPrefHeight()-subTitle.getPrefHeight())*0.50);
                        principalPane.getChildren().add(subTitle); 
                        displayed = true;
                    }

                    if(null == findedElements.buscar(current.getName())){

                        findedElements.insertAtLast(current);
                        System.out.println(finded.getWord() + "Encontrado en " + finded.getFile());

                        addFindedElements(findedElements, x);
                        x+=190;   

                    }

                }
                current = current.getNext();

            }
            x+=35;   
        }
    }
    
    
    
    /**
     * Metodo para buscar frases en un archivo, si encuentra las palabras de la frase, si las palabras estan una al lado 
     * de la otra llama a addFindedElements para hacer display de los archivos donde se encuentra la frase
     */
    private void searchPhraseInFile(){
        principalPane.getChildren().clear();
        System.out.println("Buscando "+textToSearch.getText());
        NodoLista current = listaParsedFiles.getHead();
        double x = 45;
        findedElements = new Lista();
        boolean displayed = false;
        while (current != null){
            Lista finded =(Lista) searchPhraseInFileAux(current, textToSearch.getText().split(" "),0, new Lista());
            if( finded != null){
                if(!displayed){
                    Label subTitle = new Label("Frase "+ textToSearch.getText()+ " encontarda en: ");
                    subTitle.setMinSize(200, 30);
                    subTitle.setLayoutX(x);
                    subTitle.setLayoutY((principalPane.getPrefHeight()-subTitle.getPrefHeight())*0.50);
                    principalPane.getChildren().add(subTitle); 
                    displayed = true;
                }
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
                            if(null == findedElements.buscar(current.getName())){
                                
                                findedElements.insertAtLast(current.getName());
                                addFindedElements(findedElements, x);
                                x+=190;                            
                            }
                            
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
    
    
    
    /**
     * Funcion que busca que todas las palabras de la frase se encuentre en el archivo
     * @param current
     * Nodo donde se almacena el archivo parseado sobre el que se desea buscar
     * @param words
     * Array de las palabras de la frase
     * @param wordIndex
     * Indice actual de words
     * @param wordsPosition
     * Lista en la cual se va almacenar todos los indices donde se encuentra cada palabra buscada
     * @return una lista con los indices donde se encuentra cada palabra buscada
     * @return null si por lo menos una palabra no se encuentra en el archivo
     */
    private Object searchPhraseInFileAux(NodoLista current, String[] words, int wordIndex, Lista wordsPosition){
        
        Arbol arbol = (Arbol)current.getData();
        Word finded = arbol.ifNodoExists(arbol.getRoot(), words[wordIndex].toLowerCase());

        if( finded != null){
            
            wordsPosition.insertAtLast(finded.getIndex());
            
            if(wordIndex == words.length-1)
                return wordsPosition;
            
            else 
                return searchPhraseInFileAux(current, words, wordIndex+1, wordsPosition);
//      
        }else
            return null;
       
    }
    
        
    
    /**
     * Muestra en la grafica los archivos donde hubo coincidencia con la busqueda
     * @param searched
     * La palabra que se busco
     * @param files
     * lista de archivos donde hubo coincidencia con la busqueda
     * @param x 
     * Posicion en el eje x donde debe posicionarse el label que hace referencia al archivo
     */
    private void addFindedElements(Lista files, double x){
        
        NodoLista current = files.getHead();
        while(current!= null){
            String textFile = (String) current.getData();
            Label label = new Label(textFile);
            label.setPrefSize(170, 30);
            label.setLayoutY((principalPane.getPrefHeight()-label.getPrefHeight())*0.75);
            label.setLayoutX(x);

            if(textFile.contains("pdf"))
                label.setStyle("-fx-background-color: rgb(242, 28, 10); -fx-text-fill: white ;");

            else if(textFile.contains("txt"))
                label.setStyle("-fx-background-color: grey; -fx-text-fill: white ;");  

            else if(textFile.contains("docx"))
                label.setStyle("-fx-background-color: rgb(0, 81, 151); -fx-text-fill: white; ");            

            Tooltip tooltip = new Tooltip(label.getText());
            label.setTooltip(tooltip);
            label.setTextAlignment(TextAlignment.CENTER);
            principalPane.getChildren().add(label); 
            current = current.getNext();
        }
        
    }
            
      
    
    
    /**
     * Funcion para mostrar los archivos que se encuentran en la libreria, hace display de un label
     * por cada uno de los archivos agregados a la libreria
     */
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
    
    
    
    
    /**
     * 
     * @param list
     * lista sobre la cual se va buscar
     * @param pos
     * posicion que se esta buscando
     * @return true si la posicion se encuentra en la lista
     * @return false si la posicion no se encuentra en la lista
     */
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
    
    
    
    /**
     * metodo que elimina el ultimo archivo de la libreria clickeado
     */
    private void removeFile(){ 
        
        listaFiles.delete(lastSelected.replace("  ",""));
        listaParsedFiles.delete(lastSelected.replace("  ",""));
        addLibraryElements();
          
    }
    
   
    
    /**
     * metodo que llama a las funciones que parsean los archivos
     * y inserta el arbol en una lista de archivos parseados
     */
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
    
    
    
    
    /**
     * metodo que llama a los metodos que ordenaran los archivos
     */
    private void sortBy(){
        
        String sortMethod = sortMethods.getValue().toString();
        System.out.println("Metodo " + sortMethod);
        
    } 
    
    
    
    
    /**
     * metodo que ejecuta un recorrido inOrder en un arbol
     * @param root 
     * nodo raiz de un arbol
     */
    public void inOrder(NodoArbol root) {
        if(root !=  null) {
            
            inOrder(root.getIzq());
            //Visit the node by Printing the node data  
            System.out.println(root.getData().getWord()+" "+root.getData().getRepetition());
            inOrder(root.getDer());
        
        }
    }

    
    
    
}
