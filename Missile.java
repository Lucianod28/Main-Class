/**
 * Missile.java is the blueprint for a Missile object, which moves up and destroys Mushrooms and
 * Centipedes
 * @version 6/21/16
 * @author Luciano de la Iglesia and Jonathan Shen
 */

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class Missile extends GameObject{
   //Constructs a new Missile given x and y coordinates
   public Missile (int x, int y){
      super(x, y, ID.Missile);
   }
   
   //Overrides GameObject's tick method
   public void tick(){}
   
   //Moves the Missile up the screen and checks if it hit the centipede or a mushroom
   public void tick(Centipede centipede, Handler handler, LinkedList<Mushroom> mushrooms){
      ArrayList<Segment> segments = centipede.getSegments();
      if (segments.size() > 0){
         boolean sameHeight = segments.get(0).getY() < y + 5 && segments.get(0).getY() > y - 5;
         if (sameHeight && (segments.get(0).getX() + 20 >= x && segments.get(segments.size() - 1)
               .getX() <= x || segments.get(0).getX() <= x && segments.get(segments.size() - 1).
               getX() >= x - 20)){ //Checks if missile is at same x and y coordinates as centipede
            hit(segments, centipede, handler);
            return;
         }
      }
      for (int i = 0; i < mushrooms.size(); i++){
         Mushroom mushroom = mushrooms.get(i);
         boolean sameShroomHeight = mushroom.getY() < y + 5 && mushroom.getY() > y - 5;
         if (sameShroomHeight && mushroom.getX() >= x && mushroom.getX() <= x + 20){
            handler.removeObject(mushroom);
            mushrooms.remove(mushroom);
            handler.removeObject(this);
            return;
         }
      }
      y -= 5;
      if (y < 0){
         handler.removeObject(this);
      }
   }
   
   //Removes the Missile and a segment of the Centipede when they collide
   private void hit(ArrayList<Segment> segments, Centipede centipede, Handler handler){
      if(segments.size() > 0){
         handler.removeObject(this);
         centipede.removeSegment(handler);
      }
      else{
         System.out.println("YOU WIN!");
      }
   }
   
   //Displays the missile png
   public void render(Graphics g){
      BufferedImage HellFire = null;
      try{
         HellFire = ImageIO.read(new File("HellFire.png"));
      }
      catch (IOException e){
      }
      g.drawImage (HellFire, x + 10 , y + 8, null);
   }
}