package main;

import action.SaveLoad;
import gameMusik.MusicPlayer;
import gui.GameScreen;
import spells.SpellIcons;
import spells.SpellManager;
import translation.Translation;

public class Main {
    public static void main(String[] args) {
    	SpellIcons.init();
        Translation.init();
        MusicPlayer.init();
        SaveLoad.loadConfig();
        GameScreen.erstellen();
        MusicPlayer.playSound(0, true);
		SpellManager.init();
    }
}
