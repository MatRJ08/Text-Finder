package text.finder;
import java.io.File;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Parser {

    
    String FieldDelimiter = " ";
	
    /**
     * 
     * @author jimejose
     * Este metodo permite parsear las palabras de un .pdf
     * @param lastSelected 
     * parametro para saber cual es el nombre del archivo
     * @see https://www.youtube.com/watch?v=rLsKLk-hPH8&feature=youtu.be
     * @see https://www.programcreek.com/java-api-examples/?api=org.apache.pdfbox.pdfparser.PDFParser
     * @see Link libreria: https://www.youtube.com/redirect?q=https%3A%2F%2Fdrive.google.com%2Fopen%3Fid%3D1-yT2IctsPGPArevocthYqp7cm16Oozp8&redir_token=jycP91bxGoL4IjzDeebACQQXvQx8MTU3MTg5NzM2NkAxNTcxODEwOTY2&event=video_description&v=rLsKLk-hPH8
     * @return el arbol con el documento parseado
     * 
     */
    public Arbol pdfParser(String lastSelected) {
        lastSelected = lastSelected.replace("  ", "");
        Arbol arbol = new Arbol();
        try{
            File file = new File("src\\library\\"+lastSelected);    
            PDDocument documento = PDDocument.load(file);
            documento.getClass();
            if (!documento.isEncrypted()){
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();
                String str = Tstripper.getText(documento);
                Scanner scanner = null; 
                scanner = new Scanner(str);
                String line="";
                while (scanner.hasNextLine()) {  
                    line = scanner.nextLine();                        
                    String[] fields = line.split(FieldDelimiter, -1);
                    insertLineInTree(fields, arbol, lastSelected);
                    System.out.println("\n"+line);
                } 
                return arbol;
            }
            documento.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return arbol;
        
    }
    
    
    
    
    
    private void insertLineInTree(String[] fields,Arbol arbol,String lastSelected){        
            for(int i = 0; i < fields.length; i++){
                if(!fields[i].equals(" ")){
                    arbol.insert(fields[i],i, lastSelected);
                    System.out.println(fields[i]+" Added");
                }
            }
    }
    
    
    
    
    private void inOrder(NodoArbol root) {
        if(root !=  null) {
            inOrder(root.getIzq());
            //Visit the node by Printing the node data  
            System.out.println(root.getData().getWord()+" "+root.getData().getRepetition());
            inOrder(root.getDer());
        }
    }
}
