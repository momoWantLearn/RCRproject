package ModelisationGraphe;

import java.util.Vector;

public class TestPermute {

	public static void main(String[] args) {
		int nbArg=3;
		Vector<Integer> v = new Vector<Integer>(nbArg);

		for (int i = 0; i < nbArg; i++) {
			v.add(i);
		}
		System.out.println(Permute.displaySubsets(v));
	}

}
