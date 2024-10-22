package mypong;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Panel extends JPanel implements Runnable{
	
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddles paddle1;
    Paddles paddle2;
    Ball ball;
    Score score;
    String Win= "Win.wav";
    String Hit = "hit.wav";
    String Point = "point.wav";
    Sound sound;
    int gameStatus;
    int winner=0;
    boolean running = false;
        
Panel()
{
    this.gameStatus = 0;
    newPaddles();
    newBall();
    score = new Score(GAME_WIDTH,GAME_HEIGHT);
    this.setFocusable(true);
    this.addKeyListener(new AL());
    this.setPreferredSize(SCREEN_SIZE);
    sound = new Sound();
    gameThread = new Thread(this);
    
}
        
	
public void newBall() {
    random = new Random();
    ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
}
public void newPaddles() {
    paddle1 = new Paddles(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
    paddle2 = new Paddles(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
}
public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    Graphics2D g3 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
    if (gameStatus == 0)
    {                
        g2.setColor(Color.orange);
        g2.setFont(new Font("Times New Roman", 1, 50));
        g3.drawString("PONG++", GAME_WIDTH / 2 - 110, 50);
                        
        g2.setFont(new Font("Times New Roman", 1, 30));
        g2.drawString("Press Space to Play Two Player", GAME_WIDTH/2 - 200, GAME_HEIGHT/2 - 25);
        g2.drawString("Press Enter to Play Against Bot", GAME_WIDTH / 2 - 200, GAME_HEIGHT / 2 + 35);
        g2.drawString("Press Escape to Exit", GAME_WIDTH / 2 - 130, GAME_HEIGHT / 2 + 100);
       
        g3.drawRoundRect(0, 0, GAME_WIDTH, 70, 15, 15);
        g3.drawRoundRect(290, 220, 415, 50, 15, 15);
        g3.drawRoundRect(290, 280, 415, 50, 15, 15);
        g3.drawRoundRect(360, 340, 280, 50, 15, 15);
    }
    else if(gameStatus == 1 || gameStatus == 2)
    {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
	draw(graphics);
        g.drawImage(image,0,0,this);
    }
                
    else if (gameStatus == 3)
    {              
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g3.setColor(Color.BLACK);
        g3.fillRoundRect(0, 0, GAME_WIDTH, 70, 15, 15);
        g2.setColor(Color.GREEN);
        g2.drawRoundRect(0, 0, GAME_WIDTH, 70, 15, 15);
        g2.setFont(new Font("Times New Roman", 1, 50));
        g2.drawString("PONG++", GAME_WIDTH / 2 - 85, 50);
			
        g2.setFont(new Font("Times New Roman", 1, 80));
        if(winner==1)
        {
                        
            g2.setColor(Color.BLUE);
            g2.drawString("BLUE WON!!", GAME_WIDTH/2 - 230, GAME_HEIGHT/2 - 25);
        }
        else
        {
            g2.setColor(Color.RED);
            g2.drawString("RED WON!!", GAME_WIDTH/2 - 230, GAME_HEIGHT/2 - 25);
        }
        g3.setColor(Color.BLACK);
        g3.fillRoundRect(GAME_WIDTH/3-40, GAME_HEIGHT-120, 390, 50, 15, 15);
        g3.fillRoundRect(GAME_WIDTH/3+20, GAME_HEIGHT-60, 275, 50, 15, 15);
        g2.setColor(Color.GREEN);
        g2.drawRoundRect(GAME_WIDTH/3-40, GAME_HEIGHT-120, 390, 50, 15, 15);
        g2.drawRoundRect(GAME_WIDTH/3+20, GAME_HEIGHT-60, 275, 50, 15, 15);
        g2.setFont(new Font("Times New Roman", 1, 30));
        g2.drawString("Press SHIFT For Main Menu", GAME_WIDTH/3-32, GAME_HEIGHT-85);
        g2.drawString("Press Escape to Exit", GAME_WIDTH/3+30, GAME_HEIGHT-25);
    }
}
public void draw(Graphics g) {
           
    paddle1.draw(g);
    paddle2.draw(g);
    ball.draw(g);
    score.draw(g);
    Toolkit.getDefaultToolkit().sync(); 
}
public void move() {
    if(gameStatus == 1)
    {
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    else if (gameStatus == 2)
    {
        paddle1.move();
        paddle2.bot(ball);
        ball.move();
    }
}
public void checkCollision() {
    if(ball.y <=0) {
        ball.setYDirection(-ball.yVelocity);
        sound.setFile(Hit);
    }
    if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
        ball.setYDirection(-ball.yVelocity);
        sound.setFile(Hit);
    }
		//bounce ball off paddles
   if(ball.intersects(paddle1)) {
       ball.xVelocity = Math.abs(ball.xVelocity);
       ball.xVelocity++; //optional for more difficulty
       sound.setFile(Hit);
       
       if(ball.yVelocity>0)
       {
           ball.yVelocity++; //optional for more difficulty
       }
       else
           ball.yVelocity--;
       ball.setXDirection(ball.xVelocity);
       ball.setYDirection(ball.yVelocity);
   }
   if(ball.intersects(paddle2)) {
       ball.xVelocity = Math.abs(ball.xVelocity);
       ball.xVelocity++; //optional for more difficulty
       sound.setFile(Hit);                                     
       if(ball.yVelocity>0)
           ball.yVelocity++; 
       else
           ball.yVelocity--;
       ball.setXDirection(-ball.xVelocity);
       ball.setYDirection(ball.yVelocity);
}
		//stops paddles at window edges
    if(paddle1.y<=0)
        paddle1.y=0;
    if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
        paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
    if(paddle2.y<=0)
        paddle2.y=0;
    if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
        paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;        
                

    if(ball.x <=0) {
        score.player2++;                        
        sound.setFile(Point);
        if(score.player2 == 10)
        {
            gameStatus = 3;
            sound.setFile(Win);
            winner = 2;
            score.player2=0;
            score.player1=0;
        }         
	newPaddles();
        newBall();
//			System.out.println("Player 2: "+score.player2);
}
    if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            sound.setFile(Point);
            if(score.player1 == 10)
            {                           
                gameStatus = 3;
                winner = 1;
                sound.setFile(Win); 
                score.player1=0;
                score.player2=0;
            }        
        newPaddles();
        newBall();
//			System.out.println("Player 1: "+score.player1);
}
}
@Override
public void run() {
//game loop
if (gameStatus==1 || gameStatus==2 )
{
    long lastTime = System.nanoTime();
    double amountOfTicks =30.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    running = true;
    while (running) {
        long now = System.nanoTime();
        delta += (now -lastTime)/ns;
        lastTime = now;
        if(delta >=1) {
            move();
            checkCollision();
            repaint();
            delta--;
        }
    }
}
}
        
public class AL extends KeyAdapter{
@Override
public void keyPressed(KeyEvent e) 
{
    if(e.getKeyCode()== KeyEvent.VK_SPACE)
    {
        try{
            gameStatus = 1;
            gameThread.start();
            }
        catch(Exception d)
            {
                
            }
        
    }
    paddle1.keyPressedp1(e);
    paddle2.keyPressedp2(e);
        
    if(e.getKeyCode() == KeyEvent.VK_ENTER)
    {
        try{
            gameStatus = 2;
            gameThread.start();
            paddle1.keyPressedp1(e);
            paddle2.bot(ball);        
            }
            catch(Exception d)
            {
                
            }
        }
        
    if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
    {               
        System.exit(0);
    }
    if (gameStatus == 3){    
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {               
            System.exit(0);
        }    
        }
    if (gameStatus == 3)
    {
        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            gameStatus = 0;
        }        
    if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
    {
        System.exit(0);
    }
    }

    }
@Override
public void keyReleased(KeyEvent e) 
{
    if(gameStatus==1)
    {
        paddle1.keyReleasedp1(e);
	paddle2.keyReleasedp2(e);
    }
    else if(gameStatus==2)
    {
        paddle1.keyReleasedp1(e);
    }
}
}
}