import java.io.File;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PDFparse {
	
	/***
	 * @author jimejose
	 * @see https://www.youtube.com/watch?v=rLsKLk-hPH8&feature=youtu.be
	 * @see https://www.programcreek.com/java-api-examples/?api=org.apache.pdfbox.pdfparser.PDFParser
	 * @see Link libreria: https://www.youtube.com/redirect?q=https%3A%2F%2Fdrive.google.com%2Fopen%3Fid%3D1-yT2IctsPGPArevocthYqp7cm16Oozp8&redir_token=jycP91bxGoL4IjzDeebACQQXvQx8MTU3MTg5NzM2NkAxNTcxODEwOTY2&event=video_description&v=rLsKLk-hPH8
	 * 
	 * Este metodo permite obtener las palabras de un .pdf
	 */
	
	public void getPDFwords() {
		try {
			FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("PDF", "*.pdf")
            );
            
            File selectedFile = fileChooser.showOpenDialog(TextFinder.getStage());
			PDDocument documento = PDDocument.load(selectedFile);
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
					System.out.println("\n"+line);
					} 
				}
			documento.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
