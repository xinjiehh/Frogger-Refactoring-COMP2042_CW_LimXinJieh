package frogger.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frogger.constant.DIRECTION;
import frogger.controller.SelectionController.Controls;
import frogger.model.npc.NPC;
import frogger.util.CollisionDetector;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public class World extends Pane {
	
    private AnimationTimer motionTimer = null;
    private PlayerAvatar player;
    
    /** the set of directions selected */
    private Map<KeyCode,DIRECTION> direction;
    
    /** defines movement using WASD keys */
    private static final Map<KeyCode, DIRECTION> DIRECTION_A =
    		new HashMap<>() {
    	        {
    	          put(KeyCode.W, DIRECTION.UP);
    	          put(KeyCode.A, DIRECTION.LEFT);
    	          put(KeyCode.S, DIRECTION.DOWN);
    	          put(KeyCode.D, DIRECTION.RIGHT);
    	        }
    	      };
    	      
    /** defines movement using arrow keys */	    
    private static final Map<KeyCode, DIRECTION> DIRECTION_B =
    		new HashMap<>() {
    			{
    				put(KeyCode.UP, DIRECTION.UP);
	    	    	put(KeyCode.LEFT, DIRECTION.LEFT);
	    	    	put(KeyCode.DOWN, DIRECTION.DOWN);
	    	    	put(KeyCode.RIGHT, DIRECTION.RIGHT);
	    	    }
	    	};
    
	    	
	/**
	 * This is the public constructor which initializes the 
	 * {@link Controls} chosen by the user from selection screen
	 * @param control  {@code Controls.A} for WASD keys, {@code 
	 * Controls.B} for arrow keys
	 */
    public World(Controls control) {
    	if(control==Controls.A)
    		direction = DIRECTION_A;
    	else if(control==Controls.B)
    		direction = DIRECTION_B;
    	sceneProperty().addListener(this::createNewListeners);
  
    }


	
    /**
     * This method creates an {@code AnimationTimer} 
     * responsible for {@link NPC} animation and
     * calling for {@link CollisionDetector} to check
     * if any {@link PlayerAvatar} object collides with
     * {@code NPC} objects in this {@code World} object
     */
    public void createMotionTimer() {
        motionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	
                List<NPC> npcs = getObjects(NPC.class);

                for (NPC npc: npcs) {
                	npc.act(now);
                }
                
                List<PlayerAvatar> players = getObjects(PlayerAvatar.class);
                
                
                for(PlayerAvatar p : players) {
                	player = p;
                	CollisionDetector.INSTANCE.checkIntersect(player, npcs);
                	
                		
                }

            }
        };
    }
 
    
    /**
     * This method starts the collision detection and 
     * animation of {@link NPC} objects in this {@link 
     * World} object by starting the motion timer
     */
    public void startMotion() {

        if (motionTimer == null) {
            createMotionTimer();
        }
        motionTimer.start();

    }

    /**
     * This method is called whenever game is exited 
     * or paused to stop the motion timer
     */
    public void stopMotion() {
        motionTimer.stop();
    }
    
    /**
     * This method is used to show {@code Node} by adding 
     * {@code Node} to this {@code World} object
     * @param element  {@code Node} to be added
     */
    public void add(Node element) {
        getChildren().add(element);
    }
    
    public void addAll(Collection<Node> c) {
    	getChildren().addAll(c);
    }

    /**
     * This method is used to hide {@code Node} by removing 
     * {@code Node} from this {@code World} object
     * @param element  {@code Node} to be removed
     */
    public void remove(Node element) {
        getChildren().remove(element);
    }
    
    
    /**
     * This method gets the {@code Node} children of this 
     * {@code World} object that belongs to a specific class
     * @param <A>  the class of objects to be retrieved
     * @param cls  the class literal of the objects to be retrieved
     * @return {@code List} of objects of class A
     */
    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A)n);
               
            }
        }
        return someArray;
    }
    
    
    /**
     * This method handles key press and key release events
     * @param event  any {@code KeyEvent} 
     * @param keyPress  true if key press, false if key release
     */
    public void handleKey(KeyEvent event, boolean keyPress) {
		if(player!=null) {
			moveFrog(event.getCode(),keyPress);
		}

	}
    
    /**
     * This method handles filtering of keys to control frog 
     * movement 
     * @param code  {@code KeyCode} of the {@code KeyEvent}
     * @param keyPress  true if key press, false if key release
     */
    private void moveFrog(KeyCode code, boolean keyPress) { //judge jump direction
    	if(direction.containsKey(code))
    	    player.jump(direction.get(code), keyPress);
    	
    }
    
    /**
     * This method adds listeners to the {@code Scene} that this
     * {@code Node} is a part of to handle key press and key release 
     * of the {@link Actor} objects of this {@code Node}
     * 
     * @param observable  the {@code Scene} object that this {@code 
     * Node} is a part of
     * @param oldValue  the old {@code Scene}
     * @param newValue  the new {@code Scene}
     */
    private void createNewListeners(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
		
		if(newValue!=null) {
			newValue.setOnKeyReleased((keyEvent) -> {
				
				if(getOnKeyReleased() != null) 
					getOnKeyReleased().handle(keyEvent);
				
				List<Actor> myActors = getObjects(Actor.class);
				
				for (Actor anActor: myActors) {
					if (anActor.getOnKeyReleased() != null) {
						anActor.getOnKeyReleased().handle(keyEvent);
					}
				}

			
			});
			
			newValue.setOnKeyPressed((keyEvent) -> {
	
				if(getOnKeyPressed() != null) 
					getOnKeyPressed().handle(keyEvent);
				List<Actor> myActors = getObjects(Actor.class);
				
				for (Actor anActor: myActors) {
					if (anActor.getOnKeyPressed() != null) {
						anActor.getOnKeyPressed().handle(keyEvent);
					}
				}
	
			});
		}
		
		
	}     
      
      
      
      
      
    //public abstract void act(long now);
}
//List<Frog> frogs = getObjects(Frog.class);

//CollisionDetector.INSTANCE.checkIntersecting(frogs.get(0));


//sceneProperty().addListener(new ChangeListener<Scene>() {
//
//	@Override
//	public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
//		if (newValue != null) {
//			createNewListeners(newValue);
//		}
//	}
//
//});


//private void touchAllEnd() {
//if (gameMode == GameMode.DOUBLE) {
//  activatedEnds.forEach(End::resetActor);
//}
//}

//newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
//
//	@Override
//	public void handle(KeyEvent event) {
//		if(getOnKeyReleased() != null) 
//			getOnKeyReleased().handle(event);
//		List<Actor> myActors = getObjects(Actor.class);
//		for (Actor anActor: myActors) {
//			if (anActor.getOnKeyReleased() != null) {
//				anActor.getOnKeyReleased().handle(event);
//			}
//		}
//	}
//	
//});
