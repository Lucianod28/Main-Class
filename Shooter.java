/**
 * Shooter.java is the blueprint for the shooter, or player. It create a player at the given x and
 * y coordinates.
 * @version 6/21/16
 * @author Jonathan Shen
 */

import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Shooter extends GameObject{
   //Constructs a new Shooter given x and y coordinates and an id
   public Shooter (int x, int y, ID id){
      super(x, y, id);
   
   }
   
   //Moves the Shooter around within a limit
   public void tick(){
      x += moveX;
      y += moveY;
      
      x = Game.border(x, 0, Game.WIDTH - 32);
      y = Game.border(y, 528, Game.HEIGHT - 53);
   }
   
   //Displays the spaceship png
   public void render(Graphics g){
      BufferedImage spaceshipPic = null;
      try{
         spaceshipPic = ImageIO.read(new File("SpaceShip.png"));
      } 
      catch (IOException e) {
      }
      g.drawImage(spaceshipPic, x, y, null);
   }
}