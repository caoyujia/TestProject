import java.awt.*;
import java.applet.*;

public class Test extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;
	int X, Y, moveX, moveY, width, height;
	Thread newThread;
	Image OffScreen;
	Graphics drawOffscreen;

	public void init() {
		X = 0;
		Y = 0;
		moveX = 10; // ˮƽ�ƶ����ٶ�
		moveY = 15; // ��ֱ�ƶ��ٶ�
		width = getSize().width; // ���ڵĿ��

		System.out.println("width = " + width);
		height = getSize().height;
		System.out.println("height = " + height);

		OffScreen = createImage(width, height); // ��������
		drawOffscreen = OffScreen.getGraphics();
	}

	public void start() {
		newThread = new Thread(this);
		newThread.start();
	}

	public void stop() {
		newThread = null;
	}

	public void paint(Graphics g) {
		drawOffscreen.setColor(Color.black); // ���ñ���ɫ
		drawOffscreen.fillRect(0, 0, width, height); // �����������
		drawOffscreen.setColor(Color.white); // ���������ɫ
		drawOffscreen.fillOval(X, Y, 15, 15); // ����
		g.drawImage(OffScreen, 0, 0, this); // ������
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		while (newThread != null) {
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException E) {
			}

			X = X + moveX;
			Y = Y + moveY;
			if (X >= (width - 15)) {
				X = width - 15;
				moveX = -moveX;
			}
			if (X <= 0) {
				X = 0;
				moveX = -moveX;
			}
			if (Y >= (height - 15)) {
				Y = height - 15;
				moveY = -moveY;
			}
			if (Y <= 0) {
				Y = 0;
				moveY = -moveY;
			}
		}
	}
}