package translation;

import java.util.HashMap;
import java.util.Map;

import action.Logger;

public class Translation {
	
	public static Language activLanguage = Language.Deutsch;
	
	static Map<String, String> germanTranslationMap;
	static Map<String, String> englishTranslationMap;
	
	public static void init(){
	
		Logger.logInfo("Loading Translations...");
		
		germanTranslationMap = new HashMap<String, String>();
		englishTranslationMap = new HashMap<String, String>();
		
		germanTranslationMap.put("interaction.dungeonEnter", "Dungeon betreten");
		englishTranslationMap.put("interaction.dungeonEnter", "Enter dungeon");
		
		germanTranslationMap.put("interaction.questBoard", "Missionen auswählen");
		englishTranslationMap.put("interaction.questBoard", "Choose quest");
		
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
		
		germanTranslationMap.put("settings.language", "Sprache");
		englishTranslationMap.put("settings.language", "Language");
		
		germanTranslationMap.put("settings.activLanguage", "Deutsch");
		englishTranslationMap.put("settings.activLanguage", "English");
		
		germanTranslationMap.put("components.dialogButton.skip", "[Esc] Dialog überspringen");
		englishTranslationMap.put("components.dialogButton.skip", "[Esc] skip dialog");
		
		germanTranslationMap.put("components.dialogButton.next", "[space] weiter Lesen");
		englishTranslationMap.put("components.dialogButton.next", "[space] continue reading");
		
		germanTranslationMap.put("dialogs.emptySender", "Weltsprache");
		englishTranslationMap.put("dialogs.emptySender", "Voice of the world");
		
		germanTranslationMap.put("dialogs.gildenSender", "Gilden Meister, Meister Kleister");
		englishTranslationMap.put("dialogs.gildenSender", "Guild master, Master waster");
		
		germanTranslationMap.put("interaction.testDialog", "Reden");
		englishTranslationMap.put("interaction.testDialog", "talk");
		
		germanTranslationMap.put("gilde.title", "Gilde");
		englishTranslationMap.put("gilde.title", "Guild");
		
		germanTranslationMap.put("interaction.guildEnter", "Gilde betreten");
		englishTranslationMap.put("interaction.guildEnter", "enter guild");
		
		germanTranslationMap.put("interaction.guildExit", "Gilde verlassen");
		englishTranslationMap.put("interaction.guildExit", "leave guild");
		
		germanTranslationMap.put("interaction.chestOpen", "Kiste öffnen");
		englishTranslationMap.put("interaction.chestOpen", "open Chest");
		
		germanTranslationMap.put("interaction.grabBush", "Busch durchsuchen");
		englishTranslationMap.put("interaction.grabBush", "search Bush");
		
		
		//Items
		germanTranslationMap.put("item.copperCoin", "Kupfermünze");
		englishTranslationMap.put("item.copperCoin", "Copper coin");
		
		germanTranslationMap.put("item.silverCoin", "Silbermünze");
		englishTranslationMap.put("item.silverCoin", "Silver coin");
		
		germanTranslationMap.put("item.goldCoin", "Goldmünze");
		englishTranslationMap.put("item.goldCoin", "Gold coin");
		
		germanTranslationMap.put("item.bread", "Brot");	//
		englishTranslationMap.put("item.bread", "Bread");
		
		germanTranslationMap.put("item.apple", "Apfel");
		englishTranslationMap.put("item.apple", "Apple");
		
		germanTranslationMap.put("item.potato", "Kartoffel");
		englishTranslationMap.put("item.potato", "Potato");
		
		germanTranslationMap.put("item.mondtau", "Mondtau");
		englishTranslationMap.put("item.mondtau", "Moon Dew");
		
		germanTranslationMap.put("item.daemerungswurz", "Dämerungswurz");
		englishTranslationMap.put("item.daemerungswurz", "Duskroot");
		
		germanTranslationMap.put("item.schattenkraut", "Schattenkraut");
		englishTranslationMap.put("item.schattenkraut", "Shadow Herb");
		
		germanTranslationMap.put("item.kette", "Kette");
		englishTranslationMap.put("item.kette", "Necklace");
		
		germanTranslationMap.put("item.ring", "Ring");
		englishTranslationMap.put("item.ring", "Ring");
		
		germanTranslationMap.put("item.armreif", "Armreif");
		englishTranslationMap.put("item.armreif", "Bracelet");
		
		
		//Dialogs
		germanTranslationMap.put("dialogs.log1.part1", "Das ist die erste Nachricht");
		englishTranslationMap.put("dialogs.log1.part1", "This is the First Message");
		
		germanTranslationMap.put("dialogs.log1.part2", "Das ist die zweite Nachricht");
		englishTranslationMap.put("dialogs.log1.part2", "This is the second Message");
		
		germanTranslationMap.put("dialogs.log1.part3", "Das ist die letzte Nachricht");
		englishTranslationMap.put("dialogs.log1.part3", "This is the last Message");
		
			//Gilde
		germanTranslationMap.put("dialogs.log2.part1", "Ahh, Ein Fremder. Zumindest hab ich dich hier noch nie gesehen. Was wollen Sie hier?");
		englishTranslationMap.put("dialogs.log2.part1", "AHH, a stranger. At least I've never seen you here. What do you want here?. Meth??");
		
		germanTranslationMap.put("dialogs.log2.part2", "AHH, Du willst dich in der Gilde anmelden. Weißt du, wir lassen hier nicht Jeden rein. Du musst schon was drauf haben, um hier rein zu kommen.");
		englishTranslationMap.put("dialogs.log2.part2", "AHH, you want to sign up for the guild. You know, we don't let everyone in here. You have to have what it takes to get in here.");
		
		germanTranslationMap.put("dialogs.log2.part3", "Ich sehe. Du willst es also versuchen?");
		englishTranslationMap.put("dialogs.log2.part3", "I see. So you want to give it a try?");
		
		germanTranslationMap.put("dialogs.log2.part4", "Ok, gehe zum Feld und besiege die dort existierenden Monster, wenn du es schaffst nehmen wir dich auf.");
		englishTranslationMap.put("dialogs.log2.part4", "Ok, go to the field and defeat the monsters that exist there, if you make it we will take you in.");
		
				//Nach der Mission
		
		germanTranslationMap.put("dialogs.log3.part1", "Ohh, Du hast es tatsächlich geschafft. Willkommen in der Gilde.");
		englishTranslationMap.put("dialogs.log3.part1", "oh, you actually did it. Welcome to the guild.");
		
		germanTranslationMap.put("dialogs.log3.part2", "Du kannst bei dem Händler, in der Gilde, deine Items verkaufen wenn du magst.");
		englishTranslationMap.put("dialogs.log3.part2", "You can sell your items at the merchant, in the guild, if you like.");
		
		germanTranslationMap.put("dialogs.log3.part3", "Achso hier für die Erfolgreiche Mission.");
		englishTranslationMap.put("dialogs.log3.part3", "Oh, here for the Successful Mission.");
		
		germanTranslationMap.put("dialogs.log3.part4", "An der Tafel kannst du dir weitere Quest's anschauen und auswählen. Nur musst du diese bei mir Verifizieren. Dann viel Spaß mit deinem neuen Leben als richtiger Abenteurer.");
		englishTranslationMap.put("dialogs.log3.part4", "On the board you can continue to view and select missions. Only you have to verify them with me. Then have fun with your new life as a real adventurer.");
		
			//Quest Annahme
		
		germanTranslationMap.put("dialogs.log4.part1", "Du hast noch keine Quest? Schau an die Tafel links von mir. Und wähle eine Quest aus. Aber Vorsicht. Wähle weise.");
		englishTranslationMap.put("dialogs.log4.part1", "Don't have a quest yet? Look at the board to my left. And choose a quest. But be careful. Choose wisely.");
		
		germanTranslationMap.put("dialogs.log5.part1", "Ah, Da hast du dir eine schöne Quest ausgesucht. Viel Spaß und sei Vorsichtig.");
		englishTranslationMap.put("dialogs.log5.part1", "Ah, you've chosen a nice quest. Have fun and be careful.");
		
		germanTranslationMap.put("dialogs.log6.part1", "Du hast es Geschafft? Und du Lebst noch???? Hier ist deine Belohnung");
		englishTranslationMap.put("dialogs.log6.part1", "You made it? And you're still alive???? Here's Your Reward");
		
		Logger.logInfo("Loaded Translations");
	}
	
	
	public static String get(String textID) {		
		switch (activLanguage){
		case Deutsch: return germanTranslationMap.get(textID);
		case English: return englishTranslationMap.get(textID);
		
		default:
			return "";
		}
	}
	
	public static void switchActivLanguage() {
		for (int i = 0; i < Language.values().length; i++) {
			Language language = Language.values()[i];
			if(language == activLanguage) {
				if(i+1 < Language.values().length) {
					activLanguage =Language.values()[i+1];
				}else {
					activLanguage =Language.values()[0];
				}
				return;
			}
		}

	}
	

}
