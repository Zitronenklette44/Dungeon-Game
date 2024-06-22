package action;

import gameMusik.MusicPlayer;
import gui.GameScreen;
import rooms.DungeonCore;

public class Main {
    public static void main(String[] args) {
        GameScreen.erstellen();
        MusicPlayer.init();
        SaveLoad.loadConfig();
        MusicPlayer.playSound(0, true);
    }
}
