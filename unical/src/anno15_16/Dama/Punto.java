package anno15_16.Dama;


import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.*;

class Punto extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public Punto(String nb){
		int larghezza = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int altezza = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		if (larghezza != 1920 && altezza != 1080){
			int val1 = (int)(40*larghezza)/1920;
			int val2 = (int)(40*larghezza)/1920;
			setSize(val1,val2);
		}
		else
			setSize(40,40);
		setIcon(new ImageIcon(getClass().getResource(nb)));
		setBackground(Color.LIGHT_GRAY);
	}

}
