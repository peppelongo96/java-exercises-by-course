package anno15_16.Dama;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class GUI {

	private JPanel tavolo;
	private JPanel start;
	private JTextField textField1;
	private JTextField textField2;
	private JLabel info;
	private JFrame frame;
	private Tavolo matrice;
	private JPanel panelGioc1;
	private JPanel panelGioc2;
	private JLabel lblGioc1;
	private JLabel lblGioc2;
	private JButton passaG1;
	private JButton passaG2;
	private JLabel freccia1,freccia2;
	
	//variabili temporanee listener
	private Casella temp1 = new Casella(-1,-1);
	private Casella bottonePremuto = new Casella(-1,-1);
	
	private Casella temp2 = new Casella(-1,-1);
	private Casella temp3 = new Casella(-1,-1);
	//CASO DAMA
	private Casella temp4 = new Casella(-1,-1);
	private Casella temp5 = new Casella(-1,-1);
	

	private int pedineGioc1,pedineGioc2,Patta;
	private boolean spostamento = true;
	
	private ArrayList<Casella> sequenza1 = new ArrayList<>();
	private ArrayList<Casella> sequenza2 = new ArrayList<>();
	
	////////CASO DAMA//////
	private ArrayList<Casella> sequenza3 = new ArrayList<>();
	private ArrayList<Casella> sequenza4 = new ArrayList<>();
	/////////////////////////////////////////////////////////
	

	private ArrayList<Casella> seq11 = new ArrayList<>();
	private ArrayList<Casella> seq12 = new ArrayList<>();
	private ArrayList<Casella> seq21 = new ArrayList<>();
	private ArrayList<Casella> seq22 = new ArrayList<>();
	
	/////CASO DAMA/////
	private ArrayList<Casella> seq31 = new ArrayList<>();
	private ArrayList<Casella> seq32 = new ArrayList<>();
	private ArrayList<Casella> seq41 = new ArrayList<>();
	private ArrayList<Casella> seq42 = new ArrayList<>();
	///////////////////////////////////////////////////////
	
	ArrayList<Casella> s1 = new ArrayList<>();
	ArrayList<Casella> s2 = new ArrayList<>();
	ArrayList<Casella> s11 = new ArrayList<>();
	ArrayList<Casella> s12 = new ArrayList<>();
	ArrayList<Casella> s21 = new ArrayList<>();
	ArrayList<Casella> s22 = new ArrayList<>();
	
	
	private ArrayList<Casella> verifica = new ArrayList<>();
	
	
	public GUI(){
		
		//frame
		frame = new JFrame();
		frame.setTitle("Dama");
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBounds(270,0,1372,1000);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(tavolo = new JPanel());
	
		
		info = new JLabel();
		info.setBounds(277, 891, 800, 30);
		info.setHorizontalAlignment(JLabel.CENTER);;
		info.setVisible(false);
		frame.getContentPane().add(info);
		
		//panelstart
		start = new JPanel();
		start.setBounds(12, 845, 1342, 105);
		frame.getContentPane().add(start);
		start.setLayout(null);
		
		JLabel lblInserisciNome1 = new JLabel("NOME GIOCATORE 1");
		lblInserisciNome1.setHorizontalAlignment(JLabel.CENTER);;
		lblInserisciNome1.setBounds(0, 0, 268, 26);
		start.add(lblInserisciNome1);
		
		JLabel lblInserisciNome2 = new JLabel("NOME GIOCATORE 2");
		lblInserisciNome2.setHorizontalAlignment(JLabel.CENTER);
		lblInserisciNome2.setBounds(1074, 5, 268, 26);
		start.add(lblInserisciNome2);
		
		textField1 = new JTextField();
		textField1.setBounds(10, 41, 258, 22);
		start.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setColumns(10);
		textField2.setBounds(1074, 41, 258, 22);
		start.add(textField2);
		
		/////////////////////////////////////////
		
		panelGioc1 = new JPanel();
		panelGioc1.setEnabled(false);
		panelGioc1.setBorder(BorderFactory.createLineBorder(Color.RED,3));
		panelGioc1.setBackground(Color.LIGHT_GRAY);
		panelGioc1.setBounds(10, 140, 253, 320);
		frame.getContentPane().add(panelGioc1);
		
		panelGioc2 = new JPanel();
		panelGioc2.setEnabled(false);
		panelGioc2.setBorder(BorderFactory.createLineBorder(Color.RED,3));
		panelGioc2.setBackground(Color.LIGHT_GRAY);
		panelGioc2.setBounds(1091, 485, 253, 320);
		frame.getContentPane().add(panelGioc2);
		
		lblGioc1 = new JLabel("Giocatore 1");
		lblGioc1.setEnabled(false);
		lblGioc1.setHorizontalAlignment(JLabel.CENTER);;
		lblGioc1.setBounds(10, 61, 253, 40);
		lblGioc1.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
		frame.getContentPane().add(lblGioc1);
		
		lblGioc2 = new JLabel("Giocatore 2");
		lblGioc2.setEnabled(false);
		lblGioc2.setHorizontalAlignment(JLabel.CENTER);;
		lblGioc2.setBounds(1089, 402, 253, 40);
		frame.getContentPane().add(lblGioc2);
		
		passaG1 = new JButton("PASSA TURNO");
		passaG1.setEnabled(false);
		passaG1.setBounds(12, 521, 251, 107);
		passaG1.addActionListener(new listenerPassaTurno());
		frame.getContentPane().add(passaG1);
		
		passaG2 = new JButton("PASSA TURNO");
		passaG2.setEnabled(false);
		passaG2.addActionListener(new listenerPassaTurno());
		passaG2.setBounds(1089, 235, 251, 107);
		frame.getContentPane().add(passaG2);
		
		freccia1 = new JLabel("New label");
		freccia1.setBounds(39, 660, 200, 171);
		frame.getContentPane().add(freccia1);
		freccia1.setIcon(new ImageIcon(getClass().getResource("frecciaBianco.png")));
		freccia1.setEnabled(false);;
		
		freccia2 = new JLabel("New label");
		freccia2.setBounds(1109, 31, 200, 171);
		frame.getContentPane().add(freccia2);
		freccia2.setIcon(new ImageIcon(getClass().getResource("frecciaNero.png")));
		freccia2.setEnabled(false);


		
		//pannello tavolo
		matrice = new Tavolo();
		tavolo.setBounds(277,31, 800, 800);
		tavolo.setLayout(new GridLayout(8,8));
		tavolo.setBorder(BorderFactory.createLineBorder(Color.black,5));
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				matrice.getMatrice()[i][j] = new Casella(i,j);
				if (i % 2 == j % 2){
					if (i<3){
						matrice.getMatrice()[i][j].setIcona("neroChiaro.png");
						matrice.getMatrice()[i][j].addActionListener(new listenerNero());
					}
					else if(i > 4){
						matrice.getMatrice()[i][j].setIcona("biancoChiaro.png");
						matrice.getMatrice()[i][j].addActionListener(new listenerBianco());
					}
					else{
						matrice.getMatrice()[i][j].setIcona("chiaro.png");
					}
					
				}
				else{
					matrice.getMatrice()[i][j].setIcona("scuro.png");
				}
				tavolo.add(matrice.getMatrice()[i][j]);
				
			}
		}
		
		
		JButton btnstart = new JButton("START\r\n");
		btnstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 8; i++) 
					for (int j = 0; j < 8; j++) 
						matrice.getMatrice()[i][j].setEnabled(true);

				if (!(textField1.getText().isEmpty()))
					lblGioc1.setText(textField1.getText());

				if (!(textField2.getText().isEmpty()))
					lblGioc2.setText(textField2.getText());

				panelGioc1.setEnabled(true);
				panelGioc2.setEnabled(true);
				Font font2 = new Font("Comic Sans", Font.BOLD, 15);
				lblGioc1.setEnabled(true);
				lblGioc1.setFont(font2);
				lblGioc2.setFont(font2);
				lblGioc2.setEnabled(true);
				passaG1.setEnabled(true);
				passaG2.setEnabled(true);
				freccia1.setEnabled(true);
				freccia2.setEnabled(true);
				
				start.setVisible(false);
				passaG2.setVisible(false);
				freccia2.setVisible(false);
				
				info.setVisible(true);
				info.setForeground(Color.RED);
				Font font = new Font("Comic Sans", Font.BOLD, 18);
				info.setFont(font);
			}
		});
		btnstart.setBounds(478, 23, 376, 58);
		start.add(btnstart);
		
		adattaRis();
		
		frame.revalidate();
		frame.repaint();
		pedineGioc1 = 12;
		pedineGioc2 = 12;
		Patta = 0;
		
	}
	
	private void adattaRis(){
		int larghezza = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int altezza = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		if (larghezza != 1920 && altezza != 1080){
			frame.setBounds((int)(270*larghezza)/1920,0,(int)(1372*larghezza)/1920,(int)(1000*altezza)/1080);
			
			for (int i = 0; i < frame.getContentPane().getComponentCount(); i++) {
				int posxdef = frame.getContentPane().getComponent(i).getX();
				int posydef = frame.getContentPane().getComponent(i).getY();
				int largdef = frame.getContentPane().getComponent(i).getWidth();
				int altedef = frame.getContentPane().getComponent(i).getHeight();
				
				int posx,posy,larg,alt;
				posx = (posxdef * larghezza)/1920;
				posy = (posydef * altezza)/1080;
				larg = (largdef * larghezza)/1920;
				alt = (altedef * altezza)/1080;

				frame.getContentPane().getComponent(i).setBounds(posx, posy, larg, alt);
			}
			
			for (int i = 0; i < start.getComponentCount(); i++) {
				int posxdef = start.getComponent(i).getX();
				int posydef = start.getComponent(i).getY();
				int largdef = start.getComponent(i).getWidth();
				int altedef = start.getComponent(i).getHeight();
				
				int posx,posy,larg,alt;
				posx = (posxdef * larghezza)/1920;
				posy = (posydef * altezza)/1080;
				larg = (largdef * larghezza)/1920;
				alt = (altedef * altezza)/1080;

				start.getComponent(i).setBounds(posx, posy, larg, alt);
			}
		}
	}
	
	
	// LISTENER BIANCO
	private class listenerBianco implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			Casella botTemp = (Casella)e.getSource();
			info.setText(null);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (matrice.getMatrice()[i][j].getIcona().equals("soffio.png"))
						matrice.getMatrice()[i][j].setIcona("chiaro.png");
				}
			}
			
			if ((!(botTemp.getIcona().equals("chiaro.png")) && spostamento == true)){
				bottonePremuto = botTemp;

				sequenza1.clear();
				sequenza2.clear();
				
				seq11.clear();
				seq12.clear();
				seq21.clear();
				seq22.clear();
				
				verifica.clear();
		
				temp1.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp2.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp3.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp4.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp5.setBorder(BorderFactory.createLineBorder(Color.black,1));
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (bottonePremuto == matrice.getMatrice()[i][j]){
							bottonePremuto.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
							
							Casella suc1 = new Casella(-1,-1);
							Casella suc2 = new Casella(-1,-1);
							Casella suc3 = new Casella(-1,-1);
							Casella suc4 = new Casella(-1,-1);
							
							try{
								suc1 = matrice.getMatrice()[i-1][j+1];
							}
							catch(Exception e1){
							}
							try{
								suc2 = matrice.getMatrice()[i-1][j-1];
							}
							catch(Exception e1){
							}
							try{
								suc3 = matrice.getMatrice()[i-2][j+2];
							}
							catch(Exception e1){
							}
							try{
								suc4 = matrice.getMatrice()[i-2][j-2];
							}
							catch(Exception e1){
							}
							
							if (!(suc1.getPosI() == -1 && suc1.getIcona().equals("biancoChiaro.png") && suc1.getIcona().equals("biancoDama.png"))){
								if (suc1.getIcona().equals("chiaro.png")){
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza1.add(suc1);
								}
								else if (suc3.getPosI() != -1 && suc1.getIcona().equals("neroChiaro.png") && suc3.getIcona().equals("chiaro.png")){
									suc1 = suc3;
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeBianco(suc1);
								}
							}
							
							if (!(suc2.getPosI() == -1 && suc2.getIcona().equals("biancoChiaro.png") && suc1.getIcona().equals("biancoDama.png"))){
								if (suc2.getIcona().equals("chiaro.png")){
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza2.add(suc2);

								}
								else if (suc4.getPosI() != -1 && suc2.getIcona().equals("neroChiaro.png") && suc4.getIcona().equals("chiaro.png")){
									suc2 = suc4;
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeBianco(suc2);
								}

							}
							temp1 = bottonePremuto;
							temp2 = suc1;
							temp3 = suc2;
							rendiSelezionabile();
							break;
						}
						
					}
				}
			}
		}
	}
	
	
	
	private class listenerNero implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			Casella botTemp = (Casella)e.getSource();
			info.setText(null);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (matrice.getMatrice()[i][j].getIcona().equals("soffio.png"))
						matrice.getMatrice()[i][j].setIcona("chiaro.png");
				}
			}
			if ((!(botTemp.getIcona().equals("chiaro.png")) && spostamento == false)){
				
				bottonePremuto = botTemp;
				
				temp1.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp2.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp3.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp4.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp5.setBorder(BorderFactory.createLineBorder(Color.black,1));
				
				sequenza1.clear();
				sequenza2.clear();
				
				seq11.clear();
				seq12.clear();
				seq21.clear();
				seq22.clear();
				
				verifica.clear();
		
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (bottonePremuto == matrice.getMatrice()[i][j]){
							bottonePremuto.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
							
							Casella suc1 = new Casella(-1,-1);
							Casella suc2 = new Casella(-1,-1);
							Casella suc3 = new Casella(-1,-1);
							Casella suc4 = new Casella(-1,-1);
							
							try{
								suc1 = matrice.getMatrice()[i+1][j+1];
							}
							catch(Exception e1){
							}
							try{
								suc2 = matrice.getMatrice()[i+1][j-1];
							}
							catch(Exception e1){
							}
							try{
								suc3 = matrice.getMatrice()[i+2][j+2];
							}
							catch(Exception e1){
							}
							try{
								suc4 = matrice.getMatrice()[i+2][j-2];
							}
							catch(Exception e1){
							}
							
							
							if (!(suc1.getPosI() == -1 && suc1.getIcona().equals("neroChiaro.png")&& suc1.getIcona().equals("neroDama.png"))){
								if (suc1.getIcona().equals("chiaro.png")){
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza1.add(suc1);

								}
								
								else if (suc3.getPosJ() != -1 && suc1.getIcona().equals("biancoChiaro.png") && suc3.getIcona().equals("chiaro.png")){
									suc1 = suc3;
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeNero(suc1,false);

								}
							}

							if (!(suc2.getPosI() == -1  && suc2.getIcona().equals("neroChiaro.png")&& suc1.getIcona().equals("neroDama.png"))){
								if (suc2.getIcona().equals("chiaro.png")){
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza2.add(suc2);
								}
								else if (suc4.getPosI() != -1 && suc2.getIcona().equals("biancoChiaro.png") && suc4.getIcona().equals("chiaro.png")){
									suc2 = suc4;
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeNero(suc2,false);
								}
							}
							temp1 = bottonePremuto;
							temp2 = suc1;
							temp3 = suc2;
							rendiSelezionabile();
							break;
						}
						
					}
				}
			}
		}
	}
	
	
	private class listenerDamaBianca implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			Casella botTemp = (Casella)e.getSource();

			info.setText(null);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (matrice.getMatrice()[i][j].getIcona().equals("soffio.png"))
						matrice.getMatrice()[i][j].setIcona("chiaro.png");
				}
			}
			
			if ((!(botTemp.getIcona().equals("chiaro.png")) && spostamento == true)){
				bottonePremuto = botTemp;
				
				sequenza1.clear();
				sequenza2.clear();
				sequenza3.clear();
				sequenza4.clear();
				
				seq11.clear();
				seq12.clear();
				seq21.clear();
				seq22.clear();
				
				seq31.clear();
				seq32.clear();
				seq41.clear();
				seq42.clear();
				
				verifica.clear();
		
				temp1.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp2.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp3.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp4.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp5.setBorder(BorderFactory.createLineBorder(Color.black,1));
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (bottonePremuto == matrice.getMatrice()[i][j]){
							bottonePremuto.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
							
							Casella suc1 = new Casella(-1,-1);
							Casella suc2 = new Casella(-1,-1);
							Casella suc3 = new Casella(-1,-1);
							Casella suc4 = new Casella(-1,-1);
							
							try{
								suc1 = matrice.getMatrice()[i-1][j+1];
							}
							catch(Exception e1){
							}
							try{
								suc2 = matrice.getMatrice()[i-1][j-1];
							}
							catch(Exception e1){
							}
							try{
								suc3 = matrice.getMatrice()[i-2][j+2];
							}
							catch(Exception e1){
							}
							try{
								suc4 = matrice.getMatrice()[i-2][j-2];
							}
							catch(Exception e1){
							}
							
							if (!(suc1.getPosI() == -1 && (suc1.getIcona().equals("biancoChiaro.png") || suc1.getIcona().equals("biancoDama.png")))){
								if (suc1.getIcona().equals("chiaro.png")){
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza1.add(suc1);
								}
								else if (suc3.getPosI() != -1 && (suc1.getIcona().equals("neroChiaro.png") || suc1.getIcona().equals("neroDama.png"))
										&& suc3.getIcona().equals("chiaro.png")){
									suc1 = suc3;
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeBianco(suc1);
								}
							}
							
							if (!(suc2.getPosI() == -1 && (suc2.getIcona().equals("biancoChiaro.png") && suc2.getIcona().equals("biancoDama.png")))){
								if (suc2.getIcona().equals("chiaro.png")){
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza2.add(suc2);

								}
								else if (suc4.getPosI() != -1 && (suc2.getIcona().equals("neroChiaro.png") || suc1.getIcona().equals("neroDama.png"))
										&& suc4.getIcona().equals("chiaro.png")){
									suc2 = suc4;
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeBianco(suc2);
								}

							}
							
							temp2 = suc1;
							temp3 = suc2;
							
							suc1 = new Casella(-1,-1);
							suc2 = new Casella(-1,-1);
							suc3 = new Casella(-1,-1);
							suc4 = new Casella(-1,-1);
							
							try{
								suc1 = matrice.getMatrice()[i+1][j+1];
							}
							catch(Exception e1){
							}
							try{
								suc2 = matrice.getMatrice()[i+1][j-1];
							}
							catch(Exception e1){
							}
							try{
								suc3 = matrice.getMatrice()[i+2][j+2];
							}
							catch(Exception e1){
							}
							try{
								suc4 = matrice.getMatrice()[i+2][j-2];
							}
							catch(Exception e1){
							}
							
							
							if (!(suc1.getPosI() == -1 && (suc1.getIcona().equals("biancoChiaro.png") || suc1.getIcona().equals("biancoDama.png")))){
								if (suc1.getIcona().equals("chiaro.png")){
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza4.add(suc1);

								}
								
								else if (suc3.getPosJ() != -1 && (suc1.getIcona().equals("neroChiaro.png") || suc1.getIcona().equals("neroDama.png"))
										&& suc3.getIcona().equals("chiaro.png")){
									suc1 = suc3;
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeNero(suc1,true);

								}
							}

							if (!(suc2.getPosI() == -1  && (suc2.getIcona().equals("biancoChiaro.png") || suc2.getIcona().equals("biancoDama.png")))){
								if (suc2.getIcona().equals("chiaro.png")){
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza3.add(suc2);
								}
								else if (suc4.getPosI() != -1 && (suc2.getIcona().equals("neroChiaro.png") || suc1.getIcona().equals("neroDama.png"))
										&& suc4.getIcona().equals("chiaro.png")){
									suc2 = suc4;
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeNero(suc2,true);
								}
							}
							temp1 = bottonePremuto;
							temp4 = suc1;
							temp5 = suc2;
							rendiSelezionabile();
							break;
						}
						
					}
				}
			}
		}
	}
	
	private class listenerDamaNera implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			Casella botTemp = (Casella)e.getSource();

			info.setText(null);
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (matrice.getMatrice()[i][j].getIcona().equals("soffio.png"))
						matrice.getMatrice()[i][j].setIcona("chiaro.png");
				}
			}
			
			if ((!(botTemp.getIcona().equals("chiaro.png")) && spostamento == false)){
				
				bottonePremuto = botTemp;
				
				sequenza1.clear();
				sequenza2.clear();
				sequenza3.clear();
				sequenza4.clear();
				
				seq11.clear();
				seq12.clear();
				seq21.clear();
				seq22.clear();
				
				seq31.clear();
				seq32.clear();
				seq41.clear();
				seq42.clear();
				
				verifica.clear();
		
				temp1.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp2.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp3.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp4.setBorder(BorderFactory.createLineBorder(Color.black,1));
				temp5.setBorder(BorderFactory.createLineBorder(Color.black,1));
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (bottonePremuto == matrice.getMatrice()[i][j]){
							bottonePremuto.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
							
							Casella suc1 = new Casella(-1,-1);
							Casella suc2 = new Casella(-1,-1);
							Casella suc3 = new Casella(-1,-1);
							Casella suc4 = new Casella(-1,-1);
							
							try{
								suc1 = matrice.getMatrice()[i-1][j+1];
							}
							catch(Exception e1){
							}
							try{
								suc2 = matrice.getMatrice()[i-1][j-1];
							}
							catch(Exception e1){
							}
							try{
								suc3 = matrice.getMatrice()[i-2][j+2];
							}
							catch(Exception e1){
							}
							try{
								suc4 = matrice.getMatrice()[i-2][j-2];
							}
							catch(Exception e1){
							}
							
							if (!(suc1.getPosI() == -1 && (suc1.getIcona().equals("neroChiaro.png") || suc1.getIcona().equals("neroDama.png")))){
								if (suc1.getIcona().equals("chiaro.png")){
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza1.add(suc1);
								}
								else if (suc3.getPosI() != -1 && (suc1.getIcona().equals("biancoChiaro.png") || suc1.getIcona().equals("biancoDama.png"))
										&& suc3.getIcona().equals("chiaro.png")){
									suc1 = suc3;
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeBianco(suc1);
								}
							}
							
							if (!(suc2.getPosI() == -1 && (suc2.getIcona().equals("neroChiaro.png") || suc2.getIcona().equals("neroDama.png")))){
								if (suc2.getIcona().equals("chiaro.png")){
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza2.add(suc2);

								}
								else if (suc4.getPosI() != -1 && (suc2.getIcona().equals("biancoChiaro.png") || suc2.getIcona().equals("biancoDama.png"))
										&& suc4.getIcona().equals("chiaro.png")){
									suc2 = suc4;
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeBianco(suc2);
								}

							}
							
							temp2 = suc1;
							temp3 = suc2;
							
							suc1 = new Casella(-1,-1);
							suc2 = new Casella(-1,-1);
							suc3 = new Casella(-1,-1);
							suc4 = new Casella(-1,-1);
							
							try{
								suc1 = matrice.getMatrice()[i+1][j+1];
							}
							catch(Exception e1){
							}
							try{
								suc2 = matrice.getMatrice()[i+1][j-1];
							}
							catch(Exception e1){
							}
							try{
								suc3 = matrice.getMatrice()[i+2][j+2];
							}
							catch(Exception e1){
							}
							try{
								suc4 = matrice.getMatrice()[i+2][j-2];
							}
							catch(Exception e1){
							}
							
							
							if (!(suc1.getPosI() == -1 && (suc1.getIcona().equals("neroChiaro.png") || suc1.getIcona().equals("neroDama.png")))){
								if (suc1.getIcona().equals("chiaro.png")){
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza4.add(suc1);

								}
								
								else if (suc3.getPosJ() != -1 && (suc1.getIcona().equals("biancoChiaro.png") || suc1.getIcona().equals("biancoDama.png"))
										&& suc3.getIcona().equals("chiaro.png")){
									suc1 = suc3;
									suc1.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeNero(suc1,true);

								}
							}

							if (!(suc2.getPosI() == -1  && (suc2.getIcona().equals("neroChiaro.png") || suc1.getIcona().equals("neroDama.png")))){
								if (suc2.getIcona().equals("chiaro.png")){
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenza3.add(suc2);
								}
								else if (suc4.getPosI() != -1 && (suc2.getIcona().equals("biancoChiaro.png") || suc2.getIcona().equals("biancoDama.png"))
										&& suc4.getIcona().equals("chiaro.png")){
									suc2 = suc4;
									suc2.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
									sequenzeNero(suc2,true);
								}
							}
							temp1 = bottonePremuto;
							temp4 = suc1;
							temp5 = suc2;
							rendiSelezionabile();
							break;
						}
						
					}
				}
			}
		}
	}
	

	
	
	private class listenerPassaTurno implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JButton bottonePremuto3=(JButton)e.getSource();
			if (bottonePremuto3 == passaG2){
				spostamento = true;
				lblGioc1.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
				lblGioc2.setBorder(null);
				passaG2.setVisible(false);
				passaG1.setVisible(true);
				freccia1.setVisible(true);
				freccia2.setVisible(false);
			}
			else{
				spostamento = false;
				lblGioc2.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
				lblGioc1.setBorder(null);
				passaG1.setVisible(false);
				passaG2.setVisible(true);
				freccia2.setVisible(true);
				freccia1.setVisible(false);
			}
			
			if  (bottonePremuto.getIcona().equals("biancoDama.png") || bottonePremuto.getIcona().equals("neroDama.png"))
				soffioValida(true);
			else
				soffioValida(false);

				
			
