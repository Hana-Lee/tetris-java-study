package kr.co.leehana.tetris_study;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Hana Lee
 * @since 2015-11-06 17:24
 */
public class KeyController implements KeyListener {

	private App game;
	private boolean left, right, down, rotate, pause;

	public KeyController(App game) {
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String keyText = KeyEvent.getKeyText(e.getKeyCode());
		System.out.println(keyText + " : " + e.getKeyCode() + " : " + e.getKeyChar());
		if (keyText.equals(Config.getLeft())) {
			left = true;
		} else if (keyText.equals(Config.getRight())) {
			right = true;
		} else if (keyText.equals(Config.getRotate())) {
			rotate = true;
		} else if (keyText.equals(Config.getDown())) {
			down = true;
		} else if (keyText.equals(Config.getPause())) {
			pause = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String keyText = KeyEvent.getKeyText(e.getKeyCode());
		System.out.println(keyText + " : " + e.getKeyCode() + " : " + e.getKeyChar());
		if (keyText.equals(Config.getLeft())) {
			left = false;
		} else if (keyText.equals(Config.getRight())) {
			right = false;
		} else if (keyText.equals(Config.getRotate())) {
			rotate = false;
		} else if (keyText.equals(Config.getDown())) {
			down = false;
		} else if (keyText.equals(Config.getPause())) {
			pause = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isRotate() {
		return rotate;
	}

	public boolean isPause() {
		return pause;
	}
}
