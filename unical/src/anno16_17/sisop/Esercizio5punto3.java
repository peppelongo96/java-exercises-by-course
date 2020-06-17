package anno16_17.sisop;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Esercizio5punto3 {
	
	private Buffer buffer = new Buffer();
	private Consumatore c;
	private Produttore p;
	
	private class Buffer {
		
		private int len = 10;
		private int[] buffer = new int[len];
		private int posIn = 0;
		private int posOut = 0;
		
		private Semaphore leggi = new Semaphore(0);
		private Semaphore mutex = new Semaphore(1);
		
		private void put(int i) throws InterruptedException {
			mutex.acquire();
			mutex.release();
			buffer[posIn] = i;
			posIn++;
			if ( posIn==buffer.length ) {
				buffer = Arrays.copyOf(buffer, len*2 );
				len = buffer.length;
			}
			leggi.release();
		}
		
		private int get() throws InterruptedException {
			leggi.acquire();
			mutex.acquire();
			int ris = buffer[posOut];
			buffer[posOut] = -1;
			posOut++;
//			if ( len>10 && posIn-posOut==9 ) {
//				buffer = Arrays.copyOfRange(buffer, posOut, posIn+1);
//				posOut = 0; posIn = buffer.length-1; 
//				len = buffer.length;
//			}
			mutex.release();
			return ris;
		}
		
		private void stampa() {
			for (int i = 0; i < buffer.length; i++) {
				System.out.print(" "+buffer[i]);
			}
		}
	}
	
	private class Produttore extends Thread {
		
		private Random random = new Random();
		
		@Override
		public void run() {
			int count = 15;
			while ( count>0 ) {
				try {
					int n = produci();
					buffer.put(n);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				count--;
			}
		}
		
		private int produci() throws InterruptedException {
			TimeUnit.SECONDS.sleep(2);
			return random.nextInt(30);
		}
	}
	
	private class Consumatore extends Thread {
		
		@Override
		public void run() {
			int count = 15;
			while ( count>0 ) {
				try {
					consuma();
					buffer.get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				count--;
			}
		}
		
		private void consuma() throws InterruptedException {
			TimeUnit.SECONDS.sleep(2);
		}
	}
	
	private void inizializzaThread() throws InterruptedException {
		buffer.stampa();
		c = new Consumatore(); c.start();
		p = new Produttore(); p.start();
		c.join(); p.join();
		System.out.println("");
		buffer.stampa();
	}

	public static void main(String[] args) throws InterruptedException {
		Esercizio5punto3 e = new Esercizio5punto3();
		e.inizializzaThread();
	}
}
