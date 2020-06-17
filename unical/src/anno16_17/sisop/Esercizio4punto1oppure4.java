package anno16_17.sisop;

import java.util.concurrent.Semaphore;

public class Esercizio4punto1oppure4 {
	
	private static Semaphore mutexA = new  Semaphore(1);
	private static Semaphore mutexB = new  Semaphore(0);

	
	private static class stampaA extends Thread {
		
		@Override
		public void run() {
			while ( true ) {
				try {
					mutexA.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("A");
				mutexB.release();
			}
		}
	}
	
	private static class stampaB extends Thread {
		
		@Override
		public void run() {
			while ( true ) {
				try {
					mutexB.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("B");
				mutexA.release();
			}
		}
	}
	
	public static void main(String[] args) {
		new stampaA().start();
		new stampaB().start();
	}
}
