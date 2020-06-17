package anno17_18.ingdelsoftware.progetto.model.memento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import anno17_18.ingdelsoftware.progetto.controller.GiocaGrattacieli5;

public class GrigliaGrattacieliCTSer implements CareTaker{
	
	private static final String mementoFolderPath = System.getProperty("user.dir")+"\\SOLUZIONI_GRATTACIELI";
	
	private static GrigliaGrattacieliCTSer instance; // SINGLETON
		
	private File mementoFolder;
	private String IDmementoFolder;
	private String mementoSoluzioniPath;
	private ObjectOutputStream scrittore;
	private ObjectInputStream lettore;
		
	private GrigliaGrattacieliCTSer() {
		IDmementoFolder = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		mementoFolder = new File (mementoFolderPath+"_"+IDmementoFolder);
		if ( !mementoFolder.exists() )
			mementoFolder.mkdir();
		mementoSoluzioniPath = mementoFolder.getPath()+"\\soluzione_";
	}// COSTRUTTORE
	
	public static GrigliaGrattacieliCTSer crea() {
		if ( instance == null ) 
			instance = new GrigliaGrattacieliCTSer();
		return instance;
	}// crea

	@Override
	public Memento getMem( int nrSol ) throws IOException, ClassNotFoundException {
		FileInputStream fisM = new FileInputStream(mementoSoluzioniPath+Integer.toString(nrSol));
		lettore = new ObjectInputStream(fisM);
		Memento m = (Memento)lettore.readObject();
		lettore.close();
		return m;
	}// getMem

	@Override
	public void saveMem( Memento m, int nrSol ) throws IOException {
		FileOutputStream fosM = new FileOutputStream(mementoSoluzioniPath+Integer.toString(nrSol));
		scrittore = new ObjectOutputStream(fosM);
		scrittore.writeObject(m);
		scrittore.close();
	}// saveMem

	@Override
	public void graficaMemento( int nrSol ) {
		try {
			GrigliaGrattacieliMem ggm = (GrigliaGrattacieliMem)getMem(nrSol);
			int dim = GiocaGrattacieli5.gq.getDim();
			for (int i = 0; i < dim; i++) 
				for (int j = 0; j < dim; j++)
					GiocaGrattacieli5.gui.setCellaGUI(i+1, j+1, ggm.getAltezza(i, j));
			GiocaGrattacieli5.gui.aggiornaGrigliaGUI();
		} catch ( Exception e) {e.printStackTrace();}
	}// graficaMemento

}// GrigliaGrattacieliCT
