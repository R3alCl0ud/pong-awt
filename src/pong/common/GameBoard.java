package pong.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements ActionListener {

	private static final Color light_blue = new Color(0x59A9FF), blurple = new Color(0x6435C8);

	public class StartBTN extends JButton {

		private static final long serialVersionUID = 1L;

		public StartBTN() {
			super("Start!");
			this.setLocation((800 - 120) / 2, (600 - 40) / 2);
			this.setActionCommand("START");
			this.setBounds((800 - 120) / 2, (600 - 40) / 2, 120, 40);
			this.setSize(new Dimension(120, 40));
			this.setMinimumSize(new Dimension(120, 40));
			this.setPreferredSize(new Dimension(120, 40));
		}

		@Override
		public void paint(Graphics g) {
			return;
		}

		public void paintComponents(Graphics g) {
			this.setLocation((800 - 120) / 2, (600 - 40) / 2);
			if (this.isVisible()) {
				GradientPaint stbtn = new GradientPaint((800 - 120) / 2, (600 - 40) / 2, light_blue, ((800 - 120) / 2) + 120, ((600 - 40) / 2) + 40, blurple);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setPaint(stbtn);
				g2d.fillRoundRect((800 - 120) / 2, (600 - 40) / 2, 120, 40, 30, 50);
				g.setColor(Color.WHITE);
				g.setFont(new Font("Gilroy-Light", 0, 20));
				g.drawString("Start Game!", (800 - 114) / 2, (600 + 15) / 2);
			}
		}
	}

	private static final long serialVersionUID = 3718337806421213371L;
	private int scoreP1, scoreP2, round, win = 1;
	private Ball ball;
	private JLabel rl;
	public Paddle p1, p2;
	public JButton start;

	public GameBoard() {
		super();
		// frame = parent;
		scoreP1 = 0;
		scoreP2 = 0;
		ball = new Ball((800 - 20) / 2, (600 - 20) / 2);
		p1 = new Paddle(1);
		p2 = new Paddle(2);
		rl = new JLabel("Round " + round);
		rl.setForeground(new Color(0xFFFFFF));
		start = new StartBTN();
		start.setText("Start!");
		start.addActionListener(this);
		add(p1);
		add(p2);
		add(rl);
		add(ball);
		add(start);
		setBackground(new Color(0x16161b));
		addKeyListener(p1);
		addKeyListener(p2);
		setFocusable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("START")) {
			start.setVisible(false);
			ball.setVelX(200d * win);
			ball.setVelY(100d);
			round++;
			rl.setText("Round " + round);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0x16161b));
		g.fillRect(0, 0, 800, 600);
		ball.paint(g);
		p1.paint(g);
		p2.paint(g);
		start.paintComponents(g);
		FontMetrics fm = rl.getFontMetrics(rl.getFont());
		// Rectangle2D rd = fm.getStringBounds("Round " + round,
		// rl.getGraphics());
		rl.setFont(new Font("Gilroy-Light", 0, 24));
		rl.setForeground(new Color(0x8a8a8d));
		rl.setLocation((int) (800 - fm.stringWidth("Round " + round)) / 2, 75);
		g.setColor(new Color(0x8a8a8d));
		g.setFont(new Font("Gilroy-Light", 0, 48));
		FontMetrics sm = getFontMetrics(g.getFont());
		g.drawString(":", (800 - sm.stringWidth(":")) / 2, 50);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Verdana", 0, 64));
		sm = getFontMetrics(g.getFont());
		g.drawString(String.format("%02d", scoreP1), (int) ((800 - (130 + sm.stringWidth(String.format("%02d", scoreP1)))) / 2), 60);
		g.drawString(String.format("%02d", scoreP2), (int) (800 + 50) / 2, 60);
	}

	public boolean resetBall() {
		if (ball.getPosX() <= 50 && ball.getVelX() < 0d) {
			if (ball.getPosY() >= p1.getPosY() && ball.getPosY() <= (p1.getPosY() + 80)) {
				ball.setVelX((ball.getVelX() + 10d) * -1d);
			} else {
				win = 1;
				scoreP2++;
				return true;
			}
		} else if (ball.getPosX() >= 730 && ball.getVelX() > 0d) {
			if (ball.getPosY() >= p2.getPosY() && ball.getPosY() <= (p2.getPosY() + 80)) {
				ball.setVelX((ball.getVelX() + 10d) * -1d);
			} else {
				win = -1;
				scoreP1++;
				return true;
			}
		}
		return false;
	}

	public void update(long time) {
		p1.update();
		p2.update();
		ball.updatePosition(time);
		if (resetBall()) {
			ball.reset();
			p1.reset();
			p2.reset();
			start.setVisible(true);
		}
		repaint();
	}
}
