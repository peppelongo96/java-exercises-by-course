package anno17_18.ingdelsoftware.progetto.view.builder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import anno17_18.ingdelsoftware.progetto.controller.GiocaGrattacieli5;

class Sud extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private static enum Verso { SINISTRA, DESTRA }	
	private static enum Operazione { START, RESET }
	private int curSol = 1;
	private int nSolMax;
	private int nSolEff;
	private boolean risolto = false;

	private MySudPanel11 sudPanel11;
	private MySudPanel12 sudPanel12;
	private int panel11o12 = 11;

	private MySudPanel2 sudPanel2;
		
	private MySudPanel3 sudPanel3;
	
	Sud() {
		super();
		this.setLayout(new BorderLayout());
		sudPanel11 = new MySudPanel11();
		sudPanel12 = new MySudPanel12();
		sudPanel2 = new MySudPanel2();
		sudPanel3 = new MySudPanel3();
		this.add(sudPanel11, BorderLayout.NORTH);
		this.add(sudPanel2, BorderLayout.CENTER);
		this.add(sudPanel3, BorderLayout.SOUTH);
	}// COSTRUTTORE
	
	public void writeOnInfoBox( String s ) {
		sudPanel3.writeOnInfoBox(s);
	}// writeOnInfoBox
	
	public int get11o22() {
		return this.panel11o12;
	}// get11o22
	
	private void da11a12() {
		this.remove(sudPanel11);
		this.add(sudPanel12, BorderLayout.NORTH);
		this.panel11o12 = 12;
	}// da11a12
	
	private void da12a11() {
		this.remove(sudPanel12);
		this.add(sudPanel11, BorderLayout.NORTH);
		this.panel11o12 = 11;
	}// da12a11
	
	private class MyJTextField extends JTextField {
		
		private static final long serialVersionUID = 1L;
		
		private MyJTextField( String text ) {
			super(text);
			this.setPreferredSize(new Dimension(50,25));
			this.setFont(GUI.GUI_FONT);
			this.setHorizontalAlignment(JTextField.CENTER);
		} // COSTRUTTORE
		
	}// MyJTextField

	private class MySudPanel11 extends JPanel {
		
		private static final long serialVersionUID = 1L;
		
		private JTextField tf111;
		private JTextField tf112;
		
		private int nSolMax;
		
		private MySudPanel11() {
			this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));	
			tf111 = new MyJTextField("N° max soluzioni:");
			tf111.setPreferredSize(null);
			tf111.setBorder(BorderFactory.createEmptyBorder());
			tf111.setEditable(false);
			this.add(tf111);
			tf112 = new MyJTextField("");
			tf112.setPreferredSize(new Dimension(80,30));
			tf112.addKeyListener( new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					int keyCode = e.getKeyCode();
					if ( keyCode != 8 && keyCode != 10 && keyCode != 127) { //KeyCode per DELETE, CANC e INVIO
						String tf112Text =  tf112.getText();
						if ( tf112Text.matches(GUI.REGEX_SOLO_N_INT_POS) ) {
							try {
								nSolMax = Integer.parseInt(tf112Text);
								if ( nSolMax == 0 ) throw new NumberFormatException();
							} catch ( NumberFormatException nfe ) {
								resetNSolMax();
								sudPanel3.writeOnInfoBox("Numero max di soluzioni non valido");
							}
						}
						else {
							resetNSolMax();
							sudPanel3.writeOnInfoBox("Inserire un numero (max di soluzioni) intero positivo");
						}
					}
				}
			});
			this.add(tf112);
		}// COSTRUTTORE
		
		private int getNSolMax() {
			return nSolMax;
		}// getNSolMax

		private void resetNSolMax() {
			tf112.setText("");
		}// resetNSolMax
		
	}// MySudPanel11
	
	private class MySudPanel12 extends JPanel {
		
		private static final long serialVersionUID = 1L;
		
		private JButton frecciaSx;
		private JTextField tf121;
		private String tf121PrecText;
		private JTextField tf122;
		private JTextField tf123;
		private JButton frecciaDx;
		
		private MySudPanel12() {
			this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));	
			frecciaSx = new MyFrecciaSud(Verso.SINISTRA);
			this.add(frecciaSx);
			tf121 = new MyJTextField(Integer.toString(curSol));
			tf121PrecText = tf121.getText();
			tf121.setFont(new Font(null, Font.BOLD, 16));
			tf121.setOpaque(false);
			tf121.setBorder(BorderFactory.createEmptyBorder());
			tf121.setEditable(true);
			tf121.addKeyListener(new cercaSol());
			this.add(tf121);
			tf122 = new MyJTextField("di");
			tf122.setPreferredSize(new Dimension(20,20));
			tf122.setBorder(BorderFactory.createEmptyBorder());
			tf122.setEditable(false);
			this.add(tf122);
			tf123 = new MyJTextField("");
			tf123.setBorder(BorderFactory.createEmptyBorder());
			tf123.setFont(new Font(null, Font.BOLD, 16));
			tf123.setEditable(false);
			this.add(tf123);
			frecciaDx = new MyFrecciaSud(Verso.DESTRA);
			this.add(frecciaDx);	
		}// COSTRUTTORE
		
		private void aggiornaIndiceSol( int n ) {
			tf121.setText(Integer.toString(n));
		}// aggiornaIndice
		
		private void aggiornaNumeroSol( int n ) {
			tf123.setText(Integer.toString(n));
		}// aggiornaIndice

		private class cercaSol extends KeyAdapter {
			public void keyReleased(KeyEvent e) {
				String tf121CurText = tf121.getText();
				if ( tf121CurText.equals("") ) {
					sudPanel3.writeOnInfoBox("Indice di soluzione corrente: "+curSol);
					tf121PrecText = tf121CurText;
				}
				else if ( !tf121CurText.equals(tf121PrecText) ) {
					if ( tf121CurText.matches(GUI.REGEX_SOLO_N_INT_POS) ) {
						int indice = Integer.parseInt(tf121CurText);
						if ( indice >= 1 && indice <= nSolEff ) {
							GiocaGrattacieli5.g.setMemento(GiocaGrattacieli5.ggCT.getMem(indice));
							GiocaGrattacieli5.ggCT.graficaMemento(indice);
							curSol = indice;
							tf121PrecText = tf121CurText;
						}
						else {
							aggiornaIndiceSol(curSol);
							sudPanel3.writeOnInfoBox("Indice di soluzione inserito non presente");
						}
					}
					else {
						aggiornaIndiceSol(curSol);
						sudPanel3.writeOnInfoBox("L'indice di soluzione deve essere un numero intero positivo");
					}
				}
			}
		}// cercaSol

	}// MySudPanel12

	private class MySudPanel2 extends JPanel {
		
		private static final long serialVersionUID = 1L;
		
		private JButton start;
		private JButton reset;
		
		private MySudPanel2() {
			this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));	
			start = new MyStartReset(Operazione.START);
			this.add(start);
			reset = new MyStartReset(Operazione.RESET);
			this.add(reset);
		}// COSTRUTTORE
		
		private void enableStart( boolean b ) {
			start.setEnabled(b);
		}// enableStart
		
	}// MySudPanel2
	
	private class MySudPanel3 extends JPanel {
		
		private static final long serialVersionUID = 1L;
		
		private static final String TITOLO_BORDO = "INFO GIOCO";
		
		private JTextField tf3;
		private Timer timerResetTf3;
		
		private MySudPanel3() {
			this.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));	
			this.setBorder(BorderFactory.createTitledBorder
					(BorderFactory.createLineBorder(Color.BLACK),TITOLO_BORDO));
			tf3 = new MyJTextField("");
			tf3.setEditable(false);
			tf3.setBorder(BorderFactory.createEmptyBorder());
			tf3.setPreferredSize(new Dimension(500,30));
			tf3.setFont(GUI.INFO_FONT);
			this.add(tf3);
			timerResetTf3 = new Timer(3000, new ActionListener() {
	            public void actionPerformed(ActionEvent e) { tf3.setText(""); }});
	        timerResetTf3.setRepeats(false);
		}// COSTRUTTORE
		
		private void writeOnInfoBox( String s ) {
			tf3.setText(s);
			timerResetTf3.restart();
		}// writeOnInfoBox
		
	}// MySudPanel3
	
	private class MyFrecciaSud extends JButton {

		private static final long serialVersionUID = 1L;
		
		private Verso verso;
		
		private MyFrecciaSud( Verso verso ) {
			super();
			this.verso = verso;
			if ( verso == Verso.SINISTRA ) this.setText("<");
			else this.setText(">");
			this.setPreferredSize(new Dimension(50,25));
			this.setFont(GUI.GUI_FONT);
			this.addActionListener(new click());
		}// COSTRUTTORE

		private class click implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( risolto == true ) {
					int curSolPrec = curSol;
					if ( verso == Verso.SINISTRA && curSol > 1 ) curSol--;
					else if ( verso == Verso.DESTRA && curSol < nSolEff ) curSol++;
					if ( curSol != curSolPrec ) {
						GiocaGrattacieli5.g.setMemento(GiocaGrattacieli5.ggCT.getMem(curSol));
						GiocaGrattacieli5.ggCT.graficaMemento(curSol);
						sudPanel12.aggiornaIndiceSol(curSol);
					}
				}
			}
		}// click

	}// MyFrecciaSud

	private class MyStartReset extends JButton {

		private static final long serialVersionUID = 1L;

		private MyStartReset(Operazione op ) {
			this.setFont(GUI.GUI_FONT);
			if ( op == Operazione.START ) {
				this.setText("START");
				this.addActionListener(new clickStart());
			}
			else {
				this.setText("RESET");
				this.addActionListener(new clickReset());
			}
		}// COSTRUTTORE

		private class clickStart implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( GiocaGrattacieli5.gui.grigliaPVvalida() == false ) 
					writeOnInfoBox("Definire ALMENO UNA cella PV");
				else {
					nSolMax = sudPanel11.getNSolMax();
					nSolEff = GiocaGrattacieli5.g.start(nSolMax); 
					if ( nSolEff==0 ) 
						sudPanel3.writeOnInfoBox("NON HO TROVATO SOLUZIONI :(");
					else {	
						da11a12(); panel11o12 = 12;
						GiocaGrattacieli5.gui.rendiPVModificabili(false);
						sudPanel2.enableStart(false);
						sudPanel12.aggiornaNumeroSol(nSolEff);
						risolto = true;
						sudPanel3.writeOnInfoBox("HO TROVATO SOLUZIONI :)");
					}
				}
			}
		}// clickStart

		private class clickReset implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				GiocaGrattacieli5.gq.reset();
				GiocaGrattacieli5.gui.resetGrigliaGUI();
				sudPanel11.resetNSolMax();
				GiocaGrattacieli5.gui.aggiornaGrigliaGUI();
				curSol = 1;
				risolto = false;
				sudPanel12.aggiornaIndiceSol(curSol);
				sudPanel2.enableStart(true);
				GiocaGrattacieli5.gui.rendiPVModificabili(true);
				da12a11(); panel11o12 = 11;
				sudPanel3.writeOnInfoBox("PRONTO PER UNA NUOVA SIMULAZIONE :)");
			}
		}// clickReset
		
	}// MyStartStop

}// Sud
