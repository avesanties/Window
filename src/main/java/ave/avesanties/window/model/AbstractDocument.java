package ave.avesanties.window.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public abstract class AbstractDocument {

  private static int SUM_SCALE = 2;

  private String type;

  private String number;

  private LocalDate date;

  private String username;

  private BigDecimal sum;

  protected AbstractDocument() {

  }

  public String getType() {
    return type;
  }

  public String getNumber() {
    return number;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getUsername() {
    return username;
  }

  public BigDecimal getSum() {
    return sum;
  }

  protected void setType(String type) {
    this.type = type;
  }

  protected void setNumber(String number) {
    this.number = number;
  }

  protected void setDate(LocalDate date) {
    this.date = date;
  }

  protected void setUsername(String username) {
    this.username = username;
  }

  protected void setSum(BigDecimal sum) {

    this.sum = sum.setScale(SUM_SCALE, RoundingMode.HALF_UP);
  }

}
