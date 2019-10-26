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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 * Clase que contiene los metodos que parsean archivos
 * @author jimejose
 */
public class Parser {
    
//    rootLogger.addAppender(new NullAppender());
    String FieldDelimiter = " ";
	
    /**
     * 
     * 
     * Este metodo permite parsear las palabras de un .pdf
     * @param lastSelected 
     * parametro para saber cual es el nombre del archivo
     * @see <a href= "https://www.youtube.com/watch?v=rLsKLk-hPH8&feature=youtu.be"></a>
     * @see <a href= "https://www.programcreek.com/java-api-examples/?api=org.apache.pdfbox.pdfparser.PDFParser"></a>
     * @see <a href= "https://www.youtube.com/redirect?q=https%3A%2F%2Fdrive.google.com%2Fopen%3Fid%3D1-yT2IctsPGPArevocthYqp7cm16Oozp8&redir_token=jycP91bxGoL4IjzDeebACQQXvQx8MTU3MTg5NzM2NkAxNTcxODEwOTY2&event=video_description&v=rLsKLk-hPH8">Link libreria</a>
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
    
    
    
    
    /**
     * Funcion para parsear documentos .docx
     * @param fileName nombre del documento a parsear
     * @return arbol con el documento parseado
     */
    public Arbol docxParser(String fileName){
    
        Arbol arbol = new Arbol();
        try {
            
            File file = new File("src\\library\\"+fileName);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());
            XWPFDocument document = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = document.getParagraphs();

            for (XWPFParagraph para : paragraphs) {

                String sPara = para.getText().toString();
                String[] fields = sPara.split(FieldDelimiter, -1);
                insertLineInTree(fields,arbol, fileName);
                
            }
            
            fis.close();
            return arbol;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arbol;
    
    }
    
    
    /**
     * Funcion para parsear documentos .txt
     * @param fileName nombre del documento a parsear
     * @return arbol con el documento parseado
     */
    public Arbol txtParser(String fileName){
        BufferedReader br;
        Arbol arbol = new Arbol();
            try {

                br = new BufferedReader(new FileReader("src\\library\\"+fileName));
                String line;     
                
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(FieldDelimiter, -1);
                    insertLineInTree(fields,arbol, fileName);
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
    
    
    
    
    /**
     * metodo para insertar una linea de texto en un arbol
     * @param words array con palabras en cada una de sus posiciones
     * @param arbol arbol binario de busqueda donde se insertaran las palabras
     * @param fileName nombre del archivo donde estan las palabras
     */
    private void insertLineInTree(String[] words,Arbol arbol,String fileName){        
            for(int i = 0; i < words.length; i++){
                if(!words[i].equals(" ")){
                    arbol.insert(words[i],i, fileName);
                    System.out.println(words[i]+" Added");
                }
            }
    }
    
}
