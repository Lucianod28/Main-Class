/**
 * HUD.java is the class that prints out the player's name and target status.
 * @version 6/21/16
 * @author Jonathan Shen
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;

public class HUD{
   private Game game;
   private Handler handler;
   
   //Constructs a new HUD with a given game and handler
   public HUD (Game game, Handler handler){
      this.game = game;
      this.handler = handler;
   }
   
   //Override tick to allow render to display
   public void tick(){
   }
   
   //Displays player's name and target status
   public void render(Graphics g){
      Font fnt3 = new Font("arial", 1, 18);
      Font fnt4 = new Font("arial", 1, 15);
      
      g.setFont(fnt3);
      g.setColor(Color.green);
      g.drawRect(5, 2, 100, 18);
      g.drawString("Player: "+ game.playerName.toUpperCase(), 120, 18);
      g.drawString("Target status: ", 390, 18);
      g.setFont(fnt4);
      g.drawString(" <<< BACK", 10, 17);
      
      for(int c = 450; c <= 550; c++){
         for(int z = 1; z <= 10; z ++){
            g.setColor(Color.pink);
            g.fillOval(c + 65 , 7, 10, 10);
            c = c + 10;
         }
      }
   }
}