package interactions;

import gameMusik.MusicPlayer;
import gui.GameScreen;
import translation.Translation;

public class ShopOpenTools extends InteractableTemplate{

	public ShopOpenTools(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, Translation.get("interaction.openTool"));
	}
	
	@Override
	public void performAction() {
		super.performAction();
		GameScreen.hideFrame();
		gui.Shops.ShopTools.erstellen();
		MusicPlayer.pauseAllSound();
		MusicPlayer.playSound(2, true);
		
	}
	
	
	
}
