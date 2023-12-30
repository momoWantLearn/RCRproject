package ModelisationGraphe;

import java.util.HashMap;
import java.util.Vector;

public class Permute {
	/**
	 * Creer toutes les permutations d'une liste et les retourne
	 * SOURCE: https://stackoverflow.com/a/2599724
	 * @param sortedInts
	 * @return toutes les solutions possibles pour la taille du graphe
	 */
	public static HashMap<Integer, Vector<Integer>> displaySubsets(Vector<Integer> sortedInts) {
		int nb = 0;
		HashMap<Integer, Vector<Integer>> total = new HashMap<Integer, Vector<Integer>>();
		int n = sortedInts.size();
		long combinations = 1 << n;
		for (int setNumber = 0; setNumber < combinations; setNumber++) {
			Vector<Integer> aResult = new Vector<Integer>();
			for (int digit = 0; digit < n; digit++) {
				if ((setNumber & (1 << digit)) > 0) {
					aResult.add(sortedInts.get(digit));
				}
			}
			total.put(nb, aResult);
			nb++;
		}
		return total;
	}

}