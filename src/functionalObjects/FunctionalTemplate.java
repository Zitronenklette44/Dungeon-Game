package functionalObjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import action.Logger;
import interactions.InteractableTemplate;

public class FunctionalTemplate extends InteractableTemplate{
	public BufferedImage image;
	public boolean vanishAfterInteraction;
	public boolean isExisting;
	public String name;
	
	public FunctionalTemplate(int hoehe, int breite, int posX, int posY, int range, String Action, BufferedImage image, boolean vanishAfterInteraction, boolean isExisting) {
		super(hoehe, breite, posX, posY, range, Action);
		this.image = image;
		this.vanishAfterInteraction = vanishAfterInteraction;
		this.isExisting = isExisting;
	}
	
	@Override
	public void performAction() {
		super.performAction();
		if(vanishAfterInteraction) {
			Logger.logInfo(this +" sollte jetzt verschwinden");
		}
	}
	
	public void draw(Graphics2D g){
		if(isExisting && isVisible) {
			g.drawImage(image, (int) posX, (int) posY, null);
		}
	}

	public void draw(Graphics2D g) {
		if(isExisting && isVisible) {
			g.drawImage(image, (int) posX, (int) posY, null);
		}
		
	}

}
