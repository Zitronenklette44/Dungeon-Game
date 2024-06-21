package rooms;

import java.awt.Graphics2D;

public class RoomTemplate {
	public String name;
	public String ImagePath;
	protected int RoomVariant;
	
	public RoomTemplate(String name) {
		this.name = name;
	}
	
	public RoomTemplate(String name, String Image) {
		this.name = name;
		this.ImagePath = Image;
	}
	
	public void createObjects(int currentRoom) {}
	
	public void DrawImage(Graphics2D g) {}
	
	public void changeColors() {}
	
	public String getImage(int currentRoom) {return ImagePath;}
	
	public void setSpawns() {}
	
	public void VariantExists(int currentRoom) {}
}
