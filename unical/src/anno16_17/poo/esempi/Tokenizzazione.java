package anno16_17.poo.esempi;
import java.util.*;

class Tokenizzazione{
	private static Scanner sc;	
	
	//tokenizzazione di una stringa da input
	public static void Esempio1(){
		sc = new Scanner(System.in);
		String linea = sc.nextLine();
		StringTokenizer st = new StringTokenizer(linea," .;,:!?");
		while (st.hasMoreTokens()){
			String parola = st.nextToken().toUpperCase();
			System.out.print(parola);
		}
		sc.close();
	}
	
	//tokenizzazione per risolvere un'espressione algebrica (segni equivalenti!)
	public static void Esempio2(){
		sc = new Scanner(System.in);
		System.out.println("Fornisci un'espressione intera");
		System.out.println("Operatori: +-/* assunti equiprobabili!");
		String espr = sc.nextLine();
		StringTokenizer st = new StringTokenizer(espr,"+-/*",true);
		int ris = 0;
		ris = Integer.parseInt(st.nextToken());
		while(st.hasMoreTokens()){
			char op = st.nextToken().charAt(0);
			int pros = Integer.parseInt(st.nextToken());
			switch(op){
			case '+': ris+=pros;break;
			case '-': ris-=pros; break;
			case '*': ris*=pros; break;
			default: ris/=pros;
			}
		}//while
		System.out.println(espr+" = "+ris);
		sc.close();
	}
	
	//tokenizzazione attraverso Scanner
	public static void Esempio3(){
		sc = new Scanner(System.in);
		String linea = sc.nextLine();
		Scanner sl = new Scanner(linea);
		sl.useDelimiter("[\\W]+"); //indica caratteri non presenti in una generica parola
		while(sl.hasNext()){
			String token = sl.next();
			System.out.println(token);
		}
		sc.close();
		sl.close();
	}
	
	public static void main(String[] args) {
		//inserisci esempio da eseguire
	
	}
		

}
