package anno16_17.poo.utils;

public class NumeroAlContrario {
    
    public static void calcola(int n){
    	System.out.print(n%10);
    	if ( n/10==0 ) return;
    	calcola(n/10);
    }
    
    public static void main(String[] args) {
		calcola(456);
	}
}