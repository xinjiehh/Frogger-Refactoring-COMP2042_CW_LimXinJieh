package Frogger;

import frogger.model.Lane;
import frogger.model.npc.NPC;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LaneTest extends ApplicationTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowExceptionLaneExceeded() {
		new Lane(10);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowExceptionLaneDoesNotExist() {
		new Lane(0);
	}
	
	@Test
	public void testCreateNewLane() {
		for(int i=1;i<10;i++)
			new Lane(i);
	}
	
	@Test
	public void createNewLaneElement_size_speed_equals() {
		Lane lane = new Lane(3);
		int nBefore=lane.getElementsList().size();
		lane.addElements(3, 2);
		ArrayList<NPC> list = lane.getElementsList();
		assertEquals(list.size(),nBefore+3);
		assertEquals(list.get(0).getSpeed(),2.00,0);

	}
	

	

}
