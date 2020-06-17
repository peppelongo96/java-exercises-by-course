package anno16_17.poo.regex;

import java.util.regex.Pattern;

public class EspressioneRegex {
	
	public static boolean espressioneValida(String s){
		String schemaConSegno = "([1-9]|[\\-\\+][1-9])\\d*([\\-\\+\\*/]([1-9]|\\(\\-[1-9]\\))\\d*)*";
		String schemaSenzaSegno = "[1-9]\\d*([\\-\\+\\*/][1-9]\\d*)*";
		return Pattern.matches(schemaConSegno, s);
	}
	
	
	public static void main(String[] args) {
		System.out.println(espressioneValida("+5-2"));
	}

}
