package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import action.KeyHandler;
import game.GameLogic;

public class TestScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static int screenHoehe = 800;
	static int screenBreite = 1200;
	GameLogic spiellogik = new GameLogic();


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
		contentPane.add(draw);

		setContentPane(contentPane);
	}

	public static int getScreenHoehe() {
		return screenHoehe;
	}

	public static int getScreenBreite() {
		return screenBreite;
	}

}
