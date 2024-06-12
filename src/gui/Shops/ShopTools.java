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

public class ShopTools extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static ShopTools frame;

	/**
	 * Launch the application.
	 */
	public static void erstellen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ShopTools();
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
	public ShopTools() {
		setResizable(false);
		setAlwaysOnTop(true);
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
        
        JLabel lblNewLabel_2 = new JLabel("Sword 1:                                                                                                    XCoins");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBackground(new Color(0, 0, 0,150));
        lblNewLabel_2.setOpaque(true);
        lblNewLabel_2.setBounds(50, 0, 450, 50);
        panel_6.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Icon 50x50");
        lblNewLabel_3.setBounds(0, 0, 50, 50);
        panel_6.add(lblNewLabel_3);
        
        JLabel lblNewLabel_2_1 = new JLabel("Armor 1:                                                                                                    XCoins");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setOpaque(true);
        lblNewLabel_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_1.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_1.setBounds(50, 50, 450, 50);
        panel_6.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_3_1 = new JLabel("Icon 50x50");
        lblNewLabel_3_1.setBounds(0, 50, 50, 50);
        panel_6.add(lblNewLabel_3_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("Boots 1:                                                                                                    XCoins");
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2.setOpaque(true);
        lblNewLabel_2_2.setForeground(Color.WHITE);
        lblNewLabel_2_2.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_2.setBounds(50, 100, 450, 50);
        panel_6.add(lblNewLabel_2_2);
        
        JLabel lblNewLabel_3_2 = new JLabel("Icon 50x50");
        lblNewLabel_3_2.setBounds(0, 100, 50, 50);
        panel_6.add(lblNewLabel_3_2);
        
        JLabel lblNewLabel_2_3 = new JLabel("Sword 2:                                                                                                    XCoins");
        lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3.setOpaque(true);
        lblNewLabel_2_3.setForeground(Color.WHITE);
        lblNewLabel_2_3.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_3.setBounds(50, 150, 450, 50);
        panel_6.add(lblNewLabel_2_3);
        
        JLabel lblNewLabel_3_3 = new JLabel("Icon 50x50");
        lblNewLabel_3_3.setBounds(0, 150, 50, 50);
        panel_6.add(lblNewLabel_3_3);
        
        JLabel lblNewLabel_2_4 = new JLabel("Armor 2:                                                                                                    XCoins");
        lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_4.setOpaque(true);
        lblNewLabel_2_4.setForeground(Color.WHITE);
        lblNewLabel_2_4.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_4.setBounds(50, 200, 450, 50);
        panel_6.add(lblNewLabel_2_4);
        
        JLabel lblNewLabel_3_4 = new JLabel("Icon 50x50");
        lblNewLabel_3_4.setBounds(0, 200, 50, 50);
        panel_6.add(lblNewLabel_3_4);
        
        JLabel lblNewLabel_2_5 = new JLabel("Boots 2:                                                                                                    XCoins");
        lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_5.setOpaque(true);
        lblNewLabel_2_5.setForeground(Color.WHITE);
        lblNewLabel_2_5.setBackground(new Color(0, 0, 0, 150));
        lblNewLabel_2_5.setBounds(50, 250, 450, 50);
        panel_6.add(lblNewLabel_2_5);
        
        JLabel lblNewLabel_3_5 = new JLabel("Icon 50x50");
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
        panel_7.setPreferredSize(new Dimension(480, 360)); // Adjust size as needed
        panel_7.setLayout(null);
        panel_2.setViewportView(panel_7);
		
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
		
		tabbedPane.setSelectedIndex(0);
		
		JLabel lbMerchant = new JLabel("");
		lbMerchant.setIcon(new ImageIcon(ShopTools.class.getResource("/resources/Merchant.png")));
		lbMerchant.setBounds(90, 152, 81, 100);
		panel.add(lbMerchant);
		
		JLabel lbSpeechBubbleText = new JLabel("<html><div style='text-align: center;'> Schau dir meine Waren an!<br> Es ist nur das beste vom Besten. <br>Quelle: Vertrau mir</div></html>");
		lbSpeechBubbleText.setHorizontalAlignment(SwingConstants.CENTER);
		lbSpeechBubbleText.setBounds(147, 57, 214, 65);
		panel.add(lbSpeechBubbleText);
		
		JLabel lbSpeechBubble = new JLabel("");
		lbSpeechBubble.setIcon(new ImageIcon(ShopTools.class.getResource("/resources/SpeechBubble.png")));
		lbSpeechBubble.setBounds(115, 25, 261, 150);
		panel.add(lbSpeechBubble);
		
		JButton btnBuySelected = new JButton("");
		btnBuySelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("buy");
			}
		});
		btnBuySelected.setContentAreaFilled(false);
		btnBuySelected.setIcon(new ImageIcon(ShopTools.class.getResource("/resources/BuyButton.png")));
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
		btnVerkaufen.setIcon(new ImageIcon(ShopTools.class.getResource("/resources/SellButton.png")));
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
		lblNewLabel.setIcon(new ImageIcon(ShopTools.class.getResource("/resources/TableSmall.jpeg")));
		lblNewLabel.setBackground(new Color(125, 67, 2));
		lblNewLabel.setBounds(714, 211, 200, 400);
		panel.add(lblNewLabel);
		JLabel lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon(ShopTools.class.getResource("/resources/TestBackground.jpeg")));
		lbBackground.setBounds(0, 0, 1200, 800);
		contentPane.add(lbBackground);
	}
}
