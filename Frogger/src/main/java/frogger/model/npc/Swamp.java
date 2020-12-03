package frogger.model.npc;

import java.util.Random;
import frogger.constant.FilePath;
import javafx.scene.image.Image;


/**
 * This class is the model for Swamp. The Swamp object image will alternate
 * to show crocodiles or flies, and changes when a frog approaches a swamp spot.
 */

public class Swamp extends NPC {
	
	private static final int WIDTH = 65;
	private static final int HEIGHT = 60;
	private static final Image FLY_END = new Image(FilePath.S_FLY, WIDTH, HEIGHT, false, true);
	private static final Image END = new Image(FilePath.SWAMP, WIDTH, HEIGHT, false, true);
	private static final Image CROC_END2 = new Image(FilePath.S_CROC1, WIDTH, HEIGHT, false, true);
	private static final Image CROC_END1 = new Image(FilePath.S_CROC2, WIDTH, HEIGHT, false, true);
	private static final Image FROG_END = new Image(FilePath.S_FROG, WIDTH, HEIGHT, false, true);
	private static int DELAY = 10;
	private static int MAX_FLY = 1;
	private static int MAX_CROC = 2;
	private static int flyCtr = 0;
	private static int crocCtr = 0;
	private boolean hasFly = false;
	private boolean hasCroc = false;
	private boolean activated = false;
	private int crocAnimCtr=0;
	private int flyAnimCtr=0;
	
	/**
	 * This method checks if swamp is empty, then randomly 
	 * generates a crocodile or fly in its spot
	 */
	@Override
	public void act(long now) {

		if(!activated) {
		
			if(!hasFly && !hasCroc) {
				
				int rand = new Random().nextInt(100);

				if(rand==98 && flyCtr<MAX_FLY){
					hasFly=true;
					System.out.println("fly ctr is " + flyCtr);
					flyCtr+=1;
					
				} else if (rand==99 && crocCtr<MAX_CROC) {
					crocCtr+=1;
					hasCroc=true;
					
				}
				
			}
			
			if(hasCroc) {
				handleCroc(now);
			}

			
			if(hasFly) {
				handleFly(now);
			}

		}
	
	}


	/**
	 * This method handles appearance and disappearance of fly in
	 * the swamp. The fly is set to disappear after a fixed time.
	 * @param now  the timestamp of the current timeframe given 
	 * in nanoseconds	 
	 */
	
	private void handleFly(long now) {
		
		if(activated) {
			flyCtr-=1;
			
		} else {
			
			if(now%11==0) {
				this.flyAnimCtr++;
			}
			
			if(flyAnimCtr==1) {
				setImage(FLY_END);
				
			}
			
			if(flyAnimCtr==15) {
				setImage(END);
				hasFly=false;
				flyCtr-=1;
				this.flyAnimCtr=0;
				
			
		}
		
		
			
			
		}

	}
	

	/**
	 * This method handles appearance and disappearance of crocodile in 
	 * the swamp. The crocodile is set to disappear after a fixed time.
	 * @param now  the timestamp of the current timeframe given 
	 * in nanoseconds
	 */
	private void handleCroc(long now) {

		if(now%11==0) {
			this.crocAnimCtr++;
		}
		
		crocAnim();
	}
	
	
	 /**
	  * This method handles the appearing and disappearing
	  * animation of the crocodile at the swamp.  
	  */

	private void crocAnim() {
		if(this.crocAnimCtr==1 || this.crocAnimCtr==2+DELAY) {
			setImage(CROC_END2);

		} else if(this.crocAnimCtr==2) {
			setImage(CROC_END1);
			
		} else if(this.crocAnimCtr==4+DELAY) {
			setImage(END);
			hasCroc=false;
			crocCtr-=1;
			this.crocAnimCtr=0;
		}
	}

	/**
	 * This method resets this object to an empty swamp image
	 */
	public void resetEnd() {
		setImage(END);
	}
	
	/**
	 * This method resets the counter for flies and crocodiles
	 */
	public static void resetCtr() {
		flyCtr=0;
		crocCtr=0;
	}


	/**
	 * This method is a public constructor to create an End object
	 * @param x  x position of object
	 * @param y  y position of object
	 */
	public Swamp(int x, int y) {
		setX(x);
		setY(y);
		setImage(END);
	}
	


	/**
	 * This method changes the End object Image to show an 'occupied' swamp
	 * spot
	 */
	public void setOccupied() {
		setImage(FROG_END);
		activated = true;
	}
	

	/**
	 * This method checks if the End object is previously activated / 
	 * occupied by a frog
	 * @return boolean variable true if this object is 'occupied', false otherwise
	 */
	public boolean isActivated() {
		return activated;
	}
	
	/**
	 * 
	 * @return boolean variable true if this object has a fly, false
	 * otherwise
	 */
	public boolean hasFly() {
		return hasFly;
	}
	
	/**
	 * 
	 * @return boolean variable true if this object has a crocodile,
	 * false otherwise
	 */
	
	public boolean hasCroc() {
		return hasCroc;
	}
	
	


	@Override
	protected void checkOutOfBounds() {
		
	}


	@Override
	protected void playAnimation(long now) {
		
		
	}
	

}
//private void createTimerTask() {
//	
//	task2 = new TimerTask() {
//
//		@Override
//		public void run() {
//			if(!activated) {
//				
//				if(hasFly) {
//					hasFly=false;
//					flyCtr-=1;
//				} 
//				setEndImage("End.png");
//				
//			}
//			
//			
//		}
//		
//	};
//
//	
//}

//if (GameViewManager.pause) {
//this.pause=true;
//flyTimer.cancel();
//crocTimer.cancel();
//System.out.println("fly timer cancelled");
//
//} else {
//resume();
//}
//

//private void resume() {
//	if(this.pause) {
//		this.flyTimer = new Timer();
//		this.flyTimer.schedule(task2,0,1000);
//	}
//	// TODO Auto-generated method stub
//	
//}
//
//private void handleFly() {
//	handleFly=false;
//	hasFly=true;
//	setEndImage("FlyEnd.png");
//	flyCtr+=1;
//	createTimerTask();
//	flyTimer.schedule(task2, 2000);
//	System.out.println("fly timer created");
//}

//private void handleCroc(long now) {
//	if(now%11==0) {
//		crocAnimCtr++;
//	}
//	
//	if(crocAnimCtr==1) {
//		setEndImage("CrocEnd2.png");
//		crocCtr+=1;
//		createTimerTask();
//		crocTimer.schedule(task1, 4500);
//		crocTimer.schedule(task2, 5000);
//		
//	} else if(crocAnimCtr==2) {
//		setEndImage("CrocEnd1.png");
//		
//	} 
//}
