package anno16_17.poo.file;

import java.io.IOException;

public class FusioneOrdinataConAnticipazione {

	public static void main(String[] args) throws IOException {
		LookAHeadFile l1 = new LookAHeadFile("C:\\Users\\peppe\\Desktop\\f1.txt",LookAHeadFile.Tipo.INT);
		LookAHeadFile l2 = new LookAHeadFile("C:\\Users\\peppe\\Desktop\\f2.tx",LookAHeadFile.Tipo.INT);
		LookAHeadFile l3 = new LookAHeadFile("C:\\Users\\peppe\\Desktop\\ris.txt",LookAHeadFile.Tipo.INT);
		
		l1.open(LookAHeadFile.Modo.READ);
		l2.open(LookAHeadFile.Modo.READ);
		l3.open(LookAHeadFile.Modo.WRITE);
		
		while (!l1.eof() && !l2.eof()){
			if (l1.peek().intValue() < l2.peek().intValue()){
				l3.put(l1.peek()); l1.get();
			}
			else{
				l3.put(l2.peek()); l2.get();
			}
		}
		
		while (!l1.eof()){
			l3.put(l1.peek()); l1.get();
		}
		while (!l2.eof()){
			l3.put(l2.peek()); l2.get();
		}
		
		l3.close(); l1.close(); l2.close();
		System.out.println(l3);
		}
	}
