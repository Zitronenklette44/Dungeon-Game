package rooms;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import action.Logger;
import game.GameLogic;
import gui.GameScreen;
import rooms.Castle.*;
import rooms.Dungeon.*;
import rooms.Forest.*;
import rooms.Home.*;
import rooms.Village.*;
import rooms.specialRoom.Guilde;
import rooms.specialRoom.StartRoom1;
import rooms.specialRoom.StartRoom2;

public class DungeonCore {

	public static ArrayList<Integer> DungeonRooms;
	public int DungeonLength;
	public static int currentRoom = 3;
	public static RoomTemplate[][] rooms = {
			{new Dungeon("Dungeon"), new Entrace("Eingang"), new Center("Mitte"), new Exit("Ausgang"), new Field("Field")},
			{new Normal("Path"), new HouseEntrance("Haus eingang"), new HouseMiddel("Haus Mitte 1"), new HouseMiddel1("Haus Mitte 2"), new HouseMiddel2("Haus Mitte 3"), new HouseExit("Haus Exit"),
				new MarketEntrance("Markt Eingang"), new MarketMiddel("Markt Mitte 1"), new MarketMiddel1("Markt Mitte 2"), new MarketMiddel2("Markt Mitte 3"), new MarketExit("Markt Exit"),
				new Spawn("Spawn"), new Goal("Goal")},
			{new ForestNormal("Path") ,new ForestCaveEntrance("Cave Eingang"),new ForestCaveMiddel("Cave Mitte 1"), new ForestCaveMiddel2("Cave Mittel 2"), new ForestCaveMiddel3("Cave Mitte 3"), new ForestCaveExit("Cave Exit"),
					new ForestLichtungEntrance("Lichtung Eingang"),new ForestLichtungMiddel("Lichtung Mitte 1"),new ForestLichtungMiddel2("Lichtung Mitte 2"),new ForestLichtungMiddel3("Lichtung Mitte 3"),new ForestLichtungExit("Lichtung Ausgang"),
					new ForestSeeEntrance("See eingang"), new ForestSeeMiddel("See Mitte 1"), new ForestSeeMiddel2("See Mitte 2"), new ForestSeeMiddel3("See Mitte 3"), new ForestSeeExit("See Exit"),
					new ForestEntrance("Spawn"), new ForestGoal("Goal")},
			{new CastleNormal("Path"),new CastleEsszimmerEntrance("Esszimmer Eingang"),new CastleEsszimmerMiddel("Esszimmer Mitte 1"),new CastleEsszimmerMiddel2("Esszimmer Mitte 2"),new CastleEsszimmerMiddel3("Esszimmer Mitte 3"),new CastleEsszimmerExit("Esszimmer Ausgang"),
						new CastleKuecheEntrance("Küche Eingang"),new CastleKuecheMiddel("Küche Mitte 1"),new CastleKuecheMiddel2("Küche Mitte 2"),new CastleKuecheMiddel3("Küche Mitte 3"),new CastleKuecheExit("Küche Ausgang"),
						new CastlePrivatRoomsEntrance("Privat Gemäche Eingang"),new CastlePrivatRoomsMiddel("Privat Gemäche Mitte 1"),new CastlePrivatRoomsMiddel2("Privat Gemäche Mitte 2"),new CastlePrivatRoomsMiddel3("Privat Gemäche Mitte 3"),new CastlePrivatRoomsExit("Privat Gemäche Ausgang"),
						new CastleKerkerEntrance("Kerker Eingang"),new CastleKerkerMiddel("Kerker Mitte 1"),new CastleKerkerMiddel2("Kerker Mitte 2"),new CastleKerkerMiddel3("Kerker Mitte 3"),new CastleKerkerExit("Kerker Ausgang"),
						new CastleEntrance("Spawn"),new CastleExit("Goal")},
			{new DungeonNormal("Path"), new DungeonSpawnerEntrance("Spawner Eingang"), new DungeonSpawnerMiddel("Spawner Mitte 1"), new DungeonSpawnerMiddel2("Spawner Mitte 2"), new DungeonSpawnerMiddel3("Spawner Mitte 3"), new DungeonSpawnerExit("Spawner Ausgang"),
							new DungeonWaffenkammerEntrance("Waffenkammer Eingang"), new DungeonWaffenkammerMiddel("Waffenkammer Mitte 1"),new DungeonWaffenkammerMiddel2("Waffenkammer Mitte 2"),new DungeonWaffenkammerMiddel3("Waffenkammer Mitte 3"),new DungeonWaffenkammerExit("Waffenkammer Ausgang"),
							new DungeonCrystalCaveEntrance("Crystal Cave Eingang"),new DungeonCrystalCaveMiddel("Crystal Cave Mitte 1"),new DungeonCrystalCaveMiddel2("Crystal Cave Mitte 2"),new DungeonCrystalCaveMiddel3("Crystal Cave Mitte 3"),new DungeonCrystalCaveExit("Crystal Cave Ausgang"),
							new DungeonLibraryEntrance("Library Eingang"),new DungeonLibraryMiddel("Library Mitte 1"),new DungeonLibraryMiddel2("Library Mitte 2"),new DungeonLibraryMiddel3("Library Mitte 3"),new DungeonLibraryExit("Library Ausgang"),
							new DungeonMineEntrance("Mine Eingang"),new DungeonMineMiddel("Mine Mitte 1"),new DungeonMineMiddel2("Mine Mitte 2"),new DungeonMineMiddel3("Mine mitte 3"),new DungeonMineExit("Mine Ausgang"),
							new DungeonLavaCaveEntrance("Lava Cave Eingang"),new DungeonLavaCaveMiddel("Lava Cave Mitte 1"),new DungeonLavaCaveMiddel2("Lava Cave Mitte 2"),new DungeonLavaCaveMiddel3("Lava Cave Mitte 3"),new DungeonLavaCaveExit("Lava Cave Ausgang"),
							new DungeonEntrance("Spawn"), new DungeonExit("Goal")},
			{new Guilde("Gilde"), new StartRoom1("startRaum1"), new StartRoom2("startRaum2")}
	};
	public static ArrayList<RoomTemplate> thisRooms = new ArrayList<RoomTemplate>();
	private int[][] Features = {{1, 6},	//Haus Markt
								{1,6,11},//Cave Lichtung See
								{1,6,11,16},//Esszimmer küche PrivatRäume Kerker 
								{1,6,11,16,21,26}}; //Spawner Waffenkammer KristalCave Bibiliothek Mine LavaCave
	private static int[] specialRoom = {0, 1, 2};
	private static int[] homeVillage = {0, 1, 2, 3, 4};
	public static boolean homeVillageBuild = true;
	public static boolean specialRoomBuild = false;
	public static int dungeonType = 0;
	private static int[] maxRooms = {10, 20, 50, 100};
	
