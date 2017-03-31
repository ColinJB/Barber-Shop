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
    Client testClient = new Client("Colin", "678-446-9966", 1);
    assertEquals(true, testClient instanceof Client);
  }

  @Test
  public void getName_clientInstantiatesWithName_String() {
    Client testClient = new Client("Colin", "678-446-9966", 1);
    assertEquals("Colin", testClient.getName());
  }

  @Test
  public void getNumber_clientInstantiatesWithNumber_String() {
    Client testClient = new Client("Colin", "678-446-9966", 1);
    assertEquals("678-446-9966", testClient.getNumber());
  }

  @Test
  public void equals_returnsTrueIfNameAndNumberAreSame() {
    Client firstClient = new Client("Colin", "678-446-9966", 1);
    Client secondClient = new Client("Colin", "678-446-9966", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Client testClient = new Client("Deborah", "404-644-6644", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Deborah", "404-644-6644", 1);
    firstClient.save();
    Client secondClient = new Client("Colin", "678-446-9966", 1);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void getId_returnsIdFromDatabase() {
    Client testClient = new Client("Deborah", "404-644-6644", 1);
    testClient.save();
    assertTrue(Client.all().get(0).getId() > 0);
  }

  @Test
  public void find_returnsClientWithSameId_true() {
    Client testClient = new Client("Deborah", "404-644-6644", 1);
    testClient.save();
    assertEquals(testClient, Client.find(testClient.getId()));
  }

  @Test
  public void getStylistId_returnsStylistIdFromDatabase_true() {
    Stylist testStylist = new Stylist("Colin", "678-446-9966");
    Client testClient = new Client("Deborah", "404-644-6644", 1);
    testClient.save();
    testStylist.save();
    testStylist.setId(1);
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), testStylist.getId());
  }

  @Test
  public void update_updatesClientInformation_true() {
    Client testClient = new Client("Deborah", "404-644-6644", 1);
    testClient.save();
    testClient.update("Deb", "888");
    assertEquals(Client.find(testClient.getId()).getName(), "Deb");
    assertEquals(Client.find(testClient.getId()).getNumber(), "888");
  }

  @Test
  public void delete_deletesClient_true() {
    Client testClient = new Client("Deborah", "404-644-6644", 1);
    testClient.save();
    testClient.delete();
    assertEquals(null, Client.find(testClient.getId()));
  }

}
