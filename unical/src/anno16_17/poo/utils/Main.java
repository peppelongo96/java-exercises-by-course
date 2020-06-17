package anno16_17.poo.utils;

public class Main {

	public static void main(String[] args) {
//		CollezioneOrdinata<Integer> ls = new ListaConcatenataOrdinata<>();
//		ls.add(1);
//		ls.add(3);
//		ls.add(5);
//		ls.add(0);
//		ls.add(8);
//		ls.add(-4);
//		ls.add(-2);
//		ls.add(-2);
//		ls.add(15);
//		ls.remove(89);
//		ls.remove(-4);
//		
//		System.out.println(ls);
//		System.out.println(ls.size());

		Coda<Integer> si = new CodaConcatenata<>();
		si.put(4);
		si.put(56);
		si.put(1);
		si.put(45);
		System.out.println(si);
		si.get();
		si.get();
		si.get();
		si.get();
//		si.pop();
//		si.pop();
//		si.pop();
		System.out.println(si);
	}

}
