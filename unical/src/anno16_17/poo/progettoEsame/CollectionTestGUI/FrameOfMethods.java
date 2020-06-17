package anno16_17.poo.progettoEsame.CollectionTestGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.SceltaList;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.metodiCollection;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.metodiLinkedList;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.metodiList;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.metodiListIterator;


class FrameOfMethods extends JFrame {

	private static final long serialVersionUID = 1L;

	private final String titolo;
	private final int xSize = 1700; 
	private int	ySize = 890;

	private JPanel panelStampaTitolo;
	private JLabel stampaTitolo;

	private JPanel panelLista;
	private JLabel lLista = new JLabel("LISTA:  ");
	private JScrollPane sPanelLista;
	JTextArea taLista = new JTextArea(1,70);

	private JPanel panelCorrente;
	private JLabel lCorrente = new JLabel("ELEMENTO CORRENTE:  ");
	private JScrollPane sPanelCorrente;
	JTextArea taCorrente = new JTextArea(1,20);

	//---------------------ISTANZE PANNELLO RISULTATO----------------------//
	private JPanel panelRisultato1;
	private JPanel panelRisultato2;
	private JLabel lDescrizione;
	JTextArea taRisultato = new JTextArea();
	private JScrollPane sPanelRisultato1;

	////////////////////////////////////////////////////////////////////////////////////////
	private String tipoForButtons = CollectionToGrafica.tipoForButtons;
	private String tipoForButtonsBoxed = CollectionToGrafica.tipoForButtonsBoxed;
	boolean[] configIteratoreAttivo;
	////////////////////////////////////////////////////////////////////////////////////////
	
	//-----------ISTANZE COLLECTION METHODS----------------------//
	private JPanel panelCollectionMethods;

	private JPanel panelCMTitolo;
	private JLabel lCMTitolo = new JLabel(" - COLLECTION METHODS -");

	private JPanel panelBCollectionMethods;
	JButton[] bCollectionMethods= { new JButton("add ( "+tipoForButtons+" )"), new JButton("clear ()"),
			new JButton("size ()"), new JButton("addAll ( Collection<"+tipoForButtonsBoxed+"> c )"), new JButton("isEmpty ()"), 
			new JButton("contains ( "+tipoForButtons+" )"), new JButton("containsAll ( Collection<"+tipoForButtonsBoxed+"> c )"),
			new JButton("remove ( "+tipoForButtons+" )"), new JButton("removeAll ( Collection<"+tipoForButtonsBoxed+">) c "), 
			new JButton("retainAll ( Collection<"+tipoForButtonsBoxed+"> c )"), new JButton("toArray ()"), 
			new JButton("<T> toArray ( T[] a )") };

	JPanel panelIterator;
	private JButton bAttivaIterator = new JButton(" ATTIVA ITERATOR ");
	boolean iteratorAttivo;
	private JPanel panelIteratorON;
	private JLabel lIterator = new JLabel("  ITERATOR  ");
	JButton[] bIterator = { new JButton("hasNext()"), new JButton("next()"), new JButton("remove()") };


	//----------------------------ISTANZE LIST METHODS-----------------------//
	private JPanel panelListMethods;

	private JPanel panelLMTitolo;
	private JLabel lLMTitolo = new JLabel(" - LIST METHODS -");

	private JPanel panelBListMethods;
	JButton[] bListMethods= { new JButton(" <T extends Comparable<? super T>> binarySearch ( List<T> l, T t ) "), 
			new JButton("binarySearch ( Comparator<"+tipoForButtonsBoxed+"> comp, "+tipoForButtons+" ) "),
			new JButton("sort ( Comparator<"+tipoForButtonsBoxed+"> comp )"), 
			new JButton("<T extends Comparable<? super T>> sort ( List<T> l )"), 
			new JButton("add ( int indice, "+tipoForButtons+" )"), 
			new JButton("addAll ( int indice, Collection<"+tipoForButtonsBoxed+"> c )"), 
			new JButton("get ( int indice )"),
			new JButton("indexOf ( "+tipoForButtons+" )"),
			new JButton("lastIndexOf ( "+tipoForButtons+" )"), 
			new JButton("remove ( int indice )"), 
			new JButton("set ( int indice, "+tipoForButtons+" )")}; 

