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

	
	public static void loadConfig() {
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
                        MusicPlayer.totalVolume = Float.parseFloat(value);
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
                        // Handle unknown keys if necessary
                        break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
    }
	
	public static void saveConfig() {
        String tempFile = CONFIG_FILE + ".tmp"; // Temporäre Datei
        Point location = GameScreen.location;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Schreibe die aktualisierten Konfigurationswerte
            writer.write("// "+Translation.get("save.Comment1")+"\n");
            writer.write("// Default true, -30\n");
            writer.write("musicEnabled = " + GameLogic.musicEnabled + "\n");
            writer.write("musicVolume = "+MusicPlayer.totalVolume+"\n\n");
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
            writer.write("// Default = " + GameLogic.jumpHight + "\n\n");
            writer.write("// " +Translation.get("save.Comment5")+"\n");
            writer.write("// Default all 0\n");
            writer.write("spells = " + Arrays.toString(GameLogic.unlockedSpells) + "\n\n");
            writer.write("// " +Translation.get("save.Comment6")+"\n");
            writer.write("// Default (0,0)\n");
            writer.write("frameLocation = " + location.x + "," + location.y + "\n\n");
            writer.write("// " +Translation.get("save.Comment6")+"\n");
            writer.write("// Default Deutsch\n");
            writer.write("activLanguage = " + Translation.activLanguage + "\n\n");

            System.out.println("Konfigurationsdatei erfolgreich aktualisiert.");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Schreiben der Konfigurationsdatei.");
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
            System.out.println("Fehler beim Überschreiben der Konfigurationsdatei.");
        } finally {
            // Lösche die temporäre Datei
            File tempFileToDelete = new File(tempFile);
            if (!tempFileToDelete.delete()) {
                System.out.println("Fehler beim Löschen der temporären Datei.");
            }
        }
    }


	
	public static void resetConfig() {
        // Setze alle Konfigurationswerte auf Standardwerte
        GameLogic.musicEnabled = false;
        GameLogic.dungeonKey = 1;
        GameLogic.debug = false;
        GameLogic.jumpHight = 70;
        GameLogic.player.speed = 2;
        MusicPlayer.totalVolume = -30F;

        // Speichere die neuen Standardwerte in der Konfigurationsdatei
        saveConfig();
    }
	
	private static int[] parseIntArray(String value) {
        String[] stringArray = value.replaceAll("[\\[\\]\\s]", "").split(",");
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }
	
	private static Point parsePoint(String value) {
	    String[] coords = value.split(",");
	    int x = Integer.parseInt(coords[0].trim());
	    int y = Integer.parseInt(coords[1].trim());
	    return new Point(x, y);
	}

}
