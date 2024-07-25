package interactions;

import action.Logger;
import gui.GameScreen;

public class InteractableDialog extends InteractableTemplate{
	int Dialog;
	private String sender;
	
	public InteractableDialog(int posX, int posY, String Action, int Dialog, String sender ) {
		super(10, 10, posX, posY, 100, Action);
		this.Dialog = Dialog;
		this.sender = sender;
	}
	
	@Override
	public void performAction() {
		Logger.logInfo("interacted");
		super.performAction();
		GameScreen.currentDialog = "log"+Dialog;
		GameScreen.startDialog(sender);
	}	
}
