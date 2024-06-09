package rooms;

public class CreateDungeon {
	public int[] DungeonRooms;
	public int DungeonLenght;
	public int currentRoom=0;
	
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
		return DungeonRooms[currentRoom];
	}
	
	public int getLastRoom() {
		return DungeonRooms[currentRoom-1];
	}
	
	public int getNextRoom() {
		return DungeonRooms[currentRoom+1];
	}
}
