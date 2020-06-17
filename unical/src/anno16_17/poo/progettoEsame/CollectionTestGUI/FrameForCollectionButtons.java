package anno16_17.poo.progettoEsame.CollectionTestGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import anno16_17.poo.progettoEsame.*;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.SceltaList;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.SceltaTipo;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.metodiCollection;



class FrameForCollectionButtons extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titolo;
	private int sizeX, sizeY;
	private JLabel lRichiesta = new JLabel();
	private JTextArea taRisposta = new JTextArea();
	private JScrollPane sPaneRisposta;
	private JPanel panelBottoni = new JPanel();
	private JButton bOk = new JButton("OK");
	private JButton bAnnulla = new JButton("ANNULLA");

	private JPanel panelSceltaCollection = new JPanel();
	@SuppressWarnings("rawtypes")
	private JComboBox cbSceltaCollection;

	private FrameOfMethods fom;

	FrameForCollectionButtons(FrameOfMethods fom, metodiCollection mc){

		this.fom = fom;

		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				if ( fom.iteratorAttivo ) {
					for (int i = 0; i < fom.bIterator.length; i++) 
						fom.bIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
				}
				else if ( fom.listIteratorAttivo ) {
					for (int i = 0; i < fom.bListIterator.length; i++) 
						fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
				}
				else {
					fom.panelIterator.getComponent(0).setEnabled(true);
					fom.panelListIterator.getComponent(0).setEnabled(true);
					fom.panelListIterator.getComponent(1).setEnabled(true);
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


		this.setResizable(false);

		this.getContentPane().setLayout( new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		lRichiesta.setFont( new Font(null, Font.BOLD, 18));
		this.getContentPane().add(lRichiesta);
		taRisposta.setFont( new Font(null, Font.ITALIC, 16) );
		sPaneRisposta = new JScrollPane(taRisposta, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.getContentPane().add(sPaneRisposta);

		if ( mc==metodiCollection.ADD ) {
			titolo = "Add";
			sizeX = 450; sizeY = 160;
			lRichiesta.setText("DEFINISCI ELEMENTO DA AGGIUNGERE:");
			bOk.addActionListener(new listenerFrameForADD());
		}
		else if ( mc==metodiCollection.ADD_ALL ) {
			titolo = "AddAll";
			sizeX = 550; sizeY = 190;
			lRichiesta.setText("DEFINISCI COLLEZIONE DA AGGIUNGERE:");
			String[] listaSceltaCollection = { "ArrayList<"+CollectionToGrafica.tipoForButtonsBoxed+">",
					"LinkedList<"+CollectionToGrafica.tipoForButtonsBoxed+">", "..." };
			cbSceltaCollection = new JComboBox<>(listaSceltaCollection);
			panelSceltaCollection.add(cbSceltaCollection);
			this.getContentPane().add(panelSceltaCollection);
			bOk.addActionListener(new listenerFrameForADD_ALL());
		}
		else if ( mc==metodiCollection.CONTAINS ) {
			titolo = "Contains";
			sizeX = 450; sizeY = 160;
			lRichiesta.setText("DEFINISCI ELEMENTO DA CERCARE:");
			bOk.addActionListener(new listenerFrameForCONTAINS());
		}
		else if ( mc==metodiCollection.CONTAINS_ALL ) {
			titolo = "ContainsAll";
			sizeX = 550; sizeY = 190;
			lRichiesta.setText("DEFINISCI COLLEZIONE DA CERCARE:");
			String[] listaSceltaCollection = { "ArrayList<"+CollectionToGrafica.tipoForButtonsBoxed+">",
					"LinkedList<"+CollectionToGrafica.tipoForButtonsBoxed+">", "..." };
			cbSceltaCollection = new JComboBox<>(listaSceltaCollection);
			panelSceltaCollection.add(cbSceltaCollection);
			this.getContentPane().add(panelSceltaCollection);
			bOk.addActionListener(new listenerFrameForCONTAINS_ALL());
		}
		else if ( mc==metodiCollection.REMOVE ) {
			titolo = "Remove";
			sizeX = 450; sizeY = 160;
			lRichiesta.setText("DEFINISCI ELEMENTO DA ELIMINARE:");
			bOk.addActionListener(new listenerFrameForREMOVE());
		}
		else if ( mc==metodiCollection.REMOVE_ALL ) {
			titolo = "RemoveAll";
			sizeX = 550; sizeY = 190;
			lRichiesta.setText("DEFINISCI COLLEZIONE DA ELIMINARE:");
			String[] listaSceltaCollection = { "ArrayList<"+CollectionToGrafica.tipoForButtonsBoxed+">",
					"LinkedList<"+CollectionToGrafica.tipoForButtonsBoxed+">", "..." };
			cbSceltaCollection = new JComboBox<>(listaSceltaCollection);
			panelSceltaCollection.add(cbSceltaCollection);
			this.getContentPane().add(panelSceltaCollection);
			bOk.addActionListener(new listenerFrameForREMOVE_ALL());
		}
		else if ( mc==metodiCollection.RETAIN_ALL ) {
			titolo = "RetainAll";
			sizeX = 550; sizeY = 190;
			lRichiesta.setText("DEFINISCI COLLEZIONE DA MANTENERE:");
			String[] listaSceltaCollection = { "ArrayList<"+CollectionToGrafica.tipoForButtonsBoxed+">",
					"LinkedList<"+CollectionToGrafica.tipoForButtonsBoxed+">", "..." };
			cbSceltaCollection = new JComboBox<>(listaSceltaCollection);
			panelSceltaCollection.add(cbSceltaCollection);
			this.getContentPane().add(panelSceltaCollection);
			bOk.addActionListener(new listenerFrameForRETAIN_ALL());
		}
		else if ( mc==metodiCollection.TO_ARRAY_T ) {
			titolo = "ToArray";
			sizeX = 450; sizeY = 160;
			lRichiesta.setText("DEFINISCI NUOVO ARRAY DA CREARE:");
			taRisposta.setText(" new ");
			bOk.addActionListener(new listenerFrameForTO_ARRAY());
		}

		this.setSize(sizeX, sizeY);
		this.setLocationRelativeTo(null);
		this.setTitle(titolo);

		panelBottoni.add(bOk);
		bAnnulla.addActionListener(this);
		panelBottoni.add(bAnnulla);
		this.getContentPane().add(panelBottoni);

		fom.taRisultato.setText("");

	}//COSTRUTTORE

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( fom.iteratorAttivo ) {
			for (int i = 0; i < fom.bIterator.length; i++) 
				fom.bIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
		}
		else if ( fom.listIteratorAttivo ) {
			for (int i = 0; i < fom.bListIterator.length; i++) 
				fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
		}
		else {
			fom.panelIterator.getComponent(0).setEnabled(true);
			fom.panelListIterator.getComponent(0).setEnabled(true);
			fom.panelListIterator.getComponent(1).setEnabled(true);
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
	
		super.setVisible(false);

	}//listener ANNULLA

	private class listenerFrameForADD implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String risposta = taRisposta.getText().replaceAll(" ", "");
			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					fom.taRisultato.setText(ctgI.toStringAdd(Integer.parseInt(risposta)));
					fom.taCorrente.setText("?");
				}
				else {
					if ( !risposta.matches("(\"(\\d+|\\w+)\"|null)") ) throw new Exception();
					else {
						StringTokenizer st = new StringTokenizer(risposta, "\"");
						String daInserire;
						if ( st.countTokens()==0 ) {
							if ( taRisposta.getText().equals("null") ) daInserire = null;
							else daInserire = "   ";
						}
						else daInserire = st.nextToken();
						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						fom.taRisultato.setText(ctgS.toStringAdd(daInserire));
						fom.taCorrente.setText("?");
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}


			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.ffcb.setVisible(false);
			if ( fom.iteratorAttivo ) {
				for (int i = 0; i < fom.bIterator.length; i++) 
					fom.bIterator[i].setEnabled(false);
			}
			else if ( fom.listIteratorAttivo ) {
				for (int i = 0; i < fom.bListIterator.length; i++) 
					fom.bListIterator[i].setEnabled(false);
			}
			else {
				fom.panelIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(1).setEnabled(true);
			}
			for (int i = 0; i < fom.bCollectionMethods.length; i++) 
				fom.bCollectionMethods[i].setEnabled(true);

			for (int i = 0; i < fom.bListMethods.length; i++) 
				fom.bListMethods[i].setEnabled(true);
			
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
		
	}//listenerFrameForADD

	private class listenerFrameForADD_ALL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String risposta = taRisposta.getText().replaceAll(" ", "");
			if ( risposta.length()>0 && risposta.charAt(risposta.length()-1)!=',' )
				risposta+=",";

			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {

					Collection<Integer> scelta;

					if ( cbSceltaCollection.getSelectedIndex()==0 )
						scelta = new ArrayList<>();
					else scelta = new LinkedList<>();

					StringTokenizer st = new StringTokenizer(taRisposta.getText(), " ,");
					while ( st.hasMoreTokens() ) {
						scelta.add(Integer.parseInt(st.nextToken()));
					}

					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					fom.taRisultato.setText(ctgI.toStringAddAll(scelta));
					fom.taCorrente.setText("?");
				}
				else {
					if ( !risposta.matches( "(\"(\\d+|\\w+)\",|null,)+" ) ) throw new IllegalArgumentException();
					else {
						Collection<String> scelta;

						if ( cbSceltaCollection.getSelectedIndex()==0 )
							scelta = new ArrayList<>();
						else scelta = new LinkedList<>();

						StringTokenizer st = new StringTokenizer(risposta, ",");

						while ( st.hasMoreTokens() ){
							String token = st.nextToken().replaceAll("\"", "");
							if ( token==null ) scelta.add("   ");
							else if ( token.equals("null") ) scelta.add(null);
							else scelta.add(token);
						} 

						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						fom.taRisultato.setText(ctgS.toStringAddAll(scelta));
						fom.taCorrente.setText("?");
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.ffcb.setVisible(false);
			if ( fom.iteratorAttivo ) {
				for (int i = 0; i < fom.bIterator.length; i++) 
					fom.bIterator[i].setEnabled(false);
			}
			else if ( fom.listIteratorAttivo ) {
				for (int i = 0; i < fom.bListIterator.length; i++) 
					fom.bListIterator[i].setEnabled(false);
			}
			else {
				fom.panelIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(1).setEnabled(true);
			}

			for (int i = 0; i < fom.bCollectionMethods.length; i++) 
				fom.bCollectionMethods[i].setEnabled(true);

			for (int i = 0; i < fom.bListMethods.length; i++) 
				fom.bListMethods[i].setEnabled(true);
			
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

	}//listenerFrameForADD_All

	private class listenerFrameForCONTAINS implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String risposta = taRisposta.getText().replaceAll(" ", "");
			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					fom.taRisultato.setText(ctgI.toStringContains(Integer.parseInt(risposta)));
				}
				else {
					if ( !risposta.matches("(\"(\\d+|\\w+)\"|null)") ) throw new Exception();
					else {
						StringTokenizer st = new StringTokenizer(risposta, "\"");
						String daInserire;
						if ( st.countTokens()==0 ) {
							if ( taRisposta.getText().equals("null") ) daInserire = null;
							else daInserire = "   ";
						}
						else daInserire = st.nextToken();
						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						fom.taRisultato.setText(ctgS.toStringContains(daInserire));
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}
			fom.taCorrente.setText("?");
			fom.ffcb.setVisible(false);
			if ( fom.iteratorAttivo ) {
				for (int i = 0; i < fom.bIterator.length; i++) 
					fom.bIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
			}
			else if ( fom.listIteratorAttivo ) {
				for (int i = 0; i < fom.bListIterator.length; i++) 
					fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
			}
			else {
				fom.panelIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(1).setEnabled(true);
			}
			for (int i = 0; i < fom.bCollectionMethods.length; i++) 
				fom.bCollectionMethods[i].setEnabled(true);
			
			for (int i = 0; i < fom.bListMethods.length; i++) 
				fom.bListMethods[i].setEnabled(true);
			
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
	}//listenerFrameForCONTAINS

	private class listenerFrameForCONTAINS_ALL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String risposta = taRisposta.getText().replaceAll(" ", "");
			if ( risposta.length()>0 && risposta.charAt(risposta.length()-1)!=',' )
				risposta+=",";

			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {

					Collection<Integer> scelta;

					if ( cbSceltaCollection.getSelectedIndex()==0 )
						scelta = new ArrayList<>();
					else scelta = new LinkedList<>();

					StringTokenizer st = new StringTokenizer(risposta, ",");
					while ( st.hasMoreTokens() ) {
						scelta.add(Integer.parseInt(st.nextToken()));
					}

					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					fom.taRisultato.setText(ctgI.toStringContainsAll(scelta));
					fom.taCorrente.setText("?");
				}
				else {
					if ( !risposta.matches( "(\"(\\d+|\\w+)\",|null,)+" ) ) throw new IllegalArgumentException();
					else {
						Collection<String> scelta;

						if ( cbSceltaCollection.getSelectedIndex()==0 )
							scelta = new ArrayList<>();
						else scelta = new LinkedList<>();

						StringTokenizer st = new StringTokenizer(risposta, ",");

						while ( st.hasMoreTokens() ){
							String token = st.nextToken().replaceAll("\"", "");
							if ( token==null ) scelta.add("   ");
							else if ( token.equals("null") ) scelta.add(null);
							else scelta.add(token);
						} 

						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						fom.taRisultato.setText(ctgS.toStringContainsAll(scelta));
						fom.taCorrente.setText("?");
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.ffcb.setVisible(false);
			if ( fom.iteratorAttivo ) {
				for (int i = 0; i < fom.bIterator.length; i++) 
					fom.bIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
			}
			else if ( fom.listIteratorAttivo ) {
				for (int i = 0; i < fom.bListIterator.length; i++) 
					fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
			}
			else {
				fom.panelIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(1).setEnabled(true);
			}

			for (int i = 0; i < fom.bCollectionMethods.length; i++) 
				fom.bCollectionMethods[i].setEnabled(true);

			for (int i = 0; i < fom.bListMethods.length; i++) 
				fom.bListMethods[i].setEnabled(true);
			
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

	}//listenerFrameForCONTAINS_All

	private class listenerFrameForREMOVE implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			boolean effettuata;
			String risposta = taRisposta.getText().replaceAll(" ", "");
			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					effettuata = ctgI.remove(Integer.parseInt(risposta));
					fom.taRisultato.setText(Boolean.toString(effettuata));
					fom.taCorrente.setText("?");
				}
				else {
					if ( !risposta.matches("(\"(\\d+|\\w+)\"|null)") ) throw new Exception();
					else {
						StringTokenizer st = new StringTokenizer(risposta, "\"");
						String daRimuovere;
						if ( st.countTokens()==0 ) {
							if ( taRisposta.getText().equals("null") ) daRimuovere = null;
							else daRimuovere = "   ";
						}
						else daRimuovere= st.nextToken();
						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						effettuata = ctgS.remove(daRimuovere);
						fom.taRisultato.setText(Boolean.toString(effettuata));
						fom.taCorrente.setText("?");
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.ffcb.setVisible(false);
			if ( fom.iteratorAttivo ) {
				for (int i = 0; i < fom.bIterator.length; i++) {
					if ( effettuata ) fom.bIterator[i].setEnabled(false);
					else fom.bIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
				}
			}
			else if ( fom.listIteratorAttivo ) {
				for (int i = 0; i < fom.bListIterator.length; i++) 
					if ( effettuata ) fom.bListIterator[i].setEnabled(false);
					else fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);			}
			else {
				fom.panelIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(1).setEnabled(true);
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
	}//listenerFrameForREMOVE

	private class listenerFrameForREMOVE_ALL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean effettuata;
			String risposta = taRisposta.getText().replaceAll(" ", "");
			if ( risposta.length()>0 && risposta.charAt(risposta.length()-1)!=',' )
				risposta+=",";

			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {

					Collection<Integer> scelta;

					if ( cbSceltaCollection.getSelectedIndex()==0 )
						scelta = new ArrayList<>();
					else scelta = new LinkedList<>();

					StringTokenizer st = new StringTokenizer(risposta, ",");
					while ( st.hasMoreTokens() ) {
						scelta.add(Integer.parseInt(st.nextToken()));
					}

					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					effettuata = ctgI.removeAll(scelta);
					fom.taRisultato.setText(Boolean.toString(effettuata));
					fom.taCorrente.setText("?");
				}
				else {
					if ( !risposta.matches( "(\"(\\d+|\\w+)\",|null,)+" ) ) throw new IllegalArgumentException();
					else {
						Collection<String> scelta;

						if ( cbSceltaCollection.getSelectedIndex()==0 )
							scelta = new ArrayList<>();
						else scelta = new LinkedList<>();

						StringTokenizer st = new StringTokenizer(risposta, ",");

						while ( st.hasMoreTokens() ){
							String token = st.nextToken().replaceAll("\"", "");
							if ( token==null ) scelta.add("   ");
							else if ( token.equals("null") ) scelta.add(null);
							else scelta.add(token);
						} 

						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						effettuata = ctgS.removeAll(scelta);
						fom.taRisultato.setText(Boolean.toString(effettuata));
						fom.taCorrente.setText("?");
					}
				}

			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.ffcb.setVisible(false);
			if ( fom.iteratorAttivo ) {
				for (int i = 0; i < fom.bIterator.length; i++) {
					if ( effettuata ) fom.bIterator[i].setEnabled(false);
					else fom.bIterator[i].setEnabled(fom.configIteratoreAttivo[i]);	
				}
			}
			else if ( fom.listIteratorAttivo ) {
				for (int i = 0; i < fom.bListIterator.length; i++) {
					if ( effettuata ) fom.bListIterator[i].setEnabled(false);
					else fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
				}
			}
			else {
				fom.panelIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(1).setEnabled(true);
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

	}//listenerFrameForREMOVE_All

	
	private class listenerFrameForRETAIN_ALL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean effettuata;
			String risposta = taRisposta.getText().replaceAll(" ", "");
			if ( risposta.length()>0 && risposta.charAt(risposta.length()-1)!=',' )
				risposta+=",";
			
			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {

					Collection<Integer> scelta;

					if ( cbSceltaCollection.getSelectedIndex()==0 )
						scelta = new ArrayList<>();
					else scelta = new LinkedList<>();

					StringTokenizer st = new StringTokenizer(risposta, ",");
					while ( st.hasMoreTokens() ) {
						scelta.add(Integer.parseInt(st.nextToken()));
					}

					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					effettuata = ctgI.retainAll(scelta);
					fom.taRisultato.setText(Boolean.toString(effettuata));
					fom.taCorrente.setText("?");
				}
				else {
					if ( !risposta.matches( "(\"(\\d+||\\w+)\",|null,)+" ) ) throw new IllegalArgumentException();					else {
						Collection<String> scelta;

						if ( cbSceltaCollection.getSelectedIndex()==0 )
							scelta = new ArrayList<>();
						else scelta = new LinkedList<>();

						StringTokenizer st = new StringTokenizer(risposta, ",");

						while ( st.hasMoreTokens() ){
							String token = st.nextToken().replaceAll("\"", "");
							if ( token==null ) scelta.add("   ");
							else if ( token.equals("null") ) scelta.add(null);
							else scelta.add(token);
						} 

						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						effettuata = ctgS.retainAll(scelta);
						fom.taRisultato.setText(Boolean.toString(effettuata));
						fom.taCorrente.setText("?");
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.ffcb.setVisible(false);
			if ( fom.iteratorAttivo ) {
				for (int i = 0; i < fom.bIterator.length; i++) {
					if ( effettuata ) fom.bIterator[i].setEnabled(false);
					else fom.bIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
				}
			}
			else if ( fom.listIteratorAttivo ) {
				for (int i = 0; i < fom.bListIterator.length; i++) {
					if ( effettuata ) fom.bListIterator[i].setEnabled(false);
					else fom.bListIterator[i].setEnabled(fom.configIteratoreAttivo[i]);
				}
			}
			else {
				fom.panelIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(1).setEnabled(true);
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

	}//listenerFrameForRETAIN_All

	private class listenerFrameForTO_ARRAY implements ActionListener {

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {

			String risposta = taRisposta.getText().replaceAll(" ", "");

			try {
				if ( !risposta.matches("new\\w+\\[\\d+\\]") ) 
					throw new IllegalArgumentException();

				StringTokenizer st = new StringTokenizer(risposta.substring(3), "[]");
				String tipo = st.nextToken();
				int len = Integer.parseInt(st.nextToken());
				if ( len>Integer.MAX_VALUE ) throw new IllegalArgumentException();

				if( tipo.equals("Integer")) { 
					if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
						CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
						fom.taRisultato.setText(ctgI.toStringToArray(new Integer[len]));
					}
					else {
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						fom.taRisultato.setText(ctgS.toStringToArray(new Integer[len]));
					}
				}
				else if ( tipo.equals("Object")) { 
					if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
						CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
						fom.taRisultato.setText(ctgI.toStringToArray(new Object[len]));
					}
					else {
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						fom.taRisultato.setText(ctgS.toStringToArray(new Object[len]));
					}
				}
				else if ( tipo.equals("String")) {
					if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
						CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
						fom.taRisultato.setText(ctgI.toStringToArray(new String[len]));
					}
					else {
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						fom.taRisultato.setText(ctgS.toStringToArray(new String[len]));
					}
				}
				else throw new RuntimeException();
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IMPOSSIBILE CREARE ARRAY !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taCorrente.setText("?");
			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.ffcb.setVisible(false);
			if ( fom.iteratorAttivo ) {
				for (int i = 0; i < fom.bIterator.length; i++) 
					fom.bIterator[i].setEnabled(false);
			}
			else if ( fom.listIteratorAttivo ) {
				for (int i = 0; i < fom.bListIterator.length; i++) 
					fom.bListIterator[i].setEnabled(false);
			}
			else {
				fom.panelIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(0).setEnabled(true);
				fom.panelListIterator.getComponent(1).setEnabled(true);
			}
			for (int i = 0; i < fom.bCollectionMethods.length; i++) 
				fom.bCollectionMethods[i].setEnabled(true);

			for (int i = 0; i < fom.bListMethods.length; i++) 
				fom.bListMethods[i].setEnabled(true);
		
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

	}//listenerFrameForTO_ARRAY

}//FrameforCollectionButton

