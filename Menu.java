/**
 * Menu.java creates the the menu and changes the game's state. Also lets the user use their mouse
 * to navigate the menu.
 * @version 6/21/16
 * @author Luciano de la Iglesia and Jonathan Shen
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;


public class Menu extends MouseAdapter{
   private Game game;
   private Handler handler;
   private Random rand = new Random();
   private LinkedList<Mushroom> mushrooms = new LinkedList<Mushroom>();
   
   //Constructs a new Menu with a given game and handler
   public Menu(Game game, Handler handler){
      this.game = game;
      this.handler = handler;
   }
   
   //Determines which button was pressed with the mouse and executes that button's functionality
   public void mousePressed(MouseEvent e){
      int mx = e.getX();
      int my = e.getY();
      if(game.gameState == Game.STATE.Menu){
         if(mouseOver(mx, my, 210, 150, 200, 64)){
            game.gameState = Game.STATE.Game;  
            if( game.gameState != Game.STATE.Menu){
               Centipede centipede = new Centipede(20, 22, 10, true);
               handler.addObject(centipede);
               for(int i = 0; i <= 90; i++){
                  int x1 = rand.nextInt(Game.WIDTH / 20) * 20;
                  int y1 = rand.nextInt(Game.HEIGHT / 20) * 20;
                  if (y1 > 25 && y1 < 520){
                     Mushroom mushroom = new Mushroom(x1, y1);
                     handler.addObject(mushroom);
                     mushrooms.add(mushroom);
                  }
               }
               handler.addMushroomList(mushrooms);
               int z = Game.WIDTH / 2;
               handler.addObject(new Shooter(z, 620, ID.Shooter));
            }
         }
         if(mouseOver(mx, my, 210, 250, 200, 64)){
            game.gameState = Game.STATE.Help;
         }
         
         if(mouseOver(mx, my, 210, 350, 200, 64)){
            game.gameState = Game.STATE.Credit;
          
         }
         if(mouseOver(mx, my, 210, 450, 200, 64)){
            game.gameState = Game.STATE.About;
         }
         if(mouseOver(mx, my, 210, 550, 200, 64)){
            System.exit(1);
         }
      }
   
   
      if(game.gameState == Game.STATE.About){
         if(mouseOver(mx, my, 210, 500, 200, 64)){
            game.gameState = Game.STATE.Menu;
            return;
         }
      }
      if(game.gameState == Game.STATE.Help){
         if(mouseOver(mx, my,210, 500, 200, 64)){
            game.gameState = Game.STATE.Menu;
            return;
         }
      }
      if(game.gameState == Game.STATE.Credit){
         if(mouseOver(mx, my,210, 500, 200, 64)){
            game.gameState = Game.STATE.Menu;
            return;
         }
      }
   }
   
   //Returns whether the mouse is over a given area
   private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
      if(mx > x && mx < x + width){
         if(my > y && my < y + height){
            return true;
         }
         else 
            return false;
      }
      else 
         return false;
   }
   
   //Does nothing
   public void tick(){}
   
   //Displays a message based on the game's state
   public void render(Graphics g){
      Font fnt = new Font("arial", 1, 50);
      Font fnt2 = new Font("arial", 1, 30);
      Font fnt3 = new Font("arial", 1, 16);
      Font fnt5 = new Font("arial", 1, 12);
      
      g.setColor(Color.green);
      if (game.gameState == Game.STATE.Menu){//Checks the game state
         BufferedImage rooseveltIcon = null;
         try{
            rooseveltIcon = ImageIO.read(new File("RHS.png"));
         } 
         catch (IOException e) {
         }
         BufferedImage RHSlogo = null;
         try{
            RHSlogo = ImageIO.read(new File("RHSlogo.png"));
         } 
         catch (IOException e) {
         }
      
         g.drawString("By Luciano de la Iglesia & Jonathan Shen", 210, 50);
         g.setFont(fnt);
         g.drawString("CENTIPEDE", 170, 40);
         g.drawImage(rooseveltIcon, 312, 55, null);
         g.drawImage(RHSlogo, 205, 55, null);
      
         g.setFont(fnt2);
         g.drawRect(210, 150, 200, 64);
         g.drawString("Play", 270, 190);
      
         g.drawRect(210, 250, 200, 64);
         g.drawString("Help", 270, 290);
      
         g.drawRect(210, 350, 200, 64);
         g.drawString("Credit", 270, 390);
      
         g.drawRect(210, 450, 200, 64);
         g.drawString("About", 270, 490);
         
         g.drawRect(210, 550, 200, 64);
         g.drawString("Quit", 270, 590);
      }
      else if(game.gameState == Game.STATE.Help){ //Checks the game state
         g.setFont(fnt);
         g.drawString("INTEL", 230, 110);
         g.setFont(fnt3);
         g.drawString("--------------------------------------------- START OF MESSAGE ---------------------------------------------------", 2, 140);
         g.drawString("You are the SPACESHIP located at the BOTTOM of the screen", 40, 180);
         g.drawString("Use the ARROW keys to control the movement of the spaceship", 40, 210);
         g.drawString("Press the SPACE BAR to fire a missile", 40, 240);
         g.drawString("You must hit EVERY SEGMENT of the centipede once to eliminate it", 40, 270);
         g.drawString("Eliminate the centipede before it reaches your location.", 40, 300);
         g.drawString("You will be DESTROYED if the centipede HITS you.", 40, 330);
         g.drawString("Good Luck, Soldier.", 40, 410);
         g.drawString("--------------------------------------------- END OF MESSAGE -----------------------------------------------------", 2, 440);
         g.setFont(fnt2);
         g.drawString("Back", 270, 545);
         g.drawRect(210, 500, 200, 64);
      }
      else if(game.gameState == Game.STATE.About){//Checks the game state
         g.setFont(fnt);
         g.drawString("THE MORE YOU KNOW", 25, 50);
         g.setFont(fnt3);
         g.drawString("*CHALLENGE:", 20, 70);
         g.drawString("At first, we tried to make our game in a Drawing Panel,", 50, 100);
         g.drawString("but we soon realized we would need a JFrame for the animations.", 50, 120);
         g.drawString("We had to learn how to use a JFrame, which was challenging,", 50, 140);
         g.drawString("and then we had to change all our code.", 50, 160);
         g.drawString("One of the most difficult parts of this project was figuring out", 50, 180);
         g.drawString("how to make cenetipede to move like the orginal game did.", 50, 200);
         g.drawString("Another difficult part was figuring out how to make the", 50, 220);
         g.drawString("missiles and centipede interact.", 50, 240);
         g.setFont(fnt3);
         g.drawString("--------------------------------------------- *TEAM ANALYSIS* -----------------------------------------------------", 2, 260);
         g.drawString("PROBLEM SOLVING:",20,290);
         g.drawString(">>Luciano de la Iglesia:",280,290);
         g.drawString(">>Jonathan Anthony Shen:", 280, 320);
         g.drawString(">>Shlok Wagle:", 280, 350);
         g.drawString("EXCELLENT",500,290);
         g.drawString("GOOD", 500, 320);
         g.drawString("TO BE DETERMINE", 450, 350);
         g.drawString("*COMMUNICATION SKILLS*", 20, 380);
         g.drawString(">>Luciano de la Iglesia:",20,410);
         g.drawString(">>Jonathan Shen:", 20, 440);
         g.drawString(">>Shlok Wagle:", 20, 470);
         g.drawString("EXCELLENT", 210, 410);
         g.drawString("EXCELLENT", 210, 440);
         g.drawString("POOR", 210, 470);
         g.drawString("*QUANTITY OF CODE*", 360, 380);
         g.drawString(">>Luciano de la Iglesia:",330,410);
         g.drawString(">>Jonathan Shen:", 330, 440);
         g.drawString(">>Shlok Wagle:", 330, 470);
         g.drawString("50%", 530, 410);
         g.drawString("40%", 530, 440);
         g.drawString("10%", 530, 470);
         g.setFont(fnt2);
         g.drawString("Back", 270, 545);
         g.drawRect(210, 500, 200, 64);
      }
      else if(game.gameState == Game.STATE.Credit){//Check the game state
         g.setFont(fnt);
         g.drawString("Credit", 240, 110);
         g.setFont(fnt3);
         g.drawString("ROOSEVELT HIGH SCHOOL. AP COMPUTER SCIENCE", 110, 150);
         g.setFont(fnt3);
         g.drawString("Project Brainstorm: ", 110, 180);
         g.drawString("Jonathan Shen", 300, 180);
         g.drawString("Probelm Solver:", 110, 210);
         g.drawString("Luciano de la Iglesia", 300, 210);
         g.drawString("Poster:", 110, 240);
         g.drawString("Luciano de la Iglesia & Jonathan Shen", 300, 240);
         g.drawString("Project Outline:", 110, 270);
         g.drawString("Luciano de la Iglesia & Jonathan Shen", 300,270);
         g.setFont(fnt3);
         g.drawString("INSTRUCTOR: ", 110, 300);
         g.drawString("Ashley Myers", 300, 300);
         g.drawString("Code create with the intrustion of RealTutsGML", 110, 330);
         g.drawString("Game inspire by Centipede publish by Atari, Atari, Inc. 1980", 110, 360);
         g.setFont(fnt2);
         g.drawString("Back", 270, 545);
         g.drawRect(210, 500, 200, 64);
         g.setFont(fnt3);
         g.drawString("@All Game code belongs to Luciano de la Iglesia and Jonathan Shen", 60, 620);     
      }
      else if(game.gameState == Game.STATE.End){//Checks the game state
         g.setFont(fnt);
         g.drawString("MISSION OVER", 25, 50);
         g.setFont(fnt2);
      }
   }
}