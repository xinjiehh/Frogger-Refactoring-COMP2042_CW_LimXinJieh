package Frogger;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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

public class End extends Actor {
	private static final String RESOURCE_PATH = "/swamp/";
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
	

	@Override
	public void act(long now) {
		

		if(!activated) {
			/*
			 * generate new random if no croc or fly
			 * then 
			 */
			if(!hasFly && !hasCroc) {
				
				int rand = new Random().nextInt(100);
				
				if(rand==98 && flyCtr<MAX_FLY){
					hasFly=true;
					flyCtr+=1;
					
				} else if (rand==99 && crocCtr<MAX_CROC) {
					crocCtr+=1;
					hasCroc=true;
					
				}
				
			}
			
			if(hasCroc) {
				handleCrocTest(now);
			}

			
			if(hasFly) {
				handleFlyTest(now);
			}

		}
	
	}


	
	private void handleFlyTest(long now) {
		
		if(now%11==0) {
			this.flyAnimCtr++;
		}
		
		if(flyAnimCtr==1) {
			setEndImage(RESOURCE_PATH + "FlyEnd.png");
			
		}
		
		if(flyAnimCtr==15) {
			setEndImage(RESOURCE_PATH + "End.png");
			hasFly=false;
			flyCtr-=1;
			this.flyAnimCtr=0;
			
			
			
		}

	}
	

	
	private void handleCrocTest(long now) {

		System.out.println(this + "crocHandler");

		if(now%11==0) {
			this.crocAnimCtr++;
		}
		
		handleCrocAnim();
	}

	private void handleCrocAnim() {
		if(this.crocAnimCtr==1 || this.crocAnimCtr==2+DELAY) {
			setEndImage(RESOURCE_PATH + "CrocEnd2.png");

		} else if(this.crocAnimCtr==2) {
			setEndImage(RESOURCE_PATH + "CrocEnd1.png");
			
		} else if(this.crocAnimCtr==4+DELAY) {
			setEndImage(RESOURCE_PATH + "End.png");
			hasCroc=false;
			crocCtr-=1;
			this.crocAnimCtr=0;
		}
	}






	/**
	 * This method is a public constructor to create an End object
	 * @param x - x position of object
	 * @param y - y position of object
	 */
	public End(int x, int y) {
		setX(x);
		setY(y);
		setEndImage(RESOURCE_PATH + "End.png");
	}
	


	/**
	 * This method changes the End object Image to show an 'occupied' swamp
	 * spot
	 */
	public void setOccupied() {
		setEndImage(RESOURCE_PATH + "FrogEnd.png");
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
	
	public boolean hasFly() {
		return hasFly;
	}
	
	public boolean hasCroc() {
		return hasCroc;
	}
	
	/**
	 * This method sets the value of the property image for this object 
	 * with predefined values using method inherited from ImageView class
	 * @param url - path of image to be displayed 
	 */
	private void setEndImage(String url) {
		setImage(new Image(url, WIDTH, HEIGHT, false, true));
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
