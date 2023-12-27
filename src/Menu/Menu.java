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
	                    }
	                    break;
	            }
	        }

		}
		public void chooseMethode() throws IOException {
			this.arguments = FormaterEntree.Convert(this.arguments);
			String fileContent;
			fileContent = ReadFile.readFile(this.fileName);
			Graphe g = FormaterEntree.formaterEntree(fileContent);
			if("VE-CO".equals(method)) {
				System.out.println(g.testSoluComplete(arguments));
			}
			if("DC-CO".equals(method)) {
				String s = this.arguments.substring(1,this.arguments.length()-1);
				System.out.println(g.testCreduleComplete(s));
			}
			if("DS-CO".equals(method)) {
				String s = this.arguments.substring(1,this.arguments.length()-1);
				System.out.println(g.testSepticalComplete(s));
			}
			if("VE-ST".equals(method)) {
				
			
			}
			if("DC-ST".equals(method)) {
				
				
			
			}
			if("DS-ST".equals(method)) {
				
			
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
