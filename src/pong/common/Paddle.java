package pong.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends Component implements KeyListener {

	private static final long serialVersionUID = 6233023231553274574L;
	private int player, posY = (600 - 80) / 2;
	boolean up, down;

	public Paddle(int p) {
		super();
		player = p;
	}

	public int getPosY() {
		return posY;
	}

	public void reset() {
		posY = (600 - 80) / 2;
	}

	public void update() {
		if (up && posY >= 35) posY -= 4;
		if (down && posY <= 455) posY += 4;
	}

	public void paint(Graphics g) {
		g.setColor(new Color(0x002aab));
		g.fillRect(player == 2 ? 750 : 40, posY, 10, 80);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (player == 1) {
			if (e.getKeyCode() == KeyEvent.VK_UP) up = true;
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
		} else if (player == 2) {
			if (e.getKeyCode() == KeyEvent.VK_W) up = true;
			else if (e.getKeyCode() == KeyEvent.VK_S) down = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (player == 1) {
			if (e.getKeyCode() == KeyEvent.VK_UP) up = false;
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) down = false;
		} else if (player == 2) {
			if (e.getKeyCode() == KeyEvent.VK_W) up = false;
			else if (e.getKeyCode() == KeyEvent.VK_S) down = false;
		}

	}
}
