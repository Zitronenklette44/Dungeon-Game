package action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GameLogic;
import gameMusik.MusicPlayer;
import rooms.CreateRooms;

public class KeyHandler implements KeyListener {

	GameLogic spieLogik;
	public KeyHandler(GameLogic spielLogic) {
		spieLogik = spielLogic;
		CreateRooms.setSpielLogic(spielLogic);
	}

	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A'|| e.getKeyCode() == KeyEvent.VK_LEFT) {
			GameLogic.moveLeft= true;
		}
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			GameLogic.moveRight=true;
		}
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S'|| e.getKeyCode() == KeyEvent.VK_DOWN&&!GameLogic.vertikalAxis) {
			GameLogic.moveDown= true;
		}
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == KeyEvent.VK_UP&&!GameLogic.vertikalAxis) {
			GameLogic.moveUp=true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE && GameLogic.onGround&&!GameLogic.vertikalAxis&&!GameLogic.paused) {
			if(GameLogic.isSpacePressed == false) {
				GameLogic.jump=true;
			}else {
				GameLogic.jump =false;
			}
			GameLogic.isSpacePressed=true;
			GameLogic.player.posY--;
		}
		if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
			GameLogic.Interact=true;
		}

		if(e.getKeyCode() == KeyEvent.VK_F1) {
			GameLogic.debug=!GameLogic.debug;
		}
		
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_F3) {
			MusicPlayer.playSound(0, true);
		}
		if(e.getKeyCode() == KeyEvent.VK_F4) {
			MusicPlayer.playSound(1, true);
		}
		if(e.getKeyCode() == KeyEvent.VK_F2) {
			MusicPlayer.stopAllSound();
		}
		
		
		if(e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
			MusicPlayer.setVolumeAll(MusicPlayer.totalVolume-1F);
			System.out.println(MusicPlayer.getVolume(0));
		}
		if(e.getKeyCode() == KeyEvent.VK_ADD) {
			MusicPlayer.setVolumeAll(MusicPlayer.totalVolume+1F);
			System.out.println(MusicPlayer.getVolume(0));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A'|| e.getKeyCode() == KeyEvent.VK_LEFT) {
			GameLogic.moveLeft= false;
		}
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D'|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
			GameLogic.moveRight=false;
		}
		if(e.getKeyChar() == 's' || e.getKeyChar() == 'S'|| e.getKeyCode() == KeyEvent.VK_LEFT) {
			GameLogic.moveDown= false;
		}
		if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			GameLogic.moveUp=false;
		}

		if(e.getKeyCode() == KeyEvent.VK_SPACE&&!GameLogic.vertikalAxis) {
			GameLogic.isSpacePressed=false;
			GameLogic.jump=false;
		}	
	}

}
