package ave.avesanties.window.model;

import static ave.avesanties.window.constants.Constants.DATE_PATTERN;

import ave.avesanties.window.model.builders.AmountDocumentBuilder;
import ave.avesanties.window.model.builders.CurrencyDocumentBuilder;
import ave.avesanties.window.model.builders.DateDocumentBuilder;
import ave.avesanties.window.model.builders.FinalDocumentBuilder;
import ave.avesanties.window.model.builders.NumberDocumentBuilder;
import ave.avesanties.window.model.builders.ProductDocumentBuilder;
import ave.avesanties.window.model.builders.RateDocumentBuilder;
import ave.avesanties.window.model.builders.SumDocumentBuilder;
import ave.avesanties.window.model.builders.UsernameDocumentBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Invoice extends AbstractDocument {

  private String currency;

  private BigDecimal rate;

  private String product;

  private BigDecimal amount;

  private Invoice() {

  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getRate() {
    return rate;
  }

  public String getProduct() {
    return product;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  private void setCurrency(String currency) {
    this.currency = currency;
  }

  private void setRate(BigDecimal rate) {
    this.rate = rate;
  }

  private void setProduct(String product) {
    this.product = product;
  }

  private void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public static class Builder implements CurrencyDocumentBuilder, RateDocumentBuilder,
      ProductDocumentBuilder, AmountDocumentBuilder, DateDocumentBuilder, UsernameDocumentBuilder,
      SumDocumentBuilder, NumberDocumentBuilder, FinalDocumentBuilder<Invoice> {

    private static final String TYPE = "Накладная";

    private String number;

    private LocalDate date;

    private String username;

    private BigDecimal sum;

    private String currency;

    private BigDecimal rate;

    private String product;

    private BigDecimal amount;

    private Builder() {

    }

    public static ProductDocumentBuilder createBuilder() {
      return new Builder();
    }

    @Override
    public Invoice build() {
      Invoice invoice = new Invoice();
      invoice.setType(TYPE);
      invoice.setDate(date);
      invoice.setNumber(number);
      invoice.setSum(sum);
      invoice.setUsername(username);
      invoice.setCurrency(currency);
      invoice.setProduct(product);
      invoice.setRate(rate);
      invoice.setAmount(amount);

      return invoice;
    }

    @Override
    public FinalDocumentBuilder<Invoice> withNumber(String number) {
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
    public RateDocumentBuilder withAmount(BigDecimal amount) {
      this.amount = amount;
      return this;
    }

    @Override
    public AmountDocumentBuilder withProduct(String product) {
      this.product = product;
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

  }

  @Override
  public String toString() {
    return "Номер: " + getNumber() + "\nДата: "
        + getDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN)) + "\nПользователь: "
        + getUsername() + "\nСумма: " + getSum() + "\nВалюта: " + getCurrency() + "\nКурс: "
        + getRate() + "\nТовар: " + getProduct() + "\nКоличество: " + getAmount();
  }
}
