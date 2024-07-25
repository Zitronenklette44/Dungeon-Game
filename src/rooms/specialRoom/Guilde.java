package rooms.specialRoom;

import java.awt.Color;

import action.Logger;
import entitys.MobTemplate;
import game.CreateObjects;
import game.GameLogic;
import interactions.GildSwitchInteraction;
import rooms.RoomTemplate;
import translation.Translation;

public class Guilde extends RoomTemplate{

	public Guilde(String name) {
		super(name, "/resources/rooms/backgrounds/Gilde.png");
		createEntitys();
	}
	
	@Override
	public void setSpawns(int currentRoom) {
		super.setSpawns(currentRoom);
		GameLogic.resetPos[0] = 550;
		GameLogic.resetPos[1] = 725;
		GameLogic.resetPos1[0] = 550;
		GameLogic.resetPos1[1] = 725;
		GameLogic.vertikalAxis = true;
		GameLogic.player.hoehe = 25;
		GameLogic.player.breite = 25;
		GildSwitchInteraction.isInGuild = true;
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		CreateObjects.createGildSwitchInteraction(550, 750);
//		GameLogic.functionalObjects.add(CreateObjects.createPernamentChest(600, 400, true));
		CreateObjects.createDialogInteraction(581, 103, Translation.get("interaction.testDialog") , 2, Translation.get("dialogs.gildenSender"));
		
		
		CreateObjects.createHitbox(57,1160, 0, 0);	//hohe breite posX posY
		CreateObjects.createHitbox(750,26, 1161, 0);
		CreateObjects.createHitbox(186,13, 284, 0);
		CreateObjects.createHitbox(5,284, 0, 85);
		CreateObjects.createHitbox(750,22, 0, 0);
		CreateObjects.createHitbox(97,153, 70, 90);
		CreateObjects.createHitbox(41,522, 0, 708);
		CreateObjects.createHitbox(41,600, 612, 708);
		CreateObjects.createHitbox(82,63, 562, 96);
	}
	
	protected void createEntitys() {
		Entitys = new MobTemplate[2];

		Entitys[0] = CreateObjects.createTestMob(25, 25, 0.0F, 189, 79, 0, 1);
		Entitys[0].typeColor = Color.blue;
		Entitys[0].invulnerable = true;
		
		Entitys[1] = CreateObjects.createTestMob(25, 25, 0.0F, 581, 103, 0, 1);
		Entitys[1].typeColor = Color.gray;
		Entitys[1].invulnerable = true;
		super.createEntitys();
	}
	public void spawnEntitys() {
		for(int i=0; i<2;i++) {
			if(Entitys[i] == null) {
				break;
			}
			if(!Entitys[i].defeated) {
				GameLogic.mobs.add(Entitys[i]);		//der Liste für bewegungen hinzufügen
				Entitys[i].posX = Entitys[i].SpawnX;	//Position zurücksetzen
				Entitys[i].posY = Entitys[i].SpawnY;
			}
		}
			}
			
}
