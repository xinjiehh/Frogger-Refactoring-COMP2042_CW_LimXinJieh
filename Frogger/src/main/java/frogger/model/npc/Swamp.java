package frogger.model.npc;

import java.util.ArrayList;
import java.util.Random;

import frogger.constant.FilePath;
import frogger.util.animation.SpriteAnimationTemplate;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;


/**
 * This class is the model for Swamp. The Swamp object image will alternate
 * to show crocodiles or flies, and changes when a frog approaches a {@code
 * Swamp} object
 */

public class Swamp extends NPC {
	
	private static final int WIDTH = 65;
	private static final int HEIGHT = 60;
	private static final Image END = new Image(FilePath.SWAMP, WIDTH, HEIGHT, false, true);
	private static final Image FROG_END = new Image(FilePath.S_FROG, WIDTH, HEIGHT, false, true);
	
	private static int MAX_FLY = 1;
	private static int MAX_CROC = 2;
	private static int flyCtr = 0;
	private static int crocCtr = 0;
	
	private int x;
	private int y;
	private boolean noAnimation = true;
	private boolean hasFly = false;
	private boolean hasCroc = false;
	private boolean activated = false;
	private FlyAnimation flyAnim = new FlyAnimation();
	private CrocAnimation crocAnim = new CrocAnimation();

	

	/**
	 * This method is a public constructor to create a {@code Swamp} object
	 * @param x  x position of object
	 * @param y  y position of object
	 */
	public Swamp(int x, int y) {
		super();
		setImage(END);
		initPos(x,y);
		
	}
	
	/**
	 * This method checks if {@code Swamp} is empty, then randomly 
	 * generates a crocodile or fly in its spot using animation methods
	 * {@code playCrocAnim()} and {@code playFlyAnim()}
	 */
	@Override
	public void act(long now) {
		
		if(!activated) {
			
			if(!hasFly && !hasCroc) {
				
				int rand = new Random().nextInt(100);

				if(rand==98 && flyCtr<MAX_FLY){
					hasFly=true;
					flyCtr+=1;
					
				} else if (rand==99 && crocCtr<MAX_CROC) {
					hasCroc=true;
					crocCtr+=1;
				}
				
			}
			
			if(hasCroc && noAnimation) {
				playCrocAnim();
			}
			
			if(hasFly && noAnimation) {
				playFlyAnim();
				
			}

		} 
	
	}


	/**
	 * This method resets this object to an empty image
	 */
	public void resetEnd() {
		setImage(END);
	}
	
	/**
	 * This method resets the {@code flyCtr} and {@code crocCtr} that is
	 * shared by ALL {@link Swamp} objects
	 */
	public static void resetCtr() {
		flyCtr=0;
		crocCtr=0;
	}

	/**
	 * This method changes the {@link Swamp} object Image to show an 
	 * 'occupied' ({@code activated} = true) spot
	 */
	public void setOccupied() {
		setImage(FROG_END);
		activated = true;
	}
	

	/**
	 * This method checks if the {@link Swamp} object is {@code activated} / 
	 * occupied by a frog
	 * @return boolean variable true if this object is 'occupied', false 
	 * otherwise
	 */
	public boolean isActivated() {
		return activated;
	}
	
	/**
	 * This method returns true if this {@link Swamp} object has a fly
	 * @return boolean variable true if this object has a fly, false
	 * otherwise
	 */
	public boolean hasFly() {
		return hasFly;
	}
	
	/**
	 * This method returns true if this {@link Swamp} object has a crocodile
	 * @return boolean variable true if this object has a crocodile,
	 * false otherwise
	 */
	
	public boolean hasCroc() {
		return hasCroc;
	}


	@Override
	public NPC clone() {
		return new Swamp(this.x,this.y);
	}
	
	/**
	 * This method initializes the position of this {@link Swamp} object
	 * @param x  integer x position
	 * @param y  integer y position
	 */
	private void initPos(int x, int y) {
		this.x=x;
		this.y=y;
		setX(x);
		setY(y);
		
	}
	
	/**
	 * This method plays the fly animation and sets the relevant
	 * flags 
	 */
	private void playFlyAnim() {
		flyAnim.play();
		noAnimation = false;
		hasFly=true;
	}
	
	/**
	 * This method plays the crocodile animation and sets the relevant
	 * flags 
	 */
	private void playCrocAnim() {
		crocAnim.play();
		noAnimation = false;
		hasCroc=true;
	}
	
	/**
	 * This class is responsible for animating the fly in this {@link Swamp}
	 * object
	 */
	private class FlyAnimation extends SpriteAnimationTemplate {
		
		private final Image FLY_END = new Image(FilePath.S_FLY, WIDTH, HEIGHT, false, true);
		
		@Override
		protected void initAnimation() {
			animation = new Transition() {
			{
				setCycleDuration(Duration.millis(3000));
			}

				@Override
				protected void interpolate(double frac) {
					setImage(FLY_END);
					if(activated) {
						setImage(FROG_END);
						stop();
						endAnimation();
					}
				}
			};
			
			animation.setOnFinished(e->{
				setImage(END);
				endAnimation();
			});
			
		}

