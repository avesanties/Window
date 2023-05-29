package ave.avesanties.window.controller;

import ave.avesanties.window.model.AbstractDocument;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import org.springframework.stereotype.Component;

@Component
public class MainForm implements ActionListener {

  private Presenter presenter;

  private JFrame frame;

  private JButton invoiceBtn, paymentBtn, paymentAppBtn, saveBtn, loadBtn, viewBtn, delBtn, exitBtn;

  private JTable table;

  private JScrollPane scrollPane;

  public MainForm(Presenter presenter) {
    this.presenter = presenter;
  }

  public void loadData() {
    final List<String> docs = presenter.getAllDocs();
    ((CustomTableModel) table.getModel()).refreshData(docs);

  }

  public void initPanels() {

    frame = new JFrame("Работа с документами");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container framePane = frame.getContentPane();
    framePane.setLayout(new GridBagLayout());
    GridBagConstraints constr;

    invoiceBtn = new JButton("Накладная");
    invoiceBtn.addActionListener(this);
    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.gridx = 1;
    constr.gridy = 0;
    constr.insets = new Insets(5, 10, 5, 10);
    framePane.add(invoiceBtn, constr);

    paymentBtn = new JButton("Платёжка");
    paymentBtn.addActionListener(this);
    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.gridx = 1;
    constr.gridy = 1;
    constr.insets = new Insets(5, 10, 5, 10);
    framePane.add(paymentBtn, constr);

    paymentAppBtn = new JButton("Заявка на оплату");
    paymentAppBtn.addActionListener(this);
    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.gridx = 1;
    constr.gridy = 2;
    constr.insets = new Insets(5, 10, 5, 10);
    framePane.add(paymentAppBtn, constr);

    saveBtn = new JButton("Сохранить");
    saveBtn.addActionListener(this);
    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.gridx = 1;
    constr.gridy = 3;
    constr.insets = new Insets(5, 10, 5, 10);
    framePane.add(saveBtn, constr);

    loadBtn = new JButton("Загрузить");
    loadBtn.addActionListener(this);
    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.gridx = 1;
    constr.gridy = 4;
    constr.insets = new Insets(5, 10, 5, 10);
    framePane.add(loadBtn, constr);

    viewBtn = new JButton("Просмотр");
    viewBtn.addActionListener(this);
    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.gridx = 1;
    constr.gridy = 5;
    constr.insets = new Insets(5, 10, 5, 10);
    framePane.add(viewBtn, constr);

    delBtn = new JButton("Удалить");
    delBtn.addActionListener(this);
    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.gridx = 1;
    constr.gridy = 6;
    constr.insets = new Insets(5, 10, 5, 10);
    framePane.add(delBtn, constr);

    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.weighty = 1.0;
    constr.gridx = 1;
    constr.gridy = 7;
    JPanel empty = new JPanel();
    framePane.add(empty, constr);

    exitBtn = new JButton("Выход");
    exitBtn.addActionListener(this);
    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.gridx = 1;
    constr.gridy = 8;
    constr.anchor = GridBagConstraints.PAGE_END;
    constr.insets = new Insets(5, 10, 5, 10);
    framePane.add(exitBtn, constr);

    table = new JTable(new CustomTableModel());
    scrollPane = new JScrollPane(table);
    constr = new GridBagConstraints();
    constr.fill = GridBagConstraints.BOTH;
    constr.ipady = 10;
    constr.weighty = 0.0;
    constr.weightx = 0.8;
    constr.gridheight = 9;
    constr.gridx = 0;
    constr.gridy = 0;
    table.setFillsViewportHeight(true);
    table.getColumnModel().getColumn(0).setPreferredWidth(20);
    table.getColumnModel().getColumn(1).setPreferredWidth(200);
    framePane.add(scrollPane, constr);

    frame.setMinimumSize(new Dimension(200, 200));
    frame.setSize(new Dimension(700, 470));

    frame.setVisible(true);
  }

  @SuppressWarnings("serial")
  class CustomTableModel extends AbstractTableModel {

