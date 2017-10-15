import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import animationBase.Animation;
import animationBase.Sprite;



public class GameArea extends JPanel implements ActionListener,Runnable{
	private Image backgroundImage;
	private Timer timer;
	private Player player;
	//private Missile missile;
	private List<Missile> missiles;
	//private List<HelliFire> fires;
	private HelliFire myFire;
	private List<Jet> jets;
	private List<FireCollision> firecollisions;
	private final int DELAY = 9;
    private boolean drawmissile = false;
    private int w,h;
    KeyEvent ev;
    long startTime;
    long explosionStartTime;
    long fireCollisionStartTime;
    private Thread animator;
	Clip backgroundclip = null ;
	Clip buttonClip = null;
	String gameState = "OFF";
	int score = 0;
   
	public GameArea(){
		 initGameArea();
	 }
	
	 private void initGameArea() {
	        
			loadBackgroundImage();
			
	        w = backgroundImage.getWidth(this);
	        h =  backgroundImage.getHeight(this);
	        setPreferredSize(new Dimension(w, h));  
	        
	        addKeyListener(new TAdapter());
	        addMouseListener(new MAdapter());
	        setFocusable(true);

	        player = new Player();

	       // missile = new Missile();
	        missiles = new ArrayList<Missile>();
	       // fires = new ArrayList<HelliFire>();
	        jets = new ArrayList<Jet>();
	        firecollisions = new ArrayList<FireCollision>();

	        player.setX(w/2 - player.getWidth()/2);
	        player.setY(h - player.getHeight() - 55);
	        player.areaWidth = w;
	        
//	        missile.setX(player.getX() + 35);
//	        missile.setY(player.getY() - 35);
//	        missile.setInitialY(player.getY() - 35);
//	        missile.areaHeight = h;

	        
//	        timer = new Timer(DELAY, this);
//	        timer.start();   
	        
	        
	        

	        player.standingRightAnimation.start();
	        player.standingLeftAnimation.start();
	        player.runningRightAnimation.start();
	        player.runningLeftAnimation.start();
	        player.shootingLeftAnimation.start();
	        player.shootingRightAnimation.start();
	        player.dyingLeftAnimation.start();
	        player.dyingRightAnimation.start();
	        
	       // missiles.add(missile);
	        
	        //addJet();
	        startTime = System.currentTimeMillis();
	        
			playBackGroundMusic();
	       

	}
	  
	 

	private void loadBackgroundImage(){
		 ImageIcon ii = new ImageIcon("assets/background/bg-07.png");
		 backgroundImage = ii.getImage();
	}
	

	@Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackgroundImage(g);
        if(gameState == "OFF"){
            drawWelcomeScreen(g);
        }else if(gameState == "ON"){
            drawPlayer(g);
            drawJets(g);
            drawMissiles(g);
            drawFireCollisions(g);
            drawScoreScreen(g);
        }else if(gameState == "OVER"){
        	drawGameOverScreen(g);
        	drawScoreScreen(g);
        }


