package IOFile;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
	/**
	 * Lis le fichier en fonction du filePath
	 * @param filePath
	 * @return le contenu du fichier en une ligne
	 * @throws IOException
	 */
	public static String readFile(String filePath) throws IOException {
		String data = "";							
		File file = new File(filePath);				
		Scanner myReader = new Scanner(file);		
		while (myReader.hasNextLine()) {		
			data += myReader.nextLine();			
		}
		myReader.close();
		return data;							
	}
}
