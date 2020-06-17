package anno16_17.poo.progettoEsame.CollectionTestGUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.SceltaList;
import anno16_17.poo.progettoEsame.CollectionTestGUI.CollectionToGrafica.SceltaTipo;


class FrameToChoose extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private final String titolo = "COLLECTION TEST GUI";
	private final int xSize = 500, ySize = 500;

	private JToolBar toolbar;
	private JButton bInfo = new JButton("INFO"), bLegenda = new JButton("LEGENDA");

	private JPanel panelBoss;

	private JPanel panelStampaTitolo;
	private JLabel stampaTitolo = new JLabel("COLLECTION TEST");

	private JPanel panelListChoose;
	private JRadioButton rbArrayList = new JRadioButton("ArrayList   "),
			rbLinkedList = new JRadioButton("LinkedList");

	private JPanel panelDimChoose;
	private JLabel lDim = new JLabel("Dimensione: ");
	private JTextField tfDim = new JTextField();

	private JPanel panelTypeChoose;
	private JRadioButton rbString = new JRadioButton("String   "), 
			rbInteger = new JRadioButton("int");

	private JPanel panelGenerate;
	private JButton bGenerate = new JButton("GENERATE");
	
	
	FrameToChoose() {

		setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				if( consensoUscita() ) System.exit(0);
			}
		} );

		this.setTitle(titolo);
		this.setSize(xSize, ySize);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		//TOOLBAR
		toolbar = new JToolBar();
		bInfo.setBorder(BorderFactory.createEmptyBorder());
		bLegenda.setBorder(BorderFactory.createEmptyBorder());
		toolbar.addSeparator();
		toolbar.add(bInfo);
		toolbar.addSeparator();
		toolbar.add(bLegenda);
		toolbar.setFloatable(false);
		toolbar.setVisible(true);
		bLegenda.addActionListener(new listenerInfoAndHelp() );
		bInfo.addActionListener(new listenerInfoAndHelp() );
		this.getContentPane().add(toolbar, BorderLayout.NORTH);

		//PANNELLO PRINCIPALE
		panelBoss = new JPanel();
		panelBoss.setVisible(true);
		panelBoss.setLayout(new BoxLayout(panelBoss, BoxLayout.Y_AXIS));
		this.getContentPane().add(panelBoss);

		panelStampaTitolo = new JPanel();
		stampaTitolo.setFont(new Font("sansserif",Font.BOLD,45));
		panelStampaTitolo.add(stampaTitolo);
		panelBoss.add(panelStampaTitolo);

		panelListChoose = new JPanel();
		panelBoss.add(panelListChoose);
		rbArrayList.setFont(new Font("Comic Sans MS",Font.BOLD,22));
		rbArrayList.setSelected(true);
		panelListChoose.add(rbArrayList);
		rbLinkedList.setFont(new Font("Comic Sans MS",Font.BOLD,22));
		panelListChoose.add(rbLinkedList);

		panelDimChoose = new JPanel();
		panelBoss.add(panelDimChoose);
		lDim.setFont(new Font(null,Font.LAYOUT_LEFT_TO_RIGHT,20));
		panelDimChoose.add(lDim);
		tfDim.setFont(new Font(null,Font.ITALIC,18));
		tfDim.setText(" default ");
		panelDimChoose.add(tfDim);

		panelTypeChoose = new JPanel();
		panelBoss.add(panelTypeChoose);
		rbString.setFont(new Font("Comic Sans MS",Font.BOLD,22));
		rbString.setSelected(true);
		panelTypeChoose.add(rbString);
		rbInteger.setFont(new Font("Comic Sans MS",Font.BOLD,22));
		panelTypeChoose.add(rbInteger);

		panelGenerate = new JPanel();
		bGenerate.setFont(new Font("Baskerville",Font.BOLD,25));
		panelGenerate.add(bGenerate);
		panelBoss.add(panelGenerate);

		//----------------------------------/
		inizializzaAscoltatori();

	}//COSTRUTTORE

	private void inizializzaAscoltatori(){
		bGenerate.addActionListener( this );
		rbArrayList.addActionListener(new listenerRbListChoose());
		rbLinkedList.addActionListener(new listenerRbListChoose());
		tfDim.addMouseListener ( new adapterTfDim() );
		rbString.addActionListener( new listenerRbTypeChoose() );
		rbInteger.addActionListener( new listenerRbTypeChoose() );
	}

	public void actionPerformed(ActionEvent e){
		
		@SuppressWarnings("rawtypes")
		CollectionToGrafica<?> ctg = new CollectionToGrafica();
		boolean flagOK = false;

		if ( rbArrayList.isSelected() ){
			if ( tfDim.getText().equals(" default ") || tfDim.getText().equals("") ){
				ctg.generaArrayList();
				flagOK = true;
			}
			else if ( !tfDim.getText().matches("-?\\d*") )
				JOptionPane.showMessageDialog(null, "SOLO NUMERI SONO ACCETTATI");
			else {
				try {
					ctg.generaArrayList(Integer.parseInt(tfDim.getText()));
					flagOK = true;
				} catch ( IllegalArgumentException iea ) {
					JOptionPane.showMessageDialog(null, "LA DIMENSIONE DEVE ESSERE > 0");
				}
			}
		}
		else {
			ctg.generaLinkedList();
			flagOK = true;
		}
		if ( flagOK ) avviaFrameOfMethods(ctg);

	}

	private class adapterTfDim extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			tfDim.setText("");
		}

	}//adapterTfDim

	private class listenerRbListChoose implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource()==rbArrayList ){
				rbArrayList.setSelected(true);
				rbLinkedList.setSelected(false);
				tfDim.setText(" default ");
				if ( tfDim.getMouseListeners().length == 3 ) {
					tfDim.addMouseListener( new adapterTfDim() );;
					tfDim.setEnabled(true);
				}
				CollectionToGrafica.tipoList = SceltaList.ARRAY_LIST;
			}
			else {
				rbLinkedList.setSelected(true);
				rbArrayList.setSelected(false);
				tfDim.setText(" default ");
				if ( tfDim.getMouseListeners().length == 4 )
					tfDim.removeMouseListener(tfDim.getMouseListeners()[3]);
				tfDim.setEnabled(false);
				CollectionToGrafica.tipoList = SceltaList.LINKED_LIST;
			}
		}	
	}//listenerRbListChoose

	private class listenerRbTypeChoose implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource()==rbString ){
				rbString.setSelected(true);
				rbInteger.setSelected(false);
				CollectionToGrafica.tipo = SceltaTipo.STRING;
			}
			else {
				rbString.setSelected(false);
				rbInteger.setSelected(true);
				CollectionToGrafica.tipo = SceltaTipo.INTEGER;
			}
		}	
	}//listenerRbTypeChoose
	
	private class listenerInfoAndHelp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if ( e.getSource()==bInfo ) {
				JOptionPane.showMessageDialog(null, "Author: Giuseppe Longo\n"
						+ "Matricola: 175983", "INFO",
				        JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, ""
						+ "IN FRAME_OF_METHODS...\n"
						+ "- Per tipo INT, inserire cifre normalmente\n"
						+ "- Per tipo STRING, utilizzare apici ", "LEGENDA",
				        JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}//listenerInfoAndHelp
	
	//-------------------------------------------------------------------------//
		
	private void avviaFrameOfMethods(CollectionToGrafica<?> ctg){
			super.setVisible(false);
			FrameOfMethods fom;
			
			if ( CollectionToGrafica.tipo==SceltaTipo.INTEGER ) {
				CollectionToGrafica.tipoForButtons = "int i";
				CollectionToGrafica.tipoForButtonsBoxed = "Integer";
				@SuppressWarnings("unchecked")
				CollectionToGrafica<Integer> ctgI = (CollectionToGrafica<Integer>)ctg;
				fom = new FrameOfMethods(ctgI);
			}
			else {
				CollectionToGrafica.tipoForButtons = "String s";
				CollectionToGrafica.tipoForButtonsBoxed = "String";
				@SuppressWarnings("unchecked")
				CollectionToGrafica<String> ctgS = (CollectionToGrafica<String>)ctg;
				fom = new FrameOfMethods(ctgS);
			}
			fom.setVisible(true);
		}//avviaFrameMethods
		
		private boolean consensoUscita(){
			int option=JOptionPane.showConfirmDialog(
					null, "Continuare ?","Sei deciso a terminare...", JOptionPane.YES_NO_OPTION);
			return option==JOptionPane.YES_OPTION;
		}//consensoUscita
		
	//-------------------------------------------------------------------------//
		
		
}//FrameForChoose



