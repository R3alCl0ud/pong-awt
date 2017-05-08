package pong.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class Ball extends Component {

	private static final long serialVersionUID = 7892042067701973015L;
	private double velX, velY;
	private double x, y;
	// private int posX, posY;

	public Ball(int x, int y) {
		velX = 0;
		velY = 0;
		this.x = x;
		this.y = y;
	}

	public void updatePosition(long time) {
		x += velX * (time / 1000D);
		y += velY * (time / 1000D);
		if (y >= 515) velY *= -1d;
		if (y <= 50) velY *= -1d;
	}

	public Color getColor() {
		return Color.black;
	}

	/**
	 * @return the velX
	 */
	public double getVelX() {
		return velX;
	}

	/**
	 * @param velX the velX to set
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}

	/**
	 * @return the velY
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * @param velY the velY to set
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return (int) x;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		x = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return (int) y;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		y = posY;
	}

	public void reset() {
		x = (800 - 20) / 2d;
		y = (600 - 20) / 2d;
		velY = 0;
		velX = 0;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(getPosX(), getPosY() - 10, 20, 20);
	}

}
