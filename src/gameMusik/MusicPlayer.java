package gameMusik;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.*;

import action.Logger;
import game.GameLogic;
import gui.Shops.ShopPotions;
import gui.Shops.ShopTools;
import rooms.DungeonCore;

public class MusicPlayer {
	private static Clip clip;
	public static float musicVolume = -30F;		//Musik Lautstärke
	public static float sfxVolume = -10F;		//Sound Effekt Lautstärke
	static String[] fileLocations = {"/gameMusik/musik/home_bg1.wav","/gameMusik/musik/home_bg2.wav","/gameMusik/musik/shop_bg.wav","/gameMusik/musik/soundEffects/swordSwing.wav","/gameMusik/musik/soundEffects/bowShot.wav"};
											//0									1								2										3												4
	static ArrayList<Clip> clipsType1 = new ArrayList<Clip>();	//neue Liste für jeden neuen Sound
	static ArrayList<Clip> clipsType2 = new ArrayList<Clip>();
	static ArrayList<Clip> clipsType3 = new ArrayList<Clip>();
	static ArrayList<Clip> clipsType4 = new ArrayList<Clip>();
	static ArrayList<Clip> clipsType5 = new ArrayList<Clip>();

	static ArrayList<FloatControl> volumeType1 = new ArrayList<FloatControl>();		//neue Liste für jeden neuen Sound
	static ArrayList<FloatControl> volumeType2 = new ArrayList<FloatControl>();
	static ArrayList<FloatControl> volumeType3 = new ArrayList<FloatControl>();
	static ArrayList<FloatControl> volumeType4 = new ArrayList<FloatControl>();
	static ArrayList<FloatControl> volumeType5 = new ArrayList<FloatControl>();
	
	
	static ArrayList<ArrayList<Clip>> Lists = new ArrayList<ArrayList<Clip>>();		//zusammenfügen der Listen
	static ArrayList<ArrayList<FloatControl>> volumeControlsLists = new ArrayList<ArrayList<FloatControl>>();	//zusammenfügen der Listen

	public static void init() {	//erstellen dre gesammten Listen
		Logger.logInfo("Loading Music...");
		Lists.clear();	//leeren um duplikate zu vermeiden
		//fileTypen
		Lists.add(clipsType1);	//hinzufügen der Listen
		Lists.add(clipsType2);
		Lists.add(clipsType3);
		Lists.add(clipsType4);
		Lists.add(clipsType5);

		//Lautstärke für clips
		volumeControlsLists.add(volumeType1);
		volumeControlsLists.add(volumeType2);
		volumeControlsLists.add(volumeType3);
		volumeControlsLists.add(volumeType4);
		volumeControlsLists.add(volumeType5);
		
		Logger.logInfo("finished loading Music");
	}


