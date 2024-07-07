package gameMusik;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.*;

import game.GameLogic;
import gui.Shops.ShopPotions;
import gui.Shops.ShopTools;
import rooms.DungeonCore;

public class MusicPlayer {
	private static Clip clip;
	public static float totalVolume = -30F;
	public static float sfxVolume = -10F;
	static String[] fileLocations = {"/gameMusik/musik/home_bg1.wav","/gameMusik/musik/home_bg2.wav","/gameMusik/musik/shop_bg.wav","/gameMusik/musik/soundEffects/swordSwing.wav","/gameMusik/musik/soundEffects/bowShot.wav"};
											//0									1								2										3												4
	static ArrayList<Clip> clipsType1 = new ArrayList<Clip>();
	static ArrayList<Clip> clipsType2 = new ArrayList<Clip>();
	static ArrayList<Clip> clipsType3 = new ArrayList<Clip>();
	static ArrayList<Clip> clipsType4 = new ArrayList<Clip>();
	static ArrayList<Clip> clipsType5 = new ArrayList<Clip>();

	static ArrayList<FloatControl> volumeType1 = new ArrayList<FloatControl>();
	static ArrayList<FloatControl> volumeType2 = new ArrayList<FloatControl>();
	static ArrayList<FloatControl> volumeType3 = new ArrayList<FloatControl>();
	static ArrayList<FloatControl> volumeType4 = new ArrayList<FloatControl>();
	static ArrayList<FloatControl> volumeType5 = new ArrayList<FloatControl>();
	
	
	static ArrayList<ArrayList<Clip>> Lists = new ArrayList<ArrayList<Clip>>();
	static ArrayList<ArrayList<FloatControl>> volumeControlsLists = new ArrayList<ArrayList<FloatControl>>();

	public static void init() {
		Lists.clear();
		//fileTypen
		Lists.add(clipsType1);
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
	}


	public static void playSound(int fileNR, boolean loop) {
		if(!GameLogic.musicEnabled) {
			return;
		}
		try {
			InputStream audioSrc = MusicPlayer.class.getResourceAsStream(fileLocations[fileNR]);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioSrc);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			
			clip.addLineListener(event -> {
	            if (event.getType() == LineEvent.Type.STOP) {
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

			setVolume(fileNR, totalVolume);
		}	 
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	private static void removeClipFromLists(int fileNR, Clip clip) {
	    int index = Lists.get(fileNR).indexOf(clip);
	    if (index != -1) {
	        Lists.get(fileNR).remove(index);
	        volumeControlsLists.get(fileNR).remove(index);
	    }
	}
	
	public static void pauseSound(int fileNR) {
	    for (Clip clip : Lists.get(fileNR)) {
	        if (clip != null && clip.isRunning()) {
	            clip.stop();
	        }
	    }
	}

	public static void continueSound(int fileNR) {
	    for (Clip clip : Lists.get(fileNR)) {
	        if (clip != null && !clip.isRunning()) {
	            clip.start();
	        }
	    }
	}

	public static void stopSound(int fileNR) {
	    for (Clip clip : Lists.get(fileNR)) {
	        if (clip != null && clip.isRunning()) {
	            clip.stop();
	            clip.close();
	        }
	    }
	}

	public static void pauseAllSound() {
	    for (int fileNR = Lists.size() - 1; fileNR >= 0; fileNR--) {
	        for (int i = Lists.get(fileNR).size() - 1; i >= 0; i--) {
	            Clip clip = Lists.get(fileNR).get(i);
	            if (clip != null && clip.isRunning()) {
	                clip.stop();
	            }
	        }
	    }
	}

	public static void continueAllSound() {
	    for (int fileNR = Lists.size() - 1; fileNR >= 0; fileNR--) {
	        for (int i = Lists.get(fileNR).size() - 1; i >= 0; i--) {
	            Clip clip = Lists.get(fileNR).get(i);
	            if (clip != null && !clip.isRunning()) {
	                clip.start();
	            }
	        }
	    }
	}

	public static void stopAllSound() {
		for(int fileNR = Lists.size()-1; fileNR>=0;fileNR--) {
			for(int i = Lists.get(fileNR).size()-1; i>=0;i--) {
				if(Lists.get(fileNR).getFirst() != null && Lists.get(fileNR).getFirst().isRunning()) {
					Lists.get(fileNR).get(i).stop();
					Lists.get(fileNR).get(i).close();
					Lists.get(fileNR).remove(i);
				}
			}
		}
	}
// Methode zum Einstellen der Lautstärke aller Clips einer bestimmten Datei
	public static void setVolume(int fileNR, float volume) {
		ArrayList<FloatControl> volumeControlsList = volumeControlsLists.get(fileNR);
		for(int i = volumeControlsList.size()-1;i>=0;i--) {
			FloatControl volumeControl = volumeControlsList.get(i);
			//if (volumeControl != null && volume>-80 && volume<-10) {
				volumeControl.setValue(volume);
			//}
		}
	}

	
	public static void setVolumeAll(float volume) {
		totalVolume = volume;
		for(int i = volumeControlsLists.size()-1; i>=0;i--) {
			ArrayList<FloatControl> volumeControlsList = volumeControlsLists.get(i);
			for (FloatControl volumeControl : volumeControlsList) {
				//if (volumeControl != null && volume>-80 && volume<-10) {
					volumeControl.setValue(volume);
				//}
			}
		}
	}

	public static float getVolume(int fileType) {
		return volumeControlsLists.get(fileType).getFirst().getValue();
	}


	public static void startCurrentMusic() {
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