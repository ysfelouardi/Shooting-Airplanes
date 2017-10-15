import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import animationBase.Animation;
import animationBase.Sprite;

public class Jet {
    private int x;
    private int y;
    private int width;
    private int height;
    private HelliFire fire;
    boolean startfire = false;
    boolean drawfire = true;
    boolean drawjet = true;
    public HelliFire getFire() {
		return fire;
	}


	public void setFire(HelliFire fire){
		this.fire = fire;
	}

	private boolean exploded = false;
    public boolean isExploded() {
		return exploded;
	}


	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}

	int areaWidth;
    BufferedImage[] jets;
    BufferedImage[] explotions;
    Animation jetsAnimation;
    Animation explotionsAnimation;

    
    public Jet() {
        
        initJet();
    }
    
    
    private void initJet() {
//        width= 100;
//        height = 100;
        width= 96;
        height = 32;
        loadSprites();
        setAnimations();
    }
    
    
    private void loadSprites(){
        jets = new BufferedImage[]{
    	        Sprite.loadSprite("assets/jet/helli/1.png"),
    	        Sprite.loadSprite("assets/jet/helli/2.png"),
    	        Sprite.loadSprite("assets/jet/helli/3.png"),
    	        Sprite.loadSprite("assets/jet/helli/4.png"),
    	        Sprite.loadSprite("assets/jet/helli/5.png"),
    	        Sprite.loadSprite("assets/jet/helli/6.png"),
    	        Sprite.loadSprite("assets/jet/helli/7.png"),
    	        Sprite.loadSprite("assets/jet/helli/8.png")
        }; 
        
        explotions = new BufferedImage[]{
    	        Sprite.loadSprite("assets/explosions/1.png"),
    	        Sprite.loadSprite("assets/explosions/2.png"),
    	        Sprite.loadSprite("assets/explosions/3.png"),
    	        Sprite.loadSprite("assets/explosions/4.png"),
    	        Sprite.loadSprite("assets/explosions/5.png"),
    	        Sprite.loadSprite("assets/explosions/6.png"),
    	        Sprite.loadSprite("assets/explosions/7.png")
        }; 
    }
    
    private void setAnimations(){
    	jetsAnimation = new Animation(jets,1);
    	explotionsAnimation = new Animation(explotions,13);
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
	
	public void fly(){
		this.x += 1;

	}
	


	
    
}
