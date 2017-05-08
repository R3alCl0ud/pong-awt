package pong.common;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JPanel implements ActionListener {
	
	public class StartBTN extends JButton {
		
		private static final long serialVersionUID = 1L;
		
		public StartBTN() {
			super("Start!");
			this.setLocation((800 - 60) / 2, (600 - 30) / 2);
			this.setActionCommand("START");
		}
		
		public void paintComponents(Graphics g) {
			this.setLocation((800 - 60) / 2, (600 - 30) / 2);
			// if (this.isVisible()) {
			// g.setColor(new Color(0x337ab7));
			// g.fillRect((800 - 60) / 2, (600 - 30) / 2, 60, 30);
			// g.setColor(Color.WHITE);
			// g.drawString("Start!", (800 - 32) / 2, (600 + 10) / 2);
			// }
		}
	}
	
	private static final long serialVersionUID = 3718337806421213371L;
	private int scoreP1, scoreP2, round, win = 1;
	private Ball ball;
	private JLabel s1, s2, rl;
	public Paddle p1, p2;
	// private JFrame frame;
	public JButton start;
	
	public GameBoard() {
		super();
		// frame = parent;
		scoreP1 = 0;
		scoreP2 = 0;
		ball = new Ball((800 - 20) / 2, (600 - 20) / 2);
		p1 = new Paddle(1);
		p2 = new Paddle(2);
		s1 = new JLabel("" + scoreP1);
		s2 = new JLabel("" + scoreP2);
		rl = new JLabel("Round: " + round);
		s1.setForeground(new Color(0xFFFFFF));
		s2.setForeground(new Color(0xFFFFFF));
		rl.setForeground(new Color(0xFFFFFF));
		start = new StartBTN();
		start.setText("Start!");
		start.addActionListener(this);
		add(p1);
		add(p2);
		add(s1);
		add(rl);
		add(s2);
		add(ball);
		add(start);
		setBackground(new Color(0x16161b));
		addKeyListener(p1);
		addKeyListener(p2);
		this.setFocusable(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("START")) {
			start.setVisible(false);
			ball.setVelX(200d * win);
			ball.setVelY(100d);
			round++;
			rl.setText("Round: " + round);
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0x16161b));
		g.fillRect(0, 0, 800, 600);
		// g.setColor(new Color(0x940000));
		// g.fillRect(0, 30, 30, 540);
		// g.fillRect(770, 30, 30, 540);
		// g.setColor(Color.DARK_GRAY);
		// g.fillRect(0, 0, 800, 30);
		// g.fillRect(0, 540, 800, 30);
		// g.setColor(new Color(0x16161b));
		// g.fillRect(30, 30, 740, 510);
		ball.paint(g);
		p1.paint(g);
		p2.paint(g);
		start.paintComponents(g);
		s1.setLocation(10, 10);
		s2.setLocation(800 - 20, 10);
		FontMetrics fm = rl.getFontMetrics(rl.getFont());
		Rectangle2D rd = fm.getStringBounds("Round: " + round, rl.getGraphics());
		rl.setLocation((int) (800 - rd.getWidth()) / 2, 10);
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
			s1.setText("" + scoreP1);
			s2.setText("" + scoreP2);
			ball.reset();
			p1.reset();
			p2.reset();
			start.setVisible(true);
		}
		repaint();
	}
}
