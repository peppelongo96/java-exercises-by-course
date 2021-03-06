package anno16_17.poo.eserciziPerEsame;

import java.util.Scanner;

public class TreeCharMia {
	
	private static class Nodo {
		char info;
		Nodo sx, dx;
	}//Nodo
	
	private int pos = -1;
	private Nodo radice;
	
	public void build(String clp) {
		radice = buildRic(clp,0);
	}//build
	
	private Nodo buildRic(String clp, int pos) {
		if ( clp.charAt(pos)=='.' ) 
			return null;
		Nodo nuovo = new Nodo();
		nuovo.info = clp.charAt(pos);
		nuovo.sx = buildRic( clp,pos+1);
		nuovo.dx = buildRic(clp, pos+2);
		return nuovo;
	}//build
	
	public String toString() {
		StringBuilder sb = new StringBuilder(200);
		sb.append('[');
		toString(radice,sb);
		if(radice != null) sb.setLength(sb.length() - 2);
		sb.append(']');
		return sb.toString();
	}//toString
	
	private void toString(Nodo nodo, StringBuilder sb) {
		if(nodo == null) return;
		toString(nodo.sx,sb);
		sb.append(nodo.info); sb.append(", ");
		toString(nodo.dx,sb);
	}//toString
	
	public boolean equals(Object x) {
		if(!(x instanceof TreeChar)) return false;
		if(x == this) return true;
		TreeCharMia t = (TreeCharMia) x;
		return equals(radice,t.radice);
	}//equals
	
	private boolean equals(Nodo n1, Nodo n2) {
		if(n1 == null && n2 == null) return true;
		if(n1 == null || n2 == null || n1.info != n2.info) return false;
		return equals(n1.sx,n2.sx) && equals(n1.dx,n2.dx);
	}//equals
	
	public int hashCode(){
		return hashCode(radice);
	}//hashCode

	private int hashCode(Nodo nodo){
		if(nodo == null) return 0;
		return Character.hashCode(nodo.info) * 13 + hashCode(nodo.sx) + hashCode(nodo.dx);
	}//hashCode
	
	public void postOrder() {
		postOrder(radice);
		System.out.println();
	}//postOrder

	private void postOrder(Nodo nodo) {
		if(nodo == null) { System.out.print("."); return; }
		postOrder(nodo.sx);
		postOrder(nodo.dx);
		System.out.print(nodo.info);
	}//postOrder
	
	public void preOrder() {
		preOrder(radice);
		System.out.println();
	}//postOrder

	private void preOrder(Nodo nodo) {
		if(nodo == null) { System.out.print("."); return; }
		System.out.print(nodo.info);
		preOrder(nodo.sx);
		preOrder(nodo.dx);
	}//postOrder
	
	public void inOrder() {
		inOrder(radice);
		System.out.println();
	}//inOrder

	private void inOrder(Nodo nodo) {
		if(nodo == null) { System.out.print("."); return; }
		inOrder(nodo.sx);
		System.out.print(nodo.info);
		inOrder(nodo.dx);
	}//inOrder
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String clp;
		do {
			System.out.print("Inserisci l'albero da costruire: ");
			clp = sc.nextLine();
		} while(!clp.matches("[A-Z\\.]+"));
		sc.close();
		TreeCharMia t = new TreeCharMia();
		t.build(clp);
		System.out.println("Visita pre-order");
		t.preOrder();
		System.out.println("Visita in-order");
		t.inOrder();
		System.out.println("Visita post-order");
		t.postOrder();
	}//main
}//TreeChar