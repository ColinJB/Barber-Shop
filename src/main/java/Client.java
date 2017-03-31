import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private int id;
  private String name;
  private String number;
  private LocalDateTime createdAt;
  private int stylist_id;

  public Client(String name, String number) {
    this.name = name;
    this.number = number;
    createdAt = LocalDateTime.now();
  }
}
