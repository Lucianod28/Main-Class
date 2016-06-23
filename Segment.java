/**
 * Segment.java is the blueprint for a Segment object. Each Segment stores an x and y coordinate
 * @version 6/21/16
 * @author Luciano de la Iglesia
 */

import java.awt.*;

public class Segment{
   private int x, y;
   
   //Creates a new Segment at the given x and y coordinates
   public Segment(int x, int y){
      this.x = x;
      this.y = y;
   }
   
   //Throws an exception if a given number is negative
   private void negative(int z){
      if (z < 0){
         throw new IllegalArgumentException("Negative Coordinate");
      }
   }
   
   //Sets the x coordinate to x if x is positive
   public void setX(int x){
      negative(x);
      this.x = x;
   }
   
   //Changes the x-coordinate by two in the given direction
   public void tickX(int direction){
      if (direction == 1){
         x += 2;
      }
      else{
         x -= 2;
      }
   }
   
   //Increments the y-coordinate by 2
   public void incrementY(){
      y += 2;
   }
   
   //Sets the y-coordinate to the given number if it's positive
   public void setY(int y){
      negative(y);
      this.y = y;
   }
   
   //Returns the x-coordinate
   public int getX(){
      return x;
   }
   
   //Returns the x-coordinate
   public int getY(){
      return y;
   }
   
   //Draws the segment using a given Graphics object
   public void render(Graphics g){
      g.setColor(Color.green);
      g.fillOval(x, y, 20, 15);
   }
}