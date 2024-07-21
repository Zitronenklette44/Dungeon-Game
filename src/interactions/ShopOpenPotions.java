package interactions;

import gameMusik.MusicPlayer;
import gui.GameScreen;
import translation.Translation;

public class ShopOpenPotions extends InteractableTemplate{

	public ShopOpenPotions(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, Translation.get("interaction.openPotions"));
	}
	
	@Override
	public void performAction() {
		super.performAction();
		GameScreen.hideFrame();
		gui.Shops.ShopPotions.erstellen();
		MusicPlayer.pauseAllSound();
		MusicPlayer.playSound(2, true);
		
	}
	
	
	
}
