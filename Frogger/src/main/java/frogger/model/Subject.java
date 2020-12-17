package frogger.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is an implementation of the Observer pattern. 
 * This class maintains a list of all interested observers so that 
 * {@link #notify} can loop through the list of all {@link Observer}
 * and invoke the {@link Observer#update} method on each 
 * registered observer. The observers register and unregister for 
 * updates by calling the {@link #subscribe} and {@link #unsubscribe}
 * methods on Subject. <p>
 * 
 * Any classes responsible for changing the events implement {@code Subject} 
 * and notifies the subscribers via {@link #notify}. <p>
 * 
 * One or more instances of classes implementing {@code Observer}
 * may also access classes implementing {@code Subject} for more 
 * information and therefore usually depend on those concrete class. 
 * However, there is no direct or indirect dependency from the concrete
 * {@code Subject} class on the concrete {@code Observer} class.
 *
 */

public interface Subject {

	/**
	 * List of events and their corresponding subscribers
	 */
	Map<String, ArrayList<Observer>> listeners = new LinkedHashMap<>();
	   
	/**
	 * This method adds new event entry in {@code listeners}
	 * @param operations  the {@code String} id of the events
	 */
    default void addEvent(String... operations) {
    	for (String operation : operations) {
    		if(!listeners.containsKey(operation)) {
        		listeners.put(operation, new ArrayList<Observer>());
    		}	
      }
    	
    }
    
    /**
     * This method subscribes to particular event by adding the listeners
     * of type {@link Observer} to the corresponding listener list from 
     * {@code listeners} 
     * 
     * @param listener  the interested {@code Observer} object
     * @param eventTypes  {@code String} denoting the type of event
     */
    static void subscribe(Observer listener, String... eventTypes) {
    	for(String eventType : eventTypes) {
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
    	
    }
    
    /**
     * This method unsubscribes from a particular event by removing the 
     * listeners of type {@link Observer} from the corresponding listener 
     * list from {@code listeners} 
     * 
     * @param listener  the unsubscribing {@code Observer} object
     * @param eventType  {@code String} denoting the type of event
     */
    default void unsubscribe(Observer listener, String eventType) {
        List<Observer> users = listeners.get(eventType);
        users.remove(listener);
    }
    
    /**
     * This method notifies all the subscribers / listeners of type 
     * {@link Observer} registered in {@code listeners} of a change
     * in value / state.
     * 
     * @param eventType  {@code String} denoting the type of event
     * @param s  this {@code Subject} object which state/value has 
     * changed
     */
    default void notify(String eventType, Subject s) {
    	List<Observer> users = listeners.get(eventType);
    	if(users!=null) {
            for (Observer listener : users) {
                listener.update(eventType, s);
            }
    	}
    	
    }
}
