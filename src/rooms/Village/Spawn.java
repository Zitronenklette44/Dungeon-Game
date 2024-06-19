package rooms.Village;

import java.awt.Graphics2D;

import game.GameLogic;
import rooms.RoomTemplate;

public class Spawn extends RoomTemplate{
	
	public Spawn(String name) {
		super(name, "/resources/rooms/backgrounds/VillageSpawn.png");
	}
	
	
	@Override
	public void DrawImage(Graphics2D g) {
		super.DrawImage(g);
	}
	
	@Override
	public void createObjects() {
		super.createObjects();
		//Walls
		GameLogic.createHitbox(761,100, 0, 0);
		GameLogic.createHitbox(250,100, 100, 520);	//hohe breite posX posY
		GameLogic.createHitbox(250,600, 200, 560);
		GameLogic.createHitbox(400,40, 815, 350);
		GameLogic.createHitbox(150,100, 855, 350);
		GameLogic.createHitbox(150,100, 855, 125);
		GameLogic.createHitbox(275,40, 820, 0);
		GameLogic.createHitbox(10,250, 950, 130);
		GameLogic.createHitbox(10,350, 850, 600);
		GameLogic.createHitbox(50,230, 470, 10);
		GameLogic.createHitbox(10,485, 340, 30);
		GameLogic.createHitbox(180,80,100,0);
		GameLogic.createHitbox(50,180,180,0);
		GameLogic.createHitbox(65,63,300,50);
	}
	
	@Override
	public void setSpawns() {
		GameLogic.player.breite = 25;
		GameLogic.player.hoehe = 25;
		GameLogic.resetPos[0] =250;
		GameLogic.resetPos[1] = 100;
		GameLogic.resetPos1[0]= 1150;
		GameLogic.resetPos1[1] = 350;
		super.setSpawns();
	}

}
