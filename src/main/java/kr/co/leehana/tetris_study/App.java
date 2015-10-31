package kr.co.leehana.tetris_study;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Hello world!
 *
 */
public class App extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 3679586979411270682L;

	public static final int WIDTH = 400, HEIGHT = 565;

	public static void main(String[] args) {
		final JFrame frame = new JFrame("TETRIS");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);

		final App app = new App();
		app.setBounds(0, 25, WIDTH, HEIGHT - 25);
		frame.add(app);

		final JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, WIDTH, 25);

		final JMenu fileMenu = new JMenu("File");
		fileMenu.setBounds(0, 0, 45, 24);

		final JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener((e) -> {
			System.out.println("Staring new game...");
		});

		final JMenuItem highScore = new JMenuItem("High Score");
		highScore.addActionListener((e) -> {
			final int currentHighScore = 0;
			
			final JFrame alert = new JFrame("High Score");
			
			final JLabel scoreLabel = new JLabel("The high score is : " + currentHighScore);
			scoreLabel.setBounds(10, 10, 200, 50);
			alert.add(scoreLabel);
			
			final JButton okBtn = new JButton("OK");
			okBtn.setBounds(50, 80, 100, 30);
			okBtn.addActionListener((okBtnEvent) -> {
				alert.dispose();
			});
			
			alert.add(okBtn);
			
			alert.setSize(200, 150);
			alert.setLayout(null);
			alert.setLocationRelativeTo(null);
			alert.setResizable(false);
			alert.setVisible(true);
		});

		final JMenuItem exitGame = new JMenuItem("Exit");
		exitGame.addActionListener((e) -> {
			System.out.println("Closing");
			System.exit(0);
		});

		fileMenu.add(newGame);
		fileMenu.add(highScore);
		fileMenu.add(exitGame);

		menuBar.add(fileMenu);

		frame.add(menuBar);
		frame.setVisible(true);

		app.gameStart();
	}

	public void gameStart() {
		final Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}

	@Override
	public void run() {
		boolean running = true;
		while (running) {
			update();
			final BufferStrategy bs = getBufferStrategy();
			if (bs == null) {
				createBufferStrategy(3);
				continue;
			}
			final Graphics2D g = (Graphics2D) bs.getDrawGraphics();
			render(g);
			bs.show();
		}
	}

	public void update() {

	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		g.drawString("TETRIS", 170, 50);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
