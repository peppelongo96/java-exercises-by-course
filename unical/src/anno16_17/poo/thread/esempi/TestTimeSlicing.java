package anno16_17.poo.thread.esempi;

class Thread1 extends Thread{
	public Thread1( String nome ){
		super(nome);
	}
	public void run(){
		while( true ){
			System.out.println(getName()+" A bad world sir...");	
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){}
		}
	}
}

class Task implements Runnable{
	private String nome;
	public Task( String nome ){
		this.nome=nome;
	}
	public void run(){
		while( true ){
			System.out.println(nome+" Today is raining ok? ");
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){}
		}
	}
}

public class TestTimeSlicing {
	public static void main( String[] args ){
		Thread1 t1=new Thread1("t1");
		
		Task t2=new Task("t2");
		Thread t=new Thread(t2);
		
		t1.start(); t.start();
	}
}
