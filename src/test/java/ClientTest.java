import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.time.LocalDateTime;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly_true() {
    Client testClient = new Client("Colin", "678-446-9966");
    assertEquals(true, testClient instanceof Client);
  }

  @Test
  public void getName_clientInstantiatesWithName_String() {
    Client testClient = new Client("Colin", "678-446-9966");
    assertEquals("Colin", testClient.getName());
  }

  @Test
  public void getNumber_clientInstantiatesWithNumber_String() {
    Client testClient = new Client("Colin", "678-446-9966");
    assertEquals("678-446-9966", testClient.getNumber());
  }

  @Test
  public void equals_returnsTrueIfNameAndNumberAreSame() {
    Client firstClient = new Client("Colin", "678-446-9966");
    Client secondClient = new Client("Colin", "678-446-9966");
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client testClient = new Client("Deborah", "404-644-6644");
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Deborah", "404-644-6644");
    firstClient.save();
    Client secondClient = new Client("Colin", "678-446-9966");
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

}