	JPanel panelListIterator;
	JButton bAttivaListIterator = new JButton(" ATTIVA LIST-ITERATOR ");
	boolean listIteratorAttivo;
	JCheckBox cbSpuntaLIPOS = new JCheckBox("da posizione...");
	JPanel panelListIteratorON;
	private JLabel lListIterator = new JLabel("  LIST-ITERATOR  ");
	JButton[] bListIterator = { new JButton("add ( "+tipoForButtons+" )"), 
			new JButton("hasNext()"), new JButton("hasPrevious()"),
			new JButton("next()"), new JButton("nextIndex()"), new JButton("previous()"),
			new JButton("previousIndex()"), new JButton("remove()"), new JButton("set ( "+tipoForButtons+" )") };


	//-------------------------ISTANZE LINKED LIST METHODS------------------------//
	private JPanel panelLinkedListMethods;

	private JPanel panelLinkedListTitolo;
	private JLabel lLinkedListTitolo = new JLabel(" - Linked List Methods -");

	private JPanel panelBLinkedListMethods;
	JButton[] bLinkedListMethods= { new JButton("removeFirst()"), 
			new JButton("removeLast()"),
			new JButton("getFirst()"), 
			new JButton("getLast()"), 
			new JButton("addFirst ( "+tipoForButtons+" )"), 
			new JButton("addLast ( "+tipoForButtons+" )") };

