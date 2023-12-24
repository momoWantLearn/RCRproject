package ModelisationGraphe;

import java.util.HashMap;
import java.util.Vector;

public class Graphe {

	private static int nbArg; 
	
	/**
	 * Dictionnaire noms --> indices
	 */
	private HashMap<String, Integer> args;
	
	/**
	 * Dictionnaires indices --> noms
	 */
	private HashMap<Integer, String> argsInverse;

	/**
	 * Matrices d'Adjacence du Graphe
	 */
	private int[][] matrice;

	/**
	 * Constructeur du Graphe
	 * @param n
	 */
	public Graphe(int n) {
		Graphe.nbArg = 0;
		this.args = new HashMap<String, Integer>();
		this.argsInverse = new HashMap<Integer, String>();
		this.matrice = new int[n][n];
	}

	/**
	 * Getter Arguments
	 * @return Arguments
	 */
	public HashMap<String, Integer> getArgs() {
		return args;
	}

	
	/**
	 * Transforme une solution d'indice en solution de nom D'argument (String)
	 * @param aInverser Solution d'indice 
	 * @return Solution avec les noms d'arguments en String
	 */
	public Vector<String> inverserSolu(Vector<Integer> aInverser) {

		Vector<String> sString = new Vector<String>();

		for (Integer i : aInverser) {
			sString.add(argsInverse.get(i));
		}
		return sString;
	}
	public String soluString(Vector<String> aInverser) {

		if(aInverser.size()==0) {
			return"[]";
		}
		String mot="[";
		for(int i=0; i<aInverser.size()-1;i++) {
			mot=mot+aInverser.get(i);
			mot=mot+",";
			
		}
		mot=mot+aInverser.get(aInverser.size()-1);
		mot=mot+"]";
		return mot;
	}

	/**
	 * Ajoute un argument au Graphe
	 * @param nom de l'argument
	 */
	public void addArgument(String nom) {
		args.put(nom, nbArg);
		argsInverse.put(nbArg, nom);
		nbArg++;
	}

	/**
	 * Ajoute une contradiction au Graphe
	 * @param arg1 Premier Argument
	 * @param arg2 Second Argument
	 */
	public void addContradiction(String arg1, String arg2) {
		if (args.containsKey(arg1) && args.containsKey(arg2)) {
			if(!arg1.equals(arg2)) {
				int a1 = args.get(arg1);
				int a2 = args.get(arg2);
				matrice[a1][a2] = 1;
			}else {
				throw new RuntimeException("Un argument ne peut pas se contredire");
			}
		} else {
			throw new RuntimeException("Argument non existant");
		}
	}

