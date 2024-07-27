package interactions;

import action.Logger;
import game.GameLogic;
import gui.GameScreen;

public class InteractableDialog extends InteractableTemplate{
	int Dialog;
	int[] Dialogs;
	int currentDialog;
	protected String sender;
	
	public InteractableDialog(int posX, int posY, String Action, int Dialog, String sender ) {
		super(10, 10, posX, posY, 100, Action);
		this.Dialogs = new int[] {Dialog};
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
		if(GameLogic.currentQuestDone && Dialogs.length-1 > currentDialog) {
			currentDialog ++;
		}
		Logger.logInfo("current Dialog: "+currentDialog +" max Dialog: "+ Dialogs.length);
		Dialog = Dialogs[currentDialog];
		Logger.logInfo("interacted");
		super.performAction();
		GameScreen.currentDialog = "log"+Dialog;
		GameScreen.startDialog(sender);
	}	
}
