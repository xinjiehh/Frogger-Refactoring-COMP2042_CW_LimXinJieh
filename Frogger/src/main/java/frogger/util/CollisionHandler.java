package frogger.util;

import frogger.constant.DEATH;
import frogger.constant.EndGame;
import frogger.constant.settings.Settings;
import frogger.controller.GameController;
import frogger.model.PlayerAvatar;
import frogger.model.npc.Log;
import frogger.model.npc.NPC;
import frogger.model.npc.Swamp;
import frogger.model.npc.Turtle;

/**
 * This util class implements SINGLETON pattern. It is responsible for handling collision
 * between {@link PlayerAvatar} and {@link NPC} objects and only one instance is needed per JVM.
 */
public enum CollisionHandler {
	/** singleton instance for this class */
	INSTANCE;
	
	private int swampFrog=0;
	
	
	/**
	 * This method handles the death of the {@link PlayerAvatar}
	 * by calling the corresponding audio and animation, and
	 * resetting the {@code PlayerAvatar} state and properties
	 * @param frog  {@code PlayerAvatar}
	 * @param death  {@link DEATH} state
	 */
	public void handleDeath(PlayerAvatar frog, DEATH death) {
		
		frog.handleDeath(death);
		
		
		
	}
	
	/**
	 * This method handles when {@link PlayerAvatar}
	 * comes into contact with a {@link Swamp} object
	 * @param frog  {@code PlayerAvatar} 
	 * @param swamp  {@code Swamp} touched by {@code PlayerAvatar} 
	 */
	public void handleSwamp(PlayerAvatar frog, Swamp swamp) {
		
		if(swamp.hasFly()) {
			frog.addScore(100);
			GameController.INSTANCE.showBonus(swamp.getX()+5);
		}
		
		swampFrog++;
		swamp.setOccupied();
		frog.addScore(50);
		frog.restartPlayer();
		if(swampFrog==Settings.MAX_FROG) {
			swampFrog=0;
			GameController.INSTANCE.handleGameDone(EndGame.NEXT);
		}

	}
	
	
	/**
	 * This method handles when {@link PlayerAvatar} intersects
	 * with {@link Log} or {@link Turtle}
	 * @param frog  {@code PlayerAvatar}
	 * @param dx  speed of intersecting
	 * {@link NPC}
	 */
	public void handleAttach(PlayerAvatar frog, double dx) {
		frog.attachPlayer(dx, 0);
	}
	



}