	public static void init() {
		Logger.logInfo("creating Home Village...");
		if (homeVillageBuild) {
			thisRooms.add(rooms[0][0]);
			thisRooms.add(rooms[0][1]);
			thisRooms.add(rooms[0][2]);
			thisRooms.add(rooms[0][3]);
			thisRooms.add(rooms[0][4]);
			Logger.logInfo("Home Village");
		}else {
			thisRooms.add(rooms[5][0]);
			thisRooms.add(rooms[5][1]);
			thisRooms.add(rooms[5][2]);
			Logger.logInfo("Special rooms");
		}
		Logger.logInfo("created Home Village");
	}

	public void createDungeon() {
		if (!homeVillageBuild && !specialRoomBuild) {
			Logger.logInfo("creating new Dungeon");
			DungeonLength = (int) (Math.random() * (maxRooms[dungeonType-1] / 2)) + (maxRooms[dungeonType-1] / 2);
			DungeonRooms = new ArrayList<Integer>();
			for (int i = 0; i < DungeonLength; i++) {
				DungeonRooms.add(-1);
			}
			spreadFeatures();
			finishFeatures();

			DungeonRooms.addFirst(rooms[dungeonType].length-2);
			DungeonRooms.addLast(rooms[dungeonType].length-1);

			DungeonLength+=2;
			thisRooms.clear();
			for (int i = 0; i < DungeonLength; i++) {
				rooms[dungeonType][DungeonRooms.get(i)].resetRoom();
				thisRooms.add((RoomTemplate) rooms[dungeonType][DungeonRooms.get(i)].clone()); 
			}
			Logger.logInfo("new Dungeon created");
		}
		
	}