//			System.out.println("verifica"+verifica);
			rendiNonSelezionabile();
			verifica.clear();
			if (verificaVittoria()){
				for (int i = 0; i < 8; i++) 
					for (int j = 0; j < 8; j++) 
						matrice.getMatrice()[i][j].setEnabled(false);
				panelGioc1.setEnabled(false);
				panelGioc2.setEnabled(false);
				lblGioc1.setEnabled(false);
				lblGioc2.setEnabled(false);
				passaG1.setEnabled(false);
				passaG2.setEnabled(false);
				freccia1.setEnabled(false);
				freccia2.setEnabled(false);
			}
		}
	}
	
	
	private class listenerRosso implements ActionListener {
		public void actionPerformed(ActionEvent e){
			Casella bottonePremuto3=(Casella)e.getSource();
			bottonePremuto3.setBorder(BorderFactory.createLineBorder(Color.RED,2));
			verifica.add(bottonePremuto3);
			bottonePremuto3.removeActionListener(this);
		}
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	
	int cont = 0;
	
	private void soffioValida(boolean flag){
		
		if (cont == 0){
			s1 = sequenza1;
			s2 = sequenza2;
			s11 = seq11;
			s12 = seq12;
			s21 = seq21;
			s22 = seq22;
		}
		
		try{
			
			if (cont == 0)
				if (verifica1e2())
					return;
			
			if (cont == 1){
				if (verifica3e4())
					return;
			}
					
			
			if (!(s1.isEmpty()) && verifica.get(0)== s1.get(0)){
				if (s11.isEmpty() && s21.isEmpty()){
					if (verifica.size() == 1){
						Patta++;
						mangiaSposta();
						return;
					}
					else{
						throw new NullPointerException();
					}	
				}
				else{
					int verlen = verifica.size();
					if(verlen == 3){
						if (!(s12.isEmpty() && s11.isEmpty())){
							for (int i = 0; i < s11.size(); i++) {
								if (verifica.get(1) == s11.get(i)){
									if (s11.get(i).getPosJ() > bottonePremuto.getPosJ()){
										for (int j = 0; j < s12.size(); j++) {
											if (verifica.get(2) == s12.get(j) && s12.get(j).getPosJ()>bottonePremuto.getPosJ()){
												mangiaSposta();
												return;
											}
										}
										throw new NullPointerException();
									}
									else{
										for (int j = 0; j < s12.size(); j++) {
											if (verifica.get(2) == s12.get(j) && s12.get(j).getPosJ()<=s1.get(0).getPosJ()){
												mangiaSposta();
												return;
											}
										}
										throw new NullPointerException();
									}
								}
							}
							throw new NullPointerException();
						}
						else{
							throw new NullPointerException();
						}
					}
					else if (verlen == 2){
						if (!(s12.isEmpty())){
							for (int i = 0; i < s11.size(); i++) {
								if (verifica.get(1) == s11.get(i)){
									if (verifica.get(0) == s1.get(0)){
										soffio(bottonePremuto);
										return;
									}
									else{
										throw new NullPointerException();
									}
								}
							}
							throw new NullPointerException();
						}
						else{
							for (int i = 0; i < s11.size(); i++) {
								if (verifica.get(1) == s11.get(i)){
									if (verifica.get(0) == s1.get(0)){
										mangiaSposta();	
										return;
									}
									else{
										throw new NullPointerException();
									}
								}
							}
							throw new NullPointerException();
						}
					}
					else if (verlen == 1){
						if (verifica.get(0) == s1.get(0))
							soffio(bottonePremuto);
					}
				}
			}
			else if (!(s2.isEmpty()) && verifica.get(0) == s2.get(0)){
				if (s21.isEmpty() && s11.isEmpty()){
					if (verifica.size() == 1){
						Patta++;
						mangiaSposta();
						return;
					}
					else{
						throw new NullPointerException();
					}
				}
				else{
					int verlen = verifica.size();
					if(verlen == 3){
						if (!(s22.isEmpty())){
							for (int i = 0; i < s21.size(); i++) {
								if (verifica.get(1) == s21.get(i)){
									if (s21.get(i).getPosJ() < bottonePremuto.getPosJ()){
										for (int j = 0; j < s22.size(); j++) {
											if (verifica.get(2) == s22.get(j) && s22.get(j).getPosJ()<bottonePremuto.getPosJ()){
												mangiaSposta();
												return;
											}
										}
										throw new NullPointerException();
									}
									else{
										for (int j = 0; j < s22.size(); j++) {
											if (verifica.get(2) == s22.get(j) && s22.get(j).getPosJ()>=s2.get(0).getPosJ()){
												mangiaSposta();
												return;
											}
										}
										throw new NullPointerException();
									}
								}
							}
							throw new NullPointerException();
						}
						else{
							throw new NullPointerException();
						}
					}
					else if (verlen == 2){
						if (!(s22.isEmpty())){
							for (int i = 0; i < s21.size(); i++) {
								if (verifica.get(1) == s21.get(i)){
									if (verifica.get(0) == s2.get(0)){
										soffio(bottonePremuto);
										return;
									}
									else{
										throw new NullPointerException();
									}
								}
							}
							throw new NullPointerException();
						}
						else{
							for (int i = 0; i < s21.size(); i++) {
								if (verifica.get(1) == s21.get(i)){
									if (verifica.get(0) == s2.get(0)){
										mangiaSposta();	
										return;
									}
									else{
										throw new NullPointerException();
									}
								}
							}
							throw new NullPointerException();
						}
					}
					else if (verlen == 1){
						if (verifica.get(0) == s2.get(0))
							soffio(bottonePremuto);
					}
				}
			}
			else{
				throw new NullPointerException();
			}
		}
		catch(Exception e){
			if (flag==true && cont == 0){
				cont++;
				s1 = sequenza4;
				s2 = sequenza3;
				s11 = seq41;
				s12 = seq42;
				s21 = seq31;
				s22 = seq32;
				soffioValida(false);
				cont = 0;
				return;
			}
			//System.out.println(e);
			if (verifica.size() == 0)
				info.setText("DEVI FARE ALMENO UNA MOSSA");
			else{
				info.setText("SEQUENZA ERRATA");
			}
			if (spostamento == true){
				spostamento = false;
				lblGioc2.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
				lblGioc1.setBorder(null);
				passaG1.setVisible(false);
				passaG2.setVisible(true);
				freccia1.setVisible(false);
				freccia2.setVisible(true);
			}
			else{
				spostamento = true;
				lblGioc1.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
				lblGioc2.setBorder(null);
				passaG2.setVisible(false);
				passaG1.setVisible(true);
				freccia1.setVisible(true);
				freccia2.setVisible(false);
			}
		}
	}
	
	
	
	private boolean verifica1e2(){

		if (s1.size()>0 && s2.size()>0){
			if (s1.get(0).getPosI() != s2.get(0).getPosI() && s11.isEmpty() && s21.isEmpty()){
				if (bottonePremuto.getIcona().equals("biancoChiaro.png") || bottonePremuto.getDama() != 0){
					if (s1.get(0).getPosI() > s2.get(0).getPosI()){
						if (verifica.size() == 1){
							if (verifica.get(0) == s2.get(0)){
								mangiaSposta();
								return true;
							}
							else if (verifica.get(0) == s1.get(0)){
								soffio(bottonePremuto);
								return true;
							}
						}
					}

					else if (s1.get(0).getPosI() < s2.get(0).getPosI()){
						if (verifica.size() == 1){
							if (verifica.get(0) == s1.get(0)){
								mangiaSposta();
								return true;
							}
							else if (verifica.get(0) == s2.get(0)){
								soffio(bottonePremuto);
								return true;
							}
						}
					}

					info.setText("SEQUENZA ERRATA");
					if (spostamento == true){
						spostamento = false;
						lblGioc2.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
						lblGioc1.setBorder(null);
						passaG1.setVisible(false);
						passaG2.setVisible(true);
					}
					else{
						spostamento = true;
						lblGioc1.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
						lblGioc2.setBorder(null);
						passaG2.setVisible(false);
						passaG1.setVisible(true);
					}
					return true;
				}


				else if (bottonePremuto.getIcona().equals("neroChiaro.png")){

					if (s1.get(0).getPosI() > s2.get(0).getPosI()){
						if (verifica.size() == 1){
							if (verifica.get(0) == s1.get(0)){
								mangiaSposta();
								return true;
							}
							else if (verifica.get(0) == s2.get(0)){
								soffio(bottonePremuto);
								return true;
							}
						}
					}

					else if (s1.get(0).getPosI() < s2.get(0).getPosI()){
						if (verifica.size() == 1){
							if (verifica.get(0) == s2.get(0)){
								mangiaSposta();
								return true;
							}
							else if (verifica.get(0) == s1.get(0)){
								soffio(bottonePremuto);
								return true;
							}
						}
					}

					info.setText("SEQUENZA ERRATA");
					if (spostamento == true){
						spostamento = false;
						lblGioc2.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
						lblGioc1.setBorder(null);
						passaG1.setVisible(false);
						passaG2.setVisible(true);
					}
					else{
						spostamento = true;
						lblGioc1.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
						lblGioc2.setBorder(null);
						passaG2.setVisible(false);
						passaG1.setVisible(true);
					}
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean verifica3e4(){
		
		
		if (s1.size()>0 && s2.size()>0){
			if (s1.get(0).getPosI() != s2.get(0).getPosI() && s11.isEmpty() && s21.isEmpty()){
				if (s1.get(0).getPosI() > s2.get(0).getPosI()){
					if (verifica.size() == 1){
						if (verifica.get(0) == s1.get(0)){
							mangiaSposta();
							return true;
						}
						else if (verifica.get(0) == s2.get(0)){
							soffio(bottonePremuto);
							return true;
						}
					}
				}

				else if (s1.get(0).getPosI() < s2.get(0).getPosI()){
					if (verifica.size() == 1){
						if (verifica.get(0) == s2.get(0)){
							mangiaSposta();
							return true;
						}
						else if (verifica.get(0) == s1.get(0)){
							soffio(bottonePremuto);
							return true;
						}
					}
				}

				info.setText("SEQUENZA ERRATA");
				if (spostamento == true){
					spostamento = false;
					lblGioc2.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
					lblGioc1.setBorder(null);
					passaG1.setVisible(false);
					passaG2.setVisible(true);
				}
				else{
					spostamento = true;
					lblGioc1.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
					lblGioc2.setBorder(null);
					passaG2.setVisible(false);
					passaG1.setVisible(true);
				}
				return true;
			}
		}
	return false;
	}

					
	private void mangiaSposta(){
		
		Casella c = verifica.get(verifica.size()-1);
		String temp =c.getIcona();
		c.setIcona(bottonePremuto.getIcona());
		if (bottonePremuto.getIcona().equals("biancoChiaro.png") && c.getPosI() == 0){
			Patta = 0;
			c.setIcona("biancoDama.png");
			c.setDama(-1);
			bottonePremuto.setDama(0);
			info.setText("BENVENUTA DAMA BIANCA!");
		}
		else if (bottonePremuto.getIcona().equals("neroChiaro.png") && c.getPosI() == 7){
			Patta = 0;
			c.setIcona("neroDama.png");
			c.setDama(1);
			bottonePremuto.setDama(0);
			info.setText("BENVENUTA DAMA NERA!");
		}

		bottonePremuto.setIcona(temp);

		EliminaInMezzo(bottonePremuto, verifica.get(0));
		if (verifica.size() > 1){
			EliminaInMezzo(verifica.get(0), verifica.get(1));
			if (verifica.size() > 2)
				EliminaInMezzo(verifica.get(1), verifica.get(2));
		}

		if (c.getActionListeners().length > 0)
			for (int i = 0; i < c.getActionListeners().length; i++) 
				c.removeActionListener(c.getActionListeners()[i]);

		if (c.getIcona().equals("biancoChiaro.png"))
			c.addActionListener(new listenerBianco());
		else if (c.getIcona().equals("neroChiaro.png"))
			c.addActionListener(new listenerNero());
		else if (c.getIcona().equals("biancoDama.png"))
			c.addActionListener(new listenerDamaBianca());
		else
			c.addActionListener(new listenerDamaNera());
		
		if (bottonePremuto.getActionListeners().length > 0){
			for (int i = 0; i < bottonePremuto.getActionListeners().length; i++) {
				bottonePremuto.removeActionListener(bottonePremuto.getActionListeners()[i]);
			}
		}
	}

		
					
	private void soffio (Casella c){
		info.setText("E CON UN <<SOFFIO>> TI PORTO VIA LA PEDINA >.<");
		Patta = 0;
		if (c.getIcona().equals("biancoDama.png") && c.getIcona().equals("biancoChiaro.png"))
			pedineGioc1--;
		else
			pedineGioc2--;
		
		if (verifica.get(0).getPosJ() > c.getPosJ()){
			if (!(s12.isEmpty())){
				for (Casella a : s12){
					a.setIcona("soffio.png");
					a.removeActionListener(a.getActionListeners()[0]);
				}
				c.setIcona("chiaro.png");
				c.setDama(0);
				return;
			}
		
			else if (!(s11.isEmpty())){
				for (Casella a : s11){
					a.setIcona("soffio.png");
					a.removeActionListener(a.getActionListeners()[0]);
				}
				c.setIcona("chiaro.png");
				c.setDama(0);
				return;
			}
		}
		else if (verifica.get(0).getPosJ() < c.getPosJ()){
			if (!(s22.isEmpty())){
				for (Casella a : s22){
					a.setIcona("soffio.png");
					a.removeActionListener(a.getActionListeners()[0]);

				}
				c.setIcona("chiaro.png");
				c.setDama(0);
				return;
			}
			else if (!(s21.isEmpty())){
				for (Casella a : s21){
					a.setIcona("soffio.png");
					a.removeActionListener(a.getActionListeners()[0]);

				}
				c.setIcona("chiaro.png");
				c.setDama(0);
				return;
			}
		}
		if (c.getIcona().equals("biancoChiaro.png")){

			if (s1.get(0).getPosI() > s2.get(0).getPosI()){
				s2.get(0).setIcona("soffio.png");
				s2.get(0).removeActionListener(s2.get(0).getActionListeners()[0]);

			}
			else{
				s1.get(0).setIcona("soffio.png");
				s1.get(0).removeActionListener(s1.get(0).getActionListeners()[0]);
			}

		}
		else if (c.getIcona().equals("neroChiaro.png")){

			if (s1.get(0).getPosI() > s2.get(0).getPosI()){
				s1.get(0).setIcona("soffio.png");
				s1.get(0).removeActionListener(s1.get(0).getActionListeners()[0]);

			}
			else{
				s2.get(0).setIcona("soffio.png");
				s2.get(0).removeActionListener(s2.get(0).getActionListeners()[0]);
			}
		}
		
		c.setIcona("chiaro.png");
		c.setDama(0);
		return;
	}
		
	
	private void rendiSelezionabile(){
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (matrice.getMatrice()[i][j].getIcona().equals("chiaro.png") && matrice.getMatrice()[i][j].getActionListeners().length == 0)
					matrice.getMatrice()[i][j].addActionListener(new listenerRosso());
			}
		}
	}
	
	private void rendiNonSelezionabile(){
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (matrice.getMatrice()[i][j].getIcona().equals("chiaro.png") && matrice.getMatrice()[i][j].getActionListeners().length > 0)
					for (int j2 = 0; j2 < matrice.getMatrice()[i][j].getActionListeners().length; j2++) 
						matrice.getMatrice()[i][j].removeActionListener(matrice.getMatrice()[i][j].getActionListeners()[j2]);
				matrice.getMatrice()[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		}
	}
	
	
	
	private void sequenzeBianco(Casella c){

		Casella sucd = new Casella(-1,-1);
		Casella sucs = new Casella(-1,-1);
		Casella sucdd = new Casella(-1,-1);
		Casella sucss = new Casella(-1,-1);
		
		
		if (c.getPosJ() > bottonePremuto.getPosJ()){
			
			sequenza1.add(c);
			try{
				sucd = matrice.getMatrice()[c.getPosI()-2][c.getPosJ()+2];
			}
			catch(Exception e1){
			}
			try{
				sucs = matrice.getMatrice()[c.getPosI()-2][c.getPosJ()-2];
			}
			catch(Exception e1){
			}

			
			//CASO SUCD
			boolean flag2 = false;
			
			if (bottonePremuto.getDama() == -1){
				if(sucd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
						matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("neroDama.png")))
					flag2 = true;
			}
			else if (bottonePremuto.getDama() == 1){
				if(sucd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
						matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("biancoDama.png")))
					flag2 = true;
			}
			else{		
				if(sucd.getIcona().equals("chiaro.png") && matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("neroChiaro.png"))
					flag2 = true;
			}
					
					
			if (flag2){		
				seq11.add(sucd);
				try{
					sucdd = matrice.getMatrice()[sucd.getPosI()-2][sucd.getPosJ()+2];
				}
				catch(Exception e1){
				}
				try{
					sucss = matrice.getMatrice()[sucd.getPosI()-2][sucd.getPosJ()-2];
				}
				catch(Exception e1){
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("neroDama.png")))
						seq12.add(sucdd);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("biancoChiaro.png")||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("biancoDama.png")))
						seq12.add(sucdd);
				}
				else{		
					if(sucdd.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("neroChiaro.png"))
						seq12.add(sucdd);
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("neroDama.png")))
						seq12.add(sucss);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("biancoDama.png")))
						seq12.add(sucss);
				}
				else{		
					if (sucss.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("neroChiaro.png"))
						seq12.add(sucss);
				}
			}
			////////////
				
			///CASO SUCS	
			flag2 = false;
			
			if (bottonePremuto.getDama() == -1){
				if(sucs.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
						matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("neroDama.png")))
					flag2 = true;
			}
			else if (bottonePremuto.getDama() == 1){
				if(sucs.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
						matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("biancoDama.png")))
					flag2 = true;
			}
			else{		
				if (sucs.getIcona().equals("chiaro.png") && matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("neroChiaro.png"))
					flag2 = true;
			}
					
					
			if (flag2){		
				seq11.add(sucs);
				try{
					sucdd = matrice.getMatrice()[sucs.getPosI()-2][sucs.getPosJ()+2];
				}
				catch(Exception e1){
				}
				try{
					sucss = matrice.getMatrice()[sucs.getPosI()-2][sucs.getPosJ()-2];
				}
				catch(Exception e1){
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("neroDama.png")) && seq12.contains(sucdd))
						seq12.add(sucdd);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("biancoDama.png")) && seq12.contains(sucdd))
						seq12.add(sucdd);
				}
				else{		
					if (sucdd.getIcona().equals("chiaro.png") && (!(seq12.contains(sucdd))) && matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("neroChiaro.png"))
						seq12.add(sucdd);
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()-1].getIcona().equals("neroDama.png")))
						seq12.add(sucss);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()-1].getIcona().equals("biancoDama.png")))
						seq12.add(sucss);
				}
				else{		
					if (sucss.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()-1].getIcona().equals("neroChiaro.png"))
					seq12.add(sucss);
				}
			}
		}
		
		else{
			
			boolean flag2 = false;
			
			sequenza2.add(c);
			try{
				sucd = matrice.getMatrice()[c.getPosI()-2][c.getPosJ()+2];
			}
			catch(Exception e1){
			}
			try{
				sucs = matrice.getMatrice()[c.getPosI()-2][c.getPosJ()-2];
			}
			catch(Exception e1){
			}
			
			if (bottonePremuto.getDama() == -1){
				if(sucd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
						matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("neroDama.png")))
					flag2 = true;
			}
			else if (bottonePremuto.getDama() == 1){
				if(sucd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
						matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("biancoDama.png")))
					flag2 = true;
			}
			else{
				if(sucd.getIcona().equals("chiaro.png") && matrice.getMatrice()[c.getPosI()-1][c.getPosJ()+1].getIcona().equals("neroChiaro.png"))
					flag2 = true;
			}
			
			if (flag2){
				seq21.add(sucd);

				try{
					sucdd = matrice.getMatrice()[sucd.getPosI()-2][sucd.getPosJ()+2];
				}
				catch(Exception e1){
				}
				try{
					sucss = matrice.getMatrice()[sucd.getPosI()-2][sucd.getPosJ()-2];
				}
				catch(Exception e1){
				}

				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("neroDama.png")))
						seq22.add(sucdd);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("biancoDama.png")))
						seq22.add(sucdd);
				}
				else{		
					if (sucdd.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()+1].getIcona().equals("neroChiaro.png"))
						seq22.add(sucdd);
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("neroDama.png")))
						seq22.add(sucss);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("biancoDama.png")))
						seq22.add(sucss);
				}
				else{		
					if (sucss.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("neroChiaro.png"))
						seq22.add(sucss);
				}
			}
			
			flag2 = false;
			
			if (bottonePremuto.getDama() == -1){
				if(sucs.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
						matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("neroDama.png")))
					flag2 = true;
			}
			else if (bottonePremuto.getDama() == 1){
				if(sucs.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
						matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("biancoDama.png")))
					flag2 = true;
			}
			else{		
				if (sucs.getIcona().equals("chiaro.png") && matrice.getMatrice()[c.getPosI()-1][c.getPosJ()-1].getIcona().equals("neroChiaro.png"))
					flag2 = true;
			}
			
			if (flag2){
				seq21.add(sucs);
				
				try{
					sucdd = matrice.getMatrice()[sucs.getPosI()-2][sucs.getPosJ()+2];
				}
				catch(Exception e1){
				}
				try{
					sucss = matrice.getMatrice()[sucs.getPosI()-2][sucs.getPosJ()-2];
				}
				catch(Exception e1){
				}
				
				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("neroDama.png")))
						seq22.add(sucdd);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("biancoDama.png")))
						seq22.add(sucdd);
				}
				else{		
					if (sucdd.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()+1].getIcona().equals("neroChiaro.png"))
						seq22.add(sucdd);
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("neroDama.png")))
						seq22.add(sucss);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()-1][sucd.getPosJ()-1].getIcona().equals("biancoDama.png")))
						seq22.add(sucss);
				}
				else{		
					if (sucss.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucs.getPosI()-1][sucs.getPosJ()-1].getIcona().equals("neroChiaro.png"))
						seq22.add(sucss);
				}
			}
		}
	}

	
	private void sequenzeNero(Casella c,boolean flag){
		
		s1 = sequenza1;
		s2 = sequenza2;
		s11 = seq11;
		s12 = seq12;
		s21 = seq21;
		s22 = seq22;

		Casella sucd = new Casella(-1,-1);
		Casella sucs = new Casella(-1,-1);
		Casella sucdd = new Casella(-1,-1);
		Casella sucss = new Casella(-1,-1);
		
		if (flag){
			s1 = sequenza4;
			s2 = sequenza3;
			s11 = seq41;
			s12 = seq42;
			s21 = seq31;
			s22 = seq32;
		}
		
		if (c.getPosJ() > bottonePremuto.getPosJ()){
			
			s1.add(c);
			try{
				sucd = matrice.getMatrice()[c.getPosI()+2][c.getPosJ()+2];
			}
			catch(Exception e1){
			}
			try{
				sucs = matrice.getMatrice()[c.getPosI()+2][c.getPosJ()-2];
			}
			catch(Exception e1){
			}

			
			//CASO SUCD
			boolean flag2 = false;
			
			if (bottonePremuto.getDama() == -1){
				if(sucd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
						matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("neroDama.png")))
					flag2 = true;
			}
			else if (bottonePremuto.getDama() == 1){
				if(sucd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
						matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("biancoDama.png")))
					flag2 = true;
			}
			else{		
				if(sucd.getIcona().equals("chiaro.png") && matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("biancoChiaro.png"))
					flag2= true;
			}
			
			if (flag2){

				s11.add(sucd);

				try{
					sucdd = matrice.getMatrice()[sucd.getPosI()+2][sucd.getPosJ()+2];
				}
				catch(Exception e1){
				}
				try{
					sucss = matrice.getMatrice()[sucd.getPosI()+2][sucd.getPosJ()-2];
				}
				catch(Exception e1){
				}

				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("neroDama.png")))
						s12.add(sucdd);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("biancoDama.png")))
						s12.add(sucdd);
				}
				else{		
					if (sucdd.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("biancoChiaro.png"))
						s12.add(sucdd);
				}
					
				
				if (bottonePremuto.getDama() == -1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("neroDama.png")))
						s12.add(sucss);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("biancoDama.png")))
						s12.add(sucss);
				}
				else{		
					if (sucss.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("biancoChiaro.png"))
						s12.add(sucss);
				}
			}
			
			flag2 = false;
			
			if (bottonePremuto.getDama() == -1){
				if(sucs.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
						matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("neroDama.png")))
					flag2 = true;
			}
			else if (bottonePremuto.getDama() == 1){
				if(sucs.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
						matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("biancoDama.png")))
					flag2 = true;
			}
			else{		
				if (sucs.getIcona().equals("chiaro.png") && matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("biancoChiaro.png"))
					flag2 = true;
			}
				
			if (flag2){
				s11.add(sucs);
				
				try{
					sucdd = matrice.getMatrice()[sucs.getPosI()+2][sucs.getPosJ()+2];
				}
				catch(Exception e1){
				}
				try{
					sucss = matrice.getMatrice()[sucs.getPosI()+2][sucs.getPosJ()-2];
				}
				catch(Exception e1){
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("neroDama.png") && s12.contains(sucdd)))
						s12.add(sucdd);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("biancoDama.png") && s12.contains(sucdd)))
						s12.add(sucdd);
				}
				else{		
					if (sucdd.getIcona().equals("chiaro.png") && !(s12.contains(sucdd)) && matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("biancoChiaro.png"))
						s12.add(sucdd);
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()-1].getIcona().equals("nerooChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()-1].getIcona().equals("neroDama.png")))
						s12.add(sucss);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()-1].getIcona().equals("biancoDama.png")))
						s12.add(sucss);
				}
				else{		
					if (sucss.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()-1].getIcona().equals("biancoChiaro.png"))
					s12.add(sucss);
				}
			}
		}
		else{
			
			boolean flag2 = false;
			s2.add(c);

			try{
				sucd = matrice.getMatrice()[c.getPosI()+2][c.getPosJ()+2];
			}
			catch(Exception e1){
			}
			try{
				sucs = matrice.getMatrice()[c.getPosI()+2][c.getPosJ()-2];
			}
			catch(Exception e1){
			}
			
			if (bottonePremuto.getDama() == -1){
				if(sucd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
						matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("neroDama.png")))
					flag2 = true;
			}
			else if (bottonePremuto.getDama() == 1){
				if(sucd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
						matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("biancoDama.png")))
					flag2 = true;
			}
			else{
				if(sucd.getIcona().equals("chiaro.png") && matrice.getMatrice()[c.getPosI()+1][c.getPosJ()+1].getIcona().equals("biancoChiaro.png"))
					flag2 = true;
			}
			
			if (flag2){
				s21.add(sucd);
			
				try{
					sucdd = matrice.getMatrice()[sucd.getPosI()+2][sucd.getPosJ()+2];
				}
				catch(Exception e1){
				}
				try{
					sucss = matrice.getMatrice()[sucd.getPosI()+2][sucd.getPosJ()-2];
				}
				catch(Exception e1){
				}
				
				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("neroDama.png")))
						s22.add(sucdd);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("neroDama.png")))
						s22.add(sucdd);
				}
				else{		
					if (sucdd.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()+1].getIcona().equals("biancoChiaro.png"))
						s22.add(sucdd);
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("neroDama.png")))
						s22.add(sucss);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("biancoDama.png")))
						s22.add(sucss);
				}
				else{		
					if (sucss.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("biancoChiaro.png"))
						s22.add(sucss);
				}
			}
				
			flag2 = false;
			
			if (bottonePremuto.getDama() == -1){
				if(sucs.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
						matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("neroDama.png")))
					flag2 = true;
			}
			else if (bottonePremuto.getDama() == 1){
				if(sucs.getIcona().equals("chiaro.png") && (matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
						matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("biancoDama.png")))
					flag2 = true;
			}
			else{		
				if (sucs.getIcona().equals("chiaro.png") && matrice.getMatrice()[c.getPosI()+1][c.getPosJ()-1].getIcona().equals("biancoChiaro.png"))
					flag2 = true;
			}
			
			if (flag2){
					
				s21.add(sucs);
				
				try{
					sucdd = matrice.getMatrice()[sucs.getPosI()+2][sucs.getPosJ()+2];
				}
				catch(Exception e1){
				}
				try{
					sucss = matrice.getMatrice()[sucs.getPosI()+2][sucs.getPosJ()-2];
				}
				catch(Exception e1){
				}
				
				if (bottonePremuto.getDama() == -1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("neroDama.png") && s22.contains(sucdd)))
						s22.add(sucdd);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucdd.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("biancoDama.png") && s22.contains(sucdd)))
						s22.add(sucdd);
				}
				else{		
					if (sucdd.getIcona().equals("chiaro.png") && !(s22.contains(sucdd)) && matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()+1].getIcona().equals("biancoChiaro.png"))
						s22.add(sucdd);
				}
				
				
				if (bottonePremuto.getDama() == -1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("neroChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("neroDama.png")))
						s22.add(sucss);
				}
				else if (bottonePremuto.getDama() == 1){
					if(sucss.getIcona().equals("chiaro.png") && (matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("biancoChiaro.png") ||
							matrice.getMatrice()[sucd.getPosI()+1][sucd.getPosJ()-1].getIcona().equals("biancoDama.png")))
						s22.add(sucss);
				}
				else{		
					if (sucss.getIcona().equals("chiaro.png") && matrice.getMatrice()[sucs.getPosI()+1][sucs.getPosJ()-1].getIcona().equals("biancoChiaro.png"))
						s22.add(sucss);
				}
			}
		}
	}
	
	
	 

	private void EliminaInMezzo(Casella botprem,Casella botprem2){
		
		if (botprem2.getPosI() - botprem.getPosI() == -2){
			if(botprem2.getPosJ() - botprem.getPosJ() == -2){
				Casella temp = matrice.getMatrice()[botprem.getPosI()-1][botprem.getPosJ()-1];
				if (temp.getIcona().equals("neroChiaro.png")){
					panelGioc1.add(new Punto("nero.png"));
					pedineGioc2--;
				}
				else if (temp.getIcona().equals("biancoChiaro.png")){
					panelGioc2.add(new Punto("bianco.png"));
					pedineGioc1--;
				}
				else if (temp.getIcona().equals("neroDama.png")){
					panelGioc1.add(new Punto("neroD.png"));
					pedineGioc2--;
				}
				else{
					panelGioc2.add(new Punto("biancoD.png"));
					pedineGioc1--;
				}
				temp.setIcona("chiaro.png");
				Patta = 0;
				if (temp.getActionListeners().length > 0){
					for (int i = 0; i < temp.getActionListeners().length; i++)
						temp.removeActionListener(temp.getActionListeners()[i]);
				}
				if (info.getText() == null)
					info.setText("MANGIA MANGIA ;)");
			}
			else if (botprem2.getPosJ() - botprem.getPosJ() == 2){
				Casella temp = matrice.getMatrice()[botprem.getPosI()-1][botprem.getPosJ()+1];
				if (temp.getIcona().equals("neroChiaro.png")){
					panelGioc1.add(new Punto("nero.png"));
					pedineGioc2--;
				}
				else if (temp.getIcona().equals("biancoChiaro.png")){
					panelGioc2.add(new Punto("bianco.png"));
					pedineGioc1--;
				}
				else if (temp.getIcona().equals("neroDama.png")){
					panelGioc1.add(new Punto("neroD.png"));
					pedineGioc2--;
				}
				else{
					panelGioc2.add(new Punto("biancoD.png"));
					pedineGioc1--;
				}
				temp.setIcona("chiaro.png");
				Patta = 0;
				if (temp.getActionListeners().length > 0){
					for (int i = 0; i < temp.getActionListeners().length; i++)
						temp.removeActionListener(temp.getActionListeners()[i]);
				}
				if (info.getText() == null)
					info.setText("MANGIA MANGIA ;)");
			}
		}
		else if (botprem2.getPosI() - botprem.getPosI() == 2){
			if(botprem2.getPosJ() - botprem.getPosJ() == -2){
				Casella temp = matrice.getMatrice()[botprem.getPosI()+1][botprem.getPosJ()-1];
				if (temp.getIcona().equals("neroChiaro.png")){
					panelGioc1.add(new Punto("nero.png"));
					pedineGioc2--;
				}
				else if (temp.getIcona().equals("biancoChiaro.png")){
					panelGioc2.add(new Punto("bianco.png"));
					pedineGioc1--;
				}
				else if (temp.getIcona().equals("neroDama.png")){
					panelGioc1.add(new Punto("neroD.png"));
					pedineGioc2--;
				}
				else{
					panelGioc2.add(new Punto("biancoD.png"));
					pedineGioc1--;
				}
				temp.setIcona("chiaro.png");
				Patta = 0;
				if (temp.getActionListeners().length > 0){
					for (int i = 0; i < temp.getActionListeners().length; i++)
						temp.removeActionListener(temp.getActionListeners()[i]);
				}
				if (info.getText() == null)
					info.setText("MANGIA MANGIA ;)");
			}
			else if (botprem2.getPosJ() - botprem.getPosJ() == 2){
				Casella temp = matrice.getMatrice()[botprem.getPosI()+1][botprem.getPosJ()+1];
				if (temp.getIcona().equals("neroChiaro.png")){
					panelGioc1.add(new Punto("nero.png"));
					pedineGioc2--;
				}
				else if (temp.getIcona().equals("biancoChiaro.png")){
					panelGioc2.add(new Punto("bianco.png"));
					pedineGioc1--;
				}
				else if (temp.getIcona().equals("neroDama.png")){
					panelGioc1.add(new Punto("neroD.png"));
					pedineGioc2--;
				}
				else{
					panelGioc2.add(new Punto("biancoD.png"));
					pedineGioc1--;
				}
				temp.setIcona("chiaro.png");
				Patta = 0;
				if (temp.getActionListeners().length > 0){
					for (int i = 0; i < temp.getActionListeners().length; i++)
						temp.removeActionListener(temp.getActionListeners()[i]);
				}
				if (info.getText() == null)
					info.setText("MANGIA MANGIA ;)");
			}
		}
		frame.revalidate();
		frame.repaint();
	}
	
	
	private boolean verificaVittoria(){
		if (pedineGioc1 == 0){
			if (lblGioc2.getText().equals("Giocatore 2"))
				info.setText("PARTITA TERMINATA: VINCE IL GIOCATORE 2 !!");
			else
				info.setText("PARTITA TERMINATA: VINCE "+info.getText().toUpperCase()+" !!");
			return true;
		}
		else if(pedineGioc2 == 0){
			if (lblGioc1.getText().equals("Giocatore 1"))
				info.setText("PARTITA TERMINATA: VINCE IL GIOCATORE 1 !!");
			else
				info.setText("PARTITA TERMINATA: VINCE "+info.getText().toUpperCase()+" !!");
			return true;
		}
		else if (Patta == 20){
			info.setText("Dopo 20 turni, il gioco  rimasto in stallo: LA PARTITA TERMINA IN PARITA' !!");
			return true;
		}
		return false;
	}

	
	public static void main(String[] args) {
		new GUI();
	}
}

