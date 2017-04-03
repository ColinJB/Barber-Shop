import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class Stylist {
  private int id;
  private String name;
  private String number;


  public Stylist(String name, String number) {
    this.name = name;
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public String getNumber() {
    return number;
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) && this.getNumber().equals(newStylist.getNumber());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, number) VALUES (:name, :number);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("number", this.number)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Stylist> all() {
    String sql = "SELECT * FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int newId) {
    this.id = newId;
  }

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id;";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id=:id;";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Client.class);
    }
  }

  public void update(String name, String number) {
    try(Connection con = DB.sql2o.open()) {
      String sqlName = "UPDATE stylists SET name = :name WHERE id = :id;";
      String sqlNumber = "UPDATE stylists SET number = :number WHERE id = :id;";
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
    List<Client> clients = new ArrayList<Client>();
    clients = this.getClients();
    for ( Client eachClient : clients ) {
      eachClient.addToQueue();
    }
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }
}