	public boolean isStableInt(Vector<Integer> solu, boolean msg) {

		Vector<Integer> def = new Vector<Integer>();
		Vector<Integer> att = new Vector<Integer>();

		for (Integer s : solu) {
			for (int i = 0; i < nbArg; i++) {
				if (matrice[s][i] == 1 && !att.contains(i)) {
					att.add(i);
				}
				if (matrice[i][s] == 1 && !def.contains(i)) {
					def.add(i);
				}
			}
		}
		
		/**
		 * Si un element de la solution attaque un autre element de la solution on retourne faux
		 */
		for (Integer i : att) {
			if (solu.contains(i)) {
				if(msg) {
					System.out.println("L'argument "+this.argsInverse.get(i)+" est attaqué par un membre de la solution!");
				}return false;
			}
		}
		/**
		 * Si tout les elements de defend ne sont pas compris dans attaque on retourne faux
		 */
		for (Integer i : def) {
			if (!att.contains(i)) {
				if(msg) {
				System.out.println("L'agument "+this.argsInverse.get(i)+" n'est pas défendu dans la solution!");
				}
				return false;
			}
		}
		
		
		return true;
	}
	public void trouverSoluStable() {
		Vector<Integer> v = new Vector<Integer>(nbArg);
		HashMap<Integer, Vector<Integer>> total = new HashMap<Integer, Vector<Integer>>();
		HashMap<Integer, Vector<Integer>> admissible = new HashMap<Integer, Vector<Integer>>();
		int nbAdmis = 0;

		// Initialisation de v, Vecteur [0,1,2,3,...,nbArg]
		for (int i = 0; i < nbArg; i++) {
			v.add(i);
		}

		total = Permute.displaySubsets(v); 


		for (Integer i : total.keySet()) {
			Vector<Integer> vec = total.get(i);
			if (this.isStableInt(vec, false)) {
				admissible.put(nbAdmis, vec);
				nbAdmis++;
			}
		}
		for(int i = 0 ; i<admissible.size();i++) {
			System.out.println(inverserSolu(admissible.get(i)));
		}
		
		

		
	}
	/**
	 * Test si une solution est admissible (en String)
	 * @param solu Solution a tester
	 * @param msg Vrai si on veut des messages d'erreurs
	 * @return Vraie si Admissible, Faux sinon
	 */
	public boolean isComplete(Vector<String> ensemble, boolean msg) {
		Vector<Integer> solu = new Vector<Integer>();

		for (String s : ensemble) {
			solu.add(args.get(s));
		}

		
		Vector<Integer> def = new Vector<Integer>();
		Vector<Integer> att = new Vector<Integer>();
		

		for (Integer s : solu) {
			for (int i = 0; i < nbArg; i++) {
				if (matrice[s][i] == 1 && !att.contains(i)) {
					att.add(i);
				}
				if (matrice[i][s] == 1 && !def.contains(i)) {
					def.add(i);
				}
			}
		}
		
		/**
		 * Si un element de la solution attaque un autre element de la solution on retourne faux
		 */
		for (Integer i : att) {
			if (solu.contains(i)) {
				if(msg) {
					System.out.println("L'argument "+this.argsInverse.get(i)+" est attaqué par un membre de la solution!");
				}return false;
			}
		}
		/**
		 * Si tout les elements de defend ne sont pas compris dans attaque on retourne faux
		 */
		for (Integer i : def) {
			if (!att.contains(i)) {
				if(msg) {
				System.out.println("L'agument "+this.argsInverse.get(i)+" n'est pas défendu dans la solution!");
				}
				return false;
			}
		}
		System.out.println("cest sol "+solu);
		System.out.println("cest att "+(att));
		System.out.println("cest def "+def);
		for (Integer i : att) {
			for (int j = 0; j < nbArg; j++) {
				if (matrice[i][j] == 1 && matrice[j][i]==0 && !att.contains(j)) {
					
					if (!(solu.contains(j))) {
						System.out.println(i);
						System.out.println(j);
						return false ;
					}
					;
				}
				
			}
		}
		
		System.out.println("cest sol "+solu);
		System.out.println("cest att "+(att));
		System.out.println("cest def "+def);
		System.out.println("----------------------------------");
		return true;
		

		

		
	}
	/**
	 * Test si une solution est admissible (en Indice)
	 * @param solu Solution a tester
	 * @param msg Vrai si on veut des messages d'erreurs
	 * @return Vraie si Admissible, Faux sinon
	 */
	public boolean isCompleteInt(Vector<Integer> solu, boolean msg) {

		Vector<Integer> def = new Vector<Integer>();
		Vector<Integer> att = new Vector<Integer>();

		for (Integer s : solu) {
			for (int i = 0; i < nbArg; i++) {
				if (matrice[s][i] == 1 && !att.contains(i)) {
					att.add(i);
				}
				if (matrice[i][s] == 1 && !def.contains(i)) {
					def.add(i);
				}
			}
		}
		
		/**
		 * Si un element de la solution attaque un autre element de la solution on retourne faux
		 */
		for (Integer i : att) {
			if (solu.contains(i)) {
				if(msg) {
					System.out.println("L'argument "+this.argsInverse.get(i)+" est attaqué par un membre de la solution!");
				}return false;
			}
		}
		/**
		 * Si tout les elements de defend ne sont pas compris dans attaque on retourne faux
		 */
		for (Integer i : def) {
			if (!att.contains(i)) {
				if(msg) {
				System.out.println("L'agument "+this.argsInverse.get(i)+" n'est pas défendu dans la solution!");
				}
				return false;
			}
		}
		System.out.println("cest sol "+solu);
		System.out.println("cest att "+(att));
		System.out.println("cest def "+def);
		boolean ok = true ;
	    for(int i = 0; i < nbArg; i++) {
	    	for(int r : att) {
	    		if(matrice[r][i] == 1) {
	    			ok = true ;
	    			for(int l = 0 ; l<nbArg ; l++ ) {
	    				if(matrice[l][i]==1 && !att.contains(l)) {
	    					ok = false ;
	    					System.out.println("okkkkk");
	    					System.out.println(i);
	    					System.out.println(l);
	    				}
	    			}
	    			
	    			if(ok && !solu.contains(i) && !att.contains(i)) {
	    				System.out.println(i);
	    				return false ;
	    			}
	    		}
	    	}
	    }
		
		System.out.println("cest sol "+solu);
		System.out.println("cest att "+(att));
		System.out.println("cest def "+def);
		System.out.println("----------------------------------");
		return true;
	}

