package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class ShopPotions extends JFrame {

	private static final long serialVersionUID = 1L;
	private  JPanel contentPane;
	private static JComboBox ComboWomboPotionsStufen;
	private static JLabel lbHealth;
	private static JButton btnHealt;
	private static JButton btnTemp4;
	private static JLabel lbDamage;
	private static JButton btnDamage;
	private static JLabel lbSpeed;
	private static JButton btnSpeed;
	private static JButton btnVerlassen;
	private static int DungeonFrei = 0;
	private static JLabel lbStufen;
	private	static JComboBox ComboWomboPotionsWechsel;
	private  static JLabel lbWechsel;


	/**
	 * Launch the application.
	 
	public static void erstellenShopPotions(int DungeonFrei2) {
		DungeonFrei = DungeonFrei2;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopPotions frame = new ShopPotions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ShopPotions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 484);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbTitelShop = new JLabel("");
		lbTitelShop.setBounds(0, 11, 663, 34);
		contentPane.add(lbTitelShop);

		btnTemp4 = new JButton("New button");
		btnTemp4.setBounds(71, 366, 89, 23);
		contentPane.add(btnTemp4);

		btnVerlassen = new JButton("Verlassen");
		btnVerlassen.setBounds(444, 366, 89, 23);
		contentPane.add(btnVerlassen);

		btnHealt = new JButton("Healt");
		btnHealt.setBounds(71, 246, 89, 23);
		contentPane.add(btnHealt);

		btnDamage = new JButton("Damage");
		btnDamage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDamage.setBounds(262, 246, 89, 23);
		contentPane.add(btnDamage);

		btnSpeed = new JButton("Speed");
		btnSpeed.setBounds(444, 246, 89, 23);
		contentPane.add(btnSpeed);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 155, 89, 80);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		lbHealth = new JLabel("Health1.1");
		lbHealth.setHorizontalAlignment(SwingConstants.CENTER);
		lbHealth.setBounds(0, 0, 87, 78);
		panel.add(lbHealth);

		JScrollPane spTemp2 = new JScrollPane();
		spTemp2.setBounds(262, 155, 89, 80);
		contentPane.add(spTemp2);

		JPanel panel_1 = new JPanel();
		spTemp2.setViewportView(panel_1);
		panel_1.setLayout(null);

		lbDamage = new JLabel("Damage1.1");
		lbDamage.setBounds(0, 0, 87, 78);
		panel_1.add(lbDamage);
		lbDamage.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane spTemp3 = new JScrollPane();
		spTemp3.setBounds(444, 155, 89, 80);
		contentPane.add(spTemp3);

		JPanel panel_2 = new JPanel();
		spTemp3.setViewportView(panel_2);
		panel_2.setLayout(null);

		lbSpeed = new JLabel("Speed1.1");
		lbSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lbSpeed.setBounds(0, 0, 87, 78);
		panel_2.add(lbSpeed);

		ComboWomboPotionsStufen = new JComboBox();
		ComboWomboPotionsStufen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboWomboPotionsWechsel.setSelectedIndex(0);
				UpdateShopPotions();
			}
		});
		ComboWomboPotionsStufen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ComboWomboPotionsStufen.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		ComboWomboPotionsStufen.setBounds(10, 89, 66, 40);
		contentPane.add(ComboWomboPotionsStufen);
		
		lbStufen = new JLabel("Stufen");
		lbStufen.setForeground(Color.WHITE);
		lbStufen.setBounds(10, 64, 55, 14);
		contentPane.add(lbStufen);
				
				lbWechsel = new JLabel("Wechsel");
				lbWechsel.setForeground(Color.WHITE);
				lbWechsel.setBounds(587, 64, 55, 14);
				contentPane.add(lbWechsel);
				
				ComboWomboPotionsWechsel = new JComboBox();
				ComboWomboPotionsWechsel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						UpdateShopPotions();
					}
				});
				ComboWomboPotionsWechsel.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
				ComboWomboPotionsWechsel.setFont(new Font("Tahoma", Font.PLAIN, 15));
				ComboWomboPotionsWechsel.setBounds(587, 89, 66, 40);
				contentPane.add(ComboWomboPotionsWechsel);
				
						JLabel lbBackgroundShop = new JLabel("");
						lbBackgroundShop.setBounds(0, 0, 663, 445);
						contentPane.add(lbBackgroundShop);
	}
	private static void UpdateShopPotions() {
	    int ComboWomboIndex = ComboWomboPotionsStufen.getSelectedIndex();
	    int ComboWomboWechsel = ComboWomboPotionsWechsel.getSelectedIndex();
	    System.out.println("Dungeon Frei: " + DungeonFrei);
	    System.out.println("ComboWomboIndex: " + ComboWomboIndex);
	    System.out.println("ComboWomboWechsel: " + ComboWomboWechsel);

	    // Save current selection
	    String currentSelection = (String) ComboWomboPotionsWechsel.getSelectedItem();

	    if (DungeonFrei >= 1 && ComboWomboIndex == 0) {
	        ComboWomboPotionsWechsel.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3"}));
	        switch (ComboWomboWechsel) {
	            case 0:
	                lbHealth.setText("Healt1.1");
	                lbDamage.setText("Damage1.1");
	                lbSpeed.setText("Speed1.1");
	                break;
	            case 1:
	                lbHealth.setText("Healt1.2");
	                lbDamage.setText("Damage1.2");
	                lbSpeed.setText("Speed1.2");
	                break;
	            case 2:
	                lbHealth.setText("Healt1.3");
	                lbDamage.setText("Damage1.3");
	                lbSpeed.setText("Speed1.3");
	                break;
	            default:
	                throw new IllegalArgumentException("Unexpected value: " + ComboWomboWechsel);
	        }
	    } else if (DungeonFrei >= 2 && ComboWomboIndex == 1) {
	        ComboWomboPotionsWechsel.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3"}));
	        switch (ComboWomboWechsel) {
	            case 0:
	                lbHealth.setText("Healt2.1");
	                lbDamage.setText("Damage2.1");
	                lbSpeed.setText("Speed2.1");
	                break;
	            case 1:
	                lbHealth.setText("Healt2.2");
	                lbDamage.setText("Damage2.2");
	                lbSpeed.setText("Speed2.2");
	                break;
	            case 2:
	                lbHealth.setText("Healt2.3");
	                lbDamage.setText("Damage2.3");
	                lbSpeed.setText("Speed2.3");
	                break;
	            default:
	                throw new IllegalArgumentException("Unexpected value: " + ComboWomboWechsel);
	        }
	    } else if (DungeonFrei >= 3 && ComboWomboIndex == 2) {
	        ComboWomboPotionsWechsel.setModel(new DefaultComboBoxModel(new String[]{"1", "2", "3"}));
	        switch (ComboWomboWechsel) {
	            case 0:
	                lbHealth.setText("Healt3.1");
	                lbDamage.setText("Damage3.1");
	                lbSpeed.setText("Speed3.1");
	                break;
	            case 1:
	                lbHealth.setText("Healt3.2");
	                lbDamage.setText("Damage3.2");
	                lbSpeed.setText("Speed3.2");
	                break;
	            case 2:
	                lbHealth.setText("Healt3.3");
	                lbDamage.setText("Damage3.3");
	                lbSpeed.setText("Speed3.3");
	                break;
	            default:
	                throw new IllegalArgumentException("Unexpected value: " + ComboWomboWechsel);
	        }
	    } else if (DungeonFrei >= 4 && ComboWomboIndex == 3) {
	        ComboWomboPotionsWechsel.setModel(new DefaultComboBoxModel(new String[]{"1", "2"}));
	        switch (ComboWomboWechsel) {
	            case 0:
	                lbHealth.setText("Healt4.1");
	                lbDamage.setText("Damage4.1");
	                lbSpeed.setText("Speed4.1");
	                break;
	            case 1:
	                lbHealth.setText("Healt4.2");
	                lbDamage.setText("Damage4.2");
	                lbSpeed.setText("Speed4.2");
	                break;
	            default:
	                throw new IllegalArgumentException("Unexpected value: " + ComboWomboWechsel);
	        }
	    } else if (DungeonFrei >= 5 && ComboWomboIndex == 4) {
	        ComboWomboPotionsWechsel.setModel(new DefaultComboBoxModel(new String[]{"1", "2"}));
	        switch (ComboWomboWechsel) {
	            case 0:
	                lbHealth.setText("Healt5.1");
	                lbDamage.setText("Damage5.1");
	                lbSpeed.setText("Speed5.1");
	                break;
	            case 1:
	                lbHealth.setText("Healt5.2");
	                lbDamage.setText("Damage5.2");
	                lbSpeed.setText("Speed5.2");
	                break;
	            default:
	                throw new IllegalArgumentException("Unexpected value: " + ComboWomboWechsel);
	        }
	    }

	    // Restore previous selection if still valid
	    ComboWomboPotionsWechsel.setSelectedItem(currentSelection);
	}

}
