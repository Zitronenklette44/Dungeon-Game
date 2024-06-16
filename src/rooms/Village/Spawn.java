package rooms.Village;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

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
