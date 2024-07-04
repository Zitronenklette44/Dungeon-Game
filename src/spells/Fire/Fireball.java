package spells.Fire;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import game.Collisions;
import game.GameLogic;
import spells.SpellManager;
import spells.SpellTemplate;

public class Fireball extends SpellTemplate{

	public Fireball(float posX, float posY, float dx, float dy, boolean damagePlayer) {
		super(posX, posY, dx, dy, "fire", 1,(int) ((double)(GameLogic.player.damage/100)*20), 60, damagePlayer);
		breite = 25;
		hoehe = 25;
		speed =  0.5F;

	}

	
	public void rotateToPlayerDirection() {
		// Verwendet die Blickrichtung des Spielers
		float playerDirectionX = GameLogic.player.dx;
		float playerDirectionY = GameLogic.player.dy;

		// Berechne die Länge des Richtungsvektors
		double length = Math.sqrt(playerDirectionX * playerDirectionX + playerDirectionY * playerDirectionY);

		if (length != 0) { // Verhindere Division durch Null
			// Normalisiere den Vektor (um die Richtung zu bekommen)
			double normalizedX = playerDirectionX / length;
			double normalizedY = playerDirectionY / length;

			// Setze die Bewegungsrichtung basierend auf der normierten Richtung und der Geschwindigkeit
			this.dx = (float) (normalizedX * this.speed);
			this.dy = (float) (normalizedY * this.speed);

			// Berechne den Rotationswinkel
			angle = Math.atan2(playerDirectionY, playerDirectionX);
		} else {
			this.dx = 0;
			this.dy = 0;
		}
	}

	@Override
	public void drawSpell(Graphics2D g2d) {
		checkDelet();
		// Setze die alte Transformation zurück
		AffineTransform oldTransform = g2d.getTransform();

		// Berechne das Zentrum der Kugel
		float centerX = this.posX + this.breite / 2;
		float centerY = this.posY + this.hoehe / 2;

		// Setze die Rotation um das Zentrum der Kugel
		g2d.rotate(this.angle, centerX, centerY);

		// Zeichne die Kugel als Rechteck
		g2d.fillRect((int)this.posX, (int)this.posY, this.breite, this.hoehe);

		// Setze die alte Transformation zurück
		g2d.setTransform(oldTransform);

		// Aktualisiere die Position der Kugel
		this.posX += this.dx;
		this.posY += this.dy;
		//this.range -= Math.sqrt(this.dx * this.dx + this.dy * this.dy);
	}

	public void checkDelet() {
		boolean delete = false;
		if(posX >= 1200 || posX < 0) {
			delete = true;
		}
		if(posY >= 750 || posY <0) {
			delete = true;
		}
		//if(range <= 0) {
			//delete = true;
		//}

		if(Collisions.checkCollision(this, this.dx+1, this.dy+1)||Collisions.checkMob(this, this.dx, this.dy, true)) {
			delete = true;
		}



		if(delete) {
			for(int i = 0 ;i<SpellManager.currentSpells.size();i++) {
				if(SpellManager.currentSpells.get(i)== this) {
					SpellManager.currentSpells.remove(i);
					break;
				}
			}


		}
	}
	
}

