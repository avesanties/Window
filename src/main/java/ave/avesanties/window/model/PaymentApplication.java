package ave.avesanties.window.model;

import static ave.avesanties.window.constants.Constants.DATE_PATTERN;

import ave.avesanties.window.model.builders.ContractorDocumentBuilder;
import ave.avesanties.window.model.builders.CurrencyDocumentBuilder;
import ave.avesanties.window.model.builders.DateDocumentBuilder;
import ave.avesanties.window.model.builders.FeeDocumentBuilder;
import ave.avesanties.window.model.builders.FinalDocumentBuilder;
import ave.avesanties.window.model.builders.NumberDocumentBuilder;
import ave.avesanties.window.model.builders.RateDocumentBuilder;
import ave.avesanties.window.model.builders.SumDocumentBuilder;
import ave.avesanties.window.model.builders.UsernameDocumentBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentApplication extends AbstractDocument {

  private String currency;

  private BigDecimal rate;

  private String contractor;

  private BigDecimal fee;

  private PaymentApplication() {

  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public String getContractor() {
    return contractor;
  }

  public BigDecimal getFee() {
    return fee;
  }

  private void setCurrency(String currency) {
    this.currency = currency;
  }

  private void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  private void setContractor(String contractor) {
    this.contractor = contractor;
  }

  private void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public static class Builder implements ContractorDocumentBuilder, FeeDocumentBuilder,
      CurrencyDocumentBuilder, RateDocumentBuilder, DateDocumentBuilder, UsernameDocumentBuilder,
      SumDocumentBuilder, NumberDocumentBuilder, FinalDocumentBuilder<PaymentApplication> {

    private static final String TYPE = "Заявка на оплату";

    private String number;

    private LocalDate date;

    private String username;

    private BigDecimal sum;

    private String currency;

    private BigDecimal rate;

    private String contractor;

    private BigDecimal fee;

    private Builder() {

    }

    public static ContractorDocumentBuilder createBuilder() {
      return new Builder();
    }

    @Override
    public PaymentApplication build() {
      PaymentApplication paymentApplication = new PaymentApplication();
      paymentApplication.setType(TYPE);
      paymentApplication.setDate(date);
      paymentApplication.setNumber(number);
      paymentApplication.setSum(sum);
      paymentApplication.setUsername(username);
      paymentApplication.setCurrency(currency);
      paymentApplication.setRate(rate);
      paymentApplication.setContractor(contractor);
      paymentApplication.setFee(fee);

      return paymentApplication;
    }

    @Override
    public FinalDocumentBuilder<PaymentApplication> withNumber(String number) {
      this.number = number;
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

    @Override
    public CurrencyDocumentBuilder withRate(BigDecimal rate) {
      this.rate = rate;
      return this;
    }

    @Override
    public DateDocumentBuilder withCurrency(String currency) {
      this.currency = currency;
      return this;
    }

    @Override
    public RateDocumentBuilder withFee(BigDecimal fee) {
      this.fee = fee;
      return this;
    }

    @Override
    public FeeDocumentBuilder withContractor(String contractor) {
      this.contractor = contractor;
      return this;
    }
  }

  @Override
  public String toString() {
    return "Номер: " + getNumber() + "\nДата: "
        + getDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN)) + "\nПользователь: "
        + getUsername() + "\nСумма: " + getSum() + "\nВалюта: " + getCurrency() + "\nКурс: "
        + getRate() + "\nКонтрагент: " + getContractor() + "\nКомиссия: " + getFee();
  }
}
