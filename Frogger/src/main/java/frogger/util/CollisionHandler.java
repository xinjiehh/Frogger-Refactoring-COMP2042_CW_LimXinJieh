package frogger.util;


import frogger.model.Frog;
import frogger.model.Frog.FrogDeath;
import frogger.model.NPC.Swamp;

public enum CollisionHandler {
	
	INSTANCE;
	
	public void handleDeath(Frog frog, FrogDeath death) {
		
		if(frog.getDeath()==FrogDeath.NULL) {
			System.out.println("One life lost");
			playAudio(death);
			frog.resetScore();
			DeathAnimation test = new DeathAnimation(frog, death);
			test.play();
			frog.setDeath(death);
			frog.loseLife();
		}
		
	}
	

	public void handleSwamp(Frog frog, Swamp swamp) {
		
		if(swamp.hasFly()) {
			frog.addScore(20);
			frog.setFlyBonus(true);
			frog.setBonusX(swamp.getX()+5);
		}
		
		frog.increment();
		frog.addScore(50);
		swamp.setOccupied();
		frog.restartFrog();

		
	}
	
	public void handleAttach(Frog frog, double dx) {
		frog.attachFrog(dx, 0);
	}
	
	/**
	 * This method plays death audio according to the type of death
	 * @param str -"car" for car death and "water" for water death
	 */
	private void playAudio(FrogDeath death) {
		if(death==FrogDeath.CAR)
			AudioPlayer.INSTANCE.squashSound();
		else if(death==FrogDeath.WATER)
			AudioPlayer.INSTANCE.plunkSound();
	}

}
