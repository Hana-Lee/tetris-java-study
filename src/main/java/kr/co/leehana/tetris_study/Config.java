package kr.co.leehana.tetris_study;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hana Lee
 * @since 2015-11-03 19-06
 */
public class Config {

	private static String rotate = "Up", left = "Left", right = "Right", down = "Down", pause = "P";
	private static List<Choice> choices;

	static {
		choices = new ArrayList<>();
	}

	public static void openConfig(JFrame frame) {
		final JFrame options = new JFrame("Options");
		options.setSize(400, 300);
		options.setResizable(false);
		options.setLocationRelativeTo(frame);
		options.setLayout(null);

		final Choice left = addChoice("Left", options, 30, 30);
		left.select(Config.left);

		final Choice right = addChoice("Right", options, 150, 30);
		right.select(Config.right);

		final Choice down = addChoice("Down", options, 30, 80);
		down.select(Config.down);

		final Choice rotate = addChoice("Rotate", options, 150, 80);
		rotate.select(Config.rotate);

		final Choice pause = addChoice("Pause", options, 30, 130);
		pause.select(Config.pause);

		final JButton saveButton = new JButton("Save");
		saveButton.setBounds(150, 220, 100, 30);
		saveButton.addActionListener((e) -> {
			options.dispose();
			saveChanges();
		});

		options.add(saveButton);

		options.setVisible(true);
	}

	public static void saveChanges() {
		Config.left = choices.get(0).getSelectedItem();
		Config.right = choices.get(1).getSelectedItem();
		Config.down = choices.get(2).getSelectedItem();
		Config.rotate = choices.get(3).getSelectedItem();
		Config.pause = choices.get(4).getSelectedItem();
	}

	public static Choice addChoice(String name, JFrame options, int x, int y) {
		final JLabel label = new JLabel(name);
		label.setBounds(x, y - 20, 100, 20);
		final Choice key = new Choice();
		getKeyNames().forEach(key::add);

		key.setBounds(x, y, 100, 20);
		options.add(key);
		options.add(label);
		choices.add(key);

		return key;
	}

	public static List<String> getKeyNames() {
		List<String> result = new ArrayList<>();

		for (String key : KeyGetter.getKeyNames()) {
			result.add(key);
			if (key.equalsIgnoreCase("F24")) {
				break;
			}
		}

		return result;
	}
}
