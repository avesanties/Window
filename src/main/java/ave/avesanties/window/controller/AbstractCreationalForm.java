package ave.avesanties.window.controller;

import ave.avesanties.window.model.AbstractDocument;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstractCreationalForm {

  protected static final String INPUT_MESSAGE = "Введите данные ниже";

  protected static final int NUMBER_INPUT_SIZE = 8;

  protected static final int STRING_INPUT_SIZE = 12;

  protected JTextField number, date, username, sum;

  protected JPanel panel;

  protected int confirmCode;

  protected AbstractCreationalForm() {
    number = new JTextField(NUMBER_INPUT_SIZE);
    date = new JTextField(STRING_INPUT_SIZE);
    username = new JTextField(STRING_INPUT_SIZE);
    sum = new JTextField(NUMBER_INPUT_SIZE);

    panel = new JPanel();
    panel.add(new JLabel("Номер"));
    panel.add(number);
    panel.add(new JLabel("Дата"));
    panel.add(date);
    panel.add(new JLabel("Пользователь"));
    panel.add(username);
    panel.add(new JLabel("Сумма"));
    panel.add(sum);

    confirmCode = JOptionPane.CANCEL_OPTION;
  }

  public abstract int showDialog();

  public abstract AbstractDocument getInput();
}
