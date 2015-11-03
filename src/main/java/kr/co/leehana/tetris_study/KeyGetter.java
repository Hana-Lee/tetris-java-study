package kr.co.leehana.tetris_study;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hana Lee
 * @since 2015-11-02 11-53
 */
public class KeyGetter {

	private static Map<String, Integer> keys;
	private static List<String> keyNames;

	public static void loadKeys() {
		keys = new HashMap<>();
		keyNames = new ArrayList<>();
		Field[] fields = KeyEvent.class.getFields();

		for (Field fd : fields) {
			if (Modifier.isStatic(fd.getModifiers())) {
				if (fd.getName().startsWith("VK")) {
					try {
						final int keyNum = fd.getInt(null);
						final String keyName = KeyEvent.getKeyText(keyNum);
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
