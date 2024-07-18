package gameObject;

public class Rechteck {
	public int hoehe;
	public int breite;
	public float posX;
	public float posY;
	public boolean isVisible = true;
	
	public Rechteck(int hoehe,int breite, float posX, float posY) {
		this.hoehe = hoehe;
		this.breite = breite;
		this.posX = posX;
		this.posY = posY;
	}
}
