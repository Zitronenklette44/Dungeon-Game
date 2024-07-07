package rendering;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;

import entitys.Arrow;
import entitys.InteractableTemplate;
import entitys.MobTemplate;
import game.GameLogic;
import gameObject.CollisionRechteck;
import gameObject.Column;
import gameObject.SwordAttack;
import gameObject.Rechteck;
import spells.SpellManager;
import spells.SpellTemplate;

public class DrawSpellsOverlay extends JLabel {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private int screenwidth;
	@SuppressWarnings("unused")
	private int screenheight;
	public static Rechteck player;
	static GameLogic spieLogic;
	public static int manabarBlink = -1;
	public static ArrayList<Rechteck> floor;
	public static ArrayList<Column> columns;
	public static ArrayList<CollisionRechteck> collisionRectangles;
	public static ArrayList<SwordAttack> deathRechtecks;
	public static ArrayList<MobTemplate> mobs;
	public static ArrayList<Arrow> bullets;
	public static ArrayList<InteractableTemplate> interactables;
	public static ArrayList<SpellTemplate> spells;

	public static Color backgroundColor = Color.DARK_GRAY;
	public static Color floorColor = Color.white;
	public static Color columsColor = Color.gray;
	public static Color playerColor = Color.white;
	public static Color collisionRectanglesColor = Color.white;
	public static Color deathRechteckColor = Color.red;
	public static Color mobsColor = Color.blue;
	public static Color bulletColor = Color.magenta;


	@SuppressWarnings("static-access")
	public DrawSpellsOverlay(int screenBreite, int screenHoehe, GameLogic spiellogik) {
		this.screenwidth =screenBreite;
		this.screenheight = screenHoehe;
		floor = spiellogik.floorObject;
		columns = spiellogik.columns;
		player= spiellogik.player;
		collisionRectangles = spiellogik.collisionRectangles;
		deathRechtecks = spiellogik.swordAttacks;
		mobs = spiellogik.mobs;
		bullets = spiellogik.arrows;
		this.spieLogic = spiellogik;
		interactables = spiellogik.interactables;
		this.spells = SpellManager.currentSpells;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if(GameLogic.paused) {
			//return;
		}

		//draw Cooldown
		g2d.setColor(new Color(0,0,0,200));
		if(SpellManager.Cooldowns[0]> 0) {
			g2d.fillRect(974, 11, 50, (int) ((float) SpellManager.Cooldowns[0] / SpellManager.maxCooldowns[0]* 50));
		}
		if(SpellManager.Cooldowns[1]> 0) {
			g2d.fillRect(1035, 11, 50, (int) ((float) SpellManager.Cooldowns[1] / SpellManager.maxCooldowns[1]* 50));
		}
		if(SpellManager.Cooldowns[2]> 0) {
			g2d.fillRect(1098, 11, 50, (int) ((float) SpellManager.Cooldowns[2] / SpellManager.maxCooldowns[2]* 50));
		}
		
		
		repaint();
	}



	public static void resetColor() {
		backgroundColor = Color.DARK_GRAY;
		floorColor = Color.white;
		columsColor = Color.gray;
		playerColor = Color.white;
		collisionRectanglesColor = Color.white;
		deathRechteckColor = Color.red;
		mobsColor = Color.blue;
		bulletColor = Color.magenta;
	}
}
