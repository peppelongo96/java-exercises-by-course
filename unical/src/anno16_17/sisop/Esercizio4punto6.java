package anno16_17.sisop;

import java.util.concurrent.Semaphore;

public class Esercizio4punto6 {

	private static Semaphore mutexA = new  Semaphore(2);
	private static Semaphore mutexB = new  Semaphore(0);
	private static int count = 12;
	
	private static class stampaA extends Thread {
		
		@Override
		public void run() {
			while ( count>0 ) {
				try {
					mutexA.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("A");
				mutexB.release();
				count--;
			}
		}
	}
	
	private static class stampaB extends Thread {
		
		@Override
		public void run() {
			while ( count>0 ) {
				try {
					mutexB.acquire(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("B");
				mutexA.release(2);
				count--;
			}
		}
	}
	
	public static void main(String[] args) {
		new stampaA().start();
		new stampaB().start();
	}
}
