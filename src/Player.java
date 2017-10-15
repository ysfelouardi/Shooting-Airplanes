import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import animationBase.Animation;
import animationBase.Sprite;

public class Player{

    public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int dx;
    private int dy;
    private int x;
    private int y;
    private int width;
    private int height;
    int areaWidth;
    String playerState = "STANDING";
    String playerDirection = "RIGHT";
    BufferedImage[] standingLeft ;
    BufferedImage[] standingRight ;
    BufferedImage[] runningRight ;
    BufferedImage[] runningLeft ;
    BufferedImage[] shootingLeft ;
    BufferedImage[] shootingRight ;
    BufferedImage[] dyingRight ;
    BufferedImage[] dyingLeft ;
    Animation standingLeftAnimation ;
    Animation standingRightAnimation ;
    Animation runningLeftAnimation ;
    Animation runningRightAnimation ;
    Animation shootingLeftAnimation ;
    Animation shootingRightAnimation ;
    Animation dyingRightAnimation ;
    Animation dyingLeftAnimation ;

    public Player() {
        
        initPlayer();
    }
    
    private void initPlayer() {
//        width= 100;
//        height = 134;
        width= 90;
        height = 128;
        loadSprites();
        setAnimations();
    }

    private void loadSprites(){
        standingLeft = new BufferedImage[]{
    	        Sprite.loadSprite("assets/player/idle/left/Idle (1).png"),
    	        Sprite.loadSprite("assets/player/idle/left/Idle (2).png"),
    	        Sprite.loadSprite("assets/player/idle/left/Idle (3).png"),
    	        Sprite.loadSprite("assets/player/idle/left/Idle (4).png"),
    	        Sprite.loadSprite("assets/player/idle/left/Idle (5).png"),
    	        Sprite.loadSprite("assets/player/idle/left/Idle (6).png"),
    	        Sprite.loadSprite("assets/player/idle/left/Idle (7).png"),
    	        Sprite.loadSprite("assets/player/idle/left/Idle (8).png"),
    	        Sprite.loadSprite("assets/player/idle/left/Idle (9).png"),
    	        Sprite.loadSprite("assets/player/idle/left/Idle (10).png")
        }; 
        
        standingRight = new BufferedImage[]{
        		Sprite.loadSprite("assets/player/idle/right/Idle (1).png"),
     	        Sprite.loadSprite("assets/player/idle/right/Idle (2).png"),
     	        Sprite.loadSprite("assets/player/idle/right/Idle (3).png"),
     	        Sprite.loadSprite("assets/player/idle/right/Idle (4).png"),
     	        Sprite.loadSprite("assets/player/idle/right/Idle (5).png"),
     	        Sprite.loadSprite("assets/player/idle/right/Idle (6).png"),
     	        Sprite.loadSprite("assets/player/idle/right/Idle (7).png"),
     	        Sprite.loadSprite("assets/player/idle/right/Idle (8).png"),
     	        Sprite.loadSprite("assets/player/idle/right/Idle (9).png"),
     	        Sprite.loadSprite("assets/player/idle/right/Idle (10).png")
        };
        
        runningRight = new BufferedImage[]{
   	            Sprite.loadSprite("assets/player/run/right/run (1).png"),
	 	        Sprite.loadSprite("assets/player/run/right/run (2).png"),
	 	        Sprite.loadSprite("assets/player/run/right/run (3).png"),
	 	        Sprite.loadSprite("assets/player/run/right/run (4).png"),
	 	        Sprite.loadSprite("assets/player/run/right/run (5).png"),
	 	        Sprite.loadSprite("assets/player/run/right/run (6).png"),
	 	        Sprite.loadSprite("assets/player/run/right/run (7).png"),
	 	        Sprite.loadSprite("assets/player/run/right/run (8).png")
        };
        
        runningLeft = new BufferedImage[]{
        		Sprite.loadSprite("assets/player/run/left/run (1).png"),
       	        Sprite.loadSprite("assets/player/run/left/run (2).png"),
       	        Sprite.loadSprite("assets/player/run/left/run (3).png"),
       	        Sprite.loadSprite("assets/player/run/left/run (4).png"),
       	        Sprite.loadSprite("assets/player/run/left/run (5).png"),
       	        Sprite.loadSprite("assets/player/run/left/run (6).png"),
       	        Sprite.loadSprite("assets/player/run/left/run (7).png"),
       	        Sprite.loadSprite("assets/player/run/left/run (8).png")
        };
        
        shootingLeft = new BufferedImage[]{
        		Sprite.loadSprite("assets/player/shoot/left/Melee (1).png"),
       	        Sprite.loadSprite("assets/player/shoot/left/Melee (2).png"),
       	        Sprite.loadSprite("assets/player/shoot/left/Melee (3).png"),
       	        Sprite.loadSprite("assets/player/shoot/left/Melee (4).png"),
       	        Sprite.loadSprite("assets/player/shoot/left/Melee (5).png"),
       	        Sprite.loadSprite("assets/player/shoot/left/Melee (6).png"),
       	        Sprite.loadSprite("assets/player/shoot/left/Melee (7).png"),
       	        Sprite.loadSprite("assets/player/shoot/left/Melee (8).png")
        }; 
        
        
        shootingRight = new BufferedImage[]{
        		Sprite.loadSprite("assets/player/shoot/right/Melee (1).png"),
       	        Sprite.loadSprite("assets/player/shoot/right/Melee (2).png"),
       	        Sprite.loadSprite("assets/player/shoot/right/Melee (3).png"),
       	        Sprite.loadSprite("assets/player/shoot/right/Melee (4).png"),
       	        Sprite.loadSprite("assets/player/shoot/right/Melee (5).png"),
       	        Sprite.loadSprite("assets/player/shoot/right/Melee (6).png"),
       	        Sprite.loadSprite("assets/player/shoot/right/Melee (7).png"),
       	        Sprite.loadSprite("assets/player/shoot/right/Melee (8).png")
        }; 
        
        dyingRight = new BufferedImage[]{
        		Sprite.loadSprite("assets/player/dies/right/Dead (1).png"),
        		Sprite.loadSprite("assets/player/dies/right/Dead (2).png"),
        		Sprite.loadSprite("assets/player/dies/right/Dead (3).png"),
        		Sprite.loadSprite("assets/player/dies/right/Dead (4).png"),
        		Sprite.loadSprite("assets/player/dies/right/Dead (5).png"),
        		Sprite.loadSprite("assets/player/dies/right/Dead (6).png"),
        		Sprite.loadSprite("assets/player/dies/right/Dead (7).png"),
        		Sprite.loadSprite("assets/player/dies/right/Dead (8).png"),
        		Sprite.loadSprite("assets/player/dies/right/Dead (9).png"),
        		Sprite.loadSprite("assets/player/dies/right/Dead (10).png")
        }; 
        
        dyingLeft = new BufferedImage[]{
        		Sprite.loadSprite("assets/player/dies/left/Dead (1).png"),
        		Sprite.loadSprite("assets/player/dies/left/Dead (2).png"),
        		Sprite.loadSprite("assets/player/dies/left/Dead (3).png"),
        		Sprite.loadSprite("assets/player/dies/left/Dead (4).png"),
        		Sprite.loadSprite("assets/player/dies/left/Dead (5).png"),
        		Sprite.loadSprite("assets/player/dies/left/Dead (6).png"),
        		Sprite.loadSprite("assets/player/dies/left/Dead (7).png"),
        		Sprite.loadSprite("assets/player/dies/left/Dead (8).png"),
        		Sprite.loadSprite("assets/player/dies/left/Dead (9).png"),
        		Sprite.loadSprite("assets/player/dies/left/Dead (10).png")
        };
    }
    
