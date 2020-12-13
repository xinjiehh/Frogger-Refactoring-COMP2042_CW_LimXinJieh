package frogger.util.animation;
import frogger.constant.DIRECTION;
import frogger.model.PlayerAvatar;
import frogger.model.npc.Swamp;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * This class is responsible for showing and hiding the bonus once
 * {@link PlayerAvatar} object touches {@link Swamp} object which
 * contains a fly
 *
 */
public class MenuAnimation {
	
	private PlayerAvatar player;
	private boolean right;
	private Timeline timeline;

	public MenuAnimation(PlayerAvatar player) {
		
		this.player=player;
		initTimeline();

	}
	
	public void play() {
		timeline.play();
	}
	
	public void stop() {
		timeline.stop();
	}
	
	private void initTimeline() {
		
		timeline = new Timeline (new KeyFrame(Duration.seconds(1), 
						
				event -> {
					if (player.getX()>=555) 
						right=false;
					else if(player.getX()<=10)
					    right=true;
					
					if(right) {
						player.jump(DIRECTION.RIGHT, true);
						player.jump(DIRECTION.RIGHT, false);
					} else {
						player.jump(DIRECTION.LEFT, true);
					    player.jump(DIRECTION.LEFT, false);
					}      	
				}));
		
		timeline.setCycleCount(Animation.INDEFINITE);
	}
	

	//@Override
//	protected void initAnimation() {
//		animation = new Transition() {
//		    {
//		    	setCycleCount(INDEFINITE);
//		        setCycleDuration(Duration.seconds(3)); // total time for animation
//		    }
//
//		    @Override
//		    protected void interpolate(double fraction) {
//		    	if (player.getX()>=555) {
//					right=false;
//				} else if(player.getX()<=10){
//		            right=true;
//				}
//		        
//				if(right) {
//					player.jump(DIRECTION.RIGHT, true);
//		        } else {
//		        	player.jump(DIRECTION.LEFT, true);
//		        }
//		    }
//		};
//		
//	}


}

//
//package frogger.util.animation;
//import frogger.constant.DIRECTION;
//import frogger.model.Frog;
//import frogger.model.PlayerAvatar;
//import frogger.model.npc.Swamp;
//import javafx.animation.Transition;
//import javafx.scene.layout.Pane;
//import javafx.util.Duration;
//
///**
// * This class is responsible for showing and hiding the bonus once
// * {@link PlayerAvatar} object touches {@link Swamp} object which
// * contains a fly
// *
// */
//public class MenuAnimation extends SpriteAnimationTemplate {
//	
//	//private Frog frog = new Frog(300, 706.467);
//	private PlayerAvatar player;
//	private boolean right;
//
//	public MenuAnimation(PlayerAvatar player) {
//		super();
//		this.player=player;
//
//	}
//
//	@Override
//	protected void initAnimation() {
//		animation = new Transition() {
//		    {
//		    	setCycleCount(INDEFINITE);
//		        setCycleDuration(Duration.seconds(3)); // total time for animation
//		    }
//
//		    @Override
//		    protected void interpolate(double fraction) {
//		    	if (player.getX()>=555) {
//					right=false;
//				} else if(player.getX()<=10){
//		            right=true;
//				}
//		        
//				if(right) {
//					player.jump(DIRECTION.RIGHT, true);
//		        } else {
//		        	player.jump(DIRECTION.LEFT, true);
//		        }
//		    }
//		};
//		
//	}
//
//
//}