package frogger.model.NPC;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import frogger.constant.FilePath;
import javafx.scene.image.Image;


/* frog = new Frog(width/2-grid/2,height-grid,grid)
 * int totalLanes = int(height/grid)
 * lanes= new Lane[totalLanes]
 * for(int i=0; i<lanes.length; i++)
 * ypos = i*grid
 * show function in lane
 * 
 */

/**
 * This class allows to create a swamp Image object. The image will change 
 * as a frog approaches a swamp spot.
 * @author Lim Xin Jieh (20082200)
 *
 */

public class Swamp extends Actor {
	private static final Image FLY_END = newImage("FlyEnd.png");
	private static final Image END = newImage("End.png");
	private static final Image CROC_END2 = newImage("CrocEnd2.png");
	private static final Image CROC_END1 = newImage("CrocEnd1.png");
	private static final Image FROG_END = newImage("FrogEnd.png");
	private static int DELAY = 10;
	private static int MAX_FLY = 1;
	private static int MAX_CROC = 2;
	private static final int WIDTH = 65;
	private static final int HEIGHT = 60;
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
	 * @param now - the timestamp of the current timeframe given 
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
	 * @param now - the timestamp of the current timeframe given 
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
	
	
	public static void resetCtr() {
		flyCtr=0;
		crocCtr=0;
	}


	/**
	 * This method is a public constructor to create an End object
	 * @param x - x position of object
	 * @param y - y position of object
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
	
	
	/**
	 * This method constructs a new image with the given image URL and predefined
	 * parameters 
	 * @param fileName - String representing the file name to use when fetching pixel data
	 * @return new Image object
	 */
	private static Image newImage(String fileName) {
		return new Image(FilePath.SWAMP_PATH + fileName, WIDTH, HEIGHT, false, true);
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
