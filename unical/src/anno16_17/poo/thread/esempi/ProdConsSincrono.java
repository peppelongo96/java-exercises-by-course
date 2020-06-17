package anno16_17.poo.thread.esempi;

import java.util.concurrent.SynchronousQueue;



public class ProdConsSincrono {
	//per semplicità, Producer e Consumer sono classi inner e static
	static class Producer extends Thread{
		private int gen=0;
		private SynchronousQueue<Integer> sq;
		public Producer( String nome, SynchronousQueue<Integer> sq ){
			super(nome); this.sq=sq;
		}
		public void run(){
			while( true ){
				try{
					System.out.println("Producer "+getName()+" trasmette "+gen);
					sq.put(gen); gen++;
				}catch( InterruptedException e ){}
			}
		}
	}//Producer

	static class Consumer extends Thread{
		private SynchronousQueue<Integer> sq;
		public Consumer( String nome, SynchronousQueue<Integer> sq ){
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
		SynchronousQueue<Integer> sq=new SynchronousQueue<>();
		//un produttore e un consumatore usano sq
		Producer p=new Producer("p",sq);
		Consumer q=new Consumer("q",sq);
		p.start(); q.start();
	}//main
}//ProdConsSincrono
