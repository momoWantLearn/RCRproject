import java.io.File;

import java.io.IOException;
import Menu.Menu;





public class Main {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		   // Récupérer les paramètres de ligne de commande
			Menu menu =new Menu(args);
        	String fileName = menu.getFileName();											
			File f = new File(fileName);
			if (f.exists()) {
				try {
					menu.chooseMethode();
				} catch (IOException | RuntimeException e) {
					System.out.println(e.getMessage());
				}							//Si le fichier cible entre via l'argument existe, on passe dans le menu 2
			} else {
				try {
					throw new RuntimeException("Chemin de fichier non valable"); //Si le fichier cible entre via l'argument n'existe pas, on renvoie un RuntimeException
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
					}
			}
	}
}
	

