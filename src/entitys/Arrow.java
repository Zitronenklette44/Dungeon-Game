package entitys;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import game.Collisions;
import game.GameLogic;

public class Arrow extends MobTemplate {
	public double angle;
	public float range;

	public Arrow(int hoehe, int breite, float posX, float posY, float dx, float dy, float speed, int SpawnX, int SpawnY, int damage, float range) {
		super(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY, damage, 1, 2);
		this.range = range;
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
			this.angle = Math.atan2(playerDirectionY, playerDirectionX);
		} else {
			this.dx = 0;
			this.dy = 0;
		}
	}

	public void draw(Graphics2D g2d) {
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
		if(!GameLogic.paused) {
			this.posX += this.dx*speed;
			this.posY += this.dy*speed;
			this.range -= Math.sqrt(this.dx * this.dx + this.dy * this.dy);
		}
	}

	public void checkDelet() {
		boolean delete = false;
		if(posX >= 1200 || posX < 0) {	//wenn außerhalb von sichtbarem Bereich
			delete = true;
		}
		if(posY >= 750 || posY <0) {
			delete = true;
		}
		if(range <= 0) {
			delete = true;
		}

		if(Collisions.checkCollision(this, this.dx+1, this.dy+1)||Collisions.checkMob(this, this.dx, this.dy, true)) {	//Überprüfen von Collisionen mit hinzufügen des Schadens
			delete = true;
		}



		if(delete) {	//löschen des objektes
			for(int i = 0 ;i<GameLogic.arrows.size();i++) {
				if(GameLogic.arrows.get(i)== this) {
					GameLogic.arrows.remove(i);
					break;
				}
			}


		}
	}
}
