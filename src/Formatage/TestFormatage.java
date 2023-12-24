package Formatage;

import java.util.Vector;

import ModelisationGraphe.Graphe;

public class TestFormatage {

	public static void main(String[] args) {
		String fileContent = "arg(Z).";
		
		try {
			Graphe g = FormaterEntree.formaterEntree(fileContent);
			
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
		Vector<String> v = new Vector<String>();
		v.add("A1");
		v.add("A2");
		v.add("A3");
		v.add("A4");
		
		System.out.println(FormaterSortie.formaterSortie(v));
		System.out.println();
	}

}
