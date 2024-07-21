package functionalObjects;

import java.awt.image.BufferedImage;

import action.Logger;
import interactions.InteractableTemplate;

public class FunctionalTemplate extends InteractableTemplate{
	public BufferedImage image;
	public boolean vanishAfterInteraction;
	public boolean isExisting;
	
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

}
