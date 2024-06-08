package gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;

import game.GameLogic;
import gameObject.Column;
import gameObject.Rechteck;

public class Draw extends JLabel {
	private int screenwidth;
	private int screenheight;
	public static Rechteck player;
	public static ArrayList<Rechteck> spielObjekte;
	public static ArrayList<Column> columns;
	public static Color backgroundColor = Color.DARK_GRAY;
	public static Color gameObjectsColor = Color.white;
	public static Color columsColor = Color.gray;
	public static Color playerColor = Color.white;
	
	
	@SuppressWarnings("static-access")
	public Draw(int screenBreite, int screenHoehe, GameLogic spiellogik) {
		this.screenwidth =screenBreite;
		this.screenheight = screenHoehe;
		spielObjekte = spiellogik.spielObjekte;
		columns = spiellogik.columns;
		player= spiellogik.player;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//zeiche Hintergrund
		g.setColor(backgroundColor);
		g.fillRect(0, 0, screenwidth, screenheight);
		
		g.setColor(gameObjectsColor);
		for (int i = 0; i < spielObjekte.size(); i++) {
			Rechteck aktuellesObjekt = spielObjekte.get(i);
			g.fillRect(aktuellesObjekt.posX, aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
		}
		
		g.setColor(columsColor);
		for (int i = 0; i < columns.size(); i++) {
			Column aktuellesObjekt = columns.get(i);
			aktuellesObjekt.draw(g);
		}
		
		g.setColor(playerColor);
		g.fillRect(player.posX, player.posY, player.breite, player.hoehe);
		
		
		repaint();
	}
}
