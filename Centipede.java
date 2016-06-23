/**
 * Centipede.java is the blueprint for a Centipede object. Each Centipede stores an ArrayList of
 * Segments, a boolean indicating its direction, a number indicating the segment that is turning,
 * and a tickNotch, because each Segment must be ticked ten times as it turns. Centipede inherits
 * from GameObject, so it also stores an x and y coordinate.
 * @version 6/21/16
 * @author Luciano de la Iglesia
 */

import java.util.*;
import java.awt.*;

public class Centipede extends GameObject{
   private boolean right;
   private ArrayList<Segment> segments = new ArrayList<Segment>();
   private int segmentTurning;
   private int tickNotch;
   
   //Constructs a new Centipede at the given x and y coordinates
   public Centipede(int x, int y, int length, boolean right){
      super(x, y, ID.Centipede);
      this.right = right;
      if(right){
         for (int i = 0; i < length; i++){
            segments.add(new Segment(x - i * 20, y));
         }
      }
      else{
         for (int i = 0; i < length; i++){
            segments.add(new Segment(x + i * 20, y));
         }
      }
      segmentTurning = 0;
   }

   //Draws the Centipede using the given Graphics object
   public void render(Graphics g){
      g.setColor(Color.green);
      for(int i = 0; i < segments.size(); i++){
         segments.get(i).render(g);
      }
   }
   
   //Moves the Centipede, turning if it hits the edge of the screen
   public void tick(){
      if(segments.size() == 0){
         return;
      }
      int x = segments.get(0).getX();
      if (!right){
         if (x == 0 || tickNotch > 0 || segmentTurning > 0){
            turn(-1);
         }
         else{
            tickX(-1);
         }
      }
      else if (x == 604 || segmentTurning > 0){
         turn(1);
      }
      else{
         tickX(1);
      }
   
   }
   
   //Turns the Centipede around in given direction
   private void turn(int direction){
      segments.get(segmentTurning).incrementY();
      if (tickNotch == 10){
         incrementSegmentTurning();
      }
      incrementTickNotch();
      for (int j = 0; j < segments.size(); j++){
         if (j < segmentTurning){
            segments.get(j).tickX(-direction);
         }
         else if (j > segmentTurning){
            segments.get(j).tickX(direction);
         } 
      }
      if(segmentTurning == 0 && tickNotch == 0){
         tickX(-direction);
         setRight(-direction);
      }
   }

   //Changes the x-coordinate of the Centipede and its Segments by two given a direction
   private void tickX(int direction){
      if (direction == 1){
         x += 2;
      }
      else{
         x -= 2;
      }
      for(int j = 0; j < segments.size(); j++){
         segments.get(j).tickX(direction);
      }
   }
   
   //Increases the y-coordinate of the Centipede and its Segments by 2
   private void incrementY(){
      y += 2;
      for(int j = 0; j < segments.size(); j++){
         segments.get(j).incrementY();
      }
   }

   //Sets whether the Centipede is going right using given boolean
   private void setRight(int right){
      if (right == 1){
         this.right = true;
      }
      else if (right == -1){
         this.right = false;
      }
      else{
         throw new IllegalArgumentException();
      }
   }
   
   //Increments segmentTurning by 1, or if it is on the last segment, returns to 0
   private void incrementSegmentTurning(){
      if (segmentTurning == segments.size() - 1){
         segmentTurning = 0;
      }
      else{
         segmentTurning++;
      }
   }

   //Increments the tickNotch by 1, or if it is on the 10th tick, sets it to 0
   private void incrementTickNotch(){
      if(tickNotch == 10){
         tickNotch = 0;
      }
      else{
         tickNotch++;
      }
   }
   
   //Replaces the Centipede in the Handler with one that has one segment less
   public void removeSegment(Handler handler){
      if(segments.size() == 0){
         System.out.println("YOU WIN!");
      }
      else{
         handler.removeObject(this);
         handler.addObject(new Centipede(segments.get(0).getX(), segments.get(0).getY(),
               segments.size() - 1, right));
      }
      
   }
   
   //Returns the list of segments in the Centipede
   public ArrayList<Segment> getSegments(){
      return segments;
   }
   
   //Returns whether the Centipede is going right
   public boolean getRight(){
      return right;
   }
}