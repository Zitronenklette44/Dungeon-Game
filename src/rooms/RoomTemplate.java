package rooms;

import java.awt.Graphics2D;
import java.util.ArrayList;

import action.Logger;
import entitys.MobTemplate;
import functionalObjects.FunctionalTemplate;
import game.GameLogic;

public class RoomTemplate implements Cloneable{
	public String name;
	public String ImagePath = "/resources/rooms/backgrounds/Empty.png";
	protected int RoomVariant;
	public boolean EntitysSpawned = false;
	protected MobTemplate[] Entitys;
	protected ArrayList<FunctionalTemplate> functional = new ArrayList<FunctionalTemplate>();
	
	public RoomTemplate(String name) {
		this.name = name;
		createFunctionable();
	}
	
	public RoomTemplate(String name, String Image) {
		this.name = name;
		this.ImagePath = Image;
		createFunctionable();
	}
	
	@Override
	protected RoomTemplate clone() {
		try {
            RoomTemplate roomTemplate =(RoomTemplate) super.clone();
            roomTemplate.createFunctionable();
            return roomTemplate;
        } catch (CloneNotSupportedException e) {
        	Logger.logError("Cloning from Room failed: ", e);
//            throw new AssertionError("Cloning not supported", e);
        }
		return null;
	}
	
	public void createObjects(int currentRoom) {
		for (int i = 0; i < functional.size(); i++) {
			GameLogic.functionalObjects.add(functional.get(i));
		}
	}
	
	public void DrawImage(Graphics2D g) {}
	
	public void changeColors() {}
	
	public String getImage(int currentRoom) {return ImagePath;}
	
	public void setSpawns(int currentRoom) {}
	
	protected void createEntitys() {GameLogic.mobs.clear();}
	
	public void spawnEntitys() {GameLogic.mobs.clear();}
	
	public void VariantExists(int currentRoom) {}
	
	public void resetRoom() {}
	
	public void update() {}
	
	protected void createFunctionable() {functional.clear();}

	public int getRoomVariant() {
		return RoomVariant;
	}

	public MobTemplate[] getEntitys() {
		return Entitys;
	}

	public ArrayList<FunctionalTemplate> getFunctional() {
		return functional;
	}
}
