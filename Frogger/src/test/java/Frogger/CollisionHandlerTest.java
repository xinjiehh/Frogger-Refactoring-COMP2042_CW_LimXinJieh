package Frogger;

import frogger.constant.DEATH;
import frogger.model.Frog;
import frogger.util.CollisionHandler;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class CollisionHandlerTest extends ApplicationTest {
	


	@Test
	public void testHandleDeath_checkScoreEquals_true() {
		Frog frog = new Frog(600,600);
		frog.addScore(100);
		frog.setTempScore(10);
		CollisionHandler.INSTANCE.handleDeath(frog, DEATH.CAR);
		assertEquals(frog.getScore(),90);
	}

}
