package frogger.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is an implementation of the Observer pattern. 
 * This class maintains a list of all interested observers so that 
 * {@link #notify()} can loop through the list of all {@link Observer} 
 * and invoke the {@link Observer#update(String, Subject)} method on each 
 * registered observer. The observers register and unregister for 
 * updates by calling the subscribe() and unsubscribe() methods on 
 * Subject. <p>
 * 
 * Any classes responsible for changing the events implement `Subject` 
 * and notifies the subscribers via {@link #notify()}. <p>
 * 
 * One or more instances of classes implementing {@code Observer}
 * may also access classes implementing {@code Subject} for more 
 * information and therefore usually depend on those concrete class. 
 * However, there is no direct or indirect dependency from the concrete
 * {@code Subject} class on the concrete {@code Observer} class.
 *
 */

public interface Subject {


	Map<String, ArrayList<Observer>> listeners = new LinkedHashMap<>();
	   
    default void addEvent(String... operations) {
    	for (String operation : operations) {
    		if(!listeners.containsKey(operation)) {
        		listeners.put(operation, new ArrayList<Observer>());
    		}	
      }
    	
    }
    //add listener to different properties
    static void subscribe(String eventType, Observer listener) {
    	
    	if(!listeners.containsKey(eventType)) {
    		ArrayList<Observer> list = new ArrayList<Observer>();
    		list.add(listener);
    		listeners.put(eventType, list);
    		
		} else {
			
			List<Observer> users = listeners.get(eventType);
			if(!users.contains(listener)) 
	        	   users.add(listener);
		}
    }
    
    //unsubscribe
    default void unsubscribe(String eventType, Observer listener) {
        List<Observer> users = listeners.get(eventType);
        users.remove(listener);
    }
    
    //notify all the listeners of specific property,
    default void notify(String eventType, Subject s) {
    	List<Observer> users = listeners.get(eventType);
    	if(users!=null) {
            for (Observer listener : users) {
                listener.update(eventType, s);
            }
    	}
    	
    }
}
