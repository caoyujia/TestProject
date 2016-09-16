import java.awt.Color;
import java.awt.Graphics;
 
public class Marbles implements Runnable{
    public Marbles(){
 
    }
 
    public Marbles(int startx, int starty, int xdir, int ydir, Table table){
        this.table = table;
        this.startX = startx;
        this.startY = starty;
        this.xDir = xdir;
        this.yDir = ydir;
    }
 
    @Override
    public void run(){
        while(true){
            if((startX > table.getSize().width - 25) || (startX < 0)){
                xDir *= -1;
            }
            if((startY > table.getSize().width - 25) || (startY < 0)){
                yDir *= -1;
            }
            startX += xDir;
            startY += yDir;
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
            table.repaint();
        }
 
    }
 
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.fillOval(startX, startY, 30, 30);
        g.setColor(Color.white);
        g.fillOval(startX + 5, startY + 5, 8, 6);
    }
 
    Table table = null;
    int startX, startY;
    int xDir, yDir;
}