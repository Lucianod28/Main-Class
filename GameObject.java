/**
 * GameObject.java is the superclass of the Mushrooms, Centipede, Shooter, and Missiles. It has
 * the declarations for the variables and methods that all these subclasses will need.
 * @version 6/21/16
 * @author Luciano de la Iglesia
 */

import java.awt.*;
import java.util.*;

public abstract class GameObject{
   protected int x, y;
   protected ID id;
   protected int moveX, moveY;
   protected int specialY;   
   
   //Constructs a new GameObject with a given x and y coordinate and ID
   public GameObject(int x, int y, ID id){
      this.x = x;
      this.y = y;
      this.id = id;
   }
   
   //Requires all subclasses to have a tick method
   public abstract void tick();
   
   //Requires all subclasses to have a render method
   public abstract void render(Graphics g);
   
   //Sets the x coordinate to the given integer
   public void setX(int x){
      this.x = x;
   }
   
   //Sets the y coordinate to the given integer
   public void setY(int y){
      this.y = y;
   }
   
   //Returns the x coordinate
   public int getX(){
      return x;
   }
   
   //Returns the y coordinate
   public int getY(){
      return y;
   }
   
   //Sets the ID
   public void setId(ID id){
      this.id = id;
   }
   
   //Returns the ID
   public ID getId(){
      return id;
   }
   
   //Sets moveX to the given integer
   public void setmoveX(int moveX){
      this.moveX = moveX;
   }
   
   //Sets moveY to the given integer
   public void setmoveY(int moveY){
      this.moveY = moveY;
   }
   
   //Returns moveX
   public int getmoveX(){
      return moveX;
   }
   
   //Returns moveY
   public int getmoveY(){
      return moveY;
   }
}