import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;
import java.time.LocalDateTime;

public class Stylist {
  private int id;
  private String name;
  private String number;
  private LocalDateTime createdAt;


  public Stylist(String name, String number) {
    this.name = name;
    this.number = number;
    createdAt = LocalDateTime.now();
  }
}
