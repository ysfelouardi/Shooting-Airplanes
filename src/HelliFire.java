
import java.awt.image.BufferedImage;

import animationBase.Animation;
import animationBase.Sprite;

public class HelliFire {
    private int x;
    private int y;
    private int initialY;
    private int dy;
    private int width;
    private int height;
    int areaHeight;
    BufferedImage[] HelliFires;
    Animation HelliFiresAnimation;

    
    public HelliFire() {
        
        initHelliFire();
    }
    
    
    private void initHelliFire() {
        width= 18;
        height = 18;
        loadSprites();
        setAnimations();
        dy = 2;
    }
    
    
    private void loadSprites(){
        HelliFires = new BufferedImage[]{
    	        Sprite.loadSprite("assets/jet/fire/1.png"),
    	        Sprite.loadSprite("assets/jet/fire/2.png"),
    	        Sprite.loadSprite("assets/jet/fire/3.png"),
    	        Sprite.loadSprite("assets/jet/fire/4.png"),
    	        Sprite.loadSprite("assets/jet/fire/5.png")
        }; 
    }
    
    private void setAnimations(){
    	HelliFiresAnimation = new Animation(HelliFires,5);
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
	
	public void goDown(){
		this.y += dy;
	}
	
	


	public int getInitialY() {
		return initialY;
	}


	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}
	
    
}