	private void spreadFeatures() {
		int indexLastFeature = -100;
		int random;

		for (int i = 0; i < DungeonLength; i++) {
			if (i + 3 >= DungeonLength) {
				break;
			}
			if (indexLastFeature + 3 > i) {
			} else {
				random = (int) (Math.random() * 100);
				if (random > 50) {
					DungeonRooms.set(i, Features[dungeonType - 1][(int) (Math.random() * Features[dungeonType - 1].length)]);
					indexLastFeature = i;
				}
			}
		}

	}

	private void finishFeatures() {
		for (int i = 0; i < DungeonLength; i++) {
			if (DungeonRooms.get(i) != -1 && checkFeature(DungeonRooms.get(i))) {
				int maxFeatureLenght = 0;
				for (int j = i + 1; j < DungeonLength; j++) {
					if (DungeonRooms.get(j) == -1) {
						maxFeatureLenght++;
					} else {
						break;
					}
				}

				int FeatureLength = (int) (Math.random() * (maxFeatureLenght) + 1);
				int FeatureEnd = DungeonRooms.get(i) + 4;
				DungeonRooms.set(FeatureLength + i, FeatureEnd);

				int[] possibleRooms = {DungeonRooms.get(i) + 1, DungeonRooms.get(i) + 2, DungeonRooms.get(i) + 3};
				for (int j = i + 1; j < FeatureLength + i; j++) {
					DungeonRooms.set(j, possibleRooms[(int) (Math.random() * 3)]);
				}

			}
		}
		for (int i = 0; i < DungeonLength; i++) {
			if (DungeonRooms.get(i) == -1) {
				DungeonRooms.set(i, 0);
			}
		}
	}

	private boolean checkFeature(int Feature) {
		for (int i = 0; i < Features[dungeonType - 1].length; i++) {
			if (Features[dungeonType - 1][i] == Feature) {
				return true;
			}

		}
		return false;

	}

	public int getRoom() {
		if (homeVillageBuild) {
			return homeVillage[currentRoom];
		}
		if(specialRoomBuild) {
			return specialRoom[currentRoom];
		}
		return DungeonRooms.get(currentRoom);
	}

	public int getLastRoom() {
		if (homeVillageBuild) {
			return homeVillage[currentRoom - 1];
		}
		if (specialRoomBuild) {
			return specialRoom[currentRoom-1];
		}
		return DungeonRooms.get(currentRoom - 1);
	}

	public int getNextRoom() {
		if (homeVillageBuild) {
			return homeVillage[currentRoom + 1];
		}
		if (specialRoomBuild) {
			return specialRoom[currentRoom+1];
		}
		return DungeonRooms.get(currentRoom + 1);
	}

	public int getDungeonLenght() {
		if (homeVillageBuild) {
			return homeVillage.length;
		}
		if (specialRoomBuild) {
			return specialRoom.length;
		}
		return DungeonLength;
	}

