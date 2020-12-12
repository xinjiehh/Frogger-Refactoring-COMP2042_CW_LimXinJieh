package Frogger;

import frogger.model.Frog;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class FrogTest extends ApplicationTest {
	
	private Frog frog;

	@Override
	public void start(Stage stage) {
		frog = new Frog(600,600);
		
	}

	@Test
	public void getStartScore_equals_zero() {
		assertEquals(0,frog.getScore());
	}
	
	@Test
	public void getNoMove_equals_false() {
		assertFalse(frog.getNoMove());
	}
	
	@Test
	public void createTwoFrogs_notEquals() {
		Frog frog2 = new Frog(700,600);
		assertNotSame(frog,frog2);
		
	}
	
	@Test
	public void calcResetScore_equals() {
		frog.addScore(100);
		frog.setTempScore(10);
		frog.resetScore();
		assertEquals(90,frog.getScore());
		
	}
	
	@Test
	public void calcNewScore_equals() {
		frog.addScore(90);
		frog.setTempScore(10);
		frog.resetScore();
		frog.addScore(10);
		assertEquals(90,frog.getScore());
	}
	
	@Test
	public void moveOutOfBounds_true() {
		frog.move(700,0);
		assertTrue(frog.getX()<=600);
	}
	
	@Test
	public void moveOutOfBounds_equals() {
		frog.move(0,900);
		assertTrue(frog.getY()<=800);
	}

	


	

}
