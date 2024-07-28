package rendering;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;
import gui.DungeonChooserGUI;
import gui.GameScreen;
import gui.Settings;
import gui.SpellChooser;
import gui.Shops.ShopPotions;
import spells.SpellIcons;
import test.SpriteTest;

public class Resources {
	public static BufferedImage playerAnimation;
	public static BufferedImage bush;
	public static BufferedImage chest;
	
	public static BufferedImage healthPotion;
	public static BufferedImage damagePotion;
	public static BufferedImage speedPotion;
	public static BufferedImage merchant;
	public static BufferedImage speechBubble;
	public static BufferedImage buyButton;
	public static BufferedImage sellButton;
	public static BufferedImage bgTable;
	public static BufferedImage bgTableSmall;
	
	public static BufferedImage lock;
	public static BufferedImage villageIcon;
	public static BufferedImage forestIcon;
	public static BufferedImage castleIcon;
	public static BufferedImage dungeonIcon;
	public static BufferedImage arrowBack;
	public static BufferedImage confirmButton;
	public static BufferedImage blackSomething;
	public static BufferedImage bgDungeonChooser;
	
	public static BufferedImage pauseButton;
	public static BufferedImage ATKCooldown;
	
	public static BufferedImage bgSettings;
	
	public static BufferedImage scrollPaper;
	public static BufferedImage questionMark;
	public static BufferedImage bgSpellChooser;
	
	public static BufferedImage player;
	
	public static BufferedImage copperCoin;
	public static BufferedImage silverCoin;
	public static BufferedImage goldCoin;
	
	public static BufferedImage bread;
	public static BufferedImage apple;
	public static BufferedImage potato;
	
	public static BufferedImage mondtau;
	public static BufferedImage daemmerungswurz;
	public static BufferedImage schattenkraut;
	
	public static BufferedImage ring;
	public static BufferedImage kette;
	public static BufferedImage armreif;
	
	public static BufferedImage bgEmptyRoom;
	public static BufferedImage bgFieldRoom;
	public static BufferedImage bgGuildRoom;
	public static BufferedImage bgVillageSpawnRoom;
	public static BufferedImage bgVillagePathRoom0;
	public static BufferedImage bgVillagePathRoom1;
	
	
	
