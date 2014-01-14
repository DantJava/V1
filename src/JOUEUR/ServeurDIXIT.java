package JOUEUR;

import java.net.ServerSocket;
import java.net.Socket;
 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import PARTIE.Joueur;
import PARTIE.Partie;


 
class ServeurDIXIT extends Thread{
	
	public static final int NB_JOUEUR = 4;
    private static int port = 1001; 
    private ServerSocket serveur = null;
    
    private List <Socket> listClient;
    private List <ThreadClient> poolThread;
    private ThreadPartie partie;
    private List<Partie> listPartie;
    private List<Joueur> listJoueur;
      public ServeurDIXIT() throws IOException{

    	  // Affectation du socket serveur
            serveur = new ServerSocket(port);
            listClient = new LinkedList<Socket>();
            poolThread = new LinkedList<ThreadClient>();
            //partie = new ThreadPartie();
            listPartie = new ArrayList<Partie>();
            listJoueur = new ArrayList<Joueur>();
      
      }
      public void connexion() throws IOException{
    	  //Mise en route des Threads
    	  
    	  while(listClient.size() < NB_JOUEUR) {
    		  
	    	Socket ClientNew = serveur.accept();
	    	listClient.add(ClientNew);
	    	/*synchronized(listClient) {
	    		  listClient.add(ClientNew);
	    	}
	    	synchronized(poolThread) {
		    	if(poolThread.size() != 0) {
		    		disponible = threadDisponible(); 
		    		if(disponible != null) {
			    		disponible.setEmetteur(ClientNew);
			    		synchronized(disponible) {
			    			disponible.notify();
			    		}
			    		
			    	}
		    	}
	    	}*/
	    	ThreadClient ThreadNew = new ThreadClient(ClientNew, listClient);
	    	ThreadNew.start();
	    	poolThread.add(ThreadNew); 
    	  }
    	  listPartie.add(new Partie(listClient,listJoueur));

    }
      
      public ThreadClient threadDisponible(){
    	  for(ThreadClient t : poolThread)
    	  {
    		  System.out.println("TEST");
    		  System.out.println(t.getEmetteur());
    		  if(t.getEmetteur() == null)
    		  {
    			  return t;
    		  }
    	  }
    	  return null;

    }
	public static void main (String[] args) throws IOException {
		ServeurDIXIT s = new ServeurDIXIT();
		s.connexion();
		
	}
}