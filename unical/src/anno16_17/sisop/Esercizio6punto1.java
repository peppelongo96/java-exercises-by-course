package anno16_17.sisop;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Esercizio6punto1 {
	
	private static int count = 10;
	private BufferIDInf buffer = new BufferIDInf(20);
	//private Lock lockStampa = new ReentrantLock();
	
	private class ordineProd implements Comparator<Produttore> {
		@Override
		public int compare(Produttore p1, Produttore p2) {
			if ( p1.getID() < p2.getID() ) return 1;
			else if ( p1.getID() > p2.getID() ) return -1;
			return 0;
		}
	}
	
	private class ordineCons implements Comparator<Consumatore> {
		@Override
		public int compare(Consumatore c1, Consumatore c2) {
			if ( c1.getID() < c2.getID() ) return 1;
			else if ( c1.getID() > c2.getID() ) return -1;
			return 0;
		}
	}
	
	private class Produttore extends Thread {
		private int ID;
		private Random random = new Random();
		
		private Produttore(int ID) {
			this.ID = ID;
		}
		
		private int getID () {
			return ID;
		}
		
		@Override
		public void run() {
			while ( count>0 ) {
				try {
					//lockStampa.lock();
					int n = produci();
					//lockStampa.unlock();
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
			int ris = random.nextInt(100);
			System.out.println("Produttore-"+ID+" ha prodotto "+ris);
			return ris;
		}
		
	}
	
	private class Consumatore extends Thread {
		private int ID;
		
		private Consumatore(int ID) {
			this.ID = ID;
		}
		
		private int getID() {
			return this.ID;
		}
		
		@Override
		public void run() {
			while ( count>0 ) {
				try {
					consuma();
					buffer.get();
					//lockStampa.lock();
					System.out.println("Consumatore-"+ID+" ha consumato");
					//lockStampa.unlock();
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
	
	private class BufferIDInf {
		private int len, in, out, numElem;
		private int[] buffer;
		private PriorityQueue<Produttore> codaAttesaProd = new PriorityQueue<>(new ordineProd());
		private PriorityQueue<Consumatore> codaAttesaCons = new PriorityQueue<>(new ordineCons());
		
		private Lock l = new ReentrantLock();
		private Condition produco = l.newCondition();
		private Condition consumo = l.newCondition();
		
		
		private BufferIDInf( int len ) {
			this.len = len;
			buffer = new int[len];
		}
		
		private boolean possoProdurre() {
			return numElem < len 
					&& Thread.currentThread() == codaAttesaProd.peek();
		}	
		private boolean possoConsumare() {
			return numElem > 0 
					&& Thread.currentThread() == codaAttesaCons.peek();
		}
		
		private void put ( int n ) throws InterruptedException {
			l.lock();
			try {
				codaAttesaProd.add((Produttore)Thread.currentThread());
				while( !possoProdurre() ) {
					produco.await();
				}
				codaAttesaProd.remove();
				buffer[in] = n;
				in = (in+1)%buffer.length;
				numElem++;
				consumo.signalAll();
			} finally {
				l.unlock();
			}
		}
		
		private int get() throws InterruptedException {
			l.lock();
			int ris = -666;
			try {
				codaAttesaCons.add((Consumatore)Thread.currentThread());
				while ( !possoConsumare() ) {
					consumo.await();
				}
				codaAttesaCons.remove();
				ris = buffer[out];
				out = (out+1)%buffer.length;
				numElem--;
				produco.signalAll();
			} finally {
				l.unlock();
			}
			return ris;
		}
	}
	
	private void inizializza(int p, int c) throws InterruptedException {
		for (int i = 0; i < p; i++) {
			new Produttore(i).start();
		}
		for (int i = 0; i < c; i++) {
			new Consumatore(i).start();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Esercizio6punto1 es = new Esercizio6punto1();
		es.inizializza(5, 5);
	}

}