	public static void playSound(int fileNR, boolean loop) {		//Abspielen einer Sound Datei	evt wenn gewünscht mit loop
		if(!GameLogic.musicEnabled) {	//wenn keine musik
			return;
		}
		try {
			InputStream audioSrc = MusicPlayer.class.getResourceAsStream(fileLocations[fileNR]);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			
			clip.addLineListener(event -> {		//Listener um aus Liste zu löschen wenn fertig mit Spielen
	            if (event.getType() == LineEvent.Type.CLOSE) {
	                removeClipFromLists(fileNR, clip);
	                clip.close();
	            }
	        });
			
			clip.start();
			if(loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);;

			Lists.get(fileNR).add(clip);
			volumeControlsLists.get(fileNR).add(volumeControl);

			setVolume(fileNR, musicVolume);		//Setze Immer auf Musik Lautstärke muss angepasst werden wenn nur Sound Effekt!!!!
		}	 
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e)
		{
			Logger.logError("Fehler im Abspielen der Audio Datei", e);
		}
	}
	private static void removeClipFromLists(int fileNR, Clip clip) {	//Für Listener um Sound zu entfernen nicht andersweitig Verwenden
	    int index = Lists.get(fileNR).indexOf(clip);
	    if (index != -1) {
	        Lists.get(fileNR).remove(index);
	        volumeControlsLists.get(fileNR).remove(index);
	    }
	}
	
	public static void pauseSound(int fileNR) {		//Pausieren einer Saound Datei
		for(int i = 0; i<Lists.get(fileNR).size(); i++) {
	        if (Lists.get(fileNR).get(i) != null && clip.isRunning()) {
	        	Lists.get(fileNR).get(i).stop();
	        }
	    }
	}

	public static void continueSound(int fileNR) {	//Fortsetzen einer Sound Datei
		for(int i = 0; i<Lists.get(fileNR).size(); i++) {
	        if (Lists.get(fileNR).get(i) != null && !clip.isRunning()) {
	        	Lists.get(fileNR).get(i).start();
	        }
	    }
	}

	public static void stopSound(int fileNR) {		//Beenden einer Sound Datei
		for(int i = 0; i<Lists.get(fileNR).size(); i++) {
			if (Lists.get(fileNR).get(i) != null && clip.isRunning()) {
				Lists.get(fileNR).get(i).stop();
				Lists.get(fileNR).get(i).close();
			}
		}
	}

	public static void pauseAllSound() {	//Pausieren aller Sounds
	    for (int fileNR = Lists.size() - 1; fileNR >= 0; fileNR--) {
	        for (int i = Lists.get(fileNR).size() - 1; i >= 0; i--) {
	            Clip clip = Lists.get(fileNR).get(i);
	            if (clip != null && clip.isRunning()) {
	                clip.stop();
	            }
	        }
	    }
	}

	public static void continueAllSound() {		//Fortsetzen aller Sounds
	    for (int fileNR = Lists.size() - 1; fileNR >= 0; fileNR--) {
	        for (int i = Lists.get(fileNR).size() - 1; i >= 0; i--) {
	            Clip clip = Lists.get(fileNR).get(i);
	            if (clip != null && !clip.isRunning()) {
	                clip.start();
	            }
	        }
	    }
	}

	public static void stopAllSound() {		//Beenden aller Sounds
		for(int fileNR = Lists.size()-1; fileNR>=0;fileNR--) {
			for(int i = Lists.get(fileNR).size()-1; i>=0;i--) {
				if(Lists.get(fileNR).getFirst() != null && Lists.get(fileNR).getFirst().isRunning()) {
					Lists.get(fileNR).get(i).stop();
					Lists.get(fileNR).get(i).close();
				}
			}
		}
	}
	
	public static void setVolume(int fileNR, float volume) {	//Lautstärke einer Sound Datei einstellen
		ArrayList<FloatControl> volumeControlsList = volumeControlsLists.get(fileNR);
		for(int i = volumeControlsList.size()-1;i>=0;i--) {
			FloatControl volumeControl = volumeControlsList.get(i);
			//if (volumeControl != null && volume>-80 && volume<-10) {
				volumeControl.setValue(volume);
			//}
		}
	}

	
	public static void setVolumeAll(float volume) {		//Lautstärke aller Sound Dateien einstellen
		musicVolume = volume;
		for(int i = volumeControlsLists.size()-1; i>=0;i--) {
			ArrayList<FloatControl> volumeControlsList = volumeControlsLists.get(i);
			for (FloatControl volumeControl : volumeControlsList) {
				//if (volumeControl != null && volume>-80 && volume<-10) {
					volumeControl.setValue(volume);
				//}
			}
		}
	}

	public static float getVolume(int fileType) {		//Lautstärke einer Sound Datei erhalten
		return volumeControlsLists.get(fileType).getFirst().getValue();
	}


	public static void startCurrentMusic() {		//musik passend zum aktuellen Spiel Punkt starten
		if(!GameLogic.musicEnabled) {return;}
		if(DungeonCore.homeVillageBuild && !ShopPotions.existing && !ShopTools.existing) {
			playSound(0, true);
			return;
		}
		if(!DungeonCore.homeVillageBuild && ShopPotions.existing || ShopTools.existing) {
			playSound(2, true);
		}
	}


}