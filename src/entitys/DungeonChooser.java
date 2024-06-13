package entitys;

import game.GameLogic;
import gui.DungeonChooserGUI;
import gui.GameScreen;

public class DungeonChooser extends InteractableTemplate{

	public DungeonChooser(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, "Enter Dungeon");
	}
	
	
	@Override
	public void performAction() {
		super.performAction();
		DungeonChooserGUI.erstellen(GameLogic.dungeonKey);
		GameScreen.hideFrame();
	}
}
