package anno16_17.sisop;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio4punto5 {
	
	private static Semaphore mutex = new Semaphore(0);
	private static int numSec = 1;
	
	public static class CronometroAd extends Thread {
		
		public void run() { 
			while (!isInterrupted()) { 
				try {
					mutex.acquire();
					mutex.release();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try { 
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) { 
					break; 
				} 
				System.out.println("\n" + numSec); 
				numSec++;
			} 
		}
	}
	

	public static void main(String[] args) throws InterruptedException { 
		Scanner in = new Scanner(System.in); 
		
		System.out.println("CRONOMETRO\n1)Avvia (o riavvia)\n2)Ferma\n3)Reset\n4)Esci");
		
		CronometroAd cronometro = new CronometroAd();
		cronometro.setDaemon(true);
		cronometro.start();
		
		int sceltaPrima = -1;
		while ( true ) {
			int scelta = in.nextInt();
			switch (scelta) {
				case 1: if ( sceltaPrima==1 ) 
							System.out.println("Devi prima fermare");
						else mutex.release();
						break;
				case 2: if ( sceltaPrima == 1 )
							mutex.acquire();
						else System.out.println("Devi prima avviare"); 
						break;
				case 3: if ( sceltaPrima==1 ) 
							System.out.println("Devi prima fermare");
						else{ numSec = 1; System.out.println("\n\n\n"); }
						break;
				default: return;
						
			}
			sceltaPrima = scelta;
		}
		
	}
}