package frogger.util;


import frogger.controller.GameController;
import frogger.model.Frog;
import frogger.model.Frog.FrogDeath;
import frogger.model.NPC.Swamp;

public enum CollisionHandler {
	
	INSTANCE;
	private static final int MAX_FROG = 5;
	private int swampFrog=0;
	
	public void handleDeath(Frog frog, FrogDeath death) {
		
		if(frog.getDeath()==FrogDeath.NULL) {
			System.out.println("One life lost");
			playAudio(death);
			frog.resetScore();
			DeathAnimation test = new DeathAnimation(frog, death);
			test.play();
			frog.setDeath(death);
			frog.loseLife();
			
			if(frog.getLife()==-1) {
				GameController.INSTANCE.handleGameOver();
			}
		}
		
	}
	

	public void handleSwamp(Frog frog, Swamp swamp) {
		
		if(swamp.hasFly()) {
			frog.addScore(100);
			GameController.INSTANCE.showBonus();
			
			frog.setBonusX(swamp.getX()+5);
		}
		
		swampFrog++;
		frog.addScore(50);
		swamp.setOccupied();
		frog.restartFrog();
		if(swampFrog==MAX_FROG) {
			swampFrog=0;
			GameController.INSTANCE.handleDoneLevel();
		}

	}
	
	
	
	public void handleAttach(Frog frog, double dx) {
		frog.attachFrog(dx, 0);
	}
	

	/**
	 * This method plays death audio according to the type of death
	 * @param death  type of frog death
	 */
	private void playAudio(FrogDeath death) {
		if(death==FrogDeath.CAR)
			AudioPlayer.INSTANCE.squashSound();
		else if(death==FrogDeath.WATER)
			AudioPlayer.INSTANCE.plunkSound();
	}

}
