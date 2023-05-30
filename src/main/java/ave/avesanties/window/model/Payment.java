package ave.avesanties.window.model;

import static ave.avesanties.window.constants.Constants.DATE_PATTERN;

import ave.avesanties.window.model.builders.DateDocumentBuilder;
import ave.avesanties.window.model.builders.EmployeeDocumentBuilder;
import ave.avesanties.window.model.builders.FinalDocumentBuilder;
import ave.avesanties.window.model.builders.NumberDocumentBuilder;
import ave.avesanties.window.model.builders.SumDocumentBuilder;
import ave.avesanties.window.model.builders.UsernameDocumentBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payment extends AbstractDocument {

  private String employee;

  private Payment() {

  }

  public String getEmployee() {
    return employee;
  }

  private void setEmployee(String employee) {
    this.employee = employee;
  }

  public static class Builder
      implements DateDocumentBuilder, UsernameDocumentBuilder, SumDocumentBuilder,
      EmployeeDocumentBuilder, NumberDocumentBuilder, FinalDocumentBuilder<Payment> {

    private static final String TYPE = "Платёжка";

    private String number;

    private LocalDate date;

    private String username;

    private BigDecimal sum;

    private String employee;

    private Builder() {

    }

    public static EmployeeDocumentBuilder createBuilder() {
      return new Builder();
    }

    @Override
    public Payment build() {
      Payment Payment = new Payment();
      Payment.setType(TYPE);
      Payment.setDate(date);
      Payment.setEmployee(employee);
      Payment.setNumber(number);
      Payment.setSum(sum);
      Payment.setUsername(username);

      return Payment;
    }

    @Override
    public FinalDocumentBuilder<Payment> withNumber(String number) {
      this.number = number;
      return this;
    }

    @Override
    public DateDocumentBuilder withEmployee(String employee) {
      this.employee = employee;
      return this;
    }

    @Override
    public NumberDocumentBuilder withSum(BigDecimal sum) {
      this.sum = sum;
      return this;
    }

    @Override
    public SumDocumentBuilder withUsername(String username) {
      this.username = username;
      return this;
    }

    @Override
    public UsernameDocumentBuilder withDate(LocalDate date) {
      this.date = date;
      return this;
    }
  }

  @Override
  public String toString() {
    return "Номер: " + getNumber() + "\nДата: "
        + getDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN)) + "\nПользователь: "
        + getUsername() + "\nСумма: " + getSum() + "\nСотрудник: " + getEmployee();
  }
}
