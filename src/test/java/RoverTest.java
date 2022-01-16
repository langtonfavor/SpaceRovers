import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoverTest {
    private Rover rover;
    @BeforeEach
    public void setUp(){
        rover= new Rover(5,5);
    }
    @Test
    public void testProcessCommand(){
        rover.processCommand("1 2 N");
        rover.processCommand("LMLMLMLMM");
        assertEquals("1 3 N",rover.toString());
    }
}
