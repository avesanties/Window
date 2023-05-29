package ave.avesanties.window.controller;

import static ave.avesanties.window.constants.Constants.DATE_PATTERN;

import ave.avesanties.window.model.AbstractDocument;
import ave.avesanties.window.model.PaymentApplication;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PaymentAppCreationalForm extends AbstractCreationalForm {
  
  protected JTextField currency, rate, contractor, fee;

  public PaymentAppCreationalForm() {
    super();
    currency = new JTextField();
    rate = new JTextField(NUMBER_INPUT_SIZE);
    contractor = new JTextField(STRING_INPUT_SIZE);
    fee = new JTextField(NUMBER_INPUT_SIZE);

    panel.add(new JLabel("Валюта"));
    panel.add(currency);
    panel.add(new JLabel("Курс"));
    panel.add(rate);
    panel.add(new JLabel("Контрагент"));
    panel.add(contractor);
    panel.add(new JLabel("Комиссия"));
    panel.add(fee);
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

    return PaymentApplication.Builder.createBuilder().withContractor(contractor.getText())
        .withFee(new BigDecimal(fee.getText())).withRate(new BigDecimal(rate.getText()))
        .withCurrency(currency.getText())
        .withDate(LocalDate.parse(date.getText(), DateTimeFormatter.ofPattern(DATE_PATTERN)))
        .withUsername(username.getText()).withSum(new BigDecimal(sum.getText()))
        .withNumber(number.getText()).build();
  }
}
