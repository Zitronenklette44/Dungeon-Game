package action;

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
import translation.Language;
import translation.Translation;

public class SaveLoad {
	private static final String CONFIG_FILE = "src/action/config.txt";

	
	public static void loadConfig() {	//laden der Konfigurationen aus File in Variabeln
		Logger.logInfo("Loading Configurations");
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
                        break;
                    case "activLanguage":
                        Translation.activLanguage = Language.valueOf(value);
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
            writer.write("// "+Translation.get("save.Comment1")+"\n");
            writer.write("// Default true, -30\n");
            writer.write("musicEnabled = " + GameLogic.musicEnabled + "\n");
            writer.write("musicVolume = "+MusicPlayer.musicVolume+"\n\n");
            writer.write("// "+Translation.get("save.Comment2")+"\n");
            writer.write("// Default 1\n");
            writer.write("dungeonKey = " + GameLogic.dungeonKey + "\n\n");
            writer.write("// Debug Modus\n");
            writer.write("// Default false\n");
            writer.write("debug = " + GameLogic.debug + "\n\n");
            writer.write("// " +Translation.get("save.Comment3")+"\n");
            writer.write("// Default 2\n");
            writer.write("playerSpeed = " + GameLogic.player.speed + "\n\n");
            writer.write("// " +Translation.get("save.Comment4")+"\n");
            writer.write("// Default 70" + "\n");
            writer.write("jumpHight = " + GameLogic.jumpHight + "\n\n");
            writer.write("// " +Translation.get("save.Comment5")+"\n");
            writer.write("// Default all 0\n");
            writer.write("spells = " + Arrays.toString(GameLogic.unlockedSpells) + "\n\n");
            writer.write("// " +Translation.get("save.Comment6")+"\n");
            writer.write("// Default (0,0)\n");
            writer.write("frameLocation = " + location.x + "," + location.y + "\n\n");
            writer.write("// " +Translation.get("save.Comment7")+"\n");
            writer.write("// Default Deutsch\n");
            writer.write("activLanguage = " + Translation.activLanguage + "\n\n");

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
        // Setze alle Konfigurationswerte auf Standardwerte
        GameLogic.musicEnabled = true;
        GameLogic.dungeonKey = 1;
        GameLogic.debug = false;
        GameLogic.jumpHight = 70;
        GameLogic.player.speed = 2;
        MusicPlayer.musicVolume = -30F;

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

}
