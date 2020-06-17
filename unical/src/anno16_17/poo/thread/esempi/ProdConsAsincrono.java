package anno16_17.poo.thread.esempi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdConsAsincrono{
	//per semplicità, Producer e Consumer sono classi inner e static
	static class Producer extends Thread{
		private int seed;
		private BlockingQueue<Integer> bq;
		public Producer( String nome, BlockingQueue<Integer> bq, int seed ){
			super(nome); this.bq=bq; this.seed=seed;
		}
		public void run(){
			while( true ){
				try{
					System.out.println("Producer "+getName()+" trasmette "+seed);
					bq.put(seed); seed++;
				}catch( InterruptedException e ){}
			}
		}
	}//Producer

	static class Consumer extends Thread{
		private BlockingQueue<Integer> sq;
		public Consumer( String nome, BlockingQueue<Integer> sq ){
			super(nome); this.sq=sq;
		}
		public void run(){
			while( true ){
				try{
					int msg=sq.take();
					System.out.println("Consumer "+getName()+" riceve "+msg);
				}catch( InterruptedException e ){}
			}
		}
	}//Consumer
	
	public static void main( String[] args ){
		BlockingQueue<Integer> buffer=new ArrayBlockingQueue<>(5); //Buffer limitato di capacita' 5
		//Esempio: due produttori e un consumatore comunicano tramite buffer
		Producer p1=new Producer("p1",buffer,0);
		Producer p2=new Producer("p2",buffer,1000000);
		Consumer q=new Consumer("q",buffer);
		p1.start(); p2.start(); q.start();
	}//main
	
}//ProdConsAsincrono
