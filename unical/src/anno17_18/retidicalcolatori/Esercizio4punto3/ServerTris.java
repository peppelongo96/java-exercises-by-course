/**
 * 
 */
package anno17_18.retidicalcolatori.Esercizio4punto3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.StringTokenizer;

import anno17_18.retidicalcolatori.Esercizio4punto3.Tris.Mossa;
import anno17_18.retidicalcolatori.Esercizio4punto3.Tris.Responso;

/**
 * @author Giuseppe Longo
 *
 */
class ServerTris {

	private LinkedList<Socket> listaGiocatoriDisponibili = new LinkedList<>();
	private LinkedList<BufferedReader> listaIn = new LinkedList<>();
	private LinkedList<PrintWriter> listaOut = new LinkedList<>();
	
	
	private String stampaListaGiocatoriDisponibili() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for ( Socket s : listaGiocatoriDisponibili ) 
			sb.append(s.getPort()+", ");
		sb.append("]");
		return sb.toString();
	}
	
	private class GestisciGiocatore extends Thread {
		
		private Socket s;
		private BufferedReader in;
		private PrintWriter out;		
		
		private GestisciGiocatore( Socket s) {
			this.s = s;
		}
		
		@Override
		public void run() {
			try {
				in = new BufferedReader (new InputStreamReader(this.s.getInputStream()));
				out = new PrintWriter (this.s.getOutputStream(),true);
				out.println("Ecco la lista degli utenti attualmente diponibili:");
				out.println(stampaListaGiocatoriDisponibili());
				out.println("Inserisci porta-giocatore scelta oppure A per attendere che qualcuno giochi con te: ");
				String scelta = in.readLine();
				if ( scelta.equals("A") ) {
					out.println("-> Attendi avversario..."); 
					listaGiocatoriDisponibili.add(s);
					listaIn.add(in); listaOut.add(out);
				} else {
					for ( Socket s : listaGiocatoriDisponibili ) {
						if ( s.getPort() == Integer.parseInt(scelta) ) {
							int posS = listaGiocatoriDisponibili.indexOf(s);
							new Partita(this.s.getPort(), s.getPort(), in, listaIn.remove(posS), out, listaOut.remove(posS)).start();
						}
					} 
				}
				
			} catch ( Exception e) { System.out.println(e); }
			
		}
	}
	
	private class Partita extends Thread {

		private Tris tris;
		private String turno = "G1";
		private Responso statoPartita = Responso.ANCORA_MOSSE;

		private BufferedReader inG1, inG2;
		private PrintWriter outG1, outG2;
		private int portaG1, portaG2;

		private Partita (int portaG1, int portaG2, BufferedReader inG1, BufferedReader inG2, PrintWriter outG1, PrintWriter outG2) {
			tris = new Tris();
			this.inG1 = inG1;
			this.inG2 = inG2;
			this.outG1 = outG1;
			this.outG2 = outG2;
			this.portaG1 = portaG1;
			this.portaG2 = portaG2;
		}

		public void run() {
			
			try {
				
				outG2.println("GO");
				
				outG1.println("PARTITA TRA G1(X):"+portaG1+" E G2(O):"+portaG2);
				outG2.println("PARTITA TRA G1(X):"+portaG1+" E G2(O):"+portaG2);
				outG1.println(tris); 
				outG2.println(tris);
				
				outG1.println(statoPartita.toString()); 
				outG2.println(statoPartita.toString());
				 
				while ( statoPartita==Responso.ANCORA_MOSSE ) {
					outG1.println("TOCCA A "+turno); outG2.println("TOCCA A "+turno);
					outG1.println(turno); outG2.println(turno);
					if ( turno.equals("G1") ) mossaG1();
					else mossaG2();
					outG1.println(tris); 
					outG2.println(tris);
					statoPartita = tris.controlloPartita();
					outG1.println(statoPartita); 
					outG2.println(statoPartita);
					if ( turno.equals("G1") ) turno = "G2";
					else turno = "G1";
				}
				
				if ( statoPartita==Responso.VINCE_X ) { //vinceG1
					outG1.println("VINCE IL GIOCATORE G1(x)"); 
					outG2.println("VINCE IL GIOCATORE G1(x)"); 
				} else if ( statoPartita==Responso.VINCE_O ) { //vinceG2
					outG1.println("VINCE IL GIOCATORE G2(O)"); 
					outG2.println("VINCE IL GIOCATORE G2(O)"); 
				} else {
					outG1.println("PARTITA FINITA IN PARI"); 
					outG2.println("PARTITA FINITA IN PARI"); 
				}
				
				return;
					
			} catch ( Exception e ) { System.out.println(e); }
		}
		
		private void mossaG1() {
			String mossa;
			int riga, colonna;
			try {
				outG1.println("Inserisci coordinate cella (riga,colonna)");
				mossa = inG1.readLine();
				StringTokenizer st = new StringTokenizer(mossa, ",");
				riga = Integer.parseInt(st.nextToken());
				colonna = Integer.parseInt(st.nextToken());
				tris.inserisciMossa(Mossa.X, new PosMossa(riga, colonna));
			} catch ( Exception e ) { System.out.println("1"+e); } 
		}
		
		private void mossaG2() {
			String mossa;
			int riga, colonna;
			try {
				outG2.println("Inserisci coordinate cella (riga,colonna)");
				mossa = inG2.readLine();
				StringTokenizer st = new StringTokenizer(mossa, ",");
				riga = Integer.parseInt(st.nextToken());
				colonna = Integer.parseInt(st.nextToken());
				tris.inserisciMossa(Mossa.O, new PosMossa(riga, colonna));
			} catch ( Exception e ) { System.out.println("2"+e); } 
		}
	}
	
	
	/////////////////////////////////////////////////////////////////
	
	static final int porta = 8196;

	private static ServerSocket ss;
	
	private void avviaServerTris() {
		try {
			ss = new ServerSocket(porta);
			while ( true ) {
				Socket s = ss.accept();
				new GestisciGiocatore(s).start();
			}
		} catch ( Exception e ) { System.out.println(e); }
	}


	public static void main(String[] args) {
		new ServerTris().avviaServerTris();
	}

}
