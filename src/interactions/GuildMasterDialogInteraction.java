package interactions;

import action.Logger;
import game.GameLogic;
import gui.GameScreen;
import questSystem.QuestManager;
import questSystem.quests.GuildEntryQuest;

public class GuildMasterDialogInteraction extends InteractableDialog{
	boolean finishedField = false;

	public GuildMasterDialogInteraction(int posX, int posY, String Action, int[] Dialog, String sender) {
		super(posX, posY, Action, Dialog, sender);
	}
	
	public void performAction() {
		int index = -1;
		for(int i = 0; i<GameLogic.currentQuest.size(); i++) {
			if(GameLogic.currentQuest.get(i) instanceof GuildEntryQuest) {
				index = i;
				break;
			}
			
		}
		
		if(index == -1) return;
		Logger.logInfo("currentQuest :" +GameLogic.currentQuest+" choosenQuest: "+GameLogic.choosenQuest+" currentQuestDone: "+GameLogic.currentQuestDone);
		if(GameLogic.currentQuestDone.get(index) && Dialogs.length-4 > currentDialog) {
			currentDialog ++;
		}else
		
		if((GameLogic.choosenQuest == null && GameLogic.currentQuest == null) && finishedField) {		//wenn keine Quest ausgewählt ist
			currentDialog = 2;
		}else
		
		if((GameLogic.choosenQuest != null || (GameLogic.currentQuest != null && !GameLogic.currentQuestDone.get(index))) && finishedField) {		//wenn eine Quest ausgewählt ist oder bereits eine Läuft	
			currentDialog = 3;
			if(GameLogic.currentQuest == null) QuestManager.approveQuest();		//nur wenn keine Quest läuft
		}else
		
		if((GameLogic.currentQuest.get(index) != null && GameLogic.currentQuestDone.get(index)) && finishedField) {	//wenn eine Quest läuft und diese Abgeschlossen wurde
			currentDialog = 4;
			GameLogic.currentQuest = null;
			GameLogic.currentQuestDone.set(index, false);
			
		}
		
		Logger.logInfo("current Dialog: "+currentDialog +" max Dialog: "+ Dialogs.length);
		Dialog = Dialogs[currentDialog];
		Logger.logInfo("interacted");
		GameLogic.Interact = false;
		GameLogic.counterInteraction =0;
		GameScreen.currentDialog = "log"+Dialog;
		GameScreen.startDialog(sender);
		if(currentDialog == 1) finishedField = true;
	}

}
