import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Table extends JFrame implements ActionListener{
    public Table(){
        panel.add(startButton);
        startButton.addActionListener(this);
        setContentPane(panel);
 
        setSize(400, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
 
    public static void main(String[] args){
        new Table();
    }
 
    private final JButton startButton = new JButton("¿ªÊ¼");
    JPanel panel = new JPanel();
    Marbles[] marbles = new Marbles[10];
    private final int speedMin = 2;
 
    @Override
    public void actionPerformed(ActionEvent e){
        for(int i = 0; i < marbles.length; i++){
            int xDir = (int) (Math.random() * 10 + speedMin);
            int yDir = (int) (Math.random() * 10 + speedMin);
            int startX = (int) (Math.random() * getSize().width);
            int startY = (int) (Math.random() * getSize().height);
            marbles[i] = new Marbles(startX, startY, xDir, yDir, this);
            new Thread(marbles[i]).start();
        }
    }
 
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(int i = 0; i < marbles.length; i++){
            if(marbles[i] != null){
                marbles[i].draw(g);
            }
        }
    }
}