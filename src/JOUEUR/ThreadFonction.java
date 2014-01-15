package JOUEUR;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ThreadFonction extends Thread{
	
	private String message;
	private PrintWriter out;
	private BufferedReader in;
	private String type;

	
	public ThreadFonction(PrintWriter out, BufferedReader in, String type){
		this.out = out;
		this.in = in;
		this.type = type;
	}
	
	public void run(){
		 try {
//			 VueAttente a =
//			 while(fenetre.GETCONTENTPANE.getvalLabel != ServeurDIXIT.NB_JOUEUR )
//				 a = in.readLine();
//			 	setLabel(a);
			 	
			while(true){
			message = in.readLine();
			
			switch(message){
			case "jeu" : JoueurDIXIT.pretJouer = true; System.out.println("JEU");break;
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

	public String getMessage() {
		return message;
	}
}
