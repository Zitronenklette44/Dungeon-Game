package rooms;

import java.awt.Font;
import java.awt.Graphics2D;

import gui.Draw;
import rooms.Home.*;

public class CreateDungeon {
	public int[] DungeonRooms;
	public int DungeonLenght;
	public int currentRoom=0;
	private static RoomTemplate[][] rooms = {{new Dungeon("Dungeon"), new Entrace("Eingang"), new Center("Mitte"), new Exit("Ausgang"), new Field("Field")}};
	private static int[] homeVillage = {0,1,2,3,4};
	public boolean homeVillageBuild = true;
	public static int dungeonType = 0;
	
	public void createDungeon() {
		DungeonLenght = (int)(Math.random()*5)+5;
		DungeonRooms= new int[DungeonLenght];
		System.out.println("Dungeon erstelle mit:"+DungeonLenght+" mit folgenden Räumen: ");
		for(int i= 0; i<DungeonLenght;i++) {
			DungeonRooms[i]= (int)(Math.random()*3); 
			System.out.print(DungeonRooms[i]+", ");
		}
		
		
	}
	
	public int getRoom() {
		if(homeVillageBuild) {
			return homeVillage[currentRoom];
		}
		return DungeonRooms[currentRoom];
	}
	
	public int getLastRoom() {
		if(homeVillageBuild) {
			return homeVillage[currentRoom-1];
		}
		return DungeonRooms[currentRoom-1];
	}
	
	public int getNextRoom() {
		if(homeVillageBuild) {
			return homeVillage[currentRoom+1];
		}
		return DungeonRooms[currentRoom+1];
	}
	
	public int getDungeonLenght() {
		if(homeVillageBuild) {
			return homeVillage.length;
		}
		return DungeonLenght;
	}
	
	public void createHomeVillage(){
		
	}
	
	public void drawRoom(Graphics2D g2d) {
		Draw.clearObjects();
		rooms[dungeonType][currentRoom].changeColors();
	    g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.drawString(rooms[dungeonType][currentRoom].name, 200, 300);
		rooms[dungeonType][currentRoom].createObjects();
		rooms[dungeonType][currentRoom].DrawImage(g2d);
	}
}
