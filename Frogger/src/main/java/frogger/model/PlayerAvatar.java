package frogger.model;

import frogger.constant.DIRECTION;
import frogger.constant.DEATH;
import frogger.util.AudioPlayer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;

/**
 * This class implements template method, which allows subclasses
 * to create different kind of player avatar
 * @author Lim Xin Jieh (20082200)
 *
 */
public abstract class PlayerAvatar extends Actor {

	protected static final double START_YPOS = 706.467;//679.8 + FrogModel.movement;
	protected static final double START_XPOS = 300;
	protected static final double LOWER_BOUND = 173.13;
	protected static final double UPPER_BOUND = 800;
	protected static final double MOVEMENT = 13.3333333 * 2;
	protected static final double MOVEMENT_X = 10.666666 * 2;
	protected Image imgW1;
	protected Image imgA1;
	protected Image imgS1;
	protected Image imgD1;
	protected Image imgW2;
	protected Image imgA2;
	protected Image imgS2;
	protected Image imgD2;
	
	//get objects in World class by class, then check if they intersect with actor
	//pass world objects into method, then handle
	
	/**
	 * This method checks if this player object is out of visible 
	 * bounds and reset player object position accordingly
	 */
	@Override
	protected void checkOutOfBounds() {
        if (getY() > START_YPOS) {
			setY(START_YPOS);
		}	
		 if (getX() < 0 || getX() > 556) {
			double m = getX() > 556 ? 556 : 0;
			setX(m);
			
		}
    }
	
	protected abstract void initImages();
	
	/** keeps track of the number of lives left */
	protected IntegerProperty lifeProp;
	
	/** keeps track of the score of this {@code PlayerAvatar} object throughout a level */
	protected IntegerProperty scoreProp;
	
	/** keep track of the score of this {@code PlayerAvatar} object before it reaches the swamp */
	protected int tempScore = 0;

	/** type of death */
	protected DEATH deathState = DEATH.NULL;
	
	/** flag to determine if player is mid jump */
	protected boolean isJump = false;
	
	/** this determines if this {@code PlayerAvatar} object is able to move or not */
	protected boolean noMove = false; 

	
	/**
	 * This method is a public constructor which sets this 
	 * {@code PlayerAvatar} object and its properties to their starting 
	 * state, and initializes life and score of this {@code PlayerAvatar} 
	 * object
	 */
	public PlayerAvatar() {
		initImages();
		restartPlayer();
		lifeProp = new SimpleIntegerProperty(3);
		scoreProp = new SimpleIntegerProperty(0);
	}
	
	@Override
	public void act(long now) {
		
	}
	

	/**
	 * This method sets the property {@link #tempScore} which is 
	 * the temporary score that this {@code PlayerAvatar} object 
	 * accumulates while travelling across the screen
	 * @param i  temporary score of this {@code PlayerAvatar} object
	 */
	public void setTempScore(int i) {
		tempScore=i;
	}
	
	/**
	 * This method returns the boolean property {@link #noMove}
	 * which controls if this {@code PlayerAvatar} object is able 
	 * to move or not
	 * @return  true if this {@code PlayerAvatar} object cannot move
	 */
	public boolean getNoMove() {
		return noMove;
	}
	
	
	/**
	 * This method handles the jump visual and audio for this 
	 * {@code PlayerAvatar} object.
	 * 
	 * @param direction  {@link DIRECTION} representing this {@code 
	 * PlayerAvatar} jump direction
	 * @param keyPress  true if key pressed, false if key released
	 */
	public void jump(DIRECTION direction, boolean keyPress) {
		if (!noMove) {
			
			if(!keyPress && isJump) {
				isJump = false;
			} 
			
			if(keyPress) {
				AudioPlayer.INSTANCE.hopSound();
			}
		
			
			Image img;
			switch (direction) {
			case UP:
				img = isJump ?  imgW2 : imgW1;
				moveAnim(0, MOVEMENT, img);
				if(!keyPress) checkUpY();
				break;

			case LEFT:
				img = isJump ? imgA2 : imgA1;
				moveAnim(MOVEMENT_X, 0, img);
				break;

			case DOWN:
				img = isJump ? imgS2 : imgS1;
				moveAnim(0, MOVEMENT, img);
				if(!keyPress) checkDownY();
				break;

			case RIGHT:
				img = isJump ? imgD2 : imgD1;
				moveAnim(MOVEMENT_X, 0, img);
				break;
			default:
				break;

			}	
			
			isJump = isJump ? false : true;
		} 
	}

	
	/**
	 * This method resets this {@code PlayerAvatar} object and its properties
	 * to its starting state
	 */
	public void restartPlayer() {
		setStartPlayer();
		noMove = false;
		tempScore = 0;

	}
	
	/**
	 * This method sets this {@code PlayerAvatar} object in starting position
	 */
	private void setStartPlayer() {
		setImage(imgW1);
		setX(START_XPOS);
		setY(START_YPOS);
	}

