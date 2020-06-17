package anno16_17.poo.progettoEsame;

import java.util.ListIterator;
//import java.util.ArrayList;

public class ProvaMyAL {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>();
		a.add(12);
		a.add(12);
		a.add(12);
		a.add(1,99);

		ListIterator<Integer> it = a.listIterator();
		List.sort(a);
		System.out.println(a);
		it.next();
		it.next();
		it.next();
		it.remove();
		it.add(18);
		System.out.println(a);
		it.previous();
		it.set(999999);
		it.previous();
		it.remove();
		System.out.println(a);
	}

}