		protected void endAnimation() {
			flyCtr-=1;
			hasFly=false;
			donePlaying=true;
			noAnimation=true;
			
		}
		
	}

	/**
	 * This class is responsible for animating the crocodile in this {@link 
	 * Swamp} object
	 */
	private class CrocAnimation extends SpriteAnimationTemplate {
		private final Image CROC_END2 = new Image(FilePath.S_CROC2, WIDTH, HEIGHT, false, true);
		private final Image CROC_END1 = new Image(FilePath.S_CROC1, WIDTH, HEIGHT, false, true);
		private ArrayList<Image> crocImages = new ArrayList<Image>() {
			{
				add(CROC_END2);
				add(CROC_END1);
				add(CROC_END2);
				add(CROC_END2);
			}

		};

		@Override
		protected void initAnimation() {
			animation = new Transition() {
				{
					setCycleDuration(Duration.millis(3000));
				}

				@Override
				protected void interpolate(double frac) {
					int index = (int) (frac*(crocImages.size()-1));
			        index = index > crocImages.size()-1 ? crocImages.size()-1 : index;
			        setImage(crocImages.get(index)); 
					
				}
			};
			
			animation.setOnFinished(e->{
				setImage(END);
				crocCtr-=1;
				hasCroc=false;
				donePlaying=true;
				noAnimation=true;
				
			});
			
		}
		
	}

}

//private void initAnim() {
	//initFlyAnim();
	//initCrocAnim();
//}


//private void initCrocAnim() {
	
//	crocAnim = new Transition() {
//		{
//			setCycleDuration(Duration.millis(3000));
//		}
//
//		@Override
//		protected void interpolate(double frac) {
//			int index = (int) (frac*(crocImages.size()-1));
//	        index = index > crocImages.size()-1 ? crocImages.size()-1 : index;
//	        System.out.println(index);
//	        setImage(crocImages.get(index)); 
//			
//		}
//	};
//	
//	crocAnim.setOnFinished(e->{
//		setImage(END);
//		crocCtr-=1;
//		hasCroc=false;
//		donePlaying=true;
//	});
//}




//private void initFlyAnim() {
//	
//	flyAnim = new FlyAnimation();
//	flyAnim = new Transition() {
//		{
//			setCycleDuration(Duration.millis(3000));
//		}
//
//		@Override
//		protected void interpolate(double frac) {
//			setImage(FLY_END);
//			if(activated) {
//				setImage(FROG_END);
//				flyAnim.stop();
//				flyCtr-=1;
//				hasFly=false;
//				donePlaying=true;
//				
//			}
//				
//				
//		}
//	};
//		
//	flyAnim.setOnFinished(e->{
//		setImage(END);
//		flyCtr-=1;
//		donePlaying=true;
//		hasFly=false;
//	});
//		
//}



///**
// * This method handles appearance and disappearance of fly in
// * the swamp. The fly is set to disappear after a fixed time.
// * @param now  the timestamp of the current timeframe given 
// * in nanoseconds	 
// */
//
//private void handleFly(long now) {
//	
//	//if player caught fly
//	if(activated) {
//		flyCtr-=1;
//		
//	} else {
//		
//		if(now%11==0) {
//			this.flyAnimCtr++;
//		}
//		
//		if(flyAnimCtr==1) {
//			setImage(FLY_END);
//			
//		}
//		
//		if(flyAnimCtr==flyDuration) {
//			setImage(END);
//			hasFly=false;
//			flyCtr-=1;
//			this.flyAnimCtr=0;
//		}
//		
//	}
//
//}
//
//
///**
// * This method handles appearance and disappearance of crocodile in 
// * the swamp. The crocodile is set to disappear after a fixed time.
// * @param now  the timestamp of the current timeframe given 
// * in nanoseconds
// */
//private void handleCroc(long now) {
//
//	if(now%11==0) {
//		this.crocAnimCtr++;
//	}
//	
//	crocAnim();
//}

///**
// * This method handles appearance and disappearance of fly in
// * the swamp. The fly is set to disappear after a fixed time.
// * @param now  the timestamp of the current timeframe given 
// * in nanoseconds	 
// */
//
//private void handleFly(long now) {
//	
//	//if player caught fly
//	if(activated) {
//		flyCtr-=1;
//		
//	} else {
//		
//		if(now%11==0) {
//			this.flyAnimCtr++;
//		}
//		
//		if(flyAnimCtr==1) {
//			setImage(FLY_END);
//			
//		}
//		
//		if(flyAnimCtr==flyDuration) {
//			setImage(END);
//			hasFly=false;
//			flyCtr-=1;
//			this.flyAnimCtr=0;
//		}
//		
//	}
//
//}
//
//
///**
// * This method handles appearance and disappearance of crocodile in 
// * the swamp. The crocodile is set to disappear after a fixed time.
// * @param now  the timestamp of the current timeframe given 
// * in nanoseconds
// */
//private void handleCroc(long now) {
//
//	if(now%11==0) {
//		this.crocAnimCtr++;
//	}
//	
//	crocAnim();
//}




