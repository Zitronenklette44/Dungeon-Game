package rooms.Village;

import java.awt.Color;

import entitys.MobTemplate;
import game.GameLogic;
import gameObject.CreateObjects;
import rooms.RoomTemplate;

public class Spawn extends RoomTemplate{
	
	public Spawn(String name) {
		super(name, "/resources/rooms/backgrounds/VillageSpawn.png");
		createEntitys();
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		//Hitboxen
		CreateObjects.createHitbox(761,100, 0, 0);
		CreateObjects.createHitbox(250,100, 100, 520);	//hohe breite posX posY
		CreateObjects.createHitbox(250,600, 200, 560);
		CreateObjects.createHitbox(400,40, 815, 350);
		CreateObjects.createHitbox(150,100, 855, 350);
		CreateObjects.createHitbox(150,100, 855, 125);
		CreateObjects.createHitbox(275,40, 820, 0);
		CreateObjects.createHitbox(10,250, 950, 130);
		CreateObjects.createHitbox(10,350, 850, 600);
		CreateObjects.createHitbox(50,230, 470, 10);
		CreateObjects.createHitbox(10,485, 340, 30);
		CreateObjects.createHitbox(180,80,100,0);
		CreateObjects.createHitbox(50,180,180,0);
		CreateObjects.createHitbox(65,63,300,50);
	}
	
	@Override
	public void setSpawns(int currentRoom) {
		GameLogic.player.breite = 25;
		GameLogic.player.hoehe = 25;
		GameLogic.floor = 725;
		GameLogic.resetPos[0] =250;
		GameLogic.resetPos[1] = 100;
		GameLogic.resetPos1[0]= 1150;
		GameLogic.resetPos1[1] = 350;
		super.setSpawns(currentRoom);
	}
	
	@Override
	protected void createEntitys() {
		Entitys = new MobTemplate[3];	//maximale anzahl an gegnern die gespawnt werden
		Entitys[0] = CreateObjects.createTestMob(25, 25, 0.5F, 1035, 300, 1, 1);
		Entitys[0].typeColor = Color.blue;
		Entitys[0].maxHitCooldown = 200;
		
		Entitys[1] = CreateObjects.createTestMob(25, 25, 0.7F, 1035, 400, 1, 9);
		Entitys[1].typeColor = Color.white;
		Entitys[1].maxHitCooldown = 200;
		
		Entitys[2] = CreateObjects.createTestMob(30, 30, 0.3F, 1035, 500, 1, 90);
		Entitys[2].typeColor = Color.white;
		Entitys[2].maxHitCooldown = 200;
		
		super.createEntitys();
		//killAllEntitys();
	}
	
	@Override
	public void spawnEntitys() {
		super.spawnEntitys();	//Liste wird geleert
		for(int i=0; i<Entitys.length;i++) {
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
	
	@Override
	public void resetRoom() {
		super.resetRoom();
		Entitys = null;
		createEntitys();
		
	}
	
	private void killAllEntitys() {
		for(int i = 0; i<Entitys.length ; i++) {
			Entitys[i].defeated = true;
		}

	}

}
