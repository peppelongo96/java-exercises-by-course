package anno16_17.poo.file;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FusioneOrdinata {
	
	private static File fusione(File file1, File file2) throws IOException{
		File ris = new File ("C:\\Users\\peppe\\Desktop\\ris.txt");
		RandomAccessFile lettore1 = new RandomAccessFile(file1, "r");
		RandomAccessFile lettore2 = new RandomAccessFile(file2, "r");
		RandomAccessFile scrittore = new RandomAccessFile(ris,"rw");
		
		int n1 = lettore1.readInt();
		int n2 = lettore2.readInt();
		boolean flag;
		
		for(;;){
			if (n1 < n2){
				scrittore.writeInt(n1);
				try{
				n1 = lettore1.readInt();
				} catch (EOFException e){flag = true; break;}
			}
			else if (n1 > n2){
				scrittore.writeInt(n2);
				try{
					n2 = lettore2.readInt();
				} catch (EOFException e){flag = false; break;}
			}
		}
		 
		if (flag){
			lettore2.seek(lettore2.getFilePointer()-4);
			while (lettore2.getFilePointer() < lettore2.length()){
				scrittore.writeInt(lettore2.readInt());
			}
			
				
		}
		else{
			lettore1.seek(lettore1.getFilePointer()-4); 
			while (lettore1.getFilePointer() < lettore1.length()){
				scrittore.writeInt(lettore1.readInt());
			}
		}
		
		scrittore.close();lettore1.close();lettore2.close();
		return ris;
	}
	
	public static void main(String[] args) throws IOException {
		File f1 = new File ("C:\\Users\\peppe\\Desktop\\prova1.txt");
		File f2 = new File ("C:\\Users\\peppe\\Desktop\\prova2.txt");
		fusione (f1,f2);
	}

}