    private final String[] columnNames = new String[] {"Удалить", "Документ"};

    private List<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

    @Override
    public int getColumnCount() {
      return columnNames.length;
    }

    @Override
    public int getRowCount() {
      return data.size();
    }

    @Override
    public String getColumnName(int col) {
      return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
      return data.get(row).get(col);
    }

    @Override
    public Class<?> getColumnClass(int c) {
      return getValueAt(0, c).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int col) {
      if (col > 0) {
        return false;
      } else {
        return true;
      }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
      data.get(row).set(col, value);
      fireTableCellUpdated(row, col);
    }

    public void refreshData(List<String> docs) {
      final List<ArrayList<Object>> newData = new ArrayList<ArrayList<Object>>();
      for (String doc : docs) {
        ArrayList<Object> row = new ArrayList<Object>();
        row.add(Boolean.FALSE);
        row.add(doc);
        newData.add(row);
      }

      data = newData;
      super.fireTableDataChanged();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(invoiceBtn)) {
      InvoiceCreationalForm frm = new InvoiceCreationalForm();
      int res = frm.showDialog();
      if (res == JOptionPane.OK_OPTION) {
        presenter.saveDoc(frm.getInput());
      }
      loadData();
    } else if (e.getSource().equals(paymentBtn)) {
      PaymentCreationalForm frm = new PaymentCreationalForm();
      int res = frm.showDialog();
      if (res == JOptionPane.OK_OPTION) {
        var doc = frm.getInput();
        presenter.saveDoc(doc);
        System.out.println(doc.getType());
      }
      loadData();
    } else if (e.getSource().equals(paymentAppBtn)) {
      PaymentAppCreationalForm frm = new PaymentAppCreationalForm();
      int res = frm.showDialog();
      if (res == JOptionPane.OK_OPTION) {
        presenter.saveDoc(frm.getInput());
      }
      loadData();
    } else if (e.getSource().equals(saveBtn)) {
      final int row = table.getSelectedRow();
      if (row == -1) {
        JOptionPane.showMessageDialog(null, "No rows selected");
        return;
      }
      JFileChooser saveDialog = new JFileChooser();
      int res = saveDialog.showSaveDialog(null);
      if (res == JFileChooser.APPROVE_OPTION) {
        final File file = saveDialog.getSelectedFile();
        AbstractDocument doc = presenter.getDoc(row);
        try {
          presenter.saveDocToExternalStorage(doc, file);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, "No such directory or access is restricted", "",
              JOptionPane.ERROR_MESSAGE);
          ex.printStackTrace();
        }
      }
    } else if (e.getSource().equals(loadBtn)) {
      final JFileChooser openDialog = new JFileChooser();
      int res = openDialog.showOpenDialog(null);
      if (res == JFileChooser.APPROVE_OPTION) {
        final File file = openDialog.getSelectedFile();
        try {
          presenter.openDocFromExternalStorage(file);
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(null, "No such file or access is restricted", "",
              JOptionPane.ERROR_MESSAGE);
          ex.printStackTrace();
        }
        loadData();
      }
    } else if (e.getSource().equals(viewBtn)) {
      final int row = table.getSelectedRow();
      if (row == -1) {
        JOptionPane.showMessageDialog(null, "No rows selected");
        return;
      }
      AbstractDocument doc = presenter.getDoc(row);

      DocumentViewForm frm = new DocumentViewForm(doc.getType(), doc.toString());
      frm.showDialog();
    } else if (e.getSource().equals(delBtn)) {
      final Set<Integer> removedRows = new HashSet<Integer>();
      final int delIdx = 0;
      for (int i = 0; i < table.getRowCount(); i++) {
        if (table.getValueAt(i, delIdx).equals(Boolean.TRUE)) {
          removedRows.add(i);
        }
      }
      presenter.delDocs(removedRows);
      loadData();
    } else if (e.getSource().equals(exitBtn)) {
      System.exit(0);
    }
  }

}
