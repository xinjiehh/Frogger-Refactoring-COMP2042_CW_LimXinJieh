package frogger.model.NPC;

import frogger.constant.FilePath;
import javafx.scene.image.Image;

public class Turtle extends Actor{
	private static final int SIZE = 130;
	private static final Image turtle1 = new Image(FilePath.TURTLE1,SIZE,SIZE,true,true);
	private static final Image turtle2 = new Image(FilePath.TURTLE2_DRY,SIZE,SIZE,true,true);
	private static final Image turtle3 = new Image(FilePath.TURTLE3_DRY,SIZE,SIZE,true,true);
	

	int i = 1;
	boolean bool = true;
	@Override
	public void act(long now) {

				if (now/900000000  % 3 ==0) {
					setImage(turtle2);
					
				}
				else if (now/900000000 % 3 == 1) {
					setImage(turtle1);
					
				}
				else if (now/900000000 %3 == 2) {
					setImage(turtle3);
					
				}
			
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}
	

	public Turtle() {
		setImage(turtle2);
	}
}
