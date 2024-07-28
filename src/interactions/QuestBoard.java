package interactions;

import gui.GameScreen;
import translation.Translation;

public class QuestBoard extends InteractableTemplate {

	public QuestBoard(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, Translation.get("interaction.questBoard"));
		
	}

	
		@Override
		public void performAction() {
			super.performAction();
			GameScreen.toggleQuests();
		}
}
