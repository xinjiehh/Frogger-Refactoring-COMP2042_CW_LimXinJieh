package Frogger;

import frogger.util.buttons.MenuButton;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MenuButtonTest extends ApplicationTest {

    private MenuButton button;

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     */
    @Override
    public void start(Stage stage) {
        button = new MenuButton("test");
        button.setOnAction(actionEvent -> button.setText("clicked!"));
        stage.setScene(new Scene(new StackPane(button), 200, 200));
        stage.show();
    }

    @Test
    public void testButtonContainsText_true() {
    	assertFalse(button.getText().isEmpty());
    }

    @Test
    public void testButtonClick_true() {
        clickOn(".button");
        System.out.println(button.getText());
        assertEquals("clicked!",button.getText());
    }

}