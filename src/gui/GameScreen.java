package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import action.KeyHandler;
import action.MouseHandler;
import action.SaveLoad;
import game.GameLogic;
import rendering.Draw;
import rendering.DrawSpells;
import rendering.DrawSpellsOverlay;
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
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	public static boolean settingExists = false;
	private JLabel lblNewLabel;
	private static JLabel lbSpell3;
	private static JLabel lbSpell2;
	private static JLabel lbSpell1;
	public static int selectedSpell = 0;
	private static JLabel[] spells = new JLabel[3];
	public static Point location;


	/**
	 * Launch the application.
	 */
	public static void erstellen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GameScreen();
					frame.setVisible(true);
					if(location != null) {
						frame.setLocation(location);
					}else {
						frame.setLocationRelativeTo(null);
					} 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void hideFrame() {
		location = frame.getLocationOnScreen(); 
		frame.setVisible(false);
		GameLogic.moveDown =false;
		GameLogic.moveLeft = false;
		GameLogic.moveRight = false;
		GameLogic.moveUp = false;
	}
	
	public static void showFrame() {
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
					GameLogic.paused = !GameLogic.paused;
					togglePause();
				}
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		addKeyListener(new KeyHandler(spiellogik));
		addMouseListener(new MouseHandler(spiellogik));
		
		pauseMenue = new JPanel();
		pauseMenue.setBounds(0, 0, 1184, 761);
		pauseMenue.setVisible(false);
		pauseMenue.setBackground(new Color(0,0,0,150));
		contentPane.add(pauseMenue);
		pauseMenue.setLayout(null);
		
		JLabel lbTitle = new JLabel(Translation.get("game.pause"));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(255, 255, 255));
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbTitle.setBounds(459, 0, 278, 86);
		pauseMenue.add(lbTitle);
		
		JButton btnNewButton = new JButton(Translation.get("game.settings"));
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
		lbSpell1.setIcon(new ImageIcon(GameScreen.class.getResource("/resources/Icons/Fireball.png")));
		lbSpell1.setBounds(974, 11, 50, 50);
		contentPane.add(lbSpell1);
		
		lbSpell2 = new JLabel("");
		lbSpell2.setIcon(new ImageIcon(GameScreen.class.getResource("/resources/Icons/WaterDrop.png")));
		lbSpell2.setBounds(1035, 11, 50, 50);
		contentPane.add(lbSpell2);
		
		lbSpell3 = new JLabel("");
		lbSpell3.setIcon(new ImageIcon(GameScreen.class.getResource("/resources/Icons/rock.png")));
		lbSpell3.setBounds(1098, 11, 50, 50);
		contentPane.add(lbSpell3);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GameLogic.paused = !GameLogic.paused;
				togglePause();
			}
		});
		lblNewLabel.setIcon(new ImageIcon(GameScreen.class.getResource("/resources/Icons/PauseButton.png")));
		lblNewLabel.setBounds(0, 0, 50, 50);
		contentPane.add(lblNewLabel);
		
		lbRoomNR = new JLabel(Translation.get("game.room")+" 1");
		lbRoomNR.setHorizontalAlignment(SwingConstants.CENTER);
		lbRoomNR.setForeground(Color.MAGENTA);
		lbRoomNR.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbRoomNR.setBounds(0, 11, 1184, 56);
		contentPane.add(lbRoomNR);
		
		Draw draw = new Draw(screenBreite,screenHoehe, spiellogik);
		draw.setBounds(0,0,screenBreite,screenHoehe);
		draw.setVisible(true);
		contentPane.add(draw);
		
		DrawSpells drawSpells = new DrawSpells(screenBreite,screenHoehe, spiellogik);
		drawSpells.setBounds(0,0,screenBreite,screenHoehe);
		drawSpells.setVisible(true);
		contentPane.add(drawSpells);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(GameScreen.class.getResource("/resources/Icons/spear.png")));
		lblNewLabel_1.setBounds(322, 0, 50, 50);
		contentPane.add(lblNewLabel_1);

		setContentPane(contentPane);
		
		lbBackground = new JLabel("");
		lbBackground.setBounds(0, 0, 1184, 761);
		contentPane.add(lbBackground);
		
		spells[0] = lbSpell1;
		spells[1] = lbSpell2;
		spells[2] = lbSpell3;
		
	}

	public static int getScreenHoehe() {
		return screenHoehe;
	}

	public static int getScreenBreite() {
		return screenBreite;
	}
	
	public static void updateRoomNr(int RoomNr) {
		lbRoomNR.setText(Translation.get("game.room")+" "+RoomNr);
	}	
	
	
	public static void changeBackground(String ImagePath) {
	    if(ImagePath == null) {
	        return;
	    }
	    try {
	        lbBackground.setIcon(new ImageIcon(GameScreen.class.getResource(ImagePath)));
	    } catch (NullPointerException e) {
	        e.printStackTrace();
	    }
	}
	
	private void togglePause() {
		if(!settingExists ) {
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
}
