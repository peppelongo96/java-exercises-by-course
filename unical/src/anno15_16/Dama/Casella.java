package anno15_16.Dama;


import java.awt.*;
import javax.swing.*;

class Casella extends JButton{
	private static final long serialVersionUID = 100368064207917256L;
	private String icona;
	private int Dama;
	private int posi;
	private int posj;
	
	public Casella(int i,int j){
		this.Dama = 0; // 0 = non  dama; 1 = dama nera; -1 = dama bianca
		this.posi = i;
		this.posj = j;
		this.icona = "";
		setSize(100, 100);
		setVisible(true);
		setBorder(BorderFactory.createLineBorder(Color.black,1));
		setEnabled(false);
	}
	
	public void setIcona(String icona){
		this.icona = icona;
		setIcon(new ImageIcon(getClass().getResource(icona)));
	}
	
	public String getIcona(){
		return this.icona;
	}
	
	public int getPosI(){
		return this.posi;
	}

	public int getPosJ(){
		return this.posj;
	}
	
	public int getDama(){
		return this.Dama;
	}
	
	public void setDama(int i){
		this.Dama = i;
	}
	
	public String toString(){
		return "|"+posi+","+posj+"|";
	}
	

}
