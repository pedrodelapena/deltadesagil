package br.pro.hashi.ensino.desagil.lucianogic.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import br.pro.hashi.ensino.desagil.lucianogic.model.Gate;
import br.pro.hashi.ensino.desagil.lucianogic.model.Switch;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import br.pro.hashi.ensino.desagil.lucianogic.model.LED;


public class GateView extends FixedPanel implements ItemListener {

	// Necessario para serializar objetos desta classe.
	private static final long serialVersionUID = 1L;


	private Image image;

	private JCheckBox[] inBoxes;
	private JCheckBox[] outBoxes;

	private Switch[] switches;
	private Gate gate;
	
	private LED led;
	private LED led2;
	
	private JButton ledButton = new JButton();
	private JButton ledButton2 = new JButton();
	private Color color;


	public GateView(Gate gate) {
		super(205, 180);

		this.gate = gate;
		
		led = new LED(255,0,0);
		
		led2 = new LED(255,0,0);

		image = loadImage(gate.toString());

		int inSize = gate.getInSize();
		
		int outSize = gate.getOutSize();

		inBoxes = new JCheckBox[inSize];

		switches = new Switch[inSize];
		
		outBoxes = new JCheckBox[outSize];
		


		for(int i = 0; i < inSize; i++) {
			inBoxes[i] = new JCheckBox();

			inBoxes[i].addItemListener(this);

			switches[i] = new Switch();

			gate.connect(switches[i], i);
			
		}

		
		if(inSize == 1) {
			add(inBoxes[0], 0, 60, 20, 20);			
		}
		else {
			for(int i = 0; i < inSize; i++) {
				add(inBoxes[i], 0, (i + 1) * 40, 20, 20);			
			}			
		}

		if(outSize == 1){
			outBoxes[0] = new JCheckBox();
			
			outBoxes[0].setEnabled(false);
			
			add(outBoxes[0], 184, 60, 20, 20);
			
			outBoxes[0].setSelected(gate.read());
		
		}
		else {
			
			for(int i = 0; i < outSize; i++) {
				outBoxes[i] = new JCheckBox();
				
				outBoxes[i].setEnabled(false);
				
				add(outBoxes[i], 180, ((i + 1) * 42)+15, 20, 20);
				
				outBoxes[i].setSelected(gate.read(i));

		}
			
		}

	}


	@Override
	public void itemStateChanged(ItemEvent event) {
		int i;
		for(i = 0; i < inBoxes.length; i++) {
			if(inBoxes[i] == event.getSource()) {
				break;
			}
		}
		
		switches[i].setOn(inBoxes[i].isSelected());
		
		for (i = 0; i < outBoxes.length; i++){
			outBoxes[i].setSelected(gate.read((i)));
		}
	}


	// Necessario para carregar os arquivos de imagem.
	private Image loadImage(String filename) {
		URL url = getClass().getResource("/img/" + filename + ".png");
		ImageIcon icon = new ImageIcon(url);
		return icon.getImage();
	}


	@Override
	public void paintComponent(Graphics g) {
		// Evita bugs visuais em alguns sistemas operacionais.
		super.paintComponent(g);

		g.drawImage(image, 10, 20, 184, 140, null);

		// Evita bugs visuais em alguns sistemas operacionais.
		getToolkit().sync();
    }
}
