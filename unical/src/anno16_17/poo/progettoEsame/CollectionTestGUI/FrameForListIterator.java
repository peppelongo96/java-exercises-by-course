package anno16_17.poo.progettoEsame.CollectionTestGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.SceltaList;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.SceltaTipo;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.metodiListIterator;



class FrameForListIterator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lRichiesta = new JLabel();
	private JTextArea taRisposta = new JTextArea();
	private JScrollPane sPaneRisposta;
	private JPanel panelBottoni = new JPanel();
	private JButton bOk = new JButton("OK");
	private JButton bAnnulla = new JButton("ANNULLA");

	private FrameOfMethods fom;
	
	FrameForListIterator(FrameOfMethods fom, metodiListIterator mli) {
		
		this.fom = fom;

		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				if ( mli==null) {
					fom.panelListIterator.remove(fom.panelListIteratorON);
					fom.panelListIterator.repaint();
					fom.panelListIterator.revalidate();
					fom.panelListIterator.add(fom.bAttivaListIterator);
					fom.panelListIterator.add(fom.cbSpuntaLIPOS);
					fom.cbSpuntaLIPOS.setSelected(false);
					fom.panelIterator.getComponent(0).setEnabled(true);
					for (int i = 0; i < fom.bListIterator.length; i++) 
						if ( i!=5 && i!=7 && i!=8 ) fom.bListIterator[i].setEnabled(true);
				}
				else {
					for (int i = 0; i < fom.bListIterator.length; i++) 
						fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
				}

				for (int i = 0; i < fom.bCollectionMethods.length; i++) 
					fom.bCollectionMethods[i].setEnabled(true);
				
				for (int i = 0; i < fom.bListMethods.length; i++) 
					fom.bListMethods[i].setEnabled(true);
				
				if ( Integer.parseInt(fom.ctg.toStringSize())==0 ) {
					fom.bListMethods[6].setEnabled(false);
					fom.bListMethods[9].setEnabled(false);
					fom.bListMethods[10].setEnabled(false);	
				}
				
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					for (int i = 0; i < fom.bLinkedListMethods.length; i++) 
						fom.bLinkedListMethods[i].setEnabled(true);
					if ( Integer.parseInt(fom.ctg.toStringSize())==0 ) {
						fom.bLinkedListMethods[0].setEnabled(false);
						fom.bLinkedListMethods[1].setEnabled(false);
						fom.bLinkedListMethods[2].setEnabled(false);
						fom.bLinkedListMethods[3].setEnabled(false);
					}
				}
			}
		} );

		this.setSize(450, 140);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		if ( mli==null ) {
			lRichiesta.setText("INSERISCI POSIZIONE PUNTATORE:");
			bOk.addActionListener(new listenerFrameDaPosizione());
			bAnnulla.addActionListener(new listenerFrameDaPosizione());
		}
		else if ( mli==metodiListIterator.SET ) {
			this.setTitle("Set (List-Iterator)");
			lRichiesta.setText("INSERISCI ELEMENTO DA SOSTITUIRE:");
			bOk.addActionListener(new listenerFrameforSet());
			bAnnulla.addActionListener(new listenerFrameforSet());
		}
		else if ( mli==metodiListIterator.ADD ) {
			this.setTitle("Add (List-Iterator)");
			lRichiesta.setText("DEFINISCI ELEMENTO DA AGGIUNGERE:");
			bOk.addActionListener(new listenerFrameforAdd());
			bAnnulla.addActionListener(new listenerFrameforAdd());
		}

		this.getContentPane().setLayout( new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		lRichiesta.setFont( new Font(null, Font.BOLD, 18));
		this.getContentPane().add(lRichiesta);
		taRisposta.setFont( new Font(null, Font.ITALIC, 16) );
		sPaneRisposta = new JScrollPane(taRisposta);
		this.getContentPane().add(sPaneRisposta);

		panelBottoni.add(bOk);
		panelBottoni.add(bAnnulla);
		this.getContentPane().add(panelBottoni);

	}//COSTRUTTORE

	private class listenerFrameDaPosizione implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if ( e.getSource()==bOk ) {
				if ( taRisposta.getText().equals("") || taRisposta.getText().matches("0+") ) {
					fom.ctg.creaListIterator();
					fom.taLista.setText(fom.ctg.toStringListaIterata);
					fom.ffli.setVisible(false);
					for (int i = 0; i < fom.bCollectionMethods.length; i++) {
						fom.bCollectionMethods[i].setEnabled(true);
					}
					for (int i = 0; i < fom.bListMethods.length; i++) {
						fom.bListMethods[i].setEnabled(true);
					}
					for (int i = 0; i < fom.bListIterator.length; i++) {
						fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
					}
				}
				else {
					try {
						int pos = Integer.parseInt(taRisposta.getText());
						fom.ctg.creaListIterator(pos);
						fom.taLista.setText(fom.ctg.toStringListaIterata);
						fom.ffli.setVisible(false);
						for (int i = 0; i < fom.bCollectionMethods.length; i++) {
							fom.bCollectionMethods[i].setEnabled(true);
						}
						for (int i = 0; i < fom.bListMethods.length; i++) {
							fom.bListMethods[i].setEnabled(true);
						}
						for (int i = 0; i < fom.bListIterator.length; i++) {
							fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
							if ( i==5 ) fom.bListIterator[i].setEnabled(true); //attiva previous
							if ( pos==Integer.parseInt(fom.ctg.toStringSize()) && i==3 ) //disattiva next se pos=size
								fom.bListIterator[i].setEnabled(false);
						}
					}
					catch ( IndexOutOfBoundsException iobe ) {
						JOptionPane.showMessageDialog(null, "IL CURSORE DEVE ESSERE COMPRESO TRA 0 E SIZE", "ERRORE", JOptionPane.ERROR_MESSAGE);
					}
					catch ( NumberFormatException nfe ) {
						JOptionPane.showMessageDialog(null, "SONO ACCETTATI SOLO INTERI", "ERRORE", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else {
				fom.panelListIterator.remove(fom.panelListIteratorON);
				fom.panelListIterator.repaint();
				fom.panelListIterator.revalidate();
				fom.panelListIterator.add(fom.bAttivaListIterator);
				fom.cbSpuntaLIPOS.setSelected(false);
				fom.panelListIterator.add(fom.cbSpuntaLIPOS);
				fom.panelIterator.getComponent(0).setEnabled(true);
				fom.ffli.setVisible(false);
				for (int i = 0; i < fom.bCollectionMethods.length; i++)
					fom.bCollectionMethods[i].setEnabled(true);
				for (int i = 0; i < fom.bListMethods.length; i++)
					fom.bListMethods[i].setEnabled(true);
				for (int i = 0; i < fom.bListIterator.length; i++) {
					fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
				}
			}

			if ( Integer.parseInt(fom.ctg.toStringSize())==0 ) {
				fom.bListMethods[6].setEnabled(false);
				fom.bListMethods[9].setEnabled(false);
				fom.bListMethods[10].setEnabled(false);	
			}
			
			if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
				for (int i = 0; i < fom.bLinkedListMethods.length; i++) 
					fom.bLinkedListMethods[i].setEnabled(true);
				if ( Integer.parseInt(fom.ctg.toStringSize())==0 ) {
					fom.bLinkedListMethods[0].setEnabled(false);
					fom.bLinkedListMethods[1].setEnabled(false);
					fom.bLinkedListMethods[2].setEnabled(false);
					fom.bLinkedListMethods[3].setEnabled(false);
				}
			}
			
		}
		
	}//listenerFrameDaPosizione
	

	private class listenerFrameforSet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if ( e.getSource()==bOk ) {
				try {
					if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
						@SuppressWarnings("unchecked")
						CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
						ctgI.listIteratorSet( Integer.parseInt(taRisposta.getText()) );
						fom.taCorrente.setText(taRisposta.getText());
						fom.taRisultato.setText(null);
					}
					else {
						String risposta = taRisposta.getText();
						if ( !risposta.matches("\".+\"|null") ) throw new Exception();
						else {
							StringTokenizer st = new StringTokenizer(risposta, "\" ");
							String daSettare;
							if ( st.countTokens()==0 ) {
								if ( taRisposta.getText().equals("null") ) daSettare= null;
								else daSettare = "   ";
							}
							else daSettare = st.nextToken();
							@SuppressWarnings("unchecked")
							CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
							ctgS.listIteratorSet(daSettare);
							fom.taCorrente.setText(daSettare);
							fom.taRisultato.setText(null);
						}
					}

					fom.taLista.setText(fom.ctg.toStringListaIterata);
					fom.ffli.setVisible(false);
					for (int i = 0; i < fom.bCollectionMethods.length; i++) {
						fom.bCollectionMethods[i].setEnabled(true);
					}
					for (int i = 0; i < fom.bListMethods.length; i++) {
						fom.bListMethods[i].setEnabled(true);
					}
					for (int i = 0; i < fom.bListIterator.length; i++) {
						fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
						if ( i==7||i==8 ) fom.bListIterator[i].setEnabled(false); //disattiva remove e set
					}

				} catch ( Exception ex ) {
					JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
							"ERRORE", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				fom.ffli.setVisible(false);
				for (int i = 0; i < fom.bListIterator.length; i++) 
					fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);

				for (int i = 0; i < fom.bCollectionMethods.length; i++) 
					fom.bCollectionMethods[i].setEnabled(true);

				for (int i = 0; i < fom.bListMethods.length; i++) 
					fom.bListMethods[i].setEnabled(true);
				
				if ( Integer.parseInt(fom.ctg.toStringSize())==0 ) {
					fom.bListMethods[6].setEnabled(false);
					fom.bListMethods[9].setEnabled(false);
					fom.bListMethods[10].setEnabled(false);	
				}
				
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					for (int i = 0; i < fom.bLinkedListMethods.length; i++) 
						fom.bLinkedListMethods[i].setEnabled(true);
					if ( Integer.parseInt(fom.ctg.toStringSize())==0 ) {
						fom.bLinkedListMethods[0].setEnabled(false);
						fom.bLinkedListMethods[1].setEnabled(false);
						fom.bLinkedListMethods[2].setEnabled(false);
						fom.bLinkedListMethods[3].setEnabled(false);
					}
				}
			}
			
		}

	}//listenerFrameforSet

	private class listenerFrameforAdd implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if ( e.getSource()==bOk ) {
				try {
					if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
						@SuppressWarnings("unchecked")
						CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
						ctgI.listIteratorAdd(Integer.parseInt(taRisposta.getText()));
						fom.taCorrente.setText("?");
						fom.taRisultato.setText(null);
					}
					else {
						String risposta = taRisposta.getText();
						if ( !risposta.matches("\".+\"|null") ) throw new Exception();
						else {
							StringTokenizer st = new StringTokenizer(risposta, "\" ");
							String daInserire;
							if ( st.countTokens()==0 ) {
								if ( taRisposta.getText().equals("null") ) daInserire = null;
								else daInserire = "   ";
							}
							else daInserire = st.nextToken();
							@SuppressWarnings("unchecked")
							CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
							ctgS.listIteratorAdd(daInserire);
							fom.taCorrente.setText("?");
							fom.taRisultato.setText(null);
						}
					}
					fom.taLista.setText(fom.ctg.toStringListaIterata);
					fom.ffli.setVisible(false);
					for (int i = 0; i < fom.bCollectionMethods.length; i++) {
						fom.bCollectionMethods[i].setEnabled(true);
					}
					for (int i = 0; i < fom.bListMethods.length; i++) {
						fom.bListMethods[i].setEnabled(true);
					}
					for (int i = 0; i < fom.bListIterator.length; i++) {
						fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
						if ( i==5 ) fom.bListIterator[i].setEnabled(true); //abilita previous
						if ( i==7||i==8 ) fom.bListIterator[i].setEnabled(false); //disattiva remove e set
					}

				} catch ( Exception ex ) {
					JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+"!", 
							"ERRORE", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				fom.ffli.setVisible(false);
				for (int i = 0; i < fom.bListIterator.length; i++) 
					fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);

				for (int i = 0; i < fom.bCollectionMethods.length; i++) 
					fom.bCollectionMethods[i].setEnabled(true);

				for (int i = 0; i < fom.bListMethods.length; i++) 
					fom.bListMethods[i].setEnabled(true);
				
				if ( Integer.parseInt(fom.ctg.toStringSize())==0 ) {
					fom.bListMethods[6].setEnabled(false);
					fom.bListMethods[9].setEnabled(false);
					fom.bListMethods[10].setEnabled(false);	
				}
				
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					for (int i = 0; i < fom.bLinkedListMethods.length; i++) 
						fom.bLinkedListMethods[i].setEnabled(true);
					if ( Integer.parseInt(fom.ctg.toStringSize())==0 ) {
						fom.bLinkedListMethods[0].setEnabled(false);
						fom.bLinkedListMethods[1].setEnabled(false);
						fom.bLinkedListMethods[2].setEnabled(false);
						fom.bLinkedListMethods[3].setEnabled(false);
					}
				}
			}
		}

	}//listenerFrameforAdd

}//FrameforListIterator