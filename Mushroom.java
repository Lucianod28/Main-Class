/**
 * Mushroom.java is the blueprint for a Mushroom object, which displays an image of a Mushroom
 * and disappears when it's hit by a Missile.
 * @version 6/21/16
 * @author Jonathan Shen
 */
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Mushroom extends GameObject{
   public Mushroom (int x, int y){
      super(x, y, ID.Mushroom);
   }
   
   //Does nothing because Mushrooms don't move
   public void tick(){}
   
   //Displays the Mushroom png
   public void render(Graphics g){
      BufferedImage mushroomPic = null;
      try{
         mushroomPic = ImageIO.read(new File("Mushroom.png"));
      } 
      catch (IOException e) {
      }
      Random rand = new Random();
    
      g.drawImage(mushroomPic, x, y, null);
   
   }
   
   //Returns the x-coordinate of the Mushroom
   public int getX(){
      return x;
   }
   
   //Returns the y-coordinate of the Mushroom
   public int getY(){
      return y;
   }
}