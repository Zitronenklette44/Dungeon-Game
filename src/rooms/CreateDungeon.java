package rooms;

import java.awt.Graphics2D;

import rooms.Home.*;

public class CreateDungeon {
	public int[] DungeonRooms;
	public int DungeonLenght;
	public int currentRoom=0;
	private static RoomTemplate[][] rooms = {{new Dungeon(), new Entrace(), new Center(), new Exit(), new Field()}};
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
	
	public void createHomeVillage(){
		
	}
	
	public void drawRoom(Graphics2D g2d) {
			g2d.drawString(rooms[dungeonType][currentRoom].name, 20f, 30f);
		

	}
}
