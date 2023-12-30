package Formatage;

import java.util.Vector;


public class FormaterSortie {
	/**
	 * Recupere le Vecteur d'une solutions admissible et la formate en un String 
	 * @param liste
	 * @return la chaine formatée ou "vide"
	 */
	public static String formaterSortie(Vector<String> liste) {
		StringBuffer sb = new StringBuffer();
		for (String s : liste) {
			sb.append(s);
			sb.append(",");
		}
		if (sb.length() > 1) {
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		} else {
			return "Vide";
		}

	}
}
