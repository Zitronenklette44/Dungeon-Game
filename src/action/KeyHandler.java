package action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GameLogic;
import rooms.CreateRooms;

public class KeyHandler implements KeyListener {

	GameLogic spieLogik;
	public KeyHandler(GameLogic spielLogic) {
		spieLogik = spielLogic;
	}
	
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A'|| e.getKeyCode() == KeyEvent.VK_LEFT) {
			spieLogik.moveLeft= true;
		}
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			spieLogik.moveRight=true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE && spieLogik.player.posY == spieLogik.floor) {
			spieLogik.jump=true;
			spieLogik.player.posY--;
		}
		
		//temp
		if(e.getKeyCode() == KeyEvent.VK_1 ) {
			CreateRooms.createRoom(0);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A'|| e.getKeyCode() == KeyEvent.VK_LEFT) {
			spieLogik.moveLeft= false;
		}
		if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D'|| e.getKeyCode() == KeyEvent.VK_RIGHT) {
			spieLogik.moveRight=false;
		}
		
	}

}
