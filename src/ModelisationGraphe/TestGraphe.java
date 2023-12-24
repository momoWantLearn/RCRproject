package ModelisationGraphe;

public class TestGraphe {

	public static void main(String[] args) {
		Graphe g = new Graphe(4);
		g.addArgument("A");
		g.addArgument("B");
		g.addArgument("C");
		g.addArgument("D");

		g.addContradiction("A", "B");
		g.addContradiction("B", "C");
		g.addContradiction("B", "D");

		
		System.out.println(g.trouverSoluPreferee());
	}

}
