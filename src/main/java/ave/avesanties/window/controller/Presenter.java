package ave.avesanties.window.controller;

import static ave.avesanties.window.constants.Constants.DATE_PATTERN;
import ave.avesanties.window.model.AbstractDocument;
import ave.avesanties.window.model.Invoice;
import ave.avesanties.window.model.Payment;
import ave.avesanties.window.model.PaymentApplication;
import ave.avesanties.window.service.Readable;
import ave.avesanties.window.service.Writable;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class Presenter {

  private List<AbstractDocument> data = new ArrayList<AbstractDocument>();

  private Writable writableService;

  private Readable readableService;

  {
    var invoice = Invoice.Builder.createBuilder().withProduct("масло")
        .withAmount(new BigDecimal("54.23")).withRate(new BigDecimal("78.92")).withCurrency("RUB")
        .withDate(LocalDate.parse("01.01.1991", DateTimeFormatter.ofPattern(DATE_PATTERN)))
        .withUsername("Иванова").withSum(new BigDecimal("101.00")).withNumber("К52").build();

    var payment = Payment.Builder.createBuilder().withEmployee("Петров")
        .withDate(LocalDate.parse("01.01.1991", DateTimeFormatter.ofPattern(DATE_PATTERN)))
        .withUsername("Иванова").withSum(new BigDecimal("101.00")).withNumber("К52").build();

    var peymentApp = PaymentApplication.Builder.createBuilder().withContractor("Галадзе")
        .withFee(new BigDecimal("12.13")).withRate(new BigDecimal("78.92")).withCurrency("RUB")
        .withDate(LocalDate.parse("01.01.1991", DateTimeFormatter.ofPattern(DATE_PATTERN)))
        .withUsername("Иванова").withSum(new BigDecimal("101.00")).withNumber("К52").build();

    data.addAll(Arrays.asList(invoice, payment, peymentApp));
  }

  public Presenter(Writable writableService, Readable readableService) {
    this.writableService = writableService;
    this.readableService = readableService;
  }

  public void openDocFromExternalStorage(File file) throws IOException {
    AbstractDocument doc = readableService.read(file);
    saveDoc(doc);
  }

  public void saveDocToExternalStorage(AbstractDocument doc, File file) throws Exception {
    writableService.write(doc, file);
  }

  public AbstractDocument getDoc(int id) {
    return data.get(id);
  }

  public void saveDoc(AbstractDocument doc) {
    data.add(doc);
  }

  public List<String> getAllDocs() {
    return data.stream().map(this::toDisplayDoc).map(DisplayDoc::toString).toList();
  }

  private DisplayDoc toDisplayDoc(AbstractDocument doc) {
    return new DisplayDoc(doc.getType(), doc.getDate(), doc.getNumber());
  }

  public void delDocs(Set<Integer> removedIds) {
    List<AbstractDocument> newData = new ArrayList<AbstractDocument>();
    for (int i = 0; i < data.size(); i++) {
      if (!removedIds.contains(i)) {
        newData.add(data.get(i));
      }
    }

    data = newData;
  }

  class DisplayDoc {

    private final String type;

    private final LocalDate date;

    private final String number;

    public DisplayDoc(String type, LocalDate date, String number) {
      this.type = type;
      this.date = date;
      this.number = number;
    }

    public String getType() {
      return type;
    }

    public LocalDate getDate() {
      return date;
    }

    public String getNumber() {
      return number;
    }

    @Override
    public String toString() {
      return getType() + " от " + getDate().format(DateTimeFormatter.ofPattern(DATE_PATTERN))
          + " номер " + getNumber();
    }
  }
}
