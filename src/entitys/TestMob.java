package entitys;


public class TestMob extends MobTemplate {
    public TestMob(int hoehe, int breite, int posX, int posY, int dx, int dy, int speed, int SpawnX, int SpawnY) {
		super(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY);
	}

    public void resetPos() {
        this.posX = SpawnX;
        this.posY = SpawnY;
    }
}
