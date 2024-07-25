package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import inventory.Inventory;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

public class InventoryTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void erstellen(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryTest frame = new InventoryTest();
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
	public InventoryTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Inventory lbtest = new Inventory(40);
		lbtest.setBounds(0, 0, 1184, 761);
		contentPane.add(lbtest);
		
		JLabel lbBackground = new JLabel("");
		lbBackground.setIcon(new ImageIcon(InventoryTest.class.getResource("/resources/rooms/backgrounds/Feld.png")));
		lbBackground.setBounds(0, 0, 1184, 761);
		contentPane.add(lbBackground);
	}
}
