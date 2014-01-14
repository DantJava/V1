package PARTIE;

import java.net.Socket;
import java.util.List;

public class Partie extends Thread{

	private List<Socket> listSocket;
	private List<Joueur> listJoueur;
	
	public Partie(List<Socket> listClient, List<Joueur> listJoueur) {
		// TODO Auto-generated constructor stub
		this.listJoueur = listJoueur;
		listSocket = listClient;
	}
	
	
	public void run(){
		System.out.println("Partie debuter");
	}
}
