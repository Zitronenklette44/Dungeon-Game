package interactions;

import game.GameLogic;
import gui.GameScreen;
import translation.Translation;

public class QuestBoard extends InteractableTemplate {

	public QuestBoard(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 50, Translation.get("interaction.questBoard"));
		
	}

	
		@Override
		public void performAction() {
			super.performAction();
			GameLogic.paused = true;
			GameScreen.quests.setVisible(true);
		}
}
