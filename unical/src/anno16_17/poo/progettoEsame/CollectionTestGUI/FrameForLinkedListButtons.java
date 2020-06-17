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
import javax.swing.ScrollPaneConstants;

import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.SceltaList;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.SceltaTipo;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.metodiLinkedList;



class FrameForLinkedListButtons extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String titolo;
	private int sizeX = 520, sizeY = 250;
	private JLabel lRichiesta = new JLabel("DEFINISCI ELEMENTO DA AGGIUNGERE:");
	private JPanel panelRisposta = new JPanel();
	private JTextArea taRisposta = new JTextArea(1, 30);
	private JScrollPane sPaneRisposta;
	private JPanel panelBottoni = new JPanel();
	private JButton bOk = new JButton("OK");
	private JButton bAnnulla = new JButton("ANNULLA");
	
	FrameOfMethods fom;
	metodiLinkedList mll;
	
	FrameForLinkedListButtons(FrameOfMethods fom, metodiLinkedList mll){
		
		this.fom = fom;
		this.mll = mll;
		
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
		this.setSize(sizeX, sizeY);
		this.setLocationRelativeTo(null);

		this.getContentPane().setLayout( new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
		lRichiesta.setFont( new Font(null, Font.BOLD, 18));
		this.getContentPane().add(lRichiesta);
		taRisposta.setFont( new Font(null, Font.ITALIC, 16) );
		sPaneRisposta = new JScrollPane(taRisposta, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelRisposta.add(sPaneRisposta);
		this.getContentPane().add(panelRisposta);
		
		if ( mll==metodiLinkedList.ADD_FIRST ) titolo = "addFirst";
		else titolo = "addLast";
		
		this.setTitle(titolo);
		bOk.addActionListener( new listenerFrameForADDS() );
	
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
		
	}//listenerANNULLA
	
	private class listenerFrameForADDS implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String daAggiungere = taRisposta.getText().replaceAll(" ", "");
			
			try {
				if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
					
					@SuppressWarnings("unchecked")
					CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)fom.ctg;
					if ( mll==metodiLinkedList.ADD_FIRST)
						ctgI.addFirst((Integer.parseInt(daAggiungere)));
					else ctgI.addLast(Integer.parseInt(daAggiungere));

				}
				else {
					if ( !daAggiungere.matches( "(\".*\"|null)" ) ) throw new IllegalArgumentException();
					else {
						StringTokenizer st = new StringTokenizer(daAggiungere, "\"");
						if ( st.countTokens()==0 ) {
							if ( daAggiungere.equals("null") ) daAggiungere = null;
							else daAggiungere = "   ";
						}
						else daAggiungere = st.nextToken();
						
						@SuppressWarnings("unchecked")
						CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)fom.ctg;
						if ( mll==metodiLinkedList.ADD_FIRST)
							ctgS.addFirst(daAggiungere);
						else ctgS.addLast(daAggiungere);
					}
				}
			
			} catch ( Exception ioobe ) {
				JOptionPane.showMessageDialog(null, "IL TIPO RICHIESTO è "+CollectionToGrafica.tipo+" !", 
						"ERRORE", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			fom.taCorrente.setText("?");
			fom.taLista.setText(fom.ctg.toStringListaAttiva());
			fom.ffllb.setVisible(false);
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

	}//listenerFrameForADDS

}//FrameForLinkedListButtons
