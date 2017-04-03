import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public class Client {
  private int id;
  private String name;
  private String number;
  private int stylist_id;
  private static ArrayList<Client> queue;

  public Client(String name, String number, int stylist_id) {
    this.name = name;
    this.number = number;
    this.stylist_id = stylist_id;
  }

  public static void createQueue() {
    ArrayList<Client> queue = new ArrayList<Client>();
    Client.queue = queue;
  }

  public String getName() {
    return name;
  }

  public String getNumber() {
    return number;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) && this.getNumber().equals(newClient.getNumber());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, number, stylist_id) VALUES (:name, :number, :stylist_id);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("number", this.number)
        .addParameter("stylist_id", this.stylist_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void assign(int id, int stylist_id) {
    String sql = "UPDATE clients SET stylist_id = :stylist_id WHERE id=:id;";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id", id)
      .addParameter("stylist_id", stylist_id)
      .executeUpdate();
    }
  }

  public int getId() {
    return id;
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id;";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public int getStylistId() {
    return stylist_id;
  }

  public void update(String name, String number) {
    try(Connection con = DB.sql2o.open()) {
      String sqlName = "UPDATE clients SET name = :name WHERE id = :id;";
      String sqlNumber = "UPDATE clients SET number = :number WHERE id = :id;";
      con.createQuery(sqlName)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
      con.createQuery(sqlNumber)
        .addParameter("number", number)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

  public static ArrayList<Client> getQueue() {
    return Client.queue;
  }

  public void addToQueue() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET stylist_id=0 WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }


}
