package anno16_17.poo.progettoEsame.CollectionTestGUI;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.StringTokenizer;

import anno16_17.poo.progettoEsame.*;


class CollectionToGrafica<T> {
	
	enum SceltaTipo { STRING, INTEGER };
	
	enum SceltaList { LINKED_LIST, ARRAY_LIST };
	
	enum metodiCollection { ADD, CLEAR, SIZE, ADD_ALL, IS_EMPTY, CONTAINS, CONTAINS_ALL,
		REMOVE, REMOVE_ALL, RETAIN_ALL, TO_ARRAY_OBJECT, TO_ARRAY_T }; 
	
	enum metodiList { BINARY_SEARCH_NATURALE, BINARY_SEARCH_COMPARATOR, SORT_COMPARATOR,
		SORT_NATURALE, ADD, ADD_ALL, GET, INDEX_OF, LAST_INDEX_OF, REMOVE, SET };
		
	enum metodiListIterator { ADD, HAS_NEXT, HAS_PREVIOUS, NEXT, NEXT_INDEX, PREVIOUS,
		PREVIOUS_INDEX, REMOVE, SET }
	
	enum metodiLinkedList { REMOVE_FIRST, REMOVE_LAST, GET_FIRST, GET_LAST, ADD_FIRST, ADD_LAST }
	
	///////////////////////////////////////////////////////////7
	static SceltaTipo tipo = SceltaTipo.STRING;
	static SceltaList tipoList = SceltaList.ARRAY_LIST;
	static String tipoForButtons;
	static String tipoForButtonsBoxed;
	///////////////////////////////////////////////////////////7


	////////////////////////////////////////////////////////////////////////////////////////////
	
	private List<T> listaAttiva;
	
	void generaArrayList(int dim){
		listaAttiva = new ArrayList<>(dim);
	}
	
	void generaArrayList(){
		listaAttiva = new ArrayList<>();
	}
	
	LinkedList<T> listaAttivaLL;
	void generaLinkedList(){
		listaAttiva = new LinkedList<>();
		listaAttivaLL = (LinkedList<T>)listaAttiva;
	}//generaLinkedList
	
	
//--------------------------------------------------------------------//
	
	private enum Posizione { PRIMA, DOPO }
	private int indicePosizione = -1;

	String toStringListaIterata;
	boolean iteratorAttivo = false;
	boolean listIteratorAttivo = false;
	
	//iterator
	private Iterator<T> it;

	void creaIterator() {
		it = listaAttiva.iterator();
		iteratorAttivo = true;
		toStringListaIterata = toStringForIteratori(Posizione.PRIMA, 0);
	}//creaIterator

	String iteratorHasNext() {
		return Boolean.toString(it.hasNext());
	}//iteratorHasNext

	String iteratorNext() {
		indicePosizione++;
		toStringListaIterata = toStringForIteratori(Posizione.DOPO, indicePosizione);
		T next = it.next();
		if ( next==null ) return null;
		return next.toString();
	}//iteratorNext

	void iteratorRemove() {
		it.remove();
		indicePosizione--;
		if ( indicePosizione==-1 ) toStringListaIterata = toStringForIteratori(Posizione.PRIMA, 0);
		else toStringListaIterata = toStringForIteratori(Posizione.DOPO, indicePosizione);
	}//iteratorRemove


	//listIterator
	private ListIterator<T> listIt;
	private enum Direzione { NEXT, PREVIOUS }
	private Direzione dir;

	void creaListIterator() {
		listIt = listaAttiva.listIterator();
		listIteratorAttivo = true;
		toStringListaIterata = toStringForIteratori(Posizione.PRIMA, 0);
	}

	void creaListIterator( int dim ) {
		listIt = listaAttiva.listIterator( dim );
		listIteratorAttivo = true;
		indicePosizione = dim-1;
		toStringListaIterata = toStringForIteratori(Posizione.DOPO, indicePosizione);
	}

	String listIteratorHasNext() {
		return Boolean.toString(listIt.hasNext());
	}

	String listIteratorHasPrevious() {
		return Boolean.toString(listIt.hasPrevious());
	}

	String listIteratorNext() {
		indicePosizione++;
		toStringListaIterata = toStringForIteratori(Posizione.DOPO, indicePosizione);
		dir = Direzione.NEXT;
		T next = listIt.next();
		if ( next==null ) return null;
		return next.toString();
	}

	String listIteratorNextIndex() {
		return Integer.toString(listIt.nextIndex());
	}

	String listIteratorPrevious() {
		indicePosizione--;
		if ( indicePosizione==-1 ) toStringListaIterata = toStringForIteratori(Posizione.PRIMA, 0);
		else toStringListaIterata = toStringForIteratori(Posizione.DOPO, indicePosizione);
		dir = Direzione.PREVIOUS;
		T previous = listIt.previous();
		if ( previous==null ) return null;
		return previous.toString();
	}

	String listIteratorPrevioustIndex() {
		return Integer.toString(listIt.previousIndex());
	}

	void listIteratorRemove() {
		listIt.remove();
		if ( dir==Direzione.NEXT ) indicePosizione--;
		if ( indicePosizione==-1 ) toStringListaIterata = toStringForIteratori(Posizione.PRIMA, 0);
		else toStringListaIterata = toStringForIteratori(Posizione.DOPO, indicePosizione);
	} 

