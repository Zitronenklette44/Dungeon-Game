package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import action.KeyHandler;
import game.GameLogic;
import rooms.CreateRooms;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class GameScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	static int screenHoehe = 800;
	static int screenBreite = 1200;
	static GameLogic spiellogik = new GameLogic();
	private static JLabel lbRoomNR;
	static GameScreen frame;
	private static JLabel lbBackground;


	/**
	 * Launch the application.
	 */
	public static void erstellen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new GameScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void hideFrame() {
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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, screenBreite, screenHoehe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		addKeyListener(new KeyHandler(spiellogik));
		
		Draw draw = new Draw(screenBreite,screenHoehe, spiellogik);
		draw.setBounds(0,0,screenBreite,screenHoehe);
		draw.setVisible(true);
		
		lbRoomNR = new JLabel("Room 1");
		lbRoomNR.setHorizontalAlignment(SwingConstants.CENTER);
		lbRoomNR.setForeground(Color.MAGENTA);
		lbRoomNR.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbRoomNR.setBounds(0, 11, 1184, 56);
		contentPane.add(lbRoomNR);
		contentPane.add(draw);

		setContentPane(contentPane);
		
		lbBackground = new JLabel("");
		lbBackground.setBounds(0, 0, 1184, 761);
		contentPane.add(lbBackground);
	}

	public static int getScreenHoehe() {
		return screenHoehe;
	}

	public static int getScreenBreite() {
		return screenBreite;
	}
	
	public static void updateRoomNr(int RoomNr) {
		lbRoomNR.setText("Room "+RoomNr);
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
	
}
