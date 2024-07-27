package rooms.Village;

import entitys.MobTemplate;
import game.CreateObjects;
import game.GameLogic;
import loot.LootTabels;
import rendering.Resources;
import rooms.RoomTemplate;

public class Spawn extends RoomTemplate{
	
	public Spawn(String name) {
		super(name, Resources.bgVillageSpawnRoom);
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
		GameLogic.player.breite = 30;
		GameLogic.player.hoehe = 30;
		GameLogic.resetPos[0] =250;
		GameLogic.resetPos[1] = 100;
		GameLogic.resetPos1[0]= 1150;
		GameLogic.resetPos1[1] = 350;
		super.setSpawns(currentRoom);
	}
	
	@Override
	protected void createEntitys() {		
		Entitys = new MobTemplate[1];	//maximale anzahl an gegnern die gespawnt werden
		getEntitys()[0] = CreateObjects.createSwordmen(25, 25, 0.5F, 1035, 300, 1, 1, LootTabels.createVillagerLoot(5));
		getEntitys()[0].maxHitCooldown = 200;
		
		super.createEntitys();
		//killAllEntitys();
	}
	
	@Override
	protected void createFunctionable() {
		super.createFunctionable();
		getFunctional().add(CreateObjects.createBushPoint(500, 400, true, 50));
	}
	
	@Override
	public void spawnEntitys() {
		super.spawnEntitys();	//Liste wird geleert
		for(int i=0; i<getEntitys().length;i++) {
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
	
	@Override
	public void resetRoom() {
		super.resetRoom();
		Entitys = null;
		createEntitys();
		
	}
	
	@SuppressWarnings("unused")
	private void killAllEntitys() {
		for(int i = 0; i<getEntitys().length ; i++) {
			getEntitys()[i].defeated = true;
		}

	}

}
