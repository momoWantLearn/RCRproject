package ModelisationGraphe;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public  class Softaware {
	
	
	

	public static boolean compare(String chaine1, String chaine2) {
        // Convertir les chaînes en ensembles (sets) après avoir retiré les crochets
        Set<String> ensemble1 = new HashSet<>(Arrays.asList(chaine1.replaceAll("[\\[\\]]", "").split(",")));
        Set<String> ensemble2 = new HashSet<>(Arrays.asList(chaine2.replaceAll("[\\[\\]]", "").split(",")));

        // Vérifier si les ensembles sont égaux
        return ensemble1.equals(ensemble2);
    }
	 
	 
	 
	 public static String convertir(String entree) {
	        // Diviser la chaîne en un tableau de lettres
	        String[] lettres = entree.split(",");

	        // Convertir chaque lettre en majuscule
	        for (int i = 0; i < lettres.length; i++) {
	            lettres[i] = lettres[i].toUpperCase();
	        }

	        // Joindre les lettres avec des virgules entre elles
	        String resultat = "[" + String.join(",", lettres) + "]";

	        return resultat;
	    }
	
	 public static boolean VECO(Graphe g , String S) {
		
		
		return g.testSoluComplete(S);
		
	 }
	 public static boolean DCCO(Graphe g , String S) {
			
			return g.testCreduleComplete(S);
			
		 }
	
	
	

}
