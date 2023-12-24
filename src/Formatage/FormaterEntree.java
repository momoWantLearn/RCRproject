package Formatage;

import ModelisationGraphe.Graphe;

public class FormaterEntree {

	/**
	 * Formatage du fichier lu
	 * @param fileContent
	 * @return le Graphe avec arguments passées en paramètres
	 * @throws RuntimeException
	 */
	public static Graphe formaterEntree(String fileContent) throws RuntimeException {
		/**
		 * Formatage du fichier lu en parametres
		 */
		fileContent = fileContent.replace(" ", "");
		int n = fileContent.split("arg").length - 1;
		Graphe g = new Graphe(n); 

		if (fileContent.length() > 0) {

			String[] lignes = fileContent.split("[.]"); 
			for (String ligne : lignes) {

				if (!ligne.equals("")) {
					formateLigne(g, ligne); 
				}
			}

		} else {
			throw new RuntimeException("Fichier Vide");
		}

		return g;
	}
	
	/**
	 * Formatage d'une ligne
	 * @param g
	 * @param ligne
	 * @throws RuntimeException
	 */
	private static void formateLigne(Graphe g, String ligne) throws RuntimeException {
		String command = ligne.split("[(]")[0];					

		if (command.equals("arg")) {						
			String arg = ligne.split("[(]")[1].split("[)]")[0];	
			g.addArgument(arg);									
			return;
		}
		if (command.equals("att")) {					
			String arg = ligne.split("[(]")[1].split("[)]")[0];	
			String[] args = arg.split(",");						
			g.addContradiction(args[0], args[1]);				
			return;
		}
		throw new RuntimeException("Commande non valable");		

	}
}
