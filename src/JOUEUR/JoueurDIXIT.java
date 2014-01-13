package JOUEUR;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
 
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import VIEW.VueIdentification;



 
public class JoueurDIXIT extends Thread{
	
    private static int port = 1001;
    private static String host = "localhost";
    private Socket serveurDIXIT;
    private String nom;
    // Pipe de lecture et d'ecriture
    private PrintWriter AffichageServeur;
    private PrintWriter AffichageClient;
    private BufferedReader LireServeur;
    private BufferedReader LireClient;
    
    
    //Threads
    private ThreadMessage thread1;
    private ThreadMessage thread2;
    
    public JoueurDIXIT(String nom) throws IOException, InterruptedException{
    	this.nom = nom;
    	serveurDIXIT = new Socket(host, port);
    	AffichageServeur = new PrintWriter(serveurDIXIT.getOutputStream(), true); 
    	AffichageClient = new PrintWriter(System.out, true);
        LireServeur = new BufferedReader(new InputStreamReader(serveurDIXIT.getInputStream())); 
		LireClient = new BufferedReader(new InputStreamReader(System.in));
		thread1 = new ThreadMessage(AffichageServeur, LireClient, "CLIENT : ");
		thread2 = new ThreadMessage(AffichageClient, LireServeur, "SERVEUR : ");
    }
 
	public void initialisation() throws IOException {
		   
		thread2.start();
		thread1.start();
	}

}