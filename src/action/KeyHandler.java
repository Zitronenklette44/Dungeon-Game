package action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GameLogic;
import gui.GameScreen;
import gui.SpellChooser;
import inventory.InventoryManager;
import questSystem.QuestManager;
import rooms.DungeonCore;
import test.DungeonInfos;

public class KeyHandler implements KeyListener {

	GameLogic spieLogik;
	public KeyHandler(GameLogic spielLogic) {
		spieLogik = spielLogic;
	}

	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Keys für Bewegung
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A'|| e.getKeyCode() == KeyEvent.VK_LEFT) {
			GameLogic.moveLeft= true;
		}
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			GameLogic.moveRight=true;
		}
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S'|| e.getKeyCode() == KeyEvent.VK_DOWN&&GameLogic.vertikalAxis) {
			GameLogic.moveDown= true;
		}
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == KeyEvent.VK_UP&&GameLogic.vertikalAxis) {
			GameLogic.moveUp=true;
		}
		if(!GameScreen.hasDialog) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE && GameLogic.onGround&&!GameLogic.vertikalAxis&&!GameLogic.paused) {
				if(GameLogic.isSpacePressed == false) {
					GameLogic.jump=true;
				}else {
					GameLogic.jump =false;
				}
				GameLogic.isSpacePressed=true;
				GameLogic.player.posY--;
			}
		}
		
		
		if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {	//interaction mit Objekten
			GameLogic.Interact=true;
		}
		if(e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {	//auslösen von Zaubern
			GameLogic.fireSpell = true;
		}
		if(e.getKeyChar() == 'm' || e.getKeyChar() == 'M') {	//temporär
			SpellChooser.erstellen();
		}
		if(e.getKeyChar() == 'n' || e.getKeyChar() == 'N') {	//temporär
			DungeonInfos.erstellen();
		}
		if(e.getKeyChar() == 'b' || e.getKeyChar() == 'B') {	//temporär
			GameLogic.currentQuestDone = !GameLogic.currentQuestDone;
			if(GameLogic.currentQuestDone == false) GameLogic.currentQuest = null;
		}
	
		if((e.getKeyChar() == 'i' || e.getKeyChar() == 'I') && !GameLogic.paused) {	
			GameScreen.inventory.setVisible(!GameScreen.inventory.isVisible());
			GameLogic.paused = GameScreen.inventory.isVisible();
		}
		if(e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {	//temporär zum leften eines Dungeon
			DungeonCore.thisRooms.clear();
			DungeonCore.homeVillageBuild = true;
			DungeonCore.specialRoomBuild = false;
			DungeonCore.init();
			DungeonCore.currentRoom = 0;
			GameScreen.updateRoomNr(0);
			GameLogic.vertikalAxis=false;
			GameScreen.updateRoomNr(1);
			DungeonCore.dungeonType = 0;
			GameLogic.player.breite = 50;
			GameLogic.player.hoehe = 50;
			GameLogic.resetLevel();
			GameLogic.player.Hp = GameLogic.player.maxHp;
		}
		
		//Zauber auswahl mit entsprechender Zahl
		if(e.getKeyCode() == KeyEvent.VK_1) {
			GameScreen.changeSpell(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_2) {
			GameScreen.changeSpell(1);	
		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			GameScreen.changeSpell(2);	
		}

		if(e.getKeyCode() == KeyEvent.VK_F1) {	//debug modus
			GameLogic.debug=!GameLogic.debug;
		}
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_F3) {
			GameLogic.items.clear();
		}
		/*
		if(e.getKeyCode() == KeyEvent.VK_F4) {
			MusicPlayer.playSound(1, true);
		}
		if(e.getKeyCode() == KeyEvent.VK_F2) {
			MusicPlayer.stopAllSound();
		}*/
		
		
//		if(e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
//			MusicPlayer.setVolumeAll(MusicPlayer.totalVolume-1F);
//			System.out.println(MusicPlayer.getVolume(0));
//		}
		if(e.getKeyCode() == KeyEvent.VK_ADD) {
			if(InventoryManager.maxInventorySlots<40)
				InventoryManager.maxInventorySlots++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Bewegungen stoppen wenn nicht merh benötigt
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A'|| e.getKeyCode() == KeyEvent.VK_LEFT) {
			GameLogic.moveLeft= false;
		}
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D'|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
			GameLogic.moveRight=false;
		}
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S'|| e.getKeyCode() == KeyEvent.VK_DOWN) {
			GameLogic.moveDown= false;
		}
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == KeyEvent.VK_UP) {
			GameLogic.moveUp=false;
		}
		
		if(!GameScreen.hasDialog) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE&&!GameLogic.vertikalAxis) {
				GameLogic.isSpacePressed=false;
				GameLogic.jump=false;
			}	
		}else {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				GameScreen.continueDialog();
			}
		}
	}

}