    private void setAnimations(){
    	standingLeftAnimation = new Animation(standingLeft, 3);
    	standingRightAnimation = new Animation(standingRight, 3);
    	runningRightAnimation = new Animation(runningRight, 5);
    	runningLeftAnimation = new Animation(runningLeft, 5);
    	shootingLeftAnimation = new Animation(shootingLeft,5);
    	shootingRightAnimation = new Animation(shootingRight,5);
    	dyingRightAnimation = new Animation(dyingRight,5);
    	dyingLeftAnimation = new Animation(dyingLeft,5);
    }
    public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void move() {
		//System.out.println("areaWidth = " + areaWidth + " x + dx " + (x+dx));
		if(x + dx < areaWidth - 80 && x + dx > 10){
	        x += dx;
	        y += dy;
		}

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -3;
            playerDirection = "LEFT";
            playerState = "RUNNING";
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
            playerDirection = "RIGHT";
            playerState = "RUNNING";
        }
        
        if (key == KeyEvent.VK_SPACE) {

            playerState = "SHOOTING";
        }


    }
    

    public void keyReleased(KeyEvent e) {
        
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            playerState = "STANDING";
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            playerState = "STANDING";
        }

        if (key == KeyEvent.VK_SPACE) {
           // playerState= "SHOOTING";
        	
        	
        	
        }


    }
}