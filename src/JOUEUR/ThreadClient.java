package JOUEUR;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ThreadClient  extends Thread{
	
	private String message;
	private List<Socket> Receveur;
	private Socket emetteur;
	
	public ThreadClient(Socket emet, List listClient){
		this.emetteur =emet;
		this.Receveur = listClient;
	}
	 public Socket getEmetteur(){
		 return emetteur;
	 }
	 public void setEmetteur(Socket NewEmetteur){
		 this.emetteur = NewEmetteur;
	 }
	public void run(){
		
			while(true){
				 try {
					 message = new BufferedReader(new InputStreamReader(emetteur.getInputStream())).readLine();
				synchronized(Receveur){
					for(Socket sock : Receveur)//(int i = 0; i < this.Receveur.size(); i++)
					{
						if(sock != emetteur){
							new PrintWriter(sock.getOutputStream(), true).println(message);
							System.out.println(sock);
						}	
					}
				}
				 } 
				 catch (IOException e){
					  System.out.println("Client deconnecté");
					  synchronized(Receveur) {
						  Receveur.remove(emetteur);
						  emetteur = null;
					  }
					  synchronized(this){
						  try {
							this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					  }
				 }
		
			}
		
		 
	}
}





