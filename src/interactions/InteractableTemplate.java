package interactions;

import game.GameLogic;
import gameObject.Rechteck;

public class InteractableTemplate extends Rechteck{
	public String interactionString;
	public int range;
	public boolean actionEnabled = false;
	
	public InteractableTemplate(int hoehe, int breite, int posX, int posY, int range, String Action) {
		super(hoehe, breite, posX, posY);
		this.interactionString = Action;
		this.range = range;
	}
	
	public void performAction() {GameLogic.Interact = false;GameLogic.counterInteraction =0;}	//wenn interagiert
	
	public void update(){}
	
}
