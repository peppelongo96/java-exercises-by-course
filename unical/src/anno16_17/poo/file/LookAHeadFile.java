package anno16_17.poo.file;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LookAHeadFile{
	public enum Modo{ READ, WRITE, UNKNOWN };
	public enum Tipo{ INT, DOUBLE };
	private Modo modo=Modo.UNKNOWN;
	private Tipo tipo=null;
	private long pos;
	private RandomAccessFile raf;
	private String nomeFile=null;
	public LookAHeadFile( String nomeFile, Tipo tipo ){
		this.nomeFile=nomeFile; this.tipo=tipo;	
	}
	public void open( Modo modo ) throws IOException{
		this.modo=modo;
		if( modo==Modo.READ ) raf=new RandomAccessFile( nomeFile, "r" );
		else{ raf=new RandomAccessFile( nomeFile, "rw" ); raf.setLength(0L); }
		pos=0;
	}//open
	
	public void close() throws IOException{
		modo=Modo.UNKNOWN;
		raf.close();
	}//close
	
	public boolean eof() throws IOException{
		if( modo==Modo.UNKNOWN ) throw new IOException();
		return pos==raf.length();
	}//eof
	
	public Number peek() throws IOException{
		if( modo!=Modo.READ || eof() ) throw new IOException();
		Number x=0;
		if( tipo==Tipo.INT ) x=raf.readInt();
		else x=raf.readDouble();
		raf.seek(pos);
		return x;
	}//peek
	
	public void get() throws IOException{
		if( modo!=Modo.READ || eof() ) throw new IOException();	
		pos=(tipo==Tipo.INT) ? pos+4 : pos+8;
		raf.seek( pos );
	}//get
	
	public void put( Number x ) throws IOException{
		if( modo!=Modo.WRITE || !eof() ) throw new IOException();
		if( !( x instanceof Integer || x instanceof Double ) ) throw new IOException();
		if( tipo==Tipo.INT ) raf.writeInt( x.intValue() );
		else raf.writeDouble( x.doubleValue() );
		pos=raf.length();
	}//put

	public String toString(){
		StringBuilder sb=new StringBuilder(100);
		sb.append('[');
		RandomAccessFile raf=null;
		Number n=null;
		try{
			raf=new RandomAccessFile( nomeFile, "r" );
			for(;;){
				try{
					switch( tipo ){
						case INT: n=raf.readInt(); break;
						default: n=raf.readDouble();
					}
					sb.append(n+", ");
				}catch( EOFException e ){ raf.close(); break; }
			}
		}catch( Exception e ){ e.printStackTrace(); System.exit(-1); }
		if( sb.length()>1 ) sb.setLength(sb.length()-2);
		sb.append(']');
		return sb.toString();
	}//toString
	
	public static void main( String[] args ) throws IOException{
		LookAHeadFile f1=new LookAHeadFile( "c:\\poo-file\\F1.laf", LookAHeadFile.Tipo.INT );
		System.out.println("contenuto di f1.laf");		
		System.out.println(f1);
		LookAHeadFile f2=new LookAHeadFile( "c:\\poo-file\\F2.laf", LookAHeadFile.Tipo.INT );
		System.out.println("contenuto di f2.laf");
		System.out.println(f2);	
		
		LookAHeadFile f3=new LookAHeadFile( "c:\\poo-file\\f3.laf", LookAHeadFile.Tipo.INT );
		f3.open( LookAHeadFile.Modo.WRITE );
		f1.open( LookAHeadFile.Modo.READ );
		f2.open( LookAHeadFile.Modo.READ );
		while( !f1.eof() && !f2.eof() ){
			if( f1.peek().intValue()<f2.peek().intValue() ){
				f3.put( f1.peek() ); f1.get();
			}
			else{
				f3.put( f2.peek() ); f2.get();
			}
		}
		//residuo
		while( !f1.eof() ){
			f3.put( f1.peek() ); f1.get();
		}
		while( !f2.eof() ){
			f3.put( f2.peek() ); f2.get();
		}
		f1.close(); f2.close(); f3.close();
		System.out.println("Risultato della fusione");
		System.out.println(f3);
		
		LookAHeadFile f4=new LookAHeadFile( "c:\\poo-file\\f4.laf", LookAHeadFile.Tipo.DOUBLE );
		f4.open( LookAHeadFile.Modo.WRITE );
		f4.put(2.5); f4.put(3.6); f4.put(7.8);
		f4.close();
		f4.open( LookAHeadFile.Modo.READ );
		System.out.println(f4);
		f4.close();
		f4.open( LookAHeadFile.Modo.READ );
		while( !f4.eof() ){
			System.out.println( f4.peek() );
			f4.get();
		}
		f4.close();
		
	}//main
}//LookAHeadFile
