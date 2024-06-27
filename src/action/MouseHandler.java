package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.GameLogic;
import gameObject.CreateObjects;

public class MouseHandler implements MouseListener{
	
	GameLogic spiellogik;
	public MouseHandler(GameLogic spiellogikGameLogic) {
		spiellogik = spiellogikGameLogic;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1) {
			if(GameLogic.vertikalAxis && GameLogic.player.AtkCooldown == 0) {
				CreateObjects.createBullet(10, 25, GameLogic.player.posX, GameLogic.player.posY, 0.5F, 0, 0, 1, 300);
				GameLogic.player.AtkCooldown = 200;
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
