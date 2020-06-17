package anno17_18.ingdelsoftware.progetto.view.builder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import anno17_18.ingdelsoftware.progetto.controller.GiocaGrattacieli5;

class Nord extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JButton help;

	Nord() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		help = new JButton("HELP");
		HelperGUI11 hp11 = new HelperGUI11();
		HelperGUI12 hp12 = new HelperGUI12();
		hp11.setSuperiore(hp12);
		hp12.setSuperiore(hp11);
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hp11.check(GiocaGrattacieli5.gui.chooseCORHelper());
			}
		});
		this.add(help);
	}// COSTRUTTORE

}// Nord
