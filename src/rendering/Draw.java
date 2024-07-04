package rendering;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;

import entitys.Arrow;
import entitys.InteractableTemplate;
import entitys.MobTemplate;
import game.Collisions;
import game.GameLogic;
import gameObject.CollisionRechteck;
import gameObject.Column;
import gameObject.SwordAttack;
import gameObject.Rechteck;
import rooms.DungeonCore;
import spells.SpellManager;
import spells.SpellTemplate;

public class Draw extends JLabel {
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
	public Draw(int screenBreite, int screenHoehe, GameLogic spiellogik) {
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

		//versteckte Interactionen
		g.setColor(Color.green);
		for (int i = 0; i < interactables.size(); i++) {
			InteractableTemplate aktuellesObjekt = interactables.get(i);
			g.fillRect((int)aktuellesObjekt.posX, (int)aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
		}


		//zeiche Hintergrund
		g.setColor(backgroundColor);
		//g.fillRect(0, 0, screenwidth, screenheight);

		//zeiche Raum
		g.setColor(Color.white);
		GameLogic.dungeon.drawRoom(g2d);


		//Zeichne Objekte
		g.setColor(floorColor);
		for (int i = 0; i < floor.size(); i++) {
			if(DungeonCore.dungeonType == 0) {
				Rechteck aktuellesObjekt = floor.get(i);
				g.fillRect((int)aktuellesObjekt.posX, (int)aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
			}
		}

		g.setColor(columsColor);
		for (int i = 0; i < columns.size(); i++) {
			Column aktuellesObjekt = columns.get(i);
			aktuellesObjekt.draw(g);
		}

		g.setColor(collisionRectanglesColor);
		for (int i = 0; i < collisionRectangles.size(); i++) {
			CollisionRechteck aktuellesObjekt = collisionRectangles.get(i);
			if(aktuellesObjekt.isVisible) {
				g.fillRect((int)aktuellesObjekt.posX, (int)aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
			}else if(GameLogic.debug){
				g.setColor(Color.red);
				g2d.drawString(aktuellesObjekt.breite+"", aktuellesObjekt.posX+(aktuellesObjekt.breite/2), aktuellesObjekt.posY-5);
				g2d.drawString(aktuellesObjekt.hoehe+"", aktuellesObjekt.posX-40, aktuellesObjekt.posY+(aktuellesObjekt.hoehe/2));
				g.drawRect((int)aktuellesObjekt.posX, (int)aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
			}
		}

		g.setColor(deathRechteckColor);
		for (int i = 0; i < deathRechtecks.size(); i++) {
			SwordAttack aktuellesObjekt = deathRechtecks.get(i);
			if(aktuellesObjekt.isVisible) {
				g.fillRect((int)aktuellesObjekt.posX, (int)aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
			}else if(GameLogic.debug){
				g.setColor(Color.green);
				g2d.drawString(aktuellesObjekt.breite+"", aktuellesObjekt.posX+(aktuellesObjekt.breite/2), aktuellesObjekt.posY-5);
				g2d.drawString(aktuellesObjekt.hoehe+"", aktuellesObjekt.posX-40, aktuellesObjekt.posY+(aktuellesObjekt.hoehe/2));
				g.drawRect((int)aktuellesObjekt.posX, (int)aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
				g.setColor(Color.ORANGE);
				g2d.drawString(aktuellesObjekt.existingTime+"", aktuellesObjekt.posX+aktuellesObjekt.breite/4, aktuellesObjekt.posY+(aktuellesObjekt.hoehe/2));
			}
		}

		g.setColor(mobsColor);
		for (int i = 0; i < mobs.size(); i++) {
			if(checkValidmob(i)) {
				MobTemplate aktuellesObjekt = mobs.get(i);
				aktuellesObjekt.drawMob(g2d);
				drawHealthBar(g2d, aktuellesObjekt);
			}
		}

		g.setColor(bulletColor);
		for (int i = 0; i < bullets.size(); i++) {
			Arrow aktuellesObjekt = bullets.get(i);
			aktuellesObjekt.draw(g2d);
		}

		for (int i = 0; i < spells.size(); i++) {
			SpellTemplate aktuellesObjekt = spells.get(i);
			aktuellesObjekt.drawSpell(g2d);
		}
		

		//player
		g.setColor(playerColor);
		g.fillRect((int)player.posX,(int) player.posY, player.breite, player.hoehe);
		if(GameLogic.debug) {
			g2d.drawString(player.posX+"", player.posX, player.posY-5);
			g2d.drawString(player.posY+"", player.posX-40, player.posY+(player.hoehe/2));
		}

		//Healthbar
		g.setColor(new Color(0,0,0,200));
		g.fillRect(100, 10, 210, 25);
		g.setColor(Color.red);
		g.fillRect(105, 15, 200, 15);
		g.setColor(getHealtbarColor(GameLogic.player));
		int width = (int) ((float) GameLogic.player.Hp / GameLogic.player.maxHp * 200);
		g.fillRect(105, 15, width, 15);
		
		//ManaBar
		g.setColor(new Color(0,0,0,200));
		g.fillRect(750, 10, 210, 25);
		g.setColor(Color.gray);
		g.fillRect(755, 15, 200, 15);
		g.setColor(getManabarColor());
		width = (int) ((float) GameLogic.player.mana / GameLogic.player.maxMana * 200);
		g.fillRect(755, 15, width, 15);
		
		//ATKCooldown
		g.setColor(new Color(0,0,0,150));
		int size = (int) (((float) GameLogic.player.AtkCooldown / GameLogic.player.maxAtkCooldown) * 40);
		int centerX = 327 + 20; // 322 ist die linke Grenze 	 25 ist die Hälfte der Größe (50)
		int centerY = 5 + 20;
		g.fillOval(centerX - size/2, centerY - size/2, size, size);
		

		Collisions.checkInteractable(g2d, Color.white);
		repaint();
	}


	public static void clearObjects() {
		Draw.collisionRectangles.clear();
		Draw.columns.clear();
		Draw.interactables.clear();
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

	private boolean checkValidmob(int index) {
		boolean valid =true;
		MobTemplate mob = GameLogic.mobs.get(index);
		if(mob.posX>1200 || mob.posX <0) {
			valid = false;
		}
		if(mob.posY> 750 || mob.posY <0) {
			valid = false;
		}
		if(mob.Hp<=0) {
			valid = false;
		}
		if(mob.defeated) {
			valid = false;
		}
		if(!valid) {
			mob.defeated = true;
			mob.posX = -20;
			mob.posY = -20;
		}
		return valid;
	}
	
	public void drawHealthBar(Graphics2D g2d, MobTemplate mob){
		int maxHp = mob.maxHp;
		int currentHp = mob.Hp;
		float posX = mob.posX;
		float posY = mob.posY;
		
		g2d.setColor(new Color(0,0,0,200));
		g2d.fillRect((int) posX, (int) posY-6, mob.breite+1, (int)((mob.hoehe)/5));
		
		g2d.setColor(Color.red);
		int healthBarWidth = (int) (mob.breite) - mob.breite/10;
		int healthBarHeight = (mob.hoehe / 5)-(mob.hoehe / 10);
		int healthBarX = (int) posX + (mob.breite/10);
		int healthBarY = (int) posY - healthBarHeight - ((int)((mob.hoehe)/5)-healthBarHeight);
		g2d.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);
		
		g2d.setColor(getHealtbarColor(mob));
		healthBarWidth = (int) (((float) currentHp / maxHp) * mob.breite) - mob.breite/10;
		g2d.fillRect(healthBarX, healthBarY, healthBarWidth, healthBarHeight);
		
	}
	
	public static Color getHealtbarColor(MobTemplate mob) {
		if(mob.Hp<mob.maxHp/2) {
			return Color.orange;
		}
		return Color.green;
	}
	
	public static Color getManabarColor() {
		if(manabarBlink==60)
			manabarBlink=-1;
		
		if(manabarBlink == -1)
			return Color.cyan;
		manabarBlink++;
		if(manabarBlink>=0&&manabarBlink<20) 
			return Color.red;
		else if(manabarBlink>=20&&manabarBlink<40) 
			return Color.cyan;
		else if(manabarBlink>=40&&manabarBlink<60) 
			return Color.red;
		else
			return Color.cyan;
	}



}
