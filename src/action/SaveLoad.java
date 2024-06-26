package action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import game.GameLogic;
import gameMusik.MusicPlayer;

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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            // Schreibe die aktualisierten Konfigurationswerte
            writer.write("// Wenn Musik aktiviert ist\n");
            writer.write("// Standart true, -30\n");
            writer.write("musicEnabled = " + GameLogic.musicEnabled + "\n");
            writer.write("musicVolume = "+MusicPlayer.totalVolume+"\n\n");
            writer.write("// Anzahl der in Besitz befindenen dungeon Keys\n");
            writer.write("// Standart ohne Spielfortschritt 1\n");
            writer.write("dungeonKey = " + GameLogic.dungeonKey + "\n\n");
            writer.write("// Debug Modus\n");
            writer.write("// Standart false\n");
            writer.write("debug = " + GameLogic.debug + "\n\n");
            writer.write("// Spieler Geschwindigkeit\n");
            writer.write("// Standart 2\n");
            writer.write("playerSpeed = " + GameLogic.player.speed + "\n\n");
            writer.write("// Spieler Sprung Höhe\n");
            writer.write("// Standart 70\n");
            writer.write("jumpHight = " + GameLogic.jumpHight + "\n\n");

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

}
