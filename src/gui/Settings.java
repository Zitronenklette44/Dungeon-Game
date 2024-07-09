package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import action.SaveLoad;
import game.GameLogic;
import gameMusik.MusicPlayer;
import translation.Translation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;

public class Settings extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static Settings dialog;

	/**
	 * Launch the application.
	 */
	public static void erstellen() {
		try {
			dialog = new Settings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setLocation(GameScreen.location.x+dialog.getWidth()/2, GameScreen.location.y+dialog.getHeight()/2);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void reOpen() {
		dialog.dispose();
		erstellen();
	}

	/**
	 * Create the dialog.
	 */
	public Settings() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GameScreen.settingExists = false;
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 477);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 575, 439);
		contentPanel.add(panel);
		panel.setBackground(new Color(0,0,0,175));
		panel.setLayout(null);

		JButton okButton = new JButton(Translation.get("settings.confirm"));
		okButton.setBackground(Color.LIGHT_GRAY);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveLoad.saveConfig();
				dialog.dispose();
				GameScreen.settingExists = false;
			}
		});
		okButton.setBounds(468, 405, 97, 23);
		panel.add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);


		JSlider sliderVolume = new JSlider();
		sliderVolume.setFocusTraversalKeysEnabled(false);
		sliderVolume.setFocusable(false);
		sliderVolume.setBackground(Color.GRAY);
		sliderVolume.setForeground(Color.GRAY);
		sliderVolume.setSnapToTicks(true);
		sliderVolume.setMajorTickSpacing(5);
		sliderVolume.setMinimum(-80);
		sliderVolume.setMaximum(-10);
		sliderVolume.setValue((int) MusicPlayer.totalVolume);
		sliderVolume.addChangeListener(e -> {
			float volume = sliderVolume.getValue();
			MusicPlayer.setVolumeAll(volume);
		});
		sliderVolume.setBounds(311, 30, 160, 26);
		panel.add(sliderVolume);
		
		JLabel lblNewLabel_1 = new JLabel(Translation.get("settings.volume")+":");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(73, 30, 201, 26);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(Translation.get("settings.musicEnabled")+":");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(73, 67, 201, 26);
		panel.add(lblNewLabel_1_1);
		
		JCheckBox chMusicEnabled = new JCheckBox("");
		chMusicEnabled.setBackground(Color.GRAY);
		chMusicEnabled.setForeground(Color.GRAY);
		chMusicEnabled.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameLogic.musicEnabled = chMusicEnabled.isSelected();
				MusicPlayer.stopAllSound();
				if(GameLogic.musicEnabled) {
					MusicPlayer.startCurrentMusic();
				}
			}
		});
		chMusicEnabled.setFocusPainted(false);
		chMusicEnabled.setFocusable(false);
		chMusicEnabled.setRequestFocusEnabled(false);
		chMusicEnabled.setBounds(368, 73, 21, 23);
		chMusicEnabled.setSelected(GameLogic.musicEnabled);
		panel.add(chMusicEnabled);
		
		JButton btnAufStandartZurcksetzen = new JButton(Translation.get("settings.reset"));
		btnAufStandartZurcksetzen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, Translation.get("settings.reset.message"), Translation.get("settings.reset.title"),JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					SaveLoad.resetConfig();
				}
			}
		});
		btnAufStandartZurcksetzen.setBackground(Color.LIGHT_GRAY);
		btnAufStandartZurcksetzen.setBounds(10, 405, 186, 23);
		panel.add(btnAufStandartZurcksetzen);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(Translation.get("settings.language")+":");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(73, 104, 201, 26);
		panel.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton(Translation.get("settings.activLanguage"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
				btnNewButton.setText(Translation.get("settings.activLanguage"));
				Translation.switchActivLanguage();
				reOpen();
				GameScreen.refresh();
			}
		});
		btnNewButton.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0), null, null));
		btnNewButton.setRequestFocusEnabled(false);
		btnNewButton.setFocusable(false);
		btnNewButton.setBackground(Color.gray);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBounds(336, 103, 108, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setIcon(new ImageIcon(Settings.class.getResource("/resources/backgrounds/settings.png")));
		lblNewLabel.setBounds(-90, -63, 665, 502);
		contentPanel.add(lblNewLabel);


	}
}
