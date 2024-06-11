package gui.Shops;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class ShopPotions extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static ShopPotions frame;

	/**
	 * Launch the application.
	 */
	public static void erstellen() {
		System.out.println("Will ne BANANE");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ShopPotions();
					frame.setVisible(true);
					frame.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void close() {
		frame.dispose();
	}

	/**
	 * Create the frame.
	 */
	public ShopPotions() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					System.out.println("pressed ");
					close();
				}
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		panel.setBackground(new Color(0,0,0,150));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(true);
		tabbedPane.setPreferredSize(new Dimension(20, 5));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabbedPane.setForeground(Color.white);
		tabbedPane.setBorder(null);
		tabbedPane.setFocusTraversalKeysEnabled(false);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFocusable(false);
		tabbedPane.setBackground(new Color(126, 78, 5));
		
		tabbedPane.setBounds(213, 211, 500, 400);
		panel.add(tabbedPane);
		
		JScrollPane panel_1 = new JScrollPane();
		panel_1.setBackground(new Color(126, 78, 5));
		panel_1.setBorder(null);
		tabbedPane.addTab("Stufe 1", null, panel_1, null);
		
		JPanel panel_6 = new JPanel();
        panel_6.setDoubleBuffered(false);
        panel_6.setFocusTraversalKeysEnabled(false);
        panel_6.setFocusable(false);
        panel_6.setBackground(new Color(126, 78, 5));
        panel_6.setPreferredSize(new Dimension(480, 360)); // Adjust size as needed
        panel_6.setLayout(null);
        panel_1.setViewportView(panel_6);
        
        JLabel lblNewLabel_2 = new JLabel("Health 1:                                                                                                    XCoins");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBackground(new Color(0, 0, 0,150));
        lblNewLabel_2.setOpaque(true);
        lblNewLabel_2.setBounds(50, 0, 450, 50);
        panel_6.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Icon 50x50");
        lblNewLabel_3.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/Roter_HeilungsTrank.jpeg")));
        lblNewLabel_3.setBounds(0, 0, 50, 50);
        panel_6.add(lblNewLabel_3);
        
        JLabel lblNewLabel_2_1 = new JLabel("Damage 1:                                                                                                    XCoins");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setOpaque(true);
        lblNewLabel_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_1.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_1.setBounds(50, 50, 450, 50);
        panel_6.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_3_1 = new JLabel("Icon 50x50");
        lblNewLabel_3_1.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/DamageBoost.jpg")));
        lblNewLabel_3_1.setBounds(0, 50, 50, 50);
        panel_6.add(lblNewLabel_3_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("Speed 1:                                                                                                    XCoins");
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2.setOpaque(true);
        lblNewLabel_2_2.setForeground(Color.WHITE);
        lblNewLabel_2_2.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_2.setBounds(50, 100, 450, 50);
        panel_6.add(lblNewLabel_2_2);
        
        JLabel lblNewLabel_3_2 = new JLabel("Icon 50x50");
        lblNewLabel_3_2.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/SppedBoost.jpg")));
        lblNewLabel_3_2.setBounds(0, 100, 50, 50);
        panel_6.add(lblNewLabel_3_2);
        
        JLabel lblNewLabel_2_3 = new JLabel("Health 2:                                                                                                    XCoins");
        lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3.setOpaque(true);
        lblNewLabel_2_3.setForeground(Color.WHITE);
        lblNewLabel_2_3.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_3.setBounds(50, 150, 450, 50);
        panel_6.add(lblNewLabel_2_3);
        
        JLabel lblNewLabel_3_3 = new JLabel("Icon 50x50");
        lblNewLabel_3_3.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/Roter_HeilungsTrank.jpeg")));
        lblNewLabel_3_3.setBounds(0, 150, 50, 50);
        panel_6.add(lblNewLabel_3_3);
        
        JLabel lblNewLabel_2_4 = new JLabel("Damage 2:                                                                                                    XCoins");
        lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_4.setOpaque(true);
        lblNewLabel_2_4.setForeground(Color.WHITE);
        lblNewLabel_2_4.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_4.setBounds(50, 200, 450, 50);
        panel_6.add(lblNewLabel_2_4);
        
        JLabel lblNewLabel_3_4 = new JLabel("Icon 50x50");
        lblNewLabel_3_4.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/DamageBoost.jpg")));
        lblNewLabel_3_4.setBounds(0, 200, 50, 50);
        panel_6.add(lblNewLabel_3_4);
        
        JLabel lblNewLabel_2_5 = new JLabel("Speed 2:                                                                                                    XCoins");
        lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_5.setOpaque(true);
        lblNewLabel_2_5.setForeground(Color.WHITE);
        lblNewLabel_2_5.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_5.setBounds(50, 250, 450, 50);
        panel_6.add(lblNewLabel_2_5);
        
        JLabel lblNewLabel_3_5 = new JLabel("Icon 50x50");
        lblNewLabel_3_5.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/SppedBoost.jpg")));
        lblNewLabel_3_5.setBounds(0, 250, 50, 50);
        panel_6.add(lblNewLabel_3_5);
        
        JLabel lblNewLabel_2_6 = new JLabel("Totem 1:                                                                                                    XCoins");
        lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_6.setOpaque(true);
        lblNewLabel_2_6.setForeground(Color.WHITE);
        lblNewLabel_2_6.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_6.setBounds(50, 300, 450, 50);
        panel_6.add(lblNewLabel_2_6);
        
        JLabel lblNewLabel_3_6 = new JLabel("Icon 50x50");
        lblNewLabel_3_6.setBounds(0, 300, 50, 50);
        panel_6.add(lblNewLabel_3_6);
		
		JScrollPane panel_2 = new JScrollPane();
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(126, 78, 5));
		panel_2.setFocusable(false);
		tabbedPane.addTab("Stufe 2", null, panel_2, null);
		
		JPanel panel_7 = new JPanel();
        panel_7.setDoubleBuffered(false);
        panel_7.setFocusTraversalKeysEnabled(false);
        panel_7.setFocusable(false);
        panel_7.setBackground(new Color(126, 78, 5));
        panel_7.setPreferredSize(new Dimension(480, 360));
        panel_2.setViewportView(panel_7);
        panel_7.setLayout(null);
        
        JLabel lblNewLabel_2_7 = new JLabel("Health 1:                                                                                                    XCoins");
        lblNewLabel_2_7.setOpaque(true);
        lblNewLabel_2_7.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_7.setForeground(Color.WHITE);
        lblNewLabel_2_7.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_7.setBounds(50, 0, 450, 50);
        panel_7.add(lblNewLabel_2_7);
        
        JLabel lblNewLabel_3_7 = new JLabel("Icon 50x50");
        lblNewLabel_3_7.setBounds(0, 0, 50, 50);
        panel_7.add(lblNewLabel_3_7);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Damage 1:                                                                                                    XCoins");
        lblNewLabel_2_1_1.setOpaque(true);
        lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_1.setForeground(Color.WHITE);
        lblNewLabel_2_1_1.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_1_1.setBounds(50, 50, 450, 50);
        panel_7.add(lblNewLabel_2_1_1);
        
        JLabel lblNewLabel_3_1_1 = new JLabel("Icon 50x50");
        lblNewLabel_3_1_1.setBounds(0, 50, 50, 50);
        panel_7.add(lblNewLabel_3_1_1);
        
        JLabel lblNewLabel_2_2_1 = new JLabel("Speed 1:                                                                                                    XCoins");
        lblNewLabel_2_2_1.setOpaque(true);
        lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_2_1.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_2_1.setBounds(50, 100, 450, 50);
        panel_7.add(lblNewLabel_2_2_1);
        
        JLabel lblNewLabel_3_2_1 = new JLabel("Icon 50x50");
        lblNewLabel_3_2_1.setBounds(0, 100, 50, 50);
        panel_7.add(lblNewLabel_3_2_1);
        
        JLabel lblNewLabel_2_3_1 = new JLabel("Health 2:                                                                                                    XCoins");
        lblNewLabel_2_3_1.setOpaque(true);
        lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3_1.setForeground(Color.WHITE);
        lblNewLabel_2_3_1.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_3_1.setBounds(50, 150, 450, 50);
        panel_7.add(lblNewLabel_2_3_1);
        
        JLabel lblNewLabel_3_3_1 = new JLabel("Icon 50x50");
        lblNewLabel_3_3_1.setBounds(0, 150, 50, 50);
        panel_7.add(lblNewLabel_3_3_1);
        
        JLabel lblNewLabel_2_4_1 = new JLabel("Damage 2:                                                                                                    XCoins");
        lblNewLabel_2_4_1.setOpaque(true);
        lblNewLabel_2_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_4_1.setForeground(Color.WHITE);
        lblNewLabel_2_4_1.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_4_1.setBounds(50, 200, 450, 50);
        panel_7.add(lblNewLabel_2_4_1);
        
        JLabel lblNewLabel_3_4_1 = new JLabel("Icon 50x50");
        lblNewLabel_3_4_1.setBounds(0, 200, 50, 50);
        panel_7.add(lblNewLabel_3_4_1);
        
        JLabel lblNewLabel_2_5_1 = new JLabel("Speed 2:                                                                                                    XCoins");
        lblNewLabel_2_5_1.setOpaque(true);
        lblNewLabel_2_5_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_5_1.setForeground(Color.WHITE);
        lblNewLabel_2_5_1.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_5_1.setBounds(50, 250, 450, 50);
        panel_7.add(lblNewLabel_2_5_1);
        
        JLabel lblNewLabel_3_5_1 = new JLabel("Icon 50x50");
        lblNewLabel_3_5_1.setBounds(0, 250, 50, 50);
        panel_7.add(lblNewLabel_3_5_1);
        
        JLabel lblNewLabel_2_6_1 = new JLabel("Totem 1:                                                                                                    XCoins");
        lblNewLabel_2_6_1.setOpaque(true);
        lblNewLabel_2_6_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_6_1.setForeground(Color.WHITE);
        lblNewLabel_2_6_1.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_6_1.setBounds(50, 300, 450, 50);
        panel_7.add(lblNewLabel_2_6_1);
        
        JLabel lblNewLabel_3_6_1 = new JLabel("Icon 50x50");
        lblNewLabel_3_6_1.setBounds(0, 300, 50, 50);
        panel_7.add(lblNewLabel_3_6_1);
		
		JScrollPane panel_3 = new JScrollPane();
		panel_3.setBackground(new Color(126, 78, 5));
		panel_3.setBorder(null);
		tabbedPane.addTab("Stufe 3", null, panel_3, null);
		
		JPanel panel_8 = new JPanel();
        panel_8.setDoubleBuffered(false);
        panel_8.setFocusTraversalKeysEnabled(false);
        panel_8.setFocusable(false);
        panel_8.setBackground(new Color(126, 78, 5));
        panel_8.setPreferredSize(new Dimension(480, 360)); // Adjust size as needed
        panel_8.setLayout(null);
        panel_3.setViewportView(panel_8);
        
        JLabel lblNewLabel_2_8 = new JLabel("Health 1:                                                                                                    XCoins");
        lblNewLabel_2_8.setOpaque(true);
        lblNewLabel_2_8.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_8.setForeground(Color.WHITE);
        lblNewLabel_2_8.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_8.setBounds(50, 0, 450, 50);
        panel_8.add(lblNewLabel_2_8);
        
        JLabel lblNewLabel_3_8 = new JLabel("Icon 50x50");
        lblNewLabel_3_8.setBounds(0, 0, 50, 50);
        panel_8.add(lblNewLabel_3_8);
        
        JLabel lblNewLabel_2_1_2 = new JLabel("Damage 1:                                                                                                    XCoins");
        lblNewLabel_2_1_2.setOpaque(true);
        lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_2.setForeground(Color.WHITE);
        lblNewLabel_2_1_2.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_1_2.setBounds(50, 50, 450, 50);
        panel_8.add(lblNewLabel_2_1_2);
        
        JLabel lblNewLabel_3_1_2 = new JLabel("Icon 50x50");
        lblNewLabel_3_1_2.setBounds(0, 50, 50, 50);
        panel_8.add(lblNewLabel_3_1_2);
        
        JLabel lblNewLabel_2_2_2 = new JLabel("Speed 1:                                                                                                    XCoins");
        lblNewLabel_2_2_2.setOpaque(true);
        lblNewLabel_2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2_2.setForeground(Color.WHITE);
        lblNewLabel_2_2_2.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_2_2.setBounds(50, 100, 450, 50);
        panel_8.add(lblNewLabel_2_2_2);
        
        JLabel lblNewLabel_3_2_2 = new JLabel("Icon 50x50");
        lblNewLabel_3_2_2.setBounds(0, 100, 50, 50);
        panel_8.add(lblNewLabel_3_2_2);
        
        JLabel lblNewLabel_2_3_2 = new JLabel("Health 2:                                                                                                    XCoins");
        lblNewLabel_2_3_2.setOpaque(true);
        lblNewLabel_2_3_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3_2.setForeground(Color.WHITE);
        lblNewLabel_2_3_2.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_3_2.setBounds(50, 150, 450, 50);
        panel_8.add(lblNewLabel_2_3_2);
        
        JLabel lblNewLabel_3_3_2 = new JLabel("Icon 50x50");
        lblNewLabel_3_3_2.setBounds(0, 150, 50, 50);
        panel_8.add(lblNewLabel_3_3_2);
        
        JLabel lblNewLabel_2_4_2 = new JLabel("Damage 2:                                                                                                    XCoins");
        lblNewLabel_2_4_2.setOpaque(true);
        lblNewLabel_2_4_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_4_2.setForeground(Color.WHITE);
        lblNewLabel_2_4_2.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_4_2.setBounds(50, 200, 450, 50);
        panel_8.add(lblNewLabel_2_4_2);
        
        JLabel lblNewLabel_3_4_2 = new JLabel("Icon 50x50");
        lblNewLabel_3_4_2.setBounds(0, 200, 50, 50);
        panel_8.add(lblNewLabel_3_4_2);
        
        JLabel lblNewLabel_2_5_2 = new JLabel("Speed 2:                                                                                                    XCoins");
        lblNewLabel_2_5_2.setOpaque(true);
        lblNewLabel_2_5_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_5_2.setForeground(Color.WHITE);
        lblNewLabel_2_5_2.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_5_2.setBounds(50, 250, 450, 50);
        panel_8.add(lblNewLabel_2_5_2);
        
        JLabel lblNewLabel_3_5_2 = new JLabel("Icon 50x50");
        lblNewLabel_3_5_2.setBounds(0, 250, 50, 50);
        panel_8.add(lblNewLabel_3_5_2);
        
        JLabel lblNewLabel_2_6_2 = new JLabel("Totem 1:                                                                                                    XCoins");
        lblNewLabel_2_6_2.setOpaque(true);
        lblNewLabel_2_6_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_6_2.setForeground(Color.WHITE);
        lblNewLabel_2_6_2.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_6_2.setBounds(50, 300, 450, 50);
        panel_8.add(lblNewLabel_2_6_2);
        
        JLabel lblNewLabel_3_6_2 = new JLabel("Icon 50x50");
        lblNewLabel_3_6_2.setBounds(0, 300, 50, 50);
        panel_8.add(lblNewLabel_3_6_2);
		
		JScrollPane panel_4 = new JScrollPane();
		panel_4.setBackground(new Color(126, 78, 5));
		panel_4.setBorder(null);
		tabbedPane.addTab("Stufe 4", null, panel_4, null);
		
		JPanel panel_9 = new JPanel();
        panel_9.setDoubleBuffered(false);
        panel_9.setFocusTraversalKeysEnabled(false);
        panel_9.setFocusable(false);
        panel_9.setBackground(new Color(126, 78, 5));
        panel_9.setPreferredSize(new Dimension(480, 360)); // Adjust size as needed
        panel_9.setLayout(null);
        panel_4.setViewportView(panel_9);
        
        JLabel lblNewLabel_2_9 = new JLabel("Health 1:                                                                                                    XCoins");
        lblNewLabel_2_9.setOpaque(true);
        lblNewLabel_2_9.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_9.setForeground(Color.WHITE);
        lblNewLabel_2_9.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_9.setBounds(50, 0, 450, 50);
        panel_9.add(lblNewLabel_2_9);
        
        JLabel lblNewLabel_3_9 = new JLabel("Icon 50x50");
        lblNewLabel_3_9.setBounds(0, 0, 50, 50);
        panel_9.add(lblNewLabel_3_9);
        
        JLabel lblNewLabel_2_1_3 = new JLabel("Damage 1:                                                                                                    XCoins");
        lblNewLabel_2_1_3.setOpaque(true);
        lblNewLabel_2_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_3.setForeground(Color.WHITE);
        lblNewLabel_2_1_3.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_1_3.setBounds(50, 50, 450, 50);
        panel_9.add(lblNewLabel_2_1_3);
        
        JLabel lblNewLabel_3_1_3 = new JLabel("Icon 50x50");
        lblNewLabel_3_1_3.setBounds(0, 50, 50, 50);
        panel_9.add(lblNewLabel_3_1_3);
        
        JLabel lblNewLabel_2_2_3 = new JLabel("Speed 1:                                                                                                    XCoins");
        lblNewLabel_2_2_3.setOpaque(true);
        lblNewLabel_2_2_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2_3.setForeground(Color.WHITE);
        lblNewLabel_2_2_3.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_2_3.setBounds(50, 100, 450, 50);
        panel_9.add(lblNewLabel_2_2_3);
        
        JLabel lblNewLabel_3_2_3 = new JLabel("Icon 50x50");
        lblNewLabel_3_2_3.setBounds(0, 100, 50, 50);
        panel_9.add(lblNewLabel_3_2_3);
        
        JLabel lblNewLabel_2_3_3 = new JLabel("Health 2:                                                                                                    XCoins");
        lblNewLabel_2_3_3.setOpaque(true);
        lblNewLabel_2_3_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3_3.setForeground(Color.WHITE);
        lblNewLabel_2_3_3.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_3_3.setBounds(50, 150, 450, 50);
        panel_9.add(lblNewLabel_2_3_3);
        
        JLabel lblNewLabel_3_3_3 = new JLabel("Icon 50x50");
        lblNewLabel_3_3_3.setBounds(0, 150, 50, 50);
        panel_9.add(lblNewLabel_3_3_3);
        
        JLabel lblNewLabel_2_4_3 = new JLabel("Damage 2:                                                                                                    XCoins");
        lblNewLabel_2_4_3.setOpaque(true);
        lblNewLabel_2_4_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_4_3.setForeground(Color.WHITE);
        lblNewLabel_2_4_3.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_4_3.setBounds(50, 200, 450, 50);
        panel_9.add(lblNewLabel_2_4_3);
        
        JLabel lblNewLabel_3_4_3 = new JLabel("Icon 50x50");
        lblNewLabel_3_4_3.setBounds(0, 200, 50, 50);
        panel_9.add(lblNewLabel_3_4_3);
        
        JLabel lblNewLabel_2_5_3 = new JLabel("Speed 2:                                                                                                    XCoins");
        lblNewLabel_2_5_3.setOpaque(true);
        lblNewLabel_2_5_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_5_3.setForeground(Color.WHITE);
        lblNewLabel_2_5_3.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_5_3.setBounds(50, 250, 450, 50);
        panel_9.add(lblNewLabel_2_5_3);
        
        JLabel lblNewLabel_3_5_3 = new JLabel("Icon 50x50");
        lblNewLabel_3_5_3.setBounds(0, 250, 50, 50);
        panel_9.add(lblNewLabel_3_5_3);
        
        JLabel lblNewLabel_2_6_3 = new JLabel("Totem 1:                                                                                                    XCoins");
        lblNewLabel_2_6_3.setOpaque(true);
        lblNewLabel_2_6_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_6_3.setForeground(Color.WHITE);
        lblNewLabel_2_6_3.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_6_3.setBounds(50, 300, 450, 50);
        panel_9.add(lblNewLabel_2_6_3);
        
        JLabel lblNewLabel_3_6_3 = new JLabel("Icon 50x50");
        lblNewLabel_3_6_3.setBounds(0, 300, 50, 50);
        panel_9.add(lblNewLabel_3_6_3);
		
		JScrollPane panel_5 = new JScrollPane();
		panel_5.setBackground(new Color(126, 78, 5));
		panel_5.setBorder(null);
		tabbedPane.addTab("Stufe 5", null, panel_5, null);
		
		JPanel panel_10 = new JPanel();
        panel_10.setDoubleBuffered(false);
        panel_10.setFocusTraversalKeysEnabled(false);
        panel_10.setFocusable(false);
        panel_10.setBackground(new Color(126, 78, 5));
        panel_10.setPreferredSize(new Dimension(480, 360)); // Adjust size as needed
        panel_10.setLayout(null);
        panel_5.setViewportView(panel_10);
        
        JLabel lblNewLabel_2_10 = new JLabel("Health 1:                                                                                                    XCoins");
        lblNewLabel_2_10.setOpaque(true);
        lblNewLabel_2_10.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_10.setForeground(Color.WHITE);
        lblNewLabel_2_10.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_10.setBounds(50, 0, 450, 50);
        panel_10.add(lblNewLabel_2_10);
        
        JLabel lblNewLabel_3_10 = new JLabel("Icon 50x50");
        lblNewLabel_3_10.setBounds(0, 0, 50, 50);
        panel_10.add(lblNewLabel_3_10);
        
        JLabel lblNewLabel_2_1_4 = new JLabel("Damage 1:                                                                                                    XCoins");
        lblNewLabel_2_1_4.setOpaque(true);
        lblNewLabel_2_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1_4.setForeground(Color.WHITE);
        lblNewLabel_2_1_4.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_1_4.setBounds(50, 50, 450, 50);
        panel_10.add(lblNewLabel_2_1_4);
        
        JLabel lblNewLabel_3_1_4 = new JLabel("Icon 50x50");
        lblNewLabel_3_1_4.setBounds(0, 50, 50, 50);
        panel_10.add(lblNewLabel_3_1_4);
        
        JLabel lblNewLabel_2_2_4 = new JLabel("Speed 1:                                                                                                    XCoins");
        lblNewLabel_2_2_4.setOpaque(true);
        lblNewLabel_2_2_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2_4.setForeground(Color.WHITE);
        lblNewLabel_2_2_4.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_2_4.setBounds(50, 100, 450, 50);
        panel_10.add(lblNewLabel_2_2_4);
        
        JLabel lblNewLabel_3_2_4 = new JLabel("Icon 50x50");
        lblNewLabel_3_2_4.setBounds(0, 100, 50, 50);
        panel_10.add(lblNewLabel_3_2_4);
        
        JLabel lblNewLabel_2_3_4 = new JLabel("Health 2:                                                                                                    XCoins");
        lblNewLabel_2_3_4.setOpaque(true);
        lblNewLabel_2_3_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3_4.setForeground(Color.WHITE);
        lblNewLabel_2_3_4.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_3_4.setBounds(50, 150, 450, 50);
        panel_10.add(lblNewLabel_2_3_4);
        
        JLabel lblNewLabel_3_3_4 = new JLabel("Icon 50x50");
        lblNewLabel_3_3_4.setBounds(0, 150, 50, 50);
        panel_10.add(lblNewLabel_3_3_4);
        
        JLabel lblNewLabel_2_4_4 = new JLabel("Damage 2:                                                                                                    XCoins");
        lblNewLabel_2_4_4.setOpaque(true);
        lblNewLabel_2_4_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_4_4.setForeground(Color.WHITE);
        lblNewLabel_2_4_4.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_4_4.setBounds(50, 200, 450, 50);
        panel_10.add(lblNewLabel_2_4_4);
        
        JLabel lblNewLabel_3_4_4 = new JLabel("Icon 50x50");
        lblNewLabel_3_4_4.setBounds(0, 200, 50, 50);
        panel_10.add(lblNewLabel_3_4_4);
        
        JLabel lblNewLabel_2_5_4 = new JLabel("Speed 2:                                                                                                    XCoins");
        lblNewLabel_2_5_4.setOpaque(true);
        lblNewLabel_2_5_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_5_4.setForeground(Color.WHITE);
        lblNewLabel_2_5_4.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_5_4.setBounds(50, 250, 450, 50);
        panel_10.add(lblNewLabel_2_5_4);
        
        JLabel lblNewLabel_3_5_4 = new JLabel("Icon 50x50");
        lblNewLabel_3_5_4.setBounds(0, 250, 50, 50);
        panel_10.add(lblNewLabel_3_5_4);
        
        JLabel lblNewLabel_2_6_4 = new JLabel("Totem 1:                                                                                                    XCoins");
        lblNewLabel_2_6_4.setOpaque(true);
        lblNewLabel_2_6_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_6_4.setForeground(Color.WHITE);
        lblNewLabel_2_6_4.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_6_4.setBounds(50, 300, 450, 50);
        panel_10.add(lblNewLabel_2_6_4);
        
        JLabel lblNewLabel_3_6_4 = new JLabel("Icon 50x50");
        lblNewLabel_3_6_4.setBounds(0, 300, 50, 50);
        panel_10.add(lblNewLabel_3_6_4);
		
		tabbedPane.setSelectedIndex(0);
		
		JLabel lbMerchant = new JLabel("");
		lbMerchant.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/Merchant.png")));
		lbMerchant.setBounds(90, 152, 81, 100);
		panel.add(lbMerchant);
		
		JLabel lbSpeechBubbleText = new JLabel("<html><div style='text-align: center;'> Schau dir meine Waren an!<br> Es ist nur das beste vom Besten. <br>Quelle: Vertrau mir</div></html>");
		lbSpeechBubbleText.setHorizontalAlignment(SwingConstants.CENTER);
		lbSpeechBubbleText.setBounds(147, 57, 214, 65);
		panel.add(lbSpeechBubbleText);
		
		JLabel lbSpeechBubble = new JLabel("");
		lbSpeechBubble.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/SpeechBubble.png")));
		lbSpeechBubble.setBounds(115, 25, 261, 150);
		panel.add(lbSpeechBubble);
		
		JButton btnBuySelected = new JButton("");
		btnBuySelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("buy");
			}
		});
		btnBuySelected.setContentAreaFilled(false);
		btnBuySelected.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/BuyButton.png")));
		btnBuySelected.setBorder(null);
		btnBuySelected.setFocusTraversalKeysEnabled(false);
		btnBuySelected.setFocusPainted(false);
		btnBuySelected.setFocusable(false);
		btnBuySelected.setBounds(739, 230, 136, 35);
		panel.add(btnBuySelected);
		
		JButton btnVerkaufen = new JButton("");
		btnVerkaufen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("sell");
			}
		});
		btnVerkaufen.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/SellButton.png")));
		btnVerkaufen.setContentAreaFilled(false);
		btnVerkaufen.setBorder(null);
		btnVerkaufen.setFocusTraversalKeysEnabled(false);
		btnVerkaufen.setFocusPainted(false);
		btnVerkaufen.setFocusable(false);
		btnVerkaufen.setBounds(739, 315, 136, 35);
		panel.add(btnVerkaufen);
		
		JLabel lblNewLabel_1 = new JLabel("Coins: 0000");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(723, 569, 149, 35);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/TableSmall.jpeg")));
		lblNewLabel.setBackground(new Color(125, 67, 2));
		lblNewLabel.setBounds(714, 211, 200, 400);
		panel.add(lblNewLabel);
		JLabel lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon(ShopPotions.class.getResource("/resources/TestBackground.jpeg")));
		lbBackground.setBounds(0, 0, 1200, 800);
		contentPane.add(lbBackground);
	}
}