	/**
	 * This method sets the property {@link #noMove} which determines if
	 * this {@code PlayerAvatar} is allowed to move or not
	 * @param bool  true to allow movement, false otherwise
	 */
	public void setNoMove(boolean bool) {
		noMove=bool;
	}


	
	/**
	 * This method sets the score of this {@code PlayerAvatar}
	 * @param i  new score of this {@code PlayerAvatar}
	 */
	public void setScore(int i) {
		scoreProp.setValue(i);
	}
	
	
	/**
	 * This method returns integer value of the score, which is
	 * {@link #scoreProp} of this {@code PlayerAvatar} object
	 * object
	 * @return  integer value of the score of this {@code PlayerAvatar} 
	 * object
	 */
	public int getScore() {
		return scoreProp.intValue();
	}
	
	/**
	 * This method adds the given integer amount to the current 
	 * score, which is {@link #scoreProp} of this {@code PlayerAvatar} 
	 * object
	 * @param x  the integer amount of points to be added
	 */
	
	public void addScore(int x) {
		scoreProp.setValue(scoreProp.intValue()+x);
	}
	
	/**
	 * This method allows other classes to add listener to 
	 * the IntegerProperty {@link #scoreProp} which corresponds to the
	 * score of this {@code PlayerAvatar} object
	 * @param listener  {@code ChangeListener} for {@code #scoreProp}
	 * 
	 */
	public void addScoreListener(ChangeListener<? super Number> listener) {
		scoreProp.addListener(listener);
	}
	
	/**
	 * This method allows other classes to add a {@code ChangeListener}
	 * which will be notified whenever there is a change in the
	 * IntegerProperty {@link #lifeProp} which corresponds to the lives
	 * of this {@code PlayerAvatar} object
	 * @param listener  {@code ChangeListener} for {@code #lifeProp}
	 */
	public void addLifeListener(ChangeListener<? super Number> listener) {
		lifeProp.addListener(listener);
	}
	
	/**
	 * This method sets the death state {@link #DEATH} of 
	 * this object
	 * @param death  {@code #DEATH} representing the death type
	 * of this {@code PlayerAvatar} object
	 */
	public void setDeath(DEATH death) {
		deathState=death;
	}
	
	/**
	 * This method gets the death state of this {@code PlayerAvatar} 
	 * object
	 * @return  {@link #DEATH} representing the death state
	 */
	public DEATH getDeath() {
		return deathState;
	}
	
	/**
	 * This method reduces the lives of this {@code PlayerAvatar} object 
	 * ({@link #lifeProp} by 1
	 */
	public void loseLife() {
		 lifeProp.setValue(lifeProp.intValue()-1);
		 System.out.println("Life left: " + lifeProp.toString());

	}
	
	/**
	 * This method enables this {@code PlayerAvatar} object to 'attach' to 
	 * another moving character by following their speed
	 * @param dx  horizontal distance to be moved
	 * @param dy  vertical distance to be moved
	 */
	public void attachPlayer(double dx, double dy) {
		move(dx, dy);
	}
	

	
	/**
	 * This method changes the x-position, y-position and image
	 * property of this {@code PlayerAvatar} object
	 * @param dx  horizontal distance to be moved
	 * @param dy  vertical distance to be moved
	 * @param value  Image object
	 */
	protected void moveAnim(double dx, double dy, Image value) {
		move(dx, dy);
		setImage(value);
	}

	/**
	 * This method is called whenever this {@code PlayerAvatar} object 
	 * dies so that the points gained is deducted. However, marks gained 
	 * by other {@code PlayerAvatar} objects will not be affected
	 */
	public void resetScore() {
		addScore(-tempScore);
		tempScore=0;
	}
	
	

	/**
	 * This method checks the downward movement of this {@code PlayerAvatar} object
	 * and deducts marks accordingly
	 */
	protected void checkDownY() {
		/*player moving downwards (but above starting pos.)
		deducts marks*/
		if (getY() < START_YPOS && getY() > LOWER_BOUND+1) { 
			addScore(-10);
			tempScore -= 10;
		}
		
	}

	/**
	 * This method checks the upward movement of this {@code PlayerAvatar} 
	 * object and adds marks accordingly
	 */
	protected void checkUpY() {
		if(getDeath()==DEATH.NULL) {
			if (getY() < UPPER_BOUND && getY() > LOWER_BOUND) {
				addScore(10);
				tempScore += 10;
			}
		}

	}


//    public abstract void handleKeyPress(KeyEvent event);
//    public abstract void handleKeyRelease(KeyEvent event);



}


//@Deprecated
//public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
//    A intersectingObj = null;
//    System.out.println("deprecated method used");
//    
//    if(getWorld()!=null) {
//    	
//    	for (A actor: getWorld().getObjects(cls)) { 
//        	//if actor of class CLS intersects this,
//            if (actor.intersects(this.getBoundsInLocal())) {
//                intersectingObj = actor;
//                break;
//            }
//        }
//    	
//    }
//    
//    return intersectingObj;
//}
//
//@Deprecated
//public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
//    ArrayList<A> someArray = new ArrayList<A>();
//    System.out.println("deprecated method used");
//    for (A actor: getWorld().getObjects(cls)) { //gameElements.getList()
//    	//get objects of class, check if each intersects with player object
//        if (actor.intersects(this.getBoundsInLocal())) {
//            someArray.add(actor);
//        }
//    }
//    return someArray;
//}