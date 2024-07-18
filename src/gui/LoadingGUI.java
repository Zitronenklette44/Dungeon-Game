package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import action.SaveLoad;
import rendering.SplashScreen;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class LoadingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static int screenHoehe = 800;
	static int screenBreite = 1200;
	public static Point location;
	private static LoadingGUI frame;
	private static SplashScreen splash;
	private static JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void erstellen() {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					frame = new LoadingGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void close() {
		frame.dispose();
	}
	
	public static void nextLoadingStage(String loadingStage){
		SplashScreen.currentLoadingState++;
		lblNewLabel.setText(loadingStage);
		splash.repaint();
		splash.revalidate();
	}

	/**
	 * Create the frame.
	 */
	public LoadingGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				location = frame.getLocationOnScreen(); 
				SaveLoad.saveConfig();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, screenBreite, screenHoehe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setDoubleBuffered(true);
		setUndecorated(true);
		setAlwaysOnTop(true);
		
		splash = new SplashScreen();
		splash.setBounds(0,0,screenBreite,screenHoehe);
		splash.setVisible(true);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.gray.darker().darker());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(199, 251, 800, 75);
		contentPane.add(lblNewLabel);
		contentPane.add(splash);

		setContentPane(contentPane);
	}
}
