package anno16_17.poo.utils;

public class FattorialeRicorsivo {
    
    public static int calcola(int numero){
        if ( numero<=0 ) throw new IllegalArgumentException();
        int ris = esegui(numero);
        return ris;
    }
    
    private static int esegui(int n){
        if ( n==1 ) return 1;
        return n * esegui(n-1);
    }
    
    public static void main ( String[] args ){
        System.out.println(calcola(4));
    } 
}