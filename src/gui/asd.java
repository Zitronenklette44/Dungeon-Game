package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

public class asd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					asd frame = new asd();
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
	public asd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		tabbedPane.setPreferredSize(new Dimension(20, 5));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabbedPane.setBorder(null);
		tabbedPane.setFocusTraversalKeysEnabled(false);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setOpaque(true);
		tabbedPane.setFocusable(false);
		tabbedPane.setBackground(new Color(240, 240, 240));
		
		tabbedPane.setBounds(213, 211, 500, 400);
		panel.add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Stufe 1", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setFocusable(false);
		tabbedPane.addTab("Stufe 2", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Stufe 3", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Stufe 4", null, panel_4, null);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Stufe 5", null, panel_5, null);
		panel_5.setLayout(null);
		
		tabbedPane.setSelectedIndex(0);
		
		JLabel lbMerchant = new JLabel("New label");
		lbMerchant.setIcon(new ImageIcon(asd.class.getResource("/resources/Merchant.png")));
		lbMerchant.setBounds(90, 152, 81, 100);
		panel.add(lbMerchant);
		
		JLabel lbSpeechBubbleText = new JLabel("<html><div style='text-align: center;'> Schau dir meine Waren an!<br> Es ist nur das beste vom Besten. <br>Quelle: Vertrau mir</div></html>");
		lbSpeechBubbleText.setHorizontalAlignment(SwingConstants.CENTER);
		lbSpeechBubbleText.setBounds(147, 57, 214, 65);
		panel.add(lbSpeechBubbleText);
		
		JLabel lbSpeechBubble = new JLabel("");
		lbSpeechBubble.setIcon(new ImageIcon(asd.class.getResource("/resources/SpeechBubble.png")));
		lbSpeechBubble.setBounds(115, 25, 261, 150);
		panel.add(lbSpeechBubble);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(125, 67, 2));
		lblNewLabel.setBounds(714, 211, 200, 400);
		panel.add(lblNewLabel);
		JLabel lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon(asd.class.getResource("/resources/TestBackground.jpeg")));
		lbBackground.setBounds(0, 0, 1200, 800);
		contentPane.add(lbBackground);
	}
}