	/////////////////////////////////////
	CollectionToGrafica<?> ctg;
	FrameForCollectionButtons ffcb;
	FrameForListButtons fflb;
	FrameForListIterator ffli;
	FrameForLinkedListButtons ffllb;
	////////////////////////////////////
	
	
	FrameOfMethods(CollectionToGrafica<?> ctg) {

		this.ctg = ctg;	
		
		if ( CollectionToGrafica.tipoList==SceltaList.ARRAY_LIST ) titolo = "ARRAY LIST<"+tipoForButtonsBoxed.toUpperCase()+">";
		else titolo = "LINKED LIST<"+tipoForButtonsBoxed.toUpperCase()+">";

		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				if( consensoUscita() ) System.exit(0);
			}
		} );

		this.setTitle(titolo);
		if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) 
			ySize = 940;
		this.setSize(xSize, ySize);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		panelStampaTitolo = new JPanel();
		stampaTitolo = new JLabel(titolo);
		stampaTitolo.setFont(new Font("sansserif",Font.BOLD,45));
		panelStampaTitolo.add(stampaTitolo);
		this.getContentPane().add(panelStampaTitolo);

		panelLista = new JPanel();
		lLista.setFont( new Font(null, Font.BOLD, 20));;
		panelLista.add(lLista);
		taLista.setEditable(false);
		taLista.setFont(new Font(null,Font.ITALIC,18));
		taLista.setText(ctg.toStringListaAttiva());
		sPanelLista = new JScrollPane(taLista, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelLista.add(sPanelLista);
		this.getContentPane().add(panelLista);
		this.getContentPane().add(Box.createRigidArea(new Dimension(xSize, 5)));

		panelCorrente = new JPanel();
		lCorrente.setFont( new Font(null, Font.ITALIC, 18));
		panelCorrente.add(lCorrente);
		taCorrente.setEditable(false);
		taCorrente.setFont(new Font(null,Font.ITALIC,16));
		taCorrente.setText("?");
		sPanelCorrente = new JScrollPane(taCorrente, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelCorrente.add(sPanelCorrente);
		this.getContentPane().add(panelCorrente);

		//---------------------PANNELLO RISULTATI--------------------//
		panelRisultato1 = new JPanel();
		panelRisultato1.setLayout(new BoxLayout(panelRisultato1, BoxLayout.Y_AXIS));		
		panelRisultato1.setBorder( BorderFactory.createLineBorder(Color.BLACK, 3));
		lDescrizione = new JLabel("       SPAZIO RISULTATI       ");
		lDescrizione.setFont(new Font("Comic Sans MS",Font.BOLD,25));;
		panelRisultato1.add(lDescrizione);
		taRisultato.setEditable(false);
		taRisultato.setFont(new Font(null,Font.ITALIC,20));
		taRisultato.setSize(1,1);
		sPanelRisultato1 = new JScrollPane(taRisultato,  ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelRisultato1.add(sPanelRisultato1);
		panelRisultato2 = new JPanel();
		panelRisultato2.add(panelRisultato1);
		this.getContentPane().add(Box.createRigidArea(new Dimension(xSize, 30)));
		this.getContentPane().add(panelRisultato2);
		this.getContentPane().add(Box.createRigidArea(new Dimension(xSize, 15)));

		//-----------------------------COLLECTION METHODS-----------------------------------------------------//
		panelCMTitolo = new JPanel();
		panelCMTitolo.setLayout( new BorderLayout() );
		lCMTitolo.setFont(new Font("sansserif",Font.BOLD,30));
		panelCMTitolo.add(lCMTitolo, BorderLayout.WEST);
		this.getContentPane().add(panelCMTitolo);

		panelCollectionMethods = new JPanel();
		this.getContentPane().add(panelCollectionMethods);

		panelBCollectionMethods = new JPanel();
		panelBCollectionMethods.setLayout( new GridLayout(3, 4) );
		for (int i = 0; i < bCollectionMethods.length; i++) {
			bCollectionMethods[i].setFont( new Font(null, Font.BOLD, 15));
			panelBCollectionMethods.add(bCollectionMethods[i]);
		}
		panelCollectionMethods.add(panelBCollectionMethods);

		panelIterator = new JPanel();
		panelIterator.setBorder(BorderFactory.createTitledBorder(" I T E R A T O R "));

		///////////////////////////////////////////////////////////////////////////
		bAttivaIterator.setFont( new Font("sansserif",Font.BOLD,20) );
		panelIterator.add(bAttivaIterator);

		panelIteratorON = new JPanel();
		panelIteratorON.setLayout( new GridLayout(4, 1));
		lIterator.setFont( new Font("sansserif",Font.BOLD,25) );
		panelIteratorON.add(lIterator);
		for (int i = 0; i < bIterator.length; i++) {
			bIterator[i].setFont( new Font(null, Font.BOLD, 15));
			panelIteratorON.add(bIterator[i]);
			if ( i==2 ) bIterator[i].setEnabled(false);
		}
		//////////////////////////////////////////////////////////////////////////////7

		panelCollectionMethods.add(panelIterator);


		//-----------------------------LIST METHODS--------------------------------------------//
		panelLMTitolo = new JPanel();
		panelLMTitolo.setLayout( new BorderLayout() );
		lLMTitolo.setFont(new Font("sansserif",Font.BOLD,30));
		panelLMTitolo.add(lLMTitolo, BorderLayout.WEST);
		this.getContentPane().add(panelLMTitolo);

		panelListMethods = new JPanel();
		this.getContentPane().add(panelListMethods);

		panelBListMethods = new JPanel();
		panelBListMethods.setLayout( new GridLayout(6, 2));
		for (int i = 0; i < bListMethods.length; i++) {
			bListMethods[i].setFont( new Font(null, Font.BOLD, 15));
			if ( i==6||i==9||i==10 ) bListMethods[i].setEnabled(false);
			panelBListMethods.add(bListMethods[i]);
		}
		panelListMethods.add(panelBListMethods);

		panelListIterator = new JPanel();
		panelListIterator.setLayout( new BoxLayout(panelListIterator, BoxLayout.Y_AXIS) );
		panelListIterator.setBorder(BorderFactory.createTitledBorder(" L I S T - I T E R A T O R "));

		//////////////////////////////////////////////////////////
		bAttivaListIterator.setFont( new Font("sansserif",Font.BOLD,20) );
		panelListIterator.add(bAttivaListIterator);
		cbSpuntaLIPOS.setFont( new Font("sansserif",Font.ITALIC,16) );
		panelListIterator.add(cbSpuntaLIPOS);

		panelListIteratorON = new JPanel();
		panelListIteratorON.setLayout(  new GridLayout(6, 2) );  
		lListIterator.setFont(new Font("sansserif",Font.BOLD,25));
		panelListIteratorON.add(lListIterator);
		panelListIteratorON.add(Box.createRigidArea(new Dimension(0,0)));
		for (int i = 0; i < bListIterator.length; i++) {
			bListIterator[i].setFont( new Font(null, Font.BOLD, 15));
			if ( i==5 || i==7 || i==8 ) bListIterator[i].setEnabled(false);
			panelListIteratorON.add(bListIterator[i]);
		}
		/////////////////////////////////////////////////////////////

		panelListMethods.add(panelListIterator);

		//------------------------LINKED LIST METHODS-------------------------------------//
		if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ){

			panelLinkedListTitolo = new JPanel();
			panelLinkedListTitolo.setLayout( new BorderLayout() );
			lLinkedListTitolo.setFont(new Font("sansserif",Font.ITALIC,27));
			panelLinkedListTitolo.add(lLinkedListTitolo, BorderLayout.WEST);
			this.getContentPane().add(panelLinkedListTitolo);

			panelLinkedListMethods = new JPanel();
			this.getContentPane().add(panelLinkedListMethods);

			panelBLinkedListMethods = new JPanel();
			panelBLinkedListMethods.setLayout( new GridLayout(1, 6));
			for (int i = 0; i < bLinkedListMethods.length; i++) {
				bLinkedListMethods[i].setFont( new Font(null, Font.BOLD, 15));
				if ( i==0||i==1||i==2||i==3 ) bLinkedListMethods[i].setEnabled(false);
				panelBLinkedListMethods.add(bLinkedListMethods[i]);
			}
			panelLinkedListMethods.add(panelBLinkedListMethods);
			this.getContentPane().add(Box.createRigidArea(new Dimension(xSize, 20)));

		}
		else this.getContentPane().add(Box.createRigidArea(new Dimension(xSize, 20)));

		inizializzaAscoltatori();

	}//COSTRUTTORE

	
	private void inizializzaAscoltatori(){
		for (int i = 0; i < bCollectionMethods.length; i++) {
			bCollectionMethods[i].addActionListener( new listenerBCollection() );
		}

		bAttivaIterator.addActionListener( new listenerIteratoriON() );
		for (int i = 0; i < bIterator.length; i++) {
			bIterator[i].addActionListener( new listenerIterator() );
		}

		for (int i = 0; i < bListMethods.length; i++) {
			bListMethods[i].addActionListener( new listenerBList() );
		}

		bAttivaListIterator.addActionListener( new listenerIteratoriON() );
		for (int i = 0; i < bListIterator.length; i++) {
			bListIterator[i].addActionListener( new listenerListIterator() );
		}
		
		for (int i = 0; i < bLinkedListMethods.length; i++) {
			bLinkedListMethods[i].addActionListener( new listenerBLinkedList() );
		}
		
	}//inizializzaAscoltatori


	private class listenerBCollection implements ActionListener {

		@SuppressWarnings("static-access")
		@Override
		public void actionPerformed(ActionEvent e) {
			int i = 0;
			metodiCollection metodoInvocato;

			for (i = 0; i < bCollectionMethods.length; i++) 
				if ( e.getSource()==bCollectionMethods[i] ) break;

			switch (i) {
			case 1: metodoInvocato = metodiCollection.CLEAR; break;
			case 2: metodoInvocato = metodiCollection.SIZE; break;
			case 3: metodoInvocato = metodiCollection.ADD_ALL; break;
			case 4: metodoInvocato = metodiCollection.IS_EMPTY; break;
			case 5: metodoInvocato = metodiCollection.CONTAINS; break;
			case 6: metodoInvocato = metodiCollection.CONTAINS_ALL; break;
			case 7: metodoInvocato = metodiCollection.REMOVE; break;
			case 8: metodoInvocato = metodiCollection.REMOVE_ALL; break;
			case 9: metodoInvocato = metodiCollection.RETAIN_ALL; break;
			case 10: metodoInvocato = metodiCollection.TO_ARRAY_OBJECT; break;
			case 11: metodoInvocato = metodiCollection.TO_ARRAY_T; break;
			default: metodoInvocato = metodiCollection.ADD;
			}

			taCorrente.setText("?");

			if ( metodoInvocato==metodoInvocato.SIZE )
				taRisultato.setText(ctg.toStringSize());
			else if ( metodoInvocato==metodoInvocato.IS_EMPTY )
				taRisultato.setText(ctg.toStringIsEmpty());
			else if ( metodoInvocato==metodoInvocato.CLEAR ) {
				ctg.clear();
				if ( iteratorAttivo ) {
					for (int j = 0; j < bIterator.length; j++) 
						bIterator[j].setEnabled(false);
				}
				else if ( listIteratorAttivo ) {
					for (int j = 0; j < bListIterator.length; j++) 
						bListIterator[j].setEnabled(false);
				}
				else {
					panelIterator.getComponent(0).setEnabled(true);
					panelListIterator.getComponent(0).setEnabled(true);
					panelListIterator.getComponent(1).setEnabled(true);
				}

				bListMethods[6].setEnabled(false);
				bListMethods[9].setEnabled(false);
				bListMethods[10].setEnabled(false);
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					bLinkedListMethods[0].setEnabled(false);
					bLinkedListMethods[1].setEnabled(false);
					bLinkedListMethods[2].setEnabled(false);
					bLinkedListMethods[3].setEnabled(false);
				}

				taLista.setText(ctg.toStringListaAttiva());
				taRisultato.setText(null);
			}
			else if ( metodoInvocato==metodoInvocato.TO_ARRAY_OBJECT )
				taRisultato.setText(ctg.toStringToArray());
			else {
				if ( iteratorAttivo ) {
					configIteratoreAttivo = salvaConfigurazioneforIterator();
					for (int j = 0; j < bIterator.length; j++) {
						bIterator[j].setEnabled(false);
					}
				}
				else if ( listIteratorAttivo ) {
					configIteratoreAttivo = salvaConfigurazioneforListIterator();
					for (int j = 0; j < bListIterator.length; j++) {
						bListIterator[j].setEnabled(false);
					}				}
				else {
					panelIterator.getComponent(0).setEnabled(false);
					panelListIterator.getComponent(0).setEnabled(false);
					panelListIterator.getComponent(1).setEnabled(false);
				}
				ffcb = new FrameForCollectionButtons(FrameOfMethods.this, metodoInvocato);
				ffcb.setVisible(true);
				
				for (int j = 0; j < bCollectionMethods.length; j++) 
					bCollectionMethods[j].setEnabled(false);
				
				for (int j = 0; j < bListMethods.length; j++) 
					bListMethods[j].setEnabled(false);
				
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					for (int j = 0; j < bLinkedListMethods.length; j++) 
						bLinkedListMethods[j].setEnabled(false);
				}
				
			}

		}

	}//listenerBCollection

	private class listenerIterator implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = 0;

			for (i = 0; i < bIterator.length; i++) 
				if ( e.getSource()==bIterator[i] ) break;

			switch (i) {
			case 1:
				String cur = ctg.iteratorNext();
				taRisultato.setText(cur);
				taCorrente.setText(cur);
				taLista.setText(ctg.toStringListaIterata);;
				bIterator[2].setEnabled(true); //abilita remove
				if ( Boolean.parseBoolean(ctg.iteratorHasNext())==false ) 
					bIterator[1].setEnabled(false);
				break;
			case 2:
				ctg.iteratorRemove();
				taLista.setText(ctg.toStringListaIterata);
				taRisultato.setText(null);
				taCorrente.setText("?");
				bIterator[2].setEnabled(false); //disabilita remove
				
				if ( Integer.parseInt(ctg.toStringSize())==0 ) {
					bListMethods[6].setEnabled(false);
					bListMethods[9].setEnabled(false);
					bListMethods[10].setEnabled(false);	
				}
				
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					for (int j = 0; j < bLinkedListMethods.length; j++) 
						bLinkedListMethods[j].setEnabled(true);
					if ( Integer.parseInt(ctg.toStringSize())==0 ) {
						bLinkedListMethods[0].setEnabled(false);
						bLinkedListMethods[1].setEnabled(false);
						bLinkedListMethods[2].setEnabled(false);
						bLinkedListMethods[3].setEnabled(false);
					}
				}
				
				break;					
			default: taRisultato.setText( ctg.iteratorHasNext() ); break;				
			}
			
		}

	}//listenerIterator

	private class listenerBList implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = 0;
			metodiList metodoInvocato;

			for (i = 0; i < bListMethods.length; i++) 
				if ( e.getSource()==bListMethods[i] ) break;

			switch (i) {
			case 1: metodoInvocato = metodiList.BINARY_SEARCH_COMPARATOR; break;
			case 2: metodoInvocato = metodiList.SORT_COMPARATOR; break;
			case 3: metodoInvocato = metodiList.SORT_NATURALE; break;
			case 4: metodoInvocato = metodiList.ADD; break;
			case 5: metodoInvocato = metodiList.ADD_ALL; break;
			case 6: metodoInvocato = metodiList.GET; break;
			case 7: metodoInvocato = metodiList.INDEX_OF; break;
			case 8: metodoInvocato = metodiList.LAST_INDEX_OF; break;
			case 9: metodoInvocato = metodiList.REMOVE; break;
			case 10: metodoInvocato = metodiList.SET; break;
			default: metodoInvocato = metodiList.BINARY_SEARCH_NATURALE;
			}
			
			taCorrente.setText("?");
			

			if ( iteratorAttivo ) {
				configIteratoreAttivo = salvaConfigurazioneforIterator();
				for (int j = 0; j < bIterator.length; j++) {
					bIterator[j].setEnabled(false);
				}
			}
			else if ( listIteratorAttivo ) {
				configIteratoreAttivo = salvaConfigurazioneforListIterator();
				for (int j = 0; j < bListIterator.length; j++) {
					bListIterator[j].setEnabled(false);
				}				
			}
			else {
				panelIterator.getComponent(0).setEnabled(false);
				panelListIterator.getComponent(0).setEnabled(false);
				panelListIterator.getComponent(1).setEnabled(false);
			}
			fflb = new FrameForListButtons(FrameOfMethods.this, metodoInvocato);
			fflb.setVisible(true);
			
			for (int j = 0; j < bCollectionMethods.length; j++) 
				bCollectionMethods[j].setEnabled(false);
			
			for (int j = 0; j < bListMethods.length; j++) 
				bListMethods[j].setEnabled(false);
			
			if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
				for (int j = 0; j < bLinkedListMethods.length; j++) 
					bLinkedListMethods[j].setEnabled(false);
			}

		}

	}//listenerBCollection

	private class listenerListIterator implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int i = 0;
			boolean casoDef_8 = false;
			for (i = 0; i < bListIterator.length; i++) 
				if ( e.getSource()==bListIterator[i] ) break;

			switch (i) {
			case 1: 
				taRisultato.setText(ctg.listIteratorHasNext());
				break;			
			case 2: 
				taRisultato.setText(ctg.listIteratorHasPrevious());
				break;
			case 3:
				String curN = ctg.listIteratorNext();
				taRisultato.setText(curN);
				taCorrente.setText(curN);
				taLista.setText(ctg.toStringListaIterata);
				bListIterator[7].setEnabled(true); //abilita remove
				bListIterator[8].setEnabled(true); //abilita set
				bListIterator[5].setEnabled(true); //abilita previous
				if ( ctg.listIteratorHasNext().equals("false") ) bListIterator[3].setEnabled(false);
				break;
			case 4:
				taRisultato.setText(ctg.listIteratorNextIndex());
				break;
			case 5:
				String curP = ctg.listIteratorPrevious();
				taRisultato.setText(curP);
				taCorrente.setText(curP);
				taLista.setText(ctg.toStringListaIterata);
				bListIterator[7].setEnabled(true); //abilita remove
				bListIterator[8].setEnabled(true); //abilita set
				bListIterator[3].setEnabled(true); //abilita next
				if ( ctg.listIteratorHasPrevious().equals("false") ) bListIterator[5].setEnabled(false);
				break;
			case 6:
				taRisultato.setText(ctg.listIteratorPrevioustIndex());
				break;
			case 7:
				ctg.listIteratorRemove();
				taLista.setText(ctg.toStringListaIterata);
				taRisultato.setText(null);
				taCorrente.setText("?");
				bListIterator[7].setEnabled(false); //disabilita remove
				bListIterator[8].setEnabled(false); //disabilita set
				if ( ctg.listIteratorHasPrevious().equals("false") ) bListIterator[5].setEnabled(false); //disattica previous
				if ( ctg.listIteratorHasNext().equals("false") ) bListIterator[3].setEnabled(false); //disattiva next
				
				if ( Integer.parseInt(ctg.toStringSize())==0 ) {
					bListMethods[6].setEnabled(false);
					bListMethods[9].setEnabled(false);
					bListMethods[10].setEnabled(false);	
				}
				
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					for (int j = 0; j < bLinkedListMethods.length; j++) 
						bLinkedListMethods[j].setEnabled(true);
					if ( Integer.parseInt(ctg.toStringSize())==0 ) {
						bLinkedListMethods[0].setEnabled(false);
						bLinkedListMethods[1].setEnabled(false);
						bLinkedListMethods[2].setEnabled(false);
						bLinkedListMethods[3].setEnabled(false);
					}
				}
				
				break;					
			case 8:
				casoDef_8 = true;
				ffli = new FrameForListIterator(FrameOfMethods.this, metodiListIterator.SET);
				break;
			default: 
				casoDef_8 = true;
				ffli = new FrameForListIterator(FrameOfMethods.this,metodiListIterator.ADD);				
			}
			if ( casoDef_8 ) {

				configIteratoreAttivo = salvaConfigurazioneforListIterator();
				ffli.setVisible(true);
				
				for (int j = 0; j < bCollectionMethods.length; j++) {
					bCollectionMethods[j].setEnabled(false);
				}
				for (int j = 0; j < bListMethods.length; j++) {
					bListMethods[j].setEnabled(false);
				}
				for (int j = 0; j < bListIterator.length; j++) {
					bListIterator[j].setEnabled(false);
				}
				
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					for (int j = 0; j < bLinkedListMethods.length; j++) 
						bLinkedListMethods[j].setEnabled(false);
				}
			}

		}

	}//listenerListIterator
	
	private class listenerBLinkedList implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = 0;
			metodiLinkedList metodoInvocato;

			for (i = 0; i < bLinkedListMethods.length; i++) 
				if ( e.getSource()==bLinkedListMethods[i] ) break;

			switch (i) {
			case 1: metodoInvocato = metodiLinkedList.REMOVE_LAST; break;
			case 2: metodoInvocato = metodiLinkedList.GET_FIRST; break;
			case 3: metodoInvocato = metodiLinkedList.GET_LAST; break;
			case 4: metodoInvocato = metodiLinkedList.ADD_FIRST; break;
			case 5: metodoInvocato = metodiLinkedList.ADD_LAST; break;
			default: metodoInvocato = metodiLinkedList.REMOVE_FIRST;
			}
			
			taCorrente.setText("?");
			
			if ( metodoInvocato==metodiLinkedList.REMOVE_FIRST||metodoInvocato==metodiLinkedList.REMOVE_LAST ){
				if ( metodoInvocato==metodiLinkedList.REMOVE_FIRST ) 
					taRisultato.setText(ctg.toStringRemoveFirst());
					
				else if ( metodoInvocato==metodiLinkedList.REMOVE_LAST ) 
					taRisultato.setText(ctg.toStringRemoveLast());
				
				taLista.setText(ctg.toStringListaAttiva());
				
				if ( Integer.parseInt(ctg.toStringSize())==0 ) {
					bListMethods[6].setEnabled(false);
					bListMethods[9].setEnabled(false);
					bListMethods[10].setEnabled(false);	
				}
				
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					for (int j = 0; j < bLinkedListMethods.length; j++) 
						bLinkedListMethods[j].setEnabled(true);
					if ( Integer.parseInt(ctg.toStringSize())==0 ) {
						bLinkedListMethods[0].setEnabled(false);
						bLinkedListMethods[1].setEnabled(false);
						bLinkedListMethods[2].setEnabled(false);
						bLinkedListMethods[3].setEnabled(false);
					}
				}
			}
			
			else if ( metodoInvocato==metodiLinkedList.GET_FIRST ) 
				taRisultato.setText(ctg.toStringGetFirst());

			else if ( metodoInvocato==metodiLinkedList.GET_LAST ) 
				taRisultato.setText(ctg.toStringGetLast());

			else {
				if ( iteratorAttivo ) {
					configIteratoreAttivo = salvaConfigurazioneforIterator();
					for (int j = 0; j < bIterator.length; j++) {
						bIterator[j].setEnabled(false);
					}
				}
				else if ( listIteratorAttivo ) {
					configIteratoreAttivo = salvaConfigurazioneforListIterator();
					for (int j = 0; j < bListIterator.length; j++) {
						bListIterator[j].setEnabled(false);
					}				}
				else {
					panelIterator.getComponent(0).setEnabled(false);
					panelListIterator.getComponent(0).setEnabled(false);
					panelListIterator.getComponent(1).setEnabled(false);
				}
				ffllb = new FrameForLinkedListButtons(FrameOfMethods.this, metodoInvocato);
				ffllb.setVisible(true);
				
				for (int j = 0; j < bCollectionMethods.length; j++) 
					bCollectionMethods[j].setEnabled(false);
				
				for (int j = 0; j < bListMethods.length; j++) 
					bListMethods[j].setEnabled(false);
				
				if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
					for (int j = 0; j < bLinkedListMethods.length; j++) 
						bLinkedListMethods[j].setEnabled(false);
				}
			}

		}

	}//listenerBLinkedList

	private class listenerIteratoriON implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if ( e.getSource()==bAttivaIterator ) {
				ctg.creaIterator();
				iteratorAttivo = true;
				taLista.setText(ctg.toStringListaIterata);
				panelIterator.remove(bAttivaIterator);
				panelIterator.repaint();
				panelIterator.revalidate();
				if ( ctg.toStringSize().equals("0") ) bIterator[1].setEnabled(false);
				panelIterator.add(panelIteratorON);
				for (int i = 0; i < panelListIterator.getComponentCount(); i++) {
					panelListIterator.getComponent(i).setEnabled(false);
				}
			}
			else {
				listIteratorAttivo = true;
				if ( cbSpuntaLIPOS.isSelected() ) {
					configIteratoreAttivo = salvaConfigurazioneforListIterator();
					ffli = new FrameForListIterator(FrameOfMethods.this,null);
					ffli.setVisible(true);
					for (int i = 0; i < bCollectionMethods.length; i++) {
						bCollectionMethods[i].setEnabled(false);
					}
					for (int i = 0; i < bListMethods.length; i++) {
						bListMethods[i].setEnabled(false);
					}
					for (int i = 0; i < bListIterator.length; i++) {
						bListIterator[i].setEnabled(false);
					}
					if ( CollectionToGrafica.tipoList==SceltaList.LINKED_LIST ) {
						for (int j = 0; j < bLinkedListMethods.length; j++) 
							bLinkedListMethods[j].setEnabled(false);
					}
				}
				else {
					ctg.creaListIterator();
					taLista.setText(ctg.toStringListaIterata);
				}
				panelListIterator.remove(bAttivaListIterator);
				panelListIterator.remove(cbSpuntaLIPOS);
				panelListIterator.repaint();
				panelListIterator.revalidate();
				if ( ctg.toStringSize().equals("0") ) {
					bListIterator[3].setEnabled(false);
					bListIterator[5].setEnabled(false);
				}
				panelListIterator.add(panelListIteratorON);
				panelIterator.getComponent(0).setEnabled(false);
			}
			taCorrente.setText("?");
		}

	}//listenerIteratoriON

	//////////////////////////////////////////////////////////////////////////

	private boolean[] salvaConfigurazioneforListIterator() {
		boolean[] ris = new boolean[bListIterator.length];
		for (int i = 0; i < bListIterator.length; i++) 
			ris[i] = bListIterator[i].isEnabled();
		return ris;
	}

	private boolean[] salvaConfigurazioneforIterator() {
		boolean[] ris = new boolean[bIterator.length];
		for (int i = 0; i < bIterator.length; i++) 
			ris[i] = bIterator[i].isEnabled();
		return ris;
	}

	private boolean consensoUscita(){
		int option=JOptionPane.showConfirmDialog(
				null, "Continuare ?","Sei deciso a terminare...", JOptionPane.YES_NO_OPTION);
		return option==JOptionPane.YES_OPTION;
	}//consensoUscita

	///////////////////////////////////////////////////////////////////////

}//FrameOfMethods

