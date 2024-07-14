package action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.GameLogic;
import gameMusik.MusicPlayer;
import gameObject.CreateObjects;

public class MouseHandler implements MouseListener{
	
	GameLogic spiellogik;
	public MouseHandler(GameLogic spiellogikGameLogic) {
		spiellogik = spiellogikGameLogic;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == 1) {	//Linke Maustaste
		if (GameLogic.vertikalAxis && GameLogic.player.AtkCooldown == 0) {	//wenn in Dungeon und kein ATK Cooldown
            float attackPosX = GameLogic.player.posX;
            float attackPosY = GameLogic.player.posY;
            int reach = GameLogic.player.reach;
            int playerWidth = GameLogic.player.breite;
            int playerHeight = GameLogic.player.hoehe;

            // Angriffsposition und -größe basierend auf der Richtung des Spielers setzen
            if (GameLogic.player.lastdx > 0) { // Angriff nach rechts
                attackPosX += playerWidth;
                attackPosY -= playerHeight / 2; // Angriff vertikal zentrieren
                CreateObjects.createSwordAttack(reach, attackPosX, attackPosY, (int) (reach / 1.5), GameLogic.player.damage, 40, true ,true);
            } else if (GameLogic.player.lastdx < 0) { // Angriff nach links
                attackPosX = GameLogic.player.posX-reach/2;
                attackPosY -= playerHeight / 2; // Angriff vertikal zentrieren
                CreateObjects.createSwordAttack(reach, attackPosX, attackPosY, (int) (reach / 1.5), GameLogic.player.damage, 40, false ,true);
            } else if (GameLogic.player.lastdy > 0) { // Angriff nach unten
                attackPosY += playerHeight;
                attackPosX -= reach / 2 - playerWidth / 2; // Angriff horizontal zentrieren
                CreateObjects.createSwordAttack((int) (reach/1.5), attackPosX, attackPosY, reach, GameLogic.player.damage, 40, false ,true);
            } else if (GameLogic.player.lastdy < 0) { // Angriff nach oben
                attackPosY -= reach/2;
                attackPosX -= reach / 2 - playerWidth / 2; // Angriff horizontal zentrieren
                CreateObjects.createSwordAttack((int) (reach/1.5), attackPosX, attackPosY, reach, GameLogic.player.damage, 40, false ,true);
            }
            MusicPlayer.playSound(3, false);	//spoundeffect abspielen
            MusicPlayer.setVolume(3, MusicPlayer.sfxVolume);
            GameLogic.player.setAtkCooldown(); // Start cooldown after attack
        }
		}
		
		if(e.getButton() == 3) {	//rechte Maustaste
			if(GameLogic.vertikalAxis && GameLogic.player.AtkCooldown == 0) {	//wenn in Dungeon und kein ATK Cooldown
				CreateObjects.createArrow(10, 25, GameLogic.player.posX, GameLogic.player.posY, 0.5F, 0, 0, 1, 300);	//Pfeil erstellen
				GameLogic.player.setAtkCooldown();	//ATK Cooldown setzen
				 MusicPlayer.playSound(4, false);	//soundeffect abspielen
				 MusicPlayer.setVolume(4, MusicPlayer.sfxVolume);
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
