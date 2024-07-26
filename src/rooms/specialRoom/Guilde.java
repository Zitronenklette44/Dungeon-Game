package rooms.specialRoom;

import java.awt.Color;
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
		GameLogic.player.hoehe = 30;
		GameLogic.player.breite = 30;
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
		CreateObjects.createHitbox(59,135, 326, 57);
	}
	
	protected void createEntitys() {
		Entitys = new MobTemplate[2];

		getEntitys()[0] = CreateObjects.createTestMob(25, 25, 0.0F, 189, 79, 0, 1);
		getEntitys()[0].typeColor = Color.blue;
		getEntitys()[0].invulnerable = true;
		
		getEntitys()[1] = CreateObjects.createTestMob(25, 25, 0.0F, 581, 103, 0, 1);
		getEntitys()[1].typeColor = Color.gray;
		getEntitys()[1].invulnerable = true;
		super.createEntitys();
	}
	public void spawnEntitys() {
		for(int i=0; i<2;i++) {
			if(getEntitys()[i] == null) {
				break;
			}
			if(!getEntitys()[i].defeated) {
				GameLogic.mobs.add(getEntitys()[i]);		//der Liste für bewegungen hinzufügen
				getEntitys()[i].posX = getEntitys()[i].SpawnX;	//Position zurücksetzen
				getEntitys()[i].posY = getEntitys()[i].SpawnY;
			}
		}
			}
			
}
