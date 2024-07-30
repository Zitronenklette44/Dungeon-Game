package action;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import game.GameLogic;
import gameMusik.MusicPlayer;
import gui.GameScreen;
import gui.LoadingGUI;
import inventory.InventoryManager;
import rendering.Draw;
import rooms.DungeonCore;
import translation.Language;
import translation.Translation;

public class SaveLoad {
	private static final String CONFIG_FILE = "src/saveFiles/config.txt";
	private static boolean startedGame;

	
	public static void loadConfig() {	//laden der Konfigurationen aus File in Variabeln
		Logger.logInfo("Loading Configurations...");
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] parts = line.split("=");
                if (parts.length != 2) {
                    continue; // Skip invalid lines
                }
                String key = parts[0].trim();
                String value = parts[1].trim();
                switch (key) {
                    case "musicEnabled":
                        GameLogic.musicEnabled = Boolean.parseBoolean(value);
                        break;
                    case "musicVolume":
                        MusicPlayer.musicVolume = Float.parseFloat(value);
                        break;
                    case "dungeonKey":
                        GameLogic.dungeonKey = Integer.parseInt(value);
                        break;
                    case "debug":
                        GameLogic.debug = Boolean.parseBoolean(value);
                        break;
                    case "playerSpeed":
                        GameLogic.playerSpeed = Float.parseFloat(value);
                        break;
                    case "jumpHight":
                        GameLogic.jumpHight = Integer.parseInt(value);
                        break;
                    case "spells":
                        GameLogic.unlockedSpells = parseIntArray(value);
                        break;
                    case "frameLocation":
                        GameScreen.location = parsePoint(value);
                        LoadingGUI.location = parsePoint(value);
                        break;
                    case "activLanguage":
                        Translation.activLanguage = Language.valueOf(value);
                        break;
                        
                    case "startedGame":
                        checkGameStarted(value);;
                        break;
                    case "inventorySlots":
                    	InventoryManager.maxInventorySlots = Integer.parseInt(value);
                        break;
                    case "inventory":
                    	InventoryManager.loadSaveString(value);
                    	break;
                    default:
                    	Logger.logError("Invalid Configuration: "+ line);
                        break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            Logger.logError("Configurations couldn't be loaded", e);
        }
    }
	
	public static void saveConfig() {	//speichern der Konfigurationen in File
		Logger.logInfo("saving Configurations");
        String tempFile = CONFIG_FILE + ".tmp"; // Temporäre Datei
        Point location = GameScreen.location;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Schreibe die aktualisierten Konfigurationswerte
            writer.write("// If Music activ\n");
            writer.write("// Default true, -30\n");
            writer.write("musicEnabled = " + GameLogic.musicEnabled + "\n");
            writer.write("musicVolume = "+MusicPlayer.musicVolume+"\n\n");
            writer.write("// Number of owned dungeon keys\n");
            writer.write("// Default 1\n");
            writer.write("dungeonKey = " + GameLogic.dungeonKey + "\n\n");
            writer.write("// Debug Modus\n");
            writer.write("// Default false\n");
            writer.write("debug = " + GameLogic.debug + "\n\n");
            writer.write("// player speed\n");
            writer.write("// Default 2\n");
            writer.write("playerSpeed = " + GameLogic.player.speed + "\n\n");
            writer.write("// player jump hight\n");
            writer.write("// Default 70" + "\n");
            writer.write("jumpHight = " + GameLogic.jumpHight + "\n\n");
            writer.write("// unlocked spells\n");
            writer.write("// Default all 0\n");
            writer.write("spells = " + Arrays.toString(GameLogic.unlockedSpells) + "\n\n");
            writer.write("// Frame position\n");
            writer.write("// Default (0,0)\n");
            writer.write("frameLocation = " + location.x + "," + location.y + "\n\n");
            writer.write("// Language\n");
            writer.write("// Default Deutsch\n");
            writer.write("activLanguage = " + Translation.activLanguage + "\n\n");
            writer.write("// was the game started bevor\n");
            writer.write("// Default true\n");
            writer.write("startedGame = " + startedGame+ "\n\n");
            writer.write("// Inventory Slots\n");
            writer.write("// Default 10\n");
            writer.write("inventorySlots = " + InventoryManager.maxInventorySlots+ "\n\n");
            writer.write("// Inventory\n");
            writer.write("// Default --\n");
            writer.write("inventory = " + InventoryManager.createSaveString()+ "\n\n");

        } catch (IOException e) {
            e.printStackTrace();
            Logger.logError("Couldn't save Configurations: Error while writing file");
        }

        // Kopiere Inhalte von tempFile nach CONFIG_FILE überschreiben
        try (BufferedReader tempReader = new BufferedReader(new FileReader(tempFile));
             BufferedWriter configFileWriter = new BufferedWriter(new FileWriter(CONFIG_FILE))) {

            String tempLine;
            while ((tempLine = tempReader.readLine()) != null) {
                configFileWriter.write(tempLine);
                configFileWriter.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            Logger.logError("Couldn't save Configurations: Error while overwriting file");
        } finally {
            // Lösche die temporäre Datei
            File tempFileToDelete = new File(tempFile);
            if (!tempFileToDelete.delete()) {
            	Logger.logError("Couldn't save Configurations: Error while deleting temp file");
            }
        }
        Logger.logInfo("Configurations saved");
    }


	
	public static void resetConfig() {	//Konfigurationen auf einen Standart Wert zurücksetzen
		int[] emptySpells = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        // Setze alle Konfigurationswerte auf Standardwerte
        GameLogic.musicEnabled = true;
        GameLogic.dungeonKey = 1;
        GameLogic.debug = false;
        GameLogic.jumpHight = 70;
        GameLogic.player.speed = 2;
        MusicPlayer.musicVolume = -30F;
        GameLogic.unlockedSpells = emptySpells;
        GameScreen.location = new Point(0,0);
        Translation.activLanguage = Language.Deutsch;
        startedGame = false;
        InventoryManager.maxInventorySlots = 10;
        InventoryManager.clearInventory();

        // Speichere die neuen Standardwerte in der Konfigurationsdatei
        saveConfig();
        Logger.logInfo("Configurations reset");
    }
	
	private static int[] parseIntArray(String value) {	//String zu Int Array umwandeln
        String[] stringArray = value.replaceAll("[\\[\\]\\s]", "").split(",");
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }
	
	private static Point parsePoint(String value) {		//Ints zu Point umwandeln
	    String[] coords = value.split(",");
	    int x = Integer.parseInt(coords[0].trim());
	    int y = Integer.parseInt(coords[1].trim());
	    return new Point(x, y);
	}
	
	private static void checkGameStarted(String value) {
		startedGame = Boolean.parseBoolean(value);
		if(startedGame) {
			return;
		}else {
			DungeonCore.homeVillageBuild = false;
			DungeonCore.specialRoomBuild = true;
			DungeonCore.thisRooms.clear();
			DungeonCore.currentRoom = 1;
			DungeonCore.dungeonType = 5;
			DungeonCore.init();
//			GameScreen.updateRoomNr(1);
			GameLogic.vertikalAxis=true;
//			GameScreen.updateRoomNr(1);
			GameLogic.player.breite = 25;
			GameLogic.player.hoehe = 25;
			Draw.playerColor = Color.gray;
//			GameLogic.resetLevel();
			
			//Noch weitere Anweisungen für werte bei Spielstart
			
			startedGame = true;
			saveConfig();
		}
		
		
		
		
	}

}
