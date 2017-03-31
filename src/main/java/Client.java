import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
import java.sql.Timestamp;

public class Client {
  private int id;
  private String name;
  private String number;
  private LocalDateTime created_at;
  private int stylist_id;

  public Client(String name, String number) {
    this.name = name;
    this.number = number;
    created_at = LocalDateTime.now();
  }

  public String getName() {
    return name;
  }

  public String getNumber() {
    return number;
  }

  public LocalDateTime getCreatedAt() {
    return this.created_at;
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

  public Timestamp getStamp() {
    Timestamp timestamp = Timestamp.valueOf(this.created_at);
    return timestamp;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, number, stylist_id, created_at) VALUES (:name, :number, :stylist_id, :created_at);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("number", this.number)
        .addParameter("stylist_id", this.stylist_id)
        .addParameter("created_at", this.getStamp())
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
}
