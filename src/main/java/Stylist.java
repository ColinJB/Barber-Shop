import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class Stylist {
  private int id;
  private String name;
  private String number;
  private LocalDateTime created_at;


  public Stylist(String name, String number) {
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
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) && this.getNumber().equals(newStylist.getNumber());
    }
  }

  public Timestamp getStamp() {
    Timestamp timestamp = Timestamp.valueOf(this.created_at);
    return timestamp;
  }

  // public String getDate(created_at) {
  //   String date = new SimpleDateFormat("MM/dd/yyyy").format(created_at);
  // }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, number, created_at) VALUES (:name, :number, :created_at);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("number", this.number)
        .addParameter("created_at", this.getStamp())
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

  public static Stylist find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id;";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Stylist.class);
      return stylist;
    }
  }
}
