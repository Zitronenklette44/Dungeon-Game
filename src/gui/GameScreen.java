package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import action.KeyHandler;
import action.Logger;
import action.MouseHandler;
import action.SaveLoad;
import components.DialogButton;
import game.Dialogs;
import game.GameLogic;
import inventory.Inventory;
import inventory.InventoryManager;
import rendering.Draw;
import rendering.DrawQuests;
import rendering.DrawSpells;
import rendering.DrawSpellsOverlay;
import rendering.Resources;
import rooms.DungeonCore;
import spells.SpellIcons;
import translation.Translation;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class GameScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	static int screenHoehe = 800;
	static int screenBreite = 1200;
	static GameLogic spiellogik = new GameLogic();
	private static JLabel lbRoomNR;
	static GameScreen frame;
	private static JLabel lbBackground;
	private JPanel pauseMenue;
//	private static JPanel dialogMenue;
	public static boolean settingExists = false;
	private JLabel lblNewLabel;
	private static JLabel textJLabelSender;
	private static DialogButton btnNext;
	private static DialogButton btnSkip;
	private static JLabel textJLabel;
	private static JLabel lbSpell3;
	private static JLabel lbSpell2;
	private static JLabel lbSpell1;
	public static int selectedSpell = 0;
	private static JLabel[] spells = new JLabel[3];
	public static Point location;
	private static JLabel lbTitle;
	private static JButton btnNewButton;
	public static String currentDialog = "";
	public static boolean hasDialog = false;
	private static Draw draw;
	public static boolean paintRoomNum;
	public static Inventory inventory;
	private static DrawQuests quests;
	
	
	
	public static void erstellen() {
		Logger.logInfo("creating main Frame...");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GameScreen();
					frame.setVisible(true);
					hideFrame();
					if(location != null) {
						frame.setLocation(location);
					}else {
						frame.setLocationRelativeTo(null);
					} 
					Logger.logInfo("Frame successful created");
				} catch (Exception e) {
					Logger.logError("Error while creating Frame", e);
				}
			}
		});
	}
	
	public static void refresh() {
		Logger.logInfo("reloading Translations in main Frame...");
		Dialogs.init();
		lbTitle.setText(Translation.get("game.pause"));
		btnNewButton.setText(Translation.get("game.settings"));
		lbRoomNR.setText(Translation.get("game.room")+" "+DungeonCore.currentRoom);
		if(DungeonCore.currentRoom == 0) {
			lbRoomNR.setText(Translation.get("game.room")+ " 1");
		}
		btnNext.text = Translation.get("components.dialogButton.next");
		btnSkip.text = Translation.get("components.dialogButton.skip");
		Logger.logInfo("finished reloading Translations in main Frame");
	}
	
	public static void hideFrame() {
		Logger.logInfo("hiding main Frame");
		location = frame.getLocationOnScreen(); 
		frame.setVisible(false);
		GameLogic.moveDown =false;
		GameLogic.moveLeft = false;
		GameLogic.moveRight = false;
		GameLogic.moveUp = false;
	}
	
	public static void showFrame() {
		Logger.logInfo("show main Frame");
		frame.setVisible(true);
		GameLogic.moveDown =false;
		GameLogic.moveLeft = false;
		GameLogic.moveRight = false;
		GameLogic.moveUp = false;
	}

	/**
	 * Create the frame.
	 */
	public GameScreen() {
		addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {
				GameLogic.moveDown = false;
				GameLogic.moveUp = false;
				GameLogic.moveRight = false;
				GameLogic.moveLeft = false;
				GameLogic.jump = false;
				GameLogic.isSpacePressed = false;
			}
			@Override
			public void componentHidden(ComponentEvent e) {}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				location = frame.getLocationOnScreen(); 
				SaveLoad.saveConfig();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, screenBreite, screenHoehe);
		contentPane = new JPanel();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ESCAPE) {
					if(hasDialog) {btnNext.finishDialog();return;}
					GameLogic.paused = !GameLogic.paused;
					if(inventory.isVisible()) {inventory.setVisible(false);}
					togglePause();
				}
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		addKeyListener(new KeyHandler(spiellogik));
		addMouseListener(new MouseHandler(spiellogik));
		contentPane.setDoubleBuffered(true);
		
		pauseMenue = new JPanel();
		pauseMenue.setBounds(0, 0, 1184, 761);
		pauseMenue.setVisible(false);
		pauseMenue.setBackground(new Color(0,0,0,150));
		pauseMenue.setLayout(null);
		contentPane.add(pauseMenue);
		
