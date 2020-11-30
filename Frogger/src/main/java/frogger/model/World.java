package frogger.model;


import frogger.controller.SelectionController.Controls;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import frogger.constant.AudioPlayer;
import frogger.constant.Direction;
import frogger.controller.test.CollisionDetector;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

//model?
//public void run(long now) {
//for (AutomaticActor automaticActor : automaticActors) automaticActor.act(now);
//for (End end : ends) end.act(now);
//frogA.act(now);
//TouchChecker.INSTANCE.touchActor(frogA, automaticActors, ends);
//if (frogB != null) {
//frogB.act(now);
//TouchChecker.INSTANCE.touchActor(frogB, automaticActors, ends);
//}
//}
public class World extends Pane {
    private AnimationTimer motionTimer = null;
    private Frog frog;
    private Map<KeyCode,Direction> direction;
    
    public World(Controls control) {
    	if(control==Controls.A)
    		direction = DIRECTION_A;
    	else if(control==Controls.B)
    		direction = DIRECTION_B;
    	sceneProperty().addListener((obs,oldVal,newVal) -> {createNewListeners(newVal);});
  
    }
    	

	private void createNewListeners(Scene newValue) {
		
//		if(newValue!=null) {
//			newValue.setOnKeyReleased((keyEvent) -> {
//				
//				if(getOnKeyReleased() != null) 
//					getOnKeyReleased().handle(keyEvent);
//				
//				List<Actor> myActors = getObjects(Actor.class);
//				
//				for (Actor anActor: myActors) {
//					if (anActor.getOnKeyReleased() != null) {
//						anActor.getOnKeyReleased().handle(keyEvent);
//					}
//				}
//
//			
//		});
//		
//
//		
//		newValue.setOnKeyPressed((keyEvent) -> {
//
//			if(getOnKeyPressed() != null) 
//				getOnKeyPressed().handle(keyEvent);
//			List<Actor> myActors = getObjects(Actor.class);
//			
//			for (Actor anActor: myActors) {
//				if (anActor.getOnKeyPressed() != null) {
//					anActor.getOnKeyPressed().handle(keyEvent);
//				}
//			}
//
//		});
//		}
//		
		
	}

    public void createMotionTimer() {
        motionTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //act(now);
                List<Actor> actors = getObjects(Actor.class);

                for (Actor anActor: actors) {
                	anActor.act(now);
                	
                	if(anActor instanceof Frog) {
                		frog = (Frog) anActor;
                	 	CollisionDetector.INSTANCE.checkIntersectingTest(frog, actors);

                	}
                }
                
               
            }
        };
    }
 
    

    public void startMotion() {
    	
    	if(motionTimer!=null) {
    		motionTimer.start();
    		
    	} else {
    		createMotionTimer();
    		motionTimer.start();
    	}
      
    }

    public void stopMotion() {
        motionTimer.stop();
    }
    
    //TODO changes Actor to ImageView to distinguish background from other
    //game elements
    public void add(Node element) {
        getChildren().add(element);
    }

    
    public void remove(Node element) {
        getChildren().remove(element);
    }

    
    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A)n);
               
            }
        }
        return someArray;
    }
    
    
    public void handleKeyTest(KeyEvent event, boolean keyPress) {
		if(frog!=null) {
			moveFrog(event.getCode(),keyPress);
		}

	}
    
    private void moveFrog(KeyCode code, boolean keyPress) { //judge jump direction
    	if(!direction.containsKey(code)) return; 
    	else frog.jump(direction.get(code), keyPress);
    	
    }

    public static final Map<KeyCode, Direction> DIRECTION_A =
    		new HashMap<>() {
    	        {
    	          put(KeyCode.W, Direction.UP);
    	          put(KeyCode.A, Direction.LEFT);
    	          put(KeyCode.S, Direction.DOWN);
    	          put(KeyCode.D, Direction.RIGHT);
    	        }
    	      };

    public static final Map<KeyCode, Direction> DIRECTION_B =
    		new HashMap<>() {
    			{
    				put(KeyCode.UP, Direction.UP);
	    	    	put(KeyCode.LEFT, Direction.LEFT);
	    	    	put(KeyCode.DOWN, Direction.DOWN);
	    	    	put(KeyCode.RIGHT, Direction.RIGHT);
	    	    }
	    	};
	    	
    	
      
      
      
      
      
      
      
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
