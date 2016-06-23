/**
 * Game.java is the main class in the program. It contains the game lop that makes the game "tick."
 * It also creates the window for the game using the WIDTH and HEIGHT constants.
 * @version 6/21/16
 * @author Luciano de la Iglesia and Jonathan Shen
 */

import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable{
   public static final int WIDTH = 624;
   public static final int HEIGHT = 663;
   private Thread thread;
   public boolean running = false;
   private Handler handler;
   private Menu menu;
   private HUD hud;
 
   //Creates  the different STATES of the game
   public enum STATE {
      Menu,
      Help,
      Credit,
      About,
      End,
      Game
   };
      
   public String playerName = "";
   
   //Declares the game's state
   public STATE gameState = STATE.Menu;
   public Game(){
      handler = new Handler();
      menu = new Menu(this, handler);
      hud = new HUD(this, handler);
      Scanner console = new Scanner(System.in);
      System.out.print("Player: ");
      String playerName = console.next();
      this.playerName = playerName;
      this.addMouseListener(menu);
      this.addKeyListener(new KeyInput( handler));
      new Window(WIDTH, HEIGHT, "Game Prototype 3", this);
      Random rand = new Random();
      if(gameState == STATE.Game){     
      }
   }
   
   //Starts the game by setting running to true
   public synchronized void start(){ 
      thread = new Thread(this);
      thread.start();
      running = true;
   }
   
   //Stops the game by setting running to false
   public synchronized void stop(){
      try{
         thread.join();
         running = false;
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
   
   //Contains the game loop, which calls the tick method and renders many times per second
   public void run(){
      long lastTime = System.nanoTime();
      int amountOfTicks = 60;
      double ns = 1000000000 / amountOfTicks;
      double delta = 0;
      long timer = System.currentTimeMillis();
      int frames = 0;
      while(running){
         long now = System.nanoTime();
         delta += (now - lastTime) / ns;
         lastTime = now;
         while(delta >= 1){
            tick();
            delta--;
         }
         if(running){
            render();
            frames++;
         }
         if (System.currentTimeMillis() - timer > 1000){
            timer += 1000;
            frames = 0;
         }
      }
      stop();
   }
   
   //Calls the Handler's tick method as well as another tick method based on the game's state
   private void tick(){
      handler.tick();
      
      if(gameState == STATE.Game){
         hud.tick();
      
      }
      if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Credit || gameState == STATE.About || gameState == STATE.End ){//Game Menu(Do not modify yet)
         menu.tick();
      
      }
   
   }
   
   //Checks the game state's and allows each class to display images
   private void render(){
      BufferStrategy bs = this.getBufferStrategy();
      if(bs == null){
         this.createBufferStrategy(3);
         return;
      }
      Graphics g = bs.getDrawGraphics();
      g.setColor(Color.black);
      g.fillRect(0,0, WIDTH, HEIGHT);
      g.setColor(Color.white);
      handler.render(g);
      if(gameState == STATE.Game){
         hud.render(g);
      }
      if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Credit || gameState == STATE.About || gameState == STATE.End){   
         menu.render(g);
      }
      g.dispose();
      bs.show();
   }
   
   //Limits the player's movement
   public static int border(int var, int min, int max){
      if(var >= max)
         return var = max;
      else if(var <= min)
         return var = min;
      else 
         return var;
   }

   //Creates a new Game
   public static void main(String[] args){
      new Game();
   }
}