	public static void init(){
		Logger.logInfo("started Loading Images...");
		try {
			playerAnimation = ImageIO.read(Resources.class.getResource("/resources/Entitys/Player/PlayerAnimations.png"));
			
			bush = ImageIO.read(Resources.class.getResource("/resources/objects/Bush.png"));
			
			chest = ImageIO.read(Resources.class.getResource("/resources/objects/chest.png"));
			
			healthPotion = ImageIO.read(Resources.class.getResource("/resources/Roter_HeilungsTrank.jpeg"));
			damagePotion = ImageIO.read(Resources.class.getResource("/resources/DamageBoost.jpg"));
			speedPotion = ImageIO.read(Resources.class.getResource("/resources/SppedBoost.jpg"));
			
			merchant = ImageIO.read(Resources.class.getResource("/resources/Merchant.png"));
			
			speechBubble = ImageIO.read(Resources.class.getResource("/resources/SpeechBubble.png"));
			
			buyButton = ImageIO.read(Resources.class.getResource("/resources/BuyButton.png"));
			sellButton = ImageIO.read(Resources.class.getResource("/resources/SellButton.png"));
			
			bgTable = ImageIO.read(Resources.class.getResource("/resources/backgrounds/TestBackground.jpeg"));
			bgTableSmall = ImageIO.read(Resources.class.getResource("/resources/backgrounds/TableSmall.jpeg"));
			
			lock = ImageIO.read(Resources.class.getResource("/resources/Icons/lock.png"));
			
			villageIcon = ImageIO.read(Resources.class.getResource("/resources/DungeonIcons/Village.png"));
			forestIcon = ImageIO.read(Resources.class.getResource("/resources/DungeonIcons/Forest.png"));
			castleIcon = ImageIO.read(Resources.class.getResource("/resources/DungeonIcons/Castle.png"));
			dungeonIcon = ImageIO.read(Resources.class.getResource("/resources/DungeonIcons/Dungeon.png"));
			
			arrowBack = ImageIO.read(Resources.class.getResource("/resources/Icons/ArrowBack.png"));
			
			confirmButton = ImageIO.read(Resources.class.getResource("/resources/Icons/ConfirmChoise.png"));
			
			blackSomething = ImageIO.read(Resources.class.getResource("/resources/backgrounds/black_Something.png"));
			
			bgDungeonChooser = ImageIO.read(Resources.class.getResource("/resources/backgrounds/DungeonChooserBackground.jpeg"));
			
			pauseButton = ImageIO.read(Resources.class.getResource("/resources/Icons/PauseButton.png"));
			
			ATKCooldown = ImageIO.read(Resources.class.getResource("/resources/Icons/spear.png"));
			
			bgSettings = ImageIO.read(Resources.class.getResource("/resources/backgrounds/settings.png"));
			
			scrollPaper = ImageIO.read(Resources.class.getResource("/resources/backgrounds/ScrollPaperBackground.jpg"));
			
			questionMark = ImageIO.read(Resources.class.getResource("/resources/Icons/questionMark.png"));
			
			bgSpellChooser = ImageIO.read(Resources.class.getResource("/resources/backgrounds/SpellChooserBackground.png"));
			
			player = ImageIO.read(Resources.class.getResource("/resources/Entitys/Player/Player.png"));
			
			copperCoin = ImageIO.read(Resources.class.getResource("/resources/Icons/items/CopperCoin.png"));
			silverCoin = ImageIO.read(Resources.class.getResource("/resources/Icons/items/SilverCoin.png"));
			goldCoin = ImageIO.read(Resources.class.getResource("/resources/Icons/items/GoldCoin.png"));
			
			bread = ImageIO.read(Resources.class.getResource("/resources/Icons/items/Bread.png"));
			apple = ImageIO.read(Resources.class.getResource("/resources/Icons/items/Apple.png"));
			potato = ImageIO.read(Resources.class.getResource("/resources/Icons/items/Potato.png"));
			
			mondtau = ImageIO.read(Resources.class.getResource("/resources/Icons/items/Mondtau.png"));
			daemmerungswurz = ImageIO.read(Resources.class.getResource("/resources/Icons/items/Daemerungswurz.png"));
			schattenkraut = ImageIO.read(Resources.class.getResource("/resources/Icons/items/Schattenkraut.png"));

			ring = ImageIO.read(Resources.class.getResource("/resources/Icons/items/Ring.png"));
			kette = ImageIO.read(Resources.class.getResource("/resources/Icons/items/Kette.png"));
			armreif = ImageIO.read(Resources.class.getResource("/resources/Icons/items/Armreif.png"));

			bgEmptyRoom = ImageIO.read(Resources.class.getResource("/resources/rooms/backgrounds/Empty.png"));
			bgFieldRoom = ImageIO.read(Resources.class.getResource("/resources/rooms/backgrounds/Feld.png"));
			bgGuildRoom = ImageIO.read(Resources.class.getResource("/resources/rooms/backgrounds/Gilde.png"));
			bgVillageSpawnRoom = ImageIO.read(Resources.class.getResource("/resources/rooms/backgrounds/VillageSpawn.png"));
			bgVillagePathRoom0 = ImageIO.read(Resources.class.getResource("/resources/rooms/backgrounds/VillagePath1.png"));
			bgVillagePathRoom1 = ImageIO.read(Resources.class.getResource("/resources/rooms/backgrounds/VillagePath2.png"));
			
			
			
			
			
		} catch (IOException e) {
			Logger.logError("Error loading Images: ",e);
		}
		Logger.logInfo("finished Loading Images");
	}

}