        Toolkit.getDefaultToolkit().sync();
        //setDoubleBuffered(true);
    }
	
	private void drawBackgroundImage(Graphics g){
        g.drawImage(backgroundImage, 0, 0, null);
	}
	
	private void drawWelcomeScreen(Graphics g){
		g.setFont(new Font("04b_19", Font.PLAIN, 80));
		//g.setFont(new Font("flappybirdy", Font.PLAIN, 80));
		g.setColor(Color.PINK);
        g.drawString("PLAY", w/2 - 80, h/2);
	}
	
	private void drawGameOverScreen(Graphics g){
		g.setFont(new Font("04b_19", Font.PLAIN, 80));
		//g.setFont(new Font("flappybirdy", Font.PLAIN, 80));
		g.setColor(Color.LIGHT_GRAY);
        g.drawString("GAME OVER", w/2 - 200, h/2);
        g.setFont(new Font("04b_19", Font.PLAIN, 30));
        g.setColor(Color.orange);
        g.drawString("PLAY AGAIN", w/2 - 60, h/2 + 50);
	}
	
	private void drawScoreScreen(Graphics g){
		g.setFont(new Font("04b_19", Font.PLAIN, 20));
		g.setColor(Color.white);
        g.drawString("SCORE = " + score, w - 110,30);
	}
	
	private void drawPlayer(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		//g2d.drawImage(player.getImage(), player.getX(),player.getY(), this);
		if(player.playerState == "STANDING"){
			if(player.playerDirection == "LEFT"){
				g2d.drawImage(player.standingLeftAnimation.getSprite(), player.getX(),player.getY(), player.getWidth(), player.getHeight(),this);
				player.standingLeftAnimation.update();
			}else if(player.playerDirection == "RIGHT"){
				g2d.drawImage(player.standingRightAnimation.getSprite(), player.getX(),player.getY(), player.getWidth(), player.getHeight(),this);
				player.standingRightAnimation.update();
			}
		}else if(player.playerState == "RUNNING"){
			if(player.playerDirection == "LEFT"){
				g2d.drawImage(player.runningLeftAnimation.getSprite(), player.getX(),player.getY(), player.getWidth(), player.getHeight(),this);
				player.runningLeftAnimation.update();
			}else if(player.playerDirection == "RIGHT"){
				g2d.drawImage(player.runningRightAnimation.getSprite(), player.getX(),player.getY(), player.getWidth(), player.getHeight(),this);
				player.runningRightAnimation.update();
			}
		}else if(player.playerState == "SHOOTING"){
			if(player.playerDirection == "LEFT"){
				g2d.drawImage(player.shootingLeftAnimation.getSprite(), player.getX(),player.getY(), player.getWidth(), player.getHeight(),this);
				player.shootingLeftAnimation.update();
				
	    		int currentframe = player.shootingLeftAnimation.getCurrentFrame();
	    		int totalframes = player.shootingLeftAnimation.getTotalFrames() - 1;
	    		//System.out.println("current frame = " + currentframe + " number of frames = " +totalframes);
				if(currentframe > totalframes - 1){
					playGunShotSound();
					player.playerState = "STANDING";
	    			player.shootingLeftAnimation.reset();
	    			player.shootingLeftAnimation.start();
	    			drawmissile = true;
	    			addMissile(ev);
				}else{
					player.playerState = "SHOOTING";
				}
			}else if(player.playerDirection == "RIGHT"){
				g2d.drawImage(player.shootingRightAnimation.getSprite(), player.getX(),player.getY(), player.getWidth(), player.getHeight(),this);
				player.shootingRightAnimation.update();
				int currentframe = player.shootingRightAnimation.getCurrentFrame();
	    		int totalframes = player.shootingRightAnimation.getTotalFrames() - 1;
	    		//System.out.println("current frame = " + currentframe + " number of frames = " +framescount);
				if(currentframe > totalframes - 1){
					playGunShotSound();
					player.playerState = "STANDING";
	    			player.shootingRightAnimation.reset();
	    			player.shootingRightAnimation.start();
	    			drawmissile = true;
	    			addMissile(ev);
				}else{
					player.playerState = "SHOOTING";
				}
			}
			
		}else if(player.playerState == "DYING"){
			if(player.playerDirection == "LEFT"){
				g2d.drawImage(player.dyingLeftAnimation.getSprite(), player.getX(),player.getY(), player.getWidth(), player.getHeight(),this);
				player.dyingLeftAnimation.update();
			}else if(player.playerDirection == "RIGHT"){
				g2d.drawImage(player.dyingRightAnimation.getSprite(), player.getX(),player.getY(), player.getWidth(), player.getHeight(),this);
				player.dyingRightAnimation.update();
			}
		}
		
		

		
	  

		

			
			
	}
	
	private void drawMissiles(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		//System.out.println(" missiles count = " + missiles.size());
		if(drawmissile){
			//int i = 0;
			for(int i = 0;i<missiles.size();i++){
				Missile missile = missiles.get(i);
				if(missile.getY() > 0){
					drawMissile(g2d,missile);					
					attack(missile);
				}
				else if( i == missiles.size() - 1){
					drawmissile = false;
				}
			}

		}else{
			missiles.clear();
		}		
		
	}
	
	private void drawFireCollisions(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0;i<firecollisions.size();i++){
			FireCollision fc = firecollisions.get(i);
			g2d.drawImage(fc.explotionsAnimation.getSprite(), fc.getX(),fc.getY(), fc.getWidth(), fc.getHeight(),this);
			fc.explotionsAnimation.update();
			long fireCollisionStopTime =  System.currentTimeMillis();
			if(fireCollisionStopTime - fireCollisionStartTime > 1000/2){		    				    	
		    	firecollisions.remove(fc);
			}
		}
	}
	
	private void drawJets(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		//int k = 0;
		//System.out.println(" jets count = " + jets .size());
		//int indice = 0;
		for(int i = 0;i<jets.size();i++){
			Jet j = jets.get(i);
			HelliFire fire = j.getFire();
			drawJet(g2d,j);			
			if(j.getX() >= player.getX() - 20 && j.getX() <= player.getX() + 20 && !j.isExploded()){
				//System.out.println(" fires size = " + fires.size());
				//System.out.println("start fire "+(j.getX() - j.getWidth()/2 - 120)+"<=" + player.getX() + "<=" +(j.getX() + j.getWidth()/2 + 120) );		
				//System.out.println(" fire x " + fire.getX());
				if(!j.startfire){					
					fire.setX(j.getX() + (j.getWidth()/2));
					//addFire(j);

				}
				
				j.startfire = true;
				
			}
			
			if(j.startfire){
				 
				 //System.out.println(" fire y " + fire.getY());
					 if(fire.getY() < h - 75)
					 {
						 
						 fire.goDown(); 
						 if((fire.getY() >= player.getY() - 5) && (fire.getX() >= player.getX() - player.getWidth()/2 + 47 && fire.getX() <= player.getX() + player.getWidth()/2 + 18)){
							 System.out.println("DEAD");
							 j.drawfire = false;
							 handlePlayerDeath();
						 }
						 
						 
						 drawHelliFire(g2d,j.drawfire,fire);
					 }else{
						 fire.setY(fire.getInitialY());
						 j.startfire = false;
						 j.drawfire = true;
					 } 
				 }	
			
				
			
			
		  
			
			if(!j.isExploded()){
				j.fly();			
			}
			
			//System.out.println("jets count = " + jets.size() );
			
					
			if(j.getX() > w){
				jets.remove(j);
				continue;
			}

			//k++;
		}
		

	}
	
	
	
	private void handlePlayerDeath(){
		gameState = "OVER";
		backgroundclip.stop();
		playGameOverSound();
	}
	
	private void attack(Missile m){
		int i = 0;
		for(Jet j : jets){		
			
			
			if(!j.isExploded()){
				if((j.getY() >= m.getY() - j.getHeight()/2 - 12) && (m.getX() >= j.getX() - j.getWidth()/2 + 22  && m.getX() <= j.getX() + j.getWidth()/2 + 12  )){
					
						
						//System.out.println(" i'm in attack jet y " + j.getY() + " missile y = " + m.getY() );
						//System.out.println(" i'm in attack jet x " + j.getX() + " missile x = " + m.getX() );
						j.jetsAnimation.stop();
						j.explotionsAnimation.start();
						j.setExploded(true);
						explosionStartTime = System.currentTimeMillis();
						//System.out.println(" i'm in attack jet ( "+i+" ) y " + j.getY() + " missile Y = " + (m.getY() - j.getHeight()/2 - 12) );
						//System.out.println((j.getX() - j.getWidth()/2) +" <= "+m.getX()+ " <= " + (j.getX() + j.getWidth()/2 + 12 ));
						missiles.remove(m);
						playExplosionSound();
						score++;
						break;
					//}
	
				}
				i++;
			}
			
			if(j.startfire){
				HelliFire fire = j.getFire();
				if((m.getY() >= fire.getY() - fire.getHeight()/2 && m.getY() <= fire.getY() + fire.getHeight()/2) && (m.getX() >= fire.getX() - fire.getWidth()/2 && m.getX() <= fire.getX() + fire.getWidth()/2 ) ){
					//System.out.println("size firecollison = " + firecollisions.size());
					addFireCollision(fire.getX() - fire.getWidth()/2,fire.getY() - fire.getHeight()/2);
					fireCollisionStartTime = System.currentTimeMillis();
					playExplosionSound();
					missiles.remove(m);
					fire.setY(fire.getInitialY());
					j.startfire = false;
					j.drawfire = true;
				}
			}


		}
	}
	
	private void playExplosionSound(){
		Clip clip = null ;
		try{
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/sounds/explo.wav")));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace();
	    }
		
	}
	
	private void playBackGroundMusic(){
		try{
	        backgroundclip = AudioSystem.getClip();
	        backgroundclip.open(AudioSystem.getAudioInputStream(new File("assets/sounds/2.wav")));
	        backgroundclip.loop(Clip.LOOP_CONTINUOUSLY);
	        backgroundclip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace();
	    }
	}
	
	private void playGunShotSound(){
		Clip clip = null ;
		try{
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/sounds/gun_shot.wav")));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace();
	    }
		
	}
	
	private void playButtonSound(){
		
		try{
	        buttonClip = AudioSystem.getClip();
	        buttonClip.open(AudioSystem.getAudioInputStream(new File("assets/sounds/button.wav")));
	        buttonClip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace();
	    }
		
	}
	
	private void playGameOverSound(){
		Clip clip = null ;
		try{
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/sounds/gameover.wav")));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace();
	    }
		
	}

	
	
	
	private void drawMissile(Graphics2D g2d,Missile missile){
		    	
		g2d.drawImage(missile.missilesAnimation.getSprite(), missile.getX(),missile.getY(), missile.getWidth(), missile.getHeight(),this);
		missile.missilesAnimation.update();
		
    	
	}
	
	private void drawJet(Graphics2D g2d,Jet j){
    		
		//System.out.println(" jets count = " + jets.size() );

			if(j.isExploded()){
				
				if(j.drawjet){
					g2d.drawImage(j.explotionsAnimation.getSprite(), j.getX(),0, j.getWidth(), 100,this);
					j.explotionsAnimation.update();
				}
				
				long explosionStopTime =  System.currentTimeMillis();
				if(explosionStopTime - explosionStartTime > 1000/2){		    				    	
			    	if(!j.drawjet && !j.startfire){
			    		jets.remove(j);
			    	}
			    	
					if(j.getFire().getY() >= h - 75 ){
			    		jets.remove(j);
			    	}else{
			    		j.drawjet = false;
			    	}
			    	
				}
				
			}else if(!j.isExploded()){
				g2d.drawImage(j.jetsAnimation.getSprite(), j.getX(),j.getY(), j.getWidth(), j.getHeight(),this);
				j.jetsAnimation.update();
			}
		

		
		
    	
	}
	
	private void drawHelliFire(Graphics2D g2d,boolean drawfire,HelliFire fire){
		//System.out.println("draw fire ? " + j.drawfire);
		if(drawfire){
			g2d.drawImage(fire.HelliFiresAnimation.getSprite(),fire.getX(),fire.getY(),fire.getWidth(),fire.getHeight(),this);
			fire.HelliFiresAnimation.update();	
		}

	}
	
	private void addJet(){
		Jet j = new Jet();
        j.setX(0);
        j.setY(30);
        j.areaWidth = w;        
        j.jetsAnimation.start();

        HelliFire fire = new HelliFire();
        fire.setInitialY(j.getY() + 20);
        fire.areaHeight = h;
        fire.setX(j.getX() + (j.getWidth()/2) - 6);
        fire.setY(j.getY() + 20);
        fire.HelliFiresAnimation.start();         
        j.setFire(fire);
        
        jets.add(j);

	}
	
	private void addFireCollision(int x,int y){
		FireCollision fc = new FireCollision();
        fc.setX(x);
        fc.setY(y);      
        fc.explotionsAnimation.start();
        firecollisions.add(fc);
	}
	

	private void addMissile(KeyEvent e){
		Missile missile = new Missile();
        missile.setX(player.getX() + 35);
        missile.setY(player.getY() - 35);
        missile.setInitialY(player.getY() - 35);
        missile.areaHeight = h;        
        missile.missilesAnimation.start();
        missile.keyPressed(e);
        missiles.add(missile);
	}
	
	private void resetPlayerPosition(){
        player.setX(w/2 - player.getWidth()/2);
        player.setY(h - player.getHeight() - 55);
	}
	
	private void deleteAllJets(){
		jets.clear();
		startTime = System.currentTimeMillis();
	}
	
	private void deleteAllMissiles(){
		missiles.clear();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
//		player.move();
//		if(drawmissile){
//			for(Missile missile : missiles){				
//				if(missile.getY() > 0){
//					missile.goUp();
//				}
//			}
//		}
//		repaint();
	}
	
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
            for(Missile m : missiles){
                if(e.getKeyCode() == KeyEvent.VK_LEFT && !drawmissile){
                	m.setX(player.getX() + 20 );
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !drawmissile){
                	m.setX(player.getX() + 35);
                }
            }
            
