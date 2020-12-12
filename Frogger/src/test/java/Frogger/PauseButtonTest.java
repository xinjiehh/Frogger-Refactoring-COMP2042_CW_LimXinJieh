package Frogger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import frogger.util.buttons.PauseButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PauseButtonTest extends ApplicationTest {

    private Button button;


    @Override
    public void start(Stage stage) {
        button = new PauseButton();
        stage.setScene(new Scene(new StackPane(button), 200, 200));
        stage.show();
    }


    @Test
    public void testSwitchStyleOnButtonClick_equals_true() {
    	String id = button.getId();
        clickOn(".button");
        String id2 = button.getId();
        assertNotEquals(id,id2);
        clickOn(".button");
        String id3 = button.getId();
        assertEquals(id,id3);
        
    }
    
    
}