//		dialogMenue = new JPanel();
//		dialogMenue.setBounds(0, (int) (getHeight()/1.25), 1184, (int) (getHeight()-(getHeight()/1.25)));
//		dialogMenue.setVisible(false);
////		dialogMenue.setBackground(new Color(0,0,0, 150));
//		dialogMenue.setBackground(Color.gray.darker());
//		dialogMenue.setLayout(null);
//		dialogMenue.setDoubleBuffered(true); 
//		contentPane.add(dialogMenue);
		
		textJLabel = new JLabel();
		textJLabel.setBounds(0, (getHeight()/100)*75, getWidth(), (int) ((getHeight()/100)*25));
		textJLabel.setVerticalAlignment(SwingConstants.TOP);
		textJLabel.setForeground(Color.white.darker());
		textJLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
//		textJLabel.setBackground(Color.blue);
//		textJLabel.setOpaque(true);
		textJLabel.setDoubleBuffered(true);
		textJLabel.setVisible(false);
		
		textJLabelSender = new JLabel();
		textJLabelSender.setBounds(0, (getHeight()/100)*70, getWidth(), (getHeight()/100)*5);
		textJLabelSender.setVerticalAlignment(SwingConstants.TOP);
		textJLabelSender.setForeground(Color.gray.darker().darker().darker());
		textJLabelSender.setFont(new Font("Tahoma", Font.BOLD, 25));
		textJLabelSender.setDoubleBuffered(true);
		textJLabelSender.setVisible(false);
