package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.SpellButton;
import game.GameLogic;
import rendering.DrawSpellCooser;
import spells.SpellIcons;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class SpellChooser extends JFrame {

	private static final long serialVersionUID = 1L;
	public static boolean needsUpdate = true;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void erstellen() {
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
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,125));
		panel.setBounds(0, 0, 1200, 800);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/*DrawSpellCooser draw = new DrawSpellCooser();
		draw.setBounds(0,0,1190,763);
		draw.setVisible(true);
		panel.add(draw);*/
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(35, 106, 150, 500);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		SpellButton btnNewButton = new SpellButton(SpellIcons.getIconBySpell(GameLogic.player.equipedSpells[0]), true);
		btnNewButton.setBounds(35, 50, 75, 75);
		panel_1.add(btnNewButton);
		
		SpellButton btnNewButton1 = new SpellButton(SpellIcons.getIconBySpell(GameLogic.player.equipedSpells[1]), true);
		btnNewButton1.setBounds(35, 200, 75, 75);
		panel_1.add(btnNewButton1);
		
		SpellButton btnNewButton2 = new SpellButton(SpellIcons.getIconBySpell(GameLogic.player.equipedSpells[2]), true);
		btnNewButton2.setBounds(35, 350, 75, 75);
		panel_1.add(btnNewButton2);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(SpellChooser.class.getResource("/resources/backgrounds/ScrollPaperBackground.jpg")));
		lblNewLabel_2.setBounds(0, 0, 150, 500);
		panel_1.add(lblNewLabel_2);
		
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
		
		JLabel questionMark = new JLabel("New label");
		questionMark.setIcon(new ImageIcon(SpellChooser.class.getResource("/resources/Icons/questionMark.png")));
		questionMark.setBounds(0, 0, 50, 50);
		panel.add(questionMark);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SpellChooser.class.getResource("/resources/backgrounds/SpellChooserBackground.png")));
		lblNewLabel.setBounds(0, 0, 1200, 800);
		contentPane.add(lblNewLabel);
	}
}
