package gameObject;

public class CollisionRechteck extends Rechteck {
	public boolean isVisible= true;
    public CollisionRechteck(int hoehe, int breite, int posX, int posY) {
        super(hoehe, breite, posX, posY);
        isVisible = true;
    }
    
    public CollisionRechteck(int hoehe, int breite, int posX, int posY, boolean Visible) {
        super(hoehe, breite, posX, posY);
        isVisible = Visible;
    }
}
