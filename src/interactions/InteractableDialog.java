package interactions;

import action.Logger;
import game.GameLogic;
import gui.GameScreen;

public class InteractableDialog extends InteractableTemplate{
	int Dialog;
	int[] Dialogs;
	int currentDialog;
	private String sender;
	
	public InteractableDialog(int posX, int posY, String Action, int Dialog, String sender ) {
		super(10, 10, posX, posY, 100, Action);
		this.Dialog = Dialog;
		this.sender = sender;
	}
	public InteractableDialog(int posX, int posY, String Action, int[] Dialogs, String sender ) {
		super(10, 10, posX, posY, 100, Action);
		this.Dialogs = Dialogs;
		currentDialog = 0;
		this.sender = sender;
	}
	
	@Override
	public void performAction() {
		if(GameLogic.currentQuestDone) {
			currentDialog ++;
		}
//		Dialog = Dialogs[currentDialog];
		Logger.logInfo("interacted");
		super.performAction();
		GameScreen.currentDialog = "log"+Dialog;
		GameScreen.startDialog(sender);
	}	
}
