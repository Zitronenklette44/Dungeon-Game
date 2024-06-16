package rooms;

import java.awt.Graphics2D;

public class RoomTemplate {
	public String name;
	public String ImagePath;
	
	public RoomTemplate(String name) {
		this.name = name;
	}
	
	public RoomTemplate(String name, String Image) {
		this.name = name;
		this.ImagePath = Image;
	}
	
	public void createObjects() {}
	
	public void DrawImage(Graphics2D g) {}
	
	public void changeColors() {}
	
	public String getImage() {return ImagePath;}
	
	public void setSpawns() {}
}
