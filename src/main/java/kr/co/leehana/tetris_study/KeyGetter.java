package kr.co.leehana.tetris_study;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hana Lee
 * @since 2015-11-02 11-53
 */
public class KeyGetter {

	private static final Map<String, Integer> keys = new HashMap<>();
	private static final List<String> keyNames = new ArrayList<>();
	private static final String alphaPattern = "VK_([A-Z])";

	public static void loadKeys() {
		final String os = System.getProperty("os.name").toUpperCase();
		final Field[] fields = KeyEvent.class.getFields();

		final Pattern pattern = Pattern.compile(alphaPattern);
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers())) {
				String fieldName = field.getName();

				Matcher m = pattern.matcher(fieldName);
				if (fieldName.equals("VK_LEFT")
						|| fieldName.equals("VK_RIGHT")
						|| fieldName.equals("VK_DOWN")
						|| fieldName.equals("VK_UP")
						|| fieldName.equals("VK_SPACE")
						|| m.matches()) {
					try {
						final int keyNum = field.getInt(null);
						String keyName = "";
						if (os.contains("MAC")) {
							keyName = fieldName.replace("VK_", "");
						} else if (os.contains("WIN")) {
							keyName = KeyEvent.getKeyText(keyNum).toUpperCase();
						}

						keys.put(keyName, keyNum);
						keyNames.add(keyName);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static List<String> getKeyNames() {
		return keyNames;
	}

	public static Map<String, Integer> getKeys() {
		return keys;
	}
}
