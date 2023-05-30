package ave.avesanties.window.controller;

import static ave.avesanties.window.constants.Constants.DATE_PATTERN;

import ave.avesanties.window.model.AbstractDocument;
import ave.avesanties.window.model.Invoice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public final class InvoiceCreationalForm extends AbstractCreationalForm {

  protected JTextField currency, rate, product, amount;

  public InvoiceCreationalForm() {
    super();
    currency = new JTextField(STRING_INPUT_SIZE);
    rate = new JTextField(NUMBER_INPUT_SIZE);
    product = new JTextField(STRING_INPUT_SIZE);
    amount = new JTextField(NUMBER_INPUT_SIZE);

    panel.add(new JLabel("Валюта"));
    panel.add(currency);
    panel.add(new JLabel("Курс"));
    panel.add(rate);
    panel.add(new JLabel("Товар"));
    panel.add(product);
    panel.add(new JLabel("Количество"));
    panel.add(amount);
  }

  @Override
  public int showDialog() {
    confirmCode = JOptionPane.showConfirmDialog(null, panel, INPUT_MESSAGE,
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    return confirmCode;
  }

  @Override
  public AbstractDocument getInput() {
    if (confirmCode != JOptionPane.OK_OPTION) {
      throw new IllegalStateException("User has canceled document creation on previous step");
    }

    return Invoice.Builder.createBuilder().withProduct(product.getText())
        .withAmount(new BigDecimal(amount.getText())).withRate(new BigDecimal(rate.getText()))
        .withCurrency(currency.getText())
        .withDate(LocalDate.parse(date.getText(), DateTimeFormatter.ofPattern(DATE_PATTERN)))
        .withUsername(username.getText()).withSum(new BigDecimal(sum.getText()))
        .withNumber(number.getText()).build();
  }
}
