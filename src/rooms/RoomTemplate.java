package rooms;

import java.awt.Graphics2D;

import action.Logger;
import entitys.MobTemplate;
import game.GameLogic;

public class RoomTemplate implements Cloneable{
	public String name;
	public String ImagePath;
	protected int RoomVariant;
	public boolean EntitysSpawned = false;
	protected MobTemplate[] Entitys;
	public boolean entered = false;
	
	public RoomTemplate(String name) {
		this.name = name;
	}
	
	public RoomTemplate(String name, String Image) {
		this.name = name;
		this.ImagePath = Image;
	}
	
	@Override
	protected RoomTemplate clone() {
		try {
            return (RoomTemplate) super.clone();
        } catch (CloneNotSupportedException e) {
        	Logger.logError("Cloning from Room failed: ", e);
//            throw new AssertionError("Cloning not supported", e);
        }
		return null;
	}
	
	public void createObjects(int currentRoom) {}
	
	public void DrawImage(Graphics2D g) {}
	
	public void changeColors() {}
	
	public String getImage(int currentRoom) {return ImagePath;}
	
	public void setSpawns(int currentRoom) {}
	
	protected void createEntitys() {GameLogic.mobs.clear();}
	
	public void spawnEntitys() {GameLogic.mobs.clear();}
	
	public void VariantExists(int currentRoom) {}
	
	public void resetRoom() {}
}
