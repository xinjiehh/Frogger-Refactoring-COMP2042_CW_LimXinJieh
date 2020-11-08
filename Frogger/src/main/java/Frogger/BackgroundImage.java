package Frogger;

import java.io.File;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;

//actor is imageView
public class BackgroundImage extends Actor {

	@Override
	public void act(long now) {
		
		
	}
	
	public BackgroundImage() {
		setImage(new Image("file:src/main/resources/gameBackground.png", 600, 800, false, true));
		adjustColor(); 
		
	}
	
	
	public BackgroundImage(String link) {
		setImage(new Image(link, 800, 800, false, true));
		
	}
	
	
	/**
	 * reference see <a href="https://www.tutorialspoint.com/javafx/color_adjust_effect.htm">tutorialspoint</a>
	 * 
	 */
	private void adjustColor() {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setContrast(2);     
	    setEffect(colorAdjust);  //Applying color adjust effect to the ImageView node 
	}



}
