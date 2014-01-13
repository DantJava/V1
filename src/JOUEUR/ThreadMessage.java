package JOUEUR;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ThreadMessage extends Thread{
	
	private String message;
	private PrintWriter out;
	private BufferedReader in;
	private String type;

	
	public ThreadMessage(PrintWriter out, BufferedReader in, String type){
		this.out = out;
		this.in = in;
		this.type = type;
	}
	
	public void run(){
		 try {
			while(true){
			message = in.readLine();
			out.println(type + message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
}
