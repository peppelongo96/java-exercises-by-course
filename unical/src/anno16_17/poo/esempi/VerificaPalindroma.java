package anno16_17.poo.esempi;

import java.util.Scanner;

import anno16_17.poo.utils.Stack;
import anno16_17.poo.utils.StackConcatenato;

public class VerificaPalindroma{
	public static void main( String[] args ){
		String FORMATO_INPUT="\\w+\\$\\w+";
		Stack<Character> pila=new StackConcatenato<>();
		Scanner sc=new Scanner( System.in );
		System.out.println("Fornisci una linea del tipo: stringa1$stringa2");
		String input=sc.nextLine();

		
		if( !input.matches(FORMATO_INPUT) ){
			System.out.println("Stringa malformata");
			System.exit(-1);			
		}
		
		int pos=0;
		for(;;){
			char c=input.charAt(pos); pos++;
			if( c!='$' ) pila.push( c );
			else break;
		}//for

		boolean flag=true;
		for(;;){
			if( pos==input.length() || pila.isEmpty() ) break;
			char c=input.charAt(pos);
			char x=pila.pop();
			if( c!=x ){ flag=false; break; }
			pos++;
		}//for
		
		if( flag && pila.isEmpty() && pos==input.length() )
			System.out.println(input+" e' palindroma");
		else
			System.out.println(input+" non e' palindroma");
		
		sc.close();
	}//main
}//VerificaPalindroma