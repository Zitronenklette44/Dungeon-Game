package translation;

import java.util.HashMap;
import java.util.Map;

public class Translation {
	
	public static Language activLanguage = Language.Deutsch;
	
	static Map<String, String> germanTranslationMap;
	static Map<String, String> englishTranslationMap;
	
	public static void init(){
		
		germanTranslationMap = new HashMap<String, String>();
		englishTranslationMap = new HashMap<String, String>();
		
		germanTranslationMap.put("save.Comment1", "Wenn Musik aktiviert ist");
		englishTranslationMap.put("save.Comment1", "If Music activ");
		
		germanTranslationMap.put("save.Comment2", "Anzahl der in Besitz befindenen dungeon Keys");
		englishTranslationMap.put("save.Comment2", "Number of owned dungeon keys");
		
		germanTranslationMap.put("save.Comment3", "Spieler Geschwindigkeit");
		englishTranslationMap.put("save.Comment3", "player speed");
		
		germanTranslationMap.put("save.Comment4", "Spieler Sprung Höhe");
		englishTranslationMap.put("save.Comment4", "player jump hight");
		
		germanTranslationMap.put("save.Comment5", "Freigeschaltene Zauber");
		englishTranslationMap.put("save.Comment5", "unlocked spells");
		
		germanTranslationMap.put("save.Comment6", "Fenster Position");
		englishTranslationMap.put("save.Comment6", "Frame position");
		
		germanTranslationMap.put("save.Comment7", "Sprache");
		englishTranslationMap.put("save.Comment7", "Language");
		
		germanTranslationMap.put("interaction.dungeonEnter", "Dungeon betreten");
		englishTranslationMap.put("interaction.dungeonEnter", "Enter dungeon");
		
		germanTranslationMap.put("interaction.dungeonExit", "Dungeon verlassen");
		englishTranslationMap.put("interaction.dungeonExit", "Exit dungeon");
		
		germanTranslationMap.put("interaction.openPotions", "Potion Shop öffnen");
		englishTranslationMap.put("interaction.openPotions", "Open Potion shop");
		
		germanTranslationMap.put("interaction.openTool", "Werkzeug Shop öffnen");
		englishTranslationMap.put("interaction.openTool", "Open Tool shop");
		
		germanTranslationMap.put("shopPotion.health1", "Lebenstrank 1");
		englishTranslationMap.put("shopPotion.health1", "Health 1");
		
		germanTranslationMap.put("shopPotion.damage1", "Schadenstrank 1");
		englishTranslationMap.put("shopPotion.damage1", "damage 1");
		
		germanTranslationMap.put("shopPotion.speed1", "Geschwindigkeitstrank 1");
		englishTranslationMap.put("shopPotion.speed1", "speed 1");
		
		germanTranslationMap.put("shopTool.sword1", "Schwert 1");
		englishTranslationMap.put("shopTool.sword1", "sword 1");
		
		germanTranslationMap.put("shopTool.boots1", "Schuhe 1");
		englishTranslationMap.put("shopTool.boots1", "boots 1");
		
		germanTranslationMap.put("game.pause", "Pausiert");
		englishTranslationMap.put("game.pause", "Paused");
		
		germanTranslationMap.put("game.settings", "Einstellungen");
		englishTranslationMap.put("game.settings", "Settings");
		
		germanTranslationMap.put("game.room", "Raum");
		englishTranslationMap.put("game.room", "Room");
		
		germanTranslationMap.put("settings.confirm", "Bestätigen");
		englishTranslationMap.put("settings.confirm", "Confirm");
		
		germanTranslationMap.put("settings.volume", "Lautstärke");
		englishTranslationMap.put("settings.volume", "Volume");
		
		germanTranslationMap.put("settings.musicEnabled", "Musik Ein/Aus");
		englishTranslationMap.put("settings.musicEnabled", "Music On/Off");
		
		germanTranslationMap.put("settings.reset", "Auf Standart zurücksetzen");
		englishTranslationMap.put("settings.reset", "Reset Settings");
		
		germanTranslationMap.put("settings.reset.message", "Willst du weitermachen? Die Einstellungen lassen sich nicht mehr Wiederherstellen!");
		englishTranslationMap.put("settings.reset.message", "Do you want to continue? All current Settings will be lost!");
		
		germanTranslationMap.put("settings.reset.title", "Warnung");
		englishTranslationMap.put("settings.reset.title", "Warning");
	}
	
	
	public static String get(String textID) {
		
		switch (activLanguage){
		case Deutsch: return germanTranslationMap.get(textID);
		case English: return englishTranslationMap.get(textID);
		
		default:
			return "";
		}
	}
	

}
