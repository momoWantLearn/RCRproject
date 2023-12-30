package Menu;


import java.io.IOException;

import Formatage.FormaterEntree;
import IOFile.ReadFile;
import ModelisationGraphe.Graphe;

public class Menu {
	private String arguments;
	private String fileName;
	private String method ;
	
	
	public Menu(String args[]) {
		
		for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-p":
                    if (i + 1 < args.length) {
                        this.method=args[i + 1];
                        i++; 
                    }
                    break;

                case "-f":
                    if (i + 1 < args.length) {
                        this.fileName=args[i + 1];
                        i++; 
                    }
                    break;

                case "-a":
                	
                    if (i + 1 < args.length) {
                        this.arguments = args[i + 1];
                        i++; 
                    }else {
                    	this.arguments="[]";
                    }
                    break;
            }
        }

	}
	public void chooseMethode() throws IOException, RuntimeException {
	    this.arguments = FormaterEntree.Convert(this.arguments);
	    String fileContent;
	    fileContent = ReadFile.readFile(this.fileName);
	    Graphe g = FormaterEntree.formaterEntree(fileContent);

	    switch (method) {
	        case "VE-CO":
	            System.out.println(g.testSoluComplete(arguments));
	            break;
	            
	        case "DC-CO":
	            if (arguments.length() > 3) {
	                throw new RuntimeException("il n'y a qu'un seul argument");
	            } else {
	                String s = this.arguments.substring(1, this.arguments.length() - 1);
	                System.out.println(g.testCreduleComplete(s));
	            }
	            break;

	        case "DS-CO":
	            if (arguments.length() > 3) {
	                throw new RuntimeException("il n'y a qu'un seul argument");
	            } else {
	                String s = this.arguments.substring(1, this.arguments.length() - 1);
	                System.out.println(g.testSepticalComplete(s));
	            }
	            break;

	        case "VE-ST":
	            System.out.println(g.testSoluStable(arguments));
	            break;

	        case "DC-ST":
	            if (arguments.length() > 3) {
	                throw new RuntimeException("il n'y a qu'un seul argument");
	            } else {
	                String s = this.arguments.substring(1, this.arguments.length() - 1);
	                System.out.println(g.testCreduleStable(s));
	            }
	            break;

	        case "DS-ST":
	            if (arguments.length() > 3) {
	                throw new RuntimeException("il n'y a qu'un seul argument");
	            } else {
	                String s = this.arguments.substring(1, this.arguments.length() - 1);
	                System.out.println(g.testSepticalStable(s));
	            }
	            break;

	        default:
	            throw new RuntimeException("Méthode non reconnue : " + method);
	    }
	}

	
	public String getArguments(){
		return arguments;
		
	}
	public String getFileName() {
		return fileName;
	}
	
	public String getMethod() {
		return method;
	}
		

}
