package anno16_17.poo.file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OrdinamentoDaEsterno {
	
	private static int trovaMinimo(File file) throws IOException{
		if (file.length() == 0)
			throw new EOFException();
		DataInputStream lettore = new DataInputStream(new FileInputStream(file));
		int minimo = lettore.readInt();
		for(;;){
			try{
				int n = lettore.readInt();
				if (n<minimo)
					minimo = n;
			} catch (EOFException e){break;}
			
		}
		lettore.close();
		return minimo;
	}
	
	private static File togliMininimo(File file, int min) throws IOException{
		File nuovo = new File ("nuovo.txt");
		DataOutputStream scrittore = new DataOutputStream(new FileOutputStream(nuovo));
		DataInputStream lettore = new DataInputStream(new FileInputStream(file));
		
		for(;;){
			try{
				int n = lettore.readInt();
				if (n != min)
					scrittore.writeInt(n);
			} catch (EOFException e){break;}
		}
		scrittore.close(); lettore.close();
		file.delete();
		nuovo.renameTo(file);
		return nuovo;
	}
	
	private static File ordina(File file) throws IOException{
		File ris = new File ("C:\\Users\\peppe\\Desktop\\ris.txt");
		DataOutputStream scrittore = new DataOutputStream(new FileOutputStream(ris));
		for(;;){
			try{
				int min = trovaMinimo(file);
				scrittore.writeInt(min);
				togliMininimo(file,min);
			}catch (EOFException e){break;}
		}
		scrittore.close();
		file.delete(); ris.renameTo(file);
		return ris;
	}

	public static void main(String[] args) throws IOException {
		ordina(new File("C:\\Users\\peppe\\Desktop\\prova.txt"));
	}

}
