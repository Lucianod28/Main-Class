/**
 * Handler.java controls all the game objects. All game object are organzie and updated in this
 * class. Stores all the GameObjects in a LinkedList and also stores Missiles, Mushrooms and the
 * Centipede separately
 * @version 6/21/16
 * @author Luciano de la Iglesia
 */

import java.util.*;
import java.awt.*;

public class Handler{
   private LinkedList<GameObject> object = new LinkedList<GameObject>();
   private LinkedList<GameObject> missile = new LinkedList<GameObject>();
   private LinkedList<Mushroom> mushrooms = new LinkedList<Mushroom>();
   private Centipede centipede;
   
   //Changes the Object based on its ID
   public void tick(){
      for(int i = 0; i < object.size(); i++){
         GameObject movingObject = object.get(i);
         if(movingObject.getId() == ID.Centipede){
            centipede = (Centipede) movingObject;
            centipede.tick();
         }
         else if (movingObject.getId() == ID.Missile){
            Missile missile = (Missile) movingObject;
            missile.tick(centipede, this, mushrooms);
         }
         else if(movingObject.getId() == ID.Mushroom){
            Mushroom mushroom = (Mushroom) movingObject;
            mushroom.tick();
         }
         else{
            movingObject.tick();
         }
      }
   }
   
   //Displays the object
   public void render(Graphics g){
      for (int i = 0; i < object.size(); i++){
         GameObject movingObject = object.get(i);
         movingObject.render(g);
      }
      for (int d = 0; d < missile.size(); d++){
         GameObject missileObject = missile.get(d);
         missileObject.render(g);
      }
   }
   
   //Adds an object to the list of GameObjects
   public void addObject(GameObject object){
      this.object.add(object);
   }
   
   //Adds a Missile to the list of Missiles
   public void addMissile(GameObject object){
      this.missile.add(object);
   }
   
   //Removes a given object from the list of GameObjects
   public void removeObject(GameObject object){
      this.object.remove(object);
   }
   
   //Returns the list of GameObjects
   public LinkedList<GameObject> getObject(){//DELETEGDYIFKFFKHGFUFGKFYFKGHFGFGKFKGFGKFKGFKGFGKFGKGF
      return object;
   }
   
   //Adds a list of Mushrooms to the Handler
   public void addMushroomList(LinkedList<Mushroom> mushroom){
      mushrooms = mushroom;
   }
}