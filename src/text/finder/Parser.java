package text.finder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

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
    
    
    
    
    
    public Arbol docxParser(String lastSelected){
    
        Arbol arbol = new Arbol();
        try {
            
            File file = new File("src\\library\\"+lastSelected);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph para : paragraphs) {

                String sPara = para.getText().toString();
                String[] fields = sPara.split(FieldDelimiter, -1);
                insertLineInTree(fields,arbol, lastSelected);
                
            }
            
            fis.close();
            return arbol;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arbol;
    
    }
    
    
    
    public Arbol txtParser(String lastSelected){
        BufferedReader br;
        Arbol arbol = new Arbol();
            try {

                br = new BufferedReader(new FileReader("src\\library\\"+lastSelected));
                String line;     
                
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(FieldDelimiter, -1);
                    insertLineInTree(fields,arbol, lastSelected);
                }
                return arbol;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TextFinder.class.getName())
                        .log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                Logger.getLogger(TextFinder.class.getName())
                        .log(Level.SEVERE, null, ex);
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
    
}
