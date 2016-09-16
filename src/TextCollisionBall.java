import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class TextCollisionBall {// ���������߳�ʵ����ײ�������
    public static void main(String args[]) {// java��������ڴ�
        new BallFrame();// ʵ��������
    }
}
class BallFrame  extends JFrame implements ActionListener, ChangeListener {
    private static final long serialVersionUID = 1L;
    JPanel panel;// ���
    JPanel preview;// Ԥ�����
    JSlider red;// ��ɫ����
    JSlider green;// ��ɫ����
    JSlider blue;// ��ɫ����
    JSlider JS_SIZE;// �����С
    int x = 45, y = 45;// ��λ
    int BALL_SIZE = 30;// ��Ĵ�С
    public BallFrame() {// ���췽�����г�ʼ��
        super("collision ball");
        panel = new JPanel();// �������
        panel.setBounds(20, 0, 450, 200);// ��������λ���Լ���С
        panel.setBackground(Color.WHITE);// ��������ɫΪ��ɫ
        preview = new JPanel();// ����Ԥ��������
        preview.setBounds(350, 220, 120, 120);
        preview.setBackground(Color.white);
        JTextField status = new JTextField("��ѡ�������ɫ����СȻ������ť");// ����״̬��
        status.setBounds(1, 404, 492, 20);// ����״̬����λ���Լ���С
        status.setEditable(false);// ��ʼ���ܱ༭
        // �����졢�̡���������ǩ
        JLabel redLabel = new JLabel("��");// ������ɫ��ǩ
        redLabel.setBounds(20, 215, 30, 20);// ���ú��ǩ��λ���Լ���С
        JLabel greenLabel = new JLabel("��");// ������ɫ��ǩ
        greenLabel.setBounds(20, 260, 30, 20);// �����̱�ǩ��λ���Լ���С
        JLabel blueLabel = new JLabel("��");// ������ɫ��ǩ
        blueLabel.setBounds(20, 305, 30, 20);// ��������ǩ��λ���Լ���С
        JLabel sizeLabel = new JLabel("��С");// ������С��ǩ
        sizeLabel.setBounds(20, 350, 30, 20);// ���ô�С��ǩ��λ���Լ���С
        red = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127);// ������ɫ����
        red.setBounds(50, 210, 250, 45);// ���û����λ���Լ���С
        red.putClientProperty("JSlider.isFilled", Boolean.TRUE);// ��们��
        red.setPaintTicks(true);// ���ƹ��ű��
        red.setMajorTickSpacing(50);// ��Ҫ�Ĺ��ű�Ǵ�С
        red.setMinorTickSpacing(25);// ��Ҫ�Ĺ��ű�Ǵ�С
        red.setPaintLabels(true);// ��ʾ��Ҫ�̶ȵ����ֱ��
        red.addChangeListener(this);// ��Ӽ�����
        blue = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127);// ������ɫ����
        blue.setBounds(50, 300, 250, 45);
        blue.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        blue.setPaintTicks(true);
        blue.setMajorTickSpacing(50);
        blue.setMinorTickSpacing(25);
        blue.setPaintLabels(true);
        blue.addChangeListener(this);
        green = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127);// ������ɫ����
        green.setBounds(50, 255, 250, 45);
        green.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        green.setPaintTicks(true);
        green.setMajorTickSpacing(50);
        green.setMinorTickSpacing(25);
        green.setPaintLabels(true);
        green.addChangeListener(this);
        JS_SIZE = new JSlider(SwingConstants.HORIZONTAL, 10, 50, 30);// �������С�Ļ���
        JS_SIZE.setBounds(50, 345, 250, 45);
        JS_SIZE.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        JS_SIZE.setPaintTicks(true);
        JS_SIZE.setMajorTickSpacing(10);
        JS_SIZE.setMinorTickSpacing(5);
        JS_SIZE.setPaintLabels(true);
        JS_SIZE.addChangeListener(this);
        JButton jb = new JButton("ע����");// ������ť���
        jb.setBounds(350, 360, 120, 30);
        jb.addActionListener(this);
        Container c = this.getContentPane();// �����ж�����ӵ�����
        c.setLayout(null);// �����ÿ�(��ʹ�ò���)
        c.add(panel);
        c.add(preview);
        c.add(redLabel);
        c.add(blueLabel);
        c.add(greenLabel);
        c.add(sizeLabel);
        c.add(red);
        c.add(green);
        c.add(blue);
        c.add(JS_SIZE);
        c.add(jb);
        c.add(status);
        this.setBounds(100, 50, 500, 450);// ���ô����λ�úʹ�С
        this.setResizable(false);
        this.setVisible(true);// ����
        this.addWindowListener(new WindowAdapter() {// �Դ�����Ӽ����¼�
            public void windowClosing(WindowEvent e) {// �����ڹر�ʱ
                System.exit(0);// ��ȫ�˳�
            }
        });
    }
    public void actionPerformed(ActionEvent e) {// ʵ��ActionListener�ӿڵķ���
        Color ball_color = new Color(red.getValue(), green.getValue(),
                blue.getValue());// ��ȡ��ǰ�����ɫ
        RightBall r = new RightBall(panel, JS_SIZE.getValue(), ball_color);// ʵ�����ұߵ������
        r.start();// �����߳�
        LeftBall ball = new LeftBall(panel, JS_SIZE.getValue(), ball_color);// ʵ������������
        ball.start();// �����߳�
    }
    public void stateChanged(ChangeEvent e) {// ʵ��ChangeListener�ӿڵķ���
        Graphics g = preview.getGraphics();// ���preview�Ļ��ʲ���Ԥ���������Ļ���Բ
        g.setColor(Color.white);// ͼ��Ϊ��ɫ
        g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// ������ָ�����ο��Բ
        x = 60 - JS_SIZE.getValue() / 2;
        y = 60 - JS_SIZE.getValue() / 2;
        BALL_SIZE = JS_SIZE.getValue();
        g.setColor(new Color(red.getValue(), green.getValue(), blue.getValue()));
        g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// ������ָ�����ο��Բ
        g.dispose();// ��ʾ
    }
}
class LeftBall extends Thread {// �̳�Thread��ʵ�ִӴ���������ֵ���
    JPanel LEFTPANEL;// �Ӵ���������ֵ�������
    int BALL_SIZE;// ��Ĵ�С
    Color BALL_COLOR;// �����ɫ
    public LeftBall(JPanel panel, int size, Color color) {// ���췽�����г�ʼ��
        this.LEFTPANEL = panel;// ��û���ľ��
        this.BALL_SIZE = size;// �����Ĵ�С
        this.BALL_COLOR = color;// ��������ɫ
    }
    public void run() {// �̳�Thread��ʵ�ֵķ���
        Graphics g = LEFTPANEL.getGraphics();// ���ͼ��
        int x = 0, y = 0;
        int LEFT_X = 450 - BALL_SIZE;// ���㻭��ʱX���������
        int LEFT_Y = 200 - BALL_SIZE;// ���㻭��ʱY����������
        int x_increase = 5, y_increase = 5;// ���ƶ�������
        while (true) {// ѭ���ƶ���
            g.setColor(Color.white);// ����һ�λ��������
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// ������ָ�����ο��Բ
            g.setColor(BALL_COLOR);// ���������ɫ
            x = x + x_increase;// ��ÿ��X���λ��
            y = y + y_increase;// ��ÿ��Y���λ��
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// ������ָ�����ο��Բ
            if (x <= 0 || x >= LEFT_X)// �ж����Ƿ񵽴��˱߽�,����������ת��
                x_increase = -x_increase;
            if (y <= 0 || y >= LEFT_Y)
                y_increase = -y_increase;
            try {
                Thread.sleep(30);// ����һ��ʱ��
            } catch (Exception e) {// �����쳣
            }
        }
    }
}
class RightBall extends Thread {// �̳�Thread��ʵ�ִӴ���������ֵ���
    JPanel RIGHTPANEL;// �Ӵ���������ֵ�������
    int BALL_SIZE;// ��Ĵ�С
    Color BALL_COLOR;// �����ɫ
    public RightBall(JPanel panel, int size, Color color) {// ���췽�����г�ʼ��
        this.RIGHTPANEL = panel;// ��û���ľ��
        this.BALL_SIZE = size;// �����Ĵ�С
        this.BALL_COLOR = color;// ��������ɫ
    }
    public void run() {
        Graphics g = RIGHTPANEL.getGraphics();// ���ͼ��s
        int x = 450 - BALL_SIZE, y = 0;
        int RIGHT_X = x;
        int RIGHT_Y = 200 - BALL_SIZE;
        int x_increase = -5, y_increase = 5;// ���ƶ�������
        while (true) {// ѭ���ƶ���
            g.setColor(Color.white);// ����һ�λ��������
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// ������ָ�����ο��Բ
            g.setColor(BALL_COLOR);// ���������ɫ
            x = x + x_increase;// ��ÿ��X���λ��
            y = y + y_increase;// ��ÿ��Y���λ��
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// ������ָ�����ο��Բ
            if (x <= 0 || x >= RIGHT_X)// �ж����Ƿ񵽴��˱߽�,����������ת��
                x_increase = -x_increase;
            if (y <= 0 || y >= RIGHT_Y)
                y_increase = -y_increase;
            try {
                Thread.sleep(60);// ����һ��ʱ��
            } catch (Exception e) {// �����쳣
            }
        }
    }
}
