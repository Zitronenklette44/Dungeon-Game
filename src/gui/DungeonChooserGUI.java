package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import game.GameLogic;
import rendering.Draw;
import rooms.DungeonCore;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class DungeonChooserGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static DungeonChooserGUI frame;
	private static JButton btnVillage;
	private static JButton btnForest;
	private static JButton btnCastle;
	private static JButton btnDungeon;
	private JButton btnNewButton;
	static int selectedDungeonType=0;
	private static JLabel lbLock1;
	private JButton btnConfirm;
	private JPanel panel_1;
	private JLabel lbsomething;
	private static int dungeonKey = 0;
	private static JLabel lbLock2;
	private static JLabel lbLock3;
	private static JLabel lbLock4;

	/**
	 * Launch the application.
	 */
	public static void erstellen(int dungeonKey) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DungeonChooserGUI.dungeonKey = dungeonKey;
					frame = new DungeonChooserGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void close() {
		GameScreen.showFrame();
		frame.dispose();
	}

	/**
	 * Create the frame.
	 */
	public DungeonChooserGUI() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					close();
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0,100));
		panel.setBounds(0, 0, 1200, 800);
		contentPane.add(panel);
		panel.setLayout(null);

		btnVillage = new JButton("");
		btnVillage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dungeonKey>=1) {
					clearBorders();
					btnVillage.setBorder(new LineBorder(Color.orange, 4, true));
					selectedDungeonType = 1;
					btnConfirm.setVisible(true);
				}
			}
		});

		lbLock4 = new JLabel("");
		lbLock4.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/Icons/lock.png")));
		lbLock4.setOpaque(true);
		lbLock4.setBackground(new Color(0, 0, 0, 100));
		lbLock4.setBounds(975, 308, 150, 150);
		panel.add(lbLock4);

		lbLock3 = new JLabel("");
		lbLock3.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/Icons/lock.png")));
		lbLock3.setOpaque(true);
		lbLock3.setBackground(new Color(0, 0, 0, 100));
		lbLock3.setBounds(675, 308, 150, 150);
		panel.add(lbLock3);

		lbLock2 = new JLabel("");
		lbLock2.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/Icons/lock.png")));
		lbLock2.setOpaque(true);
		lbLock2.setBackground(new Color(0, 0, 0, 100));
		lbLock2.setBounds(375, 308, 150, 150);
		panel.add(lbLock2);

		lbLock1 = new JLabel("");
		lbLock1.setOpaque(true);
		lbLock1.setBackground(new Color(0, 0, 0,100));
		lbLock1.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/Icons/lock.png")));
		lbLock1.setBounds(75, 308, 150, 150);
		panel.add(lbLock1);

		btnVillage.setRequestFocusEnabled(false);
		btnVillage.setContentAreaFilled(false);
		btnVillage.setFocusable(false);
		btnVillage.setFocusTraversalKeysEnabled(false);
		btnVillage.setFocusPainted(false);
		btnVillage.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		btnVillage.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/DungeonIcons/Village.png")));
		btnVillage.setBounds(75, 308, 150, 150);
		panel.add(btnVillage);

		btnForest = new JButton("");
		btnForest.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/DungeonIcons/Forest.png")));
		btnForest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dungeonKey>=2) {
					clearBorders();
					btnForest.setBorder(new LineBorder(Color.orange, 4, true));
					selectedDungeonType = 2;
					btnConfirm.setVisible(true);
				}
			}
		});
		btnForest.setRequestFocusEnabled(false);
		btnForest.setContentAreaFilled(false);
		btnForest.setFocusable(false);
		btnForest.setFocusTraversalKeysEnabled(false);
		btnForest.setFocusPainted(false);
		btnForest.setBounds(375, 308, 150, 150);
		btnForest.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panel.add(btnForest);

		btnCastle = new JButton("");
		btnCastle.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/DungeonIcons/Castle.png")));
		btnCastle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dungeonKey>=3) {
					clearBorders();
					btnCastle.setBorder(new LineBorder(Color.orange, 4, true));
					selectedDungeonType = 3;
					btnConfirm.setVisible(true);
				}
			}
		});
		btnCastle.setRequestFocusEnabled(false);
		btnCastle.setContentAreaFilled(false);
		btnCastle.setFocusable(false);
		btnCastle.setFocusTraversalKeysEnabled(false);
		btnCastle.setFocusPainted(false);
		btnCastle.setBounds(675, 308, 150, 150);
		btnCastle.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panel.add(btnCastle);

		btnDungeon = new JButton("");
		btnDungeon.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/DungeonIcons/Dungeon.png")));
		btnDungeon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dungeonKey>=4) {
					clearBorders();
					btnDungeon.setBorder(new LineBorder(Color.orange, 4, true));
					selectedDungeonType = 4;
					btnConfirm.setVisible(true);
				}
			}
		});
		btnDungeon.setRequestFocusEnabled(false);
		btnDungeon.setContentAreaFilled(false);
		btnDungeon.setFocusable(false);
		btnDungeon.setFocusTraversalKeysEnabled(false);
		btnDungeon.setFocusPainted(false);
		btnDungeon.setBounds(975, 308, 150, 150);
		btnDungeon.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panel.add(btnDungeon);

		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnNewButton.setRequestFocusEnabled(false);
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setBorder(null);
		btnNewButton.setFocusable(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/Icons/ArrowBack.png")));
		btnNewButton.setBounds(10, 11, 98, 23);
		panel.add(btnNewButton);

		JLabel lbTitle = new JLabel("Wähle deinen Dungeon");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbTitle.setForeground(new Color(255, 255, 255));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(10, 15, 1180, 46);
		panel.add(lbTitle);

		panel_1 = new JPanel();
		panel_1.setFocusable(false);
		panel_1.setFocusTraversalKeysEnabled(false);
		panel_1.setBounds(501, 627, 200, 55);
		panel.add(panel_1);

		btnConfirm = new JButton("");
		btnConfirm.setBackground(new Color(0,0,0,200));
		btnConfirm.setContentAreaFilled(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DungeonCore.dungeonType = selectedDungeonType;
				DungeonCore.homeVillageBuild = false;
				GameLogic.vertikalAxis = true;
				GameLogic.dungeon.createDungeon();
				GameScreen.changeBackground(DungeonCore.getImage(0));
				GameLogic.directionRoom = 0;
				GameLogic.resetLevel();
				close();
				Draw.resetColor();
			}
		});
		panel_1.setLayout(null);
		btnConfirm.setBorder(null);
		btnConfirm.setFocusTraversalKeysEnabled(false);
		btnConfirm.setFocusPainted(false);
		btnConfirm.setFocusable(false);
		btnConfirm.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/Icons/ConfirmChoise.png")));
		btnConfirm.setBounds(0, 0, 200, 50);
		btnConfirm.setVisible(false);
		panel_1.add(btnConfirm);

		lbsomething = new JLabel("New label");
		lbsomething.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/backgrounds/black_Something.png")));
		lbsomething.setBounds(0, -2, 200, 59);
		panel_1.add(lbsomething);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(DungeonChooserGUI.class.getResource("/resources/backgrounds/DungeonChooserBackground.jpeg")));
		lblNewLabel.setBounds(0, 0, 1200, 800);
		contentPane.add(lblNewLabel);
		checkKey();
	}

	private static void clearBorders() {
		btnVillage.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		btnForest.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		btnCastle.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		btnDungeon.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));

	}

	public static int getSelectedDungeonType() {
		return selectedDungeonType;
	}
	
	private static void checkKey() {
		if(dungeonKey>=1) {lbLock1.setVisible(false);}
		if(dungeonKey>=2) {lbLock2.setVisible(false);}
		if(dungeonKey>=3) {lbLock3.setVisible(false);}
		if(dungeonKey>=4) {lbLock4.setVisible(false);}
	}
}
