package ave.avesanties.window.controller;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public final class DocumentViewForm {

  private final JPanel panel;

  private final String header;

  public DocumentViewForm(String header, String content) {
    this.header = header;
    JTextArea textArea = new JTextArea();
    textArea.setWrapStyleWord(false);
    textArea.setColumns(40);
    textArea.setRows(15);
    textArea.setText(content);
    textArea.setEditable(false);
    panel = new JPanel();
    panel.add(textArea);
  }

  public void showDialog() {
    JOptionPane.showMessageDialog(null, panel, header, JOptionPane.PLAIN_MESSAGE, null);
  }
}
