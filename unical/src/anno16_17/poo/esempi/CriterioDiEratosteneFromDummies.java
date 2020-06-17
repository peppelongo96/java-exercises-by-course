package anno16_17.poo.esempi;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CriterioDiEratosteneFromDummies {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("N(>= 2): "); int n = sc.nextInt();
		sc.nextLine();
		Set<Integer> crivello = new TreeSet<Integer>();
		Set<Integer> primi = new TreeSet<Integer>();
		for (int i = 2; i <= n; i++) 
			crivello.add(i); //popola crivello
		for (int i = 2; i <= n/2; i++) {
			if (crivello.contains(i)){
				primi.add(i);
				int m = i*2;
				while (m <= n){
					crivello.remove(m);
					m+=i;
				}
			}
		}
		System.out.println(primi); //stampa set dal più piccolo al più grande
		sc.close();
	}

}
