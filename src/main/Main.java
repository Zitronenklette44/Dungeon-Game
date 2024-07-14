package main;

import action.Logger;
import action.SaveLoad;
import gameMusik.MusicPlayer;
import gui.GameScreen;
import rooms.DungeonCore;
import spells.SpellIcons;
import spells.SpellManager;
import translation.Translation;

public class Main {
    public static void main(String[] args) {
    	Logger.logInfo("Start game Loading");
    	Logger.logSeperation();
    	SpellIcons.init();					//Spell Icons erstellen und fürs laden bereitstellen
        Translation.init();					//Übersetzungen laden
        MusicPlayer.init();					//Musik Listen erstellen
        SaveLoad.loadConfig();				//Variabeln überschreiben
        GameScreen.erstellen();				//Fenster erstellen
        DungeonCore.init();					//Dungeon erstellen und ersten Raum laden
        SpellManager.init();				//Zauber system erstellen und Zauber vorladen
        MusicPlayer.playSound(0, true);		//Background Musik abspielen
        Logger.logSeperation();
        Logger.logInfo("Game Loaded");
    }
}
