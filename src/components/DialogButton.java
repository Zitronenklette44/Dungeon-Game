package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

import action.Logger;
import game.Dialogs;
import gui.GameScreen;
import translation.Translation;

public class DialogButton extends JButton {
	private static final long serialVersionUID = 1L;
	public String text;
	private String[] dialogStrings;
	private char[] currentChars;
	public int currentDialogPoint = -1;
	private int currentCharIndex = 0;
	private JLabel dialogLabel;
	private Timer timer;
	private byte skip;
	public String dialogSource;

	public DialogButton(byte skip, JLabel dialogLabel) {
		this(skip, dialogLabel, Translation.get("dialogs.emptySender"));
	}

	public DialogButton(byte skip, JLabel dialogLabel, String dialogSource) {
		this.dialogLabel = dialogLabel;
		init(skip);
		this.dialogSource = dialogSource;
	}

	private void init(byte skip) {
		setBorderPainted(true);
		setFocusPainted(false);
		setFocusable(false);

		if (skip == 0) {
			text = Translation.get("components.dialogButton.next");
		} else {
			text = Translation.get("components.dialogButton.skip");
		}
		this.skip = skip;

		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pressed();
			}
		});

		// Initialize Timer
		timer = new Timer(50, new ActionListener() { // Adjust delay as needed
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
	}

	public void pressed() {
		if (skip == 0) {
			currentDialogPoint++;
			nextDialog();
		} else {
			// Skip to end of current dialog
			finishDialog();
		}
	}

	public void nextDialog() {
		this.dialogStrings = Dialogs.get(GameScreen.currentDialog);
		currentChars = null;
		if (dialogStrings == null) {
			Logger.logError("Dialog strings are null", new NullPointerException());
			timer.stop();
			return;
		}

		if (currentDialogPoint < dialogStrings.length) {
			currentChars = dialogStrings[currentDialogPoint].toCharArray();
			currentCharIndex = 0;
			dialogLabel.setText(""); // Clear the label
			GameScreen.setSender(dialogSource);
			timer.start();
		} else {
			timer.stop();
			finishDialog();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.gray.brighter());
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawString(text, 5, getHeight() / 2 + 3);
	}

	private void update() {
		if (currentCharIndex < currentChars.length) {
			String newString = "<html>" + dialogLabel.getText().replace("<html>", "").replace("</html>", "")
					+ currentChars[currentCharIndex] + "</html>";
			dialogLabel.setText(newString);
			Logger.logInfo("generated String: " + newString);
			currentCharIndex++;
		} else {
			timer.stop();
		}
	}

	public void setDialogStrings(String dialog) {
		this.dialogStrings = Dialogs.get(dialog);
		if (this.dialogStrings != null) {
		}
		currentDialogPoint = -1;
	}

	public void finishDialog() {
		currentDialogPoint = -1;
		currentCharIndex = 0;
		GameScreen.endDialog();
		timer.stop();
	}

	public void startDialog() {
		currentDialogPoint = 0;
		nextDialog();
	}
}
