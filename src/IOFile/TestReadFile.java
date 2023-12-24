package IOFile;

import java.io.File;
import java.io.IOException;

public class TestReadFile {
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */

	public static void main(String[] args) throws IOException {
		String DBPath="."+File.separator+".."+File.separator+".."+File.separator+"DB"+File.separator+"Text.txt";
		System.out.println(ReadFile.readFile(DBPath));
	}

}
