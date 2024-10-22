package mypong;
import java.awt.*;
import java.awt.event.*;
import static mypong.Score.GAME_WIDTH;

public class Paddles extends Rectangle{

    int id;
    int yVelocity;
    int speed = 10;
	
    Paddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;
    }
	
public void keyPressedp1(KeyEvent e) {
		
    if(e.getKeyCode()==KeyEvent.VK_W) {
        setYDirection(-speed);
    }
    if(e.getKeyCode()==KeyEvent.VK_S) {
        setYDirection(speed);
    }
			
}
public void keyPressedp2(KeyEvent e) {
		
    if(e.getKeyCode()==KeyEvent.VK_UP) {
        setYDirection(-speed);
    }
    if(e.getKeyCode()==KeyEvent.VK_DOWN) {
        setYDirection(speed);
    }
		
}
	
public void keyReleasedp1(KeyEvent e) {
		
    if(e.getKeyCode()==KeyEvent.VK_W) {
        setYDirection(0);
    }
    if(e.getKeyCode()==KeyEvent.VK_S) {
        setYDirection(0);
    }			
}
public void keyReleasedp2(KeyEvent e) {		
    if(e.getKeyCode()==KeyEvent.VK_UP) {
        setYDirection(0);
    }
    if(e.getKeyCode()==KeyEvent.VK_DOWN) {
        setYDirection(0);
    }		
}
 public void bot(Ball theBall){
     
     speed=12;
    if( theBall.y > y && theBall.x>GAME_WIDTH/3){
        
       setYDirection(speed);
       move();
       
    }
    else if(theBall.y < y&& theBall.x>GAME_WIDTH/3){
        setYDirection(-speed);
        move();
    }
}
public void setYDirection(int yDirection) {
    yVelocity = yDirection;
}
public void move() {
    y= y + yVelocity;
}
public void draw(Graphics g) {
    if(id==1)
        g.setColor(Color.blue);
    else
        g.setColor(Color.red);
        g.fillRoundRect(x, y, width, height,10,10);
	}
}