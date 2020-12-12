package Frogger;

import frogger.model.PlayerAvatar;
import frogger.model.npc.NPC;
import frogger.model.npc.Swamp;
import frogger.util.GameGenerator;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.List;

import static org.junit.Assert.*;

public class GameGeneratorTest extends ApplicationTest {
	
	private GameGenerator generator;
	private List<Node> list;
	private int npcCount = 0;
	private int playerCount = 0;
	private int swampCount = 0;
	
	@Override
	public void start(Stage stage) {
		generator = new GameGenerator(1);
		list = generator.getList();
		for(Node n : list) {
			if(n instanceof NPC) {
				npcCount++;
				if(n instanceof Swamp)
					swampCount++;
			} else if(n instanceof PlayerAvatar)
				playerCount++;
		}
	}
	

	@Test
	public void testListNotEmpty() {
		assertFalse(list.isEmpty());
		
	}
	
	@Test
	public void testListHasNPC() {
		assertTrue(npcCount>10);
	}
	
	@Test
	public void testListHasPlayer() {
		assertTrue(playerCount>0);
	}
	
	@Test
	public void testListHasFiveSwamp() {
		assertEquals(5,swampCount);
	}


}
