package action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GameLogic;
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
		if(e.getKeyCode() == KeyEvent.VK_SPACE && GameLogic.onGround) {
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
		
		//temp
		if(e.getKeyCode() == KeyEvent.VK_1 ) {
			CreateRooms.createRoom(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_2 ) {
			CreateRooms.createRoom(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_3 ) {
			CreateRooms.createRoom(2);
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
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			GameLogic.isSpacePressed=false;
			GameLogic.jump=false;
		}	
	}

}