	// Trouve toute les solutions admissibles et en retorune une aleatoire
	/**
	 * Trouve une solution Admissible parmis toutes celles possible
	 * @return Une Solution Admissible
	 */
	public void trouverSoluComplete() {
		Vector<Integer> v = new Vector<Integer>(nbArg);
		HashMap<Integer, Vector<Integer>> total = new HashMap<Integer, Vector<Integer>>();
		HashMap<Integer, Vector<Integer>> admissible = new HashMap<Integer, Vector<Integer>>();
		int nbAdmis = 0;

		// Initialisation de v, Vecteur [0,1,2,3,...,nbArg]
		for (int i = 0; i < nbArg; i++) {
			v.add(i);
		}

		total = Permute.displaySubsets(v); 


		for (Integer i : total.keySet()) {
			Vector<Integer> vec = total.get(i);
			if (this.isCompleteInt(vec, false)) {
				admissible.put(nbAdmis, vec);
				nbAdmis++;
			}
		}
		for(int i = 0 ; i<admissible.size();i++) {
			System.out.println(inverserSolu(admissible.get(i)));
		}
		
		

		
	}
	public boolean testSoluComplete(String S) {
		Vector<Integer> v = new Vector<Integer>(nbArg);
		HashMap<Integer, Vector<Integer>> total = new HashMap<Integer, Vector<Integer>>();
		HashMap<Integer, Vector<Integer>> admissible = new HashMap<Integer, Vector<Integer>>();
		int nbAdmis = 0;

		// Initialisation de v, Vecteur [0,1,2,3,...,nbArg]
		for (int i = 0; i < nbArg; i++) {
			v.add(i);
		}

		total = Permute.displaySubsets(v); 


		for (Integer i : total.keySet()) {
			Vector<Integer> vec = total.get(i);
			if (this.isCompleteInt(vec, false)) {
				admissible.put(nbAdmis, vec);
				nbAdmis++;
			}
		}
		
		for(int i = 0 ; i<admissible.size();i++) {
			System.out.println(soluString(inverserSolu(admissible.get(i))));
			
			if(Softaware.compare(soluString(inverserSolu(admissible.get(i))),S)) {
				System.out.println(soluString(inverserSolu(admissible.get(i))));
				System.out.println(S);
				return true ;
			}
		}
		
		return false;
		
	}
	public boolean testCreduleComplete(String S) {
		Vector<Integer> v = new Vector<Integer>(nbArg);
		HashMap<Integer, Vector<Integer>> total = new HashMap<Integer, Vector<Integer>>();
		HashMap<Integer, Vector<Integer>> admissible = new HashMap<Integer, Vector<Integer>>();
		int nbAdmis = 0;

		// Initialisation de v, Vecteur [0,1,2,3,...,nbArg]
		for (int i = 0; i < nbArg; i++) {
			v.add(i);
		}

		total = Permute.displaySubsets(v); 


		for (Integer i : total.keySet()) {
			Vector<Integer> vec = total.get(i);
			if (this.isCompleteInt(vec, false)) {
				admissible.put(nbAdmis, vec);
				nbAdmis++;
			}
		}
		
		for(int i = 0 ; i<admissible.size();i++) {
			System.out.println(soluString(inverserSolu(admissible.get(i))));
			System.out.println(S);
			if(soluString(inverserSolu(admissible.get(i))).contains(S)) {
				System.out.println(soluString(inverserSolu(admissible.get(i))));
				System.out.println(S);
				return true ;
			}
		}
		
		return false;
		
	}




	
}