//            if(e.getKeyCode() == KeyEvent.VK_SPACE){
//    			
//            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);  
            ev = e;
        }
    }
    
	private class MAdapter extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent pos) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			int posY = pos.getY();
			int posX = pos.getX();
			
			if(gameState == "OFF"){
				if(posY < h/2 + 11 && posY > h/2 - 70 && posX >= w/2 - 83 && posX <= w/2 + 83){					
					//System.out.println(" postion  x = " + posX + " y = " + posY);
					playButtonSound();
					gameState = "ON";
				}
			}else if(gameState == "OVER"){
				
				if(posY < h/2 + 61 && posY > h/2 - 70 && posX >= w/2 - 80 && posX <= w/2 + 100){
					
					//System.out.println(" postion  x = " + posX + " y = " + posY);
					playButtonSound();
					resetPlayerPosition();
					deleteAllJets();
					deleteAllMissiles();
					score = 0;
					playBackGroundMusic();
					gameState = "ON";
					
					
				}
			}

				
		}

		@Override
		public void mouseEntered(MouseEvent pos) {
			// TODO Auto-generated method stub

		}
	
		@Override
		public void mouseExited(MouseEvent pos) {

		}
	
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
		}	
	}
    
    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 long beforeTime, timeDiff, sleep;

	     beforeTime = System.currentTimeMillis();

	        while (true) {

	    		
	        	if(gameState == "ON"){
//		    		if(!backgroundclip.isRunning()){
//						backgroundclip.start();
//						backgroundclip.loop(Clip.LOOP_CONTINUOUSLY);
//		    		}
		        	long stopTime = System.currentTimeMillis();
		    	    if(stopTime - startTime > 2000){
		    			addJet();
		    	    	startTime = System.currentTimeMillis();
		    	    }
		    		player.move();
		    		

		            
		    		if(drawmissile){
		    			for(int i=0;i<missiles.size();i++){	
		    				Missile missile = missiles.get(i);
		    				if(missile.getY() > 0){
		    					missile.goUp();
		    				}
		    			}
		    		}
	        	}


	    		repaint();

	    		timeDiff = System.currentTimeMillis() - beforeTime;
	            sleep = DELAY - timeDiff;

	            if (sleep < 0) {
	                sleep = 2;
	            }

	            try {
	                Thread.sleep(sleep);
	            } catch (InterruptedException e) {
	                System.out.println("Interrupted: " + e.getMessage());
	            }

	            beforeTime = System.currentTimeMillis();
	        }
	}



}
