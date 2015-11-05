package kr.co.leehana.tetris_study;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
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

		try {
			saveConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public static void loadConfig() throws IOException {
		File directory = new File(getDefaultSaveDirectory(), "/Tetris");
		if (!directory.exists()) {
			directory.mkdir();
		}
		System.out.println(directory.getPath());
		File configFile = new File(directory, "/config.txt");
		if (!configFile.exists()) {
			configFile.createNewFile();
			System.out.println("Config file not found, saving default config values");
			saveConfig();
			return;
		}

		Scanner s = new Scanner(configFile);
		Map<String, String> values = new HashMap<>();
		while (s.hasNext()) {
			String[] entry = s.nextLine().split(":");
			String key = entry[0];
			String value = entry[1];
			values.put(key, value);
		}

		if (values.size() != 5) {
			System.out.println("Config is unusable, saving default config values");
			saveConfig();
			return;
		}

		if (!values.containsKey("left") ||
				!values.containsKey("right") ||
				!values.containsKey("rotate") ||
				!values.containsKey("down") ||
				!values.containsKey("pause")) {
			System.out.println("Invalid key names in config, saving default config values");
			saveConfig();
			return;
		}

		String left = values.get("left");
		String right = values.get("right");
		String rotate = values.get("rotate");
		String down = values.get("down");
		String pause = values.get("pause");

		if (!(getKeyNames().contains(left) &&
				getKeyNames().contains(right) &&
				getKeyNames().contains(rotate) &&
				getKeyNames().contains(down) &&
				getKeyNames().contains(pause))) {
			System.out.println("Invalid key in config, saving default config values");
			saveConfig();
			return;
		}

		Config.left = left;
		Config.right = right;
		Config.rotate = rotate;
		Config.down = down;
		Config.pause = pause;
	}

	public static void saveConfig() throws IOException {
		File directory = new File(getDefaultSaveDirectory(), "/Tetris");
		if (!directory.exists()) {
			directory.mkdir();
		}
		File configFile = new File(directory, "/config.txt");

		PrintWriter pw = new PrintWriter(configFile);
		pw.println("right:" + right);
		pw.println("left:" + left);
		pw.println("rotate:" + rotate);
		pw.println("down:" + down);
		pw.println("pause:" + pause);
		pw.close();
	}

	public static String getDefaultSaveDirectory() {
		String os = System.getProperty("os.name").toUpperCase();

		if (os.contains("WIN")) {
			return System.getenv("APPDATA");
		}
		if (os.contains("MAC")) {
			return System.getProperty("user.home") + "/Library/Application Support";
		}
		if (os.contains("LINUX")) {
			return System.getProperty("user.home");
		}
		return System.getProperty("user.home");
	}
}
