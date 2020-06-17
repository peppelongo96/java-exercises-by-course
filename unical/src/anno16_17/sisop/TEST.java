package anno16_17.sisop;

import java.util.Arrays;

public class TEST {

	public static void main(String[] args) {
		int[] s = {1,2,3,4,5,6,7,8};
		s = Arrays.copyOfRange(s, 2, 5);
		for (int i = 0; i < s.length; i++) {
			System.out.print(" "+s[i]);
		}
	}
}
