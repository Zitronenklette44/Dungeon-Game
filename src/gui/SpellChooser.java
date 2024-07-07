package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.GameLogic;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class SpellChooser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpellChooser frame = new SpellChooser();
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
	public SpellChooser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, GameScreen.screenBreite, GameLogic.screenHoehe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,125));
		panel.setBounds(0, 0, 1200, 800);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(35, 106, 150, 500);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(SpellChooser.class.getResource("/resources/backgrounds/ScrollPaperBackground.jpg")));
		lblNewLabel_1.setBounds(0, 0, 150, 500);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(283, 45, 240, 650);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon(SpellChooser.class.getResource("/resources/backgrounds/ScrollPaperBackground.jpg")));
		lblNewLabel_1_1.setBounds(0, 0, 240, 732);
		panel_1_1.add(lblNewLabel_1_1);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBounds(579, 45, 240, 650);
		panel.add(panel_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("");
		lblNewLabel_1_1_1.setIcon(new ImageIcon(SpellChooser.class.getResource("/resources/backgrounds/ScrollPaperBackground.jpg")));
		lblNewLabel_1_1_1.setBounds(-202, 0, 492, 732);
		panel_1_1_1.add(lblNewLabel_1_1_1);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBounds(873, 45, 240, 650);
		panel.add(panel_1_1_2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("");
		lblNewLabel_1_1_2.setIcon(new ImageIcon(SpellChooser.class.getResource("/resources/backgrounds/ScrollPaperBackground.jpg")));
		lblNewLabel_1_1_2.setBounds(-710, 0, 1000, 732);
		panel_1_1_2.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SpellChooser.class.getResource("/resources/backgrounds/SpellChooserBackground.png")));
		lblNewLabel.setBounds(0, 0, 1200, 800);
		contentPane.add(lblNewLabel);
	}
}
