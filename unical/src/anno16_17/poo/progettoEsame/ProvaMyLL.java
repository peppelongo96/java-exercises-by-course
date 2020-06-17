package anno16_17.poo.progettoEsame;

//import java.util.LinkedList;

public class ProvaMyLL {

	public static void main(String[] args) {
		LinkedList<Integer> a = new LinkedList<>();
		a.add(41);
		a.add(12656);
		a.add(5656);
		a.add(665);
		a.addFirst(784);
		System.out.println(a);
		a.reverseIt();
		System.out.println(a);

//		ListIterator<Integer> it = a.listIterator(3);
//		System.out.println(a);
//		it.add(892424);
//		it.next();
//		it.next();
//		it.next();
//		it.next();
//		it.next();
//		it.add(44);
//		it.previous();
//		it.next();
//		it.remove();
//		it.add(18);
//		System.out.println(a);
//		it.previous();
//		it.next();
//		it.set(999999);
//		it.previous();
//		it.remove();
//		System.out.println(a);

	}

}
