package rooms.Home;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.GameLogic;
import gui.Draw;
import rooms.RoomTemplate;

public class Dungeon extends RoomTemplate{

	public Dungeon(String name) {
		super(name);
	}
	
	public void createObjects() {
		GameLogic.createDungeonChooser(200, 700);
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
			//g.drawImage(ImageIO.read(getClass().getResourceAsStream("/resources/DungeonBackground.jpeg")), 0, 0, null, null);
			g.drawImage(ImageIO.read(getClass().getResourceAsStream("/resources/cave.png")), 0, 550, null, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
