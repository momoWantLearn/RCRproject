import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Vector;

import Formatage.FormaterEntree;
import IOFile.ReadFile;
import ModelisationGraphe.Graphe;
import ModelisationGraphe.Softaware;



public class Main {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		   // Récupérer les paramètres de ligne de commande
        String operation = null;
        String fileName = null;
        String arguments = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-p":
                    if (i + 1 < args.length) {
                        operation = args[i + 1];
                        i++; 
                    }
                    break;

                case "-f":
                    if (i + 1 < args.length) {
                        fileName = args[i + 1];
                        i++; 
                    }
                    break;

                case "-a":
                    if (i + 1 < args.length) {
                        arguments = args[i + 1];
                        i++; 
                    }
                    break;
            }
        }
															//Verification d'une entree d'un chemin dans l'argument 
			File f = new File(fileName);
			
			if (f.exists()) {
				String fileContent;
				try {
					fileContent = ReadFile.readFile(fileName);
					Graphe g = FormaterEntree.formaterEntree(fileContent);
					if("VE-CO".equals(operation)) {
						System.out.println(Softaware.VECO(g,arguments));
					}
					if("DC-CO".equals(operation)) {
					
						System.out.println(fileContent);
					    g = FormaterEntree.formaterEntree(fileContent);
						
						g.trouverSoluComplete();
					}
					if("DS-CO".equals(operation)) {
						
						System.out.println(fileContent);
					    g = FormaterEntree.formaterEntree(fileContent);
						
						g.trouverSoluComplete();
					}
					if("VE-ST".equals(operation)) {
						
					
					}
					if("DC-ST".equals(operation)) {
						
						
					
					}
					if("DS-ST".equals(operation)) {
						
					}
					if("DC-ST".equals(operation)) {
				
					}
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
										//Si le fichier cible entre via l'argument existe, on passe dans le menu 2
			} else {
				try {
					throw new RuntimeException("Chemin de fichier non valable"); //Si le fichier cible entre via l'argument n'existe pas, on renvoie un RuntimeException
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
					}
			}
	}
}
	
