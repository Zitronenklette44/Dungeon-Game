package main;

import action.Logger;
import action.SaveLoad;
import game.Dialogs;
import gameMusik.MusicPlayer;
import gui.GameScreen;
import gui.LoadingGUI;
import rooms.DungeonCore;
import spells.SpellIcons;
import spells.SpellManager;
import translation.Translation;

public class Main {
    public static void main(String[] args) {
    	Logger.logInfo("Start game Loading...");
    	Logger.logSeperation();
    	LoadingGUI.erstellen(); 			//Ladebildschirm
    	try {Thread.sleep(1000);} catch (Exception e) {}
    	LoadingGUI.nextLoadingStage("Loading Spell Icons...");
    	SpellIcons.init();					//Spell Icons erstellen und fürs laden bereitstellen
    	LoadingGUI.nextLoadingStage("Loading translations...");
        Translation.init();					//Übersetzungen laden
        LoadingGUI.nextLoadingStage("Loading Dialogs...");
        Dialogs.init();						//Dialoge laden
        LoadingGUI.nextLoadingStage("Loading Music...");
        MusicPlayer.init();					//Musik Listen erstellen
        LoadingGUI.nextLoadingStage("Creating Game Screen...");
        GameScreen.erstellen();				//Fenster erstellen
        LoadingGUI.nextLoadingStage("Creating Dungeons...");
        DungeonCore.init();					//Dungeon erstellen und ersten Raum laden
        LoadingGUI.nextLoadingStage("Loading Save File...");
        MusicPlayer.playSound(0, true);		//Background Musik abspielen
        SaveLoad.loadConfig();				//Variabeln überschreiben
        LoadingGUI.nextLoadingStage("Loading Spells...");
        SpellManager.init();				//Zauber system erstellen und Zauber vorladen
        LoadingGUI.nextLoadingStage("Finalizing...");
        GameScreen.updateSpells();			//Spells in Slots laden
        GameScreen.showFrame();
        LoadingGUI.close();
        Logger.logSeperation();
        Logger.logInfo("Game Loaded");
    }
}
