package rooms.Village;

import java.util.ArrayList;

import game.CreateObjects;
import game.GameLogic;
import rooms.RoomTemplate;

public class Normal extends RoomTemplate {
	private String[] image = { "/resources/rooms/backgrounds/VillagePath1.png",
	"/resources/rooms/backgrounds/VillagePath2.png" };
	private ArrayList<Integer> dungeonPaths = new ArrayList<Integer>();

	public Normal(String name) {
		super(name);
	}

	private void createNewNormal(int currentRoom) {
		dungeonPaths.add(currentRoom);
		int variante = (int) (Math.random() * image.length);
		dungeonPaths.add(variante);
		setImagePath(variante);
	}

	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		getImage(currentRoom);

		int variantIndex = getVariantIndex(currentRoom);
		if (variantIndex == -1) {
			System.out.println("No variant found for currentRoom: " + currentRoom);
			return;
		}

		int variant = dungeonPaths.get(variantIndex + 1);
		switch (variant) {
		case 0: {
			CreateObjects.createHitbox(110, 105, 590, 255);
			CreateObjects.createHitbox(160, 200, 365, 0);
			CreateObjects.createHitbox(80, 110, 190, 0);
			CreateObjects.createHitbox(65, 220, 500, 0);
			CreateObjects.createHitbox(220, 185, 60, 123);
			CreateObjects.createHitbox(150, 105, 0, 370);
			CreateObjects.createHitbox(220, 140, 0, 530);
			CreateObjects.createHitbox(220, 135, 140, 590);
			CreateObjects.createHitbox(38, 100, 455, 350);
			CreateObjects.createHitbox(130, 130, 455, 400);
			CreateObjects.createHitbox(150, 190, 545, 625);
			CreateObjects.createHitbox(90, 105, 720, 0);
			CreateObjects.createHitbox(145, 200, 1005, 0);
			CreateObjects.createHitbox(320, 111, 888, 365);
			CreateObjects.createHitbox(175, 200, 1030, 290);
			CreateObjects.createHitbox(120, 100, 1100, 685);
			CreateObjects.createHitbox(230, 40, 1000, 450);
			CreateObjects.createHitbox(250, 48, 840, 430); // hoehe breite posX posY
			break;
		}
		case 1: {
			CreateObjects.createHitbox(50, 1200, 0, 220);
			CreateObjects.createHitbox(140, 105, 50, 250);
			CreateObjects.createHitbox(110, 75, 945, 290);
			CreateObjects.createHitbox(75,110,488,272);
			CreateObjects.createHitbox(200,250,150,585);
			CreateObjects.createHitbox(130,130,450,580);
			CreateObjects.createHitbox(190,200,1080,485);
		}
		break;
		case 2:
			System.out.println("Case 2 logic here");
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + variant);
		}
	}

	@Override
	public void setSpawns(int currentRoom) {
		super.setSpawns(currentRoom);
		VariantExists(currentRoom);
		getImage(currentRoom);

		int variantIndex = getVariantIndex(currentRoom);
		if (variantIndex == -1) {
			System.out.println("No variant found for currentRoom: " + currentRoom);
			return;
		}

		int variant = dungeonPaths.get(variantIndex + 1);
		switch (variant) {
		case 0: {
			GameLogic.player.breite = 25;
			GameLogic.player.hoehe = 25;
			GameLogic.floor = 750;
			GameLogic.resetPos[0] = 130;
			GameLogic.resetPos[1] = 370;
			GameLogic.resetPos1[0] = 1130;
			GameLogic.resetPos1[1] = 200;
			break;
		}
		case 1: {
			GameLogic.player.breite = 25;
			GameLogic.player.hoehe = 25;
			GameLogic.floor = 750;
			GameLogic.resetPos[0] = 30;
			GameLogic.resetPos[1] = 500;
			GameLogic.resetPos1[0] = 1130;
			GameLogic.resetPos1[1] = 425;
		}
		break;
		case 2:
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + variant);
		}
	}

	@Override
	public void VariantExists(int currentRoom) {
		super.VariantExists(currentRoom);

		if (dungeonPaths.isEmpty()) {
			createNewNormal(currentRoom);
			return;
		}

		boolean roomExists = false;
		for (int i = 0; i < dungeonPaths.size(); i += 2) {
			if (dungeonPaths.get(i) == currentRoom) {
				roomExists = true;
				break;
			}
		}

		if (!roomExists) {
			createNewNormal(currentRoom);
		}
	}

	@Override
	public String getImage(int currentRoom) {
		int variantIndex = getVariantIndex(currentRoom);
		if (variantIndex == -1) {
			return "";
		}
		setImagePath(dungeonPaths.get(variantIndex + 1));
		return ImagePath;
	}

	private void setImagePath(int variant) {
		if (variant >= 0 && variant < image.length) {
			ImagePath = image[variant];
		} else {
			throw new IllegalArgumentException("Invalid variant: " + variant);
		}
	}

	private int getVariantIndex(int currentRoom) {
		for (int i = 0; i < dungeonPaths.size(); i +=2) {
			if (dungeonPaths.get(i) == currentRoom) {
				return i;
			}
		}
		return -1;
	}
}
