package ave.avesanties.window.controller;

import static ave.avesanties.window.constants.Constants.DATE_PATTERN;

import ave.avesanties.window.model.AbstractDocument;
import ave.avesanties.window.model.Payment;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public final class PaymentCreationalForm extends AbstractCreationalForm {

  protected JTextField employee;

  public PaymentCreationalForm() {
    super();
    employee = new JTextField(STRING_INPUT_SIZE);

    panel.add(new JLabel("Сотрудник"));
    panel.add(employee);
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

    return Payment.Builder.createBuilder().withEmployee(employee.getText())
        .withDate(LocalDate.parse(date.getText(), DateTimeFormatter.ofPattern(DATE_PATTERN)))
        .withUsername(username.getText()).withSum(new BigDecimal(sum.getText()))
        .withNumber(number.getText()).build();
  }
}
