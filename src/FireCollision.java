import java.awt.image.BufferedImage;

import animationBase.Animation;
import animationBase.Sprite;

public class FireCollision {
	 private int x;
	 private int y;
	 private int width;
	 private int height;
	 BufferedImage[] explotions;
	 Animation explotionsAnimation;
	 
	 public FireCollision() {
	        
	        initFireCollision();
	 }
	    
	    
	    private void initFireCollision() {
	        width= 60;
	        height = 60;
	        loadSprites();
	        setAnimations();
	    }
	    private void loadSprites(){
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


		public BufferedImage[] getExplotions() {
			return explotions;
		}


		public void setExplotions(BufferedImage[] explotions) {
			this.explotions = explotions;
		}


		public Animation getExplotionsAnimation() {
			return explotionsAnimation;
		}


		public void setExplotionsAnimation(Animation explotionsAnimation) {
			this.explotionsAnimation = explotionsAnimation;
		}
	    
}
