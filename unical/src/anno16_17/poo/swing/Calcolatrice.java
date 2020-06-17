package anno16_17.poo.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Calcolatrice { //DA COMPLETARE
	
	private static class CalcolatriceGUI extends JFrame{
		JPanel pannelloRis;
		JPanel pannelloNum;
		JTextField spazioRis;
		//...
		String corrente ="";
		//...
		JButton zero;
		JButton uno;
		JButton due;
		JButton tre;
		JButton quattro;
		JButton cinque;
		JButton sei;
		JButton sette;
		JButton otto;
		JButton nove;
		JButton somma;
		JButton sottrai;
		JButton dividi;
		JButton moltiplica;
		JButton calcola;
		JButton punto;
		
		listener listener = new listener();
		
		public CalcolatriceGUI(){
			setTitle("Calcolatrice");
			setSize(400,400);
			setLocation(500,200);
			//...
			pannelloRis=new JPanel();
			JLabel l=new JLabel("Risultato:",JLabel.RIGHT); 
			spazioRis=new JTextField("",12); spazioRis.setEditable(false);
			pannelloRis.add(l);
			pannelloRis.add(spazioRis);
			//...
			pannelloNum=new JPanel();
			pannelloNum.setLayout( new GridLayout(4,4,3,3) );
			sette=new JButton("7"); sette.addActionListener(listener);
			otto=new JButton("8"); otto.addActionListener(listener);
			nove=new JButton("9"); nove.addActionListener(listener);
			dividi=new JButton("/"); dividi.addActionListener(listener);
			quattro=new JButton("4"); quattro.addActionListener(listener);
			cinque=new JButton("5"); cinque.addActionListener(listener);
			sei=new JButton("6"); sei.addActionListener(listener);
			moltiplica=new JButton("*"); moltiplica.addActionListener(listener);
			uno=new JButton("1"); uno.addActionListener(listener);
			due=new JButton("2"); due.addActionListener(listener);			
			tre=new JButton("3"); tre.addActionListener(listener);
			sottrai=new JButton("-"); sottrai.addActionListener(listener);
			zero=new JButton("0"); zero.addActionListener(listener);
			punto=new JButton("."); punto.addActionListener(listener);
			calcola=new JButton("="); calcola.addActionListener(listener);
			somma=new JButton("+"); somma.addActionListener(listener);
			//...
			pannelloNum.add(sette); pannelloNum.add(otto); pannelloNum.add(nove); pannelloNum.add(dividi);
			pannelloNum.add(quattro); pannelloNum.add(cinque); pannelloNum.add(sei); pannelloNum.add(moltiplica);
			pannelloNum.add(uno); pannelloNum.add(due); pannelloNum.add(tre); pannelloNum.add(sottrai);	
			pannelloNum.add(zero); pannelloNum.add(punto); pannelloNum.add(calcola); pannelloNum.add(somma);
			//...
			
			add( pannelloRis, BorderLayout.NORTH ); //aggiunta del pannello p alla JFrame
			add( pannelloNum, BorderLayout.CENTER);
			//...
		}//costruttore GUI
		
		private class listener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == zero)
					corrente+="0";
				else if (e.getSource() == uno)
					corrente+="1";
				else if (e.getSource() == due)
					corrente+="2";
				else if (e.getSource() == tre)
					corrente+="3";
				else if (e.getSource() == quattro)
					corrente+="4";
				else if (e.getSource() == cinque)
					corrente+="5";
				else if (e.getSource() == sei)
					corrente+="6";
				else if (e.getSource() == sette)
					corrente+="7";
				else if (e.getSource() == otto)
					corrente+="8";
				else if (e.getSource() == nove)
					corrente+="9";
				else if (e.getSource() == somma)
					corrente+="+";
				else if (e.getSource() == moltiplica)
					corrente+="*";
				else if (e.getSource() == sottrai)
					corrente+="-";
				else if (e.getSource() == dividi)
					corrente+="/";
				else if (e.getSource() == punto)
					corrente+=".";
				else if (e.getSource() == calcola){
					this.controllaECalcola();
					return;
				}
				
				spazioRis.setText(corrente);
			}
			
			private void controllaECalcola(){
				char op;
				double n1,n2,ris;
				try{
					StringTokenizer st = new StringTokenizer(corrente,"+-/*",true);
					if (st.countTokens()>3) throw new Exception();
					n1 = Double.parseDouble(st.nextToken());
					op = st.nextToken().charAt(0);
					n2 = Double.parseDouble(st.nextToken());
					switch (op){
						case '+': ris=n1+n2; break;
						case '-': ris=n1-n2; break;
						case '*': ris=n1*n2; break;
						default: ris=n1/n2; break;
					}
					spazioRis.setText(Double.toString(ris));
					corrente = "";
				}catch (Exception e){errore();}
			}
			
			private void errore(){
				corrente=" "; 
				JOptionPane.showMessageDialog(null, "OPERAZIONE NON VALIDA");
				spazioRis.setText(null);
			}
			
		}
	}
	
	public static void main( String[] args ){
		CalcolatriceGUI fe=new CalcolatriceGUI();
		fe.setVisible(true);
	}
}
