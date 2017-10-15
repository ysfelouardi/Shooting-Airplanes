import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import animationBase.Animation;
import animationBase.Sprite;

public class Missile {
    private int x;
    private int y;
    private int initialY;
    private int dy;
    private int width;
    private int height;
    int areaHeight;
    BufferedImage[] missiles;
    Animation missilesAnimation;

    
    public Missile() {
        
        initMissile();
    }
    
    
    private void initMissile() {
        width= 25;
        height = 25;
        loadSprites();
        setAnimations();
    }
    
    
    private void loadSprites(){
        missiles = new BufferedImage[]{
    	        Sprite.loadSprite("assets/player/objects/Bullet_000.png"),
    	        Sprite.loadSprite("assets/player/objects/Bullet_001.png"),
    	        Sprite.loadSprite("assets/player/objects/Bullet_002.png"),
    	        Sprite.loadSprite("assets/player/objects/Bullet_003.png"),
    	        Sprite.loadSprite("assets/player/objects/Bullet_004.png")
        }; 
    }
    
    private void setAnimations(){
    	missilesAnimation = new Animation(missiles,4);
    }
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
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
	
	public void goUp(){
		if(y > 0){
			this.y -= dy;
		}else{
			y = initialY;
		}

	}
	
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {

            dy = 3;
        }


    }
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {

            y = initialY;
        }


    }


	public int getInitialY() {
		return initialY;
	}


	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}
	
    
}
