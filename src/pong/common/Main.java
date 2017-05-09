package pong.common;

import javax.swing.JFrame;

public class Main {

	public static JFrame window;
	private static Thread drawing;
	private static final long frameTime = 17l;

	public static void main(String... args) {
		window = new JFrame("Pong!");
		window.setSize(800, 600);
		window.setResizable(false);
		window.setVisible(true);
		GameBoard panel = new GameBoard();
		window.add(panel);
		window.validate();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		drawing = new Thread("uwa!! Frames!!") {

			public void run() {
				while (!drawing.isInterrupted()) {
					panel.update(17l);
					try {
						sleep(frameTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		drawing.start();
	}
}
