import oop.if2210_tb2_sc4.draggable.DraggablePane;
import org.junit.Test;
import static org.junit.Assert.*;

public class DraggablePaneTest {
    @Test
    public void testDraggablePaneCreation() {
        DraggablePane pane = new DraggablePane();
        assertNotNull(pane);
    }
}