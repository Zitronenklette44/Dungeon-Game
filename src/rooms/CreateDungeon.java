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
	private int[][] Features= {{1,6}};
	private static int[] homeVillage = {0,1,2,3,4};
	public boolean homeVillageBuild = true;
	public static int dungeonType = 1;
	private static int[] maxRooms = {10,20,50,100};

	public void createDungeon() {
		DungeonLenght = (int)(Math.random()*(maxRooms[dungeonType]/2))+(maxRooms[dungeonType]/2);
		DungeonRooms= new int[DungeonLenght];
		System.out.println("Dungeon erstelle mit:"+DungeonLenght+" mit folgenden Räumen: ");
		for(int i= 0; i<DungeonLenght;i++) {
			DungeonRooms[i]= -1;
		}
		spreadFeatures();
		finishFeatures();

		for(int i= 0; i<DungeonLenght;i++) {
			System.out.print(DungeonRooms[i]+", ");
		}
	}

	private void spreadFeatures() {
		int indexLastFeature= -100;
		int random;

		for(int i = 0;i<DungeonLenght;i++) {
			if(i+3>=DungeonLenght) {
				break;
			}
			if(indexLastFeature+3>i) {
			}else {
				random = (int) (Math.random()*100);
				if(random>50) {
					DungeonRooms[i]=Features[dungeonType-1][(int) (Math.random()*Features[dungeonType-1].length)]; 
					indexLastFeature = i;
				}
			}
		}

	}
	
	private void finishFeatures() {
		for(int i = 0;i<DungeonLenght;i++) {
			if(i!=-1) {
				if(checkFeature(i)) {
					if(DungeonRooms[i+2]==-1) {
						DungeonRooms[i+1]=(int) (Math.random()*3+i);
					}else {
						DungeonRooms[i+1]=i+3;
					}//TODO: Brocken
				}
				
				
			}
			
			
		}

	}
	
	
	private boolean checkFeature(int Feature) {
		for(int i= 0;i<Features[dungeonType-1].length;i++) {
			if(i==Feature) {
				return true;
			}
			
		}
		return false;

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

	public void drawRoom(Graphics2D g2d) {
		Draw.clearObjects();
		rooms[dungeonType][currentRoom].changeColors();
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.drawString(rooms[dungeonType][currentRoom].name, 200, 300);
		rooms[dungeonType][currentRoom].createObjects();
		rooms[dungeonType][currentRoom].DrawImage(g2d);
	}
}
