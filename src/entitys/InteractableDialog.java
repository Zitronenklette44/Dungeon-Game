package entitys;

import action.Logger;
import gui.GameScreen;

public class InteractableDialog extends InteractableTemplate{
	int Dialog;
	
	public InteractableDialog(int posX, int posY, String Action, int Dialog ) {
		super(10, 10, posX, posY, 100, Action);
		this.Dialog = Dialog;
	}
	
	@Override
	public void performAction() {
		Logger.logInfo("interacted");
		super.performAction();
		GameScreen.currentDialog = "log"+Dialog;
		GameScreen.startDialog("Test");
	}	
}
