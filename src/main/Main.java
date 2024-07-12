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
    	SpellIcons.init();
        Translation.init();
        MusicPlayer.init();
        SaveLoad.loadConfig();
        GameScreen.erstellen();
        DungeonCore.init();
        SpellManager.init();
        MusicPlayer.playSound(0, true);
		Logger.logInfo("Game Loaded");
    }
}
