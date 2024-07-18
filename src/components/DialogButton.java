package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import action.Logger;
import game.Dialogs;
import translation.Translation;

public class DialogButton extends JButton{
	private static final long serialVersionUID = 1L;
	String text;
	private String[] dialogStrings;
	private String[] currentStrings;
	private int currentDialogPoint = -1;
	
	public DialogButton(byte skip) {
		init(skip);
	}
	
	private void init(byte skip){
		setBorderPainted(true); 
        setFocusPainted(false);
        setFocusable(false);
        
		if(skip == 0) {
			text = Translation.get("components.dialogButton.next");
		}else {
			text = Translation.get("components.dialogButton.skip");
		}
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(skip == 0) {
					currentDialogPoint++;
				}else {
					
				}
				Logger.logInfo("pressed");
			}
		});
	}
	
 @Override
 	public void paintComponent(Graphics g) {
	 	super.paintComponent(g);
	 	g.setColor(Color.gray.brighter());
	 	g.fillRect(0, 0, getWidth(), getHeight());
	 	g.setColor(Color.black);
	 	g.drawString(text, 5, getHeight()/2+3);
 	}
 
 	public void nextDialog() {
		

	}	
	

	public void setDialogStrings(String dialog) {
		this.dialogStrings = Dialogs.get(dialog);
	}

}
