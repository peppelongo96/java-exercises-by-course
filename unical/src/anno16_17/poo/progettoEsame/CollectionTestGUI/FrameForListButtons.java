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
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.metodiList;



class FrameForListButtons extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titolo;
	private int sizeX, sizeY;
	private JLabel lRichiesta = new JLabel();
	private JPanel panelRisposta = new JPanel();
	private JTextArea taRisposta = new JTextArea(1, 30);
	private JScrollPane sPaneRisposta;
	private JPanel panelBottoni = new JPanel();
	private JButton bOk = new JButton("OK");
	private JButton bAnnulla = new JButton("ANNULLA");
	
	private JPanel panelScelta = new JPanel();
	@SuppressWarnings("rawtypes")
	private JComboBox cbScelta;
	private JTextArea taStampaComparator = new JTextArea(7,42);
	private String tipoBoxed = CollectionToGrafica.tipoForButtonsBoxed;
	private JTextArea taRisposta2;


	private FrameOfMethods fom;
	private metodiList ml;
	
	FrameForListButtons(FrameOfMethods fom, metodiList ml){
		
		this.fom = fom;
		this.ml = ml;

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
		if ( ml!=metodiList.BINARY_SEARCH_COMPARATOR && ml!=metodiList.SORT_COMPARATOR ) {
			taRisposta.setFont( new Font(null, Font.ITALIC, 16) );
			sPaneRisposta = new JScrollPane(taRisposta, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			panelRisposta.add(sPaneRisposta);
			this.getContentPane().add(panelRisposta);
		}
		
		if ( ml==metodiList.BINARY_SEARCH_NATURALE ) {
			titolo = "binarySearch -confronto naturale-";
			sizeX = 550; sizeY = 280;
			lRichiesta.setText("DEFINISCI LIST DOVE EFFETTUARE LA RICERCA:");
			
			String setCreata = fom.ctg.toStringListaAttiva().substring(1,fom.ctg.toStringListaAttiva().length()-2);
			if ( setCreata.length()>0 ) {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER)
					taRisposta.setText(setCreata);
				else {
					StringBuilder sb = new StringBuilder();
					StringTokenizer st = new StringTokenizer(setCreata, " ,");
					while ( st.hasMoreTokens() ) 
						sb.append(" \""+st.nextToken()+"\",");
					sb.deleteCharAt(sb.length()-1);
					taRisposta.setText(sb.toString());
				}
			}
			
			String[] listaSceltaList = { "ArrayList<Integer>", "ArrayList<String>", 
					"LinkedList<Integer>", "LinkedList<String>", "..." };
			cbScelta = new JComboBox<>(listaSceltaList);
			if ( CollectionToGrafica.tipoList==SceltaList.ARRAY_LIST ) {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER )
					cbScelta.setSelectedIndex(0);
				else cbScelta.setSelectedIndex(1);
			}
			else {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER )
					cbScelta.setSelectedIndex(2);
				else cbScelta.setSelectedIndex(3);
			}
			panelScelta.add(cbScelta);
			this.getContentPane().add(panelScelta);
			
			JLabel lRichiesta2 = new JLabel("DEFINISCI ELEMENTO DA CERCARE:");
			lRichiesta2.setFont( new Font(null, Font.BOLD, 18));
			this.getContentPane().add(lRichiesta2);
			JPanel panelRisposta2 = new JPanel();
			taRisposta2 = new JTextArea(1,20);
			taRisposta2.setFont( new Font(null, Font.ITALIC, 16) );
			JScrollPane sPaneRisposta2 = new JScrollPane(taRisposta2, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			panelRisposta2.add(sPaneRisposta2);
			this.getContentPane().add(panelRisposta2);
			bOk.addActionListener( new listenerFrameForBINARY_SEARCH_NATURALE() );
		}
		
		else if ( ml==metodiList.BINARY_SEARCH_COMPARATOR ) {
			titolo = "binarySearch";
			sizeX = 650; sizeY = 400;
			
			lRichiesta.setText("SCEGLI COMPARATOR TRA QUELLI PROPOSTI:");
			String[] listaSceltaComparator = { "(ordine crescente)", "(ordine decrescente)", "(shuffling)" };
			if ( CollectionToGrafica.tipo==SceltaTipo.STRING ) {
				listaSceltaComparator[0] = "(ordine alfanumerio crescente)";
				listaSceltaComparator[1] = "(ordine alfanumerio decrescente)";
				listaSceltaComparator[2] = "(dalla String più lunga alla più corta)";
			}
				
			cbScelta = new JComboBox<>(listaSceltaComparator);
			
			panelScelta.add(cbScelta);
			cbScelta.addActionListener( new listenerCbScelta() );
			this.getContentPane().add(panelScelta);
			
			JPanel panelStampaComparator = new JPanel();
			
			taStampaComparator.setFont( new Font(null, Font.ITALIC, 17));
			taStampaComparator.setText(""
					+ "  static class crescenteComp<"+tipoBoxed+"> implements Comparator<"+tipoBoxed+"> {\n"
					+ "     public int compare("+tipoBoxed+" arg0, "+tipoBoxed+" arg1) {\n"
					+ "        return arg0.compareTo(arg1);\n"
					+ "     }\n"
					+ "  }//ordineCrescente");
			taStampaComparator.setEditable(false);
			
			panelStampaComparator.add(taStampaComparator);
			this.getContentPane().add(panelStampaComparator);
			
			JLabel lRichiesta2 = new JLabel("INSERISCI ELEMENTO DA CERCARE:");
			lRichiesta2.setFont( new Font(null, Font.BOLD, 18));
			this.getContentPane().add(lRichiesta2);
			JPanel panelRisposta2 = new JPanel();
			taRisposta2 = new JTextArea(1,20);
			taRisposta2.setFont( new Font(null, Font.ITALIC, 16) );
			JScrollPane sPaneRisposta2 = new JScrollPane(taRisposta2, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			panelRisposta2.add(sPaneRisposta2);
			this.getContentPane().add(panelRisposta2);
			bOk.addActionListener( new listenerFrameForBINARY_SEARCH_COMPARATOR());
		}
		
		else if ( ml==metodiList.SORT_COMPARATOR ) {
			titolo = "sort";
			sizeX = 650; sizeY = 350;
			
			lRichiesta.setText("SCEGLI COMPARATOR TRA QUELLI PROPOSTI:");
			String[] listaSceltaComparator = { "(ordine crescente)", "(ordine decrescente)", "(shuffling)" };
			if ( CollectionToGrafica.tipo==SceltaTipo.STRING ) {
				listaSceltaComparator[0] = "(ordine alfanumerio crescente)";
				listaSceltaComparator[1] = "(ordine alfanumerio decrescente)";
				listaSceltaComparator[2] = "(dalla String più lunga alla più corta)";
			}
				
			cbScelta = new JComboBox<>(listaSceltaComparator);
			
			panelScelta.add(cbScelta);
			cbScelta.addActionListener( new listenerCbScelta() );
			this.getContentPane().add(panelScelta);
			
			JPanel panelStampaComparator = new JPanel();
			taStampaComparator.setFont( new Font(null, Font.ITALIC, 17));
			String tipoBoxed = CollectionToGrafica.tipoForButtonsBoxed;
			taStampaComparator.setText(""
					+ "  static class crescenteComp<"+tipoBoxed+"> implements Comparator<"+tipoBoxed+"> {\n"
					+ "     public int compare("+tipoBoxed+" arg0, "+tipoBoxed+" arg1) {\n"
					+ "        return arg0.compareTo(arg1);\n"
					+ "     }\n"
					+ "  }//ordineCrescente");
			taStampaComparator.setEditable(false);
			
			panelStampaComparator.add(taStampaComparator);
			this.getContentPane().add(panelStampaComparator);
			
			bOk.addActionListener( new listenerFrameForSORT_COMPARATOR());
		}
		
		else if ( ml==metodiList.SORT_NATURALE ) {
			titolo = "sort -confronto naturale-";
			sizeX = 550; sizeY = 200;
			lRichiesta.setText("DEFINISCI LIST DA ORDINARE:");
			
			String setCreata = fom.ctg.toStringListaAttiva().substring(1,fom.ctg.toStringListaAttiva().length()-2);
			if ( setCreata.length()>0 ) {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER)
					taRisposta.setText(setCreata);
				else {
					StringBuilder sb = new StringBuilder();
					StringTokenizer st = new StringTokenizer(setCreata, ",");
					while ( st.hasMoreTokens() ) 
						sb.append(" \""+st.nextToken()+"\",");
					sb.deleteCharAt(sb.length()-1);
					taRisposta.setText(sb.toString());
				}
			}
			

			String[] listaSceltaCollection = { "ArrayList<Integer>", "ArrayList<String>", 
					"LinkedList<Integer>", "LinkedList<String>", "..." };
			cbScelta = new JComboBox<>(listaSceltaCollection);
			if ( CollectionToGrafica.tipoList==SceltaList.ARRAY_LIST ) {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER )
					cbScelta.setSelectedIndex(0);
				else cbScelta.setSelectedIndex(1);
			}
			else {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER )
					cbScelta.setSelectedIndex(2);
				else cbScelta.setSelectedIndex(3);
			}
		
			panelScelta.add(cbScelta);
			this.getContentPane().add(panelScelta);
			bOk.addActionListener( new listenerFrameForSORT_NATURALE() );
		}
		
		else if ( ml==metodiList.ADD||ml==metodiList.SET ) {
			titolo = "add";
			sizeX = 520; sizeY = 250;
			lRichiesta.setText("INSERISCI ELEMENTO DA AGGIUNGERE:");
			if ( ml==metodiList.SET ) {
				titolo = "set";
				lRichiesta.setText("INSERISCI ELEMENTO DA SOSTITUIRE:");
			}
			
			JLabel lRichiesta2 = new JLabel("DEFINISCI POSIZIONE:");
			lRichiesta2.setFont( new Font(null, Font.BOLD, 18));
			this.getContentPane().add(lRichiesta2);
			JPanel panelRisposta2 = new JPanel();
			taRisposta2 = new JTextArea(1,20);
			taRisposta2.setFont( new Font(null, Font.ITALIC, 16) );
			JScrollPane sPaneRisposta2 = new JScrollPane(taRisposta2, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			panelRisposta2.add(sPaneRisposta2);
			this.getContentPane().add(panelRisposta2);
			bOk.addActionListener( new listenerFrameForADDeSET() );
		}
		
		if ( ml==metodiList.ADD_ALL ) {
			titolo = "addAll";
			sizeX = 520; sizeY = 250;
			lRichiesta.setText("INSERISCI COLLEZIONE DA AGGIUNGERE:");
			
			String setCreata = fom.ctg.toStringListaAttiva().substring(1,fom.ctg.toStringListaAttiva().length()-2);
			if ( setCreata.length()>0 ) {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER)
					taRisposta.setText(setCreata);
				else {
					StringBuilder sb = new StringBuilder();
					StringTokenizer st = new StringTokenizer(setCreata, ",");
					while ( st.hasMoreTokens() ) 
						sb.append(" \""+st.nextToken()+"\",");
					sb.deleteCharAt(sb.length()-1);
					taRisposta.setText(sb.toString());
				}
			}
			
			String[] listaSceltaCollection = { "ArrayList<"+tipoBoxed+">", "LinkedList<"+tipoBoxed+">", "..." };
			cbScelta = new JComboBox<>(listaSceltaCollection);
			if ( CollectionToGrafica.tipoList==SceltaList.ARRAY_LIST )
				cbScelta.setSelectedIndex(0);
			else cbScelta.setSelectedIndex(1);
		
			panelScelta.add(cbScelta);
			this.getContentPane().add(panelScelta);
			
			JLabel lRichiesta2 = new JLabel("DEFINISCI POSIZIONE (a partire dalla quale...) :");
			lRichiesta2.setFont( new Font(null, Font.BOLD, 18));
			this.getContentPane().add(lRichiesta2);
			JPanel panelRisposta2 = new JPanel();
			taRisposta2 = new JTextArea(1,20);
			taRisposta2.setFont( new Font(null, Font.ITALIC, 16) );
			JScrollPane sPaneRisposta2 = new JScrollPane(taRisposta2, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			panelRisposta2.add(sPaneRisposta2);
			this.getContentPane().add(panelRisposta2);
			bOk.addActionListener( new listenerFrameForADD_ALL() );
		}
		else if ( ml==metodiList.GET||ml==metodiList.REMOVE ) {
			titolo = "get";
			sizeX = 520; sizeY = 250;
			lRichiesta.setText("DEFINISCI INDICE ELEMENTO DA RITORNARE:");
			if ( ml==metodiList.REMOVE ) {
				titolo = "remove";
				lRichiesta.setText("DEFINISCI INDICE ELEMENTO DA ELIMINARE:");
			}
			
			bOk.addActionListener( new listenerFrameForGETeREMOVE() );
		}
		
		else if ( ml==metodiList.INDEX_OF||ml==metodiList.LAST_INDEX_OF) {
			titolo = "indexOf";
			if ( ml==metodiList.LAST_INDEX_OF ) titolo = "lastIndexOf";
			sizeX = 520; sizeY = 250;
			lRichiesta.setText("DEFINISCI ELEMNTO DA CERCARE:");
			
			bOk.addActionListener( new listenerFrameForINDEXES_OF() );
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
	
	private class listenerCbScelta implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selOra = cbScelta.getSelectedIndex();
			System.out.println(selOra);
			if ( selOra==0 ) {
				taStampaComparator.setText(""
						+ "  static class crescenteComp<"+tipoBoxed+"> implements Comparator<"+tipoBoxed+"> {\n"
						+ "     public int compare("+tipoBoxed+" arg0, "+tipoBoxed+" arg1) {\n"
						+ "        return arg0.compareTo(arg1);\n"
						+ "     }\n"
						+ "  }//ordineCrescente");
			}
			else if ( selOra==1 ) {
				taStampaComparator.setText(""
						+ "  static class decrescenteComp<"+tipoBoxed+"> implements Comparator<"+tipoBoxed+"> {\n"
						+ "     public int compare("+tipoBoxed+" arg0, "+tipoBoxed+" arg1) {\n"
						+ "        return arg1.compareTo(arg0);\n"
						+ "     }\n"
						+ "  }//ordineDecrescente");
			}
			else if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
				taStampaComparator.setText(""
						+ "  static class shuffleComp implements Comparator<Integer> {\n"
						+ "     Random r = new Random();\n"
						+ "     public int compare(Integer o1, Integer o2) {\n"
						+ "        return r.nextInt();\n"
						+ "     }\n"
						+ "  }//shuffleComp");
			}
			else {
				taStampaComparator.setText(""
						+ "  static class lenStringComp implements Comparator<String> {\n"
						+ "     public int compare(String o1, String o2) {\n"
						+ "        if ( o1.length() > o2.length() ) return -1;\n"
						+ "        else if ( o1.length() < o2.length() ) return 1;\n"
						+ "        return 0;\n"
						+ "     }\n"
						+ "  }//lenStringComp");
			}	
		}
	}
	
	private class listenerFrameForBINARY_SEARCH_NATURALE implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int selezionatoOra = cbScelta.getSelectedIndex();
			String risposta = taRisposta.getText().replaceAll(" ", "");
			if ( risposta.charAt(risposta.length()-1)!=',' )
				risposta+=",";
			String daCercare = taRisposta2.getText().replaceAll(" \"", "");

			try {
				
				if ( selezionatoOra==0 || selezionatoOra==2 ) {

					List<Integer> scelta;

					if ( selezionatoOra==0 ) scelta = new ArrayList<>();
					else scelta = new LinkedList<>();

					StringTokenizer st = new StringTokenizer(risposta, ",");
					while ( st.hasMoreTokens() ) {
						scelta.add(Integer.parseInt(st.nextToken()));
					}

					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					fom.taRisultato.setText(ctgI.toStringBinarySearch(scelta, Integer.parseInt(daCercare)));
				}
				else {

					if ( !risposta.matches( "(\"(\\d+!\\w+)\",|null,)+" ) ) throw new IllegalArgumentException();
					else {
						List<String> scelta;

						if ( selezionatoOra==1) scelta = new ArrayList<>();
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
						fom.taRisultato.setText(ctgS.toStringBinarySearch(scelta,daCercare));
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taCorrente.setText("?");
			fom.fflb.setVisible(false);
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

	}//listenerFrameForBINARY_SEARCH_NATURALE
	
	private class listenerFrameForBINARY_SEARCH_COMPARATOR implements ActionListener {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void actionPerformed(ActionEvent e) {
			int selezionatoOra = cbScelta.getSelectedIndex();
			String daCercare = taRisposta2.getText().replaceAll(" \"", "");

			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					switch ( selezionatoOra ) {
					case 0: fom.taRisultato.setText(ctgI.toStringBinarySearch( new EsempiComp.crescenteComp(), Integer.parseInt(daCercare))); break;
					case 1: fom.taRisultato.setText(ctgI.toStringBinarySearch( new EsempiComp.decrescenteComp(),Integer.parseInt(daCercare))); break;
					default: fom.taRisultato.setText(ctgI.toStringBinarySearch(new EsempiComp.shuffleComp(), Integer.parseInt(daCercare)));
					}
				}
				else {
					if ( !daCercare.matches("\".+\"") ) throw new IllegalArgumentException();
					CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
					switch ( selezionatoOra ) {
					case 0: fom.taRisultato.setText(ctgS.toStringBinarySearch( new EsempiComp.crescenteComp(), daCercare)); break;
					case 1: fom.taRisultato.setText(ctgS.toStringBinarySearch( new EsempiComp.decrescenteComp(), daCercare)); break;
					default: fom.taRisultato.setText(ctgS.toStringBinarySearch( new EsempiComp.lenStringComp(), daCercare));
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taCorrente.setText("?");
			fom.fflb.setVisible(false);
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

	}//listenerFrameForBINARY_SEARCH_COMPARATOR
	
	private class listenerFrameForSORT_COMPARATOR implements ActionListener {

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void actionPerformed(ActionEvent e) {
			int selezionatoOra = cbScelta.getSelectedIndex();

			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					switch ( selezionatoOra ) {
					case 0: ctgI.sort( new EsempiComp.crescenteComp() ); break;
					case 1: ctgI.sort( new EsempiComp.decrescenteComp() ); break;
					default: ctgI.sort( new EsempiComp.shuffleComp() );
					}
				}
				else {
					CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
					switch ( selezionatoOra ) {
					case 0: ctgS.sort( new EsempiComp.crescenteComp() ); break;
					case 1: ctgS.sort( new EsempiComp.decrescenteComp() ); break;
					default: ctgS.sort( new EsempiComp.lenStringComp() );
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taCorrente.setText("?");
			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.fflb.setVisible(false);
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

	}//listenerFrameForSORT_COMPARATOR
	
	private class listenerFrameForSORT_NATURALE implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int selezionatoOra = cbScelta.getSelectedIndex();
			String risposta = taRisposta.getText().replaceAll(" ", "");
			if ( risposta.length()>0 && risposta.charAt(risposta.length()-1)!=',' )
				risposta+=",";

			try {
				
				if ( selezionatoOra==0 || selezionatoOra==2 ) {

					List<Integer> scelta;

					if ( selezionatoOra==0 ) scelta = new ArrayList<>();
					else scelta = new LinkedList<>();

					StringTokenizer st = new StringTokenizer(risposta, ",");
					while ( st.hasMoreTokens() ) {
						scelta.add(Integer.parseInt(st.nextToken()));
					}

					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					ctgI.sort(scelta);
					fom.taRisultato.setText(scelta.toString());
				}
				else {

					if ( !risposta.matches( "(\"(\\d+!\\w+)\",|null,)+" ) ) throw new IllegalArgumentException();
					else {
						List<String> scelta;

						if ( selezionatoOra==1) scelta = new ArrayList<>();
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
						ctgS.sort(scelta);
						fom.taRisultato.setText(scelta.toString());
					}
				}
			} catch ( Exception ex ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taCorrente.setText("?");
			fom.fflb.setVisible(false);
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

	}//listenerFrameForSORT_NATURALE
	
	private class listenerFrameForADDeSET implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String daModificare = taRisposta.getText().replaceAll(" ","");
			
			try {
				int posizione = Integer.parseInt(taRisposta2.getText().replaceAll(" ",""));

				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
					
					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					if ( ml==metodiList.ADD )
						ctgI.add(posizione, Integer.parseInt(daModificare));
					else fom.taRisultato.setText(ctgI.toStringSet(posizione, Integer.parseInt(daModificare)));
					
				}
				else {

					if ( !daModificare.matches( "(\"(\\d+!\\w+)\"|null)" ) ) throw new IllegalArgumentException();
					else {
						StringTokenizer st = new StringTokenizer(daModificare,"\"");
						if ( st.countTokens()==0 ) {
							if ( taRisposta.getText().equals("null") ) daModificare = null;
							else daModificare = "   ";
						}
						else daModificare = st.nextToken();
							
						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						if ( ml==metodiList.ADD )
							ctgS.add(posizione, daModificare);
						else fom.taRisultato.setText(ctgS.toStringSet(posizione, daModificare));
					}
				}
			} catch ( IllegalArgumentException iae ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			} catch ( IndexOutOfBoundsException ioobe ) {
				JOptionPane.showMessageDialog(null, "POSIZIONE ERRATA !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			fom.taCorrente.setText("?");
			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.fflb.setVisible(false);
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

	}//listenerFrameForADDeSET
	
	private class listenerFrameForADD_ALL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int selezionatoOra = cbScelta.getSelectedIndex();
			
			String risposta = taRisposta.getText().replaceAll(" ", "");
			if ( risposta.length()>0 && risposta.charAt(risposta.length()-1)!=',' )
				risposta+=",";
			
			Collection<?> scelta;
			if ( selezionatoOra==0 ) scelta = new ArrayList<>();
			else scelta = new LinkedList<>();

			try {
				int posizione = -1;
				if( taRisposta2.getText().length()>0 )
					posizione = Integer.parseInt(taRisposta2.getText().replaceAll(" ", ""));
				
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {

					@SuppressWarnings("unchecked")
					Collection<Integer> sceltaInteger = (Collection<Integer>)scelta;

					if ( selezionatoOra==0 ) sceltaInteger = new ArrayList<>();
					else sceltaInteger = new LinkedList<>();

					StringTokenizer st = new StringTokenizer(risposta, ",");
					if ( st.countTokens()==0 ) throw new IllegalArgumentException();
					while ( st.hasMoreTokens() ) {
						sceltaInteger.add(Integer.parseInt(st.nextToken()));
					}

					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					ctgI.addAll(posizione, sceltaInteger);
				}
				else {

					if ( !risposta.matches( "(\"(\\d+|\\w+)\",|null,)+" ) ) throw new IllegalArgumentException();
					else {
						
						@SuppressWarnings("unchecked")
						Collection<String> sceltaString = (Collection<String>)scelta;

						StringTokenizer st = new StringTokenizer(risposta, ",");

						while ( st.hasMoreTokens() ){
							String token = st.nextToken().replaceAll("\"", "");
							if ( token==null ) sceltaString.add("   ");
							else if ( token.equals("null") ) sceltaString.add(null);
							else sceltaString.add(token);
						} 

						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						ctgS.addAll(posizione, sceltaString);
					}
				}

			} catch ( IndexOutOfBoundsException ioobe  ) {
				JOptionPane.showMessageDialog(null, "POSIZIONE ERRATA !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			} catch ( Exception ex) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}

			fom.taCorrente.setText("?");
			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.fflb.setVisible(false);
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

	}//listenerFrameForADD_ALL
	
	private class listenerFrameForGETeREMOVE implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
					
					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					if ( ml==metodiList.GET )
						fom.taRisultato.setText(ctgI.toStringGet(Integer.parseInt(taRisposta.getText().replaceAll(" ",""))));
					else fom.taRisultato.setText(ctgI.toStringRemove(Integer.parseInt(taRisposta.getText().replaceAll(" ",""))));

				}
				else {

					@SuppressWarnings("unchecked")
					CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
					if ( ml==metodiList.GET )
						fom.taRisultato.setText(ctgS.toStringGet(Integer.parseInt(taRisposta.getText().replaceAll(" ",""))));	
					else fom.taRisultato.setText(ctgS.toStringRemove(Integer.parseInt(taRisposta.getText().replaceAll(" ",""))));
				}
			
			} catch ( IndexOutOfBoundsException ioobe ) {
				JOptionPane.showMessageDialog(null, "POSIZIONE ERRATA !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			fom.taCorrente.setText("?");
			if ( ml==metodiList.REMOVE )
				fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.fflb.setVisible(false);
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

	}//listenerFrameForGET
	
	private class listenerFrameForINDEXES_OF implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String risposta = taRisposta.getText().replaceAll(" ", "");
			
			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
					
					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					if ( ml==metodiList.INDEX_OF)
						fom.taRisultato.setText(ctgI.toStringIndexOf(Integer.parseInt(risposta)));
					else fom.taRisultato.setText(ctgI.toStringlastIndexOf(Integer.parseInt(risposta)));

				}
				else {
					if ( !risposta.matches( "(\"(\\d+|\\w+)\"|null)" ) ) throw new IllegalArgumentException();
					else {
						StringTokenizer st = new StringTokenizer(risposta, "\"");
						String daCercare;
						if ( st.countTokens()==0 ) {
							if ( taRisposta.getText().equals("null") ) daCercare = null;
							else daCercare = "   ";
						}
						else daCercare = st.nextToken();
						
						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						if ( ml==metodiList.INDEX_OF)
							fom.taRisultato.setText(ctgS.toStringIndexOf(daCercare));
						else fom.taRisultato.setText(ctgS.toStringlastIndexOf(daCercare));
					}
				}
			
			} catch ( Exception ioobe ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			fom.taCorrente.setText("?");
			fom.fflb.setVisible(false);
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

	}//listenerFrameForINDEXES_OF
	
}//FrameforListButton