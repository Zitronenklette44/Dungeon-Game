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

public class TestScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	static int screenHoehe = 800;
	static int screenBreite = 1200;
	static GameLogic spiellogik = new GameLogic();
	private static JLabel lbRoomNR;


	/**
	 * Launch the application.
	 */
	public static void erstellen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestScreen frame = new TestScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public TestScreen() {
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
		//CreateRooms.createRoom(spiellogik.dungeon.getRoom());
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
}
