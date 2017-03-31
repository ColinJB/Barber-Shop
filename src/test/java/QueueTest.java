import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.time.LocalDateTime;

public class QueueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void queue_instantiatesCorrectly() {
    Queue queue = new Queue();
    assertTrue(queue instanceof Queue);
  }
}
