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
public class TextCollisionBall {// 操作运用线程实现碰撞的球的类
    public static void main(String args[]) {// java程序主入口处
        new BallFrame();// 实例化对象
    }
}
class BallFrame  extends JFrame implements ActionListener, ChangeListener {
    private static final long serialVersionUID = 1L;
    JPanel panel;// 面板
    JPanel preview;// 预览面板
    JSlider red;// 红色滑块
    JSlider green;// 绿色滑块
    JSlider blue;// 蓝色滑块
    JSlider JS_SIZE;// 滑块大小
    int x = 45, y = 45;// 方位
    int BALL_SIZE = 30;// 球的大小
    public BallFrame() {// 构造方法进行初始化
        super("collision ball");
        panel = new JPanel();// 创建面板
        panel.setBounds(20, 0, 450, 200);// 设置面板的位置以及大小
        panel.setBackground(Color.WHITE);// 面析背景色为白色
        preview = new JPanel();// 创建预览球的面板
        preview.setBounds(350, 220, 120, 120);
        preview.setBackground(Color.white);
        JTextField status = new JTextField("请选择球的颜色、大小然后点击按钮");// 设置状态栏
        status.setBounds(1, 404, 492, 20);// 设置状态栏的位置以及大小
        status.setEditable(false);// 初始不能编辑
        // 创建红、绿、蓝三个标签
        JLabel redLabel = new JLabel("红");// 创建红色标签
        redLabel.setBounds(20, 215, 30, 20);// 设置红标签的位置以及大小
        JLabel greenLabel = new JLabel("绿");// 创建绿色标签
        greenLabel.setBounds(20, 260, 30, 20);// 设置绿标签的位置以及大小
        JLabel blueLabel = new JLabel("蓝");// 创建蓝色标签
        blueLabel.setBounds(20, 305, 30, 20);// 设置蓝标签的位置以及大小
        JLabel sizeLabel = new JLabel("大小");// 创建大小标签
        sizeLabel.setBounds(20, 350, 30, 20);// 设置大小标签的位置以及大小
        red = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127);// 创建红色滑块
        red.setBounds(50, 210, 250, 45);// 设置滑块的位置以及大小
        red.putClientProperty("JSlider.isFilled", Boolean.TRUE);// 填充滑块
        red.setPaintTicks(true);// 绘制勾号标记
        red.setMajorTickSpacing(50);// 主要的勾号标记大小
        red.setMinorTickSpacing(25);// 次要的勾号标记大小
        red.setPaintLabels(true);// 显示主要刻度的数字标记
        red.addChangeListener(this);// 添加监听者
        blue = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127);// 创建蓝色滑块
        blue.setBounds(50, 300, 250, 45);
        blue.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        blue.setPaintTicks(true);
        blue.setMajorTickSpacing(50);
        blue.setMinorTickSpacing(25);
        blue.setPaintLabels(true);
        blue.addChangeListener(this);
        green = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 127);// 创建绿色滑块
        green.setBounds(50, 255, 250, 45);
        green.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        green.setPaintTicks(true);
        green.setMajorTickSpacing(50);
        green.setMinorTickSpacing(25);
        green.setPaintLabels(true);
        green.addChangeListener(this);
        JS_SIZE = new JSlider(SwingConstants.HORIZONTAL, 10, 50, 30);// 创建球大小的滑块
        JS_SIZE.setBounds(50, 345, 250, 45);
        JS_SIZE.putClientProperty("JSlider.isFilled", Boolean.TRUE);
        JS_SIZE.setPaintTicks(true);
        JS_SIZE.setMajorTickSpacing(10);
        JS_SIZE.setMinorTickSpacing(5);
        JS_SIZE.setPaintLabels(true);
        JS_SIZE.addChangeListener(this);
        JButton jb = new JButton("注入球");// 创建球按钮点击
        jb.setBounds(350, 360, 120, 30);
        jb.addActionListener(this);
        Container c = this.getContentPane();// 将所有对象添加到窗体
        c.setLayout(null);// 布局置空(不使用布局)
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
        this.setBounds(100, 50, 500, 450);// 设置窗体的位置和大小
        this.setResizable(false);
        this.setVisible(true);// 可视
        this.addWindowListener(new WindowAdapter() {// 对窗口添加监听事件
            public void windowClosing(WindowEvent e) {// 当窗口关闭时
                System.exit(0);// 安全退出
            }
        });
    }
    public void actionPerformed(ActionEvent e) {// 实现ActionListener接口的方法
        Color ball_color = new Color(red.getValue(), green.getValue(),
                blue.getValue());// 获取当前球的颜色
        RightBall r = new RightBall(panel, JS_SIZE.getValue(), ball_color);// 实例化右边的球对象
        r.start();// 启动线程
        LeftBall ball = new LeftBall(panel, JS_SIZE.getValue(), ball_color);// 实例化左边球对象
        ball.start();// 启动线程
    }
    public void stateChanged(ChangeEvent e) {// 实现ChangeListener接口的方法
        Graphics g = preview.getGraphics();// 获得preview的画笔并在预览面板的中心画个圆
        g.setColor(Color.white);// 图形为白色
        g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// 填充外接指定矩形框的圆
        x = 60 - JS_SIZE.getValue() / 2;
        y = 60 - JS_SIZE.getValue() / 2;
        BALL_SIZE = JS_SIZE.getValue();
        g.setColor(new Color(red.getValue(), green.getValue(), blue.getValue()));
        g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// 填充外接指定矩形框的圆
        g.dispose();// 显示
    }
}
class LeftBall extends Thread {// 继承Thread类实现从窗口左面出现的球
    JPanel LEFTPANEL;// 从窗口左面出现的球的面板
    int BALL_SIZE;// 球的大小
    Color BALL_COLOR;// 球的颜色
    public LeftBall(JPanel panel, int size, Color color) {// 构造方法进行初始化
        this.LEFTPANEL = panel;// 获得画板的句柄
        this.BALL_SIZE = size;// 获得球的大小
        this.BALL_COLOR = color;// 获得球的颜色
    }
    public void run() {// 继承Thread类实现的方法
        Graphics g = LEFTPANEL.getGraphics();// 获得图形
        int x = 0, y = 0;
        int LEFT_X = 450 - BALL_SIZE;// 计算画球时X轴最大坐标
        int LEFT_Y = 200 - BALL_SIZE;// 计算画球时Y轴的最大坐标
        int x_increase = 5, y_increase = 5;// 球移动的增量
        while (true) {// 循环移动球
            g.setColor(Color.white);// 将上一次画的球擦掉
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// 填充外接指定矩形框的圆
            g.setColor(BALL_COLOR);// 设置球的颜色
            x = x + x_increase;// 球每次X轴的位置
            y = y + y_increase;// 球每次Y轴的位置
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// 填充外接指定矩形框的圆
            if (x <= 0 || x >= LEFT_X)// 判断球是否到达了边界,若到达了则转向
                x_increase = -x_increase;
            if (y <= 0 || y >= LEFT_Y)
                y_increase = -y_increase;
            try {
                Thread.sleep(30);// 休眠一段时间
            } catch (Exception e) {// 捕获异常
            }
        }
    }
}
class RightBall extends Thread {// 继承Thread类实现从窗口右面出现的球
    JPanel RIGHTPANEL;// 从窗口右面出现的球的面板
    int BALL_SIZE;// 球的大小
    Color BALL_COLOR;// 球的颜色
    public RightBall(JPanel panel, int size, Color color) {// 构造方法进行初始化
        this.RIGHTPANEL = panel;// 获得画板的句柄
        this.BALL_SIZE = size;// 获得球的大小
        this.BALL_COLOR = color;// 获得球的颜色
    }
    public void run() {
        Graphics g = RIGHTPANEL.getGraphics();// 获得图形s
        int x = 450 - BALL_SIZE, y = 0;
        int RIGHT_X = x;
        int RIGHT_Y = 200 - BALL_SIZE;
        int x_increase = -5, y_increase = 5;// 球移动的增量
        while (true) {// 循环移动球
            g.setColor(Color.white);// 将上一次画的球擦掉
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// 填充外接指定矩形框的圆
            g.setColor(BALL_COLOR);// 设置球的颜色
            x = x + x_increase;// 球每次X轴的位置
            y = y + y_increase;// 球每次Y轴的位置
            g.fillOval(x, y, BALL_SIZE, BALL_SIZE);// 填充外接指定矩形框的圆
            if (x <= 0 || x >= RIGHT_X)// 判断球是否到达了边界,若到达了则转向
                x_increase = -x_increase;
            if (y <= 0 || y >= RIGHT_Y)
                y_increase = -y_increase;
            try {
                Thread.sleep(60);// 休眠一段时间
            } catch (Exception e) {// 捕获异常
            }
        }
    }
}