	public static String getImage(int addToRoom) {
	    if(dungeonType != 0 && dungeonType != 5) {
	        try {
	        	thisRooms.get(currentRoom).VariantExists(currentRoom);
	        	thisRooms.get(currentRoom+addToRoom).getImage(currentRoom);
	            if(thisRooms.get(currentRoom+addToRoom).ImagePath == null) {
	            	return "/resources/rooms/backgrounds/Empty.png";
	            }
	            return thisRooms.get(currentRoom+addToRoom).ImagePath;
	        } catch (NullPointerException e) {
	        	return "/resources/rooms/backgrounds/Empty.png";
	        }
	    } else  if(dungeonType == 5) {
	    	try {
	            if(thisRooms.get(currentRoom+addToRoom).ImagePath == null) {
	            }
	            return thisRooms.get(currentRoom+addToRoom).ImagePath;
	        } catch (NullPointerException  e ) {
	        	return "/resources/rooms/backgrounds/Empty.png";
	        }
	        catch (IndexOutOfBoundsException e) {
	        	Logger.logWarning("Rooms not fully initialized: Index error");
			}
	    }else{
	        try {
	            if(thisRooms.get(currentRoom+addToRoom).ImagePath == null) {
	            	//Logger.logWarning("Image missing");
	            }
	            return thisRooms.get(currentRoom+addToRoom).ImagePath;
	        } catch (NullPointerException  e ) {
	        	return "/resources/rooms/backgrounds/Empty.png";
	        }
	        catch (IndexOutOfBoundsException e) {
	        	Logger.logWarning("Rooms not fully initialized: Index error");
			}
	    }
	    return null;
	}

	public void drawRoom(Graphics2D g2d) {
		if (!homeVillageBuild && !specialRoomBuild) {
			thisRooms.get(currentRoom).VariantExists(currentRoom);
			GameLogic.floor = 725;
			thisRooms.get(currentRoom).changeColors(); 
			g2d.setFont(new Font("Arial", Font.BOLD, 20));
			if(GameLogic.debug) {
				g2d.drawString(thisRooms.get(currentRoom).name, 200, 300);
			}
//			Draw.clearObjects();
//			thisRooms.get(currentRoom).createObjects(currentRoom);
			thisRooms.get(currentRoom).DrawImage(g2d);
		} else if (specialRoomBuild){
			GameLogic.floor = 725;
			try {
				thisRooms.get(currentRoom).changeColors();
			} catch (Exception e) {
				Logger.logWarning("Rooms not fully initialized");
			}
			g2d.setFont(new Font("Arial", Font.BOLD, 20));
			if(GameLogic.debug) {
				g2d.drawString(thisRooms.get(currentRoom).name, 200, 300);
			}
//			Draw.clearObjects();
//			thisRooms.get(currentRoom).createObjects(currentRoom);
			thisRooms.get(currentRoom).DrawImage(g2d);
		}else{
			if(!(thisRooms.get(currentRoom) instanceof Field)) { GameLogic.floor = 700;}else {GameLogic.floor = 725;}
			thisRooms.get(currentRoom).changeColors();
			g2d.setFont(new Font("Arial", Font.BOLD, 20));
			if(GameLogic.debug) {
				g2d.drawString(thisRooms.get(currentRoom).name, 200, 300);
			}
//			Draw.clearObjects();
//			thisRooms.get(currentRoom).createObjects(currentRoom);
			thisRooms.get(currentRoom).DrawImage(g2d);
		}
	}

	public void setSpawns() {
		if(dungeonType == 0) {
			thisRooms.get(currentRoom).spawnEntitys();
			thisRooms.get(currentRoom).setSpawns(currentRoom);
			return;
		}
		thisRooms.get(currentRoom).setSpawns(currentRoom);
		thisRooms.get(currentRoom).spawnEntitys();
	}
	
	
	public static void changeRoom(int newRoom) {
			GameLogic.directionRoom = 0;		//setze richtung auf links nach rechts
			DungeonCore.currentRoom = newRoom;	//aktuellen Raum um 1 nach hinten verscheieben
			GameLogic.resetLevel();		//Level reseten
			GameScreen.updateRoomNr(DungeonCore.currentRoom+1);		//Raum Nummer updaten
		try {
			GameScreen.changeBackground(DungeonCore.getImage(0));
		} catch (Exception e) {}
	}
}
