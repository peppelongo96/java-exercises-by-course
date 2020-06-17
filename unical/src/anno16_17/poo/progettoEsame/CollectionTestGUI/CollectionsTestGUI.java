package anno16_17.poo.progettoEsame.CollectionTestGUI;

import java.awt.EventQueue;

public class CollectionsTestGUI {
	
	public CollectionsTestGUI() {
		EventQueue.invokeLater( new Runnable() {
			public void run() {
				FrameToChoose ffm = new FrameToChoose();
				ffm.setVisible(true);
				//ffm alla sua chiusura, determinerà l'apertura di FrameOfAllMethods
			}
		} );
	}//COSTRUTTORE
	
	//---------------------------------------------------------------------------//
	public static void main(String[] args) {
		new CollectionsTestGUI();
	}//main
	
}//GUI
