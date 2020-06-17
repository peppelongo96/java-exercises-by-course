package anno17_18.ingdelsoftware.progetto.view.builder;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import anno17_18.ingdelsoftware.progetto.model.memento.GrigliaGrattacieliCTSer;

@SuppressWarnings("unused")
class Finestra extends JFrame {
	
	private static final long serialVersionUID = 1L;

	Finestra( String titolo, int xSize, int ySize ) {
		super(titolo);
		this.setResizable(false);
		this.setSize(xSize,ySize);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centraFinestra();
	}// COSTRUTTORE
	
	private void centraFinestra() {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}// centraFinestra

	public void aggiornaFinestra() {
		SwingUtilities.updateComponentTreeUI(this);
	}// aggiornaFinestra

}// Finestra
