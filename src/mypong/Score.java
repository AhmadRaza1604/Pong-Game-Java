/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypong;

import java.awt.*;

public class Score extends Rectangle{
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;	
    Score(int GAME_WIDTH, int GAME_HEIGHT){
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
public void draw(Graphics g) {
    g.setColor(Color.ORANGE);
    g.setFont(new Font("Times New Roman",Font.PLAIN,60));
    g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
    g.drawRect(1, 1, GAME_WIDTH-3, GAME_HEIGHT-3);
    g.drawRoundRect(410, 0, 180, 65,15,15);
    g.drawRect(150, 120, 700, 330);
    g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-85, 50);
    g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+20, 50);		            
    }
}