	void listIteratorSet(T tGen) {
		listIt.set(tGen);
		if ( indicePosizione==-1 ) toStringListaIterata = toStringForIteratori(Posizione.PRIMA, 0);
		else toStringListaIterata = toStringForIteratori(Posizione.DOPO, indicePosizione);
	}

	void listIteratorAdd(T tGen) {
		listIt.add(tGen);
		indicePosizione++;
		toStringListaIterata = toStringForIteratori(Posizione.DOPO, indicePosizione);	
	}

	//------------------------------------------------------//

	//METODI COLECTION

	String toStringSize() {
		return Integer.toString(listaAttiva.size());
	}//toStringSize

	String toStringIsEmpty() {
		return Boolean.toString(listaAttiva.isEmpty());
	}//toStringIsEmpty

	void clear() {
		listaAttiva.clear();
	}//clear

	String toStringAdd( T tGen ) {
		return Boolean.toString(listaAttiva.add(tGen));
	}//toStringAdd
	
	String toStringAddAll( Collection<T> c ) {
		return Boolean.toString(listaAttiva.addAll(c));
	}//toStringAddAll
	
	String toStringContains ( T tGen ) {
		return Boolean.toString(listaAttiva.contains(tGen));
	}//toStringContains
	
	String toStringContainsAll( Collection<T> c ) {
		return Boolean.toString(listaAttiva.containsAll(c));
	}//toStringContainsAll
	
	boolean remove( T tGen ) {
		return listaAttiva.remove(tGen);
	}//toStringRemove
	
	boolean removeAll( Collection<T> c ) {
		return listaAttiva.removeAll(c);
	}//toStringRemoveAll
	
	boolean retainAll( Collection<T> c ) {
		return listaAttiva.retainAll(c);
	}//toStringRetainAll
	
	String toStringToArray() {
		return Arrays.toString(listaAttiva.toArray());
	}//toStringToArray
	
	<E> String toStringToArray( E[] array) {
		return Arrays.toString(listaAttiva.toArray(array));
	}//toStringToArray(T[])
	
	
	//METODI LIST
	
	<E extends Comparable<? super E>> String toStringBinarySearch( List<E> l, E e ) {
		return Integer.toString(List.binarySearch(l, e));
	}//toStringBinarySearch
	
	String toStringBinarySearch( Comparator<T> comp, T e) {
		return Integer.toString(listaAttiva.binarySearch(comp, e));
	}//toStringBinarySearch
	
	void sort( Comparator<T> comp ) {
		listaAttiva.sort(comp);
	}//sort
	
	<E extends Comparable<? super E>> void sort( List<E> l ) {
		List.sort(l);
	}//sort

	void add( int indice, T e ) {
		listaAttiva.add(indice, e);
	}//add
	
	void addAll( int indice, Collection<T> c) {
		listaAttiva.addAll(indice, c);
	}//addAll
	
	String toStringGet( int indice ) {
		return listaAttiva.get(indice).toString();
	}//addAll
	
	String toStringIndexOf( T e ) {
		return Integer.toString(listaAttiva.indexOf(e));
	}//toStringIndexOf
	
	String toStringlastIndexOf( T e ) {
		return Integer.toString(listaAttiva.lastIndexOf(e));
	}//toStringLastIndexOf
	
	String toStringRemove( int indice) {
		return listaAttiva.remove(indice).toString();
	}//toStringRemove
	
	String toStringSet( int indice, T e) {
		return listaAttiva.set(indice, e).toString();
	}//toStrinSet
	
	
	//METODI LINKED LIST
	
	
	String toStringRemoveFirst() {
		return listaAttivaLL.removeFirst().toString();
	}//toStringRemoveFirst
	
	String toStringRemoveLast() {
		return listaAttivaLL.removeLast().toString();
	}//toStringRemoveLast
	
	String toStringGetFirst() {
		return listaAttivaLL.getFirst().toString();
	}//toStringGetFirst
	
	String toStringGetLast() {
		return listaAttivaLL.getLast().toString();
	}//toStringGetLast
	
	void addFirst( T e ) {
		listaAttivaLL.addFirst(e);
	}//addFirst
	
	void addLast( T e ) {
		listaAttivaLL.addLast(e);
	}//addLast
	

	//---------------------------------------------------------------------------------------------------------------------------------
	
	String toStringListaAttiva() {
		return listaAttiva.toString();
	}//toStringListaAttiva

	private String toStringForIteratori(Posizione op, int pos) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		if ( listaAttiva.size()==0 ) sb.append(" ^");
		else {
			StringTokenizer st = new StringTokenizer(toStringListaAttiva(),"[],");
			int contPos = 0;
			while ( st.hasMoreTokens() ) {
				String founded = st.nextToken();
				if ( contPos==pos ) {
					if ( op==Posizione.DOPO ) founded = founded+"  ^";
					else founded = "^  "+founded;
				}
				sb.append("  "+founded);
				contPos++;
			}
		}
		sb.append(" ]");
		return sb.toString();
	}//toStringForIteratori

}
