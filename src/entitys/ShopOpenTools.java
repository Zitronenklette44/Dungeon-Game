package entitys;

import gameMusik.MusicPlayer;
import gui.GameScreen;

public class ShopOpenTools extends InteractableTemplate{

	public ShopOpenTools(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, "Shop für Tools öffnen");
	}
	
	@Override
	public void performAction() {
		super.performAction();
		gui.Shops.ShopTools.erstellen();
		GameScreen.hideFrame();
		MusicPlayer.pauseAllSound();
		MusicPlayer.playSound(2, true);
		
	}
	
	
	
}
