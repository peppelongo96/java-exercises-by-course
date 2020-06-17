package anno16_17.sisop.TracciaNegozio;

class CassaAutomatica implements Runnable {
	
	private Negozio n;
	
	public CassaAutomatica( Negozio n ) {
		this.n = n;
	}

	@Override
	public void run() {
		while ( true ) {
			try {
				n.scansionaProdotti();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
