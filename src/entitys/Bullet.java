package entitys;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import gameObject.Rechteck;

public class Bullet extends MobTemplate{
	public double angle;
	public Bullet(int hoehe, int breite, int posX, int posY, int dx, int dy, int speed, int SpawnX, int SpawnY) {
		super(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY);
	}
	
	public void rotateToPlayerAndUpdate(Rechteck player){
		// Berechne den Vektor von der Bullet-Position zur Spieler-Position
        int playerX = player.posX;
        int playerY = player.posY;

        int directionX = playerX - this.posX;
        int directionY = playerY - this.posY;

        // Berechne die Länge des Vektors
        double length = Math.sqrt(directionX * directionX + directionY * directionY);

        // Normalisiere den Vektor (um die Richtung zu bekommen)
        double normalizedX = directionX / length;
        double normalizedY = directionY / length;

        // Setze die Bewegungsrichtung basierend auf der normierten Richtung und der Geschwindigkeit
        this.dx = (int) (normalizedX * this.speed);
        this.dy = (int) (normalizedY * this.speed);

        // Berechne den Rotationswinkel
        this.angle = Math.atan2(directionY, directionX);

        // Aktualisiere die Position des Bullets
        this.posX += this.dx;
        this.posY += this.dy;

        // Berechne den Rotationswinkel
        this.angle = Math.atan2(directionY, directionX);
    }
	
	public void draw(Graphics2D g2d) {
        AffineTransform oldTransform = g2d.getTransform();
        // Setze die Rotation
        g2d.rotate(angle, this.posX + (this.breite / 2), this.posY + (this.hoehe / 2));
        // Setze die alte Transformation zurück
        g2d.setTransform(oldTransform);
    }
}
