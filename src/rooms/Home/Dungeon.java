package rooms.Home;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameLogic;
import gameObject.CreateObjects;
import rendering.Draw;
import rooms.RoomTemplate;

public class Dungeon extends RoomTemplate{

	public Dungeon(String name) {
		super(name);
	}
	

	@Override
	public void createObjects(int CurrentRoom) {
		super.createObjects(CurrentRoom);
		CreateObjects.createDungeonChooser(200, 700);
		CreateObjects.createDialogInteraction(500, 700, "read", 1);
	}
	
	@Override
	public void changeColors() {
		Draw.backgroundColor= Color.cyan;
		Draw.playerColor=Color.gray;
		
	}
	
	@Override
	public void DrawImage(Graphics2D g) {
		super.DrawImage(g);
		try {
			g.drawImage(ImageIO.read(getClass().getResourceAsStream("/resources/cave.png")), 0, 550, null, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setSpawns(int currentRoom) {
		super.setSpawns(currentRoom);
		GameLogic.resetPos[0] = 50;
		GameLogic.resetPos[1] = 700;
		GameLogic.resetPos1[0] = 1100;
		GameLogic.resetPos1[1] = 700;
		
		
		
	}

}
