import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class Queue {
  private List<Client> clients;

  public Queue() {}

  public List<Client> listClients() {
    String sql = "SELECT * FROM queue";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }
}
