package entitys;

import gameObject.Rechteck;

public class TestMob extends Rechteck {
    public int dx;
    public int SpawnX;
    public int SpawnY;
    public int speed;

    public TestMob(int hoehe, int breite, int posX, int posY, int dx, int speed, int SpawnX, int SpawnY) {
        super(hoehe, breite, posX, posY);
        this.dx = dx;
        this.SpawnX = SpawnX;
        this.SpawnY = SpawnY;
        this.speed = speed;
    }

    public void resetPos() {
        this.posX = SpawnX;
        this.posY = SpawnY;
    }
}
