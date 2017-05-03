package br.pro.hashi.ensino.desagil.lucianogic.model;

public class HalfGate extends Gate {
	private NandGate nandLeft;
	private NandGate nandUp;
	private NandGate nandDown;
	private NandGate nandRight;
	private NandGate nandBot;
			

	public HalfGate() {
		super(2, 2);

		name = "HALF";

		nandLeft = new NandGate();
		nandUp = new NandGate();
		nandDown = new NandGate();
		nandRight = new NandGate();
		nandBot = new NandGate();
		
		nandUp.connect(nandLeft,1);
		nandDown.connect(nandLeft,0);
		nandRight.connect(nandUp, 0);
		nandRight.connect(nandDown, 1);
		nandBot.connect(nandLeft,0);
		nandBot.connect(nandLeft,1);

		
	}

	@Override
	public boolean doRead(int index) {
		switch(index) {
		
		case 0:
			
			return nandRight.read();

		case 1:
			
			return nandBot.read();
		}
		
		return false;
	}

	
	@Override
	protected void doConnect(Emitter emitter, int index) {
		switch(index) {
		
		case 0:
			
			nandLeft.connect(emitter, 0);
			nandUp.connect(emitter, 0);
			
			break;
			
		case 1:
			
			nandLeft.connect(emitter, 1);
			nandDown.connect(emitter, 1);
			
			break;
		}
	}
}