//		textJLabelSender.setBackground(Color.green);
//		textJLabelSender.setOpaque(true);
		contentPane.add(textJLabelSender);
		contentPane.add(textJLabel);
		
		btnSkip = new DialogButton((byte) 1, textJLabel);
		btnSkip.setBounds(1000, (getHeight()/100)*85, 170, 30);
		btnSkip.setVisible(true);
		btnSkip.setDoubleBuffered(true);
		contentPane.add(btnSkip);
		
		btnNext = new DialogButton((byte) 0, textJLabel);
		btnNext.setBounds(800, (getHeight()/100)*85, 170, 30);
		btnNext.setVisible(true);
		btnNext.setDoubleBuffered(true);
		contentPane.add(btnNext);
		
		lbTitle = new JLabel(Translation.get("game.pause"));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(255, 255, 255));
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbTitle.setBounds(459, 0, 278, 86);
		pauseMenue.add(lbTitle);
		
		btnNewButton = new JButton(Translation.get("game.settings"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings.erstellen();
				settingExists = true;
			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setFocusable(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(459, 198, 278, 42);
		pauseMenue.add(btnNewButton);
		
		
		DrawSpellsOverlay drawSpellsOverlay = new DrawSpellsOverlay(screenBreite,screenHoehe, spiellogik);
		drawSpellsOverlay.setBounds(0,0,screenBreite,screenHoehe);
		drawSpellsOverlay.setVisible(true);
		contentPane.add(drawSpellsOverlay);
		
		lbSpell1 = new JLabel("");
		lbSpell1.setBorder(new LineBorder(Color.ORANGE, 3));
		lbSpell1.setBounds(974, 11, 50, 50);
		contentPane.add(lbSpell1);
		
		lbSpell2 = new JLabel("");
		lbSpell2.setBounds(1035, 11, 50, 50);
		contentPane.add(lbSpell2);
		
		lbSpell3 = new JLabel("");
		lbSpell3.setBounds(1098, 11, 50, 50);
		contentPane.add(lbSpell3);
		
		lblNewLabel = new JLabel();
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameLogic.paused = !GameLogic.paused;
				togglePause();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(Resources.pauseButton));
		lblNewLabel.setBounds(0, 0, 50, 50);
		contentPane.add(lblNewLabel);
		
		lbRoomNR = new JLabel(Translation.get("game.room")+" 1");
		lbRoomNR.setHorizontalAlignment(SwingConstants.CENTER);
		lbRoomNR.setForeground(Color.MAGENTA);
		lbRoomNR.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbRoomNR.setBounds(0, 11, 1184, 56);
		contentPane.add(lbRoomNR);
		
		draw = new Draw(screenBreite,screenHoehe, spiellogik);
		draw.setBounds(0,0,screenBreite,screenHoehe);
		draw.setVisible(true);
		contentPane.add(draw);
		
		DrawSpells drawSpells = new DrawSpells(screenBreite,screenHoehe, spiellogik);
		drawSpells.setBounds(0,0,screenBreite,screenHoehe);
		drawSpells.setVisible(true);
		contentPane.add(drawSpells);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Resources.ATKCooldown));
		lblNewLabel_1.setBounds(322, 0, 50, 50);
		contentPane.add(lblNewLabel_1);
		
		inventory = new Inventory(InventoryManager.maxInventorySlots);
		inventory.setBounds(0,0,1184,764);
		inventory.setVisible(false);
		contentPane.add(inventory);
		
		quests = new DrawQuests();
		quests.setBounds(0,0,1184,764);
		quests.setVisible(false);
		contentPane.add(quests);

		setContentPane(contentPane);
		
		lbBackground = new JLabel("");
		lbBackground.setBounds(0, 0, 1184, 761);
		contentPane.add(lbBackground);
		
		spells[0] = lbSpell1;
		spells[1] = lbSpell2;
		spells[2] = lbSpell3;
	
		contentPane.setComponentZOrder(inventory, 0);
		contentPane.setComponentZOrder(quests, 1);
		endDialog();
	}

	public static int getScreenHoehe() {
		return screenHoehe;
	}

	public static int getScreenBreite() {
		return screenBreite;
	}
	
	public static void updateRoomNr(int RoomNr) {
		if(!paintRoomNum) {
		lbRoomNR.setText(Translation.get("game.room")+" "+RoomNr);
		}
	}
	
	public static void updateRoomNr(boolean gilde) {
		paintRoomNum = gilde;
		lbRoomNR.setText(Translation.get("gilde.title"));
	}
	
	
	public static void changeBackground(BufferedImage image) {
	    try {
	        lbBackground.setIcon(new ImageIcon(image));
	    } catch (NullPointerException e) {
	        Logger.logWarning("GUI not fully initialized", e);
	    }
	}
	
	private void togglePause() {
		if(!settingExists) {
		boolean paused = GameLogic.paused;
		pauseMenue.setVisible(paused);
		}
	}
	
	public static void changeSpell(int spell) {
		for(int i = 0; i<spells.length;i++) {
			spells[i].setBorder(null);
		}
		spells[spell].setBorder(new LineBorder(Color.ORANGE, 3));
		selectedSpell = spell;
	}
	
	public static void updateSpells() {
		BufferedImage spellImage = SpellIcons.getBufferedImageBySpell(GameLogic.player.equipedSpells[0]);
		if (spellImage != null) {lbSpell1.setIcon(new ImageIcon(spellImage));}else {lbSpell1.setIcon(null);}
		
		spellImage = SpellIcons.getBufferedImageBySpell(GameLogic.player.equipedSpells[1]);
		if (spellImage != null) {lbSpell2.setIcon(new ImageIcon(spellImage));}else {lbSpell2.setIcon(null);}
		
		spellImage = SpellIcons.getBufferedImageBySpell(GameLogic.player.equipedSpells[2]);
		if (spellImage != null) {lbSpell3.setIcon(new ImageIcon(spellImage));}else {lbSpell3.setIcon(null);}
	}
	
	public static void startDialog(String sender) {
		Logger.logInfo("started Dialog");
		draw.setIgnoreRepaint(true);
		hasDialog=true;
		textJLabelSender.setVisible(true);
		btnNext.setVisible(true);
		btnSkip.setVisible(true);
		textJLabel.setVisible(true);
		
		GameLogic.paused = true;
		btnNext.setDialogStrings(currentDialog);
		btnNext.dialogSource = sender;
		btnNext.startDialog();
	}
	
	public static void continueDialog() {
		btnNext.currentDialogPoint++;
		btnNext.nextDialog();
	}
	
	public static void setSender(String sender) {
		textJLabelSender.setText(sender+":");
	}
	
	public static void endDialog() {
		hasDialog=false;
		draw.setIgnoreRepaint(false);
		textJLabelSender.setVisible(false);
		btnNext.setVisible(false);
		btnSkip.setVisible(false);
		textJLabel.setVisible(false);
		GameLogic.paused = false;
		Dialogs.setQuest(currentDialog);
	}
	
	public static void updateInventory() {
		inventory.maxInventarSlots = InventoryManager.maxInventorySlots;
	}
	public static void toggleQuests() {
		quests.setVisible(!quests.isVisible());
	}
}
