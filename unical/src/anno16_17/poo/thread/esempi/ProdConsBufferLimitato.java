package anno16_17.poo.thread.esempi;
import anno16_17.poo.utils.BufferLimitato;

public class ProdConsBufferLimitato {
	//per semplicità, Producer e Consumer sono classi inner e static
	static class Producer extends Thread{
		private int seed;
		private BufferLimitatoThreadSafe<Integer> buffer;
		public Producer( String nome, BufferLimitatoThreadSafe<Integer> buffer, int seed ){
			super(nome); this.buffer=buffer; this.seed=seed;
		}
		public void run(){
			while( true ){
				System.out.println("Producer "+getName()+" trasmette "+seed);
				buffer.put(seed); seed++;
			}
		}
	}//Producer

	static class Consumer extends Thread{
		private BufferLimitatoThreadSafe<Integer> buffer;
		public Consumer( String nome, BufferLimitatoThreadSafe<Integer> buffer ){
			super(nome); this.buffer=buffer;
		}
		public void run(){
			while( true ){
				int msg=buffer.take();
				System.out.println("Consumer "+getName()+" riceve "+msg);
			}
		}
	}//Consumer
	
	static class BufferLimitatoThreadSafe<T>{
		private BufferLimitato<T> b;
		public BufferLimitatoThreadSafe( int n ){
			b=new BufferLimitato<>(n);
		}
		public synchronized void put( T x ){
			while( b.isFull() ) //se buffer pieno, blocca il thread
				try{ wait(); }catch( InterruptedException e ){}
			b.put(x);
			notifyAll();
		}//put
		public synchronized T take(){
			while( b.isEmpty() ) //se buffer vuoto, blocca il thread
				try{ wait(); }catch( InterruptedException e ){}
			T x=b.get();
			notifyAll();
			return x;
		}//take
 	}//BufferLimitatoThreadSafe
	
	public static void main( String[] args ){
		BufferLimitatoThreadSafe<Integer> buffer=
				new BufferLimitatoThreadSafe<>(5);
		//Esempio: due produttori e un consumatore comunicano tramite buffer
		Producer p1=new Producer("p1",buffer,0);
		Producer p2=new Producer("p2",buffer,1000000);
		Consumer q=new Consumer("q",buffer);
		p1.start(); p2.start(); q.start();
	}//main
}//ProdConsBufferLimitato
