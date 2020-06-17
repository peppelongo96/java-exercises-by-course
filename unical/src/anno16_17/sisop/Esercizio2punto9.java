package anno16_17.sisop;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Esercizio2punto9 {

	public static class Cronometro extends Thread {
		public void run() { 
			int numSecondi = 1; 
			while (!isInterrupted()) { 
				try { 
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) { 
					break; 
				} 
				System.out.println("\n" + numSecondi); 
				numSecondi++; } 
		}
	}
	

	public static void main(String[] args) { 
		Scanner in = new Scanner(System.in); 
		Cronometro cronometro = new Cronometro();
		cronometro.setDaemon(true);
		System.out.println("Premi invio per cominciare"); 
		in.nextLine(); 
		cronometro.start(); 
		System.out.println("Premi invio per terminare"); 
		in.nextLine(); 
		System.out.println("Programma terminato");
	}

}
