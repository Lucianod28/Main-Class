/**
 * KeyInput.java is the control center of the movement of spaceship and also adds Missile objects.
 * The for loop runs through all of the game objects in the list and gets the ID of each object.
 * The ID is need to identify all the objects and control the behavior.
 * The KeyInput class implements KeyAdapter.
 * @version 6/21/16
 * @author  Jonathan Shen
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
   private Handler handler;
   
   //Constructs a new KeyInput with a given Handler
   public KeyInput(Handler handler){
      this.handler = handler;
   }
   //Runs through all the objects on the list and does something based on the ID
   public void keyPressed(KeyEvent e){
      int key = e.getKeyCode();
      for(int i = 0; i < handler.getObject().size(); i++){
         GameObject movingObject = handler.getObject().get(i);
         if(movingObject.getId() == ID.Shooter ){
            if (key == KeyEvent.VK_LEFT){
               movingObject.setmoveX(-5);
            }
            if (key == KeyEvent.VK_RIGHT){
               movingObject.setmoveX(5);
            }
            if (key == KeyEvent.VK_UP){
               movingObject.setmoveY(-5);
            }
            if (key == KeyEvent.VK_DOWN){
               movingObject.setmoveY(5);
            }
            if(key == KeyEvent.VK_SPACE){
               handler.addObject(new Missile(movingObject.getX() - 1, movingObject.getY() - 20));
               if(movingObject.getId() == ID.Missile){
                  movingObject.setmoveY(movingObject.getY()-15);
               }
            }
         }
      }
      if(key == KeyEvent.VK_ESCAPE) System.exit(1);
      
   }
   
   //Says what to do when a key is released
   public void keyReleased(KeyEvent e){
      int key = e.getKeyCode();
      for(int i = 0; i < handler.getObject().size();i++){
         GameObject movingObject = handler.getObject().get(i);
         if(movingObject.getId() == ID.Shooter){
            if (key == KeyEvent.VK_LEFT) {
               movingObject.setmoveX(0);
            }
            if (key == KeyEvent.VK_RIGHT)movingObject.setmoveX(0);
            if (key == KeyEvent.VK_UP)movingObject.setmoveY(0);
            if (key == KeyEvent.VK_DOWN)movingObject.setmoveY(0);
         }
         if (key == KeyEvent.VK_SPACE){
            if(movingObject.getId() == ID.Missile){
            
               movingObject.setmoveY(movingObject.getY()-15);
               
            }
         }
      }
   }
}