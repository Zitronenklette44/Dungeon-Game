package entitys;

import game.GameLogic;
import gui.DungeonChooserGUI;
import gui.GameScreen;
import translation.Translation;

public class DungeonChooser extends InteractableTemplate{

	public DungeonChooser(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, Translation.get("interaction.dungeonEnter"));
	}
	
	
	@Override
	public void performAction() {
		super.performAction();
		GameScreen.hideFrame();
		DungeonChooserGUI.erstellen(GameLogic.dungeonKey);
	}
}
