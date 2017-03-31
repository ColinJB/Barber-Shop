import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.time.LocalDateTime;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Deborah", "404-644-6644");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_String() {
    Stylist testStylist = new Stylist("Deborah", "404-644-6644");
    assertEquals("Deborah", testStylist.getName());
  }

  @Test
  public void getNumber_stylistInstantiatesWithNumber_String() {
    Stylist testStylist = new Stylist("Deborah", "404-644-6644");
    assertEquals("404-644-6644", testStylist.getNumber());
  }

  @Test
  public void equals_returnsTrueIfNameAndNumberAreSame() {
    Stylist firstStylist = new Stylist("Deborah", "404-644-6644");
    Stylist secondStylist = new Stylist("Deborah", "404-644-6644");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist testStylist = new Stylist("Deborah", "404-644-6644");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Deborah", "404-644-6644");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Colin", "678-446-9966");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }

  @Test
  public void getId_returnsIdFromDatabase() {
    Stylist testStylist = new Stylist("Deborah", "404-644-6644");
    testStylist.save();
    assertTrue(Stylist.all().get(0).getId() > 0);
  }

  @Test
  public void find_returnsStylistWithSameId_true() {
    Stylist testStylist = new Stylist("Deborah", "404-644-6644");
    testStylist.save();
    assertEquals(testStylist, Stylist.find(testStylist.getId()));
  }

  @Test
  public void getClients_returnsListOfAllClientsWithSameStylistId() {
    Stylist testStylist = new Stylist("Deborah", "404-644-6644");
    testStylist.save();
    Client firstClient = new Client("Colin", "555", testStylist.getId());
    Client secondClient = new Client("Caitlin", "777", testStylist.getId());
    firstClient.save();
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(testStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
