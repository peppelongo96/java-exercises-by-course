package anno16_17.poo.esempi;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CriterioDiEratosteneFromFox {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("N(>= 2): "); int n = sc.nextInt();
		sc.nextLine();
		Set<Integer> crivello = new TreeSet();
		for (int i = 2; i <= n; i++) 
			crivello.add(i); //popola crivello
		for (int i = 2; i <= Math.round(Math.sqrt(n)); i = (i==2)? 3:i+2) { //la nostra i sarà minore della radice di n e salterà i multipli di 2
			if (crivello.contains(i)){
				int m = i*2;
				while (m <= n){
					crivello.remove(m);
					m+=i;
				}
			}
		}
		
		Iterator<Integer> it = crivello.iterator();
		int c = 0;
		while (it.hasNext()){
			int p = it.next(); // per autounboxing
			c++;
			System.out.printf("%11d", p);
			if (c%10==0) System.out.println();
		}
		System.out.println();
		sc.close();
	}
	
}
